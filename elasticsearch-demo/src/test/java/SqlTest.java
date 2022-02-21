import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.SqlElasticSearchRequestBuilder;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.yaojinwei.study.es.TestsConstants.TEST_INDEX_ACCOUNT;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class SqlTest {

    private static final int TEMP_DIR_ATTEMPTS = 3;
    private static TransportClient client;
    private static SearchDao searchDao;

    public static SearchDao getSearchDao() {
        return searchDao;
    }

    @BeforeClass
    public static void setUp() throws Exception {
        InputStream inputStreamCa = SqlTest.class.getResourceAsStream("ca.crt");

        InputStream inputStreamP12 = SqlTest.class.getResourceAsStream("elastic-certificates.p12");

        String caPath = inputStreamToFile(inputStreamCa, "ca.crt");
        String p12Path = inputStreamToFile(inputStreamP12, "elastic-certificates.p12");


        Settings settings = Settings.builder()
                .put("cluster.name", "cardicare")
                //.put("client.transport.sniff", true)
                .put("client.transport.ignore_cluster_name", true)
                .put("xpack.security.enabled", true)
                .put("xpack.security.transport.ssl.enabled", true)
                .put("xpack.security.transport.ssl.keystore.path", p12Path)
                .put("xpack.security.transport.ssl.truststore.path", p12Path)
                .put("xpack.ssl.certificate_authorities", caPath)
                .put("xpack.security.transport.ssl.verification_mode", "certificate")
//                .put("client.transport.ignore_cluster_name",true)
                .put("xpack.security.user", "elastic:AliHealth@$#@!160533ops")
                .build();
        client = new PreBuiltXPackTransportClient(settings).addTransportAddress(getTransportAddress());

        NodesInfoResponse nodeInfos = client.admin().cluster().prepareNodesInfo().get();
        String clusterName = nodeInfos.getClusterName().value();
        System.out.println(String.format("Found cluster... cluster name: %s", clusterName));


        searchDao = new SearchDao(client);

        //refresh to make sure all the docs will return on queries
//        client.admin().indices().prepareRefresh(TEST_INDEX + "*").get();

        System.out.println("Finished the setup process...");
    }

    @Test
    public void selectSpecificFields() throws IOException, SqlParseException, SQLFeatureNotSupportedException {
        String[] arr = new String[] {"age", "account_number"};
        Set expectedSource = new HashSet(Arrays.asList(arr));

        SearchHits response = query("select * from open_parent_child_test_agg_2019_v3 where or_patient_id = '1777f89fc661fd05104'");
        SearchHit[] hits = response.getHits();
        for(SearchHit hit : hits) {
            Assert.assertEquals(expectedSource, hit.getSourceAsMap().keySet());
        }
    }


    private SearchHits query(String query) throws SqlParseException, SQLFeatureNotSupportedException {
        SearchDao searchDao = getSearchDao();
        SqlElasticSearchRequestBuilder select = (SqlElasticSearchRequestBuilder) searchDao.explain(query).explain();
        return ((SearchResponse)select.get()).getHits();
    }

    protected static TransportAddress getTransportAddress() throws UnknownHostException {
        String host = "39.98.209.81";//System.getenv("ES_TEST_HOST");
        String port = System.getenv("ES_TEST_PORT");

        if(host == null) {
            host = "localhost";
            System.out.println("ES_TEST_HOST enviroment variable does not exist. choose default 'localhost'");
        }

        if(port == null) {
            port = "9300";
            System.out.println("ES_TEST_PORT enviroment variable does not exist. choose default '9300'");
        }

        System.out.println(String.format("Connection details: host: %s. port:%s.", host, port));
        return new TransportAddress(InetAddress.getByName(host), Integer.parseInt(port));
    }


    public static String inputStreamToFile(InputStream inputStream, String targetFile) {
        OutputStream outputStream = null;
        try {
            File tempDir = createTempDir();
            File jarF = new File(tempDir, targetFile);
            File file = new File(jarF.getParentFile() + "/" + targetFile);
            file.mkdirs();
            if (file.exists()) {
                file.delete();
            }
            outputStream = new FileOutputStream(file);
            int byteCount = 0;
            byte[] bytes = new byte[1024];
            while ((byteCount = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, byteCount);
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static File createTempDir() {
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        String baseName = System.currentTimeMillis() +"-";

        for (int counter = 0; counter < TEMP_DIR_ATTEMPTS; counter++) {
            File tempDir = new File(baseDir, baseName + counter);
            if (tempDir.mkdir()) {
                return tempDir;
            }
        }
        throw new IllegalStateException("Failed to create directory within"
                + TEMP_DIR_ATTEMPTS +" attempts (tried"
                + baseName +"0 to" + baseName + (TEMP_DIR_ATTEMPTS - 1) + ')');
    }
}

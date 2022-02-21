import com.alibaba.fastjson.JSON;
import com.alihealth.wiseframework.common.utils.DateUtils;
import com.yaojinwei.study.es.Application;
import com.yaojinwei.study.es.test.TestEntity;
import com.yaojinwei.study.es.test.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.alihealth.wiseframework.common.utils.DateUtils.DEFAULT_FORMAT;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, properties = "classpath:application.yml")
public class JdbcTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TestService testService;

    @Test
    public void testDataSource() throws Exception{
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select count(*) as cnt from open_parent_child_test_agg_2019_v3");
        ResultSet resultSet = ps.executeQuery();
        List<String> result = new ArrayList<String>();
        while (resultSet.next()) {
            result.add(resultSet.getDouble("cnt") + "");
        }

        ps.close();
        connection.close();
        Assert.assertTrue(result.size()==1);
        System.out.println(result.get(0));
//        Assert.assertTrue(result.get(0).equals("Heath,39,F"));
    }

    @Test
    public void testDataSource1() throws Exception{
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select or_name, or_age, patient_id from open_parent_child_test_agg_2019_v3");
        ResultSet resultSet = ps.executeQuery();
        List<String> result = new ArrayList<String>();
        while (resultSet.next()) {
            result.add(resultSet.getString("or_age") + "");
        }

        ps.close();
        connection.close();
        System.out.println(result.get(0));
//        Assert.assertTrue(result.size()==2000);
//        Assert.assertTrue(result.get(0).equals("Heath,39,F"));
    }
    @Test
    public void testNested() throws Exception{
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select transfer_hospital_date as or_age from open_parent_child_test_agg_2019_v3");
        ResultSet resultSet = ps.executeQuery();
        List<String> result = new ArrayList<String>();
        while (resultSet.next()) {
            result.add(resultSet.getString("or_age") + "");
        }

        ps.close();
        connection.close();
        System.out.println(result.get(0));
    }
    @Test
    public void testService(){
        List<TestEntity> select = testService.createQuery()
//                .whereEqualTo(TestEntity::getPatientId, "1777f89fc661fd05104") //DateUtils.parse("2019-01-01 00:00:00", DEFAULT_FORMAT)
                .andGreaterThan(TestEntity::getDischargeDate, "2000-01-01 00:00:00")
                .select();
        System.out.println(JSON.toJSONString(select));
    }
}

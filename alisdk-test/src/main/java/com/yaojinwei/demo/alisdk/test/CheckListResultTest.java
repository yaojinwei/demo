package com.yaojinwei.demo.alisdk.test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaFmhealthChecklistResultAddRequest;
import com.taobao.api.response.AlibabaFmhealthChecklistResultAddResponse;

/**
 * @author jinwei.yjw
 * @date 2018/6/14 18:02
 */
public class CheckListResultTest {
    public static void main(String[] args) throws ApiException {
        String url = "http://140.205.164.4/top/router/rest";
        String appkey = "24933430";
        String secret = "47911c0f554c6f1372ddaa4228205934";

        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaFmhealthChecklistResultAddRequest req = new AlibabaFmhealthChecklistResultAddRequest();
        req.setUserId(18000000305350100L);
        req.setAuthCode("1000100001");
        req.setResultCode(1L);
        req.setResultText("重度症状");
        req.setResultCaption("您的情况比较复杂，除了采用系统推荐的调节方案外，建议寻求医生帮助。");
        AlibabaFmhealthChecklistResultAddResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
}

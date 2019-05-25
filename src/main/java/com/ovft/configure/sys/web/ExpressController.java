
package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.ExpressBean;
import com.ovft.configure.sys.bean.HttpRequest;
import com.ovft.configure.sys.utils.MD5;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author vvtxw
 * @create 2019-05-21 14:53
 */


@RestController
@RequestMapping("researchems")
public class ExpressController {


    @PostMapping(value = "search")
    public WebResult researchEms() {
        String customer = "9D743BCB9EE6518A4570F7C70232BEF2";
        String param = "{\"com\":\"shunfeng\",\"num\":\"294060323023\",\"mobiletelephone\":\"15897763960\"}";
        String key = "jmeQKaLK9775";
        String sign = MD5.encode(param + key + customer);
        HashMap params = new HashMap();
        params.put("param", param);
        params.put("sign", sign);
        params.put("customer", customer);
        String resp;
        try {
            resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
            System.out.println(resp);
            JSONObject jsonpObject = JSONObject.fromObject(resp);
            JSONArray data = jsonpObject.getJSONArray("data");
            List<ExpressBean> expressBeans = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                JSONObject o = data.getJSONObject(i);
                System.out.println(o.get("content"));
                System.out.println(o.get("ftime"));
                System.out.println(o.get("context"));
                ExpressBean bean = new ExpressBean();
                bean.setContent((String) o.get("content"));
                bean.setFtime((String) o.get("ftime"));
                bean.setContext((String) o.get("context"));
                expressBeans.add(bean);
            }
            return new WebResult(StatusCode.OK, "查询成功", expressBeans);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new WebResult(StatusCode.OK, "查询失败");
    }

}


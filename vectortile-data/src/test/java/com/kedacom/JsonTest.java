package com.kedacom;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedacom.vector.entity.Text;

import java.io.File;

/**
 * @Auther: YinPeng
 * @Date: 2019/2/20 0020 09:26
 * @Description:
 */
public class JsonTest {
    public static void main(String[] args) {
        Text t = new Text();
        t.setAdmincode("320505");
        t.setFeatid("1111");
        t.setKeyword("hhhh");
        System.out.println(JSON.toJSONString(t));
        jsonOutPut(JSON.toJSON(t));
    }

    public static void jsonOutPut(Object obj) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("D:/river-site.geojson"), obj);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}

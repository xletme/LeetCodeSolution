package utils;



import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class Sample {

    public static void main(String[] args) {
        String host = "https://ocrapi-document.taobao.com";
        String path = "/ocrservice/document";
        String method = "POST";
        String appcode = "7eb464eceaf3494c953060fabfd4e9cc";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();

        //示例：身份证正面 http://jwell-ecommerce-cloud.cn-beijing.oss.aliyuncs.com/e-commerce/202009/b34a6c1f-7cb7-ca55-9d54-39f78d8a8b34.jpg
        //示例：身份证背面 http://jwell-ecommerce-cloud.cn-beijing.oss.aliyuncs.com/e-commerce/202009/673dac22-35ff-605b-cc15-39f78d8b16be.jpg
        String bodys = "{\"url\":\"http://jwell-ecommerce-cloud.cn-beijing.oss.aliyuncs.com/e-commerce/202009/673dac22-35ff-605b-cc15-39f78d8b16be.jpg\"}";


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            System.out.println(response.toString());
            //获取response的body
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println(jsonObject.get("content"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> idCardInfoA =  convertIdCardInfoA(null);
        System.out.println(idCardInfoA);

        Map<String, String> idCardInfoB = convertIdCardInfoB(null);
        System.out.println(idCardInfoB);
    }

    public static Map<String, String> convertIdCardInfoA(String content) {
        content = "中华人民共和国 居 民 身 份 证 签发机关 重庆市公安局九龙坡分局 有效期限 2005.06.23-2025.06.23";
        Map<String, String> result = new HashMap<>();
        String trimContent = content.replaceAll(" ", "").trim();
        String vaildStr = trimContent.substring(trimContent.lastIndexOf("有效期限") + 4);
        String[] valid = vaildStr.split("-");
        result.put("valid-begin", valid[0]);
        result.put("valid-end", valid[1]);
        return result;
    }

    public static Map<String, String> convertIdCardInfoB(String content) {
        content = "姓 名 徐 越 性 别 女 民 族 汉 出 生 1964 年 10 月13日 住 址 重庆市九龙坡区石小路 197号附1号2-1 公民身份号码 510213196410131645 ";
        Map<String, String> result = new HashMap<>();
        String trimContent = content.replaceAll(" ", "").trim();
        String name = trimContent.substring(trimContent.indexOf("姓名") + 2, trimContent.indexOf("性别"));
        String address = trimContent.substring(trimContent.indexOf("住址") + 2, trimContent.indexOf("公民身份号码"));
        String idCardNo = trimContent.substring(trimContent.indexOf("公民身份号码") + 6);
        result.put("name", name);
        result.put("address", address);
        result.put("idCardNo", idCardNo);
        return result;
    }



}

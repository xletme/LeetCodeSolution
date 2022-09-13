package utils;

import cn.com.infosec.util.Base64;
import com.pingan.b2bic.Sign.sign.SignUtil;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author maoxin
 * @version 1.0
 * @description:
 * @date 2022/7/15 15:55
 */
public class TestSign {

    public static void main(String[] args) throws Exception {
        String ALG = "DesEde/CBC/PKCS5Padding";

        String fsrc = "/Users/maoxin/pay/JY2022081142731.txt";

        String key = "qhcf2Q5EMivR6WagjlfSJlUhtll/idQA";

        //��Ҫ�л��ɼ��ܣ����н�if���false��Ϊtrue����
        if(false){
            Random random = new SecureRandom();
            byte[] bkey = new byte[24];
            random.nextBytes(bkey);
            key = new String(Base64.encode(bkey));
            System.out.println("����=[" + key + "]");
            String srcFile = fsrc;
            String zipFile = srcFile + ".zip";
            String encFile = srcFile + ".enc";
            // ��ѹ
            SignUtil.compress(srcFile, zipFile);
            // ����
            SignUtil.encrypt(zipFile, encFile, bkey, ALG, "DesEde", null);
        } else {
            String desFile = fsrc;

            String srcFile = fsrc + ".enc";
            String zipFile = fsrc + ".zip";
            byte[] bkey = Base64.decode(key.getBytes());
            // ����
            SignUtil.decrypt(srcFile, zipFile, bkey, ALG, "DesEde", null);
            // ��ѹ
            SignUtil.uncompress(zipFile, desFile);
        }
    }
}


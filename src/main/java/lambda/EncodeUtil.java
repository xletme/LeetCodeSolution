package lambda;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;
public class EncodeUtil {
    private static final Base64.Decoder DECODER = Base64.getDecoder();
    private static final Base64.Encoder ENCODER = Base64.getEncoder();
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String AES = "AES";
    private static final String Cipher7Mode = "AES/ECB/PKCS7Padding";
    public static void main(String[] args) throws Exception {
        String content = "sdkfsdfksdjfkj123";
        String password = "adope@qi3@rcfg#k28c";
        System.out.println("原内容：\n" + content);
        String tempContent = encrypt(content, password);
        System.err.println("加密后：\n" + tempContent);
        tempContent = decrypt(tempContent, password);
        System.out.println("解密后：\n" + tempContent);
    }
    private static String createKey(String password) {
        if (password == null) {
            password = "";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append(password);
        while (sb.length() < 32) {
            sb.insert(0, '0');
        }
        if (sb.length() > 32) {
            sb.setLength(32);
        }
        return sb.toString();
    }
    /**
     * AES ECB PKCS7Padding 解密
     *
     * @param content 需要解密的内容
     * @param aesKey  解密用的Key
     * @return 解密后的内容
     * @throws Exception 错误异常
     */
    public static String decrypt(String content, String aesKey) throws Exception {
        if (content == null || content.length() == 0)
            return null;
        byte[] newContent = DECODER.decode(content);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        String key = createKey(aesKey);
        Cipher cipher = Cipher.getInstance(Cipher7Mode);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(CHARSET), AES));
        byte[] result = cipher.doFinal(newContent);
        if (result != null && result.length > 0) {
            return new String(result, CHARSET);
        }
        return null;
    }
    /**
     * AES ECB PKCS7Padding 加密
     *
     * @param content 需要加密的内容
     * @param aesKey  加密用的Key
     * @return 加密后的内容
     * @throws Exception 错误异常
     */
    public static String encrypt(String content, String aesKey) throws Exception {
        if (content == null || content.length() == 0)
            return null;
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        String key = createKey(aesKey);
        Cipher cipher = Cipher.getInstance(Cipher7Mode);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(CHARSET), AES));
        byte[] result = cipher.doFinal(content.getBytes(CHARSET));
        if (result != null && result.length > 0) {
            return ENCODER.encodeToString(result);
        }
        return null;
    }
}
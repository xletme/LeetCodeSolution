package poi;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author maoXin
 * @Description
 * @Date 16:27 2022/1/21
 */
public class DownLoadFileToLocal {

    /**
     * @Description: 方法功能，读取多个阿里云链接， 新建一个文件，把文件下载到文件夹里面
     * @Date: 2022/1/21 16:33
     */
    @Test
    public void testDownloadAndSaveToLocal() {
        String aliDownLoadUrl1 = "//jwell-ecommerce-cloud.oss-cn-beijing.aliyuncs.com/e-commerce/uploads/202105/12791a07-9077-9936-83ce-39fc5c5138da.jpg";
        String aliDownLoadUrl2 = "//jwell-ecommerce-cloud.oss-cn-beijing.aliyuncs.com/e-commerce/uploads/202105/f6f6555f-2d12-6880-551a-39fc5c520528.jpg";
        String aliDownLoadUrl3 = "//jwell-ecommerce-cloud.oss-cn-beijing.aliyuncs.com/e-commerce/uploads/202105/b1be87e2-b0f2-91bd-ba7c-39fc5c538315.jpg";
        String aliDownLoadUrl4 = "//jwell-ecommerce-cloud.oss-cn-beijing.aliyuncs.com/e-commerce/uploads/202105/a1fac54d-e571-b77d-3475-39fc5c5538b2.jpg";
        List<String> downLoadList =
                new ArrayList<>(Arrays.asList(aliDownLoadUrl1, aliDownLoadUrl2, aliDownLoadUrl3, aliDownLoadUrl4));
        String dir = "C:\\Users\\10178\\Desktop\\融通微链资料\\testDownLoad\\";
        File file = new File(dir);
        if (file.exists()) {
            boolean delete = file.delete();
            String deleteDesc = delete ? "成功" : "失败";
            System.out.println("删除文件" + deleteDesc);
        }
        boolean isSuccess = file.mkdir();
        String mkdirDesc = isSuccess ? "成功" : "失败";
        System.out.println("创建文件夹" + mkdirDesc);
        for (String urlLink : downLoadList) {
            String name  = urlLink.substring(urlLink.lastIndexOf("/"));
            urlLink = "http:" + urlLink;
            downloadByUrl(urlLink, dir, name);
        }
    }

    /**
     * @Description: url 需要下载的链接  path保存的目录
     * @Date: 2022/1/21 16:40
     */
    public void downloadByUrl(String url, String path, String name) {
        URL uri = null;
        try {
            uri = new URL(url);
            URLConnection urlConnection = uri.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            FileOutputStream outputStream = new FileOutputStream(new File(path + name));

            int len;
            byte[] bs = new byte[1024];
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
            }

            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

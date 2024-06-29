package stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author maoxin
 * @version 1.0
 * @description:
 * @date 2023/10/20 14:44
 */
public class StreamUtils {

    public void copyData(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }

   
}

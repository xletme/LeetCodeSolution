package poi;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author maoxin
 * @version 1.0
 * @description:
 * @date 2023/4/19 15:51
 */
public class PdfExporter {

    public static void exportPdf(String data, String filePath) throws IOException, DocumentException {
        // 创建Document对象
        Document document = new Document();
        // 创建PdfWriter对象
        PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
        // 打开文档
        document.open();
        // 添加内容
        document.add(new Paragraph(data));
        // 关闭文档
        document.close();
    }

    public static void main(String[] args) throws DocumentException, IOException {
        exportPdf("1111", "/Users/maoxin/Desktop/PDF文件夹/123.pdf");
    }
}

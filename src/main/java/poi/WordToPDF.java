package poi;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author maoXin
 * @Description
 * @Date 13:40 2021/1/29
 */
public class WordToPDF {

    private final static WordToPDF instance = new WordToPDF();

    public void convertWordToPDF(String wordPath, String pdfPath) throws Exception {
        FileInputStream in = new FileInputStream(wordPath);
        XWPFDocument document = new XWPFDocument(in);
        File outFile = new File(pdfPath);
        OutputStream out = new FileOutputStream(outFile);
        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(document, out, options);
    }

    public static void main(String[] args) throws Exception {
        String wordPath="/Users/maoxin/Desktop/合同文件夹/赊销合同/转换失败的pdf.docx";
        String pdfPath="/Users/maoxin/Desktop/合同文件夹/赊销合同/demo123.pdf";
        List<String> strList = Collections.synchronizedList(new ArrayList<>());
        instance.convertWordToPDF(wordPath,pdfPath);
    }
}

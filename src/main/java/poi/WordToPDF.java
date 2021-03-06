package poi;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
        document.createNumbering();
        File outFile = new File(pdfPath);
        OutputStream out = new FileOutputStream(outFile);
        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(document, out, options);
    }

    public static void main(String[] args) throws Exception {
        String wordPath="C:\\Users\\10178\\Desktop\\所有合同最终版本\\全款寄售合同模板\\生产转pdf出错的合同_20220307144014.docx";
        String pdfPath="C:\\Users\\10178\\Desktop\\所有合同最终版本\\全款寄售合同模板\\demo.pdf";
        instance.convertWordToPDF(wordPath,pdfPath);
    }
}

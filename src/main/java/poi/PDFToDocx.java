package poi;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * @Author maoXin
 * @Description
 * @Date 16:51 2021/1/21
 */
public class PDFToDocx {

    private static final PDFToDocx instance = new PDFToDocx();

    public void convert(String filename, String destName) {
        try {
            XWPFDocument doc = new XWPFDocument();
            String pdf = filename;
            PdfReader reader = new PdfReader(pdf);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                TextExtractionStrategy strategy =
                        parser.processContent(i, new SimpleTextExtractionStrategy());
                String text = strategy.getResultantText();
                XWPFParagraph p = doc.createParagraph();
                XWPFRun run = p.createRun();
                run.setText(text);
                run.addBreak(BreakType.PAGE);
            }
            FileOutputStream out = new FileOutputStream(destName);
            doc.write(out);
        } catch (Exception e) {
            System.err.println("can not convert");
        }
    }

    @Test
    public void testConvert() {
        String pdfPath="C:\\Users\\10178\\Desktop\\所有合同最终版本\\20220114修复运营系统合同\\JWD22010500077-副本.pdf";
        String outputPath="C:\\Users\\10178\\Desktop\\所有合同最终版本\\20220114修复运营系统合同\\JWD22010500077-副本.docx";
        instance.convert(pdfPath,outputPath);
    }
}

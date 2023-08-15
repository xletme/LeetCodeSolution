package poi;

import com.aspose.words.Document;
import com.aspose.words.PdfSaveOptions;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author maoXin
 * @Description
 * @Date 13:40 2021/1/29
 */
public class WordToPDF {

    private final static WordToPDF instance = new WordToPDF();

    //poi 转pdf
    public void convertWordToPDF(String wordPath, String pdfPath) throws Exception {
        FileInputStream in = new FileInputStream(wordPath);
        File outFile = new File(pdfPath);
        OutputStream out = new FileOutputStream(outFile);
        XWPFDocument document = new XWPFDocument(in);
        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(document, out, options);
    }

    //aspose 插件 转pdf
    public void wordToPdf(String wordFile, String pdfFile) throws Exception {
        com.aspose.words.Document wordDoc = new Document(wordFile);
        PdfSaveOptions pso = new PdfSaveOptions();
        wordDoc.save(pdfFile, pso);
    }

    public void convertWordToPDFUseAli(String wordPath, String pdfPath) throws Exception {
        WordprocessingMLPackage pkg = WordprocessingMLPackage.load(new File(wordPath));
        Mapper fontMapper = new IdentityPlusMapper();
        PhysicalFont font = PhysicalFonts.getPhysicalFonts().get("Comic Sans MS");
        fontMapper.put("Calibri", font);
        pkg.setFontMapper(fontMapper);

        OutputStream os = new FileOutputStream(pdfPath);
        FOSettings foSettings = Docx4J.createFOSettings();
        foSettings.setWmlPackage(pkg);

        Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
    }

    public void convertWordToPDFUseDocx4J(String wordPath, String pdfPath) throws Exception {
        try {
            InputStream templateInputStream = new FileInputStream(wordPath);
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
            //MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

            FileOutputStream os = new FileOutputStream(pdfPath);
            Docx4J.toPDF(wordMLPackage,os);
            os.flush();
            os.close();
        } catch (Throwable e) {

            e.printStackTrace();
        }
    }






    public static void main(String[] args) throws Exception {
        String wordPath="/Users/maoxin/Desktop/合同文件夹/平台寄售采购合同/未命名.docx";
        String pdfPath="/Users/maoxin/Desktop/合同文件夹/平台寄售采购合同/未命名.pdf";
        instance.wordToPdf(wordPath,pdfPath);
    }
}

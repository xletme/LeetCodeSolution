package poi;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.fit.pdfdom.PDFDomTree;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author maoXin
 * @Description
 * @Date 16:09 2021/1/20
 */
public class PdfToHtml {

    private static PdfToHtml instance = new PdfToHtml();

    public void pdfToHtmlTest(String inPdfPath,String outputHtmlPath)  {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputHtmlPath)), "utf-8"));
            PDDocument document = PDDocument.load(new File(inPdfPath));
            PDFDomTree pdfDomTree = new PDFDomTree();
            pdfDomTree.writeText(document, out);
        }catch (Exception e) {
            System.out.println("");
        }
    }

    public void getPagesFromPDF(String filename,String extension) {
        try {
            PDDocument document = PDDocument.load(new File(filename));
            PDPageTree pages = document.getPages();
            for (PDPage page : pages) {
                PDResources resources = page.getResources();
                int i = 1;
                for (COSName name : resources.getXObjectNames()) {
                    PDXObject object = resources.getXObject(name);
                    if (object instanceof PDImageXObject) {
                        PDImageXObject image = (PDImageXObject) object;
                        String imageFileName = "C:\\Users\\10178\\Desktop\\仓小白合同对接\\" + i +"."+extension;
                        ImageIO.write(image.getImage(),extension,new File(imageFileName));
                        i++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Exception while trying to create pdf document - " + e);
        }
    }

    private void generateImageFromPDF(InputStream in, String extension) {
        try {
            PDDocument document = PDDocument.load(in);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(
                        page, 300, ImageType.RGB);
                ImageIOUtil.writeImage(
                        bim, String.format("C:\\Users\\10178\\Desktop\\仓小白合同对接\\image\\pdf-%d.%s", page + 1, extension), 300);
            }
            document.close();
        } catch (IOException e) {
            throw new RuntimeException("pdf处理失败,"+e.getMessage());
        }
    }

    public static void main (String[] args) {
        String pdfPath="C:\\Users\\10178\\Desktop\\仓小白合同对接\\运输合同.docx";
        String outputPath="C:\\Users\\10178\\Desktop\\仓小白合同对接\\demo.pdf";
       instance.pdfToHtmlTest(pdfPath,outputPath);
    }
}

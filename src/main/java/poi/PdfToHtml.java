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

    /**
     * @Description:
     *
     * 只是太爱你
     *
     * 一不小心 就北京摩爱着你的快了 我知道这样不应爱 也知道你会受伤害 只是不想让自己对你太过依赖 我明白
     *
     * 只是我 不懂得如何去爱 才会让你想离开 因为我不知道下一辈子还是否能遇见你
     * 所有今生才会俺么女里 把最好的给你
     * 爱你都变成山还是你 不是故事 只是太爱你
     * 原谅我真的喝醉了 因为我真的喜感你了
     * 我知道这样给你不应该 额知道你会受伤害
     *
     * 对你太过依赖
     * 我明白 你给的爱是正式的存在 只是我不懂得如何去爱 才会让您想离开
     * 因为我不知道下一辈子还是否能遇见你 所以为晋升才会那么女里把最好的给你
     * 爱你都变成伤害你 我们的爱要窒息
     * 不是故意 只是太爱你
     *
     * 飘向北方
     *
     * 在外漂泊思想 能改变自己的历史 让父母过得好一点
     * 一切都是那么多的控
     * 说妈妈我还好 急需解决我的温饱
     * 我飘向北方
     *
     * 别问我家乡
     * 也是自后期望
     * 他们都叫我mr mass
     * 去呀到这headers的迹象
     * 高寒Mr king
     *
     * 我璐在前方  挡不住又上阿辉
     * 家人是否无恙 升满了惆怅
     * 养活一家 内心子阿基来挤压
     * 技术在灵魂面前笔数
     * 挡不住忧伤
     * 家人是否无恙
     * 回不去的远方
     *
     *一丝不挂
     *
     *分手是内购的你一转念 为你以后不想什么牵连
     * 碎料你见松绑有愿减免
     * 这根线其实说到底 谁哪捏在手
     * 当我拖着苦活
     *
     * 原来 只是在等我设个
     * 然后资费天国
     *
     * 但我拖着葵科 最后水压UN缠绕到提那个过
     * 当道爱可爱本省自傲与错伏
     *
     * 那只是一场游戏一场梦
     *
     * 不要谈什么分离 我不会因为这样哭泣
     *  那只是作业作业的衣阿华长梦而已
     *  那只是昨夜的一场游戏
     *  虽然你影子还出现我眼里
     *  不要把残缺的爱留在这里
     *
     *  说了刺青又不理 说什么我爱你 
     *
     * 在两人的世界里不该有你 哦 为什么道别离
     * 有说什么字啊一起
     * 如今虽然没有你 我还是我自己 说什么我爱你 如今依然没有你 我还是我自己
     * 为什么道别离
     * 有说什么在一起 如今虽然 没有你 说什么我爱你 如今依然没有你 我还是我自己
     *
     * @Date: 2022/3/2 17:09
     */
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

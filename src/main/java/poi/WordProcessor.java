package poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.assertj.core.util.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordProcessor {



    public static File replaceDataInWordDocument(List<String> goodsList, File inputWordFile) {
        try {
            FileInputStream fis = new FileInputStream(inputWordFile);
            XWPFDocument document = new XWPFDocument(fis);

            // 遍历Word文档中的段落
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        // 根据需要替换文本
                        for (String goods : goodsList) {
                            text = text.replace("{{$}}", goods);
                        }
                        run.setText(text, 0);
                    }
                }
            }

            // 创建一个新文件来保存修改后的Word文档
            File outputWordFile = new File("output.docx");
            FileOutputStream fos = new FileOutputStream(outputWordFile);
            document.write(fos);
            fos.close();

            return outputWordFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        File file = new File("");
        ArrayList<String> goodsList = Lists.newArrayList("1", "2", "3");

        replaceDataInWordDocument(goodsList, file);


    }
}


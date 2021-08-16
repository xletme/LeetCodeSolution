package poi;


import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Word07ToHtml {

    public static void parseToHtml() throws IOException {
        File f = new File("C:\\Users\\10178\\Desktop\\仓小白合同对接\\copy.docx");
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {

                // 1) 加载XWPFDocument及文件
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);

                // 2) 实例化XHTML内容(这里将会把图片等文件放到生成的"word/media"目录)
                File imageFolderFile = new File("C:\\Users\\10178\\Desktop\\仓小白合同对接");

                XHTMLOptions options = XHTMLOptions.create().URIResolver(
                        new FileURIResolver(imageFolderFile));
                options.setExtractor(new FileImageExtractor(imageFolderFile));

                // 3) 将XWPFDocument转成XHTML并生成文件
                OutputStream out = new FileOutputStream(new File(
                        "C:\\Users\\10178\\Desktop\\仓小白合同对接\\合同模板.html"));
                XHTMLConverter.getInstance().convert(document, out, options);
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }
    }

    @Test
    public void testConvert2007() throws IOException {
        //生成XWPFDocument对象  word文档目录
       // InputStream in = new FileInputStream("C:\\Users\\10178\\Desktop\\仓小白合同对接\\运输合同 - 副本.docx");
        InputStream in;
        String strUrl = "http://jwell-ecommerce-cloud.oss-cn-beijing.aliyuncs.com/jwell-cloud/contract/YS/20210119/6057f6c91d29b4e2eb5d9254cd7c830d.docx" ;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20 * 1000);
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy(conn.getInputStream(),output);
            in = new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("url获取stream失败");
        }finally {
            try{
                if (conn != null) {
                    conn.disconnect();
                }
            }catch (Exception e){
                throw new RuntimeException("url关闭失败");
            }
        }

        XWPFDocument document = new XWPFDocument(in);

        String firstParty = "成都达海";
        String secondParty = "四川九技有限公司";
        String startStation = "成都";
        String destination = "南充";
        String cargoName = "钢铁";
        String cargoQty = "1   3t";
        String startYear = "2021";
        String startMonth = "1";
        String startDay = "19";
        String endYear = "2021";
        String endMonth = "1";
        String endDay = "23";
        String demandDesc = "货物完好无损,准时送达";
        String transportPrice = "3000";


        List<XWPFTable> tables = document.getTables();
        System.out.println("1----"+tables.get(0).getRow(0).getTableCells().get(0).getText());
        System.out.println("2----"+tables.get(0).getRow(0).getTableCells().get(1).getText());
        System.out.println("3----"+tables.get(0).getRow(1).getTableCells().get(0).getText());
        System.out.println("4----"+tables.get(0).getRow(1).getTableCells().get(1).getText());
        System.out.println("5----"+tables.get(0).getRow(2).getTableCells().get(0).getText());
        System.out.println("6----"+tables.get(0).getRow(2).getTableCells().get(1).getText());
        System.out.println("7----"+tables.get(0).getRow(3).getTableCells().get(0).getText());
        System.out.println("8----"+tables.get(0).getRow(3).getTableCells().get(1).getText());
        System.out.println("----------");

        XWPFParagraph array1 = document.getParagraphArray(3);
        array1.getRuns().get(1).setText(firstParty);
        XWPFParagraph array2 = document.getParagraphArray(4);
        array2.getRuns().get(1).setText(secondParty);
        XWPFParagraph array3 = document.getParagraphArray(7);
        array3.getRuns().get(3).setText(startStation);
        array3.getRuns().get(5).setText(destination);
        XWPFParagraph array4 = document.getParagraphArray(9);
        array4.getRuns().get(2).setText(startYear);
        array4.getRuns().get(4).setText(startMonth);
        array4.getRuns().get(6).setText(startDay);
        array4.getRuns().get(8).setText(endYear);
        array4.getRuns().get(10).setText(endMonth);
        array4.getRuns().get(12).setText(endDay);
        XWPFParagraph array5 = document.getParagraphArray(10);
        array5.getRuns().get(3).setText(demandDesc);
        XWPFParagraph array6 = document.getParagraphArray(12);
        array6.getRuns().get(2).setText(transportPrice);
        XWPFParagraph array7 = document.getParagraphArray(42);
        XWPFRun xwpfRun1 = array7.getRuns().get(0);
        xwpfRun1.setText(firstParty);
        array7.addRun(xwpfRun1);
        XWPFParagraph array8 = document.getParagraphArray(48);
        XWPFRun xwpfRun2 = array8.getRuns().get(0);
        xwpfRun2.setText(secondParty);
        array8.addRun(xwpfRun2);


        //保存图片操作  图片保存目录
        File imageFolderFile = new File("C:\\Users\\10178\\Desktop\\仓小白合同对接");
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));

        //将XWPFDocument转成XHTML并生成文件  html生成目录
        OutputStream out = new FileOutputStream(new File("C:\\Users\\10178\\Desktop\\仓小白合同对接\\temple_transport.html"));
        XHTMLConverter.getInstance().convert(document,out,options);
    }

    public static void main(String[] args) {
        try {
            parseToHtml();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

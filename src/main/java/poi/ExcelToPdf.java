package poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

/**
 * @author maoxin
 * @version 1.0
 * @description:
 * @date 2023/5/25 11:25
 */
public class ExcelToPdf {


    public static void main(String[] args) {
        String excelFilePath = "/Users/maoxin/Downloads/成都达海金属加工配送有限公司物资出门凭证.xlsx"; // Excel文件路径
        String pdfFilePath = "/Users/maoxin/Downloads/成都达海金属加工配送有限公司物资出门凭证.pdf"; // PDF文件路径

        try (InputStream excelFile = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(excelFile);
             OutputStream pdfFile = new FileOutputStream(pdfFilePath)) {

            Document document = new Document();
            PdfWriter.getInstance(document, pdfFile);
            document.open();

            Sheet sheet = workbook.getSheetAt(0); // 假设要转换的Excel文件中只有一个工作表

            for (Row row : sheet) {
                for (Cell cell : row) {
                    String cellValue = cell.toString();

                    Paragraph paragraph = new Paragraph(cellValue);
                    document.add(paragraph);
                }
            }

            document.close();
            System.out.println("Excel转换为PDF成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package poi;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.assertj.core.util.Lists;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class WordTableFiller {
    public static String fillWordTableWithItems(String inputWordPath, List<String> items) {
        try (FileInputStream fis = new FileInputStream(inputWordPath);
             XWPFDocument document = new XWPFDocument(fis)) {

            // 获取文档中的第一个表格
            XWPFTable table = document.getTables().get(0);

            // 遍历物品列表并将数据添加到表格
            for (String item : items) {
                XWPFTableRow row = table.createRow();
                XWPFTableCell cell = row.getCell(0);
                cell.setText(item);
            }

            // 生成新的Word文件
            String outputWordPath = "/Users/maoxin/Desktop/中信银行/银行资料/文件模板/中信银行抵押物品模板-output.docx";
            try (OutputStream out = new FileOutputStream(outputWordPath)) {
                document.write(out);
            }

            return outputWordPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Word文件路径
        //String inputWordPath = "/Users/maoxin/Desktop/中信银行/银行资料/文件模板/中信银行抵押物品模板-2023101301.docx";

        String inputWordPath = "/Users/maoxin/Desktop/合同文件夹/平台寄售采购合同/未命名.docx";

        // 物品列表
        List<String> items = Lists.newArrayList("Item 1", "Item 2", "Item 3");

        // 调用方法填充数据
        String outputWordPath = fillWordTableWithItems(inputWordPath, items);

        if (outputWordPath != null) {
            System.out.println("Word文件已生成：" + outputWordPath);
        } else {
            System.out.println("操作失败");
        }
    }
}


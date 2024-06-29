package poi;

import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextRange;

import java.awt.*;

public class CreateTable
{

    public static void main(String[] args)
    {

        //创建一个Document对象
        Document document = new Document();

        //添加一个节
        Section section = document.addSection();

        //定义表格数据
        String[] header = { "学号", "姓名", "性别", "班级", "成绩" };
        String[][] data =
                {
                        new String[]{"0105", "李雷", "男", "1", "88"},
                        new String[]{"0721", "赵文", "女", "7", "92"},
                        new String[]{"1131", "陈华", "女", "11", "91"},
                        new String[]{"0418", "宋野", "男", "4", "95"},
                        new String[]{"0513", "韩梅", "女", "5", "94"},
                };

        //添加表格
        Table table = section.addTable(true);
        table.resetCells(data.length + 1, header.length);

        //将第一行设置为表格标题
        TableRow row = table.getRows().get(0);
        row.isHeader(true);
        row.setHeight(20);
        row.setHeightType(TableRowHeightType.Exactly);
        row.getRowFormat().setBackColor(Color.gray);
        for (int i = 0; i < header.length; i++)
        {
            row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange txtRange = p.appendText(header[i]);
            txtRange.getCharacterFormat().setBold(true);
        }

        //将数据添加到其余行
        for (int r = 0; r < data.length; r++)
        {
            TableRow dataRow = table.getRows().get(r + 1);
            dataRow.setHeight(25);
            dataRow.setHeightType(TableRowHeightType.Exactly);
            dataRow.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < data[r].length; c++)
            {
                dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);
            }
        }

        //设置单元格的背景颜色
        for (int j = 1; j < table.getRows().getCount(); j++)
        {
            if (j % 2 == 0)
            {
                TableRow row2 = table.getRows().get(j);
                for (int f = 0; f < row2.getCells().getCount(); f++)
                {
                    row2.getCells().get(f).getCellFormat().setBackColor(new Color(173, 216, 230));
                }
            }
        }

        //保存结果文件
        document.saveToFile("result.docx", FileFormat.Docx_2013);
    }
}
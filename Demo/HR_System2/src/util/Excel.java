package util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Excel {

	/**
	 * 生成excel文件(文件标题栏与文件内容一定要对应)
	 * 
	 * @param os
	 * @param title
	 *            (excel文件标题栏)
	 * @param lists
	 *            (excel文件内容)
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void writeExcel(OutputStream os, String[] title, List lists,Field[] fi)
			throws IOException, RowsExceededException, WriteException,
			IllegalArgumentException, IllegalAccessException {
		// 创建可以写入的Excel工作薄(默认运行生成的文件在tomcat/bin下 )
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		// 生成工作表,(name:First Sheet,参数0表示这是第一页)
		WritableSheet sheet = wwb.createSheet("First Sheet", 0);

		// 开始写入第一行(即标题栏)
		for (int i = 0; i < title.length; i++) {
			// 用于写入文本内容到工作表中去
			Label label = null;
			// 在Label对象的构造中指明单元格位置(参数依次代表列数、行数、内容 )
			label = new Label(i, 0, title[i]);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
		}

		for (int i = 0; i < lists.size(); i++) {
			Object ob = lists.get(i);
			// 获取类对象 ---反射
			//Class cl = ob.getClass();
			// 获取对象的所有属性
			//Field[] fi = cl.getDeclaredFields();
			//getDeclaredField("dept_name");

			// 遍历所有的参数，其实j也就是对于的列数
			for (int j = 0; j < fi.length; j++) {
				// 设置对私有属性可操作
				fi[j].setAccessible(true);
				Label la = new Label(j, i+1, String.valueOf(fi[j].get(ob)));
				sheet.addCell(la);
			}
		}

		/*
		 * 生成一个保存数字的单元格,必须使用Number的完整包路径,否则有语法歧义,值为789.123 jxl.write.Number
		 * number = new jxl.write.Number(col, row, 555.12541);
		 * sheet.addCell(number);
		 */

		/*
		 * 生成一个保存日期的单元格,必须使用DateTime的完整包路径,否则有语法歧义,值为new Date()
		 * jxl.write.DateTime date = new jxl.write.DateTime(col, row, new
		 * java.util.Date()); sheet.addCell(date);
		 */

		// 写入数据
		wwb.write();
		// 关闭文件
		wwb.close();
		// 关闭输出流
		os.close();
	}
}
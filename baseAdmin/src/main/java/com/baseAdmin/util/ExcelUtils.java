package com.baseAdmin.util;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

	public static List<Map<String, Object>> excelToList(String excelName) {
		List<Map<String, Object>> list = new ArrayList<>();
		Workbook workbook = null;
		try {
			File file = new File(excelName);
			// 根据传递过来的文件输入流创建一个workbook对象（对应Excel中的工作簿）
			workbook = WorkbookFactory.create(file);
			// 创建完成，关闭输入流
			// 获取工作表对象，即第一个工作表 （工作簿里面有很多张工作表，这里取第一张工作表）
			Sheet sheet = workbook.getSheetAt(0);
			// 获取工作表的总行数
			int rowLength = sheet.getLastRowNum() + 1;
			System.out.println("行数:" + rowLength);
			// 获取工作表第一行数据
			Row row = sheet.getRow(0);
			// 获取工作表总列数的长度
			int colLength = row.getLastCellNum();
			System.out.println("列数:" + colLength);
			String[] titles = new String[colLength]; 
			// 创建一个单元格对象
			Cell cell = null;
			for (int b = 0; b < colLength; b++) {
				cell = row.getCell(b);
				String stringCellValue = "";
				if (cell != null) {
					cell.setCellType(CellType.STRING);
					// 获取单元格的数据
					stringCellValue = cell.getStringCellValue();
				}
				titles[b] = stringCellValue;
			}

			// 双重循环 因为一个单元格由行和列的索引下表构成
			for (int a = 1; a < rowLength; a++) {
				Map<String, Object> map = new HashMap<String, Object>();
				//System.out.print(a + ":");
				for (int b = 0; b < colLength; b++) {
					//System.out.print(b + ",");
					// 分别取出第 a行 b列的单元格数据
				//	System.out.println(sheet.getRow(a));
					if(sheet.getRow(a)==null) continue;
					//System.out.println(a+"::::"+b);
					cell = sheet.getRow(a).getCell(b);
					// 设置单元格的类型是String类型
					String stringCellValue = null;
					if (cell != null) {
						cell.setCellType(CellType.STRING);
						// 获取单元格的数据
						stringCellValue = cell.getStringCellValue();
					}
					// System.out.println(cell.getStringCellValue());
					// 通过列来进行判断要赋值的属性
					map.put(titles[b], stringCellValue);

				}
				list.add(map);
			}
		} catch (Exception e) {
			LOGGER.error("parse excel file error :", e);
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	private static String getColumnName(int columnNum) {
		columnNum=columnNum+1;
		int first;
		int last;
		String result = "";
		if (columnNum > 256)
			columnNum = 256;
		first = columnNum / 27;
		last = columnNum - (first * 26);
		if (first > 0)
			result = String.valueOf((char) (first + 64));
		if (last > 0)
			result = result + String.valueOf((char) (last + 64));
		return result;
	}
}

package com.abcft.system.company;

import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Iterator;
import java.util.Map;  
  







import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.DateUtil;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 读取Excel
 */
public class ReadExcelUtils {
	private Logger logger = Logger.getLogger(ReadExcelUtils.class);
	private Workbook wb;
	private Sheet sheet;
	private Row row;

	public ReadExcelUtils(String fileType, InputStream is) {
		try {
			if ("xls".equals(fileType)) {
				wb = new HSSFWorkbook(is);
			} else if ("xlsx".equals(fileType)) {
				wb = new XSSFWorkbook(is);
			} else {
				wb = null;
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
	}

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 */
	public Iterator<Cell> readExcelTitle() throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		
		Iterator<Cell> title = row.cellIterator();
		
		return title;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, Map<Integer, Object>> readExcelContentForMap()
			throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();

		
		sheet = wb.getSheetAt(0);
		
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				cellValue.put(j, obj);
				j++;
			}
			content.put(i, cellValue);
		}
		return content;
	}
	
	/**
	 * 读取Excel数据内容
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public JSONArray readExcelContentForJSON() throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		JSONArray content = new JSONArray();
		
		sheet = wb.getSheetAt(0);
		
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			JSONObject rows = new JSONObject();
			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				rows.put(String.valueOf(j), obj);
				j++;
			}
			content.add(rows);
		}
		return content;
	}

	/**
	 * 
	 * 根据Cell类型设置数据
	 * 
	 * @param cell
	 */
	private Object getCellFormatValue(Cell cell) {
		Object cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (DateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					// data格式是带时分秒的：2013-7-10 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// data格式是不带时分秒的：2013-7-10
					Date date = cell.getDateCellValue();
					cellvalue = date;
				} else {// 如果是纯数字
					// 取得当前Cell的数值
					cellvalue = (int) cell.getNumericCellValue();
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:// 默认的Cell值
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

//	public static void main(String[] args) {
//		try {
//			String filepath = "F:test.xls";
//			ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
//			// 对读取Excel表格标题测试
//			// String[] title = excelReader.readExcelTitle();
//			// System.out.println("获得Excel表格的标题:");
//			// for (String s : title) {
//			// System.out.print(s + " ");
//			// }
//
//			// 对读取Excel表格内容测试
//			Map<Integer, Map<Integer, Object>> map = excelReader
//					.readExcelContent();
//			System.err.println("获得Excel表格的内容:");
//			for (int i = 1; i <= map.size(); i++) {
//				System.err.println(map.get(i));
//			}
//		} catch (FileNotFoundException e) {
//			System.err.println("未找到指定路径的文件!");
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}

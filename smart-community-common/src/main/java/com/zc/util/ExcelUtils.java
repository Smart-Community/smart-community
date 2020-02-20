package com.zc.util;

import org.apache.commons.lang.StringUtils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * excel操作类
 *
 * @author LIUTQ
 *
 */
public class ExcelUtils {
	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		String finalFileName = fileName;
		try {
			if (StringUtils.contains(userAgent, "firefox")) {// 火狐浏览器
				finalFileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = new String(URLEncoder.encode(fileName, "UTF8").getBytes(), "ISO-8859-1");// 其他浏览器
			}

			response.setHeader("Content-type", "text/html;charset=UTF-8");
			//response.setHeader("Content-Length", ""+fileName.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");// 这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
		} catch (Exception e) {
			logger.error("setFileDownloadHeader error", e);
		}
	}

	/**
	 * 多sheet导出
	 *
	 * @param wb
	 * @param mainTitle
	 * @param titles
	 * @param contents
	 * @param sheetNum
	 * @param totalSheet
	 */
	public final static void buildExcel(SXSSFWorkbook wb, String mainTitle, String[] titles, List<Object[]> contents, int sheetNum, int totalSheet, OutputStream os) {
		try {
			/** **********创建工作表************ */
			Sheet sheet = wb.createSheet("Sheet" + sheetNum);

			/** ************设置单元格字体************** */
			Font font = wb.createFont();
			//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			font.setBold(true);

			/** ************以下设置三种单元格样式************ */
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyle.setFont(font);
			Row row = sheet.createRow(0);
			for (short i = 0; i < titles.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(titles[i]);
			}
			/** ***************以下是EXCEL正文数据********************* */
			for (int i = 0; i < contents.size(); i++) {// row
				Object[] rowContent = contents.get(i);
				Row row2 = sheet.createRow(i + 1);
				for (int j = 0; j < titles.length; j++) { // cell
					Cell cell = row2.createCell(j);
					cell.setCellValue(String.valueOf(rowContent[j]));
				}
			}
			if (totalSheet == sheetNum) {
				wb.write(os);
				os.close();
			}

		} catch (Exception e) {
			logger.error("buildExcel error,", e);
		}
	}

}

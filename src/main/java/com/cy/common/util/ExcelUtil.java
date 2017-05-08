package com.cy.common.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Created by zxj on 2017/3/13.
 */
public class ExcelUtil {

    public HSSFWorkbook createExcel() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        sheet.setColumnWidth(1, 10000);
        HSSFRow row = sheet.createRow(1);
        row.setHeightInPoints(23);

        return workbook;
    }

}

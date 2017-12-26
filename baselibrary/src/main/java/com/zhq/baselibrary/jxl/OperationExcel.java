package com.zhq.baselibrary.jxl;

import android.util.Log;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

public class OperationExcel {
    private static final String TAG = "OperationExcel";

    /**
     * 获取 excel 表格中的数据,不能在主线程中调用
     *
     * @param index 第几张表格中的数据
     */
    public static void getExcelData(InputStream inputStream, int index) {
        try {
            // 获取Excel的工作空间
            Workbook workbook = Workbook.getWorkbook(inputStream);
            // 获取Excel的工作空间中的第index表格
            Sheet sheet = workbook.getSheet(index);

            // Excel的工作空间中有几张表格
            int sheetNum = workbook.getNumberOfSheets();
            // 表格有几行
            int sheetRows = sheet.getRows();
            // 表格有列
            int sheetColumns = sheet.getColumns();

            for (int i = 0; i < sheetRows; i++) {
                for (int j = 0; j < sheetColumns; j++) {
                    sheet.getCell(j, i).getContents();// 第(j+1)列 中 第(i+1)行 的数据
                }
            }
            workbook.close();
        } catch (Exception e) {
            Log.e(TAG, "read error=" + e, e);
        }
    }
}

package com.example.util;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class ExcelUtil {

    public static String readStringDatas(String filePath, int sheetIndex, int rowIndex, int cellIndex) {
        FileInputStream xlsInputStream = null;
        String cellValue = null;
        try {
            //Excel工作簿输入流
            xlsInputStream = new FileInputStream(new File(filePath));
            //构造工作簿对象
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(xlsInputStream);
            //获取工作簿
            HSSFSheet sheetAt = hssfWorkbook.getSheetAt(sheetIndex);
            //获取行
            HSSFRow hssfRow = sheetAt.getRow(rowIndex);
            //获取单元格
            HSSFCell cell = hssfRow.getCell(cellIndex);
            //获取单元格的值
            cellValue = cell.getStringCellValue();

//            System.out.println(cellValue);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (xlsInputStream != null) {
                try {
                    xlsInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cellValue;
    }

    public static Double readNumDatas(String filePath, int sheetIndex, int rowIndex, int cellIndex) {
        FileInputStream xlsInputStream = null;
        Double cellValue = null;
        try {
            //Excel工作簿输入流
            xlsInputStream = new FileInputStream(new File(filePath));
            //构造工作簿对象
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(xlsInputStream);
            //获取工作簿
            HSSFSheet sheetAt = hssfWorkbook.getSheetAt(sheetIndex);
            //获取行
            HSSFRow hssfRow = sheetAt.getRow(rowIndex);
            //获取单元格
            HSSFCell cell = hssfRow.getCell(cellIndex);
            //获取单元格的值
            cellValue = cell.getNumericCellValue();

            System.out.println(cellValue);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (xlsInputStream != null) {
                try {
                    xlsInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cellValue;
    }

    public static Object[][] readObjDatas(String filePath, int sheetIndex) throws IOException {
        FileInputStream xlsInputStream = null;
        String cellValue = null;
        List<Object[]> records = new ArrayList<>();
        Object[][] results;

        //Excel工作簿输入流  0731
        xlsInputStream = new FileInputStream(new File(filePath));
        //构造工作簿对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(xlsInputStream);
        //获取工作簿
        HSSFSheet sheetAt = hssfWorkbook.getSheetAt(sheetIndex);


//            System.out.println(cellValue);
//            int fistRowNum = sheetAt.getFirstRowNum();
        int lastRowNum = sheetAt.getLastRowNum();
//            int sumRowNum = lastRowNum - fistRowNum;
//        System.out.println("~~~~~~~~~~~~");
//        System.out.println(lastRowNum);

        for (int i = 1; i <= lastRowNum; i++) {
            //获取行
            HSSFRow hssfRow = sheetAt.getRow(i);
            System.out.println("最大列号：" + hssfRow.getLastCellNum());
            String[] fields = new String[2];
            for (int j = 4; j < hssfRow.getLastCellNum(); j++) {
                //获取单元格
                HSSFCell cell = hssfRow.getCell(j);
                fields[j-4] = cell.getStringCellValue();
            }
            records.add(fields);
        }
        //定义函数返回值，即Object[][]
        //将存储测试数据的list转换为一个Object的二维数组
//        System.out.println("records.size():"+records.size());
        results = new Object[records.size()][];
        //设置二维数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }

        return results;
    }


    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/data.xls";
//        ExcelUtil.readStringDatas(path, 0, 1, 4);
//        ExcelUtil.readStringDatas(path, 0, 1, 9);
//        ExcelUtil.readNumDatas(path, 0, 1, 10);
        Object[][] res = ExcelUtil.readObjDatas(path, 0);
//        System.out.println("res.length:"+res.length);

        for (int i = 0; i < res.length; i++) {
//            System.out.println("res[i].length:"+res[i].length);
            for (int j = 0; j < res[i].length; j++) {
                System.out.println(res[i][j]);

            }

        }

    }
}

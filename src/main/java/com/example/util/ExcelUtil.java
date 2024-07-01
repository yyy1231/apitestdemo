package com.example.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tomcat.util.net.TLSClientHelloExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {

    public static String readStringDatas(String filePath,int sheetIndex,int rowIndex,int cellIndex){
        FileInputStream xlsInputStream = null;
        String cellValue = null;
        try{
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

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(xlsInputStream!=null){
                try{
                    xlsInputStream.close();
                }catch(IOException e){e.printStackTrace();}
            }
        }
        return cellValue;
    }

    public static Double readNumDatas(String filePath,int sheetIndex,int rowIndex,int cellIndex){
        FileInputStream xlsInputStream = null;
        Double cellValue = null;
        try{
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

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(xlsInputStream!=null){
                try{
                    xlsInputStream.close();
                }catch(IOException e){e.printStackTrace();}
            }
        }
        return cellValue;
    }

    public static void main(String[] args) {
        System.out.println("hello 验证mainfangfang ggggggggggg");
        String path = "src/main/resources/testdata.xls";
        ExcelUtil.readStringDatas(path,0,1,4);
        ExcelUtil.readStringDatas(path,0,1,9);
        ExcelUtil.readNumDatas(path,0,1,10);
    }
}

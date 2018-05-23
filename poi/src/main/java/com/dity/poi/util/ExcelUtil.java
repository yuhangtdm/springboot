package com.dity.poi.util;

import com.dity.poi.pojo.TmpSale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/4/28
 */
public class ExcelUtil {

    public static   Workbook wb;

    public  static Sheet sheet;

    public static int rowNum;

    public  static int colNum;



    public static   List<TmpSale> list=new ArrayList<>();

    public static  void initByPath(String filePath){
        try {
            InputStream inputStream=new FileInputStream(new File(filePath));
            if(filePath.endsWith(".xlsx")){
                wb=new XSSFWorkbook(inputStream);
            }else if(filePath.endsWith(".xls")){
                wb=new XSSFWorkbook(inputStream);
            }else{
            }
            inputStream.close();
            sheet=wb.getSheetAt(0);
            rowNum=sheet.getLastRowNum();
            colNum=sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println(rowNum);
            System.out.println(colNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static  void readExcel(){
        for(int i=1;i<=rowNum;i++){
            Cell cell = sheet.getRow(i).getCell(1);
            String employeeNo = cell.getStringCellValue();
            Cell cell2=sheet.getRow(i).getCell(9);
            String idno=cell2.getStringCellValue();
            TmpSale tmpSale=new TmpSale(employeeNo.trim(),idno.trim());
            list.add(tmpSale);
        }
    }


    public static void main(String[] args) {
        ExcelUtil.initByPath("D:\\sale.xlsx");
        ExcelUtil.readExcel();
        System.out.println(list.size());
    }




}

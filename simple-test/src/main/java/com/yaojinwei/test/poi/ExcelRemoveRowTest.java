package com.yaojinwei.test.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author jinwei.yjw
 * @date 2018/5/25 16:03
 */
public class ExcelRemoveRowTest {
    public static void main(String[] args) {
        try{

            FileInputStream is = new FileInputStream("D:\\workers\\胃癌筛查\\testremove.xls");

            HSSFWorkbook workbook = new HSSFWorkbook(is);

            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();

            System.out.println("删除前：" + (sheet.getLastRowNum()+1));
            for(int i = 0; i < rowNum + 1; i++){
                if(i == 1 || i == 3 || i ==5){
                    removeRow0(sheet, i);
                }
                else{
                    HSSFRow row = sheet.getRow(i);
                    System.out.println(row.getCell(0).getNumericCellValue());
                }

            }
            //removeRow(sheet, 0);
            //sheet.shiftRows(10, 1, -1);
            FileOutputStream os = new FileOutputStream("D:\\workers\\胃癌筛查\\testremove.xls");
            System.out.println("删除后：" + (sheet.getLastRowNum()+1));

            for(int i = 0; i < rowNum + 1; i++){
                Row row = sheet.getRow(i);
                if(row == null || row.getLastCellNum() == 0){
                    removeRow(sheet, i);
                }
            }
            System.out.println("删除后1123：" + (sheet.getLastRowNum()+1));
            workbook.write(os);

            is.close();

            os.close();

        } catch(Exception e) {

            e.printStackTrace();

        }
    }

    public static void removeRow0(HSSFSheet sheet, int rowIndex){
        HSSFRow row = sheet.getRow(rowIndex);
        sheet.removeRow(row);
    }

    public static void removeRow(HSSFSheet sheet, int rowIndex) {
        int lastRowNum=sheet.getLastRowNum();
        if(rowIndex>=0&&rowIndex<lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);//将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
        }
        if(rowIndex==lastRowNum){
            HSSFRow removingRow=sheet.getRow(rowIndex);
            if(removingRow!=null) {
                sheet.removeRow(removingRow);
            }
        }
    }
}

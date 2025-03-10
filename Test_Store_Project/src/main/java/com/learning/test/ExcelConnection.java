package com.learning.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelConnection {
	FileInputStream Excelfile;	
	Workbook ExcelWorkbook;
	
	public ExcelConnection(String Path)
	{
	try 
	{
		Excelfile = new FileInputStream(Path);
		ExcelWorkbook =  new HSSFWorkbook(Excelfile);
	} catch (FileNotFoundException e) {
	
		System.out.println("Error 1 : "+e);		
	}catch(IOException e)
	{
	System.out.println("Error 2 : "+e);	
	}
	}
	
	public String GetDatafromExcel(String Sheetname, int Rownumber, String field)
	{
		Sheet excelsheet = ExcelWorkbook.getSheet(Sheetname);		
		int counter = 0;
		Boolean condition =false;
		do
		{
		String cellvalue = excelsheet.getRow(0).getCell(counter).getStringCellValue().trim();	
		if(field.trim().equals(cellvalue))
		{
			condition = true;
			break;
		}
			counter++;
		}while(counter<excelsheet.getRow(0).getLastCellNum());
		
		if (condition =true)
		{
			 String value = excelsheet.getRow(Rownumber).getCell(counter).toString().trim();			 
			return(value);
			
		}else
		{
			return(field+" : "+"Value is not Present in Sheet");
		}	
	}

	public void Setdata(String filepath,String SheetName,int Rownumber,String field,String fieldvalue)
	{
		Sheet excelsheet = ExcelWorkbook.getSheet(SheetName);
		int counter = 0;
		Boolean condition =false;
		do
		{
		String cellvalue = excelsheet.getRow(0).getCell(counter).getStringCellValue().trim();	
		if(field.trim().equals(cellvalue))
		{
			condition = true;
			break;
		}
			counter++;
		}while(counter<excelsheet.getRow(0).getLastCellNum());
		
		if (condition =true)
		{

			try
			{
				excelsheet.getRow(Rownumber).createCell(counter);
				excelsheet.getRow(Rownumber).getCell(counter).setCellValue(fieldvalue);	
			}catch(NullPointerException e)
			{
				System.out.println("Empty cell : "+e);
			}
			
		}else
		{
			System.out.println(field+" : "+"is not Present in Sheet");
		}	
					
		try
		{
			Excelfile.close();			
			FileOutputStream outputStream = new FileOutputStream(filepath);
			ExcelWorkbook.write(outputStream);
			outputStream.close();	
		}catch(IOException e)
		{
			System.out.println("File not able to write : "+e);
		}	
			
	}

	}

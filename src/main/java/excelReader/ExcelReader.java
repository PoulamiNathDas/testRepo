package excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader
{
	public FileOutputStream fileout = null; // write the content to the file (excel)
	public String filepath;  // filepath location where the .xlsx file is kept
	public FileInputStream fis;  // read the content from the file (excel)
	public XSSFWorkbook workbook; // workbook variable
	public XSSFSheet sheet;  // sheet variable
	public XSSFRow row;  // row variable
	public XSSFCell cell = null; // column/cell data variable
	int rownum, columncount = 0; // initialization
	//int rownum = 0;
	String str = "";
	ArrayList<String> loginValues = new ArrayList<String>();
	int i, j = 0;
	//int j=0;
	
	public ArrayList<String> getCellData(String filepath,String excelName,String sheetName, int rowNumber)
	{	
		try
		{
			this.filepath=filepath;
			//String filepath1 = filepath+excelName;
			fis = new FileInputStream(filepath+excelName); // reading data / fetching data from a file
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
		    rownum = sheet.getLastRowNum(); // limit of rows that are filled
			System.out.println("Number of rows: " + rownum);
			
			columncount = sheet.getRow(0).getLastCellNum();	
			System.out.println("Number of columns: " + columncount);
			
			row = sheet.getRow(rowNumber);
			loginValues = new ArrayList<String>();
			
			for(j=0;j<columncount-1;j++)
			{
				str = row.getCell(j).getStringCellValue();
				loginValues.add(str);			
			}	
			workbook.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loginValues;
	}
	
	public void updateCellData(String filepath,String excelName,String sheetName, int rowNumber, String result)
	{		
		try
		{
			this.filepath=filepath;
			//String filepath1 = filepath+excelName;
			fis = new FileInputStream(filepath+excelName);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			
			columncount = sheet.getRow(0).getLastCellNum();	
			
			row = sheet.getRow(rowNumber);
			row.createCell(columncount-1).setCellValue(result);
			
			fileout = new FileOutputStream(filepath+excelName);
	        workbook.write(fileout);
		    workbook.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeCellData(String filepath,String excelName,String sheetName, String message)
	{	
		try
		{
			this.filepath=filepath;
			//String filepath1 = filepath+excelName;
			fis = new FileInputStream(filepath+excelName);
			workbook = new XSSFWorkbook(fis);
			
			sheet = workbook.createSheet(sheetName);
		
			rownum=sheet.getLastRowNum();
			System.out.println(rownum);			
	
			if (rownum == -1)
			{
				row = sheet.createRow(0);
				row.createCell(0).setCellValue("Message");
			}			
			
			row = sheet.createRow(2);
			row.createCell(0).setCellValue(message);
			
			fileout = new FileOutputStream(filepath+excelName);
			
			workbook.write(fileout);
			workbook.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
		
  public void newSheet(String filepath,String excelName,String sheetName)
  {	
	try
	{
		this.filepath=filepath;
		//String filepath1 = filepath+excelName;
		fileout = new FileOutputStream(filepath+excelName);
		workbook = new XSSFWorkbook();
		
		sheet = workbook.createSheet(sheetName);
		
		for(i = 0; i<=5; i++)
		{
			row = sheet.createRow(i);
			for(j = 0; j<=5; j++)
				row.createCell(j).setCellValue("Test");
		}
		
		workbook.write(fileout);
		workbook.close();
		}
	catch (IOException e)
	{
		e.printStackTrace();
	}
  }
}

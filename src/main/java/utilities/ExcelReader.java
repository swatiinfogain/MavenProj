package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;


public class ExcelReader  {

	String fileName;
	int row;
	int col;
	public ExcelReader(String fileName) {
		this.fileName = fileName;
	}
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		}
		else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}	
		return workbook;

	}
	public static void main(String args[]){
		ExcelReader er= new ExcelReader("Test2.xlsx");
		String arr[][]=er.ReadEntireExcel();
		for(String x[]: arr)
		{
			for(String y: x)
				System.out.print(y+" ");
			System.out.println(" ");
		}
		String arr1[]=er.ReadSpecificColumn("Onwarddate");
		for(String yy: arr1)
			System.out.print(yy+" ");
		System.out.println(er.ReadSpecificCell(1, 1));
		System.out.println(er.ReadSpecificCell(2, "source"));
	}

	public String[] ReadSpecificColumn(String ColumnName){
		String arr[]=null;
		try {

			FileInputStream excelFile = new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"testData"+File.separator+fileName));
			Workbook workbook=this.getWorkbook(excelFile, fileName);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			Row nextRow = null;
			DataFormatter formatter = new DataFormatter();
			this.row=firstSheet.getPhysicalNumberOfRows();
			arr=new String[row];
			int x=0;
			String arrCol=null;
			int fixedCol=0;
			while (iterator.hasNext()) {
				nextRow = iterator.next();
				this.col=nextRow.getLastCellNum();
				Iterator<Cell> cellIterator= nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int row1=nextCell.getRowIndex();
					int col1=nextCell.getColumnIndex();
					String aa=formatter.formatCellValue(firstSheet.getRow(row1).getCell(col1));
					if(aa.equalsIgnoreCase(ColumnName)){
						arrCol=aa;
						fixedCol=col1;
					}
					if(col1==fixedCol)
						arrCol=aa;
				}
				arr[x]=arrCol;
				System.out.println(arrCol);
				x++;
			}
			workbook.close();
			excelFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;
	}
	public String ReadSpecificCell(int rowNum, int ColumnNum){
		String term="No Cell Value Found";
		try {

			FileInputStream excelFile = new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"testData"+File.separator+fileName));
			Workbook workbook=this.getWorkbook(excelFile, fileName);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			Row nextRow = null;
			DataFormatter formatter = new DataFormatter();
			this.row=firstSheet.getPhysicalNumberOfRows();
			int x=0;
			String arrCol=null;
			int fixedCol=0;
			while (iterator.hasNext()) {
				nextRow = iterator.next();
				this.col=nextRow.getLastCellNum();
				Iterator<Cell> cellIterator= nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int row1=nextCell.getRowIndex();
					int col1=nextCell.getColumnIndex();
					String aa=formatter.formatCellValue(firstSheet.getRow(row1).getCell(col1));
					if(row1==rowNum && col1==ColumnNum){
						term=aa;
						return term;
					}
				}
			}
			workbook.close();
			excelFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return term;
	}
	public String ReadSpecificCell(int rowNum, String ColumnName){
		String term="No Cell Value Found";
		try {

			FileInputStream excelFile = new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"testData"+File.separator+fileName));
			Workbook workbook=this.getWorkbook(excelFile, fileName);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			Row nextRow = null;
			DataFormatter formatter = new DataFormatter();
			this.row=firstSheet.getPhysicalNumberOfRows();
			int x=0;
			String arrCol=null;
			int fixedCol=0;
			while (iterator.hasNext()) {
				nextRow = iterator.next();
				this.col=nextRow.getLastCellNum();
				Iterator<Cell> cellIterator= nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int row1=nextCell.getRowIndex();
					int col1=nextCell.getColumnIndex();
					String aa=formatter.formatCellValue(firstSheet.getRow(row1).getCell(col1));
					if(aa.equalsIgnoreCase(ColumnName) && row>rowNum){
						term=formatter.formatCellValue(firstSheet.getRow(rowNum).getCell(col1));
						return term;
					}
				}
			}
			workbook.close();
			excelFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return term;
	}
	public String[][] ReadEntireExcel(){
		String arr[][]=null;
		try {

			FileInputStream excelFile = new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"testData"+File.separator+fileName));
			Workbook workbook=this.getWorkbook(excelFile, fileName);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			Row nextRow = null;
			DataFormatter formatter = new DataFormatter();
			this.row=firstSheet.getPhysicalNumberOfRows();
			arr=new String[row][];
			int x=0;
			while (iterator.hasNext()) {
				nextRow = iterator.next();
				this.col=nextRow.getLastCellNum();
				String arrCol[]= new String[col];
				Iterator<Cell> cellIterator= nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int row1=nextCell.getRowIndex();
					int col1=nextCell.getColumnIndex();
					String aa=formatter.formatCellValue(firstSheet.getRow(row1).getCell(col1));
					arrCol[col1]=aa;
				}
				arr[x]=arrCol;
				x++;
			}
			workbook.close();
			excelFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;
	}
}

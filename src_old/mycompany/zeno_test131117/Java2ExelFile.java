package com.mycompany.zeno_test131117;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michelle
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author Michelle
 */
public class Java2ExelFile {
    
    public static final int BASELINE = 0, INTERVENTION = 1;
    private FileInputStream file;
//    private OPCPackage pack;
    private Workbook workbook;
    private Sheet recognition, copy, select, identify, eyeGaze;
    private String outputFilename;
    private final String TEMPLATE = "Z000_Protocol 3 v1.xlsx";
    private int type;
    private int attempts = 0;
    boolean closed = false;
//    private final String PATH = "C:\\Users\\RobotController\\Desktop\\ZenoBrainPictures2\\com.mycompany_ZenoTherapyBrain6_jar_1.0-SNAPSHOT\\Z000_Protocol 3 v1.ods";
    
    public Java2ExelFile(String outputFilename, int type) {
        this.type = type;
        this.outputFilename = outputFilename;
        try {
            file = new FileInputStream(TEMPLATE);
//            System.out.println("file");
//            pack = OPCPackage.open(TEMPLATE);
            workbook = WorkbookFactory.create(file);
//            workbook = new XSSFWorkbook(pack);
            recognition = workbook.getSheetAt(0);
            copy = workbook.getSheetAt(1);
            select = workbook.getSheetAt(2);
            identify = workbook.getSheetAt(3); 
            eyeGaze = workbook.getSheetAt(4);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed IOException");
        } 
        catch (InvalidFormatException ex) {
            System.out.println("InvalidFormat");
            Logger.getLogger(Java2ExelFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	private int eyeGazeCount = 0;
	public void writeEyeGaze(String dev, String observer, String order, String head, String eyes, String response, String rt) {
		Row r = eyeGaze.getRow(eyeGazeCount+1);
		r.getCell(0).setCellValue(dev);
		r.getCell(1).setCellValue(observer);
		r.getCell(2).setCellValue(order);
		r.getCell(3).setCellValue(head);
		r.getCell(4).setCellValue(eyes);
		r.getCell(5).setCellValue(response);
		r.getCell(6).setCellValue(rt);
		eyeGazeCount++;
	}
    
    private int recognitionCount = 0;
    public void writeRecognition(boolean correct, String expectedEmote, String respEmote, String promptTime, String respTime, String startTime, String endTime) {
        write(recognition, recognitionCount, expectedEmote, correct, respEmote, promptTime, respTime, startTime, endTime);
        recognitionCount++;
    }
    
    private int selectCount = 0;
    public void writeSelect(boolean correct, String expectedEmote, String respEmote, String promptTime, String respTime, String startTime, String endTime) {
        write(select, selectCount, expectedEmote, correct, respEmote, promptTime, respTime, startTime, endTime);
        selectCount++;
    }
    
    private int identifyCount = 0;
    public void writeIdentify(boolean correct, String expectedEmote, String respEmote, String promptTime, String respTime, String startTime, String endTime) {
        write(identify, identifyCount, expectedEmote, correct, respEmote, promptTime, respTime, startTime, endTime);
        identifyCount++;
    }
    
    private int copyCount = 0;
    public void writeCopy(String aus, String emotion, String startTime, String endTime) {
        boolean correct = !aus.contains("0");
        int col = copyCount * 6 + 1;
        for (int i = 0; i < aus.length(); i++) {
            copy.getRow(2 + i).getCell(col + attempts).setCellValue(aus.substring(i, i+1));
        }
        copy.getRow(15).getCell(col + attempts).setCellValue(startTime);
        copy.getRow(16).getCell(col + attempts).setCellValue(endTime);
        copy.getRow(0).getCell(col + 1).setCellValue(emotion);
        if (type == INTERVENTION) {
            attempts++;
            if (correct)
                copyCount++;
        } else {
            copyCount++;
        }
    }
    
    public void closeFile() {
        if (closed)
            return;
        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(new File(outputFilename));
            workbook.write(outFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Java2ExelFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Java2ExelFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
//                pack.close();
            } catch (IOException ex) {
                Logger.getLogger(Java2ExelFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Java2ExelFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        closed = true;
    }
    
    
//    public static void writeToExcel(String filePath, int SheetNumber, int row, int col, String Stuff2Write)
//    {
//                try {
////                    FileInputStream file = new FileInputStream(new File(filePath));
//
////                    XSSFWorkbook workbook = new XSSFWorkbook(file);
//                    XSSFSheet sheet = workbook.getSheetAt(SheetNumber);
//                    Cell cell = null;
//
//                    //Update the value of cell
//                    cell = sheet.getRow(row-1).getCell(col-1);
//                    cell.setCellValue(Stuff2Write);
//                   
//                    file.close();
//
//                    FileOutputStream outFile =new FileOutputStream(new File(filePath));
//                    workbook.write(outFile);
//                    outFile.close();
//                    System.out.println("Written to Excel!");
//                    
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                    System.out.println("Failed File Not Found");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.println("Failed IOException");
//                }
// 
//    }
    

    private void write(Sheet sheet, int count, String expectedEmote, boolean correct, String respEmote, String promptTime, String respTime, String startTime, String endTime) {
        Row row = sheet.getRow(count * 4 + 1);
        row.getCell(1).setCellValue(expectedEmote);
        row.getCell(2 + attempts * 3).setCellValue(correct ? 1 : 0);
        if (correct) {
            respEmote = expectedEmote;
            attempts = 0;
        } else if (type == INTERVENTION) {
            attempts++;
        }
        row.getCell(3 + attempts * 3).setCellValue(respEmote.replaceAll("wrong|right|[ 1-9]", ""));
        row.getCell(16 + attempts).setCellValue(promptTime);
        sheet.getRow(count * 4 + 2).getCell(16 + attempts).setCellValue(respTime);
        sheet.getRow(count * 4 + 3).getCell(16 + attempts).setCellValue(startTime);
        sheet.getRow(count * 4 + 4).getCell(16 + attempts).setCellValue(endTime);
    }
}

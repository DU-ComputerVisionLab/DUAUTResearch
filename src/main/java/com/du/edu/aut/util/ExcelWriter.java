package com.du.edu.aut.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;

/**
 *
 *  I have no idea how this class functions.
 *
 * Created by David on 7/24/2015.
 */
public class ExcelWriter {

        public static final int BASELINE = 0, INTERVENTION = 1;
        private FileInputStream file;
        private Workbook workbook;
        private Sheet recognition, copy, select, identify, eyeGaze;
        private String outputFilename;
        private final String TEMPLATE = "Z000_Protocol 3 v1.xlsx";
        private int type;
        private int attempts = 0;
        boolean closed = false;

        public ExcelWriter(String outputFilename, int type) throws IOException,
                InvalidFormatException {
            this.type = type;
            this.outputFilename = outputFilename;
            File ft = new File(TEMPLATE);
            file = new FileInputStream(ft);
            workbook = WorkbookFactory.create(file);
            recognition = workbook.getSheetAt(0);
            copy = workbook.getSheetAt(1);
            select = workbook.getSheetAt(2);
            identify = workbook.getSheetAt(3);
            eyeGaze = workbook.getSheetAt(4);
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
                //TODO: Add Logging
            } catch (IOException ex) {
                //TODO: Add Logging
            } finally {
                try {
                    file.close();
                } catch (IOException ex) {
                    //TODO: Add Logging
                }
                try {
                    outFile.close();
                } catch (IOException ex) {
                    //TODO: Add Logging
                }
            }
            closed = true;
        }

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

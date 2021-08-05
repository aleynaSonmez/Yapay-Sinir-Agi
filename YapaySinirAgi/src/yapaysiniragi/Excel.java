/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sonme
 */
public class Excel {

    static ArrayList veri = new ArrayList();//excelden aldığım veriler arraylisti
    static String dosyaYolu = "enerji verimliliği veri-seti.xlsx";//bunun yerine fonksiyonda yollanabilir.file içerisine

    public void veriler(int sutun) {
        //direkt gözlemleri excelden çektiğim yer.
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(String.valueOf(dosyaYolu)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(fis);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        int sayac = 0;
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case NUMERIC:
                        veri.add(cell.getNumericCellValue());
                        //System.out.print(cell.getNumericCellValue() + "\t\t");
                        sayac++;
                        if (sayac % sutun == 0) {
                            //System.out.print("\n");
                        }
                        break;
                }
            }
        }
    }
}

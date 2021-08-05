/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import yapaysiniragi.GerekliHesaplamaDegerleri;
import static yapaysiniragi.ExcelIslemler.matrisTestSeti;
import static yapaysiniragi.UygulamaArayuz.agirlikTest;
import static yapaysiniragi.UygulamaArayuz.agirlikTestCikis;
import static yapaysiniragi.UygulamaArayuz.noronSayi;
import static yapaysiniragi.UygulamaArayuz.sutun;
import static yapaysiniragi.UygulamaArayuz.testCikisAgirliklari;
import static yapaysiniragi.UygulamaArayuz.testCikisBiaslari;
import static yapaysiniragi.UygulamaArayuz.testGirisAgirliklari;
import static yapaysiniragi.UygulamaArayuz.testGirisBiaslari;
import java.util.ArrayList;
import java.util.Collections;

public class TestSinifi {

    static ArrayList randomVeriTest = new ArrayList();
    static ArrayList noronSigmoidTest = new ArrayList();
    GerekliHesaplamaDegerleri gerekliDegerler = new GerekliHesaplamaDegerleri();
    static double noronSigmoidCikisTest = 0;
    static double hataTest;
    static double gercekCikisTest;

    public void kullanilacakSatir() {
        for (int h = 0; h < sutun - 1; h++) {
            randomVeriTest.add(matrisTestSeti[UygulamaArayuz.siraliGezmeTest][h]);
        }

    }

    public void testIslemleriGirisAra() {
        Collections.reverse(testGirisAgirliklari);//son güncel elemanları almak için terse çevirme
        Collections.reverse(testCikisAgirliklari);
        double carpimToplamlari = 0;
        kullanilacakSatir();
        for (int i = 0; i < noronSayi; i++) {
            for (int j = 0; j < sutun - 1; j++) {
                carpimToplamlari += (double) randomVeriTest.get(j) * (double) testGirisAgirliklari.get(agirlikTest);
                //System.out.println("w:" + agirlikTest);
                agirlikTest++;
            }
            carpimToplamlari += (double) testGirisBiaslari.get(i);
            //System.out.println("a:" + carpimToplamlari);
            carpimToplamlari = gerekliDegerler.sigmoid(carpimToplamlari);
            noronSigmoidTest.add(carpimToplamlari);
            carpimToplamlari = 0;
        }
        //System.out.println("randomveri:" + randomVeriTest);
        //System.out.println("bias Giris:" + testGirisBiaslari);
        //System.out.println("sigmoidliNoron:" + noronSigmoidTest);
        testIslemleriAraCikis();
    }

    public void testIslemleriAraCikis() {

        noronSigmoidCikisTest = 0;
        for (int i = 0; i < noronSayi; i++) {
            noronSigmoidCikisTest += (double) noronSigmoidTest.get(i) * (double) testCikisAgirliklari.get(agirlikTestCikis);
            //System.out.println("w:" + testCikisAgirliklari.get(agirlikTestCikis));
            agirlikTestCikis++;
        }
        noronSigmoidCikisTest += (double) testCikisBiaslari.get(0);
        //System.out.println("sigmoidsiz Cikis:" + noronSigmoidCikisTest);
        noronSigmoidCikisTest = gerekliDegerler.sigmoid(noronSigmoidCikisTest);
        //System.out.println("tahmin:" + noronSigmoidCikisTest);
        //System.out.println("biasCikis:" + testCikisBiaslari);
        gercekCikisTest = matrisTestSeti[UygulamaArayuz.siraliGezmeTest][sutun - 1];
        //System.out.println("gercek Cikis:" + gercekCikisTest);
        hataTest = gercekCikisTest - noronSigmoidCikisTest;
        //System.out.println("hata:" + hataTest);
    }
}

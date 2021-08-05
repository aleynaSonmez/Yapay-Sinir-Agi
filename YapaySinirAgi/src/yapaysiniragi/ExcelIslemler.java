/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import yapaysiniragi.Excel;
import static yapaysiniragi.Excel.veri;
import static yapaysiniragi.UygulamaArayuz.satir;
import static yapaysiniragi.UygulamaArayuz.sutun;
import java.util.ArrayList;
import yapaysiniragi.GerekliHesaplamaDegerleri;

public class ExcelIslemler {

    GerekliHesaplamaDegerleri gerekliDegerler = new GerekliHesaplamaDegerleri();
    Excel excel = new Excel();
    ArrayList sutunVerileri = new ArrayList();
    double matrisTumVeriler[][];
    double normalizeVeriler[][];
    static double[][] matrisEgitimSeti;
    static double[][] matrisTestSeti;
    static int egitimSeti = 0;
    static int testSeti = 0;
    static int rand = 0;

    public void veriIslemleri() {
        excel.veriler(sutun);//excelden veriler dönüyor.
        matrisTumVeriler = new double[satir][sutun];//verileri satır sutun şeklinde kullanmak adına matrise atıyorum.
        normalizeVeriler = new double[satir][sutun];//normalize ettiğim veriler matriste.
        int tum = 0;
        for (int b = 0; b < satir; b++) {
            for (int a = 0; a < sutun; a++) {
                matrisTumVeriler[b][a] = (double) veri.get(tum);
                tum++;
                //System.out.println(matrisTumVeriler[b][a]);
            }
        }
        veri.clear();//verileri silip içerisine yeniden atıyorum çünkü 1 kere işim bitti
        //efektif ve fazladan kullanımı azaltmak adına.

        //verilerimi normalize ediyorum.sütun sütun.
        for (int i = 0; i < sutun; i++) {
            for (int j = 0; j < satir; j++) {
                sutunVerileri.add(matrisTumVeriler[j][i]);
            }
            veri.addAll(gerekliDegerler.normalizasyon(sutunVerileri));
            sutunVerileri.clear();//döngü içerisinde tekrar tekrar kullanım sağlıyor.
        }

        int artis = 0;
        for (int i = 0; i < sutun; i++) {
            for (int j = 0; j < satir; j++) {
                normalizeVeriler[j][i] = (double) veri.get(artis);
                artis++;

            }
        }
        egitimTestVerileri();//egitim test şeklinde bölme işlemi
    }

    public void egitimTestVerileri() {
        //normalize ettiğim verileri 70/30 şekilinde bölüyorum.random.
        egitimSeti = (satir * 70) / 100;//0-537 arası 538 tane veri gelicek
        testSeti = (satir * 30) / 100;
        int adet = 0;
        matrisEgitimSeti = new double[egitimSeti][sutun];
        matrisTestSeti = new double[satir - egitimSeti][sutun];
        for (int i = 0; i < satir; i++) {
            rand = (int) gerekliDegerler.randomSatirUret().get(i);
            for (int j = 0; j < sutun; j++) {
                if (i < egitimSeti) {
                    matrisEgitimSeti[i][j] = normalizeVeriler[rand][j];
                } else {
                    matrisTestSeti[adet][j] = normalizeVeriler[rand][j];
                    if (j == sutun - 1) {
                        adet++;
                    }
                }
            }
        }
        /*for (int a = 0; a < egitimSeti; a++) {
            for (int v = 0; v < sutun; v++) {
                System.out.println("egitim = " + matrisEgitimSeti[a][v]);
            }

        }
        System.out.println("egitim = " + matrisEgitimSeti.length);
        for (int c = 0; c < satir - egitimSeti; c++) {
            for (int b = 0; b < sutun; b++) {
                System.out.println("test = " + matrisTestSeti[c][b]);
            }

        }
        System.out.println("test = " + matrisTestSeti.length);*/
    }
}

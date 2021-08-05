/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import yapaysiniragi.GerekliHesaplamaDegerleri;
import static yapaysiniragi.ExcelIslemler.matrisEgitimSeti;
import static yapaysiniragi.IleriBeslemeGirisAraKatman.noronSigmoid;
import static yapaysiniragi.UygulamaArayuz.noronSayi;
import static yapaysiniragi.UygulamaArayuz.sutun;
import static yapaysiniragi.UygulamaArayuz.testCikisBiaslari;
import java.util.ArrayList;

/**
 *
 * @author sonme
 */
public class IleriBeslemeAraCikisKatman {

    GerekliHesaplamaDegerleri gerekliDegerler = new GerekliHesaplamaDegerleri();
    static ArrayList agirlikDegerleriCikis = new ArrayList();
    double biasCikis = gerekliDegerler.randomSayi();
    static double noronSigmoidCikis = 0;
    static double hata = 0;
    static double gercekCikis = 0;

    public IleriBeslemeAraCikisKatman() {
        gerekliDegerler.agirlikCikis(noronSayi, agirlikDegerleriCikis);
        testCikisBiaslari.add(biasCikis);
    }

    public void islemler() {
        noronSigmoidCikis = 0;
        for (int i = 0; i < noronSayi; i++) {
            noronSigmoidCikis += (double) noronSigmoid.get(i) * (double) agirlikDegerleriCikis.get(i);
        }
        noronSigmoidCikis += (double) biasCikis;
        //System.out.println("bias ekli:" + noronSigmoidCikis);
        noronSigmoidCikis = gerekliDegerler.sigmoid(noronSigmoidCikis);
        //noronSigmoidCikis.add(carpimToplamlari);

        //System.out.println("randomveri:" + noronSigmoid);
        //System.out.println("w:" + agirlikDegerleriCikis);
        //System.out.println("biasCikis:" + biasCikis);
        //System.out.println("tahmin:" + noronSigmoidCikis);
        gercekCikis = matrisEgitimSeti[UygulamaArayuz.siraliGezme][sutun - 1];
        hata = gercekCikis - noronSigmoidCikis;
        //System.out.println("gercekCikis:" + gercekCikis);
        //System.out.println("hata:" + hata);
    }
}

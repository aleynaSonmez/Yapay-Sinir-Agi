/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import yapaysiniragi.GerekliHesaplamaDegerleri;
import static yapaysiniragi.ExcelIslemler.matrisEgitimSeti;
import static yapaysiniragi.UygulamaArayuz.noronSayi;
import static yapaysiniragi.UygulamaArayuz.sutun;
import static yapaysiniragi.UygulamaArayuz.testGirisBiaslari;
import java.util.ArrayList;

/**
 *
 * @author sonme
 */
public class IleriBeslemeGirisAraKatman {

    static ArrayList agirlikDegerleriGiris = new ArrayList();
    static ArrayList biasDegerleriGiris = new ArrayList();
    static ArrayList noronSigmoid = new ArrayList();
    static ArrayList randomVeri = new ArrayList();
    GerekliHesaplamaDegerleri gerekliDegerler = new GerekliHesaplamaDegerleri();

    //const. classın nesnesi oluştuğunda içindekileri bir kere yapar.!!!!
    public IleriBeslemeGirisAraKatman() {
        gerekliDegerler.biasGiris(noronSayi, biasDegerleriGiris);
        gerekliDegerler.agirlikGiris(noronSayi, sutun, agirlikDegerleriGiris);
        testGirisBiaslari = (ArrayList) biasDegerleriGiris.clone();//test için.
    }

    public void kullanilacakSatir() {
        for (int h = 0; h < sutun - 1; h++) {
            randomVeri.add(matrisEgitimSeti[UygulamaArayuz.siraliGezme][h]);//sıralı gezme
            //arayüzde arttırılıyor.tüm eğitimsetini gezmek için.
        }
    }

    public void islemler() {
        double carpimToplamlari = 0;//yeni satır için sıfırlıyorum.
        int agirlik = 0;
        kullanilacakSatir();
        for (int i = 0; i < noronSayi; i++) {
            for (int j = 0; j < sutun - 1; j++) {
                carpimToplamlari += (double) randomVeri.get(j) * (double) agirlikDegerleriGiris.get(agirlik);
                agirlik++;
            }
            carpimToplamlari += (double) biasDegerleriGiris.get(i);
            carpimToplamlari = gerekliDegerler.sigmoid(carpimToplamlari);
            noronSigmoid.add(carpimToplamlari);
            carpimToplamlari = 0;//2.noron için sıfırlıyorum.
        }
        //System.out.println("randomveri:" + randomVeri);
        //System.out.println("w:" + agirlikDegerleriGiris);
        //System.out.println("bias Giris:" + biasDegerleriGiris);
        //System.out.println("sigmoidliNoron:" + noronSigmoid);
    }
}

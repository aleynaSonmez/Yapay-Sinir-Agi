/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import static yapaysiniragi.GeriYayilimAraCikisKatman.guncelAgirlikAraCikis;
import static yapaysiniragi.GeriYayilimAraCikisKatman.sm;
import static yapaysiniragi.IleriBeslemeGirisAraKatman.agirlikDegerleriGiris;
import static yapaysiniragi.IleriBeslemeGirisAraKatman.noronSigmoid;
import static yapaysiniragi.IleriBeslemeGirisAraKatman.randomVeri;
import static yapaysiniragi.UygulamaArayuz.noronSayi;
import static yapaysiniragi.UygulamaArayuz.sutun;
import java.util.ArrayList;
import static yapaysiniragi.GerekliHesaplamaDegerleri.momentumKatSayisi;
import static yapaysiniragi.GerekliHesaplamaDegerleri.ogrenmeKatsayisi;

public class GeriYayilimAraGirisKatman {

    static ArrayList sjTurevDizi = new ArrayList();
    static ArrayList deltaAgirlikAraGiris = new ArrayList();
    static ArrayList oncekiDeltaAgirlikAraGiris = new ArrayList();
    static ArrayList guncelAgirlikAraGiris = new ArrayList();

    public GeriYayilimAraGirisKatman() {
        for (int i = 0; i < noronSayi * (sutun - 1); i++) {
            oncekiDeltaAgirlikAraGiris.add(0.0);//bir kere nesne oluşturuyoruz ve 1 kere sıfırlar üretiyor.
        }
    }

    public void araGirisKatmanGuncelle() {
        for (int i = 0; i < noronSigmoid.size(); i++) {
            double sjTurev = (double) noronSigmoid.get(i) * (1 - (double) noronSigmoid.get(i));
            sjTurevDizi.add(sjTurev);
        }
        //System.out.println("sjTurev:" + sjTurevDizi);
        //System.out.println("onceki delta:" + oncekiDeltaAgirlikAraGiris);
        //guncelagirlikcikis * sm formülde toplam sembollü ama beyin nöron yapısı "ağaç" yapısına uyması adına
        //böyle yaptık arada bir katman daha olsaydı toplam sembolünü bağlı norönlar arası kullanabilirdik.
        int artis = 0;
        for (int i = 0; i < noronSayi; i++) {
            for (int j = 0; j < sutun - 1; j++) {
                double sj = (ogrenmeKatsayisi * ((double) sjTurevDizi.get(i) * sm * (double) guncelAgirlikAraCikis.get(i)) * (double) randomVeri.get(j)) + (momentumKatSayisi * (double) oncekiDeltaAgirlikAraGiris.get(artis));
                deltaAgirlikAraGiris.add(sj);
                artis++;
            }

        }
        //System.out.println("delta:" + deltaAgirlikAraGiris);
        for (int i = 0; i < deltaAgirlikAraGiris.size(); i++) {
            double guncel = (double) deltaAgirlikAraGiris.get(i) + (double) agirlikDegerleriGiris.get(i);
            guncelAgirlikAraGiris.add(guncel);
        }
        //System.out.println("guncel:" + guncelAgirlikAraGiris);

    }

    public void degerGuncellemeler() {
        oncekiDeltaAgirlikAraGiris.clear();
        oncekiDeltaAgirlikAraGiris = (ArrayList) deltaAgirlikAraGiris.clone();
        deltaAgirlikAraGiris.clear();
        agirlikDegerleriGiris.clear();
        agirlikDegerleriGiris = (ArrayList) guncelAgirlikAraGiris.clone();
        guncelAgirlikAraGiris.clear();
        sjTurevDizi.clear();

    }
}

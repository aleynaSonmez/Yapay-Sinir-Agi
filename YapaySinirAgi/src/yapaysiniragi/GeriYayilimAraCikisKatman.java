/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import yapaysiniragi.GerekliHesaplamaDegerleri;
import static yapaysiniragi.IleriBeslemeAraCikisKatman.agirlikDegerleriCikis;
import static yapaysiniragi.IleriBeslemeAraCikisKatman.hata;
import static yapaysiniragi.IleriBeslemeAraCikisKatman.noronSigmoidCikis;
import static yapaysiniragi.IleriBeslemeGirisAraKatman.noronSigmoid;
import static yapaysiniragi.UygulamaArayuz.noronSayi;
import java.util.ArrayList;
import static yapaysiniragi.GerekliHesaplamaDegerleri.momentumKatSayisi;
import static yapaysiniragi.GerekliHesaplamaDegerleri.ogrenmeKatsayisi;

/**
 *
 * @author sonme
 */
public class GeriYayilimAraCikisKatman {

    GerekliHesaplamaDegerleri gerekliDegerler = new GerekliHesaplamaDegerleri();
    static ArrayList deltaAgirlikAraCikis = new ArrayList();
    static ArrayList oncekiDeltaAgirlikAraCikis = new ArrayList();
    static ArrayList guncelAgirlikAraCikis = new ArrayList();
    static double sm = 0;

    public GeriYayilimAraCikisKatman() {
        for (int i = 0; i < noronSayi; i++) {
            oncekiDeltaAgirlikAraCikis.add(0.0);//bir kere nesne oluşturuyoruz ve 1 kere sıfırlar üretiyor.
        }
    }

    public void araCikisKatmanGuncelle() {
        sm = (noronSigmoidCikis * (1 - noronSigmoidCikis)) * hata;
        //System.out.println("sm:" + sm);

        //System.out.println("onceki delta:" + oncekiDeltaAgirlikAraCikis);
        for (int i = 0; i < noronSigmoid.size(); i++) {
            double delta = ogrenmeKatsayisi * sm * (double) noronSigmoid.get(i) + momentumKatSayisi * (double) oncekiDeltaAgirlikAraCikis.get(i);
            deltaAgirlikAraCikis.add(delta);
        }
        //System.out.println("delta:" + deltaAgirlikAraCikis);
        for (int i = 0; i < deltaAgirlikAraCikis.size(); i++) {
            double guncel = (double) agirlikDegerleriCikis.get(i) + (double) deltaAgirlikAraCikis.get(i);
            guncelAgirlikAraCikis.add(guncel);
        }
        //System.out.println("guncel agirlik ara cikis:" + guncelAgirlikAraCikis);
    }

    public void degerGuncellemeler() {
        oncekiDeltaAgirlikAraCikis.clear();
        oncekiDeltaAgirlikAraCikis = (ArrayList) deltaAgirlikAraCikis.clone();
        deltaAgirlikAraCikis.clear();
        agirlikDegerleriCikis.clear();
        agirlikDegerleriCikis = (ArrayList) guncelAgirlikAraCikis.clone();
        guncelAgirlikAraCikis.clear();
    }

}

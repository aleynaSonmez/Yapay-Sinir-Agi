/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import static yapaysiniragi.IleriBeslemeAraCikisKatman.agirlikDegerleriCikis;
import static yapaysiniragi.IleriBeslemeGirisAraKatman.agirlikDegerleriGiris;
import static yapaysiniragi.UygulamaArayuz.satir;
import static yapaysiniragi.UygulamaArayuz.testCikisAgirliklari;
import static yapaysiniragi.UygulamaArayuz.testGirisAgirliklari;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author sonme
 */
public class GerekliHesaplamaDegerleri {

    ArrayList<Double> normalizasyon(ArrayList<Double> veriler) {
        double veri;
        double normalizeVeri = 0;
        double min = 0;
        double max = 0;
        ArrayList<Double> normalizeEdilmisDeger = new ArrayList<>();
        min = veriler.indexOf(Collections.min(veriler));
        min = veriler.get((int) min);
        max = veriler.indexOf(Collections.max(veriler));
        max = veriler.get((int) max);
        for (int i = 0; i < veriler.size(); i++) {
            veri = veriler.get(i);
            normalizeVeri = (veri - min) / (max - min);
            normalizeEdilmisDeger.add(normalizeVeri);
        }
        return normalizeEdilmisDeger;
    }

    static ArrayList randomSatirUret() {
        ArrayList<Integer> randomDizi = new ArrayList<>();
        for (int a = 0; a < satir;) {
            int sayi = ThreadLocalRandom.current().nextInt(0, satir);
            if (!randomDizi.contains(sayi)) {
                randomDizi.add(sayi);
                a++;
            } else {
                continue;
            }
        }
        return randomDizi;
    }

    double randomSayi() {
        double randomSayi = ThreadLocalRandom.current().nextDouble(0, 1);
        return randomSayi;

    }

    public ArrayList<Double> agirlikGiris(int noronSayisi, int sutun, ArrayList agirlikDegerleri) {
        for (int i = 0; i < (sutun - 1) * noronSayisi; i++) {
            agirlikDegerleri.add(randomSayi());
        }

        return agirlikDegerleri;
    }

    public ArrayList biasGiris(int noronSayisi, ArrayList biasDegerleri) {
        for (int u = 0; u < noronSayisi; u++) {
            biasDegerleri.add(randomSayi());

        }
        return biasDegerleri;
    }

    public static double sigmoid(double x) {
        return (double) (1 / (1 + Math.exp(-x)));
    }

    public ArrayList agirlikCikis(int nöronSayisi, ArrayList agirlikDegerleriCikis) {
        for (int u = 0; u < nöronSayisi; u++) {
            agirlikDegerleriCikis.add(randomSayi());

        }
        return agirlikDegerleriCikis;
    }

    static final double ogrenmeKatsayisi = 0.01;
    static final double momentumKatSayisi = 0.02;

    public static ArrayList mape(ArrayList<Double> hataMutlak, ArrayList<Double> gercekCikis, int n) {
        ArrayList<Double> topla = new ArrayList();
        ArrayList<Double> mape = new ArrayList();
        double toplam = 0;
        double sonuc;
        for (int a = 0; a < gercekCikis.size(); a++) {
            try {
                toplam += (hataMutlak.get(a) / gercekCikis.get(a));
            } catch (ArithmeticException ex) {
                //ex.printStackTrace();
            }
        }
        topla.add(toplam);
        //System.out.println("top:" + topla);
        for (int h = 0; h < topla.size(); h++) {
            sonuc = (topla.get(h) / n) * 100;//n -> gözlem sayısı
            mape.add(sonuc);

        }
        return mape;
    }

    public static ArrayList mse(ArrayList<Double> hata, int n) {
        ArrayList<Double> topla = new ArrayList();
        ArrayList<Double> mse = new ArrayList();
        double toplam = 0;
        double bolum;
        for (int a = 0; a < 1; a++) {
            for (int c = 0; c < hata.size(); c++) {
                try {
                    toplam += hata.get(c) * hata.get(c);
                } catch (ArithmeticException ex) {
                    //ex.printStackTrace();
                }

            }
            topla.add(toplam);
        }
        for (int b = 0; b < topla.size(); b++) {
            try {
                bolum = topla.get(b) / n;//n = 1 ya da bunu sor 768 mi??? toplam gerçek veriler mi?
                //System.out.println("bolum:"+bolum);
                mse.add(bolum);
            } catch (ArithmeticException ex) {

            }

        }
        return mse;
    }

    public void testAgirliklari() {
        //eğitim sonunda agırlıkları kullanmak adına tüm eğitim boyunca bu dizilere ekliyorum.
        testGirisAgirliklari.addAll(agirlikDegerleriGiris);//all hepsini ekliyor. add son değeri ekliyor.
        testCikisAgirliklari.addAll(agirlikDegerleriCikis);

    }
}

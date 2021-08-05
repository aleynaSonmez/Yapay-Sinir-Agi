/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import yapaysiniragi.GeriYayilimAraGirisKatman;
import yapaysiniragi.GeriYayilimAraCikisKatman;
import yapaysiniragi.GerekliHesaplamaDegerleri;
import yapaysiniragi.ExcelIslemler;
import static yapaysiniragi.ExcelIslemler.egitimSeti;
import static yapaysiniragi.ExcelIslemler.testSeti;
import static yapaysiniragi.IleriBeslemeAraCikisKatman.gercekCikis;
import static yapaysiniragi.IleriBeslemeAraCikisKatman.hata;
import static yapaysiniragi.IleriBeslemeGirisAraKatman.noronSigmoid;
import static yapaysiniragi.IleriBeslemeGirisAraKatman.randomVeri;
import static yapaysiniragi.TestSinifi.gercekCikisTest;
import static yapaysiniragi.TestSinifi.hataTest;
import static yapaysiniragi.TestSinifi.noronSigmoidTest;
import static yapaysiniragi.TestSinifi.randomVeriTest;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author sonme
 */
public class UygulamaArayuz extends javax.swing.JFrame {

    static int satir = 768;
    static int sutun = 9;
    static int noronSayi;
    static int siraliGezme = 0;
    static int siraliGezmeTest = 0;
    static int epokSayisi = 0;
    static int agirlikTest;
    static int agirlikTestCikis;
    ExcelIslemler excelIslemler = new ExcelIslemler();
    GerekliHesaplamaDegerleri gerekliDegerler = new GerekliHesaplamaDegerleri();
    GrafikCiz grafik = new GrafikCiz();
    ArrayList hataMutlak = new ArrayList();
    ArrayList hataMutlakTest = new ArrayList();
    ArrayList kareler = new ArrayList();
    ArrayList gercekCikis1 = new ArrayList();
    ArrayList gercekCikis1Test = new ArrayList();
    static ArrayList testGirisAgirliklari = new ArrayList();
    static ArrayList testCikisAgirliklari = new ArrayList();
    static ArrayList testGirisBiaslari = new ArrayList();
    static ArrayList testCikisBiaslari = new ArrayList();

    public UygulamaArayuz() {
        initComponents();
    }

    public void uygula() {
        noronSayi = Integer.parseInt(noronSayisi.getText());
        IleriBeslemeAraCikisKatman araCikis = new IleriBeslemeAraCikisKatman();
        IleriBeslemeGirisAraKatman girisAra = new IleriBeslemeGirisAraKatman();
        GeriYayilimAraCikisKatman gAraCikis = new GeriYayilimAraCikisKatman();
        GeriYayilimAraGirisKatman gAraGiris = new GeriYayilimAraGirisKatman();
        TestSinifi testSinifi = new TestSinifi();
        excelIslemler.veriIslemleri();
        if (Integer.parseInt(noronSayisi.getText()) >= 2 && Integer.parseInt(noronSayisi.getText()) <= 20) {
            for (int j = 0; j < 100; j++) {
                if (epokSayisi < 101) {
                    siraliGezme = 0;
                    System.out.println(epokSayisi + 1 + " .epok");
                    for (int i = 0; i < egitimSeti; i++) {
                        girisAra.islemler();//yerleri değişik olduğunda çalışmıyor.
                        araCikis.islemler();
                        hataMutlak.add(Math.abs(hata));
                        //infinity durumunu ortadan kaldırmak için 0 a çok yakın normalize yleri göz ardı ediyoruz.
                        if (gercekCikis != 0) {
                            gercekCikis1.add(gercekCikis);
                        }
                        kareler.add(hata);
                        gAraCikis.araCikisKatmanGuncelle();
                        gAraGiris.araGirisKatmanGuncelle();
                        gerekliDegerler.testAgirliklari();//test için gerekli veriler topluyorum.
                        gAraCikis.degerGuncellemeler();
                        gAraGiris.degerGuncellemeler();
                        noronSigmoid.clear();
                        randomVeri.clear();
                        siraliGezme++;
                        if ((i + 1) % 50 == 0) {
                            double z2 = 0;
                            z2 = (double) gerekliDegerler.mse(kareler, 50).get(0);//n değeri gözlem sayısı
                            System.out.println("mse: " + z2);
                            grafik.hataVerileri.add(z2);
                            kareler.clear();
                        }

                    }
                    System.out.println("mape:" + gerekliDegerler.mape(hataMutlak, gercekCikis1, egitimSeti));
                    if (gerekliDegerler.mape(hataMutlak, gercekCikis1, egitimSeti).equals(3)) {
                        break;
                    }
                    hataMutlak.clear();
                    gercekCikis1.clear();

                }
                epokSayisi++;

            }

            System.out.println("Eğitim sona erdi..");
            grafik.setVisible(true);
            grafik.calistir();
            System.out.println("Test Başladı..");
            for (int i = 0; i <= testSeti; i++) {
                //System.out.println(i + 1 + ".satır");
                testSinifi.testIslemleriGirisAra();
                testSinifi.testIslemleriAraCikis();
                siraliGezmeTest++;
                agirlikTest++;//son guncel agirliklar alınıyor.reverse ile baştan ama
                //aslında 231*8*noronSayisi kadar sondan alınıyor.
                //reverse ile en günceller ilk indekslere çekilip kullanılıyor.
                agirlikTestCikis++;
                hataMutlakTest.add(Math.abs(hataTest));
                if (gercekCikisTest != 0) {
                    gercekCikis1Test.add(gercekCikisTest);
                }
                randomVeriTest.clear();
                noronSigmoidTest.clear();

            }
            System.out.println("mapeTest:" + gerekliDegerler.mape(hataMutlakTest, gercekCikis1Test, testSeti));
        } else {
            JOptionPane.showMessageDialog(null, "Nöron Sayısını [2-20] Arasında Giriniz");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calistir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        noronSayisi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        calistir.setText("Uygula");
        calistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calistirActionPerformed(evt);
            }
        });

        jLabel4.setText("Nöron Sayısını Giriniz:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calistir, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noronSayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noronSayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(calistir, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calistirActionPerformed
        uygula();
    }//GEN-LAST:event_calistirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UygulamaArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UygulamaArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UygulamaArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UygulamaArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UygulamaArayuz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calistir;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField noronSayisi;
    // End of variables declaration//GEN-END:variables
}

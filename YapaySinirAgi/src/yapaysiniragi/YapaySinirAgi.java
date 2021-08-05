/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import javax.swing.JFrame;

/**
 *
 * @author sonme
 */
public class YapaySinirAgi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UygulamaArayuz m = new UygulamaArayuz();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setVisible(true);
        m.setTitle("Yapay Sinir Ağı");
    }

}

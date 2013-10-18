/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import logiikka.Logiikka;
import logiikka.PelinArgumentit;
import logiikka.tekoaly.Vaikeustaso;

/**
 *
 * @author henrik
 */
public class Valinnat extends javax.swing.JFrame implements Runnable {

    private boolean syoteetOk;

    /**
     * Creates new form Valinnat
     */
    public Valinnat() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lentotukialukset = new javax.swing.JTextField();
        taistelulaivat = new javax.swing.JTextField();
        risteilijat = new javax.swing.JTextField();
        havittajat = new javax.swing.JTextField();
        virhekentta = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        sukellusveneet = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        vaikeusTasonValinta = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laivanupotus - Uusi peli");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jTextField1.setText("10");

        jButton1.setText("Aloita Peli");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Pelilaudan koko:");

        jLabel2.setText("Laivat:");

        jLabel3.setText("Lentotukialus");

        jLabel4.setText("Taistelulaiva");

        jLabel5.setText("Risteilijä");

        jLabel6.setText("Hävittäjä");

        lentotukialukset.setText("1");
        lentotukialukset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lentotukialuksetActionPerformed(evt);
            }
        });

        taistelulaivat.setText("1");

        risteilijat.setText("2");

        havittajat.setText("2");

        virhekentta.setForeground(new java.awt.Color(255, 0, 0));

        jLabel7.setText("Nimi");

        jLabel8.setText("Koko");

        jLabel9.setText("Lukumäärä");

        jLabel10.setText("5");

        jLabel11.setText("4");

        jLabel12.setText("3");

        jLabel13.setText("2");

        jLabel14.setText("Sukellusvene");

        jLabel15.setText("1");

        sukellusveneet.setText("0");
        sukellusveneet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sukellusveneetActionPerformed(evt);
            }
        });

        jLabel16.setText("Vaikeustaso");

        vaikeusTasonValinta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Helppo", "Keskitaso", "Vaikea" }));
        vaikeusTasonValinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaikeusTasonValintaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(virhekentta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(37, 37, 37)
                        .addComponent(vaikeusTasonValinta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel6))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel11)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel13))))
                                        .addGap(18, 100, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(taistelulaivat, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                                    .addComponent(lentotukialukset)
                                                    .addComponent(risteilijat)
                                                    .addComponent(havittajat)
                                                    .addComponent(sukellusveneet))))
                                        .addGap(58, 58, 58))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel15)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lentotukialukset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(taistelulaivat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(risteilijat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(havittajat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(sukellusveneet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(vaikeusTasonValinta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(virhekentta, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        syoteetOk = true;
        if (!syotteetOnLaillisia()) {
            return;
        }
        if (ikkunaOnLiianiso()) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                int pelilaudankoko = haekentanArvo(jTextField1);
                int lentotukialustenMaara = haekentanArvo(lentotukialukset);
                int taisteluLaivojenMaara = haekentanArvo(taistelulaivat);
                int risteilijoidenMaara = haekentanArvo(risteilijat);
                int havittajienMaara = haekentanArvo(havittajat);
                int sukellusVeneidenMaara = haekentanArvo(sukellusveneet);
                Vaikeustaso vaikeustaso;
                System.out.println(vaikeusTasonValinta.getSelectedItem().toString());
                switch (vaikeusTasonValinta.getSelectedItem().toString()) {
                    case "Helppo":
                        vaikeustaso = Vaikeustaso.HELPPO;
                        break;
                    case "Keskitaso":
                        vaikeustaso = Vaikeustaso.KESKITASO;
                        break;
                    default:
                        vaikeustaso = Vaikeustaso.HANKALA;
                }
                PelinArgumentit argumentit = new PelinArgumentit(pelilaudankoko, lentotukialustenMaara, taisteluLaivojenMaara, risteilijoidenMaara, havittajienMaara, sukellusVeneidenMaara, vaikeustaso);
                Logiikka.uusiPeli(argumentit);

            }
        }.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Valinnat.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setVisible(false);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void lentotukialuksetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lentotukialuksetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lentotukialuksetActionPerformed

    private void sukellusveneetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sukellusveneetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sukellusveneetActionPerformed

    private void vaikeusTasonValintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaikeusTasonValintaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vaikeusTasonValintaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField havittajat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField lentotukialukset;
    private javax.swing.JTextField risteilijat;
    private javax.swing.JTextField sukellusveneet;
    private javax.swing.JTextField taistelulaivat;
    private javax.swing.JComboBox vaikeusTasonValinta;
    private javax.swing.JLabel virhekentta;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        new Valinnat().setVisible(true);
    }

    private boolean syotteetOnLaillisia() {
        int pelilaudankoko, lentotukialustenMaara, taisteluLaivojenMaara, risteilijoidenMaara, havittajienMaara, sukellusVeneidenMaara;
        try {
            pelilaudankoko = haekentanArvo(jTextField1);
            lentotukialustenMaara = haekentanArvo(lentotukialukset);
            taisteluLaivojenMaara = haekentanArvo(taistelulaivat);
            risteilijoidenMaara = haekentanArvo(risteilijat);
            havittajienMaara = haekentanArvo(havittajat);
            sukellusVeneidenMaara = haekentanArvo(sukellusveneet);
        } catch (NumberFormatException e) {
            virhekentta.setText("Virheellisiä arvoja");
            return false;
        }
        int summa = 0;
        summa += (5 * 4 + 1) * lentotukialustenMaara;
        summa += (4 * 4 + 1) * taisteluLaivojenMaara;
        summa += (3 * 4 + 1) * risteilijoidenMaara;
        summa += (2 * 4 + 1) * havittajienMaara;
        summa += sukellusVeneidenMaara * 5;
        if (summa > pelilaudankoko * pelilaudankoko) {
            virhekentta.setText("Laivat eivät mahdu kunnolla pelilaudalle");
            return false;
        }
        return true;
    }

    private int haekentanArvo(JTextField tekstiKentta) throws NumberFormatException {
        int palautettava = Integer.parseInt(tekstiKentta.getText());
        if (palautettava < 0) {
            throw new NumberFormatException();
        }
        return palautettava;
    }

    private boolean ikkunaOnLiianiso() {
        int pelilaudankoko = haekentanArvo(jTextField1);
        Dimension naytonKoko = Toolkit.getDefaultToolkit().getScreenSize();
        if ((pelilaudankoko * 2 + 3) * 30 > naytonKoko.getWidth() || (pelilaudankoko + 2) * 30 > naytonKoko.getHeight()) {
            virhekentta.setText("Tämän kokoinen pelilauta ei mahdu näytölle");
            return true;
        }
        return false;
    }
}

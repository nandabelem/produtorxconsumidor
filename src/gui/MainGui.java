/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import produtorxconsumidor.*;


/**
 *
 * @author aluno-linux
 */
public class MainGui extends javax.swing.JFrame  {
       
    /**
     * Creates new form Main
     */
    public MainGui() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numProdCons = new javax.swing.JTextField();
        tamBuffer = new javax.swing.JTextField();
        iniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Numero de produtores e consumidores");

        jLabel2.setText("Tamanho do buffer");

        numProdCons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numProdConsActionPerformed(evt);
            }
        });

        tamBuffer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamBufferActionPerformed(evt);
            }
        });

        iniciar.setText("INICIAR");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numProdCons)
                    .addComponent(tamBuffer, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(numProdCons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tamBuffer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(39, 39, 39)
                .addComponent(iniciar)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numProdConsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numProdConsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numProdConsActionPerformed

    private void tamBufferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamBufferActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tamBufferActionPerformed

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        int buffer = Integer.parseInt(tamBuffer.getText());
        int quantidade = Integer.parseInt(numProdCons.getText());
        tamBuffer.setText("");
        numProdCons.setText("");
        dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Log().setVisible(true);
            }
        });
        inicializa(buffer,quantidade);
    }//GEN-LAST:event_iniciarActionPerformed

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
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        
    }
    
        public void inicializa(int tamanhoBuffer, int qtdProdutoresConsumidores){
        
        Buffer buffer = new Buffer(tamanhoBuffer);
        Sincroniza sincroniza = new Sincroniza();
        Produtor[] vetorProdutor;
        Consumidor[] vetorConsumidor;
        Lock lock = new ReentrantLock();
        Log log = new Log();
      
        
        buffer.iniciarBuffer();
        
        
        vetorProdutor = new Produtor[qtdProdutoresConsumidores];
        vetorConsumidor = new Consumidor[qtdProdutoresConsumidores];
        
        for(int i = 1; i < qtdProdutoresConsumidores+1; i++){
            Produtor produtor = new Produtor(i, buffer, sincroniza, lock, log);
            Consumidor consumidor = new Consumidor(i, buffer, sincroniza, lock, log);
            vetorProdutor[i-1] = produtor;
            vetorConsumidor[i-1] = consumidor;
            Thread threadDoProdutor = new Thread(produtor);
            Thread threadDoConsumidor = new Thread(consumidor);            
            
            produtor.start();
            System.out.println("Criou produtor "+i);
            System.out.flush();
            
            consumidor.start();  
            System.out.println("Criou consumidor "+i);
            System.out.flush();
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton iniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField numProdCons;
    private javax.swing.JTextField tamBuffer;
    // End of variables declaration//GEN-END:variables
}
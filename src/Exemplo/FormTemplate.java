/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exemplo;

import javax.swing.JOptionPane;

/**
 *
 * @author Windows7
 */
public class FormTemplate extends javax.swing.JFrame {

    /**
     * Creates new form FormTemplate
     */
    public FormTemplate() {
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

        jPanel1 = new javax.swing.JPanel();
        jPBuscar = new javax.swing.JPanel();
        jTFNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBBuscaRapida = new javax.swing.JTable();
        jLBusca = new javax.swing.JLabel();
        jLInstrucao = new javax.swing.JLabel();
        jPBotoes = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        
        jBTAlterar = new javax.swing.JButton();
        jBTSalvar = new javax.swing.JButton();
        jBTExcluir = new javax.swing.JButton();
        jBTCadastrar = new javax.swing.JButton();
        jBTConfirmar = new javax.swing.JButton();
        jBTCancelar = new javax.swing.JButton();
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPBuscar.setPreferredSize(new java.awt.Dimension(600, 300));

        jTFNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomeActionPerformed(evt);
            }
        });

        jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTBBuscaRapida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBBuscaRapidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTBBuscaRapida);

        jLBusca.setText("Busca R�pida");

        jLInstrucao.setText("Instru��es para a pesquisa");

        javax.swing.GroupLayout jPBuscarLayout = new javax.swing.GroupLayout(jPBuscar);
        jPBuscar.setLayout(jPBuscarLayout);
        jPBuscarLayout.setHorizontalGroup(
            jPBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLInstrucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPBuscarLayout.createSequentialGroup()
                        .addComponent(jLBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNome))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPBuscarLayout.setVerticalGroup(
            jPBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLInstrucao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPBuscar, java.awt.BorderLayout.PAGE_START);

        jPBotoes.setPreferredSize(new java.awt.Dimension(600, 50));
        
        jBTAlterar.setText("Alterar");
        jBTAlterar.setEnabled(false);
        /*
        jBTAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAlterarActionPerformed(evt);
            }
        });
        */

        jBTSalvar.setText("Salvar");
        jBTSalvar.setEnabled(false);
        /*
         jBTSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTSalvarActionPerformed(evt);
            }
        });
         */

        jBTExcluir.setText("Excluir");
        jBTExcluir.setEnabled(false);
        /*
         jBTExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTExcluirActionPerformed(evt);
            }
        });
         */

        jBTCadastrar.setText("Cadastrar");
        /*
         jBTCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCadastrarActionPerformed(evt);
            }
        });
         */

        jBTConfirmar.setText("Confirmar");
        jBTConfirmar.setEnabled(false);
        /*jBTConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTConfirmarActionPerformed(evt);
            }
        });
         */

        jBTCancelar.setText("Cancelar");
        jBTCancelar.setEnabled(false);
        /*jBTCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCancelarActionPerformed(evt);
            }
        });
        */

        javax.swing.GroupLayout jPBotoesLayout = new javax.swing.GroupLayout(jPBotoes);
        jPBotoes.setLayout(jPBotoesLayout);
        jPBotoesLayout.setHorizontalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jBTCadastrar)
                .addGap(22, 22, 22)
                .addComponent(jBTConfirmar)
                .addGap(26, 26, 26)
                .addComponent(jBTCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jBTAlterar)
                .addGap(18, 18, 18)
                .addComponent(jBTSalvar)
                .addGap(18, 18, 18)
                .addComponent(jBTExcluir)
                .addGap(52, 52, 52))
        );
        jPBotoesLayout.setVerticalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBTExcluir)
                        .addComponent(jBTSalvar)
                        .addComponent(jBTAlterar))
                    .addGroup(jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBTCadastrar)
                        .addComponent(jBTConfirmar)
                        .addComponent(jBTCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPBotoes, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNomeActionPerformed

    protected void jBTCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTCadastrarActionPerformed
        this.jBTConfirmar.setEnabled(true);
        this.jBTCadastrar.setEnabled(false);
        this.jBTAlterar.setEnabled(false);
        this.jBTSalvar.setEnabled(false);
        this.jBTExcluir.setEnabled(false);
        this.jBTCancelar.setEnabled(true);
    }//GEN-LAST:event_jBTCadastrarActionPerformed

    protected void jBTConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTConfirmarActionPerformed
        this.jBTConfirmar.setEnabled(false);
        this.jBTCadastrar.setEnabled(true);
        this.jBTAlterar.setEnabled(false);
        this.jBTSalvar.setEnabled(false);
        this.jBTExcluir.setEnabled(false);
        this.jBTCancelar.setEnabled(false);
    }//GEN-LAST:event_jBTConfirmarActionPerformed

    protected void jBTAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAlterarActionPerformed
        this.jBTConfirmar.setEnabled(false);
        this.jBTCadastrar.setEnabled(false);
        this.jBTSalvar.setEnabled(true);
        this.jBTAlterar.setEnabled(false);
        this.jBTExcluir.setEnabled(false);
        this.jBTCancelar.setEnabled(true);
    }//GEN-LAST:event_jBTAlterarActionPerformed

    protected void jBTSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTSalvarActionPerformed
        this.jBTConfirmar.setEnabled(false);
        this.jBTCadastrar.setEnabled(true);
        this.jBTSalvar.setEnabled(false);
        this.jBTAlterar.setEnabled(false);
        this.jBTExcluir.setEnabled(false);
        this.jBTCancelar.setEnabled(false);
    }//GEN-LAST:event_jBTSalvarActionPerformed

    protected void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTExcluirActionPerformed
        int confirmOption = 1;

        confirmOption = JOptionPane.showConfirmDialog(null, "Voc� realmente deseja excluir estes dados?", "Alerta de exclus�o de dados", JOptionPane.YES_NO_OPTION);
                
        if (confirmOption == 1)
            return;
            
        
        this.jBTConfirmar.setEnabled(false);
        this.jBTCadastrar.setEnabled(true);
        this.jBTAlterar.setEnabled(false);
        this.jBTSalvar.setEnabled(false);
        this.jBTExcluir.setEnabled(false);
        this.jBTCancelar.setEnabled(false);
    }//GEN-LAST:event_jBTExcluirActionPerformed

    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBBuscaRapidaMouseClicked
        this.jBTConfirmar.setEnabled(false);
        this.jBTCadastrar.setEnabled(false);
        this.jBTAlterar.setEnabled(false);
        this.jBTAlterar.setEnabled(true);
        this.jBTExcluir.setEnabled(true);
        this.jBTCancelar.setEnabled(true);
    }//GEN-LAST:event_jTBBuscaRapidaMouseClicked

    protected void jBTCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTCancelarActionPerformed
        this.jBTConfirmar.setEnabled(false);
        this.jBTCadastrar.setEnabled(true);
        this.jBTAlterar.setEnabled(false);
        this.jBTSalvar.setEnabled(false);
        this.jBTExcluir.setEnabled(false);
        this.jBTCancelar.setEnabled(false);
    }//GEN-LAST:event_jBTCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FormTemplate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTemplate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTemplate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTemplate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTemplate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jBTAlterar;
    protected javax.swing.JButton jBTCadastrar;
    protected javax.swing.JButton jBTCancelar;
    protected javax.swing.JButton jBTConfirmar;
    protected javax.swing.JButton jBTExcluir;
    protected javax.swing.JButton jBTSalvar;
    protected javax.swing.JLabel jLBusca;
    private javax.swing.JLabel jLInstrucao;
    protected javax.swing.JPanel jPBotoes;
    protected javax.swing.JPanel jPBuscar;
    private javax.swing.JPanel jPanel1;
    protected javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    protected javax.swing.JTable jTBBuscaRapida;
    protected javax.swing.JTextField jTFNome;
    // End of variables declaration//GEN-END:variables
}

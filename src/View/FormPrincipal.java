/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Dimension;

/**
 *
 * @author cristhian
 */
public class FormPrincipal extends javax.swing.JFrame {

    public static boolean isShowingModal;

    /**
     * Creates new form FormPrincipal
     */
    public FormPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setSize(new Dimension(408, 309));
        setMinimumSize(new Dimension(408, 309));

        jButtonManterAutomovel.setVisible(false);
        jButtonManterMulta.setVisible(false);
        jButtonManterPessoa.setVisible(false);
        jButtonManterTipoAutuacao.setVisible(false);

        isShowingModal = false;
    }

    public void ativaAdm() {
//        System.out.println("ativaAdm");
        jButtonLogarSistema.setVisible(false);
        jButtonManterAutomovel.setVisible(true);
        jButtonManterMulta.setVisible(true);
        jButtonManterPessoa.setVisible(true);
        jButtonManterTipoAutuacao.setVisible(true);
        setSize(new Dimension(408, 409));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelUsuario = new javax.swing.JPanel();
        jButtonBuscarAutomovel = new javax.swing.JButton();
        jButtonBuscarCarteira = new javax.swing.JButton();
        jButtonBuscarMulta = new javax.swing.JButton();
        jPanelAdm = new javax.swing.JPanel();
        jButtonLogarSistema = new javax.swing.JButton();
        jButtonManterAutomovel = new javax.swing.JButton();
        jButtonManterPessoa = new javax.swing.JButton();
        jButtonManterTipoAutuacao = new javax.swing.JButton();
        jButtonManterMulta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Usu�rio"));

        jButtonBuscarAutomovel.setText("Buscar autom�vel");
        jButtonBuscarAutomovel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarAutomovelActionPerformed(evt);
            }
        });

        jButtonBuscarCarteira.setText("Buscar carteira");
        jButtonBuscarCarteira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarCarteiraActionPerformed(evt);
            }
        });

        jButtonBuscarMulta.setText("Buscar multa");
        jButtonBuscarMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarMultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUsuarioLayout = new javax.swing.GroupLayout(jPanelUsuario);
        jPanelUsuario.setLayout(jPanelUsuarioLayout);
        jPanelUsuarioLayout.setHorizontalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonBuscarAutomovel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBuscarCarteira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBuscarMulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelUsuarioLayout.setVerticalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBuscarAutomovel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBuscarCarteira)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBuscarMulta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelAdm.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrador"));

        jButtonLogarSistema.setText("Logar no sistema");
        jButtonLogarSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogarSistemaActionPerformed(evt);
            }
        });

        jButtonManterAutomovel.setText("Manter autom�veis");
        jButtonManterAutomovel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManterAutomovelActionPerformed(evt);
            }
        });

        jButtonManterPessoa.setText("Manter pessoas");
        jButtonManterPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManterPessoaActionPerformed(evt);
            }
        });

        jButtonManterTipoAutuacao.setText("Manter tipos de autua��o");
        jButtonManterTipoAutuacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManterTipoAutuacaoActionPerformed(evt);
            }
        });

        jButtonManterMulta.setText("Manter multas");
        jButtonManterMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManterMultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAdmLayout = new javax.swing.GroupLayout(jPanelAdm);
        jPanelAdm.setLayout(jPanelAdmLayout);
        jPanelAdmLayout.setHorizontalGroup(
            jPanelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonLogarSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonManterAutomovel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonManterPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonManterTipoAutuacao, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addComponent(jButtonManterMulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelAdmLayout.setVerticalGroup(
            jPanelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonLogarSistema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonManterAutomovel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonManterPessoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonManterTipoAutuacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonManterMulta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelAdm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAdm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLogarSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogarSistemaActionPerformed
        if (isShowingModal) {
            return;
        }
        isShowingModal = true;

        FormLogar.getForm(this).abre();
    }//GEN-LAST:event_jButtonLogarSistemaActionPerformed

    private void jButtonBuscarMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarMultaActionPerformed
        if (isShowingModal) {
            return;
        }
        isShowingModal = true;

        FormVisualizarMulta.getForm().abre();
    }//GEN-LAST:event_jButtonBuscarMultaActionPerformed

    private void jButtonBuscarAutomovelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarAutomovelActionPerformed
        if (isShowingModal) {
            return;
        }
        isShowingModal = true;

        FormVisualizarAutomovel.getForm().abre();
    }//GEN-LAST:event_jButtonBuscarAutomovelActionPerformed

    private void jButtonBuscarCarteiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarCarteiraActionPerformed
        if (isShowingModal) {
            return;
        }
        isShowingModal = true;

        FormVisualizarCarteira.getForm().abre();
    }//GEN-LAST:event_jButtonBuscarCarteiraActionPerformed

    private void jButtonManterAutomovelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManterAutomovelActionPerformed
        if (isShowingModal) {
            return;
        }
        isShowingModal = true;

        FormManterAutomovel.getForm().abre();
    }//GEN-LAST:event_jButtonManterAutomovelActionPerformed

    private void jButtonManterPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManterPessoaActionPerformed
        if (isShowingModal) {
            return;
        }
        isShowingModal = true;

        FormManterPessoa.getForm().abre();
    }//GEN-LAST:event_jButtonManterPessoaActionPerformed

    private void jButtonManterTipoAutuacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManterTipoAutuacaoActionPerformed
        if (isShowingModal) {
            return;
        }
        isShowingModal = true;

        FormManterAutuacao.getForm().abre();
    }//GEN-LAST:event_jButtonManterTipoAutuacaoActionPerformed

    private void jButtonManterMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManterMultaActionPerformed
        if (isShowingModal) {
            return;
        }
        isShowingModal = true;

        FormManterMulta.getForm().abre();
    }//GEN-LAST:event_jButtonManterMultaActionPerformed

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
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarAutomovel;
    private javax.swing.JButton jButtonBuscarCarteira;
    private javax.swing.JButton jButtonBuscarMulta;
    private javax.swing.JButton jButtonLogarSistema;
    private javax.swing.JButton jButtonManterAutomovel;
    private javax.swing.JButton jButtonManterMulta;
    private javax.swing.JButton jButtonManterPessoa;
    private javax.swing.JButton jButtonManterTipoAutuacao;
    private javax.swing.JPanel jPanelAdm;
    private javax.swing.JPanel jPanelUsuario;
    // End of variables declaration//GEN-END:variables
}

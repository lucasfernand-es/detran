/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exemplo;

/**
 *
 * @author Windows7
 */
public class FormManterExercicio extends FormTemplate {

    /**
     * Creates new form FormManterExercicio
     */
    private FormManterExercicio() {
        this.setTitle("Gerenciar Exercicios");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);
    }
    
    private static FormManterExercicio manterExercicio= null;
    
    public static FormManterExercicio getFormManterExercicio()
    {
        if(manterExercicio == null)
            manterExercicio = new FormManterExercicio();
        
        return manterExercicio;
            
    }
    
    public void iniciarComponentes ()
    {                
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Músculo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        super.jScrollPane1.setViewportView(jTBBuscaRapida);
        
        jTBBuscaRapida.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBBuscaRapidaMouseClicked(evt);
            }
        });
        
        jBTAlterar.setText("Alterar");
        jBTAlterar.setEnabled(false);
        jBTAlterar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAlterarActionPerformed(evt);
            }
        });

        jBTSalvar.setText("Salvar");
        jBTSalvar.setEnabled(false);
        jBTSalvar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTSalvarActionPerformed(evt);
            }
        });

        jBTExcluir.setText("Excluir");
        jBTExcluir.setEnabled(false);
        jBTExcluir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTExcluirActionPerformed(evt);
            }
        });

        jBTCadastrar.setText("Cadastrar");
        jBTCadastrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCadastrarActionPerformed(evt);
            }
        });

        jBTConfirmar.setText("Confirmar");
        jBTConfirmar.setEnabled(false);
        jBTConfirmar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTConfirmarActionPerformed(evt);
            }
        });

        jBTCancelar.setText("Cancelar");
        jBTCancelar.setEnabled(false);
        jBTCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCancelarActionPerformed(evt);
            }
        });        
    }
    
    public void liberarComponentes()
    {
        this.jTFMusculo.setEnabled(true);
        this.jTFNomeEvento.setEnabled(true);
    }
    
    public void bloquearComponentes()
    {
        this.jTFMusculo.setEnabled(false);
        this.jTFNomeEvento.setEnabled(false);
        
        
    }
    
public void limparComponentes()
    {
        this.jTFMusculo.setText(null);
        this.jTFNomeEvento.setText(null);
        
    }
    
        @Override
    protected void jBTCadastrarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        super.jBTCadastrarActionPerformed(evt);
        
        liberarComponentes();
    }                                            

    @Override
    protected void jBTConfirmarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        super.jBTConfirmarActionPerformed(evt);
        
        bloquearComponentes();
        limparComponentes();
    }                                            

    @Override
    protected void jBTAlterarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        super.jBTAlterarActionPerformed(evt);
        
        liberarComponentes();
    }                                          

    @Override
    protected void jBTSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        super.jBTSalvarActionPerformed(evt);
        
        bloquearComponentes();
        limparComponentes();
    }                                         

    @Override
    protected void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        super.jBTExcluirActionPerformed(evt);
        
        bloquearComponentes();
        limparComponentes();
    }                                          

    @Override
    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {  
        super.jTBBuscaRapidaMouseClicked(evt);
        
        bloquearComponentes();
    }                                           

    @Override
    protected void jBTCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        super.jBTCancelarActionPerformed(evt);
        
        bloquearComponentes();
        limparComponentes();
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTFNomeEvento = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTFMusculo = new javax.swing.JTextField();

        jPanel3.setPreferredSize(new java.awt.Dimension(600, 50));

        jTFNomeEvento.setEnabled(false);

        jLabel12.setText("Nome");

        jLabel13.setText("Músculo");

        jTFMusculo.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jTFNomeEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFMusculo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTFMusculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomeEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FormManterExercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManterExercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManterExercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManterExercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormManterExercicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTFMusculo;
    private javax.swing.JTextField jTFNomeEvento;
    // End of variables declaration//GEN-END:variables
}

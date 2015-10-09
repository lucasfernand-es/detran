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
public class FormManterEvento extends FormTemplate {

    /**
     * Creates new form FormManterEvento
     */
    private FormManterEvento() {
        this.setTitle("Gerenciar Agenda");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);
    }
    
    private static FormManterEvento manterEvento= null;
    
    public static FormManterEvento getFormManterEvento()
    {
        if(manterEvento == null)
            manterEvento = new FormManterEvento();
        
        return manterEvento;
            
    }
    
    public void iniciarComponentes ()
    {        
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Evento", "Status da Academia", "Data do Evento", "Hora Inicio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        this.jCBHoraFim.setEnabled(true);
        this.jCBHoraInicio.setEnabled(true);
        this.jCBStatusAcademia.setEnabled(true);
        this.jTADescricao.setEnabled(true);
        this.jTFEvento.setEnabled(true);
    }
    
    public void bloquearComponentes()
    {
        this.jCBHoraFim.setEnabled(false);
        this.jCBHoraInicio.setEnabled(false);
        this.jCBStatusAcademia.setEnabled(false);
        this.jTADescricao.setEnabled(false);
        this.jTFEvento.setEnabled(false);
        
    }
    
public void limparComponentes()
    {
        this.jCBHoraFim.setSelectedIndex(0);
        this.jCBHoraInicio.setSelectedIndex(0);
        this.jCBStatusAcademia.setSelectedIndex(0);
        this.jTADescricao.setText(null);
        this.jTFEvento.setText(null);     
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
        jCBStatusAcademia = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTFEvento = new javax.swing.JTextField();
        jCBHoraInicio = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jCBHoraFim = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADescricao = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        jPanel3.setPreferredSize(new java.awt.Dimension(600, 200));

        jCBStatusAcademia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fechada", "Aberta", "Em Balanço" }));
        jCBStatusAcademia.setEnabled(false);

        jLabel10.setText("Data do Evento");

        jLabel14.setText("Status da Academia");

        jLabel13.setText("Evento");

        jTFEvento.setEnabled(false);

        jCBHoraInicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00" }));
        jCBHoraInicio.setEnabled(false);
        jCBHoraInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBHoraInicioActionPerformed(evt);
            }
        });

        jLabel16.setText("Hora Inicio");

        jLabel17.setText("Hora Fim");

        jCBHoraFim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));
        jCBHoraFim.setEnabled(false);

        jTADescricao.setColumns(20);
        jTADescricao.setRows(5);
        jTADescricao.setEnabled(false);
        jScrollPane1.setViewportView(jTADescricao);

        jLabel1.setText("Descrição do Evento");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCBStatusAcademia, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jCBHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jCBHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(214, 214, 214))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jCBHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTFEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jCBStatusAcademia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(jCBHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBHoraInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBHoraInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBHoraInicioActionPerformed

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
            java.util.logging.Logger.getLogger(FormManterEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManterEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManterEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManterEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormManterEvento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCBHoraFim;
    private javax.swing.JComboBox jCBHoraInicio;
    private javax.swing.JComboBox jCBStatusAcademia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTADescricao;
    private javax.swing.JTextField jTFEvento;
    // End of variables declaration//GEN-END:variables
}

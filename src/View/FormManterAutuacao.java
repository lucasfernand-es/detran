/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Addons.Aviso;
import Controller.AutomovelController;
import Controller.AutuacaoController;
import Controller.CarteiraController;
import Controller.PessoaController;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import valueObject.Automovel;
import valueObject.Autuacao;
import valueObject.Carteira;
import valueObject.Pessoa;

/**
 *
 * @author LucasFernandes
 */
public final class FormManterAutuacao extends FormTemplate {
    
    private boolean editing = false;
    private int idAutomovel;
   
    private static FormManterAutuacao manterForm = null;

    public static FormManterAutuacao getForm() {
        if (manterForm == null) {
            manterForm = new FormManterAutuacao();
        }
        return manterForm;
    }
    
    /**
     * Creates new form FormManterEvento
     */
    private FormManterAutuacao() {
        this.setTitle("Gerenciar Autua��es");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);

        // Resetar todos os componentes
        bloquearComponentes();
        limparComponentes();
        
        // Toda vez que o formul�rio for mostrado, carrega o ComboBoxTitular Novamente e limpar os campos
        this.addComponentListener(new ComponentAdapter() {
            /* code run when JFrame shown */
            
            @Override
            public void componentShown(ComponentEvent e) {
                    limparComponentes();
                }
            });
        
    }

    
    public void iniciarComponentes() {
        super.setSize(714, 776);
        
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null}
                },
                new String[]{
                    "T�tulo", "Descri��o", "Pontua��o",
                    "Custo", "Prazo"
                }
        ) {
            // Quatidade de Colunas
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class,
                java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        super.jSPTable.setViewportView(jTBBuscaRapida);
        
        super.jLInstrucao.setText("Informe o t�tulo da autua��o");

        jTBBuscaRapida.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBBuscaRapidaMouseClicked(evt);
            }
        });
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        tableModel.setRowCount(0);

        jBTAlterar.setText("Alterar");
        jBTAlterar.setEnabled(false);
        //jBTAlterar.addActionListener(this::jBTAlterarActionPerformed);

        jBTSalvar.setText("Salvar");
        jBTSalvar.setEnabled(false);
        //jBTSalvar.addActionListener(this::jBTSalvarActionPerformed);

        jBTExcluir.setText("Excluir");
        jBTExcluir.setEnabled(false);
        //jBTExcluir.addActionListener(this::jBTExcluirActionPerformed);

        jBTCadastrar.setText("Cadastrar");
        //jBTCadastrar.addActionListener(this::jBTCadastrarActionPerformed);

        jBTConfirmar.setText("Confirmar");
        jBTConfirmar.setEnabled(false);
        jBTConfirmar.addActionListener((java.awt.event.ActionEvent evt) -> {
            //jBTConfirmarActionPerformed(evt);
        });

        jBTCancelar.setText("Cancelar");
        jBTCancelar.setEnabled(false);
        //jBTCancelar.addActionListener(this::jBTCancelarActionPerformed);
    }

    public void liberarComponentes() {
        jTFRenavam.setEnabled(true);
        jTFMarca.setEnabled(true);
        jTFModelo.setEnabled(true);
        jTFCor.setEnabled(true);
        jTFPlaca.setEnabled(true);
        jTFChassi.setEnabled(true);
        jTFAno.setEnabled(true);
        jCBProprietario.setEnabled(true);
        super.getjTFBusca().setEnabled(false);
        super.getjTBBuscaRapida().setEnabled(false);
        editing = true;
    }

    public void bloquearComponentes() {
        jTFRenavam.setEnabled(false);
        jTFMarca.setEnabled(false);
        jTFModelo.setEnabled(false);
        jTFCor.setEnabled(false);
        jTFPlaca.setEnabled(false);
        jTFChassi.setEnabled(false);
        jTFAno.setEnabled(false);
        jCBProprietario.setEnabled(false);
        super.getjTFBusca().setEnabled(true);
        super.getjTBBuscaRapida().setEnabled(true);
        editing = false;
    }
    public void preencheComponentes(Automovel automovel) {
        jTFRenavam.setText(automovel.getRenavam());
        jTFMarca.setText(automovel.getMarca());
        jTFModelo.setText(automovel.getModelo());
        jTFCor.setText(automovel.getCor());
        jTFPlaca.setText(automovel.getPlaca());
        jTFChassi.setText(automovel.getChassi());
        jTFAno.setText(automovel.getAno());
        for(int i=0; i<jCBProprietario.getItemCount(); i++) {
            if(((Pessoa)jCBProprietario.getItemAt(i)).getCpf().equals( automovel.getProprietario().getCpf() )) {
                jCBProprietario.setSelectedIndex(i);
                break;
            }
        }
    }

    // Define valores nulos para todos os componentes

    public void limparComponentes() {
        jTFRenavam.setText("");
        jTFMarca.setText("");
        jTFModelo.setText("");
        jTFCor.setText("");
        jTFPlaca.setText("");
        jTFChassi.setText("");
        jTFAno.setText("");
    }
    
    @Override
    protected void jTFBuscaKeyReleased(java.awt.event.KeyEvent evt) {                                    
        // TODO add your handling code here:
        super.jTFBuscaKeyReleased(evt);
        
        ArrayList<Autuacao> autuacaoList;
        
        Autuacao autuacao = new Autuacao();
        autuacao.setTitulo( jTFBusca.getText() );
        
        autuacaoList = AutuacaoController.buscarAutuacao(autuacao, "TITULO");
        
        if(autuacao.isError())
        {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    autuacao.getMessage());
        }
        else if (autuacaoList == null)
            return;
        
        preenchePesquisa(autuacaoList);
    } 
    
    private void preenchePesquisa(ArrayList<Autuacao> autuacaoList) {
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        
        tableModel.setRowCount(0);
        
        for(Autuacao autuacao : autuacaoList ) {
            tableModel.addRow(new Object[] { 
                autuacao.getTitulo(),
                autuacao.getDescricao(),
                autuacao.getPontuacao(),
                autuacao.getCusto(),
                autuacao.getPrazo()
            });
        }
    }

    @Override
    protected void jBTCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        super.jBTCadastrarActionPerformed(evt);

        liberarComponentes();
    }

    @Override
    protected void jBTConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        String renavam = jTFRenavam.getText();
        String marca = jTFMarca.getText();
        String modelo = jTFModelo.getText();
        String cor = jTFCor.getText();
        String placa = jTFPlaca.getText();
        String chassi = jTFChassi.getText();
        String ano = jTFAno.getText();
        Pessoa auxProprietario = (Pessoa) jCBProprietario.getSelectedItem();
        
        Automovel automovel =  new Automovel (
                renavam, marca, modelo, cor,
                placa, chassi, auxProprietario, ano, 
                true, -1
        );
        // Nenhum erro at� o momento
        automovel.setError(false);
        automovel.setMessage("");

        //System.out.println(carteira.showCarteira());
        AutomovelController.cadastrarAutomovel(automovel);
        
        if(automovel.isError()){
                Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                        automovel.getMessage());
            }
        else {
                Aviso.showInformation(automovel.getMessage());
                super.jBTConfirmarActionPerformed(evt);
                bloquearComponentes();
                limparComponentes();
        }
    }
    @Override
    protected void jBTAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        super.jBTAlterarActionPerformed(evt);

        liberarComponentes();
        
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        String renavam = (String) tableModel.getValueAt(super.jTBBuscaRapida.getSelectedRow(), 1);
        Automovel automovel = new Automovel();
        automovel.setRenavam(renavam);
        automovel = AutomovelController.buscarAutomovel(automovel, "RENAVAM").get(0);
        
        preencheComponentes(automovel);
        idAutomovel = automovel.getIdAutomovel();
    }

    @Override
    protected void jBTSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String renavam = jTFRenavam.getText();
        String marca = jTFMarca.getText();
        String modelo = jTFModelo.getText();
        String cor = jTFCor.getText();
        String placa = jTFPlaca.getText();
        String chassi = jTFChassi.getText();
        String ano = jTFAno.getText();
        Pessoa auxProprietario = (Pessoa) jCBProprietario.getSelectedItem();
        
        Automovel automovel =  new Automovel (
                renavam, marca, modelo, cor,
                placa, chassi, auxProprietario, ano, 
                true, this.idAutomovel
        );
        // Nenhum erro at� o momento
        automovel.setError(false);
        automovel.setMessage("");

        AutomovelController.alterarAutomovel(automovel);
        
        if(automovel.isError()){
                Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                        automovel.getMessage());
            }
        else {
                Aviso.showInformation(automovel.getMessage());
                super.jBTSalvarActionPerformed(evt);
                bloquearComponentes();
                limparComponentes();
                jTFBuscaKeyReleased(null);
        }
    }

    @Override
    protected void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if(JOptionPane.showConfirmDialog(
                null, 
                "Voc� realmente deseja excluir estes dados?",
                "Alerta de exclus�o de dados",
                JOptionPane.YES_NO_OPTION) == 1) 
            return;
        
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        String renavam = (String) tableModel.getValueAt(super.jTBBuscaRapida.getSelectedRow(), 1);
        Automovel automovel = new Automovel();
        automovel.setRenavam(renavam);
        automovel = AutomovelController.buscarAutomovel(automovel, "RENAVAM").get(0);
        
        AutomovelController.excluirAutomovel(automovel);
        
        if(automovel.isError()){
                Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                        automovel.getMessage());
            }
        else {
                Aviso.showInformation(automovel.getMessage());
                super.jBTSalvarActionPerformed(evt);
                bloquearComponentes();
                limparComponentes();
                jTFBuscaKeyReleased(null);
        }
    }

    @Override
    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {
        if(editing) return;
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

        bGStatus = new javax.swing.ButtonGroup();
        jPManter = new javax.swing.JPanel();
        jPDados = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTFMarca = new javax.swing.JTextField();
        jTFModelo = new javax.swing.JTextField();
        jTFCor = new javax.swing.JTextField();
        jTFChassi = new javax.swing.JTextField();
        jTFAno = new javax.swing.JTextField();
        jCBProprietario = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTFPlaca = new javax.swing.JFormattedTextField();
        jTFRenavam = new javax.swing.JFormattedTextField();

        setSize(new java.awt.Dimension(650, 185));

        jPManter.setPreferredSize(new java.awt.Dimension(650, 200));

        jPDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Ve�culo"));
        jPDados.setRequestFocusEnabled(false);

        jLabel3.setText("Renavam");

        jTFMarca.setBackground(new java.awt.Color(240, 240, 240));

        jTFModelo.setBackground(new java.awt.Color(240, 240, 240));

        jTFCor.setBackground(new java.awt.Color(240, 240, 240));

        jTFChassi.setBackground(new java.awt.Color(240, 240, 240));

        jTFAno.setBackground(new java.awt.Color(240, 240, 240));

        jCBProprietario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Titular 1", "Titular 2", "Titular 3" }));
        jCBProprietario.setEnabled(false);

        jLabel8.setText("Marca");

        jLabel9.setText("Modelo");

        jLabel10.setText("Cor");

        jLabel11.setText("Placa");

        jLabel12.setText("Chassi");

        jLabel13.setText("Ano");

        jLabel14.setText("Propriet�rio");

        jTFPlaca.setBackground(new java.awt.Color(240, 240, 240));
        try {
            jTFPlaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFPlaca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFPlacaFocusGained(evt);
            }
        });

        jTFRenavam.setBackground(new java.awt.Color(240, 240, 240));
        try {
            jTFRenavam.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFRenavam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFRenavamFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPDadosLayout = new javax.swing.GroupLayout(jPDados);
        jPDados.setLayout(jPDadosLayout);
        jPDadosLayout.setHorizontalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFModelo)
                            .addComponent(jCBProprietario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDadosLayout.createSequentialGroup()
                                .addComponent(jTFRenavam, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addGap(9, 9, 9)
                                .addComponent(jTFAno, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTFMarca)
                            .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTFChassi, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addComponent(jTFCor))
                            .addComponent(jTFPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPDadosLayout.setVerticalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTFRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTFPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBProprietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPManterLayout = new javax.swing.GroupLayout(jPManter);
        jPManter.setLayout(jPManterLayout);
        jPManterLayout.setHorizontalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterLayout.createSequentialGroup()
                .addComponent(jPDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPManterLayout.setVerticalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPManter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFPlacaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFPlacaFocusGained
        this.jTFPlaca.setText(null);
    }//GEN-LAST:event_jTFPlacaFocusGained

    private void jTFRenavamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFRenavamFocusGained
        this.jTFRenavam.setText(null);
    }//GEN-LAST:event_jTFRenavamFocusGained

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
            java.util.logging.Logger.getLogger(FormManterAutuacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManterAutuacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManterAutuacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManterAutuacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormManterAutuacao().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGStatus;
    private javax.swing.JComboBox jCBProprietario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPDados;
    private javax.swing.JPanel jPManter;
    private javax.swing.JTextField jTFAno;
    private javax.swing.JTextField jTFChassi;
    private javax.swing.JTextField jTFCor;
    private javax.swing.JTextField jTFMarca;
    private javax.swing.JTextField jTFModelo;
    private javax.swing.JFormattedTextField jTFPlaca;
    private javax.swing.JFormattedTextField jTFRenavam;
    // End of variables declaration//GEN-END:variables

}
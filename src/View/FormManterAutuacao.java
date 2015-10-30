/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Addons.Aviso;
import Controller.AutomovelController;
import Controller.AutuacaoController;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import valueObject.Automovel;
import valueObject.Autuacao;

/**
 *
 * @author LucasFernandes
 */
public final class FormManterAutuacao extends FormTemplate {

    private boolean editing = false;
    private int idAutuacao;

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
        this.setTitle("Gerenciar Autuações");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);

        // Resetar todos os componentes
        bloquearComponentes();
        limparComponentes();

        // Toda vez que o formulário for mostrado, carrega o ComboBoxTitular Novamente e limpar os campos
        this.addComponentListener(new ComponentAdapter() {
            /* code run when JFrame shown */

            @Override
            public void componentShown(ComponentEvent e) {
                limparComponentes();
            }
        });
        
        jTFBuscaKeyReleased(null);
    }

    public void iniciarComponentes() {
        super.setSize(714, 776);

        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null}
                },
                new String[]{
                    "Id", "Título", "Descrição", 
                    "Pontuação", "Custo", "Prazo"
                }
        ) {
            // Quatidade de Colunas
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class,
                java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
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

        super.jLInstrucao.setText("Informe o título da autuação");
        
        jTFBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBuscaKeyReleased(evt);
            }
        });

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
        jBTAlterar.addActionListener(this::jBTAlterarActionPerformed);

        jBTSalvar.setText("Salvar");
        jBTSalvar.setEnabled(false);
        jBTSalvar.addActionListener(this::jBTSalvarActionPerformed);

        jBTExcluir.setText("Excluir");
        jBTExcluir.setEnabled(false);
        jBTExcluir.addActionListener(this::jBTExcluirActionPerformed);

        jBTCadastrar.setText("Cadastrar");
        jBTCadastrar.addActionListener(this::jBTCadastrarActionPerformed);

        jBTConfirmar.setText("Confirmar");
        jBTConfirmar.setEnabled(false);
        jBTConfirmar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jBTConfirmarActionPerformed(evt);
        });

        jBTCancelar.setText("Cancelar");
        jBTCancelar.setEnabled(false);
        jBTCancelar.addActionListener(this::jBTCancelarActionPerformed);
    }

    public void liberarComponentes() {
        jTextFieldTitulo.setEnabled(true);
        jTextAreaDescricao.setEnabled(true);
        jTextFieldCusto.setEnabled(true);
        jSpinnerPrazo.setEnabled(true);
        jSpinnerPontuacao.setEnabled(true);
        super.getjTFBusca().setEnabled(false);
        super.getjTBBuscaRapida().setEnabled(false);
        editing = true;
    }

    public void bloquearComponentes() {
        jTextFieldTitulo.setEnabled(false);
        jTextAreaDescricao.setEnabled(false);
        jTextFieldCusto.setEnabled(false);
        jSpinnerPrazo.setEnabled(false);
        jSpinnerPontuacao.setEnabled(false);
        super.getjTFBusca().setEnabled(true);
        super.getjTBBuscaRapida().setEnabled(true);
        editing = false;
    }

    public void preencheComponentes(Autuacao autuacao) {
        jTextFieldTitulo.setText(autuacao.getTitulo());
        jTextAreaDescricao.setText(autuacao.getDescricao());
        jTextFieldCusto.setText(String.valueOf(autuacao.getCusto()));
        jSpinnerPrazo.setValue(autuacao.getPrazo());
        jSpinnerPontuacao.setValue(autuacao.getPontuacao());
    }

    // Define valores nulos para todos os componentes
    public void limparComponentes() {
        jTextFieldTitulo.setText("");
        jTextAreaDescricao.setText("");
        jTextFieldCusto.setText("");
        jSpinnerPrazo.setValue(1);
        jSpinnerPontuacao.setValue(1);
    }

    @Override
    protected void jTFBuscaKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        super.jTFBuscaKeyReleased(evt);

        ArrayList<Autuacao> autuacaoList;

        Autuacao autuacao = new Autuacao();
        autuacao.setTitulo(jTFBusca.getText().toUpperCase());

        autuacaoList = AutuacaoController.buscarAutuacao(autuacao, "TITULO");

        if (autuacao.isError()) {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n"
                    + autuacao.getMessage());
        } else if (autuacaoList == null) {
            return;
        }

        preenchePesquisa(autuacaoList);
    }

    private void preenchePesquisa(ArrayList<Autuacao> autuacaoList) {
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();

        tableModel.setRowCount(0);

        for (Autuacao autuacao : autuacaoList) {
            tableModel.addRow(new Object[]{
                autuacao.getIdAutuacao(),
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
        String titulo = jTextFieldTitulo.getText();
        String descricao = jTextAreaDescricao.getText();
        String custo = jTextFieldCusto.getText();
        int prazo = (int) jSpinnerPrazo.getValue();
        int pontuacao = (int) jSpinnerPontuacao.getValue();

        Autuacao autuacao = new Autuacao(
                titulo, descricao, pontuacao,
                0.0, prazo, -1
        );
        autuacao.setCustoStr(custo);
        
        // Nenhum erro até o momento
        autuacao.setError(false);
        autuacao.setMessage("");

        AutuacaoController.cadastrarAutuacao(autuacao);

        if (autuacao.isError()) {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n"
                    + autuacao.getMessage());
        } else {
            Aviso.showInformation(autuacao.getMessage());
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
        this.idAutuacao = (int) tableModel.getValueAt(super.jTBBuscaRapida.getSelectedRow(), 0);
        Autuacao autuacao = new Autuacao();
        autuacao.setIdAutuacao(this.idAutuacao);
        autuacao = AutuacaoController.buscarAutuacao(autuacao, "ID").get(0);

        preencheComponentes(autuacao);
    }

    @Override
    protected void jBTSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String titulo = jTextFieldTitulo.getText();
        String descricao = jTextAreaDescricao.getText();
        String custo = jTextFieldCusto.getText();
        int prazo = (int) jSpinnerPrazo.getValue();
        int pontuacao = (int) jSpinnerPontuacao.getValue();

        Autuacao autuacao = new Autuacao(
                titulo, descricao, pontuacao,
                0.0, prazo, idAutuacao
        );
        autuacao.setCustoStr(custo);
        // Nenhum erro até o momento
        autuacao.setError(false);
        autuacao.setMessage("");

        AutuacaoController.alterarAutuacao(autuacao);

        if (autuacao.isError()) {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n"
                    + autuacao.getMessage());
        } else {
            Aviso.showInformation(autuacao.getMessage());
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
                "Você realmente deseja excluir estes dados?",
                "Alerta de exclusão de dados",
                JOptionPane.YES_NO_OPTION) == 1) 
            return;
        
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        this.idAutuacao = (int) tableModel.getValueAt(super.jTBBuscaRapida.getSelectedRow(), 0);
        Autuacao autuacao = new Autuacao();
        autuacao.setIdAutuacao(this.idAutuacao);
        
        AutuacaoController.excluirAutuacao(autuacao);
        
        if(autuacao.isError()){
                Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                        autuacao.getMessage());
            }
        else {
                Aviso.showInformation(autuacao.getMessage());
                super.jBTSalvarActionPerformed(evt);
                bloquearComponentes();
                limparComponentes();
                jTFBuscaKeyReleased(null);
        }
    }

    @Override
    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {
        if (editing) {
            return;
        }
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
        jTextFieldCusto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldTitulo = new javax.swing.JTextField();
        jSpinnerPontuacao = new javax.swing.JSpinner();
        jSpinnerPrazo = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();

        setSize(new java.awt.Dimension(650, 185));

        jPManter.setPreferredSize(new java.awt.Dimension(650, 200));

        jPDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Autuação"));
        jPDados.setRequestFocusEnabled(false);

        jLabel3.setText("Título");

        jTextFieldCusto.setBackground(new java.awt.Color(240, 240, 240));

        jLabel8.setText("Descrição");

        jLabel9.setText("Pontos");

        jLabel10.setText("Custo");

        jLabel11.setText("Prazo");

        jTextFieldTitulo.setBackground(new java.awt.Color(240, 240, 240));

        jTextAreaDescricao.setBackground(new java.awt.Color(240, 240, 240));
        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescricao);

        javax.swing.GroupLayout jPDadosLayout = new javax.swing.GroupLayout(jPDados);
        jPDados.setLayout(jPDadosLayout);
        jPDadosLayout.setHorizontalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(25, 25, 25)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTitulo)
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSpinnerPontuacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addComponent(jSpinnerPrazo, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPDadosLayout.setVerticalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jSpinnerPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerPontuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
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
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPManter, java.awt.BorderLayout.CENTER);

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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPDados;
    private javax.swing.JPanel jPManter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerPontuacao;
    private javax.swing.JSpinner jSpinnerPrazo;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldCusto;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Addons.Aviso;
import Controller.CarteiraController;
import Controller.MultaController;
import Controller.PessoaController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import valueObject.Carteira;
import valueObject.Multa;
import valueObject.Pessoa;

/**
 *
 * @author LucasFernandes
 */
public final class FormVisualizarCarteira extends FormTemplate {
   
    private ArrayList<Carteira> carteiraList = new ArrayList<>();
    private Carteira carteiraSelected = null;
   
    private static FormVisualizarCarteira manterForm = null;

    public static FormVisualizarCarteira getForm() {
        if (manterForm == null) {
            manterForm = new FormVisualizarCarteira();
        }
        return manterForm;
    }
    
    /**
     * Creates new form FormManterEvento
     */
    private FormVisualizarCarteira() {
        this.setTitle("Buscar Carteira");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);

        // Resetar todos os componentes
        this.setAllEnabled(false);
        limparComponentes();
    }

    
    public void iniciarComponentes() {

        
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null}
                },
               new String[]{
                    "N. Registro", "Titular", "Data de Emissão",
                    "Data de Vencimento", "Tipo"
                }
        ) {
            // Quatidade de Colunas
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class
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

        jTBBuscaRapida.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBBuscaRapidaMouseClicked(evt);
            }
        });
        
        jTFBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBuscaKeyReleased(evt);
            }
        });
        
        super.jPBotoes.setVisible(false);
        super.jPBotoes.setEnabled(false);
        super.jPBotoes.setSize(0, 0);
        
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        tableModel.setRowCount(0);
        
        setAllEditable(false);
    }
    
    private void setAllEditable(boolean opt) {
        jTFNRegistro.setEditable(opt);
        
        jTFDataEmissao.setEditable(opt);
        jTFDataVencimento.setEditable(opt);
        
        jTFPermissao.setEditable(opt);
        jTFAtivo.setEditable(opt);
        
        jTFCategoria.setEditable(opt);
        jTFTitular.setEditable(opt);
    }
    

    private void setAllEnabled(boolean opt) {
        jTFNRegistro.setEnabled(opt);
        
        jTFDataEmissao.setEnabled(opt);
        jTFDataVencimento.setEnabled(opt);
        
        jTFPermissao.setEnabled(opt);
        jTFAtivo.setEnabled(opt);
        
        jTFCategoria.setEnabled(opt);
        jTFTitular.setEnabled(opt);
    }

    // Define valores nulos para todos os componentes

    public void limparComponentes() { 
        
        jLVencida.setText("");
        
        jTFNRegistro.setText("");
        
        jTFDataEmissao.setText("");
        jTFDataVencimento.setText("");
        
        jTFPermissao.setText("");
        jTFAtivo.setText("");
        
        jTFCategoria.setText("");
        jTFTitular.setText("");
        
        jLPontuacao.setText("");
        
    }
    
    
    @Override
    protected void jTFBuscaKeyReleased(java.awt.event.KeyEvent evt) {                                    
        // TODO add your handling code here:
        super.jTFBuscaKeyReleased(evt);
        
        String busca = jTFBusca.getText();
        
        Carteira carteira = new Carteira();
        carteira.setnRegistro( busca );
        
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(busca);
        boolean cpfValido  = PessoaController.mascaraCPF(pessoa);
        // Se o que foi digitado na pesquisa não for válido, não é necessário fazer a busca
        if(!cpfValido)
            pessoa.setCpf("");
        //System.out.println("MASK: " + pessoa.getCpf());
        
        carteira.setTitular(pessoa);
        
        
        carteiraList.clear();
        carteiraList = CarteiraController.buscarCarteira(carteira, "REGISTRO_CPF");
        
            
        if(carteira.isError())
        {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    carteira.getMessage());
        }
        
        if (carteiraList == null)
        {
            carteiraList = new ArrayList<>();
            return;
        }
        preencherPesquisa();
    } 
    
    private void preencherPesquisa( ) { 
                    
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
    
        tableModel.setRowCount(0);
        
        for(Carteira carteira : carteiraList ) {
            tableModel.addRow(new Object[] { 
                    carteira.getnRegistro(), carteira.getTitular(), carteira.getDataEmissao(),
                    carteira.getDataVencimento(), carteira.getTipo()
            } );
        }
        
        
        limparComponentes();
        setAllEnabled(false);
    }

    @Override
    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {
        super.jTBBuscaRapidaMouseClicked(evt);
        //System.out.println("carregar coisinhas"); // oi
        // Não executar sem dados
        if (carteiraList.isEmpty())
            return;
        
        int selectedRow = super.jTBBuscaRapida.getSelectedRow();
        carteiraSelected = carteiraList.get(selectedRow);
        
        //System.out.println(pessoaSelected.showPessoa());
        preencheComponentes(carteiraSelected);
    }
    
    public void preencheComponentes(Carteira carteira) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        
        jTFNRegistro.setText( carteira.getnRegistro() );
        
        jTFDataEmissao.setText( df.format( carteira.getDataEmissao() ) );
        jTFDataVencimento.setText( df.format( carteira.getDataVencimento() ) );
        
        jTFPermissao.setText( (carteira.isPermissao())? "Sim": "Não" );
        jTFAtivo.setText( (carteira.isStatus())? "Sim": "Não" );
        
        jTFCategoria.setText( carteira.getTipo() );
        jTFTitular.setText( String.valueOf( carteira.getTitular() ) );
        
        Date hoje = new Date();
                
        jLVencida.setText( ( carteira.getDataVencimento().before( hoje )) ? "Vencida" : "Dentro do Prazo de Validade" );
        
        
        Multa multa = new Multa();
        multa.setCarteira(carteira);
        ArrayList<Multa> multaList = MultaController.buscarMulta(multa, "CARTEIRA_1YEAR");
        

        if(multa.isError())
        {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    multa.getMessage());
        }
        if (multaList == null)
        {
            multaList = new ArrayList<>();
        }
        
        int totalPontos = 0;
        for(Multa item: multaList){
            totalPontos += item.getAutuacao().getPontuacao();
            //System.out.println(item);
        }
        
        if (totalPontos > 0) {
            if(totalPontos == 1)
                jLPontuacao.setText("1 Ponto");
            else
               jLPontuacao.setText(totalPontos + " Pontos");
        }
        else
            jLPontuacao.setText("Nenhum Ponto");
        
                
        setAllEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPManter = new javax.swing.JPanel();
        jPDados = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFNRegistro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTFDataVencimento = new javax.swing.JTextField();
        jTFPermissao = new javax.swing.JTextField();
        jTFAtivo = new javax.swing.JTextField();
        jTFDataEmissao = new javax.swing.JTextField();
        jTFCategoria = new javax.swing.JTextField();
        jTFTitular = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLVencida = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLPontuacao = new javax.swing.JLabel();

        setSize(new java.awt.Dimension(650, 185));

        jPManter.setPreferredSize(new java.awt.Dimension(650, 200));

        jPDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Carteira"));
        jPDados.setRequestFocusEnabled(false);

        jLabel1.setText("Data de Emissão");

        jLabel2.setText("Validade");

        jLabel3.setText("Número de Registro");

        jTFNRegistro.setEnabled(false);
        jTFNRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNRegistroActionPerformed(evt);
            }
        });

        jLabel4.setText("Ativo");

        jLabel5.setText("Permissão");

        jLabel6.setText("Categoria");

        jLabel7.setText("Titular");

        jTFPermissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFPermissaoActionPerformed(evt);
            }
        });

        jTFAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFAtivoActionPerformed(evt);
            }
        });

        jLabel8.setText("Status da Carteira:");

        jLabel9.setText("Pontuação no Último Ano:");

        javax.swing.GroupLayout jPDadosLayout = new javax.swing.GroupLayout(jPDados);
        jPDados.setLayout(jPDadosLayout);
        jPDadosLayout.setHorizontalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLVencida, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                            .addComponent(jLPontuacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPDadosLayout.createSequentialGroup()
                                        .addComponent(jTFCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPDadosLayout.createSequentialGroup()
                                        .addComponent(jTFPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTFDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTFTitular)))
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPDadosLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTFAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPDadosLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTFNRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPDadosLayout.setVerticalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFNRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTFDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jTFAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTFDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTFTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLVencida, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLPontuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPManterLayout = new javax.swing.GroupLayout(jPManter);
        jPManter.setLayout(jPManterLayout);
        jPManterLayout.setHorizontalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jTFNRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNRegistroActionPerformed

    private void jTFPermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFPermissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFPermissaoActionPerformed

    private void jTFAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFAtivoActionPerformed

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
            java.util.logging.Logger.getLogger(FormVisualizarCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormVisualizarCarteira().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLPontuacao;
    private javax.swing.JLabel jLVencida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPDados;
    private javax.swing.JPanel jPManter;
    private javax.swing.JTextField jTFAtivo;
    private javax.swing.JTextField jTFCategoria;
    private javax.swing.JTextField jTFDataEmissao;
    private javax.swing.JTextField jTFDataVencimento;
    private javax.swing.JTextField jTFNRegistro;
    private javax.swing.JTextField jTFPermissao;
    private javax.swing.JTextField jTFTitular;
    // End of variables declaration//GEN-END:variables

}

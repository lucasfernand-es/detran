/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Addons.Aviso;
import Controller.CarteiraController;
import Controller.PessoaController;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import valueObject.Carteira;
import valueObject.Pessoa;

/**
 *
 * @author LucasFernandes
 */
public final class FormManterMulta extends FormTemplate {
    
    
    private ArrayList <Pessoa> pessoaList = new ArrayList<>();
   
    private static FormManterMulta manterForm = null;
    
    

    public static FormManterMulta getForm() {
        if (manterForm == null) {
            manterForm = new FormManterMulta();
        }
        else {
            
        }

        return manterForm;
    }
    
    /**
     * Creates new form FormManterEvento
     */
    private FormManterMulta() {
        this.setTitle("Gerenciar Carteira");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);

        // Resetar todos os componentes
        bloquearComponentes();
        limparComponentes();
        
        iniciarComboBoxPessoa();
        
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFBuscaKeyTyped(evt);
            }
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFBuscaKeyPressed(evt);
            }
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBuscaKeyReleased(evt);
            }
        });

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
    
    //  Carregar Pessoas do BD
    //  Será carregado somente quando a Janela for visível
    private void iniciarComboBoxPessoa() {
        jCBPessoa.removeAllItems();
        // Carrega todas as pessoas do BD, que são ativos. (Status == true)
        Pessoa pessoa = new Pessoa();
        pessoa.setStatus(true);
        
        if (pessoaList == null) {
            pessoaList = new ArrayList();
        }
        pessoaList.clear();
        // Busca no Banco de Dados TODOS os possíveis titulares
        pessoaList = PessoaController.buscarPessoa(pessoa, "STATUS");
        
        // Caso algum erro tenha acontecido
        if(pessoa.isError()){
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    pessoa.getMessage());
            return;
        }
        
        // Caso a carteira tenha sido recém criada, devemos por como seleção default
        // no jCBPessoa, caso contrário será 0
        
        
        for (Pessoa pessoaItem : pessoaList) {
            jCBPessoa.addItem(pessoaItem);
        }
        
        
    }

    public void liberarComponentes() {
        jDCDataPagamento.setEnabled(true);
        jDCDataEmissao.setEnabled(true);
        jFTFTaxa.setEnabled(true);
        
        jCBAutomovel.setEnabled(true);
        jCBAutuacao.setEnabled(true);
        jCBPessoa.setEnabled(true);
        jCBCarteira.setEnabled(true);
        
        jRBPessoa.setEnabled(true);
        jRBCarteira.setEnabled(true);
    }

    public void bloquearComponentes() {
        jDCDataPagamento.setEnabled(false);
        jDCDataEmissao.setEnabled(false);
        jFTFTaxa.setEnabled(false);
        
        jCBAutomovel.setEnabled(false);
        jCBAutuacao.setEnabled(false);
        jCBPessoa.setEnabled(false);
        jCBCarteira.setEnabled(false);
        
        jRBPessoa.setEnabled(false);
        jRBCarteira.setEnabled(false);
    }

    // Define valores nulos para todos os componentes

    public void limparComponentes() {
        
        jDCDataPagamento.setDate(null);
        jDCDataEmissao.setDate(null);
        jFTFTaxa.setText("");
        
        jCBAutomovel.setSelectedIndex(0);
        jCBAutuacao.setSelectedIndex(0);
        jCBPessoa.setSelectedIndex(0);
        jCBCarteira.setSelectedIndex(0);
        
        jRBCarteira.setSelected(true);
    }

    @Override
    protected void jBTCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        super.jBTCadastrarActionPerformed(evt);

        liberarComponentes();
    }

    @Override
    protected void jBTConfirmarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
        /*
        
        Date dataVencimento = jDCDataPagamento.getDate();
	Date dataEmissao = jDCDataEmissao.getDate();
	String nRegistro = jTFNRegistro.getText();
        // Se a permissão existe. Sim == true e Não == false 
        // Se o Sim não estiver selecionado, o Não está
	boolean permissao = (jRBSimPermissao.isSelected());
	String tipo = (String) jCBTipo.getSelectedItem();
	Pessoa pessoaTitular = (Pessoa) jCBPessoa.getSelectedItem();
        // Se o status for Ativo. Sim == true e Não == false 
        // Se o Sim não estiver selecionado, o Não está
        boolean status = (jRBSimStatus.isSelected());
        // ID deste objeto no banco de dados
        int idCarteira = -1;
        
        Carteira carteira =  new Carteira(dataVencimento, dataEmissao, nRegistro, 
            permissao, tipo, pessoaTitular, status, idCarteira);
        // Nenhum erro até o momento
        carteira.setError(false);
        carteira.setMessage("");

        //System.out.println(carteira.showCarteira());
        CarteiraController.cadastrarCarteira(carteira);
        
        if(carteira.isError()){
                Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                        carteira.getMessage());
            }
        else {
                Aviso.showInformation(carteira.getMessage());
                super.jBTConfirmarActionPerformed(evt);
                bloquearComponentes();
                limparComponentes();
        }
        
        */
    } 
    @Override
    protected void jBTAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        super.jBTAlterarActionPerformed(evt);

        liberarComponentes();
        /*
        jTFNRegistro.setEnabled(false);
        jDCDataEmissao.setEnabled(false);
        jDCDataPagamento.setEnabled(false);
        jCBTipo.setEnabled(false);
        jCBPessoa.setEnabled(false);
        */
    }

    @Override
    protected void jBTSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        /*
        Date dataVencimento = jDCDataPagamento.getDate();
	Date dataEmissao = jDCDataEmissao.getDate();
	String nRegistro = jTFNRegistro.getText();
        // Se a permissão existe. Sim == true e Não == false 
        // Se o Sim não estiver selecionado, o Não está
	boolean permissao = (jRBSimPermissao.isSelected());
	String tipo = (String) jCBTipo.getSelectedItem();
	Pessoa pessoaTitular = (Pessoa) jCBPessoa.getSelectedItem();
        // Se o status for Ativo. Sim == true e Não == false 
        // Se o Sim não estiver selecionado, o Não está
        boolean status = (jRBSimStatus.isSelected());
        // ID deste objeto no banco de dados
        int idCarteira = carteiraSelected.getIdCarteira();
        
        Carteira carteira =  new Carteira(dataVencimento, dataEmissao, nRegistro, 
            permissao, tipo, pessoaTitular, status, idCarteira);
        // Nenhum erro até o momento
        carteira.setError(false);
        carteira.setMessage("");

        //System.out.println(carteira.showCarteira());
        CarteiraController.alterarCarteira(carteira);
        
        if(carteira.isError()){
                Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                        carteira.getMessage());
            }
        else {
                Aviso.showInformation(carteira.getMessage());
                super.jBTConfirmarActionPerformed(evt);
                bloquearComponentes();
                limparComponentes();
        }
        */
    }

    @Override
    protected void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if(JOptionPane.showConfirmDialog(
                null, 
                "Você realmente deseja excluir estes dados?",
                "Alerta de exclusão de dados",
                JOptionPane.YES_NO_OPTION) == 1) 
            return;
        
        Carteira carteira = carteiraSelected;

        //System.out.println(carteira.showPessoa());
        CarteiraController.excluirCarteira(carteira);

        if(carteira.isError())
        {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    carteira.getMessage());
        }
        
        bloquearComponentes();
        limparComponentes();
    }


    @Override
    protected void jBTCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        super.jBTCancelarActionPerformed(evt);

        bloquearComponentes();
        limparComponentes();
    }
    
    @Override
    protected void jTFBuscaKeyReleased(java.awt.event.KeyEvent evt) {                                    
        // TODO add your handling code here:
        super.jTFBuscaKeyReleased(evt);
        
       // System.out.println(jTFBusca.getText());
        
        
        
        Carteira carteira = new Carteira();
        carteira.setnRegistro( jTFBusca.getText() );
        //System.out.println(jTFBusca.getText());
        carteiraList.clear();
        carteiraList = CarteiraController.buscarCarteira(carteira, "REGISTRO");
        
            
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
            
        
        //testPessoa(pessoaList);
        preencherPesquisa(carteiraList);
    } 
    
    private void preencherPesquisa( ArrayList<Carteira> carteiraList) { 
                    
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
    
        tableModel.setRowCount(0);
        
        for(Carteira carteira : carteiraList ) {
            tableModel.addRow(new Object[] { 
                    carteira.getnRegistro(), carteira.getTitular(), carteira.getDataEmissao(),
                    carteira.getDataVencimento(), carteira.getTipo()
            } );
        }
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
        
        bloquearComponentes();
    }
    
    public void preencheComponentes(Carteira carteira) {
        
        
       /*
         jTFNRegistro.setText(carteira.getnRegistro());
        jDCDataEmissao.setDate(carteira.getDataEmissao());
        // Por definição
        if (carteira.isPermissao())
            jRBSimPermissao.setSelected(true);
        else
            jRBNaoPermissao.setSelected(true);
        jDCDataPagamento.setDate(carteira.getDataVencimento());
        // Por definição
        if (carteira.isStatus())
            jRBSimStatus.setSelected(true);
        else
            jRBNaoStatus.setSelected(true);
        
        jCBPessoa.setSelectedItem( carteira.getTipo());
        
        int idCarteira = CarteiraController.buscaIDCarteira(titularList, carteira);

        jCBPessoa.setSelectedIndex(idCarteira);
        */
       
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
        bgPermissao = new javax.swing.ButtonGroup();
        bgResponsavel = new javax.swing.ButtonGroup();
        jPManter = new javax.swing.JPanel();
        jPDados = new javax.swing.JPanel();
        jDCDataEmissao = new com.toedter.calendar.JDateChooser();
        jDCDataPagamento = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCBPessoa = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCBAutomovel = new javax.swing.JComboBox();
        jCBAutuacao = new javax.swing.JComboBox();
        jRBPessoa = new javax.swing.JRadioButton();
        jRBCarteira = new javax.swing.JRadioButton();
        jCBCarteira = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jFTFTaxa = new javax.swing.JFormattedTextField();

        setSize(new java.awt.Dimension(650, 185));

        jPManter.setPreferredSize(new java.awt.Dimension(650, 220));

        jPDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Multa"));
        jPDados.setRequestFocusEnabled(false);

        jDCDataEmissao.setDateFormatString("d MMM , yyyy");
        jDCDataEmissao.setEnabled(false);

        jDCDataPagamento.setDateFormatString("d MMM , yyyy");
        jDCDataPagamento.setEnabled(false);

        jLabel1.setText("Data de Emissão");

        jLabel2.setText("Data de Pagamento");

        jCBPessoa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Titular 1", "Titular 2", "Titular 3" }));
        jCBPessoa.setEnabled(false);

        jLabel9.setText("Automóvel");

        jLabel10.setText("Autuação");

        jCBAutomovel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCBAutuacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bgResponsavel.add(jRBPessoa);
        jRBPessoa.setText("Pessoa");
        jRBPessoa.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRBPessoaStateChanged(evt);
            }
        });
        jRBPessoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBPessoaMouseClicked(evt);
            }
        });
        jRBPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPessoaActionPerformed(evt);
            }
        });

        bgResponsavel.add(jRBCarteira);
        jRBCarteira.setSelected(true);
        jRBCarteira.setText("Carteira");
        jRBCarteira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBCarteiraActionPerformed(evt);
            }
        });

        jCBCarteira.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Taxa de Acréscimo");

        jFTFTaxa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        javax.swing.GroupLayout jPDadosLayout = new javax.swing.GroupLayout(jPDados);
        jPDados.setLayout(jPDadosLayout);
        jPDadosLayout.setHorizontalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDadosLayout.createSequentialGroup()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPDadosLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDCDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDCDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCBAutomovel, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTFTaxa))
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCBAutuacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addContainerGap(25, Short.MAX_VALUE)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBCarteira)
                            .addComponent(jRBPessoa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBPessoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBCarteira, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPDadosLayout.setVerticalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFTFTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDCDataEmissao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2))
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jCBAutomovel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jCBAutuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCarteira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBCarteira))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBPessoa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPManterLayout = new javax.swing.GroupLayout(jPManter);
        jPManter.setLayout(jPManterLayout);
        jPManterLayout.setHorizontalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPManterLayout.setVerticalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPManter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRBPessoaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRBPessoaStateChanged
        
       
    }//GEN-LAST:event_jRBPessoaStateChanged

    private void jRBPessoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBPessoaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBPessoaMouseClicked

    private void jRBPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPessoaActionPerformed

        if(jRBPessoa.isEnabled() && jRBPessoa.isSelected()) 
        {
            jCBPessoa.setEnabled(true);
            jCBCarteira.setEnabled(false);
        }
    }//GEN-LAST:event_jRBPessoaActionPerformed

    private void jRBCarteiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBCarteiraActionPerformed
        if(jRBCarteira.isEnabled() && jRBCarteira.isSelected()) 
        {
            jCBPessoa.setEnabled(false);
            jCBCarteira.setEnabled(true);
        }
    }//GEN-LAST:event_jRBCarteiraActionPerformed

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
            java.util.logging.Logger.getLogger(FormManterMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManterMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManterMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManterMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormManterMulta().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGStatus;
    private javax.swing.ButtonGroup bgPermissao;
    private javax.swing.ButtonGroup bgResponsavel;
    private javax.swing.JComboBox jCBAutomovel;
    private javax.swing.JComboBox jCBAutuacao;
    private javax.swing.JComboBox jCBCarteira;
    private javax.swing.JComboBox jCBPessoa;
    private com.toedter.calendar.JDateChooser jDCDataEmissao;
    private com.toedter.calendar.JDateChooser jDCDataPagamento;
    private javax.swing.JFormattedTextField jFTFTaxa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPDados;
    private javax.swing.JPanel jPManter;
    private javax.swing.JRadioButton jRBCarteira;
    private javax.swing.JRadioButton jRBPessoa;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the titular
     */
    public Pessoa getTitular() {
        return titular;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }
}

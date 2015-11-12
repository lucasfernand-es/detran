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
public final class FormManterCarteira extends FormTemplate {
    
    
    private Pessoa titular = null;
    private int titularIndex = 0;
    private ArrayList <Pessoa> titularList = new ArrayList<>();
    
    
    private ArrayList<Carteira> carteiraList = new ArrayList<>();
    private Carteira carteiraSelected = null;
   
    private static FormManterCarteira manterForm = null;

    public static FormManterCarteira getForm() {
        if (manterForm == null) {
            manterForm = new FormManterCarteira();
        }
        else {
            
        }

        return manterForm;
    }
    
    /**
     * Creates new form FormManterEvento
     */
    private FormManterCarteira() {
        this.setTitle("Gerenciar Carteira");
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
                    
                    iniciarComboBoxTitular();
                    limparComponentes();
                }
            });
        
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
    private void iniciarComboBoxTitular() {
        jCBTitular.removeAllItems();
        // Carrega todas as pessoas do BD, que são ativos. (Status == true)
        Pessoa pessoa = new Pessoa();
        pessoa.setStatus(true);
        
        if (titularList == null) {
            titularList = new ArrayList();
        }
        titularList.clear();
        // Busca no Banco de Dados TODOS os possíveis titulares
        titularList = PessoaController.buscarPessoa(pessoa, "STATUS");
        
        // Caso algum erro tenha acontecido
        if(pessoa.isError()){
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    pessoa.getMessage());
            return;
        }
        
        // Caso a carteira tenha sido recém criada, devemos por como seleção default
        // no jCBTitular, caso contrário será 0
        
        // Para diminuir o número de comparações para 2, terá dois "for" diferentes
        if (titular == null) {
            for (Pessoa pessoaItem : titularList) {
                jCBTitular.addItem(pessoaItem);
            }
        }
        else {
            for (Pessoa pessoaItem : titularList) {
                jCBTitular.addItem(pessoaItem);
                // Se o titular selecionado e o item atual tiveram o mesmo ID
                // esse será o novo indexTitular
                // Seleciona o último Item selecionado
                if( isTitularIndex(pessoaItem) ) 
                    titularIndex = jCBTitular.getItemCount()-1;
            }
        }
        
    }
    // Busca o Index do Titular no ComboBox
    private boolean isTitularIndex(Pessoa pessoaItem) {
        // Se os ID forem iguais
        return (titular.getIdPessoa() == pessoaItem.getIdPessoa());
    }

    public void liberarComponentes() {
        jTFNRegistro.setEnabled(true);
        jDCDataEmissao.setEnabled(true);
        jRBSimPermissao.setEnabled(true);
        jRBNaoPermissao.setEnabled(true);
        jDCDataVencimento.setEnabled(true);
        jRBSimStatus.setEnabled(true);
        jRBNaoStatus.setEnabled(true);
        jCBTipo.setEnabled(true);
        jCBTitular.setEnabled(true);
    }

    public void bloquearComponentes() {
        jTFNRegistro.setEnabled(false);
        jDCDataEmissao.setEnabled(false);
        jRBSimPermissao.setEnabled(false);
        jRBNaoPermissao.setEnabled(false);
        jDCDataVencimento.setEnabled(false);
        jRBSimStatus.setEnabled(false);
        jRBNaoStatus.setEnabled(false);
        jCBTipo.setEnabled(false);
        jCBTitular.setEnabled(false);
    }

    // Define valores nulos para todos os componentes

    public void limparComponentes() {
        jTFNRegistro.setText("");
        jDCDataEmissao.setDate(null);
        // Por definição
        jRBSimPermissao.setSelected(true);
        jDCDataVencimento.setDate(null);
        // Por definição
        jRBSimStatus.setSelected(true);
        jCBTipo.setSelectedIndex(0);
        // Verificação para selecionar um item no comboBox
        if(jCBTitular.getItemCount() == 0){
            // Não há como selecionar um item
            //System.out.println("Vazio");
        }
        else {
            jCBTitular.setSelectedIndex(titularIndex);
        }
    }

    @Override
    protected void jBTCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        super.jBTCadastrarActionPerformed(evt);

        liberarComponentes();
    }

    @Override
    protected void jBTConfirmarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
        Date dataVencimento = jDCDataVencimento.getDate();
	Date dataEmissao = jDCDataEmissao.getDate();
	String nRegistro = jTFNRegistro.getText();
        // Se a permissão existe. Sim == true e Não == false 
        // Se o Sim não estiver selecionado, o Não está
	boolean permissao = (jRBSimPermissao.isSelected());
	String tipo = (String) jCBTipo.getSelectedItem();
	Pessoa pessoaTitular = (Pessoa) jCBTitular.getSelectedItem();
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
    } 
    @Override
    protected void jBTAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        super.jBTAlterarActionPerformed(evt);

        liberarComponentes();
        
        jTFNRegistro.setEnabled(false);
        jDCDataEmissao.setEnabled(false);
        jDCDataVencimento.setEnabled(false);
        jCBTipo.setEnabled(false);
        jCBTitular.setEnabled(false);
    }

    @Override
    protected void jBTSalvarActionPerformed(java.awt.event.ActionEvent evt) {
         Date dataVencimento = jDCDataVencimento.getDate();
	Date dataEmissao = jDCDataEmissao.getDate();
	String nRegistro = jTFNRegistro.getText();
        // Se a permissão existe. Sim == true e Não == false 
        // Se o Sim não estiver selecionado, o Não está
	boolean permissao = (jRBSimPermissao.isSelected());
	String tipo = (String) jCBTipo.getSelectedItem();
	Pessoa pessoaTitular = (Pessoa) jCBTitular.getSelectedItem();
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
        
        
        jTFNRegistro.setText(carteira.getnRegistro());
        jDCDataEmissao.setDate(carteira.getDataEmissao());
        // Por definição
        if (carteira.isPermissao())
            jRBSimPermissao.setSelected(true);
        else
            jRBNaoPermissao.setSelected(true);
        jDCDataVencimento.setDate(carteira.getDataVencimento());
        // Por definição
        if (carteira.isStatus())
            jRBSimStatus.setSelected(true);
        else
            jRBNaoStatus.setSelected(true);
        
        jCBTitular.setSelectedItem( carteira.getTipo());
        
        int idCarteira = CarteiraController.buscaIDTitutar(titularList, carteira);

        jCBTitular.setSelectedIndex(idCarteira);
       
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
        jPManter = new javax.swing.JPanel();
        jPDados = new javax.swing.JPanel();
        jDCDataEmissao = new com.toedter.calendar.JDateChooser();
        jDCDataVencimento = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFNRegistro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jRBSimStatus = new javax.swing.JRadioButton();
        jRBNaoStatus = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jRBSimPermissao = new javax.swing.JRadioButton();
        jRBNaoPermissao = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jCBTipo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jCBTitular = new javax.swing.JComboBox();

        setSize(new java.awt.Dimension(650, 185));

        jPManter.setPreferredSize(new java.awt.Dimension(650, 200));

        jPDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Carteira"));
        jPDados.setRequestFocusEnabled(false);

        jDCDataEmissao.setDateFormatString("d MMM , yyyy");
        jDCDataEmissao.setEnabled(false);

        jDCDataVencimento.setDateFormatString("d MMM , yyyy");
        jDCDataVencimento.setEnabled(false);

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

        bGStatus.add(jRBSimStatus);
        jRBSimStatus.setMnemonic('1');
        jRBSimStatus.setSelected(true);
        jRBSimStatus.setText("Sim");
        jRBSimStatus.setToolTipText("");
        jRBSimStatus.setEnabled(false);
        jRBSimStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBSimStatusActionPerformed(evt);
            }
        });

        bGStatus.add(jRBNaoStatus);
        jRBNaoStatus.setMnemonic('0');
        jRBNaoStatus.setText("Não");
        jRBNaoStatus.setEnabled(false);

        jLabel5.setText("Permissão");

        bgPermissao.add(jRBSimPermissao);
        jRBSimPermissao.setMnemonic('1');
        jRBSimPermissao.setSelected(true);
        jRBSimPermissao.setText("Sim");
        jRBSimPermissao.setToolTipText("");
        jRBSimPermissao.setEnabled(false);
        jRBSimPermissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBSimPermissaoActionPerformed(evt);
            }
        });

        bgPermissao.add(jRBNaoPermissao);
        jRBNaoPermissao.setMnemonic('0');
        jRBNaoPermissao.setText("Não");
        jRBNaoPermissao.setEnabled(false);

        jLabel6.setText("Categorias");

        jCBTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "AB", "C", "D", "E", "ACC", "MOTOR-CASA" }));
        jCBTipo.setEnabled(false);

        jLabel7.setText("Titular");

        jCBTitular.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Titular 1", "Titular 2", "Titular 3" }));
        jCBTitular.setEnabled(false);

        javax.swing.GroupLayout jPDadosLayout = new javax.swing.GroupLayout(jPDados);
        jPDados.setLayout(jPDadosLayout);
        jPDadosLayout.setHorizontalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDCDataEmissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDCDataVencimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBTipo, 0, 173, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRBSimPermissao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRBNaoPermissao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(191, 191, 191))
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDadosLayout.createSequentialGroup()
                                .addComponent(jCBTitular, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDadosLayout.createSequentialGroup()
                                .addComponent(jRBSimStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRBNaoStatus)
                                .addGap(168, 168, 168)
                                .addComponent(jLabel6)
                                .addGap(191, 191, 191))))))
        );
        jPDadosLayout.setVerticalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDCDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTFNRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDCDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRBSimPermissao)
                            .addComponent(jRBNaoPermissao)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jRBSimStatus)
                                    .addComponent(jRBNaoStatus))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
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
                .addContainerGap(7, Short.MAX_VALUE))
        );

        getContentPane().add(jPManter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFNRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNRegistroActionPerformed

    private void jRBSimStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBSimStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBSimStatusActionPerformed

    private void jRBSimPermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBSimPermissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBSimPermissaoActionPerformed

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
            java.util.logging.Logger.getLogger(FormManterCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManterCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManterCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManterCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormManterCarteira().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGStatus;
    private javax.swing.ButtonGroup bgPermissao;
    private javax.swing.JComboBox jCBTipo;
    private javax.swing.JComboBox jCBTitular;
    private com.toedter.calendar.JDateChooser jDCDataEmissao;
    private com.toedter.calendar.JDateChooser jDCDataVencimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPDados;
    private javax.swing.JPanel jPManter;
    private javax.swing.JRadioButton jRBNaoPermissao;
    private javax.swing.JRadioButton jRBNaoStatus;
    private javax.swing.JRadioButton jRBSimPermissao;
    private javax.swing.JRadioButton jRBSimStatus;
    private javax.swing.JTextField jTFNRegistro;
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

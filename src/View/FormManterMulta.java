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
import Controller.MultaController;
import Controller.PessoaController;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import valueObject.Automovel;
import valueObject.Autuacao;
import valueObject.Carteira;
import valueObject.Multa;
import valueObject.Pessoa;

/**
 *
 * @author LucasFernandes
 */
public final class FormManterMulta extends FormTemplate {
    
    private ArrayList <Automovel> automovelList = new ArrayList<>();
    private ArrayList <Autuacao> autuacaoList = new ArrayList<>();
    private ArrayList <Carteira> carteiraList = new ArrayList<>();
    private ArrayList <Pessoa> pessoaList = new ArrayList<>();
    
    
    private ArrayList<Multa> multaList = new ArrayList<>();
   
    private static FormManterMulta manterForm = null;
    
    private Multa multaSelected;
    
    

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
        this.setTitle("Gerenciar Multa");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);

        // Resetar todos os componentes
        bloquearComponentes();
        limparComponentes();
        
        iniciarComboBoxPessoa();
        iniciarComboBoxCarteira();
        iniciarComboBoxAutomovel();
        iniciarComboBoxAutuacao();
        
    }

    
    public void iniciarComponentes() {
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null}
                },
               new String[]{
                    "Responsável", "Automóvel", "Autuação",
                    "Data de Emissão", "Status do Pagamento", "Taxa de Acréscimo"
                }
        ) {
            // Quatidade de Colunas
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        
        
        super.jLInstrucao.setText("Informe o número do CPF, Renavam ou Número da Carteira");

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
        
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        tableModel.setRowCount(0);
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
        pessoaList = PessoaController.buscarPessoa(pessoa, "SEMCARTEIRA");
        
        // Caso algum erro tenha acontecido
        if(pessoa.isError()){
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    pessoa.getMessage());
            return;
        }
        
        // Caso a carteira tenha sido recém criada, devemos por como seleção default
        // no jCBPessoa, caso contrário será 0
        
        jCBPessoa.addItem(null);
        for (Pessoa pessoaItem : pessoaList) {
            jCBPessoa.addItem(pessoaItem);
        }
        
    }
    
    //  Carregar Automóveis do BD
    //  Será carregado somente quando a Janela for visível
    private void iniciarComboBoxAutomovel() {
        jCBAutomovel.removeAllItems();
        // Carrega todas as pessoas do BD, que são ativos. (Status == true)
        Automovel automovel = new Automovel();
        automovel.setStatus(true);
        
        if (automovelList == null) {
            automovelList = new ArrayList();
        }
        automovelList.clear();
        // Busca no Banco de Dados TODOS os possíveis automoveis
        automovelList = AutomovelController.buscarAutomovel(automovel, "STATUS");
        
        // Caso algum erro tenha acontecido
        if(automovel.isError()){
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    automovel.getMessage());
            return;
        }
        
        // Caso a carteira tenha sido recém criada, devemos por como seleção default
        // no jCBPessoa, caso contrário será 0
        
        for (Automovel item : automovelList) {
            jCBAutomovel.addItem(item);
        }
        
    }
    
    //  Carregar Autuações do BD
    //  Será carregado somente quando a Janela for visível
    private void iniciarComboBoxAutuacao() {
        jCBAutuacao.removeAllItems();
        // Carrega todas as pessoas do BD, que são ativos. (Status == true)
        Autuacao autuacao = new Autuacao();
        //autuacao.setStatus(true);
        
        if (autuacaoList == null) {
            autuacaoList = new ArrayList();
        }
        autuacaoList.clear();
        // Busca no Banco de Dados TODOS os possíveis automoveis
        autuacaoList = AutuacaoController.buscarAutuacao(autuacao, "ALL");
        
        // Caso algum erro tenha acontecido
        if(autuacao.isError()){
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    autuacao.getMessage());
            return;
        }
        
        // Caso a carteira tenha sido recém criada, devemos por como seleção default
        // no jCBPessoa, caso contrário será 0
        
        for (Autuacao item : autuacaoList) {
            jCBAutuacao.addItem(item);
        }
        
    }
    
    //  Carregar Autuações do BD
    //  Será carregado somente quando a Janela for visível
    private void iniciarComboBoxCarteira() {
        jCBCarteira.removeAllItems();
        // Carrega todas as pessoas do BD, que são ativos. (Status == true)
        Carteira carteira = new Carteira();
        //autuacao.setStatus(true);
        
        if (carteiraList == null) {
            carteiraList = new ArrayList();
        }
        carteiraList.clear();
        // Busca no Banco de Dados TODOS os possíveis automoveis
        carteiraList = CarteiraController.buscarCarteira(carteira, "CARTEIRASATIVAS");
        
        // Caso algum erro tenha acontecido
        if(carteira.isError()){
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    carteira.getMessage());
            return;
        }
        
        // Caso a carteira tenha sido recém criada, devemos por como seleção default
        // no jCBPessoa, caso contrário será 0
        jCBCarteira.addItem(null);
        for (Carteira item : carteiraList) {
            jCBCarteira.addItem(item);
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
        
        try {
            Date dataEmissao = jDCDataEmissao.getDate();
            float taxaAcrescimo = Float.parseFloat(jFTFTaxa.getText());
            Date dataPagamento = jDCDataPagamento.getDate();
            Automovel automovel = (Automovel) jCBAutomovel.getSelectedItem();
            Autuacao autuacao = (Autuacao) jCBAutuacao.getSelectedItem();
            Pessoa pessoa = null;
            Carteira carteira = null ;

            if(jRBCarteira.isSelected())
                carteira = (Carteira) jCBCarteira.getSelectedItem();
            else
                pessoa = (Pessoa) jCBPessoa.getSelectedItem();

            // ID deste objeto no banco de dados
            int idMulta = -2;

            Multa multa = new Multa(dataEmissao, taxaAcrescimo, dataPagamento,
                automovel, pessoa, carteira,
                autuacao, idMulta);

            multa.setError(false);
            multa.setMessage("");


            //System.out.println(carteira.showCarteira());
            MultaController.cadastrarMulta(multa);

            if(multa.isError()){
                    Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                            multa.getMessage());
                }
            else {
                    Aviso.showInformation(multa.getMessage());
                    super.jBTConfirmarActionPerformed(evt);
                    bloquearComponentes();
                    limparComponentes();
            }
        }
        catch(Exception e)
        {
             Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                            "Campo numérico Taxa de Acréscimo não é válido\n" +
                            e.getMessage());
        }
        
    } 
    @Override
    protected void jBTAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        super.jBTAlterarActionPerformed(evt);
        
        jDCDataPagamento.setEnabled(true);
        jDCDataEmissao.setEnabled(false);
        jFTFTaxa.setEnabled(false);
        
        jCBAutomovel.setEnabled(false);
        jCBAutuacao.setEnabled(false);
        jCBPessoa.setEnabled(true);
        jCBCarteira.setEnabled(true);
        
        jRBPessoa.setEnabled(true);
        jRBCarteira.setEnabled(true);
       
    }

    @Override
    protected void jBTSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        
        try {
            
            multaSelected.showMulta();
            
            multaSelected.setDataPagamento(  jDCDataPagamento.getDate()  );
            
            
            
            
            Pessoa pessoa = null;
            Carteira carteira = null ;

            if(jRBCarteira.isSelected())
                carteira = (Carteira) jCBCarteira.getSelectedItem();
            else
                pessoa = (Pessoa) jCBPessoa.getSelectedItem();
            
            multaSelected.setCarteira(carteira);
            multaSelected.setPessoa(pessoa);
            
            multaSelected.showMulta();
            
            multaSelected.setError(false);
            multaSelected.setMessage("");


            //System.out.println(carteira.showCarteira());
            MultaController.alterarMulta(multaSelected);

            if(multaSelected.isError()){
                    Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                            multaSelected.getMessage());
                }
            else {
                    Aviso.showInformation(multaSelected.getMessage());
                    super.jBTConfirmarActionPerformed(evt);
                    bloquearComponentes();
                    limparComponentes();
                    
                    DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
                    tableModel.setRowCount(0);
            }
        }
        catch(Exception e)
        {
             Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                            e.getMessage());
        }
    }

    @Override
    protected void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        
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
        
        //System.out.println("Busca: " + jTFBusca.getText());
        
        String busca = jTFBusca.getText();
       
        Automovel automovel = new Automovel(); 
        Carteira carteira = new Carteira();
        Pessoa pessoa = new Pessoa();
        
        //renavam, cpf e/ou carteira
        
        automovel.setRenavam(busca);
        
        pessoa.setCpf(busca);
        boolean cpfValido  = PessoaController.mascaraCPF(pessoa);
        // Se o que foi digitado na pesquisa não for válido, não é necessário fazer a busca
        if(!cpfValido)
            pessoa.setCpf("");
        //System.out.println("MASK: " + pessoa.getCpf());
        
        carteira.setnRegistro(busca);
        
        Multa multa = new Multa();
        
        multa.setAutomovel(automovel);
        multa.setPessoa(pessoa);
        multa.setCarteira(carteira);
        
        multaList.clear();
        
        
        multaList = MultaController.buscarMulta(multa, "ALLMULTA");
        

        if(multa.isError())
        {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    multa.getMessage());
        }
        if (multaList == null)
        {
            multaList = new ArrayList<>();
            return;
        }
            
        preencherPesquisa();
        
        bloquearComponentes();
        limparComponentes();
    } 
    
    private void preencherPesquisa() { 
                    
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
    
        tableModel.setRowCount(0);
        
        for(Multa multa : multaList ) {
            tableModel.addRow(new Object[] { 
                    (multa.getPessoa() == null)? multa.getCarteira().getTitular(): multa.getPessoa(),
                    multa.getAutomovel(),
                    multa.getAutuacao(),
                    multa.getDataEmissao(),
                    (multa.estaPago())? "Pago: " + multa.getDataPagamento() : "Pendente",
                    multa.getTaxaAcrescimo()
            } );
        }
    }

    @Override
    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {
        super.jTBBuscaRapidaMouseClicked(evt);
        jBTExcluir.setEnabled(false);
        //System.out.println("carregar coisinhas"); // oi
        // Não executar sem dados
        if (multaList.isEmpty())
            return;
        
        int selectedRow = super.jTBBuscaRapida.getSelectedRow();
        multaSelected = multaList.get(selectedRow);
        
        //System.out.println(pessoaSelected.showPessoa());
        preencheComponentes();
        
        bloquearComponentes();
    }
    
    public void preencheComponentes() {
        
        
        jDCDataPagamento.setDate(multaSelected.getDataPagamento());
        jDCDataEmissao.setDate(multaSelected.getDataEmissao());
        jFTFTaxa.setText(String.valueOf( multaSelected.getTaxaAcrescimo() ));
        
        // busca automovel
        int idAutomovel = AutomovelController.buscaIDAutomovel(automovelList, multaSelected.getAutomovel());
        jCBAutomovel.setSelectedIndex(idAutomovel);
        jTFProprietario.setText( String.valueOf(multaSelected.getAutomovel().getProprietario()) );
        //System.out.println("idAutomovel" + idAutomovel);
        
        //busca autuacao
        int idAutuacao= AutuacaoController.buscaIDAutuacao(autuacaoList, multaSelected.getAutuacao());
        jCBAutuacao.setSelectedIndex(idAutuacao);
        //System.out.println("idAutuacao"+idAutuacao);
        
        //busca pessoa
        jCBPessoa.setSelectedIndex(0);
        if(multaSelected.getPessoa() != null) {
            int idPessoa = PessoaController.buscaIDPessoa(pessoaList, multaSelected.getPessoa());
            jCBPessoa.setSelectedIndex(idPessoa + 1);
            //System.out.println("idPessoa" + idPessoa);
        }
        
        //busca carteira
        jCBCarteira.setSelectedIndex(0);
        if(multaSelected.getCarteira() != null) {
            int idCarteira = CarteiraController.buscaIDCarteira(carteiraList, multaSelected.getCarteira());
            jCBCarteira.setSelectedIndex(idCarteira + 1);
            //System.out.println("idCarteira pos: " + idCarteira + " - " + multaSelected.getCarteira().getIdCarteira());
        }
        
        if(multaSelected.getPessoa() == null)
            jRBCarteira.setSelected(true);
        else
            jRBPessoa.setSelected(true);
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
        jLabel3 = new javax.swing.JLabel();
        jTFProprietario = new javax.swing.JLabel();

        setSize(new java.awt.Dimension(650, 185));

        jPManter.setPreferredSize(new java.awt.Dimension(650, 250));

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
        jCBAutomovel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBAutomovelItemStateChanged(evt);
            }
        });

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

        jLabel3.setText("Proprietário do Veículo:");

        javax.swing.GroupLayout jPDadosLayout = new javax.swing.GroupLayout(jPDados);
        jPDados.setLayout(jPDadosLayout);
        jPDadosLayout.setHorizontalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDadosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPDadosLayout.createSequentialGroup()
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBCarteira)
                            .addComponent(jRBPessoa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBPessoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPDadosLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTFProprietario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPDadosLayout.createSequentialGroup()
                                    .addComponent(jCBCarteira, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
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
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFProprietario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCarteira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBCarteira))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBPessoa))
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
                .addComponent(jPDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jCBAutomovelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBAutomovelItemStateChanged
  
        Automovel automovel = (Automovel) jCBAutomovel.getSelectedItem();
        
        if(automovel == null)
        {
            jTFProprietario.setText("");
            return;
        }
        
        jTFProprietario.setText( String.valueOf( automovel.getProprietario()) );
        
        Pessoa pessoa = automovel.getProprietario();
        
        //busca pessoa
        int idPessoa = PessoaController.buscaIDPessoa(pessoaList, pessoa);
        int idCarteira = CarteiraController.buscaIDCarteira(carteiraList, pessoa);
        
        
        if(idPessoa == -1) {
            jCBCarteira.setSelectedIndex(idCarteira + 1);
            jCBPessoa.setSelectedIndex(0); jCBPessoa.setEnabled(false);
            jRBCarteira.setSelected(true);
        }
        else if(idCarteira == -1){
            jCBCarteira.setSelectedIndex(0); jCBCarteira.setEnabled(false);
            jCBPessoa.setSelectedIndex(idPessoa + 1);
            jRBPessoa.setSelected(true);
        }
        else {
            jCBCarteira.setSelectedIndex(0);
            jCBPessoa.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jCBAutomovelItemStateChanged

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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPDados;
    private javax.swing.JPanel jPManter;
    private javax.swing.JRadioButton jRBCarteira;
    private javax.swing.JRadioButton jRBPessoa;
    private javax.swing.JLabel jTFProprietario;
    // End of variables declaration//GEN-END:variables

}

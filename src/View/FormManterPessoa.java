/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Addons.Aviso;
import Addons.CepWebService;
import valueObject.Pessoa;
import java.awt.Color;

/**
 *
 * @author LucasFernandes
 */
public final class FormManterPessoa extends FormTemplate {
    
    /**
     * Creates new form FormManterEvento
     */
    private FormManterPessoa() {
        this.setTitle("Gerenciar Pessoa");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);

        // Resetar todos os componentes
        bloquearComponentes();
        limparComponentes();
    }

    private static FormManterPessoa manterEvento = null;

    public static FormManterPessoa getFormManterPessoa() {
        if (manterEvento == null) {
            manterEvento = new FormManterPessoa();
        }

        return manterEvento;

    }

    public void iniciarComponentes() {
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null}
                },
                new String[]{
                    "Titulo", "Tema", "Data de Inicio",
                    "Data de Fim", "Data Prazo das Inscrições", "Agenda Finalizada"
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
        super.jScrollPane1.setViewportView(jTBBuscaRapida);

        jTBBuscaRapida.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBBuscaRapidaMouseClicked(evt);
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

    public void liberarComponentes() {
        jTFNome.setEnabled(true);
        jFTFCPF.setEnabled(true);
        jDCDataNascimento.setEnabled(true);
        jFTFRG.setEnabled(true);
        jCBOrgaoEmissor.setEnabled(true);
        jCBEstadoEmissor.setEnabled(true);
        jTFNomeMae.setEnabled(true);
        jTFNomePai.setEnabled(true);
        jTFLogradouro.setEnabled(true);
        jFTFNumero.setEnabled(true);
        jTFComplemento.setEnabled(true);
        jTFBairro.setEnabled(true);
        jTFCidade.setEnabled(true);
        jTFEstado.setEnabled(true);
        jFTFCEP.setEnabled(true);
    }

    public void bloquearComponentes() {
        jTFNome.setEnabled(false);
        jFTFCPF.setEnabled(false);
        jDCDataNascimento.setEnabled(false);
        jFTFRG.setEnabled(false);
        jCBOrgaoEmissor.setEnabled(false);
        jCBEstadoEmissor.setEnabled(false);
        jTFNomeMae.setEnabled(false);
        jTFNomePai.setEnabled(false);
        jTFLogradouro.setEnabled(false);
        jFTFNumero.setEnabled(false);
        jTFComplemento.setEnabled(false);
        jTFBairro.setEnabled(false);
        jTFCidade.setEnabled(false);
        jTFEstado.setEnabled(false);
        jFTFCEP.setEnabled(false);
    }

    // Define valores nulos para todos os componentes

    public void limparComponentes() {
        jTFNome.setText("");
        jFTFCPF.setText("123.456.789-00");
        jDCDataNascimento.setDate(null);
        jFTFRG.setText("12.345.678-X");
        jCBOrgaoEmissor.setSelectedIndex(0);
        jCBEstadoEmissor.setSelectedIndex(0);
        jTFNomeMae.setText("");
        jTFNomePai.setText("");
        jTFLogradouro.setText("");
        jFTFNumero.setText("");
        jTFComplemento.setText("");
        jTFBairro.setText("");
        jTFCidade.setText("");
        jTFEstado.setText("");
        jFTFCEP.setText("12.345-678");
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

        bGAgenda = new javax.swing.ButtonGroup();
        jPManterPessoa = new javax.swing.JPanel();
        jPEndereco = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTFLogradouro = new javax.swing.JTextField();
        jTFBairro = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTFComplemento = new javax.swing.JTextField();
        jTFEstado = new javax.swing.JTextField();
        jTFCidade = new javax.swing.JTextField();
        jBBuscarEndereço = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jFTFCEP = new javax.swing.JFormattedTextField();
        jFTFNumero = new javax.swing.JFormattedTextField();
        jPDados = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jDCDataNascimento = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jCBEstadoEmissor = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTFNomeMae = new javax.swing.JTextField();
        jTFNomePai = new javax.swing.JTextField();
        jFTFCPF = new javax.swing.JFormattedTextField();
        jFTFRG = new javax.swing.JFormattedTextField();
        jCBOrgaoEmissor = new javax.swing.JComboBox();

        setSize(new java.awt.Dimension(650, 185));

        jPManterPessoa.setPreferredSize(new java.awt.Dimension(650, 370));

        jPEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));
        jPEndereco.setSize(new java.awt.Dimension(650, 180));

        jLabel19.setText("Logradouro");

        jLabel21.setText("Estado");

        jTFLogradouro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFLogradouro.setEnabled(false);

        jTFBairro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFBairro.setEnabled(false);

        jLabel22.setText("Bairro");

        jLabel23.setText("Cidade");

        jLabel24.setText("N°");

        jLabel1.setText("Complemento");

        jTFComplemento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFComplemento.setEnabled(false);

        jTFEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFEstado.setEnabled(false);

        jTFCidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFCidade.setEnabled(false);

        jBBuscarEndereço.setText("Buscar Endereço");
        jBBuscarEndereço.setEnabled(false);
        jBBuscarEndereço.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarEndereçoActionPerformed(evt);
            }
        });

        jLabel20.setText("CEP");

        try {
            jFTFCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTFCEP.setText("12.345-678");
        jFTFCEP.setEnabled(false);
        jFTFCEP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFTFCEPFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTFCEPFocusLost(evt);
            }
        });

        jFTFNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFTFNumero.setToolTipText("1234");
        jFTFNumero.setEnabled(false);

        javax.swing.GroupLayout jPEnderecoLayout = new javax.swing.GroupLayout(jPEndereco);
        jPEndereco.setLayout(jPEnderecoLayout);
        jPEnderecoLayout.setHorizontalGroup(
            jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19)
                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPEnderecoLayout.createSequentialGroup()
                        .addComponent(jTFBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTFLogradouro)
                    .addGroup(jPEnderecoLayout.createSequentialGroup()
                        .addComponent(jFTFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFComplemento)))
                .addContainerGap())
            .addGroup(jPEnderecoLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTFCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBBuscarEndereço, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPEnderecoLayout.setVerticalGroup(
            jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEnderecoLayout.createSequentialGroup()
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTFLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTFComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jFTFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTFCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jTFEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jTFBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTFCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jBBuscarEndereço))
                .addContainerGap())
        );

        jPDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));
        jPDados.setSize(new java.awt.Dimension(650, 190));

        jLabel11.setText("Estado Emissor");

        jDCDataNascimento.setDateFormatString("d MMM , yyyy");
        jDCDataNascimento.setEnabled(false);

        jLabel12.setText("CPF");

        jLabel13.setText("Data de Nascimento");

        jLabel14.setText("Orgão Emissor");

        jLabel15.setText("Nome Completo");

        jTFNome.setEnabled(false);
        jTFNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomeActionPerformed(evt);
            }
        });

        jCBEstadoEmissor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
        jCBEstadoEmissor.setEnabled(false);

        jLabel16.setText("RG");

        jLabel17.setText("Nome da Mãe");

        jLabel18.setText("Nome do Pai");

        jTFNomeMae.setEnabled(false);
        jTFNomeMae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomeMaeActionPerformed(evt);
            }
        });

        jTFNomePai.setEnabled(false);
        jTFNomePai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomePaiActionPerformed(evt);
            }
        });

        try {
            jFTFCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTFCPF.setText("123.456.789-00");
        jFTFCPF.setEnabled(false);
        jFTFCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFTFCPFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTFCPFFocusLost(evt);
            }
        });

        try {
            jFTFRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-A")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTFRG.setText("12.345.678-X");
        jFTFRG.setEnabled(false);
        jFTFRG.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFTFRGFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTFRGFocusLost(evt);
            }
        });
        jFTFRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFTFRGActionPerformed(evt);
            }
        });

        jCBOrgaoEmissor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SSP", "COREN", "CRA", "CRAS", "CRB", "CRC", "CRE", "CREA", "CRECI", "CREFIT", "CRF", "CRM", "CRN", "CRO", "CRP", "CRPRE", "CRQ", "CRRC", "CRMV", "DPF", "EST", "I CLA", "MAE", "MEX", "MMA", "OAB", "OMB", "IFP", "Outro" }));
        jCBOrgaoEmissor.setEnabled(false);

        javax.swing.GroupLayout jPDadosLayout = new javax.swing.GroupLayout(jPDados);
        jPDados.setLayout(jPDadosLayout);
        jPDadosLayout.setHorizontalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNome))
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addComponent(jFTFRG)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCBOrgaoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCBEstadoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPDadosLayout.createSequentialGroup()
                                .addComponent(jFTFCPF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPDadosLayout.createSequentialGroup()
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFNomeMae)
                            .addComponent(jTFNomePai))))
                .addContainerGap())
        );
        jPDadosLayout.setVerticalGroup(
            jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosLayout.createSequentialGroup()
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)
                        .addComponent(jFTFCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDCDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11)
                    .addComponent(jCBEstadoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jFTFRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBOrgaoEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTFNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTFNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPManterPessoaLayout = new javax.swing.GroupLayout(jPManterPessoa);
        jPManterPessoa.setLayout(jPManterPessoaLayout);
        jPManterPessoaLayout.setHorizontalGroup(
            jPManterPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterPessoaLayout.createSequentialGroup()
                .addGroup(jPManterPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPEndereco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPManterPessoaLayout.setVerticalGroup(
            jPManterPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPManterPessoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPDados, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPManterPessoa, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNomeActionPerformed

    private void jTFNomeMaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomeMaeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNomeMaeActionPerformed

    private void jTFNomePaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomePaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNomePaiActionPerformed

    private void jBBuscarEndereçoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarEndereçoActionPerformed
        // Buscar os dados de CEP
        Pessoa pessoa = new Pessoa();
        pessoa.setCep(this.jFTFCEP.getText());
        CepWebService cws = new CepWebService();
        cws.buscaCEP(pessoa);

        if(pessoa.isError())
        {
            Aviso.showError(pessoa.getMessage());
            jTFLogradouro.setText("");
            jTFBairro.setText("");
            jTFCidade.setText("");
            jTFEstado.setText("");
        }
        else {
            jTFLogradouro.setText(pessoa.getLogradouro());
            jTFBairro.setText(pessoa.getBairro());
            jTFCidade.setText(pessoa.getCidade());
            jTFEstado.setText(pessoa.getEstado());
        }
    }//GEN-LAST:event_jBBuscarEndereçoActionPerformed

    private void jFTFCEPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTFCEPFocusGained
        this.jFTFCEP.setText(null);
        this.jFTFCEP.setForeground(Color.BLACK);
    }//GEN-LAST:event_jFTFCEPFocusGained

    private void jFTFCEPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTFCEPFocusLost
        if(this.jFTFCEP.getText().equals("  .   -   "))
        {
            Aviso.showWarning("Preencha corretamente o campo");
            this.jFTFCEP.setText("12.345-678");
            this.jFTFCEP.setForeground(Color.gray);
        }

    }//GEN-LAST:event_jFTFCEPFocusLost

    private void jFTFCPFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTFCPFFocusGained
        this.jFTFCPF.setText(null);
        this.jFTFCPF.setForeground(Color.BLACK);
    }//GEN-LAST:event_jFTFCPFFocusGained

    private void jFTFCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTFCPFFocusLost
        if(this.jFTFCPF.getText().equals("   .   .   -  "))
        {
            Aviso.showWarning("Preencha corretamente o campo");
            this.jFTFCPF.setText("123.456.789-00");
            this.jFTFCPF.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jFTFCPFFocusLost

    private void jFTFRGFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTFRGFocusGained
        this.jFTFRG.setText(null);
        this.jFTFRG.setForeground(Color.BLACK);
    }//GEN-LAST:event_jFTFRGFocusGained

    private void jFTFRGFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTFRGFocusLost
        if(this.jFTFRG.getText().equals("  .   .   - "))
        {
            Aviso.showWarning("Preencha corretamente o campo");
            this.jFTFRG.setText("12.345.678-X");
            this.jFTFRG.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jFTFRGFocusLost

    private void jFTFRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFTFRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFTFRGActionPerformed

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
            java.util.logging.Logger.getLogger(FormManterPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManterPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManterPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManterPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormManterPessoa().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGAgenda;
    private javax.swing.JButton jBBuscarEndereço;
    private javax.swing.JComboBox jCBEstadoEmissor;
    private javax.swing.JComboBox jCBOrgaoEmissor;
    private com.toedter.calendar.JDateChooser jDCDataNascimento;
    private javax.swing.JFormattedTextField jFTFCEP;
    private javax.swing.JFormattedTextField jFTFCPF;
    private javax.swing.JFormattedTextField jFTFNumero;
    private javax.swing.JFormattedTextField jFTFRG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPDados;
    private javax.swing.JPanel jPEndereco;
    private javax.swing.JPanel jPManterPessoa;
    private javax.swing.JTextField jTFBairro;
    private javax.swing.JTextField jTFCidade;
    private javax.swing.JTextField jTFComplemento;
    private javax.swing.JTextField jTFEstado;
    private javax.swing.JTextField jTFLogradouro;
    private javax.swing.JTextField jTFNome;
    private javax.swing.JTextField jTFNomeMae;
    private javax.swing.JTextField jTFNomePai;
    // End of variables declaration//GEN-END:variables
}

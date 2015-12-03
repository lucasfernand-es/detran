package View;

import Addons.Aviso;
import Controller.MultaController;
import Controller.PessoaController;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import valueObject.Automovel;
import valueObject.Carteira;
import valueObject.Multa;
import valueObject.Pessoa;

/**
 *
 * @author cristhian
 */
public final class FormVisualizarMulta extends FormTemplate {

    private ArrayList<Multa> multaList;
    private final ArrayList<Multa> selectedMultas;

    private static FormVisualizarMulta formVisualizarMulta = null;
    public static FormVisualizarMulta getForm() {
        if (formVisualizarMulta == null) {
            formVisualizarMulta = new FormVisualizarMulta();
        }
        return formVisualizarMulta;
    }

    /**
     * Creates new form FormManterEvento
     */
    private FormVisualizarMulta() {
        this.setTitle("Buscar Multa");
        initComponents();
        iniciarComponentes();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                FormPrincipal.isShowingModal = false;
            }
        });

        multaList = new ArrayList<>();
        selectedMultas = new ArrayList<>();
    }

    public void iniciarComponentes() {
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null}
                },
                new String[]{
                    "", "Responsável", "Automóvel",
                    "Autuação", "Data de Emissão", "Status do Pagamento",
                    "Taxa de Acréscimo"
                }
        ) {
            // Quantidade de Colunas
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBuscaKeyReleased(evt);
            }
        });

        jPBotoes.setVisible(false);
        jButtonGerarExtrato.setEnabled(false);

        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        tableModel.setRowCount(0);

        setSize(new Dimension(882, 380));
    }
    
    public void abre() {
        limpa();
        this.setVisible(true);
    }
    
    private void limpa() {
        jTFBusca.setText("");
        jTFBuscaKeyReleased(null);
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
        boolean cpfValido = PessoaController.mascaraCPF(pessoa);
        // Se o que foi digitado na pesquisa não for válido, não é necessário fazer a busca
        if (!cpfValido) {
            pessoa.setCpf("");
            return;
        }
        //System.out.println("MASK: " + pessoa.getCpf());

        carteira.setnRegistro(busca);

        Multa multa = new Multa();

        multa.setAutomovel(automovel);
        multa.setPessoa(pessoa);
        multa.setCarteira(carteira);

        multaList.clear();
        multaList = MultaController.buscarMulta(multa, "EQUAL_ALMULTA");

        if (multa.isError()) {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n"
                    + multa.getMessage());
        }
        if (multaList == null) {
            multaList = new ArrayList<>();
            return;
        }

        preencherPesquisa();
    }

    private void preencherPesquisa() {
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();

        tableModel.setRowCount(0);
        
        for (Multa multa : multaList) {
            tableModel.addRow(new Object[]{
                false,
                (multa.getPessoa() == null) ? multa.getCarteira().getTitular() : multa.getPessoa(),
                multa.getAutomovel(),
                multa.getAutuacao(),
                multa.getDataEmissao(),
                (multa.estaPago()) ? "Pago: " + multa.getDataPagamento() : "Pendente",
                multa.getTaxaAcrescimo()
            });
        }
    }

    @Override
    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {
        super.jTBBuscaRapidaMouseClicked(evt);
        if (multaList.isEmpty()) {
            return;
        }

        int selectedColumn = super.jTBBuscaRapida.getSelectedColumn();
        if (selectedColumn == 0) {
            int selectedRow = super.jTBBuscaRapida.getSelectedRow();
            super.jTBBuscaRapida.getModel().setValueAt(
                    !((Boolean) jTBBuscaRapida.getModel().getValueAt(selectedRow, 0)),
                    selectedRow,
                    0
            );

            if ((Boolean) jTBBuscaRapida.getModel().getValueAt(selectedRow, 0) == true) {
                selectedMultas.add(
                        multaList.get(selectedRow)
                );
            } else {
                selectedMultas.remove(
                        multaList.get(selectedRow)
                );
            }
            jButtonGerarExtrato.setEnabled(!selectedMultas.isEmpty());
            for(Multa multa : selectedMultas) {
                if(multa.getDataPagamento() != null) {
                    jButtonGerarExtrato.setEnabled(false);
                }
            }
        }
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
        jButtonGerarExtrato = new javax.swing.JButton();

        setSize(new java.awt.Dimension(650, 185));

        jPManter.setPreferredSize(new java.awt.Dimension(650, 250));

        jButtonGerarExtrato.setText("Gerar Extrato");
        jButtonGerarExtrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarExtratoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPManterLayout = new javax.swing.GroupLayout(jPManter);
        jPManter.setLayout(jPManterLayout);
        jPManterLayout.setHorizontalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPManterLayout.createSequentialGroup()
                .addContainerGap(540, Short.MAX_VALUE)
                .addComponent(jButtonGerarExtrato)
                .addContainerGap())
        );
        jPManterLayout.setVerticalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPManterLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jButtonGerarExtrato)
                .addContainerGap())
        );

        getContentPane().add(jPManter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGerarExtratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarExtratoActionPerformed
        // chamar tela do lucas
    }//GEN-LAST:event_jButtonGerarExtratoActionPerformed

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
            java.util.logging.Logger.getLogger(FormVisualizarMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            new FormVisualizarMulta().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGStatus;
    private javax.swing.ButtonGroup bgPermissao;
    private javax.swing.ButtonGroup bgResponsavel;
    private javax.swing.JButton jButtonGerarExtrato;
    private javax.swing.JPanel jPManter;
    // End of variables declaration//GEN-END:variables

}

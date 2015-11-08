package View;

import Addons.Aviso;
import Controller.AutomovelController;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import valueObject.Automovel;
import valueObject.Pessoa;

/**
 *
 * @author cristhian
 */
public final class FormVisualizarAutomovelOld extends FormTemplate {
    
    
    private Pessoa proprietario = null;
    private int titularIndex = 0;
    private ArrayList <Pessoa> titularList = new ArrayList<>();
    private boolean editing = false;
    private int idAutomovel;
   
    private static FormVisualizarAutomovelOld manterForm = null;

    public static FormVisualizarAutomovelOld getForm() {
        if (manterForm == null) {
            manterForm = new FormVisualizarAutomovelOld();
        }
        return manterForm;
    }
    
    /**
     * Creates new form FormManterEvento
     */
    private FormVisualizarAutomovelOld() {
        this.setTitle("Gerenciar Automóvel");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);
        
        // Toda vez que o formulário for mostrado, carrega o ComboBoxTitular Novamente e limpar os campos
        this.addComponentListener(new ComponentAdapter() {
            /* code run when JFrame shown */
            
            @Override
            public void componentShown(ComponentEvent e) {
                }
            });
        
        jTFBuscaKeyReleased(null);
    }

    
    public void iniciarComponentes() {
        super.setSize(714, 426);
        
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null}
                },
                new String[]{
                    "Proprietário", "Renavam", "Marca",
                    "Modelo", "Cor", "Placa"
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
        
        super.jLInstrucao.setText("Informe o número do Renavam (Somente Números)");

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
        
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        tableModel.setRowCount(0);
        
        jPBotoes.setVisible(false);
    }
    
    @Override
    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {                                            
        this.jButtonVisualizarMultas.setEnabled(true);
    }     
    
    @Override
    protected void jTFBuscaKeyReleased(java.awt.event.KeyEvent evt) {                                    
        // TODO add your handling code here:
        super.jTFBuscaKeyReleased(evt);
        this.jButtonVisualizarMultas.setEnabled(false);
        
        ArrayList<Automovel> automovelList;
        
        Automovel automovel = new Automovel();
        automovel.setRenavam( super.jTFBusca.getText() );
        automovelList = AutomovelController.buscarAutomovel(automovel, "RENAVAM_RESTRITO");
        
        if(automovel.isError())
        {
            Aviso.showError("O(s) seguinte(s) erro(s) foi(ram) encontrado(s):\n" + 
                    automovel.getMessage());
        }
        else if (automovelList == null)
            return;
        
        preencherPesquisa(automovelList);
    } 
    
    private void preencherPesquisa(ArrayList<Automovel> automovelList) {
        DefaultTableModel tableModel = (DefaultTableModel) super.jTBBuscaRapida.getModel();
        
        tableModel.setRowCount(0);
        if(automovelList == null) return;
        
        for(Automovel automovel : automovelList ) if(automovel != null) {
            String nome;
            if(automovel.getProprietario() == null) 
                nome = "Sem proprietário.";
            else 
                nome = automovel.getProprietario().getNome();
            
            tableModel.addRow(new Object[] { 
                nome,
                automovel.getRenavam(),
                automovel.getMarca(),
                automovel.getModelo(),
                automovel.getCor(),
                automovel.getPlaca()
            });
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
        jPManter = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButtonVisualizarMultas = new javax.swing.JButton();

        setSize(new java.awt.Dimension(650, 185));

        jPManter.setPreferredSize(new java.awt.Dimension(650, 200));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButtonVisualizarMultas.setText("Visualizar multas");
        jButtonVisualizarMultas.setEnabled(false);
        jButtonVisualizarMultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisualizarMultasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(450, Short.MAX_VALUE)
                .addComponent(jButtonVisualizarMultas)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVisualizarMultas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPManterLayout = new javax.swing.GroupLayout(jPManter);
        jPManter.setLayout(jPManterLayout);
        jPManterLayout.setHorizontalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPManterLayout.setVerticalGroup(
            jPManterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPManter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVisualizarMultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisualizarMultasActionPerformed
        JOptionPane.showMessageDialog(this, "esta interface será montada na sprint 3");
    }//GEN-LAST:event_jButtonVisualizarMultasActionPerformed

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
            java.util.logging.Logger.getLogger(FormVisualizarAutomovelOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarAutomovelOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarAutomovelOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarAutomovelOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
            new FormVisualizarAutomovelOld().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGStatus;
    private javax.swing.JButton jButtonVisualizarMultas;
    private javax.swing.JPanel jPManter;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the titular
     */
    public Pessoa getTitular() {
        return proprietario;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(Pessoa titular) {
        this.proprietario = titular;
    }
}

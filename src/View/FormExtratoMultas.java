/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Addons.Aviso;
import Controller.MultaController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Period;
import valueObject.Automovel;
import valueObject.Autuacao;
import valueObject.Multa;
import valueObject.Pessoa;

/**
 *
 * @author cristhian
 */
public class FormExtratoMultas extends javax.swing.JFrame {

    protected ArrayList<Multa> multaList = new ArrayList<>();
    
    private static FormExtratoMultas formLogar;

    public static FormExtratoMultas getForm(FormVisualizarMulta formPrincipal) {
        if (formLogar == null) {
            formLogar = new FormExtratoMultas(formPrincipal);
        }
        return formLogar;
    }

    private final FormVisualizarMulta formPrincipal;

    /**
     * Creates new form FormLogar
     */
    private FormExtratoMultas(FormVisualizarMulta formPrincipal) {
        initComponents();
        this.formPrincipal = formPrincipal;

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                FormPrincipal.isShowingModal = false;
            }
        });
        
        
        
    }

    private FormExtratoMultas() {
        initComponents();
        this.formPrincipal = null;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //FormPrincipal.isShowingModal = false;
            }
        });
        
        
        //this.multaList = MultaController.buscarMulta(new Multa(), "NENHUMA");
        //inicializarPainel(); // Apagar essa linha
    }
    
    public void abre() {
        limpa();
        inicializarPainel();
        this.setVisible(true);
    }
    
    private void limpa() {
        jEPMultas.setContentType("text/html");
        jEPMultas.setText("");
    }
    
    
    private void inicializarPainel() {
    
        
        jEPMultas.setContentType("text/html");
        
        String HTML = "";
        
        HTMLEditorKit editorKit = (HTMLEditorKit) jEPMultas.getEditorKit();
        StyleSheet ss = new StyleSheet();
        Enumeration e = editorKit.getStyleSheet().getStyleNames();
        while (e.hasMoreElements()) {
           ss.addRule(e.nextElement().toString());
        }
        ss.addRule("table {font-family: Latin Modern Math, LM Math; font-size: 10px; align: right; width: 100%}");
        ss.addRule("th {font-family: Latin Modern Math, LM Math; font-size: 10px; font-weight:bold; text-align: center; outline-width: 0px; margin: 0px;  border-width: 1px; border-style: solid; border-color: #000000;}");
        ss.addRule("td {font-family: Latin Modern Math, LM Math; font-size: 10px; padding: 3px; margin: 0px; border-width: 1px; border-style: solid; border-color: #000000;}");
        jEPMultas.setDocument(new HTMLDocument(ss));
        
        
        if(multaList == null || multaList.isEmpty()) {
            Aviso.showError("Nenhuma multa selecionada.");
            return;
        }
        
        multaList.sort(new Multa());

        int idAtual;
        int idPassado = -1;
        int rowCount = 1;
        double total = 0;
        
        HTML = HTML.concat("<table>");
        for ( Multa multaVO : multaList ) {
            Pessoa pessoaResponsavel;
                if( multaVO.getPessoa() == null )
                    pessoaResponsavel = multaVO.getCarteira().getTitular(); 
                else
                    pessoaResponsavel = multaVO.getPessoa();
                
            idAtual = pessoaResponsavel.getIdPessoa();
            
            
            
            if(idAtual != idPassado)
            {   
                if(idPassado != -1) {
                   HTML = totalMultaPessoa(HTML, total );
                }
                rowCount = 1; total = 0;
                HTML = HTML.concat("<tr style=\"background-color:#FFFFFF\"></tr>" );
            
                HTML = HTML.concat("<tr style=\"background-color:#0080FF\">" +
                                        "<th width=\"100%\" colspan=\"7\">" + pessoaResponsavel.getNome() + "</th>"+
                                    "</tr>");
                
                HTML = HTML.concat( "<tr style= \"background-color:#3399FF\" >" +
                                        "<th > Placa </th>"+
                                        "<th > Infração </th>"+
                                        "<th > Pontuação </th>"+
                                        "<th > Data da Ocorrência </th>"+
                                        "<th > Atraso </th>"+
                                        "<th > Valor Original </th>"+
                                        "<th > Valor Final </th>"+
                                    "</tr>"
                                    );
            }
            
            Automovel auto = multaVO.getAutomovel();
            Autuacao autuacao = multaVO.getAutuacao();

            
            Date emissao = multaVO.getDataEmissao();
            boolean temAtraso;
            
            DateTime dataInicio = new DateTime( emissao ); 
            DateTime dataFinal = new DateTime(); 
            
            Days dias = Days.daysBetween(dataInicio, dataFinal); 
            
            temAtraso = dias.getDays() > autuacao.getPrazo();

            SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy"); 
            String date = formataData.format( emissao );
            
            double custoReal = MultaController.calcularMulta(multaVO, temAtraso);
            total += custoReal; 
            
            HTML = HTML.concat( "<tr style= " 
                    + ((rowCount%2==0)? "\"background-color:#99CCFF\" " : 
                                        "\"background-color:#CCE5FF\" " )  + ">" +
                                        "<th >" + auto.getPlaca() + "</th>"+
                                        "<th >" + autuacao.getTitulo() + "</th>"+
                                        "<th >" + autuacao.getPontuacao() + "</th>"+
                                        "<th >" + date + "</th>"+
                                        "<th >" + ((temAtraso)? "Atrasado!": "OK!") + "</th>"+
                                        "<th >" + "R$ " + autuacao.getCusto() + "</th>"+
                                        "<th >" + "R$ " + custoReal + "</th>"+
                                "</tr>"
                                );

            
            idPassado = idAtual;
            rowCount++;
        }
        
        HTML = totalMultaPessoa(HTML, total );
        HTML = HTML.concat("</table>");
        
        jEPMultas.setText(HTML);
                
    }
    
    private String totalMultaPessoa( String HTML, double total )
    {
        return HTML.concat("<tr style=\"background-color:#66B2FF\">" +
                                "<th class=\"total\" width=\"100%\" colspan=\"5\"> Total </th>"+
                                "<th class=\"totalSum\" width=\"100%\" colspan=\"2\">" + "R$ " + total + "</th>"+
                            "</tr>");
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonPagar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEPMultas = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButtonPagar.setText("Pagar");
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jEPMultas.setEditable(false);
        jScrollPane3.setViewportView(jEPMultas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonPagar)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPagar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
        
        if(this.multaList.isEmpty())
            Aviso.showError("Nenhuma Multa Secionada");
        
        Date hoje = new Date();
        
        for(Multa multa: this.multaList)
        {
            multa.setDataPagamento(hoje);
            MultaController.alterarMulta(multa);
            
            if(multa.isError()) {
                Aviso.showError(multa.getMessage());
                this.dispose();
            }
        }
        
        Aviso.showInformation("Multa(s) com sucesso!");
        this.limpa();
        multaList.clear();
        
        this.dispose();
        /*if ((jTextFieldLogin.getText().equals("cristhian")
                || jTextFieldLogin.getText().equals("lucas"))
                && String.valueOf(jTextFieldSenha.getPassword()).equals("detran")) {
            JOptionPane.showMessageDialog(this, "Logado com sucesso.");
            formPrincipal.ativaAdm();
            FormPrincipal.isShowingModal = false;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login ou senha incorreto.");
        }*/
    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        FormPrincipal.isShowingModal = false;
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JEditorPane jEPMultas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    /**
     * @param multaList the multaList to set
     */
    public void setMultaList(ArrayList<Multa> multaList) {
        this.multaList = multaList;
    }
    
    public ArrayList<Multa> getMultaList() {
        return this.multaList;
    }
    // apagar
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
            new FormExtratoMultas().setVisible(true);
        });
    }
}

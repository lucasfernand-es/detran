/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

import java.util.Comparator;
import java.util.Date;

public class Multa implements Comparator<Multa>, Comparable<Multa>{

    private Date dataEmissao;
    private float taxaAcrescimo;
    private Date dataPagamento;
    private Automovel automovel;
    private Pessoa pessoa;
    private Carteira carteira;
    private Autuacao autuacao;

    // ID deste objeto no banco de dados
    private int idMulta;

    /*
     * Ajuda MVC
     * Os atributos abaixo contribuir para o controle das opera��es 
     * envolvendo os objetos desta classe.
     */
    // Quando uma opera��o envolvendo este objeto der erro em tempo de execu��o, 
    // este atributo ser� acionado
    private boolean error = false;
    // Especifica��o do erro
    private String message;

    public boolean estaPago() {
        return this.dataPagamento != null;
    }
    
    public Multa(Date dataEmissao, float taxaAcrescimo, Date dataPagamento,
            Automovel automovel, Pessoa pessoa, Carteira carteira,
            Autuacao autuacao, int idMulta) {
        this.dataEmissao = dataEmissao;
        this.taxaAcrescimo = taxaAcrescimo;
        this.dataPagamento = dataPagamento;
        this.automovel = automovel;
        this.pessoa = pessoa;
        this.carteira = carteira;
        this.autuacao = autuacao;
        this.idMulta = idMulta;
    }
    public Multa() {
    }
    
    @Override
    public String toString() {
        return "multa: " + this.idMulta + " ao automovel: " + this.automovel.getIdAutomovel();
    }
    

    public String showMulta() {
        return  "Multa: " + this.idMulta + 
               " Autom�vel: " + this.automovel.getIdAutomovel() +
               " Carteira: " + this.carteira +
               " Pessoa: " + this.pessoa;
    }

    /**
     * @return the dataEmissao
     */
    public Date getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return the taxaAcrescimo
     */
    public float getTaxaAcrescimo() {
        return taxaAcrescimo;
    }

    /**
     * @param taxaAcrescimo the taxaAcrescimo to set
     */
    public void setTaxaAcrescimo(float taxaAcrescimo) {
        this.taxaAcrescimo = taxaAcrescimo;
    }

    /**
     * @return the dataPagamento
     */
    public Date getDataPagamento() {
        return dataPagamento;
    }

    /**
     * @param dataPagamento the dataPagamento to set
     */
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * @return the automovel
     */
    public Automovel getAutomovel() {
        return automovel;
    }

    /**
     * @param automovel the automovel to set
     */
    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the carteira
     */
    public Carteira getCarteira() {
        return carteira;
    }

    /**
     * @param carteira the carteira to set
     */
    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    /**
     * @return the autuacao
     */
    public Autuacao getAutuacao() {
        return autuacao;
    }

    /**
     * @param autuacao the autuacao to set
     */
    public void setAutuacao(Autuacao autuacao) {
        this.autuacao = autuacao;
    }

    /**
     * @return the idMulta
     */
    public int getIdMulta() {
        return idMulta;
    }

    /**
     * @param idMulta the idMulta to set
     */
    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    /**
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int compare(Multa m1, Multa m2) {
        
        Pessoa p1, p2;
        
        if ( m1.getPessoa() == null )
            p1 = m1.getCarteira().getTitular();
        else
            p1 = m1.getPessoa();
    
        if ( m2.getPessoa() == null )
            p2 = m2.getCarteira().getTitular();
        else
            p2 = m2.getPessoa();
        
        if( p1.getIdPessoa() < p2.getIdPessoa() )
            return -1;
        else if ( p1.getIdPessoa() > p2.getIdPessoa() )
            return 1;
        else
            return 0;
        
        
    }

    @Override
    public int compareTo(Multa m) {
        Pessoa p1, p2;
        
        if ( this.getPessoa() == null )
            p1 = this.getCarteira().getTitular();
        else
            p1 = this.getPessoa();
    
        if ( m.getPessoa() == null )
            p2 = m.getCarteira().getTitular();
        else
            p2 = m.getPessoa();
        
        if( p1.getIdPessoa() < p2.getIdPessoa() )
            return -1;
        else if ( p1.getIdPessoa() > p2.getIdPessoa() )
            return 1;
        else
            return 0;
    }

}

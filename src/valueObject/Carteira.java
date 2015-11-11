/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

import java.util.Date;

public class Carteira {

	private Date dataVencimento;
	private Date dataEmissao;
	private String nRegistro;
	private boolean permissao;
	private String tipo;
	private Pessoa titular = null;
        private boolean status;

        // ID deste objeto no banco de dados
        private int idCarteira;

        /*
         * Ajuda MVC
         * Os atributos abaixo contribuir para o controle das operações 
         * envolvendo os objetos desta classe.
         */

        // Quando uma operação envolvendo este objeto der erro em tempo de execução, 
        // este atributo será acionado
        private boolean error = false;
        // Especificação do erro
        private String message;
    
    public Carteira(Date dataVencimento, Date dataEmissao, String nRegistro, 
            boolean permissao, String tipo, Pessoa titular, boolean status, int idCarteira) {
        this.dataVencimento = dataVencimento;
	this.dataEmissao = dataEmissao;
	this.nRegistro = nRegistro;
	this.permissao = permissao;
	this.tipo = tipo;
	this.titular = titular;
        this.status = status;
        this.idCarteira = idCarteira;
    }
        
    public Carteira() {
        
    }

    /**
     * @return the dataVencimento
     */
    public Date getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
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
     * @return the nRegistro
     */
    public String getnRegistro() {
        return nRegistro;
    }

    /**
     * @param nRegistro the nRegistro to set
     */
    public void setnRegistro(String nRegistro) {
        this.nRegistro = nRegistro;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the idCarteira
     */
    public int getIdCarteira() {
        return idCarteira;
    }

    /**
     * @param idCarteira the idCarteira to set
     */
    public void setIdCarteira(int idCarteira) {
        this.idCarteira = idCarteira;
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

    /**
     * @return the permissao
     */
    public boolean isPermissao() {
        return permissao;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setPermissao(boolean permissao) {
        this.permissao = permissao;
    }

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

    public String showCarteira() {
        String carteira = dataVencimento + " " + dataEmissao + " " + nRegistro + " " + 
                        permissao + " " + tipo + " " + titular + " " + status + " " + idCarteira;
        return carteira;
    }
    
    public String toString() {
        return this.titular.getNome() + " - " + this.nRegistro;
    }

}

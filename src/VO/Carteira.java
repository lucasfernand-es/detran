/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.util.Date;

public class Carteira {

	private Date dataVencimento;
	private Date dataEmissao;
	private String nRegistro;
	private String permissao;
	private String tipo;
	private Pessoa pessoa;
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
     * @return the permissao
     */
    public String getPermissao() {
        return permissao;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setPermissao(String permissao) {
        this.permissao = permissao;
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

}

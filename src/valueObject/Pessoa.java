/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

import java.util.ArrayList;
import java.util.Date;

public class Pessoa {

	private String nome;
	private String cpf;
	private String rg;
	private String orgaoEmissor;
	private String rgEstado;
	private Date dataNascimento;
	private String logradouro;
	private String numeroLogradouro;
	private String complementoLogradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String nomeMae;
	private String nomePai;
        private boolean status;
        
        private ArrayList<Carteira> carteiras = new ArrayList<>();
        private ArrayList<Automovel> automoveis = new ArrayList<>();

        // ID deste objeto no banco de dados
        private int idPessoa;

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

    public Pessoa (String nome,	String cpf, String rg,	String orgaoEmissor, 
            String rgEstado, Date dataNascimento, String logradouro, 
            String numeroLogradouro, String complementoLogradouro, String bairro, 
            String cidade, String estado, String cep, String nomeMae, String nomePai,  
            boolean status, int idPessoa) {
            
            this.nome =  nome;
            this.cpf =  cpf;
            this.rg =  rg;
            this.orgaoEmissor =  orgaoEmissor;
            this.rgEstado =  rgEstado;
            this.dataNascimento =  dataNascimento;
            this.logradouro =  logradouro;
            this.numeroLogradouro =  numeroLogradouro;
            this.complementoLogradouro =  complementoLogradouro;
            this.bairro =  bairro;
            this.cidade =  cidade;
            this.estado =  estado;
            this.cep =  cep;
            this.nomeMae =  nomeMae;
            this.nomePai =  nomePai;
            this.status =  status;
            this.idPessoa =  idPessoa;
    }
    public Pessoa () {
        
    }
    
    public String showPessoa() {
        String pessoa = this.nome + " " + this.cpf + " " + this.rg + " " + this.orgaoEmissor + " " +
                this.rgEstado + " " + this.dataNascimento + " " + this.logradouro + " " + this.numeroLogradouro + " " +
                this.complementoLogradouro + " " + this.bairro + " " + this.cidade + " " + this.estado + " " +
                this.cep + " " + this.nomeMae + " " + this.nomePai + " " + this.status + " " +
                this.idPessoa;
        return pessoa;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the orgaoEmissor
     */
    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    /**
     * @param orgaoEmissor the orgaoEmissor to set
     */
    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    /**
     * @return the rgEstado
     */
    public String getRgEstado() {
        return rgEstado;
    }

    /**
     * @param rgEstado the rgEstado to set
     */
    public void setRgEstado(String rgEstado) {
        this.rgEstado = rgEstado;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the numeroLogradouro
     */
    public String getNumeroLogradouro() {
        return numeroLogradouro;
    }

    /**
     * @param numeroLogradouro the numeroLogradouro to set
     */
    public void setNumeroLogradouro(String numeroLogradouro) {
        this.numeroLogradouro = numeroLogradouro;
    }

    /**
     * @return the complementoLogradouro
     */
    public String getComplementoLogradouro() {
        return complementoLogradouro;
    }

    /**
     * @param complementoLogradouro the complementoLogradouro to set
     */
    public void setComplementoLogradouro(String complementoLogradouro) {
        this.complementoLogradouro = complementoLogradouro;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the nomeMae
     */
    public String getNomeMae() {
        return nomeMae;
    }

    /**
     * @param nomeMae the nomeMae to set
     */
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    /**
     * @return the nomePai
     */
    public String getNomePai() {
        return nomePai;
    }

    /**
     * @param nomePai the nomePai to set
     */
    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
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
     * @return the idPessoa
     */
    public int getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
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
     * @return the carteiras
     */
    public ArrayList<Carteira> getCarteiras() {
        return carteiras;
    }

    /**
     * @param carteiras the carteiras to set
     */
    public void setCarteiras(ArrayList<Carteira> carteiras) {
        this.carteiras = carteiras;
    }

        @Override
    public String toString() {
        return this.nome + " " + this.cpf;
    }

    /**
     * @return the automoveis
     */
    public ArrayList<Automovel> getAutomoveis() {
        return automoveis;
    }

    /**
     * @param automoveis the automoveis to set
     */
    public void setAutomoveis(ArrayList<Automovel> automoveis) {
        this.automoveis = automoveis;
    }
}

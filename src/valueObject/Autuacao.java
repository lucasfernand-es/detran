/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

public class Autuacao {
    
    private String titulo;
    private String descricao;
    private int pontuacao;
    private double custo;
    private int prazo;
    
    // checagem de custo
    private String custo_str;
    
    // ID deste objeto no banco de dados
    private int idAutuacao;

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
    
    public Autuacao(String titulo, String descricao, int pontuacao,
            double custo, int prazo, int idAutuacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.pontuacao = pontuacao;
        this.custo = custo;
        this.prazo = prazo;
        this.idAutuacao = idAutuacao;
    }
    
    public Autuacao() {
    }
        
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the pontuacao
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao the pontuacao to set
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * @return the custo
     */
    public double getCusto() {
        return custo;
    }

    /**
     * @param custo the custo to set
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * @return the prazo
     */
    public int getPrazo() {
        return prazo;
    }

    /**
     * @param prazo the prazo to set
     */
    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    /**
     * @return the idAutuacao
     */
    public int getIdAutuacao() {
        return idAutuacao;
    }

    /**
     * @param idAutuacao the idAutuacao to set
     */
    public void setIdAutuacao(int idAutuacao) {
        this.idAutuacao = idAutuacao;
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
    
    public String getCustoStr() {
        return this.custo_str;
    }
    public void setCustoStr(String custo_str) {
        this.custo_str = custo_str;
    }
    
    public String toString() {
        return this.titulo + " - " + this.custo;
    }

}

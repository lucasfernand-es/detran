package valueObject;

public class Automovel {

        private String renavam;
        private String marca;
        private String modelo;
        private String cor;
        private String placa;
        private String chassi;
        private Pessoa proprietario;
        private String ano;
        private boolean status;

        // ID deste objeto no banco de dados
        private int idAutomovel;

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

    
    public Automovel(String renavam, String marca, String modelo, String cor,
            String placa, String chassi, Pessoa proprietario, String ano, 
            boolean status, int idAutomovel) {
        this.renavam = renavam;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.chassi = chassi;
        this.proprietario = proprietario;
        this.ano = ano;
        this.status = status;
        this.idAutomovel = idAutomovel;
    }
    public Automovel() {
    }
    
    @Override
    public String toString() {
        return "Renavam: " + this.renavam;
    }
    
    /**
     * @return the renavam
     */
    public String getRenavam() {
        return renavam;
    }

    /**
     * @param renavam the renavam to set
     */
    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the chassi
     */
    public String getChassi() {
        return chassi;
    }

    /**
     * @param chassi the chassi to set
     */
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getProprietario() {
        return proprietario;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * @return the idAutomovel
     */
    public int getIdAutomovel() {
        return idAutomovel;
    }

    /**
     * @param idAutomovel the idAutomovel to set
     */
    public void setIdAutomovel(int idAutomovel) {
        this.idAutomovel = idAutomovel;
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

}

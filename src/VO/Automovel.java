/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Automovel {
    
    
    /*
        informar os dados do automóvel 
        (RENAVAM, marca, modelo, cor, place, 
    chassi, proprietário (nome), ano)
    */
    private String renavam;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private String chassi;
    private Date ano;
    private Pessoa proprietario;

    
    
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
    public Date getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Date ano) {
        this.ano = ano;
    }

    /**
     * @return the proprietario
     */
    public Pessoa getProprietario() {
        return proprietario;
    }

    /**
     * @param proprietario the proprietario to set
     */
    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }
    
    
    
}

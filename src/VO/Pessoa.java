/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Pessoa {
    
    /*
        informar os dados da pessoa (nome, rg(número, orgão emissor e estado), 
        cpf, data de nascimento, endereço, carteira, filiação)
    */
    
    String nome;
    String rg;
    String orgaoEmissorRG;
    String cpf;
    // Endereço
    ArrayList<Carteira> carteiras;
    String nomePai; // Filiação
    String nomeMae; // Filiação
    
}

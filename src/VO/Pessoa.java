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
        informar os dados da pessoa (nome, rg(n�mero, org�o emissor e estado), 
        cpf, data de nascimento, endere�o, carteira, filia��o)
    */
    
    String nome;
    String rg;
    String orgaoEmissorRG;
    String cpf;
    // Endere�o
    ArrayList<Carteira> carteiras;
    String nomePai; // Filia��o
    String nomeMae; // Filia��o
    
}

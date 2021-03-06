/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import valueObject.Automovel;
import valueObject.Autuacao;
import valueObject.Carteira;
import valueObject.Multa;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class Util {

    public static Pessoa criarPessoa(ResultSet rs) {
        Pessoa pessoa;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            String rg = rs.getString("rg");
            String orgaoEmissor = rs.getString("orgaoEmissor");
            String rgEstado = rs.getString("rgEstado");
            Date dataNascimento = rs.getDate("dataNascimento");
            String logradouro = rs.getString("logradouro");
            String numeroLogradouro = rs.getString("numeroLogradouro");
            String complementoLogradouro = rs.getString("complementoLogradouro");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String cep = rs.getString("cep");
            String nomeMae = rs.getString("nomeMae");
            String nomePai = rs.getString("nomePai");
            boolean status = rs.getBoolean("status");
            int idPessoa = rs.getInt("idPessoa");
            
            int automoveis = Util.getAutomoveisCount(idPessoa);
            int carteiras = Util.getCarteiras(idPessoa);
            
            pessoa = new Pessoa(nome, cpf, rg, orgaoEmissor, rgEstado, 
                    dataNascimento, logradouro,  numeroLogradouro, complementoLogradouro, 
                    bairro, cidade, estado, cep, nomeMae, nomePai, status, idPessoa);
            pessoa.setAutomoveis(automoveis);
            pessoa.setCarteiras(carteiras);
            return pessoa;
        }
        catch(Exception e) {
            pessoa = new Pessoa();
            pessoa.setError(true);
            pessoa.setMessage(e.getMessage());
            return null;
        }
    }

    public static Carteira criarCarteira(ResultSet rs) {
        Carteira carteira;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            Date dataVencimento = rs.getDate("dataVencimento");
            Date dataEmissao = rs.getDate("dataEmissao");
            String nRegistro = rs.getString("nRegistro");
            boolean permissao = rs.getBoolean("permissao");
            String tipo = rs.getString("tipo");
            int idPessoa = rs.getInt("idPessoa");
            Pessoa pessoaTitular = Util.getPessoa(idPessoa);
            boolean status = rs.getBoolean("status");
            int idCarteira = rs.getInt("idCarteira");
        
            carteira =  new Carteira(dataVencimento, dataEmissao, nRegistro, 
                permissao, tipo, pessoaTitular, status, idCarteira);
            return carteira;
        }
        catch(Exception e) {
            carteira = new Carteira();
            carteira.setError(true);
            carteira.setMessage(e.getMessage());
            return null;
        }
    }

    private static Pessoa getPessoa(int idPessoa) {
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(idPessoa);
        
        ArrayList<Pessoa> newList = PessoaController.buscarPessoa(pessoa, "UNIQUE_ID");

        pessoa = (newList == null || newList.isEmpty())? null: newList.get(0);

        return pessoa;
    }
        
    
    private static Automovel getAutomovel(int idAutomovel) {
        Automovel automovel = new Automovel();
        automovel.setIdAutomovel(idAutomovel);
        
        automovel = AutomovelController.buscarAutomovel(automovel, "ID").get(0);
        return automovel;
    }
    
    public static Automovel criarAutomovel(ResultSet rs) {
        Automovel automovel;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            String renavam = rs.getString("renavam");
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");
            String cor = rs.getString("cor");
            String placa = rs.getString("placa");
            String chassi = rs.getString("chassi");
            int idProprietario = rs.getInt("idPessoa");
            Pessoa proprietario = Util.getPessoa(idProprietario);
            String ano = rs.getString("ano");
            boolean status = rs.getBoolean("status");
            int idAutomovel = rs.getInt("idAutomovel");
            
            automovel = new Automovel(renavam, marca, modelo, cor, placa,
                    chassi, proprietario, ano, status, idAutomovel);
            return automovel;
        }
        catch(Exception e) {
            automovel = new Automovel();
            automovel.setError(true);
            automovel.setMessage(e.getMessage());
            return null;
        }
    }
    public static Multa criarMulta(ResultSet rs) {
        Multa multa;
        try {
            Date dataEmissao;
            try {
                dataEmissao = rs.getDate("dataEmissao");
            } catch(Exception e) {
                dataEmissao = null;
            }
            float taxaAcrescimo = rs.getFloat("taxaAcrescimo");
            Date dataPagamento;
            try {
                dataPagamento = rs.getDate("dataPagamento");
            } catch(Exception e) {
                dataPagamento = null;
            }
            int idAutomovel = rs.getInt("idAutomovel");
            //System.out.println(idAutomovel + "idAutomovel");
            Automovel automovel = Util.getAutomovel(idAutomovel);
            
            int idPessoa = rs.getInt("idPessoa");
            //System.out.println(idPessoa + "idPessoa");
            Pessoa pessoa = Util.getPessoa(idPessoa); // Pessoa pessoa = null;
            
            int idCarteira = rs.getInt("idCarteira");
            //System.out.println(idCarteira + "idCarteira");
            Carteira carteira = Util.getCarteira(idCarteira); // Carteira carteira = null; 
            
            //System.out.println(idCarteira + " depois");
            
            int idAutuacao = rs.getInt("idAutuacao");
            //System.out.println(idAutuacao + "idAutuacao");
            Autuacao autuacao = Util.getAutuacao(idAutuacao); // Autuacao autuacao = null; 
            
            int idMulta = rs.getInt("idMulta");
            
            multa = new Multa(
                    dataEmissao, taxaAcrescimo, dataPagamento, automovel, pessoa, carteira, autuacao, idMulta
            );
            return multa;
        } catch(Exception e) {
            multa = new Multa();
            multa.setError(true);
            multa.setMessage(e.getMessage());
            return null;
        }
    }
    public static int multasPendentesCount(int idAutomovel) {
        Multa multa = new Multa();
        Automovel automovel = new Automovel();
        automovel.setIdAutomovel(idAutomovel);
        multa.setAutomovel(automovel);
        
        ArrayList<Multa> list = MultaController.buscarMulta(multa, "IDAUTOMOVEL");
        
        int count = 0;
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getDataPagamento() == null) {
                count++;
            }
        }
        return count;
    }

    private static int getCarteiras(int idPessoa) {

        Carteira carteira = new Carteira();
        Pessoa pessoa = new Pessoa();
        
        pessoa.setIdPessoa(idPessoa);
        carteira.setTitular(pessoa);
        
        
       ArrayList<Carteira> carteiras = CarteiraController.buscarCarteira(carteira, "NCARTEIRAS");
       
       if (carteiras == null) {
            return 0;
        }
        else {
            int nCarteiras = carteiras.size();
            return nCarteiras;
        }
    }

    private static int getAutomoveisCount(int idPessoa) {
        Automovel automovel = new Automovel();
        Pessoa pessoa = new Pessoa();
        
        pessoa.setIdPessoa(idPessoa);
        automovel.setProprietario(pessoa);
        
        ArrayList<Automovel> automoveis = AutomovelController.buscarAutomovel(automovel, "NAUTOMOVEIS");
        if (automoveis == null) {
            return 0;
        }
        else {
            int nAutomoveis = automoveis.size();
            return nAutomoveis;
        }
    }

    public static int multasPendentesCount(Pessoa pessoa) {
        
        Automovel automovel = new Automovel();
        Carteira carteira = new Carteira();
        
        automovel.setProprietario(pessoa);
        carteira.setTitular(pessoa);
        
        Multa multa = new Multa();
        multa.setPessoa(pessoa);
        multa.setCarteira(carteira);
        multa.setAutomovel(automovel);
        
        ArrayList<Multa> list = MultaController.buscarMulta(multa, "MULTAPENDENTE");

        if (list == null)
            return -1;
        int count = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getDataPagamento() == null) {
                count++;
            }
        }
        return count;
    }
    
    public static int multasPendentesCount(Carteira carteira) {
        Multa multa = new Multa();
        multa.setCarteira(carteira);
        
        ArrayList<Multa> list = MultaController.buscarMulta(multa, "IDCARTEIRA");
        
        if (list == null)
            return 0;
        int count = 0;
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getDataPagamento() == null) {
                count++;
            }
        }
        return count;
    }
    public static int multasRelacionadasCount(int idAutuacao) {
        Multa multa = new Multa();
        Autuacao autuacao = new Autuacao();
        autuacao.setIdAutuacao(idAutuacao);
        multa.setAutuacao(autuacao);
        
        ArrayList<Multa> list = MultaController.buscarMulta(multa, "IDAUTUACAO");
        
        if(list != null)
            return list.size();
        else
            return 0;
    }
    
    public static Autuacao criarAutuacao(ResultSet rs) {
        Autuacao autuacao;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            String titulo = rs.getString("titulo");
            String descricao = rs.getString("descricao");
            int pontuacao = rs.getInt("pontuacao");
            double custo = rs.getDouble("custo");
            int prazo = rs.getInt("prazo");
            int idAutuacao = rs.getInt("idAutuacao");
            
            autuacao = new Autuacao(titulo, descricao, pontuacao, 
                    custo, prazo, idAutuacao);
            return autuacao;
        }
        catch(Exception e) {
            autuacao = new Autuacao();
            autuacao.setError(true);
            autuacao.setMessage(e.getMessage());
            return null;
        }
    }

    private static Carteira getCarteira(int idCarteira) {
        Carteira carteira = new Carteira();
        carteira.setIdCarteira(idCarteira);
        
        ArrayList<Carteira> newList = CarteiraController.buscarCarteira(carteira, "UNIQUE_ID");
        
        carteira = (newList == null || newList.isEmpty())? null: newList.get(0);

        return carteira;
    }
    
    private static Autuacao getAutuacao(int idAutuacao) {
        Autuacao autuacao = new Autuacao();
        autuacao.setIdAutuacao(idAutuacao);
        
        autuacao = AutuacaoController.buscaAutuacaoID(autuacao);

        
        return autuacao;
    }
    
}

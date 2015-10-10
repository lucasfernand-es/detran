/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Addons;


import valueObject.Pessoa;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author LucasEmanuel
 */
public final class CepWebService { 
    
        
    
    public CepWebService () {
        
    }
    

    private static void criarEndereco(Endereco endereco) {
        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + endereco.getCep() + "&formato=xml");

            Document document = getDocumento(url);

            Element root = document.getRootElement();

            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                
                if (element.getQualifiedName().equals("uf"))
                    endereco.setUf(element.getText());                
                
                if (element.getQualifiedName().equals("cidade"))
                    endereco.setCidade(element.getText());                
                
                if (element.getQualifiedName().equals("bairro"))
                    endereco.setBairro(element.getText());                
                
                if (element.getQualifiedName().equals("tipo_logradouro"))
                    endereco.setTipo_logradouro(element.getText());                
                
                if (element.getQualifiedName().equals("logradouro"))
                    endereco.setLogradouro(element.getText());                
                
                if (element.getQualifiedName().equals("resultado"))
                    endereco.setResultado(Integer.parseInt(element.getText()));                
                
                if (element.getQualifiedName().equals("resultado_txt"))
                    endereco.setResultado_txt(element.getText());                                
            }
            
            endereco.setError(false);
        }
        catch (MalformedURLException | DocumentException | NumberFormatException ex) {
            endereco.setError(true);
            endereco.setMessage(ex.getMessage());
        }
    }

    private static Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    } 
    
    public void buscaCEP (Pessoa pessoa) {
        try {
           
            Endereco endereco = new Endereco();
            String cep = pessoa.getCep();  
            endereco.setCep(cep);
            
            
            CepWebService.criarEndereco(endereco);
                
            if (endereco.getResultado() == 1) {
                pessoa.setLogradouro(endereco.getTipo_logradouro() + " " + endereco.getLogradouro());
                pessoa.setBairro(endereco.getBairro());
                pessoa.setCidade(endereco.getCidade());
                pessoa.setEstado(endereco.getUf());
                
                /*
                System.out.println(endereco.getTipo_logradouro() + " " + endereco.getLogradouro());
                System.out.println(endereco.getCidade());
                System.out.println(endereco.getBairro());
                System.out.println(endereco.getUf());
                System.out.println(endereco.getResultado());
                System.out.println(endereco.getResultado_txt());
                */
                
                pessoa.setError(false);
                pessoa.setMessage("");
            }
            else {
                pessoa.setError(true);
                pessoa.setMessage("Servidor não está respondendo.");
            }            
        }
        catch (Exception ex) {
            pessoa.setError(true);
            pessoa.setMessage(ex.getMessage());
        }  
    } 
    
    public class Endereco {
        private String uf = "";
        private String cidade = "";
        private String bairro = "";
        private String tipo_logradouro = "";
        private String logradouro = "";
        private String cep = "";
        
        private int resultado = 0;
        private String resultado_txt = "";  
        
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
         * @return the uf
         */
        public String getUf() {
            return uf;
        }

        /**
         * @param uf the uf to set
         */
        public void setUf(String uf) {
            this.uf = uf;
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
         * @return the tipo_logradouro
         */
        public String getTipo_logradouro() {
            return tipo_logradouro;
        }

        /**
         * @param tipo_logradouro the tipo_logradouro to set
         */
        public void setTipo_logradouro(String tipo_logradouro) {
            this.tipo_logradouro = tipo_logradouro;
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
        

        public int getResultado() {
            return resultado;
        }

        public void setResultado(int resultado) {
            this.resultado = resultado;
        }

        public String getResultado_txt() {
            return resultado_txt;
        }

        public void setResultado_txt(String resultado_txt) {
            this.resultado_txt = resultado_txt;
        }
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class ConexaoMySql {
    
    //futura fábrica de conexão
    public static Connection conexao(){
        //define o caminho do Driver
        String driver = "com.mysql.cj.jdbc.Driver";
        //url do banco de dados
        //localhost - IP da máquina onde roda o BD
        //3306 - porta onde roda o MySQL
        //ciec - nome do schema (database)
        //?useTimezone=true&serverTimezone=UTC
        String url = "jdbc:mysql://localhost:3306/ciec?useTimezone=true&serverTimezone=UTC";        
        //nome e senha de usuário
        String user = "root";
        String pass = "";
        
        //carregar o driver
        try{
            Class.forName(driver);
            System.out.println("Driver carregado!");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        //conectando ao banco de dados
        try {            
            Connection conexao =
               DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado com sucesso!");
            //retorna a conexão ao BD
            return conexao;
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;       
    }
    
}



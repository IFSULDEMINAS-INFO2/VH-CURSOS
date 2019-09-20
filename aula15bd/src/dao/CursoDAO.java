/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Curso;
import conexao.ConexaoMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsável pelo conteúdo SQL para tabela Curso
 * CRUD - Create, Recuperate, Update e Delete
 * @author bruno
 */
public class CursoDAO {
    //realiza insert no BD
    public boolean gravarCurso(Curso c){
        //criar conexão com BD usando método estático
        Connection conn = ConexaoMySql.conexao();
        //instrução para o BD
        String sql = "INSERT INTO cursos(nomeCurso,"
                + "descricao) VALUES (?, ?);";
        try {
            //criação do statement de comunicação
            PreparedStatement ps =
                    conn.prepareStatement(sql);
            //definir os valores dos "?"
            ps.setString(1, c.getNome());
            ps.setString(2, c.getDescricao());
            //executar a instrução SQL
            ps.executeUpdate();
            //encerrar a conexão
            ps.close();
            conn.close();
            //retorno de ok
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;        
    }
    
    //recupera todos os cursos cadastrados
     public List<Curso> recuperaTodosCursos(){
         //criar conexao com banco
         Connection conexao = ConexaoMySql.conexao();
         //instrução SQL
         String sql="SELECT *FROM cursos;";
         
        try {
            //stetement conexao
            PreparedStatement ps = conexao.prepareStatement(sql);
            //recebe a tabela de retorno do banco de dados em formato java
            ResultSet rs = ps.executeQuery();
            //criar a lista de retorno
            List<Curso>lista = new ArrayList<>();
            //tratar o retorno do banco 
            while(rs.next()){
               //criar um objeto modelo do tipo do retorno
               Curso c = new Curso();
               c.setIdCurso(rs.getInt(1));
               c.setNome(rs.getString(2));
               c.setDescricao(rs.getString(3));
               //add na lista
               lista.add(c);
            }
            //retornar lista preenchida
            return lista;
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        //retornar null em caso de exceção
        return null;
     }
    
    public List<Curso>pesquisaCursos(String pesquisa){
     Connection conexao = ConexaoMySql.conexao();
         //instrução SQL
     String sql = "SELECT * FROM cursos"+" WHERE nomeCurso LIKE?;";
      
         
        try {
            //stetement conexao
            PreparedStatement ps = conexao.prepareStatement(sql);
            //recebe a tabela de retorno do banco de dados em formato java
            ps.setString(1,"%"+pesquisa+"%");
            ResultSet rs = ps.executeQuery();
            //criar a lista de retorno
            List<Curso>lista = new ArrayList<>();
            //tratar o retorno do banco 
            while(rs.next()){
               //criar um objeto modelo do tipo do retorno
               Curso c = new Curso();
               c.setIdCurso(rs.getInt(1));
               c.setNome(rs.getString(2));
               c.setDescricao(rs.getString(3));
               //add na lista
               lista.add(c);
            }
            //retornar lista preenchida
            return lista;
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        //retornar null em caso de exceção
        return null;
     }
    
}

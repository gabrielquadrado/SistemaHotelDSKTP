package br.com.mvchotel.conexaomysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gabriel Quadrado
 */
public class ConexaoMySQL {
 Connection con;
 public ConexaoMySQL(){
 }
 public Connection AbrirBDConn(){
  try{
   //Class.forName("com.mysql.jdbc.Driver");
   String url = "jdbc:mysql://localhost:3306/hotel";
   con = DriverManager.getConnection(url,"root","lombada");
   System.out.println("Conexao efetuada com sucesso");
   return con;
  }
  catch(SQLException e){
   System.out.println("Erro ao abrir conexao com banco: "+e.getMessage());
   return null;
  }
 }
  public void FecharBDConn(){
 try{
  con.close();
  System.out.println("Conexao finalizada com sucesso");
 }catch(Exception e){
  System.out.println("Erro ao fechar conexao com banco: " + e.getMessage());
 }
  }
}

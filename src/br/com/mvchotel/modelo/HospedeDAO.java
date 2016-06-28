package br.com.mvchotel.modelo;

import br.com.mvchotel.conexaomysql.ConexaoMySQL;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Quadrado
 */
public class HospedeDAO {
    public void CriarHospede(Hospede h) throws IOException{
        ConexaoMySQL banco = new ConexaoMySQL();
        try {
            Connection con = banco.AbrirBDConn();
            String sql = "insert into hospede " +
                    "(id, nome, idade, sexo, status) " +
                    "values (?,?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, null);
            stmt.setString(2, h.getNome());
            stmt.setString(3, Integer.toString(h.getIdade()));
            stmt.setString(4, h.getSexo());
            stmt.setString(5, h.getStatus());
            boolean res = stmt.execute();
            JOptionPane.showMessageDialog(null, (!res)?"Dados inseridos com sucesso!":""+
                        "Os dados não puderam ser inseridos");
            /*try (Statement stmt = ExConn.createStatement()) {
                String sSQL = "INSERT INTO HOSPEDE VALUES (null, '"
                        +h.getNome()+"',"
                        +Integer.toString(h.getIdade())+",'"
                        +h.getSexo()+"','"
                        +h.getStatus()+"');";
                boolean res = stmt.execute(sSQL);
                JOptionPane.showMessageDialog(null, (!res)?"Dados inseridos com sucesso!":""+
                        "Os dados não puderam ser inseridos");
            }
            banco.FecharBDConn();*/
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Os dados não puderam ser inseridos\n"+e.getMessage());
        } finally {banco.FecharBDConn();}
    }
    public String ExibirHospede() throws FileNotFoundException, IOException, SQLException{
        ConexaoMySQL banco = new ConexaoMySQL();
        Statement stmt;
        ResultSet rs;
        String allHosp;
        try (Connection con = banco.AbrirBDConn()) {
            stmt = con.createStatement();
            String sSQL = "SELECT * FROM hospede;";
            rs = stmt.executeQuery(sSQL);
            allHosp = "";
            while(rs.next()){
                allHosp+=rs.getString("id")+";";
                allHosp+=rs.getString("nome")+";";
                allHosp+=rs.getString("idade")+";";
                allHosp+=rs.getString("sexo")+";";
                allHosp+=rs.getString("status")+";";
            }
            banco.FecharBDConn();
        }
        stmt.close();
        rs.close();
        return allHosp;
}
    public void RemoverHospede(String nome) throws IOException, SQLException{
        ConexaoMySQL banco = new ConexaoMySQL();
        Connection ExConn = banco.AbrirBDConn();
        Statement stmt = ExConn.createStatement();
        String sSQL = "DELETE FROM hospede WHERE nome='"+nome+"';";
        stmt.execute(sSQL);
        banco.FecharBDConn();
    }
    public String ContarHospedes() throws FileNotFoundException, IOException, SQLException{
        ConexaoMySQL banco = new ConexaoMySQL();
        Connection ExConn = banco.AbrirBDConn();
        Statement stmt = ExConn.createStatement();
        String sSQL = "SELECT COUNT(*) as qtdd FROM hospede;";
        ResultSet rs = stmt.executeQuery(sSQL);
        rs.next();
        String qtdd = rs.getString("qtdd");
        banco.FecharBDConn();
        return qtdd;
    }
}
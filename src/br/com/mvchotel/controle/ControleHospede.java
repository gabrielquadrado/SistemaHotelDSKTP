package br.com.mvchotel.controle;
import br.com.mvchotel.modelo.Hospede;
import br.com.mvchotel.modelo.HospedeDAO;
import br.com.mvchotel.visao.ExibirIdade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Gabriel Quadrado
 */
public class ControleHospede {
    public void Cadastrar(Hospede h) throws IOException, ClassNotFoundException, SQLException{
        HospedeDAO hd = new HospedeDAO();
        hd.CriarHospede(h);
    }
    public String Exibir() throws IOException, FileNotFoundException, SQLException{
        HospedeDAO hd = new HospedeDAO();
        String allHosp=hd.ExibirHospede();
        return allHosp;
    }
    public void Remover(String nome) throws IOException, SQLException{
        HospedeDAO hd = new HospedeDAO();
        hd.RemoverHospede(nome);
    }
    public String Contar() throws IOException, FileNotFoundException, SQLException {
        HospedeDAO hd = new HospedeDAO();
        return hd.ContarHospedes();
    }
}
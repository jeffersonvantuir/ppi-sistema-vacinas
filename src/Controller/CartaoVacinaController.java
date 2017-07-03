/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CartaoVacina;
import Model.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.VacinaFornecedor;

/**
 *
 * @author Jefferson Vantuir
 */
public class CartaoVacinaController {
    private Connection conn;
    
    public CartaoVacinaController(Connection conn) {
        this.conn = conn;
    }
    
    public String inserirCartaoVacina (CartaoVacina cv){
    String SQL = "INSERT INTO cartao_vacina (n_dose, data, preco, nome_vac, lote_vac, cpf_paciente, cod_funcionario) values(?,?,?,?,?,?,?);";
    try{
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, cv.getN_dose());
        ps.setDate(2, cv.getData_aplicacao());
        ps.setFloat(3, cv.getPreco());
        ps.setString(4, cv.getNome_vac());
        ps.setString(5, cv.getLote_vac());
        ps.setString(6, cv.getCpf_paciente());
        ps.setInt(7, cv.getCod_funcionario());
        if(ps.executeUpdate()>0){
            return "Aplicação Cadastrada com Sucesso!!!";
        }else{
            return "Falha ao Cadastrar Aplicação!!!";
        }
    
    }catch(SQLException e){
        return e.getMessage();
    }

    }
    
    public List<CartaoVacina> mostrarCartaoVacina (String cpf) {
        String SQL="SELECT * FROM cartao_vacina WHERE cpf_paciente LIKE '%"+cpf+"%' ORDER BY data DESC;";
        List<CartaoVacina> listacartao = new ArrayList<CartaoVacina>();
       try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(); 
            if(rs !=null){
                while(rs.next()){
                    CartaoVacina cv = new CartaoVacina();
                    cv.setNome_vac(rs.getString("nome_vac"));
                    cv.setLote_vac(rs.getString("lote_vac"));
                    cv.setN_dose(rs.getInt("n_dose"));
                    cv.setData_aplicacao(rs.getDate("data"));
                    cv.setPreco(rs.getFloat("preco"));
                    cv.setCod_funcionario(rs.getInt("cod_funcionario"));
                    listacartao.add(cv);
                }
            }
            return listacartao;
        } catch (SQLException e) {
            return null;
        }
    }
//    public String alterarCompra (VacinaFornecedor vf){
//    String SQL = "UPDATE vacinas_fornecedores SET nome = ? WHERE cnpj = '"+vf.getCnpj()+"';";
//        try{
//            PreparedStatement ps= conn.prepareStatement(SQL);
//            ps.setString(1, forn.getNomeFornecedor());
//            if(ps.executeUpdate() > 0){
//                return "Fornecedor Alterado com Sucesso!!!";
//            } else {
//                return "Falha ao Alterar Fornecedor!!!";
//            }
//        } catch(SQLException e) {
//            return e.getMessage();
//        }
//    }    
//    public String excluirFornecedor (Fornecedor forn){
//    String SQL = "DELETE FROM fornecedores WHERE cnpj = ?;";
//        try{
//            PreparedStatement ps = conn.prepareStatement(SQL);
//            ps.setString(1, forn.getCnpj());
//            ps.executeUpdate();//podemos retirar este aqui.
//            return "Fornecedor Excluído com Sucesso!!!";
//        } catch(SQLException e) {
//            return e.getMessage();
//        }
//    }
    
    
//    public List<VacinaFornecedor> listarTodasCompras(String texto) {
//        String SQL="SELECT * FROM fornecedores WHERE nome LIKE '%"+texto+"%' ORDER BY cnpj;";
//        List<Fornecedor> listaforn = new ArrayList<Fornecedor>();
//       try {
//            PreparedStatement ps = conn.prepareStatement(SQL);
//            ResultSet rs = ps.executeQuery(); 
//            if(rs !=null){
//                while(rs.next()){
//                    Fornecedor forn = new Fornecedor();
//                    forn.setCnpj(rs.getString("cnpj"));
//                    forn.setNomeFornecedor(rs.getString("nome"));
//                    listaforn.add(forn);
//                }
//            }
//            return listaforn;
//        } catch (SQLException e) {
//            return null;
//        }
//    }
//    
//    public int numeroTotalRegistros() {
//        String SQL = "SELECT count(*) FROM fornecedores";
//        int numt = 0;
//        try {
//            PreparedStatement ps = conn.prepareStatement(SQL);
//            ResultSet rs = ps.executeQuery();
//            if (rs != null) {
//                while (rs.next()) {
//                    numt = rs.getInt(1);
//                }
//            }
//            return numt;
//        } catch (SQLException e) {
//            System.out.println("" + e);
//            return 0;
//        }
//    } 
}

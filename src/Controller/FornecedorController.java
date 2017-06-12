/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Fornecedor;

/**
 *
 * @author Jefferson Vantuir
 */
public class FornecedorController {
    private Connection conn;
    
    public FornecedorController(Connection conn) {
        this.conn = conn;
    }
    
    public String inserirFornecedor (Fornecedor cont){
    String SQL = "INSERT INTO fornecedores (cnpj, nome) values(?,?);";
    try{
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, cont.getCnpj());
        ps.setString(2, cont.getNomeFornecedor());
        if(ps.executeUpdate()>0){
            return "Fornecedor Cadastrado com Sucesso!!!";
        }else{
            return "Falha ao Cadastrar Fornecedor!!!";
        }
    
    }catch(SQLException e){
        return e.getMessage();
    }

    }
    public String alterarFornecedor (Fornecedor forn){
    String SQL = "UPDATE fornecedores SET nome = ? WHERE cnpj = '"+forn.getCnpj()+"';";
        try{
            PreparedStatement ps= conn.prepareStatement(SQL);
            ps.setString(1, forn.getNomeFornecedor());
            if(ps.executeUpdate() > 0){
                return "Fornecedor Alterado com Sucesso!!!";
            } else {
                return "Falha ao Alterar Fornecedor!!!";
            }
        } catch(SQLException e) {
            return e.getMessage();
        }
    }    
    public String excluirFornecedor (Fornecedor forn){
    String SQL = "DELETE FROM fornecedores WHERE cnpj = ?;";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, forn.getCnpj());
            ps.executeUpdate();//podemos retirar este aqui.
            return "Fornecedor Excluído com Sucesso!!!";
        } catch(SQLException e) {
            return e.getMessage();
        }
    }
    
    
    public List<Fornecedor> listarTodosFornecedores(String texto) {
        String SQL="SELECT * FROM fornecedores WHERE nome LIKE '%"+texto+"%' ORDER BY cnpj;";
        List<Fornecedor> listaforn = new ArrayList<Fornecedor>();
       try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(); 
            if(rs !=null){
                while(rs.next()){
                    Fornecedor forn = new Fornecedor();
                    forn.setCnpj(rs.getString("cnpj"));
                    forn.setNomeFornecedor(rs.getString("nome"));
                    listaforn.add(forn);
                }
            }
            return listaforn;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public int numeroTotalRegistros() {
        String SQL = "SELECT count(*) FROM fornecedores";
        int numt = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    numt = rs.getInt(1);
                }
            }
            return numt;
        } catch (SQLException e) {
            System.out.println("" + e);
            return 0;
        }
    }    
}

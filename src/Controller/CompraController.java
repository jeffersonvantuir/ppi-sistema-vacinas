/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.VacinaFornecedor;

/**
 *
 * @author Jefferson Vantuir
 */
public class CompraController {
    private Connection conn;
    
    public CompraController(Connection conn) {
        this.conn = conn;
    }
    
    public String inserirCompra (VacinaFornecedor vf){
    String SQL = "INSERT INTO vacinas_fornecedores (cnpj_fornecedor, nome_vac, lote_vac, valor_unit, data, qtde) values(?,?,?,?,?,?);";
    try{
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, vf.getCnpj_fornecedor());
        ps.setString(2, vf.getNome_vac());
        ps.setString(3, vf.getLote_vac());
        ps.setFloat(4, vf.getValor_unit());
        ps.setDate(5, vf.getData());
        ps.setInt(6, vf.getQtde());
        if(ps.executeUpdate()>0){
            return "Compra Cadastrada com Sucesso!!!";
        }else{
            return "Falha ao Cadastrar Compra!!!";
        }
    
    }catch(SQLException e){
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
    
    public List<VacinaFornecedor> listarTodasCompras(String texto) {
        String SQL="SELECT * FROM vacinas_fornecedores WHERE nome_vac LIKE '%"+texto+"%' ORDER BY data DESC;";
        List<VacinaFornecedor> lista = new ArrayList<VacinaFornecedor>();
       try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(); 
            if(rs !=null){
                while(rs.next()){
                    VacinaFornecedor vf = new VacinaFornecedor();
                    vf.setCnpj_fornecedor(rs.getString("cnpj_fornecedor"));
                    vf.setNome_vac(rs.getString("nome_vac"));
                    vf.setLote_vac(rs.getString("lote_vac"));
                    vf.setData(rs.getDate("data"));
                    vf.setValor_unit(rs.getFloat("valor_unit"));
                    vf.setQtde(rs.getInt("qtde"));
                    lista.add(vf);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
}

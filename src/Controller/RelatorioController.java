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
import Model.Paciente;

/**
 *
 * @author Jefferson Vantuir
 */
public class RelatorioController {
    private Connection conn;
    
    public RelatorioController(Connection conn) {
        this.conn = conn;
    }
    
    public List<Fornecedor> relatorioFornecedores(String texto) {
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
    
    public List<Paciente> relatorioPacientes(String texto) {
        String SQL="SELECT * FROM pacientes WHERE nome LIKE '%"+texto+"%' ORDER BY nome;";
        List<Paciente> lista = new ArrayList<Paciente>();
       try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(); 
            if(rs !=null){
                while(rs.next()){
                    Paciente pac = new Paciente();
                    pac.setCpf(rs.getString("cpf"));
                    pac.setNome(rs.getString("nome"));
                    lista.add(pac);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Vacina;

/**
 *
 * @author Jefferson Vantuir
 */
public class VacinaController {
    private Connection conn;
    
    public VacinaController(Connection conn) {
        this.conn = conn;
    }
    
    public String inserirVacina (Vacina vac){
    String SQL = "INSERT INTO vacinas (nome, lote, validade, qtd_estoque) VALUES (?,?,?,?);";
    try{
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, vac.getNome());
        ps.setString(2, vac.getLote());
        ps.setDate(3, vac.getValidade());
        ps.setInt(4, vac.getQtd_estoque());
        if(ps.executeUpdate()>0){
            return "Vacina Cadastrada com Sucesso!!!";
        }else{
            return "Falha ao Cadastrar Vacina!!!";
        }
    
    }catch(SQLException e){
        return e.getMessage();
    }

    }
    public String alterarVacina (Vacina vac){
    String SQL = "UPDATE vacinas SET validade = ?, qtd_estoque = ? WHERE nome = '"+vac.getNome()+"' AND lote = '"+vac.getLote()+"';";
        try{
            PreparedStatement ps= conn.prepareStatement(SQL);
            ps.setDate(1, vac.getValidade());
            ps.setInt(2, vac.getQtd_estoque());
            if(ps.executeUpdate() > 0){
                return "Vacina Alterada com Sucesso!!!";
            } else {
                return "Falha ao Alterar Vacina!!!";
            }
        } catch(SQLException e) {
            return e.getMessage();
        }
    }    
    public String excluirVacina (Vacina vac){
    String SQL = "DELETE FROM vacinas WHERE nome = ? AND lote = ?;";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, vac.getNome());
            ps.setString(2, vac.getLote());
            ps.executeUpdate();//podemos retirar este aqui.
            return "Vacina Excluída com Sucesso!!!";
        } catch(SQLException e) {
            return e.getMessage();
        }
    }
    
    
    public List<Vacina> listarTodasVacinas(String texto) {
        String SQL="SELECT * FROM vacinas WHERE nome LIKE '%"+texto+"%' ORDER BY nome;";
        List<Vacina> listavac = new ArrayList<Vacina>();
       try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(); 
            if(rs !=null){
                while(rs.next()){
                    Vacina vac = new Vacina();
                    vac.setNome(rs.getString("nome"));
                    vac.setLote(rs.getString("lote"));
                    vac.setValidade(rs.getDate("validade"));
                    vac.setQtd_estoque(rs.getInt("qtd_estoque"));
                    listavac.add(vac);
                }
            }
            return listavac;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public int numeroTotalRegistros() {
        String SQL = "SELECT count(*) FROM vacinas";
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

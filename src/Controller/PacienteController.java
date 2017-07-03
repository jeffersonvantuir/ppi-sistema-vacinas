/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Paciente;

/**
 *
 * @author Jefferson Vantuir
 */
public class PacienteController {
    private Connection conn;
    
    public PacienteController(Connection conn) {
        this.conn = conn;
    }
    
    public String inserirPaciente (Paciente pac){
        String SQL = "INSERT INTO pacientes (cpf, nome, email, telefone, sexo, data_nasc) values(?,?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, pac.getCpf());
            ps.setString(2, pac.getNome());
            ps.setString(3, pac.getEmail());
            ps.setString(4, pac.getTelefone());
            ps.setString(5, pac.getSexo());
            ps.setDate(6, pac.getData_nasc());
            if(ps.executeUpdate()>0){
                return "Paciente Cadastrado com Sucesso!!!";
            }else{
                return "Falha ao Cadastrar Paciente!!!";
            }
        } catch(SQLException e) {
            return e.getMessage();
        }
    }
    public String alterarPaciente (Paciente pac){
    String SQL = "UPDATE pacientes SET nome = ?, email = ?, telefone = ?, sexo = ?, data_nasc = ? WHERE cpf = '"+pac.getCpf()+"';";
        try{
            PreparedStatement ps= conn.prepareStatement(SQL);
            ps.setString(1, pac.getNome());
            ps.setString(2, pac.getEmail());
            ps.setString(3, pac.getTelefone());
            ps.setString(4, pac.getSexo());
            ps.setDate(5, pac.getData_nasc());
            if(ps.executeUpdate() > 0){
                return "Paciente Alterado com Sucesso!!!";
            } else {
                return "Falha ao Alterar Paciente!!!";
            }
        } catch(SQLException e) {
            return e.getMessage();
        }
    }    
    public String excluirPaciente (Paciente pac){
    String SQL = "DELETE FROM pacientes WHERE cpf = ?;";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, pac.getCpf());
            ps.executeUpdate();//podemos retirar este aqui.
            return "Paciente Excluído com Sucesso!!!";
        } catch(SQLException e) {
            return e.getMessage();
        }
    }
    
    
    public List<Paciente> listarTodosPacientes(String texto) {
        String SQL="SELECT * FROM pacientes WHERE nome LIKE '%"+texto+"%' ORDER BY nome;";
        List<Paciente> listapac = new ArrayList<Paciente>();
       try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(); 
            if(rs !=null){
                while(rs.next()){
                    Paciente pac = new Paciente();
                    pac.setCpf(rs.getString("cpf"));
                    pac.setNome(rs.getString("nome"));
                    pac.setSexo(rs.getString("sexo"));
                    pac.setTelefone(rs.getString("telefone"));
                    pac.setData_nasc(rs.getDate("data_nasc"));
                    pac.setEmail(rs.getString("email"));
                    listapac.add(pac);
                }
            }
            return listapac;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public int numeroTotalRegistros() {
        String SQL = "SELECT count(*) FROM pacientes";
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

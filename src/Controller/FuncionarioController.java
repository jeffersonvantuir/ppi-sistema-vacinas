/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Funcionario;

/**
 *
 * @author Jefferson Vantuir
 */
public class FuncionarioController {
    private Connection conn;
    
    public FuncionarioController(Connection conn) {
        this.conn = conn;
    }
    
    public String inserirFuncionario (Funcionario cont){
    String SQL = "INSERT INTO funcionarios (crm, nome, cpf) values(?,?, ?);";
    try {
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, cont.getCrm());
        ps.setString(2, cont.getNome());
        ps.setString(3, cont.getCpf());
        if(ps.executeUpdate()>0){
            return "Funcionario Cadastrado com Sucesso!!!";
        }else{
            return "Falha ao Cadastrar Funcionario!!!";
        }
    
    } catch(SQLException e) {
        return e.getMessage();
    }

    }
    public String alterarFuncionario (Funcionario func){
    String SQL = "UPDATE funcionarios SET crm = ?, nome = ?, cpf = ? WHERE cod = '"+func.getCod()+"';";
        if (func.getCpf().length() == 11) {
            try{
                PreparedStatement ps= conn.prepareStatement(SQL);
                ps.setString(1, func.getCrm());
                ps.setString(2, func.getNome());
                ps.setString(3, func.getCpf());
                if(ps.executeUpdate() > 0){
                    return "Funcionario Alterado com Sucesso!!!";
                } else {
                    return "Falha ao Alterar Funcionario!!!";
                }
            } catch(SQLException e) {
                return e.getMessage();
            }
        } else {
            return "CPF inválido. CPF precisa ter 11 digitos";
        }
    }    
    public String excluirFuncionario (Funcionario func){
    String SQL = "DELETE FROM funcionarios WHERE cod = ?;";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, func.getCod());
            ps.executeUpdate();//podemos retirar este aqui.
            return "Funcionario Excluído com Sucesso!!!";
        } catch(SQLException e) {
            return e.getMessage();
        }
    }
    
    
    public List<Funcionario> listarTodosFuncionarios(String texto) {
        String SQL="SELECT * FROM funcionarios WHERE nome LIKE '%"+texto+"%' ORDER BY nome;";
        List<Funcionario> listafunc = new ArrayList<Funcionario>();
       try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(); 
            if(rs !=null){
                while(rs.next()){
                    Funcionario func = new Funcionario();
                    func.setCod(rs.getInt("cod"));
                    func.setCrm(rs.getString("crm"));
                    func.setNome(rs.getString("nome"));
                    func.setCpf(rs.getString("cpf"));
                    listafunc.add(func);
                }
            }
            return listafunc;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public int numeroTotalRegistros() {
        String SQL = "SELECT count(*) FROM funcionarios";
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

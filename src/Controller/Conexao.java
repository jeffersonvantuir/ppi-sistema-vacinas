/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;

/**
 *
 * @author Jefferson Vantuir
 */
public class Conexao {
    public static Connection AbrirConexao(){
        Connection conn = null;
        try{

            Class.forName("org.postgresql.Driver").newInstance();
            String URL="jdbc:postgresql://localhost:5433/sistema_vacinas";
            String usuario="postgres";
            String senha="postgres";
            conn = DriverManager.getConnection(URL,usuario,senha);
            System.out.println("Conexão aberta");
        } catch(SQLException e) {
            System.out.println("Erro de Conexão"+e.getMessage());
        } catch(ClassNotFoundException e1) {
            System.out.println("Erro Classe"+e1.getMessage());
        } catch(Exception e2) {
            System.out.println("Erro Exception"+e2.getMessage());
        }  
        return conn;
    }
    
    public static void  fechaconexao(Connection con) throws SQLException{
        con.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Jefferson Vantuir
 */
public class CartaoVacina {
    public int cod;
    public int n_dose;
    public boolean pendente;
    public Date data;
    public float preco;
    public String nome_vac;
    public String lote_vac;
    public String cpf_paciente;
    public int cod_funcionario;
    
}

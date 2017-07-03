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
    public Date data_aplicacao;
    public float preco;
    public String nome_vac;
    public String lote_vac;
    public String cpf_paciente;
    public int cod_funcionario;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getN_dose() {
        return n_dose;
    }

    public void setN_dose(int n_dose) {
        this.n_dose = n_dose;
    }

    public Date getData_aplicacao() {
        return data_aplicacao;
    }

    public void setData_aplicacao(Date data_aplicacao) {
        this.data_aplicacao = data_aplicacao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome_vac() {
        return nome_vac;
    }

    public void setNome_vac(String nome_vac) {
        this.nome_vac = nome_vac;
    }

    public String getLote_vac() {
        return lote_vac;
    }

    public void setLote_vac(String lote_vac) {
        this.lote_vac = lote_vac;
    }

    public String getCpf_paciente() {
        return cpf_paciente;
    }

    public void setCpf_paciente(String cpf_paciente) {
        this.cpf_paciente = cpf_paciente;
    }

    public int getCod_funcionario() {
        return cod_funcionario;
    }

    public void setCod_funcionario(int cod_funcionario) {
        this.cod_funcionario = cod_funcionario;
    }
    
       
    
}

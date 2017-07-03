/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author Jefferson Vantuir
 */
public class VacinaFornecedor {
    public int cod;
    public int qtde;
    public Date data;
    public float valor_unit;
    public String nome_vac;
    public String lote_vac;
    public String cnpj_fornecedor;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValor_unit() {
        return valor_unit;
    }

    public void setValor_unit(float valor_unit) {
        this.valor_unit = valor_unit;
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

    public String getCnpj_fornecedor() {
        return cnpj_fornecedor;
    }

    public void setCnpj_fornecedor(String cnpj_fornecedor) {
        this.cnpj_fornecedor = cnpj_fornecedor;
    }
}

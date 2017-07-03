/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CartaoVacina;

import View.Compras.*;
import Controller.CompraController;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import Controller.Conexao;
import Model.Fornecedor;
import java.sql.Connection;
import Model.VacinaFornecedor;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.component.Table;
import java.sql.Date;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaIncluirCompra extends Window {
    private Panel painel01;
    private Panel pnlLista;
    private Table tblCli;
    private Button botaoSair;
    private Button botaoSalvar;
    private Button btnConsultar;
    private Label label01;
    private Label lblNome;
    private TextBox txtNome;
    private Label lblLote;
    private TextBox txtLote;
    private Label lblDataCompra;
    private TextBox txtDataCompra;
    private Label lblValorUnit;
    private TextBox txtValorUnit;
    private Label lblQtde;
    private TextBox txtQtde;
    private String cnpjForn;
    private GUIScreen guiscreen;
    
    VacinaFornecedor vacForn = new VacinaFornecedor();

    public TelaIncluirCompra(GUIScreen guitelaprincipal, String cnpjForn) {
        super("Cadastro de Compras");
        this.guiscreen = guitelaprincipal;
        this.cnpjForn = cnpjForn;
        init();
    }

    private void init() {
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        
        Connection conn = Conexao.AbrirConexao();
        CompraController compraCtrl = new CompraController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        label01 = new Label("Incluir Compra: ");
        addComponent(label01);
        lblNome = new Label("Nome ");
        txtNome = new TextBox();
        lblLote = new Label("Lote ");
        txtLote = new TextBox();
        lblDataCompra = new Label("Data Compra (YYY-MM-DD) ");
        txtDataCompra = new TextBox();
        lblValorUnit = new Label("Valor Unitário ");
        txtValorUnit = new TextBox();
        lblQtde = new Label("Quantidade ");
        txtQtde = new TextBox();
        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblLote);
        addComponent(txtLote);
        addComponent(lblDataCompra);
        addComponent(txtDataCompra);
        addComponent(lblValorUnit);
        addComponent(txtValorUnit);
        addComponent(lblQtde);
        addComponent(txtQtde);
        
        botaoSalvar = new Button("Incluir", new Action() {
            @Override
            public void doAction() {
               vacForn.setNome_vac(txtNome.getText());
               vacForn.setLote_vac(txtLote.getText());
               vacForn.setData(Date.valueOf(txtDataCompra.getText()));
               vacForn.setValor_unit(Float.valueOf(txtValorUnit.getText()));
               vacForn.setQtde(Integer.valueOf(txtQtde.getText()));
               vacForn.setCnpj_fornecedor(cnpjForn);
                MessageBox.showMessageBox(guiscreen, "Compra", compraCtrl.inserirCompra(vacForn));
            }
        });
         botaoSair = new Button("Sair", new Action() {
             @Override
            public void doAction() {
                close();
            }
         });
        addComponent(botaoSalvar);
        addComponent(botaoSair);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CartaoVacina;

import View.CartaoVacina.*;
import Controller.CartaoVacinaController;
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
import Model.CartaoVacina;
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
public class TelaAplicacaoVacina extends Window {
    private Panel painel01;
    private Panel pnlLista;
    private Table tblCli;
    private Button botaoSair;
    private Button botaoSalvar;
    private Button btnConsultar;
    private Label label01;
    
    private Label lblCodFunc;
    private TextBox txtCodFunc;
    private Label lblNome;
    private TextBox txtNome;
    private Label lblLote;
    private TextBox txtLote;
    private Label lblDataAplicacao;
    private TextBox txtDataAplicacao;
    private Label lblPreco;
    private TextBox txtPreco;
    private Label lblNDose;
    private TextBox txtNDose;
    private String cpfPaciente;
    private GUIScreen guiscreen;
    
    CartaoVacina cartaoVacina = new CartaoVacina();

    public TelaAplicacaoVacina(GUIScreen guitelaprincipal, String cpfPaciente) {
        super("Cadastro de Aplicacao");
        this.guiscreen = guitelaprincipal;
        this.cpfPaciente = cpfPaciente;
        init();
    }

    private void init() {
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        
        Connection conn = Conexao.AbrirConexao();
        CartaoVacinaController cvCtrl = new CartaoVacinaController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        label01 = new Label("Cadastrar Aplicação ");
        addComponent(label01);
        lblCodFunc = new Label("Codigo do Funcionario");
        txtCodFunc = new TextBox();
        lblNome = new Label("Nome Vacina");
        txtNome = new TextBox();
        lblLote = new Label("Lote Vacina");
        txtLote = new TextBox();
        lblDataAplicacao = new Label("Data Aplicação");
        txtDataAplicacao = new TextBox();
        lblNDose = new Label("Nº da Dose");
        txtNDose = new TextBox();
        lblPreco = new Label("Preço");
        txtPreco = new TextBox();
        addComponent(lblCodFunc);
        addComponent(txtCodFunc);
        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblLote);
        addComponent(txtLote);
        addComponent(lblDataAplicacao);
        addComponent(txtDataAplicacao);
        addComponent(lblNDose);
        addComponent(txtNDose);
        addComponent(lblPreco);
        addComponent(txtPreco);
        
        botaoSalvar = new Button("Cadastrar", new Action() {
            @Override
            public void doAction() {
               cartaoVacina.setCod_funcionario(Integer.valueOf(txtCodFunc.getText()));
               cartaoVacina.setN_dose(Integer.valueOf(txtNDose.getText()));
               cartaoVacina.setNome_vac(txtNome.getText());
               cartaoVacina.setLote_vac(txtLote.getText());
               cartaoVacina.setData_aplicacao(Date.valueOf(txtDataAplicacao.getText()));
               cartaoVacina.setPreco(Float.valueOf(txtPreco.getText()));
               cartaoVacina.setCpf_paciente(cpfPaciente);
                MessageBox.showMessageBox(guiscreen, "Aplicação", cvCtrl.inserirCartaoVacina(cartaoVacina));
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Vacinas;

import View.Vacinas.*;
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
import Controller.VacinaController;
import java.sql.Connection;
import Model.Vacina;
import java.sql.Date;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaIncluirVacina extends Window {
    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Label label01;
    private Label lblNome;
    private TextBox txtNome;
    private Label lblLote;
    private TextBox txtLote;
    private Label lblValidade;
    private TextBox txtValidade;
    private Label lblQtdEstoque;
    private TextBox txtQtdEstoque;
    
    private GUIScreen guiscreen;
    
    Vacina vacina = new Vacina();

    public TelaIncluirVacina(GUIScreen guitelaprincipal) {
        super("Cadastro de Vacinas");
        this.guiscreen = guitelaprincipal;
        init();
    }

    private void init() {
        Connection conn = Conexao.AbrirConexao();
        VacinaController vacCtrl = new VacinaController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        label01 = new Label("Incluir Vacina: ");
        addComponent(label01);
        lblNome = new Label("Nome ");
        txtNome = new TextBox();
        lblLote = new Label("Lote ");
        txtLote = new TextBox();
        lblValidade = new Label("Validade (YYYY-MM-DD) ");
        txtValidade = new TextBox();
        lblQtdEstoque = new Label("Qtd Estoque ");
        txtQtdEstoque = new TextBox();
        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblLote);
        addComponent(txtLote);
        addComponent(lblValidade);
        addComponent(txtValidade);
        addComponent(lblQtdEstoque);
        addComponent(txtQtdEstoque);
        
        botaoSalvar = new Button("Incluir", new Action() {
            @Override
            public void doAction() {
               vacina.setNome(txtNome.getText());
               vacina.setLote(txtLote.getText());
               vacina.setValidade(Date.valueOf(txtValidade.getText()));
               vacina.setQtd_estoque(Integer.valueOf(txtQtdEstoque.getText()));
                MessageBox.showMessageBox(guiscreen, "Vacina", vacCtrl.inserirVacina(vacina));
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

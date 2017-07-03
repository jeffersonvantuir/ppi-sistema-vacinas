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
public class TelaModificarVacina extends Window {
    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Button botaoExcluir;
    private Label label01;
    private Label lblNome;
    private Label lblLote;
    private Label lblValidade;
    private TextBox txtValidade;
    private Label lblQtdEstoque;
    private TextBox txtQtdEstoque;
    VacinaController vCtrl;
    private String operacao;
    
    private GUIScreen guiscreen;
    
    Vacina vacinas = new Vacina();

    public TelaModificarVacina(GUIScreen guitelaprincipal, Vacina vac, String op) {
        super("Modificar Vacina");
        this.guiscreen = guitelaprincipal;
        this.vacinas = vac;
        this.operacao = op;
        if (op.equals("A")) {
            Alterar();
        } else {
            Excluir();
        }
    }

    private void Alterar() {
        Connection conn = Conexao.AbrirConexao();
        VacinaController vacCtrl = new VacinaController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        label01 = new Label("Alterar Vacina: ");
        addComponent(label01);
        lblNome = new Label("Nome: "+ vacinas.getNome());
        lblLote = new Label("Lote: "+ vacinas.getLote());
        lblValidade = new Label("Validade ");
        txtValidade = new TextBox(String.valueOf(vacinas.getValidade()));
        lblQtdEstoque = new Label("Qtd Estoque ");
        txtQtdEstoque = new TextBox(String.valueOf(vacinas.getQtd_estoque()));
        
        addComponent(lblNome);
        addComponent(lblLote);
        addComponent(lblValidade);
        addComponent(txtValidade);
        addComponent(lblQtdEstoque);
        addComponent(txtQtdEstoque);

        botaoSalvar = new Button("Salvar", new Action() {
            @Override
            public void doAction() {
                
                if (txtValidade.getText().equals("null") || txtValidade.equals("NULL") || txtValidade.equals("")) {
                    vacinas.setValidade(null);
                } else {
                    vacinas.setValidade(Date.valueOf(txtValidade.getText()));
                }
               vacinas.setNome(vacinas.getNome());
               vacinas.setLote(vacinas.getLote());
               vacinas.setQtd_estoque(Integer.valueOf(txtQtdEstoque.getText()));
                MessageBox.showMessageBox(guiscreen, "Vacina", vacCtrl.alterarVacina(vacinas));
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

    private void Excluir() {
        Connection conn = Conexao.AbrirConexao();
        VacinaController vacCtrl = new VacinaController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        lblNome = new Label("Nome: "+ vacinas.getNome());
        lblLote = new Label("Lote: "+ vacinas.getLote());
        lblValidade = new Label("Validade: "+ vacinas.getValidade());
        lblQtdEstoque = new Label("Qtd Estoque: "+vacinas.getQtd_estoque());
        addComponent(lblNome);
        addComponent(lblLote);
        addComponent(lblValidade);
        addComponent(lblQtdEstoque);
        
        botaoExcluir = new Button("Excluir", new Action() {
            @Override
            public void doAction() {
                MessageBox.showMessageBox(guiscreen, "Vacina", vacCtrl.excluirVacina(vacinas));
            }
        });
         botaoSair = new Button("Sair", new Action() {
             @Override
            public void doAction() {
                close();
            }
         });
        addComponent(botaoExcluir);
        addComponent(botaoSair);
    }
    
}

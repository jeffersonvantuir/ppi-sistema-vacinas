/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Funcionarios;

import View.Funcionarios.*;
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
import Controller.FuncionarioController;
import java.sql.Connection;
import Model.Funcionario;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaModificarFuncionario extends Window {
    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Button botaoExcluir;
    private Label label01;
    private Label lblCod;
    private Label lblCrm;
    private TextBox txtCrm;
    private Label lblNome;
    private TextBox txtNome;
    private Label lblCpf;
    private TextBox txtCpf;
    FuncionarioController fCtrl;
    private String operacao;
    
    private GUIScreen guiscreen;
    
    Funcionario funcionario = new Funcionario();

    public TelaModificarFuncionario(GUIScreen guitelaprincipal, Funcionario func, String op) {
        super("Modificar Funcionario");
        this.guiscreen = guitelaprincipal;
        this.funcionario = func;
        this.operacao = op;
        if (op.equals("A")) {
            Alterar();
        } else {
            Excluir();
        }
    }

    private void Alterar() {
        Funcionario func = new Funcionario();
        Connection conn = Conexao.AbrirConexao();
        FuncionarioController funcCtrl = new FuncionarioController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        
        label01 = new Label("Alterar Funcionario: ");
        addComponent(label01);
        lblCrm = new Label("CRM: ");
        txtCrm = new TextBox(funcionario.getCrm());
        lblNome = new Label("Nome: ");
        txtNome = new TextBox(funcionario.getNome());
        lblCpf = new Label("CPF: ");
        txtCpf = new TextBox(funcionario.getCpf());
        addComponent(lblCrm);
        addComponent(txtCrm);
        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblCpf);
        addComponent(txtCpf);
        
        botaoSalvar = new Button("Salvar", new Action() {
            @Override
            public void doAction() {
               funcionario.setCrm(txtCrm.getText());
               funcionario.setNome(txtNome.getText());
               funcionario.setCpf(txtCpf.getText());
                MessageBox.showMessageBox(guiscreen, "Funcionario", funcCtrl.alterarFuncionario(funcionario));
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
        FuncionarioController funcCtrl = new FuncionarioController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        lblCrm = new Label("CRM: "+ funcionario.getCrm());
        lblNome = new Label("Nome: "+ funcionario.getNome());
        lblCpf = new Label("CPF: "+ funcionario.getCpf());
        addComponent(lblCrm);
        addComponent(lblNome);
        addComponent(lblCpf);
        
        botaoExcluir = new Button("Excluir", new Action() {
            @Override
            public void doAction() {
                MessageBox.showMessageBox(guiscreen, "Funcionario", funcCtrl.excluirFuncionario(funcionario));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Funcionarios;

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
public class TelaIncluirFuncionario extends Window {
    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Label label01;
    private Label lblCrm;
    private Label lblNome;
    private Label lblCpf;
    private TextBox txtCrm;
    private TextBox txtNome;
    private TextBox txtCpf;
    
    private GUIScreen guiscreen;
    
    Funcionario funcionario = new Funcionario();

    public TelaIncluirFuncionario(GUIScreen guitelaprincipal) {
        super("Cadastro de Funcionarios");
        this.guiscreen = guitelaprincipal;
        init();
    }

    private void init() {
        Connection conn = Conexao.AbrirConexao();
        FuncionarioController funcCtrl = new FuncionarioController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        label01 = new Label("Incluir Funcionario: ");
        addComponent(label01);
        lblCrm = new Label("CRM: ");
        txtCrm = new TextBox();
        lblNome = new Label("Nome: ");
        txtNome = new TextBox();
        lblCpf = new Label("CPF: ");
        txtCpf = new TextBox();
        addComponent(lblCrm);
        addComponent(txtCrm);
        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblCpf);
        addComponent(txtCpf);
        
        botaoSalvar = new Button("Incluir", new Action() {
            @Override
            public void doAction() {
                funcionario.setCod(0);
               funcionario.setCrm(txtCrm.getText());
               funcionario.setNome(txtNome.getText());
               funcionario.setCpf(txtCpf.getText());
                MessageBox.showMessageBox(guiscreen, "Funcionario", funcCtrl.inserirFuncionario(funcionario));
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

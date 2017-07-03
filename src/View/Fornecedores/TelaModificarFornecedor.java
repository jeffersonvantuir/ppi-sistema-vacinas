/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Fornecedores;

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
import Controller.FornecedorController;
import java.sql.Connection;
import Model.Fornecedor;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaModificarFornecedor extends Window {
    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Button botaoExcluir;
    private Label label01;
    private Label lblCNPJ;
    private TextBox txtCNPJ;
    private Label lblNome;
    private TextBox txtNome;
    FornecedorController fCtrl;
    private String operacao;
    
    private GUIScreen guiscreen;
    
    Fornecedor fornecedor = new Fornecedor();

    public TelaModificarFornecedor(GUIScreen guitelaprincipal, Fornecedor forn, String op) {
        super("Modificar Fornecedor");
        this.guiscreen = guitelaprincipal;
        this.fornecedor = forn;
        this.operacao = op;
        if (op.equals("A")) {
            Alterar();
        } else {
            Excluir();
        }
    }

    private void Alterar() {
        Connection conn = Conexao.AbrirConexao();
        FornecedorController fornCtrl = new FornecedorController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        label01 = new Label("Alterar Fornecedor: ");
        addComponent(label01);
        lblCNPJ = new Label("CNPJ: "+ fornecedor.getCnpj());
        lblNome = new Label("Nome do Fornecedor: ");
        txtNome = new TextBox(fornecedor.getNomeFornecedor());
        addComponent(lblCNPJ);
        addComponent(lblNome);
        addComponent(txtNome);
        
        botaoSalvar = new Button("Salvar", new Action() {
            @Override
            public void doAction() {
               fornecedor.setNomeFornecedor(txtNome.getText());
                MessageBox.showMessageBox(guiscreen, "Fornecedor", fornCtrl.alterarFornecedor(fornecedor));
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
        FornecedorController fornCtrl = new FornecedorController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        lblCNPJ = new Label("CNPJ: "+ fornecedor.getCnpj());
        lblNome = new Label("Nome do Fornecedor: "+ fornecedor.getNomeFornecedor());
        addComponent(lblCNPJ);
        addComponent(lblNome);
        
        botaoExcluir = new Button("Excluir", new Action() {
            @Override
            public void doAction() {
                MessageBox.showMessageBox(guiscreen, "Fornecedor", fornCtrl.excluirFornecedor(fornecedor));
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

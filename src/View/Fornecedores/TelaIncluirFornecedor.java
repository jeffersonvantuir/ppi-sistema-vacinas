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
public class TelaIncluirFornecedor extends Window {
    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Label label01;
    private Label lblCNPJ;
    private TextBox txtCNPJ;
    private Label lblNome;
    private TextBox txtNome;
    
    private GUIScreen guiscreen;
    
    Fornecedor fornecedor = new Fornecedor();

    public TelaIncluirFornecedor(GUIScreen guitelaprincipal) {
        super("Cadastro de Fornecedores");
        this.guiscreen = guitelaprincipal;
        init();
    }

    private void init() {
        Connection conn = Conexao.AbrirConexao();
        FornecedorController fornCtrl = new FornecedorController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        label01 = new Label("Incluir Fornecedor: ");
        addComponent(label01);
        lblCNPJ = new Label("CNPJ : ");
        txtCNPJ = new TextBox();
        lblNome = new Label("Nome do Fornecedor: ");
        txtNome = new TextBox();
        addComponent(lblCNPJ);
        addComponent(txtCNPJ);
        addComponent(lblNome);
        addComponent(txtNome);
        
        botaoSalvar = new Button("Incluir", new Action() {
            @Override
            public void doAction() {
               fornecedor.setCnpj(txtCNPJ.getText());
               fornecedor.setNomeFornecedor(txtNome.getText());
                MessageBox.showMessageBox(guiscreen, "Fornecedor", fornCtrl.inserirFornecedor(fornecedor));
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

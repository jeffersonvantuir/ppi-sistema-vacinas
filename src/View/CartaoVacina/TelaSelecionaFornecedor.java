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

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaSelecionaFornecedor extends Window {
    private Panel painel01;
    private Panel pnlLista;
    private Table tblCli;
    private Button botaoSair;
    private Button botaoSalvar;
    private Button btnConsultar;
    private Label label01;
    private Label lblCNPJ;
    private TextBox txtCNPJ;
    private Label lblNome;
    private TextBox txtNome;
    
    private GUIScreen guiscreen;
    
    VacinaFornecedor vacForn = new VacinaFornecedor();

    public TelaSelecionaFornecedor(GUIScreen guitelaprincipal) {
        super("Cadastro de Compras");
        this.guiscreen = guitelaprincipal;
        init();
    }

    private void init() {
        
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        
        // Consulta por nome
        lblNome = new Label("Procure pelo nome do Fornecedor");
        txtNome = new TextBox();
        addComponent(lblNome);
        addComponent(txtNome);
         btnConsultar = new Button("Consultar", new Action() {
            @Override
            public void doAction() {
                removeComponent(tblCli);
                removeComponent(botaoSair);
                consulta(txtNome.getText());
            }
        });
         addComponent(btnConsultar);
         
         botaoSair = new Button("Sair", new Action() {
             @Override
            public void doAction() {
                close();
            }
         });
        addComponent(botaoSalvar);
        addComponent(botaoSair);
    }
    
    private Component[] linha = new Component[3];
    private void consulta(String texto) {
        tblCli = new Table(3, "Relação de Fornecedores");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        CompraController ctrl = new CompraController(conn);
        linha[0] = new Label("CNPJ");
        linha[1] = new Label("Nome              ");
        linha[2] = new Label("Opção");
        tblCli.addRow(linha);

        linha[0] = new Label("---------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("---------");
        tblCli.addRow(linha);

        for (Fornecedor vf : ctrl.listarTodosFornecedores(txtNome.getText())) {
            linha[0] = new Label(vf.getCnpj());
            linha[1] = new Label(vf.getNomeFornecedor());
            linha[2] = new Button("Selecionar", new Action() {
                @Override
                public void doAction() {
//                    DialogResult result = MessageBox.showMessageBox(guiscreen, "Alteração de Fornecedor", 
//                            "Deseja alterar o Fornecedor "+forn.getNomeFornecedor().toString() + "?", DialogButtons.OK_CANCEL);
//                    if (result.equals(DialogResult.OK)){
//                        // Shoooow
//                    } else {
//                        // Não Shoooow
//                    }
                    guiscreen.showWindow(new TelaIncluirCompra(guiscreen, vf.getCnpj()));
                }
            });
            tblCli.addRow(linha);
        }
        addComponent(tblCli);
        
        botaoSair = new Button("Voltar", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        addComponent(botaoSair);
    }
}

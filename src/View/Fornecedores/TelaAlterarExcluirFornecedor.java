/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Fornecedores;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Table;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.DialogButtons;
import com.googlecode.lanterna.gui.dialog.DialogResult;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import Controller.Conexao;
import Controller.FornecedorController;
import java.sql.Connection;
import Model.Fornecedor;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaAlterarExcluirFornecedor extends Window{
    private Button botaoSair;
    private Table tblCli;
    private Panel pnlLista;
    private Label lbnome;
    private TextBox txtNome;
    private Button btnConsultar;
    
    private Component[] linha = new Component[4];
    
    private static GUIScreen guiscreen;
    
    public TelaAlterarExcluirFornecedor(GUIScreen gs) {
        super("Alterar ou Excluir Fornecedores");
        this.guiscreen = gs;
        init();
    }


    private void init() {
        
        setBorder(new Border.Standard());
        pnlLista = new Panel(Panel.Orientation.HORISONTAL);
        
        // Consulta por nome
        lbnome = new Label("Procure pelo nome");
        txtNome = new TextBox();
        addComponent(lbnome);
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
         
//        consulta(txtNome.getText());
        
        botaoSair = new Button("Voltar", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        addComponent(botaoSair);
    }
    
    private void consulta(String texto) {
        tblCli = new Table(4, "Relação de Fornecedores");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        FornecedorController ctrl = new FornecedorController(conn);
        linha[0] = new Label("CNPJ");
        linha[1] = new Label("Nome              ");
        linha[2] = new Label("Alteração");
        linha[3] = new Label("Exclusão");
        tblCli.addRow(linha);

        linha[0] = new Label("---------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("---------");
        linha[3] = new Label("--------");
        tblCli.addRow(linha);

        for (Fornecedor forn : ctrl.listarTodosFornecedores(txtNome.getText())) {
            linha[0] = new Label(""+forn.getCnpj());
            linha[1] = new Label(forn.getNomeFornecedor());
            linha[2] = new Button("Alterar", new Action() {
                @Override
                public void doAction() {
//                    DialogResult result = MessageBox.showMessageBox(guiscreen, "Alteração de Fornecedor", 
//                            "Deseja alterar o Fornecedor "+forn.getNomeFornecedor().toString() + "?", DialogButtons.OK_CANCEL);
//                    if (result.equals(DialogResult.OK)){
//                        // Shoooow
//                    } else {
//                        // Não Shoooow
//                    }
                    guiscreen.showWindow(new TelaModificarFornecedor(guiscreen, forn, "A"));
                }
                
            });
            linha[3] = new Button("Excluir", new Action() {
                @Override
                public void doAction() {
                   guiscreen.showWindow(new TelaModificarFornecedor(guiscreen, forn, "E"));
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

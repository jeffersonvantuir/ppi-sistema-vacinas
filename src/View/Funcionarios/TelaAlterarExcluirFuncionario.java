/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Funcionarios;
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
import Controller.FuncionarioController;
import java.sql.Connection;
import Model.Funcionario;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaAlterarExcluirFuncionario extends Window{
    private Button botaoSair;
    private Table tblCli;
    private Panel pnlLista;
    private Label lblNome;
    private TextBox txtNome;
    private Button btnConsultar;
    
    private Component[] linha = new Component[5];
    
    private static GUIScreen guiscreen;
    
    public TelaAlterarExcluirFuncionario(GUIScreen gs) {
        super("Alterar ou Excluir Funcionarios");
        this.guiscreen = gs;
        init();
    }


    private void init() {
        
        setBorder(new Border.Standard());
        pnlLista = new Panel(Panel.Orientation.HORISONTAL);
        
        // Consulta por nome
        lblNome = new Label("Procure pelo nome");
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
        tblCli = new Table(5, "Relação de Funcionarios");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        FuncionarioController ctrl = new FuncionarioController(conn);
        linha[0] = new Label("CRM");
        linha[1] = new Label("Nome              ");
        linha[2] = new Label("CPF");
        linha[3] = new Label("Alteração");
        linha[4] = new Label("Exclusão");
        tblCli.addRow(linha);

        linha[0] = new Label("---------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("----------------------");
        linha[3] = new Label("---------");
        linha[4] = new Label("--------");
        tblCli.addRow(linha);

        for (Funcionario func : ctrl.listarTodosFuncionarios(txtNome.getText())) {
            linha[0] = new Label(func.getCrm());
            linha[1] = new Label(func.getNome());
            linha[2] = new Label(func.getCpf());
            linha[3] = new Button("Alterar", new Action() {
                @Override
                public void doAction() {
//                    DialogResult result = MessageBox.showMessageBox(guiscreen, "Alteração de Fornecedor", 
//                            "Deseja alterar o Fornecedor "+forn.getNomeFornecedor().toString() + "?", DialogButtons.OK_CANCEL);
//                    if (result.equals(DialogResult.OK)){
//                        // Shoooow
//                    } else {
//                        // Não Shoooow
//                    }
                    guiscreen.showWindow(new TelaModificarFuncionario(guiscreen, func, "A"));
                }
            });
            linha[4] = new Button("Excluir", new Action() {
                @Override
                public void doAction() {
                   guiscreen.showWindow(new TelaModificarFuncionario(guiscreen, func, "E"));
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

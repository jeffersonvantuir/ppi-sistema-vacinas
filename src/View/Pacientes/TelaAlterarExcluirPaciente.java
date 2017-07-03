/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pacientes;
import View.Pacientes.*;
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
import Controller.PacienteController;
import java.sql.Connection;
import Model.Fornecedor;
import Model.Paciente;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaAlterarExcluirPaciente extends Window{
    private Button botaoSair;
    private Table tblCli;
    private Panel pnlLista;
    private Label lbnome;
    private TextBox txtNome;
    private Button btnConsultar;
    
    private Component[] linha = new Component[6];
    
    private static GUIScreen guiscreen;
    
    public TelaAlterarExcluirPaciente(GUIScreen gs) {
        super("Alterar ou Excluir Paciente");
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
        
        botaoSair = new Button("Voltar", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        addComponent(botaoSair);
    }
    
    private void consulta(String texto) {
        tblCli = new Table(6, "Relação de Pacientes");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        PacienteController ctrl = new PacienteController(conn);
        linha[0] = new Label("CPF               ");
        linha[1] = new Label("Nome              ");
        linha[2] = new Label("Telefone          ");
        linha[3] = new Label("E-mail            ");
        linha[4] = new Label("Alteração");
        linha[5] = new Label("Exclusão");
        tblCli.addRow(linha);

        linha[0] = new Label("---------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("----------------------");
        linha[3] = new Label("----------------------");
        linha[4] = new Label("---------");
        linha[5] = new Label("--------");
        tblCli.addRow(linha);

        for (Paciente pac : ctrl.listarTodosPacientes(txtNome.getText())) {
            linha[0] = new Label(""+pac.getCpf());
            linha[1] = new Label(pac.getNome());
            linha[2] = new Label(pac.getTelefone());
            linha[3] = new Label(pac.getEmail());
            linha[4] = new Button("Alterar", new Action() {
                @Override
                public void doAction() {
                    guiscreen.showWindow(new TelaModificarPaciente(guiscreen, pac, "A"));
                }
                
            });
            linha[5] = new Button("Excluir", new Action() {
                @Override
                public void doAction() {
                   guiscreen.showWindow(new TelaModificarPaciente(guiscreen, pac, "E"));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Relatorios;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Table;
import com.googlecode.lanterna.gui.component.TextBox;
import Controller.Conexao;
import Controller.FuncionarioController;
import Model.Funcionario;
import java.sql.Connection;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaRelatorioFuncionarios extends Window{
    private Button botaoSair;
    private Table tblCli;
    private Label lbnome;
    private TextBox txtNome;
    private Button btnConsultar;
       
    public TelaRelatorioFuncionarios() {
        super("Relatório de Funcionários");
        init();
    }

    private void init() {  
        setBorder(new Border.Standard());
        lbnome = new Label("Procure pelo nome do Funcionário");
        txtNome = new TextBox();
        addComponent(lbnome);
        addComponent(txtNome);
         btnConsultar = new Button("Consultar", new Action() {
            @Override
            public void doAction() {
                removeComponent(tblCli);
                removeComponent(botaoSair);
                consulta();
            }
        });
        addComponent(btnConsultar);
        consulta();
    }
    
    private Component[] linha = new Component[3];
    
    private void consulta() {
        tblCli = new Table(3, "Relação de Funcionários");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        FuncionarioController ctrl = new FuncionarioController(conn);
        linha[0] = new Label("CRM");
        linha[1] = new Label("Nome              ");
        linha[2] = new Label("CPF               ");
        
        tblCli.addRow(linha);

        linha[0] = new Label("----------------------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("----------------------");
        
        tblCli.addRow(linha);

        for (Funcionario func : ctrl.listarTodosFuncionarios(txtNome.getText())) {
            linha[0] = new Label(""+func.getCrm());
            linha[1] = new Label(func.getNome());
            linha[2] = new Label(func.getCpf());
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

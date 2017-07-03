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
import Controller.FornecedorController;
import java.sql.Connection;
import Model.Fornecedor;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaRelatorioFornecedores extends Window{
    private Button botaoSair;
    private Table tblCli;
    private Label lbnome;
    private TextBox txtNome;
    private Button btnConsultar;
    
    private Component[] linha = new Component[2];
    
    public TelaRelatorioFornecedores() {
        super("Relatório Fornecedores");
        init();
    }

    private void init() {
        
        setBorder(new Border.Standard());
        lbnome = new Label("Procure pelo nome");
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
    
    private void consulta() {
        tblCli = new Table(2, "Relação de Fornecedores");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        FornecedorController ctrl = new FornecedorController(conn);
        linha[0] = new Label("CNPJ");
        linha[1] = new Label("Nome              ");
        tblCli.addRow(linha);

        linha[0] = new Label("---------");
        linha[1] = new Label("----------------------");
        tblCli.addRow(linha);

        for (Fornecedor forn : ctrl.listarTodosFornecedores(txtNome.getText())) {
            linha[0] = new Label(""+forn.getCnpj());
            linha[1] = new Label(forn.getNomeFornecedor());
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

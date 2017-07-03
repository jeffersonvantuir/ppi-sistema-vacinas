/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Relatorios;
import Controller.CompraController;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Table;
import com.googlecode.lanterna.gui.component.TextBox;
import Controller.Conexao;
import Controller.VacinaController;
import java.sql.Connection;
import Model.Vacina;
import Model.VacinaFornecedor;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaRelatorioCompras extends Window{
    private Button botaoSair;
    private Table tblCli;
    private Label lbnome;
    private TextBox txtNome;
    private Button btnConsultar;
       
    public TelaRelatorioCompras() {
        super("Relatório de Compras");
        init();
    }

    private void init() {  
        setBorder(new Border.Standard());
        lbnome = new Label("Procure pelo Nome da Vacina");
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
    
    private Component[] linha = new Component[6];
    
    private void consulta() {
        tblCli = new Table(6, "Relação de Compras");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        CompraController ctrl = new CompraController(conn);
        linha[0] = new Label("Nome da Vacina");
        linha[1] = new Label("Lote da Vacina");
        linha[2] = new Label("Valor Unitario");
        linha[3] = new Label("Quantidade");
        linha[4] = new Label("Data da Compra");
        linha[5] = new Label("CNPJ do Fornecedor");
        
        
        tblCli.addRow(linha);

        linha[0] = new Label("----------------------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("----------------------");
        linha[3] = new Label("----------------------");
        linha[4] = new Label("----------------------");
        linha[5] = new Label("----------------------");
        
        tblCli.addRow(linha);

        for (VacinaFornecedor vf : ctrl.listarTodasCompras(txtNome.getText())) {
            linha[0] = new Label(vf.getNome_vac());
            linha[1] = new Label(vf.getLote_vac());
            linha[2] = new Label(""+vf.getValor_unit());
            linha[3] = new Label(""+vf.getQtde());
            linha[4] = new Label(""+vf.getData());
            linha[5] = new Label(vf.getCnpj_fornecedor());
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

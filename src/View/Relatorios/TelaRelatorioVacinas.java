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
import Controller.VacinaController;
import java.sql.Connection;
import Model.Vacina;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaRelatorioVacinas extends Window{
    private Button botaoSair;
    private Table tblCli;
    private Label lbnome;
    private TextBox txtNome;
    private Button btnConsultar;
       
    public TelaRelatorioVacinas() {
        super("Relatório de Pacientes");
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
    
    private Component[] linha = new Component[4];
    
    private void consulta() {
        tblCli = new Table(4, "Relação de Vacinas");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        VacinaController ctrl = new VacinaController(conn);
        linha[0] = new Label("Nome     ");
        linha[1] = new Label("Lote     ");
        linha[2] = new Label("Validade     ");
        linha[3] = new Label("Estoque   ");
        
        
        tblCli.addRow(linha);

        linha[0] = new Label("----------------------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("----------------------");
        linha[3] = new Label("----------------------");
        
        tblCli.addRow(linha);

        for (Vacina vac : ctrl.listarTodasVacinas(txtNome.getText())) {
            linha[0] = new Label(""+vac.getNome());
            linha[1] = new Label(vac.getLote());
            linha[2] = new Label(""+vac.getValidade());
            linha[3] = new Label(""+vac.getQtd_estoque());
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

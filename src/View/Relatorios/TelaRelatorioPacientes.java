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
import Controller.PacienteController;
import java.sql.Connection;
import Model.Paciente;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaRelatorioPacientes extends Window{
    private Button botaoSair;
    private Table tblCli;
    private Label lbnome;
    private TextBox txtNome;
    private Button btnConsultar;
       
    public TelaRelatorioPacientes() {
        super("Relatório de Pacientes");
        init();
    }

    private void init() {  
        setBorder(new Border.Standard());
        lbnome = new Label("Procure pelo nome do Paciente");
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
        tblCli = new Table(6, "Relação de Pacientes");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        PacienteController ctrl = new PacienteController(conn);
        linha[0] = new Label("CPF");
        linha[1] = new Label("Nome              ");
        linha[2] = new Label("Sexo     ");
        linha[3] = new Label("Data Nascimento   ");
        linha[4] = new Label("E-mail            ");
        linha[5] = new Label("Telefone          ");
        
        
        tblCli.addRow(linha);

        linha[0] = new Label("----------------------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("------");
        linha[3] = new Label("-----------------");
        linha[4] = new Label("----------------------");
        linha[5] = new Label("-----------------");
        
        tblCli.addRow(linha);

        for (Paciente pac : ctrl.listarTodosPacientes(txtNome.getText())) {
            linha[0] = new Label(""+pac.getCpf());
            linha[1] = new Label(pac.getNome());
            linha[2] = new Label(pac.getSexo());
            linha[3] = new Label(pac.getEmail());
            linha[4] = new Label(pac.getTelefone());
            linha[5] = new Label(""+pac.getData_nasc());
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

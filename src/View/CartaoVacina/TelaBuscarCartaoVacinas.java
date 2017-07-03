/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CartaoVacina;

import Controller.CartaoVacinaController;
import View.CartaoVacina.*;
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
import Controller.PacienteController;
import java.sql.Connection;
import Model.CartaoVacina;
;import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.component.Table;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaBuscarCartaoVacinas extends Window {
    private Panel painel01;
    private Panel pnlLista;
    private Table tblCli;
    private Button botaoSair;
    private Button botaoSalvar;
    private Button btnConsultar;
    private Label label01;
    private Label lblCpf;
    private TextBox txtCpf;
    
    private GUIScreen guiscreen;
    
    CartaoVacina cartaoVacina = new CartaoVacina();

    public TelaBuscarCartaoVacinas(GUIScreen guitelaprincipal) {
        super("Cartão de Vacinas");
        this.guiscreen = guitelaprincipal;
        init();
    }

    private void init() {
        
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        
        // Consulta por nome
        lblCpf = new Label("Procure pelo CPF do Paciente");
        txtCpf = new TextBox();
        addComponent(lblCpf);
        addComponent(txtCpf);
         btnConsultar = new Button("Consultar", new Action() {
            @Override
            public void doAction() {
                removeComponent(tblCli);
                removeComponent(botaoSair);
                if (txtCpf.getText().equals("") || txtCpf.getText().length() != 11) {
                    MessageBox.showMessageBox(guiscreen, "Erro", "Insira um CPF Válido");
                } else {
                    consulta(txtCpf.getText());
                }
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
    
    private Component[] linha = new Component[6];
    private void consulta(String texto) {
        tblCli = new Table(6, "Cartão de Vacinas");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        Connection conn = Conexao.AbrirConexao();
        CartaoVacinaController ctrl = new CartaoVacinaController(conn);
        linha[0] = new Label("Nome Vacina              ");
        linha[1] = new Label("Lote Vacina              ");
        linha[2] = new Label("Nº Dose      ");
        linha[3] = new Label("Data Aplicação      ");
        linha[4] = new Label("Preço      ");
        linha[5] = new Label("Cod Funcionario");
        tblCli.addRow(linha);

        linha[0] = new Label("----------------------");
        linha[1] = new Label("----------------------");
        linha[2] = new Label("---------");
        linha[3] = new Label("--------------------");
        linha[4] = new Label("----------------");
        linha[5] = new Label("----------------------");
        tblCli.addRow(linha);

        for (CartaoVacina cv : ctrl.mostrarCartaoVacina(txtCpf.getText())) {
            linha[0] = new Label(cv.getNome_vac());
            linha[1] = new Label(cv.getLote_vac());
            linha[2] = new Label(""+cv.getN_dose());
            linha[3] = new Label(""+cv.getData_aplicacao());
            linha[4] = new Label(""+cv.getPreco());
            linha[5] = new Label(""+cv.getCod_funcionario());
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

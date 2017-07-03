/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pacientes;

import View.Pacientes.*;
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
import Model.Paciente;
import View.ListaRadio;
import com.googlecode.lanterna.gui.component.CheckBox;
import com.googlecode.lanterna.gui.component.RadioCheckBoxList;
import java.sql.Date;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaIncluirPaciente extends Window {
    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Label label01;
    private Label lblCPF;
    private TextBox txtCPF;
    private Label lblNome;
    private TextBox txtNome;
    private Label lblEmail;
    private TextBox txtEmail;
    private Label lblTelefone;
    private TextBox txtTelefone;
    private Label lblSexo;
    private Label lblDataNasc;
    private TextBox txtDataNasc;
    private ListaRadio listc;
    
    private GUIScreen guiscreen;
    
    Paciente paciente = new Paciente();

    private static String[] opRadio = new String[] {
        "Masculino",
        "Feminino",
    };
    
    public TelaIncluirPaciente(GUIScreen guitelaprincipal) {
        super("Cadastro de Fornecedores");
        this.guiscreen = guitelaprincipal;
        init();
    }

    private void init() {
        Connection conn = Conexao.AbrirConexao();
        PacienteController fornCtrl = new PacienteController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        label01 = new Label("Incluir Paciente: ");
        addComponent(label01);
        lblCPF = new Label("CPF : ");
        txtCPF = new TextBox();
        lblNome = new Label("Nome: ");
        txtNome = new TextBox();
        lblEmail = new Label("E-mail: ");
        txtEmail = new TextBox();
        lblTelefone = new Label("Telefone: ");
        txtTelefone = new TextBox();
        lblSexo = new Label("Sexo (M - F): ");
               
        listc = new ListaRadio(opRadio);
        listc.setCheckedItemIndex(0);
        lblDataNasc = new Label("Data de Nascimento: ");
        txtDataNasc = new TextBox();
        addComponent(lblCPF);
        addComponent(txtCPF);
        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblEmail);
        addComponent(txtEmail);
        addComponent(lblTelefone);
        addComponent(txtTelefone);
        addComponent(lblSexo);
        addComponent(listc);
        addComponent(lblDataNasc);
        addComponent(txtDataNasc);
        
        botaoSalvar = new Button("Incluir", new Action() {
            @Override
            public void doAction() {
               paciente.setNome(txtNome.getText());
               paciente.setEmail(txtEmail.getText());
               paciente.setTelefone(txtTelefone.getText());
               paciente.setData_nasc(Date.valueOf(txtDataNasc.getText()));
               if (listc.getCheckedItemIndex() == 0) {
                    paciente.setSexo("M");
               } else {
                   paciente.setSexo("F");
               }
//               paciente.setData_nasc(txtDataNasc.getText());
                MessageBox.showMessageBox(guiscreen, "Paciente", fornCtrl.inserirPaciente(paciente));
            }
        });
         botaoSair = new Button("Sair", new Action() {
             @Override
            public void doAction() {
                close();
            }
         });
        addComponent(botaoSalvar);
        addComponent(botaoSair);
    }
    
}

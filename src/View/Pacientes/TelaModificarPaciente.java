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
import java.sql.Date;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaModificarPaciente extends Window {
    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Button botaoExcluir;
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
    PacienteController pCtrl;
    private String operacao;
    
    private GUIScreen guiscreen;
    
    Paciente paciente = new Paciente();

    public TelaModificarPaciente(GUIScreen guitelaprincipal, Paciente pac, String op) {
        super("Modificar Paciente");
        this.guiscreen = guitelaprincipal;
        this.paciente = pac;
        this.operacao = op;
        if (op.equals("A")) {
            Alterar();
        } else {
            Excluir();
        }
    }
    
    private static String[] opRadio = new String[] {
        "Masculino",
        "Feminino",
    };

    private void Alterar() {
        Connection conn = Conexao.AbrirConexao();
        PacienteController pacCtrl = new PacienteController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(4);
        label01 = new Label("Alterar Paciente ");
        addComponent(label01);
        lblCPF = new Label("CPF: "+ paciente.getCpf());
        lblNome = new Label("Nome ");
        txtNome = new TextBox(paciente.getNome());
        lblEmail = new Label("E-mail: ");
        txtEmail = new TextBox(paciente.getEmail());
        lblTelefone = new Label("Telefone: ");
        txtTelefone = new TextBox(paciente.getTelefone());
        lblSexo = new Label("Sexo (M - F): ");
               
        listc = new ListaRadio(opRadio);
        if (paciente.getSexo().equals("M")) {
            listc.setCheckedItemIndex(0);
        } else {
            listc.setCheckedItemIndex(1);
        }
        
        lblDataNasc = new Label("Data de Nascimento: ");
        txtDataNasc = new TextBox(""+paciente.getData_nasc());
        
        addComponent(lblCPF);
        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblEmail);
        addComponent(txtEmail);
        addComponent(lblTelefone);
        addComponent(txtTelefone);
        addComponent(listc);
        addComponent(lblDataNasc);
        addComponent(txtDataNasc);
        
        botaoSalvar = new Button("Salvar", new Action() {
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
                MessageBox.showMessageBox(guiscreen, "Paciente", pacCtrl.alterarPaciente(paciente));
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

    private void Excluir() {
        Connection conn = Conexao.AbrirConexao();
        PacienteController pacCtrl = new PacienteController(conn);
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        lblCPF = new Label("CPF: "+ paciente.getCpf());
        lblNome = new Label("Nome: "+ paciente.getNome());
        lblNome = new Label("Telefone: "+ paciente.getTelefone());
        lblNome = new Label("E-mail: "+ paciente.getEmail());
        lblNome = new Label("Sexo: "+ paciente.getSexo());
        lblNome = new Label("Data de Nascimento: "+ paciente.getData_nasc());
        addComponent(lblCPF);
        addComponent(lblNome);
        addComponent(lblTelefone);
        addComponent(lblEmail);
        addComponent(lblSexo);
        addComponent(lblDataNasc);
        
        botaoExcluir = new Button("Excluir", new Action() {
            @Override
            public void doAction() {
                MessageBox.showMessageBox(guiscreen, "Paciente", pacCtrl.excluirPaciente(paciente));
            }
        });
         botaoSair = new Button("Sair", new Action() {
             @Override
            public void doAction() {
                close();
            }
         });
        addComponent(botaoExcluir);
        addComponent(botaoSair);
    }
    
}

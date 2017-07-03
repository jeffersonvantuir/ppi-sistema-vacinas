/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.CartaoVacina.TelaBuscarCartaoVacinas;
import View.CartaoVacina.TelaSelecionarPaciente;
import View.Compras.TelaCompra;
import View.Fornecedores.TelaFornecedor;
import View.Funcionarios.TelaFuncionario;
import View.Pacientes.TelaPaciente;
import View.Vacinas.TelaVacina;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaCartaoVacina extends Window {
    private Panel painel01;
    
    private Button botaoMostrarCartao;
    private Button botaoAplicacao;
    private Button botaoSair;

    private static GUIScreen guitelaCadastros;
    
    public TelaCartaoVacina(GUIScreen gs) {
        super("Sistema de Controle de Vacinas");
        this.guitelaCadastros = gs;
        init();
    }

    private void init() {
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        
        botaoAplicacao = new Button("Mostrar Cartão Vacina", new Action() {
            @Override
            public void doAction() {
              guitelaCadastros.showWindow(new TelaBuscarCartaoVacinas(guitelaCadastros));
            }
        });
        painel01.addComponent(botaoAplicacao);
        
        botaoAplicacao = new Button("Fazer Aplicação", new Action() {
            @Override
            public void doAction() {
              guitelaCadastros.showWindow(new TelaSelecionarPaciente(guitelaCadastros));
            }
        });
        painel01.addComponent(botaoAplicacao);
        
        botaoSair = new Button("Voltar", new Action() {
             @Override
            public void doAction() {
                close();
            }
         });
        
        painel01.addComponent(botaoSair);
        addComponent(painel01);
    }   
}

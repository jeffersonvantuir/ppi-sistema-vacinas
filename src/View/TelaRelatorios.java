/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.Relatorios.TelaRelatorioCompras;
import View.Relatorios.TelaRelatorioFornecedores;
import View.Relatorios.TelaRelatorioFuncionarios;
import View.Relatorios.TelaRelatorioPacientes;
import View.Relatorios.TelaRelatorioVacinas;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Panel;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaRelatorios extends Window {
    private Panel painel01;
    
    private Button botaoPaciente;
    private Button botaoFornecedores;
    private Button botaoFuncionarios;
    private Button botaoVacinas;
    private Button botaoCompras;
    private Button botaoSair;

    private static GUIScreen guitelaCadastros;
    
    public TelaRelatorios(GUIScreen gs) {
        super("Sistema de Controle de Vacinas");
        this.guitelaCadastros = gs;
        init();
    }

    private void init() {
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        
        botaoCompras = new Button("Compras", new Action() {
            @Override
            public void doAction() {
                guitelaCadastros.showWindow(new TelaRelatorioCompras());
            }
        });
        painel01.addComponent(botaoCompras);
        
        botaoFornecedores = new Button("Fornecedores", new Action() {
            @Override
            public void doAction() {
                guitelaCadastros.showWindow(new TelaRelatorioFornecedores());
            }
        });
        painel01.addComponent(botaoFornecedores);
        
        botaoFuncionarios = new Button("Funcionarios", new Action() {
            @Override
            public void doAction() {
                guitelaCadastros.showWindow(new TelaRelatorioFuncionarios());
            }
        });
        painel01.addComponent(botaoFuncionarios);
        
        botaoPaciente = new Button("Pacientes", new Action() {
            @Override
            public void doAction() {
                guitelaCadastros.showWindow(new TelaRelatorioPacientes());
            }
        });
        painel01.addComponent(botaoPaciente);
        
        botaoVacinas = new Button("Vacinas", new Action() {
            @Override
            public void doAction() {
                guitelaCadastros.showWindow(new TelaRelatorioVacinas());
            }
        });
        painel01.addComponent(botaoVacinas);
        
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

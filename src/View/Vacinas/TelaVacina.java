/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Vacinas;

import View.Vacinas.*;
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
public class TelaVacina extends Window {
    private Panel painel01;
    private Button botaoIncluir;
    private Button botaoAlterarExcuir;
    private Button botaoSair;
    
    private static GUIScreen guitelaVacinas;
    
    public TelaVacina(GUIScreen gs) {
        super("Sistema de Controle de Vacinas");
        this.guitelaVacinas = gs;
        init();
    }

    private void init() {
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.VERTICAL);
        
        botaoIncluir = new Button("Incluir Vacina", new Action() {
            @Override
            public void doAction() {
              guitelaVacinas.showWindow(new TelaIncluirVacina(guitelaVacinas));
            }
        });
        painel01.addComponent(botaoIncluir);
        
        botaoAlterarExcuir = new Button("Alterar/Excluir Vacina", new Action() {
            @Override
            public void doAction() {
              guitelaVacinas.showWindow(new TelaAlterarExcluirVacina(guitelaVacinas));
            }
        });
        painel01.addComponent(botaoAlterarExcuir);
        
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

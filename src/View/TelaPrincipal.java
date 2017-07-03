/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.Fornecedores.TelaIncluirFornecedor;
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
public class TelaPrincipal extends Window {
    private Panel painel01;
    
    private Button botaoCadastros;
    private Button botaoCartaoVacinas;
    private Button botaoRelatorios;
    private Button botaoSair;

    private static GUIScreen guitelaprincipal;
    
    public TelaPrincipal(GUIScreen guiScreen) {
        super("Sistema de Controle de Vacinas");
        this.guitelaprincipal = guiScreen;
        init();
    }

    private void init() {
       setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        
        botaoCadastros = new Button("Cadastros", new Action() {
            @Override
            public void doAction() {
              guitelaprincipal.showWindow(new TelaCadastro(guitelaprincipal));
            }
            
        });
        painel01.addComponent(botaoCadastros);
        
        botaoCartaoVacinas = new Button("Carteira de Vacinas", new Action() {
            @Override
            public void doAction() {
              guitelaprincipal.showWindow(new TelaCartaoVacina(guitelaprincipal));
            }
            
        });
        painel01.addComponent(botaoCartaoVacinas);
        
        botaoRelatorios = new Button("Relatórios", new Action() {
            @Override
            public void doAction() {
              guitelaprincipal.showWindow(new TelaRelatorios(guitelaprincipal));
            }
            
        });
        painel01.addComponent(botaoRelatorios);
        
        botaoSair = new Button("Sair", new Action() {
             @Override
            public void doAction() {
                close();
            }
         });
        
        painel01.addComponent(botaoSair);
        addComponent(painel01);
    }
    
}

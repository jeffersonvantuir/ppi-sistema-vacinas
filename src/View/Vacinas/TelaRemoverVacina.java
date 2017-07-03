/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Vacinas;

import View.Vacinas.*;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import Model.Vacina;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaRemoverVacina {

    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Label label01;
    private Label lblCNPJ;
    private TextBox txtCNPJ;
    private Label lblNome;
    private TextBox txtNome;
    
    private GUIScreen guiscreen;
    
    Vacina vacina = new Vacina();

    public TelaRemoverVacina(GUIScreen guitelaprincipal) {
        this.guiscreen = guitelaprincipal;
    }

    
}

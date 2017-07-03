/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pacientes;

import View.Fornecedores.*;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import Model.Fornecedor;

/**
 *
 * @author Jefferson Vantuir
 */
public class TelaRemoverPaciente {

    private Panel painel01;
    private Button botaoSair;
    private Button botaoSalvar;
    private Label label01;
    private Label lblCNPJ;
    private TextBox txtCNPJ;
    private Label lblNome;
    private TextBox txtNome;
    
    private GUIScreen guiscreen;
    
    Fornecedor fornecedor = new Fornecedor();

    public TelaRemoverPaciente(GUIScreen guitelaprincipal) {
        this.guiscreen = guitelaprincipal;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.googlecode.lanterna.gui.Interactable;
import com.googlecode.lanterna.gui.component.RadioCheckBoxList;
import com.googlecode.lanterna.input.Key;

/**
 *
 * @author Jefferson Vantuir
 */
public class ListaRadio extends RadioCheckBoxList{
    public ListaRadio (String[] options) {
        super();
        for (String option : options) {
            addItem(option);
        }
    }
    
    @Override
    protected Interactable.Result unhandledKeyboardEvent(Key key) {
        Interactable.Result result = super.unhandledKeyboardEvent(key);
        
        return result;
    }
}

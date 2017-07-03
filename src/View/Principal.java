package View;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

/**
 *
 * @author Jefferson Vantuir
 */
public class Principal {
    public static Terminal terminal;
    public static Screen screen;
    public static GUIScreen guiScreen;
    
    public static void main(String[] args) {
        terminal = TerminalFacade.createTerminal();
        screen = new Screen(terminal);
        guiScreen = new GUIScreen(screen);
        
        screen.startScreen();
        
        guiScreen.showWindow(new TelaPrincipal(guiScreen));
        screen.stopScreen();
    }
}


package mylib;


import java.io.PrintStream;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrealottarini
 */
public class ComponentPair implements WebComponent {
    public WebComponent first;
    public WebComponent second;

    public void preRender(List<String> headtext) {
        
    }

    public void render(PrintStream output, List<String> headText) {
        first.render(output, headText);
        second.render(output, headText);
    }

}

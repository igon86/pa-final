package mylib;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author andrealottarini
 */
public interface WebComponent {
    void preRender(List<String> headtext);
    void render(PrintStream output,List<String> headText);
}

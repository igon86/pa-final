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
public class Button implements WebComponent{
    
    public String image;

    public Button(String string) {
        image = string;
    }

    public Button(){
        
    }
    
    @Override
    public void preRender(List<String> headtext) {
        
    }

    @Override
    public void render(PrintStream output,List<String> headtext) {
        output.println(" <button name=\"vai\" type=\"submit\"> <img src="+image+
                " align=\"middle\"> </button> ");
    }

}

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
public class Label implements WebComponent{
    public String text;

    public Label(String text){
        this.text = text;
    }

    public Label(){
        
    }

    @Override
    public void preRender(List<String> headtext) {
        
    }

    @Override
    public void render(PrintStream output,List<String> headtext) {
        output.println("<span style=\"font-weight : bold;\">"+text+"</span>");
    }


}

package mylib;

import java.io.PrintStream;
import java.util.List;

public class Label implements WebComponent{
    public String text;

    public Label(){
        
    }

    public void preRender(List<String> headtext) {
    }

    public void render(PrintStream output) {
        output.println("<span>"+text+"</span>");
    }


}

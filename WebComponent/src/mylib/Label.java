package mylib;

import java.io.PrintStream;
import java.util.List;

public class Label implements WebComponent{
    public String text;

    public Label(String text){
        this.text = text;
    }

    public Label(){
        
    }

    public void preRender(List<String> headtext) {
        headtext.add("<style type=\"text/css\">\nspan.Label {color:red;}</style>");
    }

    public void render(PrintStream output) {
        output.println("<span class=\"Label\"\">"+text+"</span>");
    }


}

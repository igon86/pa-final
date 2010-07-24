package mylib;


import java.io.PrintStream;
import java.util.List;

public class ComponentPair implements WebComponent {
    public WebComponent first;
    public WebComponent second;

    public void preRender(List<String> headtext) {
        
    }

    public void render(PrintStream output) {
        first.render(output);
        second.render(output);
    }

}

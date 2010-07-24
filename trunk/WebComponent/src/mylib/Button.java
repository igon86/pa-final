package mylib;

import java.io.PrintStream;
import java.util.List;

public class Button implements WebComponent {

    public String image;

    public Button(String string) {
        image = string;
    }

    public Button() {
    }

    @Override
    public void preRender(List<String> headtext) {
    }

    @Override
    public void render(PrintStream output) {
        output.println(" <button name=\"vai\" type=\"submit\"> <img src=" + image +
                " align=\"middle\"> </button> ");
    }
}

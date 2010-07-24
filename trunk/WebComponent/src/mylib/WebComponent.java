package mylib;

import java.io.PrintStream;
import java.util.List;

public interface WebComponent {
    void preRender(List<String> headtext);
    void render(PrintStream output);
}

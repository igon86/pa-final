package Features;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import PaFaces.PaFacesObject;
import PaFaces.Code;
import Main.*;
import java.io.PrintStream;

/**
 *
 * @author andrealottarini
 */
public class PaFacesGenerator {

    private static final String HEADER="import java.util.List;\nimport java.io.PrintStream;\nimport java.util.Calendar;\n";

    private Code code;

    public PaFacesGenerator() {
        this.code = new Code();
    }

    public void writeCode(PrintStream pout,PaFacesObject root){
        root.getCode(code);
        pout.println(code.getHead());
        pout.println(HEADER);
        pout.println("public class Page implements WebComponent {\n");
        pout.println(code.getVar());
        pout.println("\tpublic Page() {");
        pout.print(code.getConstr());
        pout.println("\t}\n\tpublic void preRender(List<String> headtext){");
        pout.print(code.getPreRender());
        pout.println("\t}\n\tpublic void render(PrintStream output, List<String> headText){");
        pout.print(code.getRender());
        pout.println("\t}");
        pout.println("}");
    }
}



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
        pout.println(code.head);
        pout.println(HEADER);
        pout.println("public class Page implements WebComponent {\n");
        pout.println(code.var);
        pout.println("\tpublic Page() {");
        pout.print(code.constr);
        pout.println("\t}\n\tpublic void preRender(List<String> headtext){");
        pout.print(code.preRender);
        pout.println("\t}\n\tpublic void render(PrintStream output, List<String> headText){");
        pout.print(code.render);
        pout.println("\t}");
        pout.println("}");
    }
}



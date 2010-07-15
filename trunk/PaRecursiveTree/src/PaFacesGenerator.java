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

    private Code code;

    public PaFacesGenerator() {
        this.code = new Code();
    }

    public void writeCode(PrintStream pout,PaFacesObject root){
        root.getCode(code);
        pout.println(code.head);
        pout.println("public class Page implements WebComponent {\n");
        pout.println(code.var);
        pout.println("public Page() {");
        pout.print(code.constr);
        pout.println("}\npublic void preRender(List<String> headtext){");
        pout.print(code.preRender);
        pout.println("}\npublic void render(PrintStream output, List<String> headtext){");
        pout.print(code.render);
        pout.println("}");
        pout.println("}");
    }
}



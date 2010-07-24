package Features;

import PaFaces.PaFacesObject;

import java.io.PrintStream;

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
        pout.println("\t}\n\tpublic void render(PrintStream output){");
        pout.print(code.getRender());
        pout.println("\t}\n}");
    }
}



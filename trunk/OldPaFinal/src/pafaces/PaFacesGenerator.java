/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafaces;

import java.io.PrintStream;
import java.util.LinkedList;

/**
 *
 * @author andrealottarini
 */
public class PaFacesGenerator {

    String header = "";
    String variables = "";
    String constructor = "";
    String render = "public void Render(Printstream output){\n";

    public PaFacesGenerator() {
    }

    public void printSpace(PrintStream pout, int levels) {
        
        for (int i = 0; i < levels; i++) {
            pout.print("\t");
        }
    }

    public void stupidGenerate(PrintStream pout, int levels, PaFacesObject root) {
        printSpace(pout, levels);
        pout.print(root.id.trim());

        for (PaFacesAttributes attr : root.getAttr()) {
            pout.print("     " + attr.id + "=" + attr.value + "    ");
        }

        pout.println("");

        if (root.children != null) {
            for (PaFacesObject children : root.children) {
                stupidGenerate(pout, levels + 1, children);
            }
        }
    }

    public void XMLgenerate(PrintStream pout, PaFacesObject root) {
        //System.out.println("GENERATE");
        if (root instanceof PaFacesText) {
            System.out.print(root.id);
        } else {
            System.out.print("<" + root.id);
            //CONTROLLI RIDONDANTI -> FIXARE

            for (PaFacesAttributes attr : root.getAttr()) {
                System.out.print(" " + attr.id + "=\"" + attr.value + "\"");
            }


            if (root.children != null) {
                // HA FIGLI QUINDI GLI METTO LA CHIUSURA
                System.out.print(">");
                for (PaFacesObject children : root.children) {
                    XMLgenerate(pout, children);
                }
                System.out.print("<\\" + root.id + ">");
            } else {
                System.out.print("\\>");
            }
        }
    }

    public void generate(PrintStream pout, PaFacesObject root) {
        // SOLO TESTO
        if (root instanceof PaFacesText) {
            //GLI SPAZI/CAPO ANDREBBERO MANTENUTI!!
            render = render.concat("output.println(\"" + root.id.trim() + "\");\n");
            System.out.println(render);
        } else if (root.id.equals("using")) {
            System.out.println("Ho beccato using");
            LinkedList<PaFacesAttributes> list = root.getAttr();
            String temp = list.getFirst().value;
            int last = temp.lastIndexOf(".");
            String lib = temp.substring(0, last);
            header = header.concat("using " + lib + "\n");
            System.out.print(header);
        } else if (root.id.equals("insert-head")) {
            //TODO
        } else {
            boolean found = false;
            // E` ROBA CHE NECESSITA IL CONTROLLO DI GENERATE
            for (PaFacesAttributes attr : root.getAttr()) {
                System.out.println(attr.toString());
                if (attr.equals(new PaFacesAttributes("code", "generate"))) {
                    found = true;
                }

            }
            if (found) {
                String id = "";
                for (PaFacesAttributes attr : root.getAttr()) {
                    //variabile
                    if (attr.id.equals("id")) {
                        id = attr.value;
                        String nome = root.id.substring(root.id.lastIndexOf(":") + 1, root.id.length());
                        variables = variables.concat("protected " + nome + " " + id + ";\n");
                        constructor = constructor.concat(id + " = new " + nome + ";\n");
                        //System.out.println(variables);
                        //VA MESSO ANCHE IL COSRUTTURE
                    } else if (attr.equals(new PaFacesAttributes("code", "generate"))) {
                        continue;
                    } //OKKIO STAI FACENDO SUPPOSIZIONI (RAGIONEVOLISSIME SULL'ORDINAMENTO
                    else {
                        render = render.concat(id + "." + attr.id + "=" + attr.value.substring(attr.value.indexOf("{") + 1, attr.value.lastIndexOf("}")) + ";\n");
                    }
                }
                //metto la chiamata di render dell'oggetto embedded
                render = render.concat(id + ".Render(output);\n");
            } else {
                //LO INCOLLO PARI PARI :)
                render = render.concat("output.println(\"<" + root.id);
                if (root.children == null) {
                    render = render.concat("/>\")\n");
                } else {
                    render = render.concat(">\")\n");
                }
                System.out.println(render);
            }
        }
        //RICORSIONE
        if (root.children != null) {
            // HA FIGLI QUINDI GLI METTO LA CHIUSURA
            for (PaFacesObject children : root.children) {
                generate(pout, children);
            }
            if (root instanceof PaFacesText) {
                render = render.concat("output.println(\"<" + root.id + "/>\")\n");
            }
            System.out.println(render);
        }

    }

    public void sbrodolaFuori(PrintStream pout) {
        pout.print(header);
        pout.println("public class Page implements WebComponent {");
        pout.print(variables);
        pout.println("public Page() {");
        pout.print(constructor);
        pout.println("}");
        pout.print(render);
        pout.println("}");
    }

    public void writeCode(PrintStream pout,PaFacesObject root){
        genera(root);
        pout.print(header);
        pout.println("public class Page implements WebComponent {");
        pout.print(variables);
        pout.println("public Page() {");
//        pout.print(constructor);
//        pout.println("}");
//        pout.print(render);
//        pout.println("}");
    }

    public void genera(PaFacesObject root){
        header = header.concat(root.getUsing());
        variables = variables.concat(root.getVar());
        for( PaFacesObject child : root.children){
            genera(child);
        }
    }
}



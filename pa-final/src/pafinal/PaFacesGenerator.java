/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafinal;

import java.io.PrintStream;

/**
 *
 * @author andrealottarini
 */
public class PaFacesGenerator {



    public PaFacesGenerator( ) {

    }

    public void printSpace(PrintStream pout, int levels) {
        for (int i = 0; i < levels; i++) {
            pout.print("\t");
        }
    }

    public void stupidGenerate(PrintStream pout, int levels, PaFacesObject root) {
        printSpace(pout, levels);
        pout.print(root.id.trim());
        if (root.attr != null) {
            for (PaFacesAttributes attr : root.attr) {
                pout.print("     " + attr.id + "=" + attr.value + "    ");
            }
        }
        pout.println("");

        if (root.children != null) {
            for (PaFacesObject children : root.children) {
                stupidGenerate(pout, levels + 1, children);
            }
        }
    }
    public void generate(PrintStream pout,PaFacesObject root) {
        //System.out.println("GENERATE");
        System.out.print("<"+root.id);
        //CONTROLLI RIDONDANTI -> FIXARE
        if (root.attr != null ) {
            for (PaFacesAttributes attr : root.attr) {
                System.out.print(" " + attr.id + "=\"" + attr.value + "\"");
            }
        }

        if (root.children != null) {
            // HA FIGLI QUINDI LI METTO LA CHIUSURA
            System.out.print(">");
            for (PaFacesObject children : root.children) {
                generate(pout, children);
            }
            System.out.print("<\\"+root.id+">");
        }
        else{
            System.out.print("\\>");
        }
    }
}

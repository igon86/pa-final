/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import javax.xml.stream.XMLStreamException;
import sun.security.pkcs.ParsingException;


/**
 *
 * @author andrealottarini
 */
public class Main {

    public static PrintStream pout;
    public static PrintStream outTree;
    public static PrintStream outCode;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, ParsingException {
        File f = new File("/Users/andrealottarini/Desktop/scanned.txt");
        pout = new PrintStream(f);

        File g = new File("/Users/andrealottarini/Desktop/out.txt");
        outTree = new PrintStream(g);

        File h = new File("/Users/andrealottarini/Desktop/code.txt");
        outCode = new PrintStream(h);

        PaFacesParser parser = new PaFacesParser("/Users/andrealottarini/Desktop/test.xml");
        PaFacesObject parseTree = parser.parseComponent();
        //parser.stupidParse();
        PaFacesGenerator generator = new PaFacesGenerator();
        generator.stupidGenerate(outTree, 0, parseTree);
        generator.XMLgenerate(outCode, parseTree);
        System.out.println("\n PARTE LA GENERATE\n");
        generator.generate(outCode, parseTree);
        generator.sbrodolaFuori(outCode);
    }
}

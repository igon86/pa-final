/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    public static PrintStream outHTML;
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

        File j = new File("/Users/andrealottarini/Desktop/out.html");
        outHTML = new PrintStream(j);

        PaFacesParser parser = new PaFacesParser("/Users/andrealottarini/Desktop/test.xml");
        PaFacesObject parseTree = parser.parseComponent();
        //parser.stupidParse();
        PaFacesGenerator generator = new PaFacesGenerator();
        generator.stupidGenerate(outTree, 0, parseTree);
        generator.XMLgenerate(outCode, parseTree);
        System.out.println("\n PARTE LA GENERATE\n");
        generator.generate(outCode, parseTree);
        generator.sbrodolaFuori(outCode);

        Calendario cal  = new Calendario(new GregorianCalendar(2010,Calendar.JUNE,2));
        //Calendario cal  = new Calendario(Calendar.getInstance());
        cal.Render(outHTML);
    }
}

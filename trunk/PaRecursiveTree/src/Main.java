/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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

    public static PrintStream genOut;
    public static PrintStream outTree;
    public static PrintStream outCode;
    public static PrintStream outHTML;
    public static PrintStream outCode2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, ParsingException {
        PaFacesGenerator generator;
        
        File f = new File("/Users/andrealottarini/Desktop/PA_stuff/generator.txt");
        genOut = new PrintStream(f);

        File g = new File("/Users/andrealottarini/Desktop/PA_stuff/out.txt");
        outTree = new PrintStream(g);

        File h = new File("/Users/andrealottarini/Desktop/PA_stuff/code.txt");
        outCode = new PrintStream(h);

        File j = new File("/Users/andrealottarini/Desktop/PA_stuff/out.html");
        outHTML = new PrintStream(j);

        File k = new File("/Users/andrealottarini/Desktop/PA_stuff/CAZZO.txt");
        outCode2 = new PrintStream(k);

        PaFacesParser parser = new PaFacesParser("/Users/andrealottarini/Desktop/PA_stuff/test2.xml");
        PaFacesObject parseTree = parser.parseComponent();

        generator = new PaFacesGenerator();
//        generator.stupidGenerate(outTree, 0, parseTree);
//        generator.XMLgenerate(outCode, parseTree);
//        System.out.println("\n PARTE LA GENERATE\n");
//        generator.generate(outCode, parseTree);
//        generator.sbrodolaFuori(outCode);

//
//        generator = new PaFacesGenerator();
        generator.writeCode(outCode2,parseTree);
//
//        Calendario cal  = new Calendario(new GregorianCalendar(2010,Calendar.JUNE,2));
//        //Calendario cal  = new Calendario(Calendar.getInstance());
//        cal.Render(outHTML);
    }
}

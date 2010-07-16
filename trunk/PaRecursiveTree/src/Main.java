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

    public static final String PA_DIRECTORY = "/Users/andrealottarini/Desktop/PA_stuff/";

    public static PrintStream outGen;
    public static PrintStream outCode;
    public static PrintStream outHTML;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, ParsingException {
        PaFacesGenerator generator;

        File code = new File(PA_DIRECTORY+"code.txt");
        outCode = new PrintStream(code);

        File gen = new File(PA_DIRECTORY+"generatore.txt");
        outGen = new PrintStream(gen);

        PaFacesParser parser = new PaFacesParser(PA_DIRECTORY+"test3.xml");
        PaFacesObject parseTree = parser.parseComponent();

        generator = new PaFacesGenerator();
//        generator.stupidGenerate(outTree, 0, parseTree);
//        generator.XMLgenerate(outCode, parseTree);
//        System.out.println("\n PARTE LA GENERATE\n");
//        generator.generate(outCode, parseTree);
//        generator.sbrodolaFuori(outCode);

//
//        generator = new PaFacesGenerator();
        generator.writeCode(outCode,parseTree);
//
//        Calendario cal  = new Calendario(new GregorianCalendar(2010,Calendar.JUNE,2));
//        //Calendario cal  = new Calendario(Calendar.getInstance());
//        cal.Render(outHTML);
    }
}

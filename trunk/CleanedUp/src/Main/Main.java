package Main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import PaFaces.PaFacesObject;
import Features.PaFacesGenerator;
import Features.PaFacesParser;
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

    public static final String PA_DIRECTORY = "/Users/andrealottarini/Desktop/PA_stuff/";
    public static final String PA_OUT_DIRECTORY = "/Users/andrealottarini/NetBeansProjects/TextBox/src/";

    public static PrintStream outGen;
    public static PrintStream outCode;
    public static PrintStream outHtml;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, ParsingException {
        PaFacesGenerator generator;

        File code = new File(PA_OUT_DIRECTORY+"Page.java");
        outCode = new PrintStream(code);

        File gen = new File(PA_DIRECTORY+"generatore.txt");
        outGen = new PrintStream(gen);

        File html = new File(PA_DIRECTORY+"out.html");
        outHtml = new PrintStream(html);

        PaFacesParser parser = new PaFacesParser(PA_DIRECTORY+"test3.xml");
        PaFacesObject parseTree = parser.parseComponent();

        generator = new PaFacesGenerator();

        generator.writeCode(outCode,parseTree);

    }
}

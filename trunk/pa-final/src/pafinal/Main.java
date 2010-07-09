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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, ParsingException {
        File f = new File("/Users/andrealottarini/Desktop/scanned.txt");
        pout = new PrintStream(f);
        PaFacesParser parser = new PaFacesParser("/Users/andrealottarini/Desktop/test.xml");
        parser.parseComponent();
        //parser.stupidParse();
    }
}

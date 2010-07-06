/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import javax.xml.stream.XMLStreamException;


/**
 *
 * @author andrealottarini
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        PaFacesTokenizer scanner = new PaFacesTokenizer("/Users/andrealottarini/Desktop/test.xml");
        File f = new File("/Users/andrealottarini/Desktop/scanned.txt");
        PrintStream pout = new PrintStream(f);
        String token = scanner.next();
        while (token != null){
            System.out.println("TOKEN: "+token);
            token = scanner.next();
            pout.println(token);
        }
    }
}

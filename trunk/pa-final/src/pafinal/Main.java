/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafinal;

import java.io.*;
import javax.xml.stream.*;

/**
 *
 * @author andrealottarini
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        System.out.println("Creata la factory");
        File f = new File("/Users/andrealottarini/Desktop/test.xml");
        System.out.println("Aperto il file");
        FileInputStream fin = new FileInputStream(f);
        System.out.println("Aperto il FileInputStream");
        XMLStreamReader reader = inputFactory.createXMLStreamReader(fin);
        System.out.println("Creato il reader");
        System.out.println("ATTRIBUTE" + XMLStreamConstants.ATTRIBUTE);
        System.out.println("START DOCUMENT" + XMLStreamConstants.START_DOCUMENT);
        System.out.println("SPACE" + XMLStreamConstants.SPACE);
        System.out.println("START ELEMENT" + XMLStreamConstants.START_ELEMENT);
        System.out.println("END ELEMENT" + XMLStreamConstants.END_ELEMENT);
        System.out.println("CHARACTERS" + XMLStreamConstants.CHARACTERS);
        System.out.println("END_DOCUMENT" + XMLStreamConstants.END_DOCUMENT);
        String text = "";
        while (reader.hasNext()) {
            int test = reader.next();
            System.out.println("Ho letto" + test);
            switch (test) {
                case XMLStreamReader.CHARACTERS: {
                    if (!reader.getText().trim().isEmpty()) {
                        text = reader.getText();
                        System.out.println("     "+text);
                    } else {
                        text = "";
                    }
                    break;
                }
                case XMLStreamReader.START_ELEMENT: {

                    int attr = reader.getAttributeCount();
                    String local = reader.getLocalName();
                    System.out.println("Ci sono "+attr + " attributi e ho letto "+local);
                    for (int i = 0;i<attr;i++){
                        local = reader.getAttributeLocalName(i);
                        System.out.println(" "+local);
                    }
                    break;
                }
                case XMLStreamReader.END_ELEMENT: {

                    String local = reader.getLocalName();
                    System.out.println("End Element "+local);
                    break;
                }
                case XMLStreamReader.END_DOCUMENT: {

                    System.out.println("End Document");
                }
            }
        }
    }
}

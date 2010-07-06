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
public class PaFacesTokenizer {

    private int actualAttribute, numAttribute;
    private XMLStreamReader reader;
    private boolean retname = false;

    public PaFacesTokenizer(String filename) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        inputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);

        System.out.println("Creata la factory");
        File f = new File(filename);
        System.out.println("Aperto il file");
        FileInputStream fin = new FileInputStream(f);
        System.out.println("Aperto il FileInputStream");
        reader = inputFactory.createXMLStreamReader(fin);
        System.out.println("Creato il reader");
        System.out.println("ATTRIBUTE" + XMLStreamConstants.ATTRIBUTE);
        System.out.println("START DOCUMENT" + XMLStreamConstants.START_DOCUMENT);
        System.out.println("SPACE" + XMLStreamConstants.SPACE);
        System.out.println("START ELEMENT" + XMLStreamConstants.START_ELEMENT);
        System.out.println("END ELEMENT" + XMLStreamConstants.END_ELEMENT);
        System.out.println("CHARACTERS" + XMLStreamConstants.CHARACTERS);
        System.out.println("END_DOCUMENT" + XMLStreamConstants.END_DOCUMENT);
    }

    public String next() throws XMLStreamException {

        if (actualAttribute < numAttribute){
            System.out.println("Sono nel ciclo iniziale");
            retname = !retname;
            if(retname){
                String dummy = reader.getAttributeLocalName(actualAttribute);
                System.out.println("AttributeLocalName   "+dummy);
                return dummy;
            }
            else{
                String dummy = reader.getAttributeValue(actualAttribute++);
                System.out.println("AttributeValue   "+dummy);
                return dummy;
            }
        }

        String text = "";
        while (reader.hasNext()) {
            int test = reader.next();
            System.out.println("Ho letto" + test);
            switch (test) {
                case XMLStreamReader.CHARACTERS: {
                    if (!reader.getText().trim().isEmpty()) {
                        text = reader.getText();
                        //System.out.println("     " + text);
                        return text;
                    }
                    break;
                }
                case XMLStreamReader.START_ELEMENT: {

                    numAttribute = reader.getAttributeCount();
                    String local = reader.getLocalName();
                    System.out.println("Ci sono " + numAttribute + " Attributi");
                    actualAttribute = 0;
                    return local;
                }
                case XMLStreamReader.END_ELEMENT: {

                    String local = reader.getLocalName();
                    System.out.println("End Element " + local);
                    return local;
                }
                case XMLStreamReader.END_DOCUMENT: {

                    System.out.println("End Document");
                    return "$";
                }
            }
        }
        return null;
    }
}

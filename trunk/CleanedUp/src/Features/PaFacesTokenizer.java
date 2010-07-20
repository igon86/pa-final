package Features;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.*;

/**
 *
 * @author andrealottarini
 */
public class PaFacesTokenizer {

    private int actualAttribute, numAttribute;
    private XMLStreamReader reader;
    boolean retname = false;
    private int actualNamespace, numNamespace;

    public int sectionType;

    public PaFacesTokenizer(String filename) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        //inputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);

        File f = new File(filename);
        
        FileInputStream fin = new FileInputStream(f);
        
        reader = inputFactory.createXMLStreamReader(fin);
    }

    public String next() throws XMLStreamException {

        if (actualNamespace < numNamespace){
            sectionType = XMLStreamConstants.NAMESPACE;
            System.out.println("SCANNER: sono nel ciclo namespace");
            retname = !retname;
            if(retname){
                String dummy = reader.getNamespacePrefix(actualNamespace);
                System.out.println("SCANNER: NamespacePrefix:   "+dummy);
                return dummy;
            }
            else{
                String dummy = reader.getNamespaceURI(actualNamespace++);
                System.out.println("SCANNER: AttributeValue:   "+dummy);
                return dummy;
            }
        }

        if (actualAttribute < numAttribute){
            sectionType = XMLStreamConstants.ATTRIBUTE;
            System.out.println("SCANNER: Sono nel ciclo attributi");
            retname = !retname;
            if(retname){
                String dummy = reader.getAttributeLocalName(actualAttribute);
                System.out.println("SCANNER: AttributeLocalName:   "+dummy);
                return dummy;
            }
            else{
                String dummy = reader.getAttributeValue(actualAttribute++);
                System.out.println("SCANNER: AttributeValue:   "+dummy);
                return dummy;
            }
        }

        String text = "";
        while (reader.hasNext()) {
            sectionType = reader.next();
            System.out.println("SCANNER: Ho letto" + sectionType);
            switch (sectionType) {
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
                    numNamespace = reader.getNamespaceCount();
                    String local = reader.getLocalName();
                    System.out.println("SCANNER: " +local+" Ci sono " + numAttribute + " Attributi e " + numNamespace +" Namespace");
                    actualAttribute = 0;
                    actualNamespace = 0;
                    return local;
                }
                case XMLStreamReader.END_ELEMENT: {

                    String local = reader.getLocalName();
                    System.out.println("SCANNER: End Element " + local);
                    return local;
                }
                case XMLStreamReader.END_DOCUMENT: {

                    System.out.println("SCANNER: End Document");
                    return "$";
                }
            }
        }
        return null;
    }

    public void close() throws XMLStreamException{
        reader.close();
    }
}

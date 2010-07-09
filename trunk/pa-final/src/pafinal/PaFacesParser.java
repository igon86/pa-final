/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafinal;

import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import sun.security.pkcs.ParsingException;

/**
 *
 * @author andrealottarini
 */
public class PaFacesParser {

    private PaFacesTokenizer scanner;
    private String lookahead;

    public PaFacesParser(String filename) throws FileNotFoundException, XMLStreamException {
        scanner = new PaFacesTokenizer(filename);
        lookahead = scanner.next();
        //manca la roba dove scrivere
    }

    public void stupidParse() throws XMLStreamException {
        while (lookahead != null) {
            System.out.println("TOKEN: " + lookahead);
            lookahead = scanner.next();
            Main.pout.println(lookahead);
        }

    }

    public void parseComponent() throws XMLStreamException, ParsingException {
        System.out.println("Parse Component: " + lookahead);
        parseElement();
        match("$");
        scanner.close();
    }

    private void parseElement() throws XMLStreamException, ParsingException {
        System.out.println("Parse Element: " + lookahead);
        String actual = lookahead;
        /* parso il primo TAG necessario */
        parseTag();
        if (scanner.sectionType != XMLStreamConstants.END_ELEMENT) {
            System.out.println("Parse Element: Elemento "+actual+" NON terminato analizzo il sottoalbero");
            parseTree();
            
        }
        else{
            System.out.println("Parse Element: Elemento terminato chiamo la next");   
        }
        lookahead = scanner.next();
    }

    private void parseTree() throws XMLStreamException, ParsingException {
        System.out.println("Parse Tree: " + lookahead);
        /* controllo di non avere un tree vuoto */
        if (scanner.sectionType != XMLStreamConstants.END_ELEMENT) {

            if (scanner.sectionType == XMLStreamConstants.CHARACTERS) {
                
                //metti il testo da qualche parte
                lookahead = scanner.next();
            }
            //if(scanner.sectionType == XMLStreamConstants.CHARACTERS)
            else {
                parseElement();
            }
            parseTree();
        }
    }

    private boolean parseClosingTag() throws XMLStreamException, ParsingException {
        System.out.println("Parse Closing Tag: " + lookahead);
        if (scanner.sectionType == XMLStreamConstants.END_ELEMENT) {
            System.out.println("Closing Tag Ho beccato l'end element e chiamo la next");
            lookahead = scanner.next();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return true     Non ho chiuso il tag
     *         false    Altrimenti
     */
    private void parseTag() throws XMLStreamException, ParsingException {
        System.out.println("Parse Tag: " + lookahead);
        if (scanner.sectionType == XMLStreamConstants.START_ELEMENT) {
            // QUESTA DISTINZIONE HA SENSO SOLO SE DECIDERO DI DIFFERENZIARE I VARI TAG
            if (lookahead.equals("using")) {
                lookahead = scanner.next();
                parseAttribute();
            } else if (lookahead.equals("insert-head")) {
                lookahead = scanner.next();
                parseAttribute();
            } else {
                //aggiungo il tagname alla macchina
                System.out.println("parseTag: chiamo la next per vedere gli attributi");
                lookahead = scanner.next();
                parseAttribute();
            }
        }
    }

    private void parseAttribute() throws XMLStreamException {
        System.out.println("Parse Attribute: " + lookahead);
        if (scanner.sectionType == XMLStreamConstants.ATTRIBUTE) {
            System.out.println("CI sono attributi");
            while(scanner.sectionType == XMLStreamConstants.ATTRIBUTE) scanner.next();
        }
        else System.out.println("NON ci sono attributi");
    }

    private void match(String s) throws ParsingException{
        if(! lookahead.equals(s) )
            throw new ParsingException();
    }
}

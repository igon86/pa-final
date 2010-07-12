/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafinal;

import java.io.FileNotFoundException;
import java.util.LinkedList;
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

    //deprecated
    public void stupidParse() throws XMLStreamException {
        while (lookahead != null) {
            System.out.println("TOKEN: " + lookahead);
            lookahead = scanner.next();
            Main.pout.println(lookahead);
        }

    }

    public PaFacesObject parseComponent() throws XMLStreamException, ParsingException {
        System.out.println("Parse Component: " + lookahead);
        //Un po brutale?
        PaFacesObject machine;
        machine = parseElement();
        match("$");
        scanner.close();
        return machine;
    }

    private PaFacesObject parseElement() throws XMLStreamException, ParsingException {
        System.out.println("Parse Element: " + lookahead);
        String actual = lookahead;
        /* parso il primo TAG necessario e lo assegno al nodo che mi rappresenta*/
        PaFacesObject me = parseTag();
        if (scanner.sectionType != XMLStreamConstants.END_ELEMENT) {
            //C'E` UN TREE
            System.out.println("Parse Element: Elemento "+actual+" NON terminato analizzo il sottoalbero");
            //ESSENDO UN TREE LA SU ROBA STA NEI FIGLIOLI
            me.children = parseTree();
        }
        /* Either I parsed a tree or a single element I need to match its closing tag*/
        parseClosingTag();
        return me;
    }

    private LinkedList<PaFacesObject> parseTree() throws XMLStreamException, ParsingException {
        System.out.println("Parse Tree: " + lookahead);
        /* controllo di non avere un tree vuoto */
        LinkedList<PaFacesObject> ret = new LinkedList<PaFacesObject>();
        LinkedList<PaFacesObject> next;
        if (scanner.sectionType != XMLStreamConstants.END_ELEMENT) {

            if (scanner.sectionType == XMLStreamConstants.CHARACTERS) {
                
                //metti il testo da qualche parte
                PaFacesObject temp = new PaFacesObject(lookahead);
                temp.onlyText=true;
                ret.add(temp);
                lookahead = scanner.next();

            }
            //if(scanner.sectionType == XMLStreamConstants.CHARACTERS)
            else {
                ret.add(parseElement());
            }
            //RICORSIONE
            next = parseTree();
            // MI APPICCICO I VICINI
            ret.addAll(next);
        }
        //RITORNO LA LISTA
        return ret;
    }

    // JUST CHECK WHETHER  A CLOSING TAG EXISTS AND TAKES THE NEW LOOKAHEAD SYMBOL
    // IT EXPLICETELY HAS NO RETURN STATUS
    private void parseClosingTag() throws XMLStreamException, ParsingException {
        System.out.println("Parse Closing Tag: " + lookahead);
        if (scanner.sectionType == XMLStreamConstants.END_ELEMENT) {
            System.out.println("Closing Tag Ho beccato l'end element e chiamo la next");
            lookahead = scanner.next();
        } else {
            throw new ParsingException();
        }
    }

    // SHOULD BE INVOKED ONLY ON A OPEN TAG and STOPS at the CLOSING TAG
   private PaFacesObject parseTag() throws XMLStreamException, ParsingException {
        System.out.println("Parse Tag: " + lookahead);
        if (scanner.sectionType == XMLStreamConstants.START_ELEMENT) {
            // QUESTA DISTINZIONE HA SENSO SOLO SE DECIDERO DI DIFFERENZIARE I VARI TAG!!
//            if (lookahead.equals("using")) {
//                lookahead = scanner.next();
//                parseAttribute();
//            } else if (lookahead.equals("insert-head")) {
//                lookahead = scanner.next();
//                parseAttribute();
//            } else {
                //aggiungo il tagname alla macchina
                PaFacesObject ret = new PaFacesObject(lookahead);
                System.out.println("parseTag: chiamo la next per vedere gli attributi");
                lookahead = scanner.next();
                ret.attr = parseAttribute();
                return ret;
//            }
        }
        return null;
    }

    private LinkedList<PaFacesAttributes> parseAttribute() throws XMLStreamException {
        System.out.println("Parse Attribute: " + lookahead);
        LinkedList<PaFacesAttributes> ret = new LinkedList<PaFacesAttributes>();
        String id=null;
        String value;
        if (scanner.sectionType == XMLStreamConstants.ATTRIBUTE) {
            System.out.println("CI sono attributi");
            
            while(scanner.sectionType == XMLStreamConstants.ATTRIBUTE){
                //E` il nome
                if (scanner.retname) id = lookahead;
                //e` il valore
                else{
                    value = lookahead;
                    ret.add(new PaFacesAttributes(id, value));
                }
                lookahead = scanner.next();
            }
            return ret;
        }
        else{
            System.out.println("NON ci sono attributi");
            //Sarebbe piu` giusto ritornare null?
            return ret;
        }
    }

    private void match(String s) throws ParsingException{
        if(! lookahead.equals(s) )
            throw new ParsingException();
    }
}

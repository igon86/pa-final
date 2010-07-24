package Features;

import PaFaces.*;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import javax.xml.stream.*;
import sun.security.pkcs.ParsingException;

public class PaFacesParser {

    private PaFacesTokenizer scanner;
    private String lookahead;
    private boolean markup;  //presence of generate tag

    public PaFacesParser(String filename) throws FileNotFoundException, XMLStreamException {
        scanner = new PaFacesTokenizer(filename);
        lookahead = scanner.next();
        markup = false;
    }

    public PaFacesObject parseComponent() throws XMLStreamException, ParsingException {

        PaFacesObject machine;
        machine = parseElement();
        match("$");
        scanner.close();
        return machine;
    }

    private PaFacesObject parseElement() throws XMLStreamException, ParsingException {

        /*parsing of the first tag*/
        PaFacesObject machine = parseTag();
        if (scanner.sectionType != XMLStreamConstants.END_ELEMENT) {

            machine.children = parseTree();
        }
        /* Either I parsed a tree or a single element I need to match its closing tag*/
        if (machine != null) {
            LinkedList<PaFacesObject> sons = parseTree();
            if (sons != null) {
                machine.children = sons;
            }
            parseClosingTag();
            return machine;
        }
        return null;
    }

    private LinkedList<PaFacesObject> parseTree() throws XMLStreamException, ParsingException {

        /* check whether text or element is present */
        PaFacesObject actual = parseText();
        if (actual == null) {
            actual = parseElement();
        }

        if (actual != null) {
            LinkedList<PaFacesObject> ret = new LinkedList<PaFacesObject>();
            ret.add(actual);

            LinkedList<PaFacesObject> next; /* check for children */
            next = parseTree();
            if (next != null) {
                ret.addAll(next);
            }
            return ret;
        } else {
            return null;
        }
    }

    private void parseClosingTag() throws XMLStreamException, ParsingException {

        if (scanner.sectionType == XMLStreamConstants.END_ELEMENT) {

            lookahead = scanner.next();
        } else {
            throw new ParsingException();
        }
    }

    private PaFacesObject parseTag() throws XMLStreamException, ParsingException {
        if (scanner.sectionType == XMLStreamConstants.START_ELEMENT) {

            PaFacesElement ret;
            LinkedList<PaFacesAttribute> attributes = new LinkedList<PaFacesAttribute>();
            String name = "";
            String id = lookahead;
            lookahead = scanner.next();
            PaFacesAttribute attr = parseAttribute();

            while (attr != null) {

                attributes.add(attr);
                if (attr.id.equals("code") && attr.value.equals("generate")) {
                    markup = true;
                }
                if (attr.id.equals("id")) {
                    name = attr.value;
                }
                attr = parseAttribute();
            }

            if (id.equals("using")) {
                ret = new PaFacesUsing(id);
            } else if (id.equals("insert-head")) {
                ret = new PaFacesInsert(id);
            } else {
                if (markup) {
                    ret = new PaFacesInstance(id, name);
                } else {
                    ret = new PaFacesHtml(id);
                }
            }
            markup = false;
            ret.attr = attributes;
            return ret;
        }
        return null;
    }

    private PaFacesAttribute parseAttribute() throws XMLStreamException {


        String id = null, value = null;

        if (scanner.sectionType == XMLStreamConstants.ATTRIBUTE) {
            id = lookahead;
            lookahead = scanner.next();
            value = lookahead;
            lookahead = scanner.next();
            return new PaFacesAttribute(id, value);
        }

        return null;

    }

    private PaFacesObject parseText() throws XMLStreamException {

        if (scanner.sectionType == XMLStreamConstants.CHARACTERS) {
            PaFacesText ret = new PaFacesText(lookahead);
            lookahead = scanner.next();
            return ret;
        }

        return null;

    }

    private void match(String s) throws ParsingException {
        if (!lookahead.equals(s)) {
            throw new ParsingException();
        }
    }
}

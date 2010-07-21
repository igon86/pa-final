package Features;

import java.io.*;
import javax.xml.stream.*;

public class PaFacesTokenizer {

    private int actualAttribute, numAttribute;
    private XMLStreamReader reader;
    boolean retname = false;
    private int actualNamespace, numNamespace;
    public int sectionType;

    public PaFacesTokenizer(String filename) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        File f = new File(filename);

        FileInputStream fin = new FileInputStream(f);

        reader = inputFactory.createXMLStreamReader(fin);
    }

    public String next() throws XMLStreamException {

        if (actualNamespace < numNamespace) {
            sectionType = XMLStreamConstants.NAMESPACE;

            retname = !retname;
            if (retname) {
                String dummy = reader.getNamespacePrefix(actualNamespace);

                return dummy;
            } else {
                String dummy = reader.getNamespaceURI(actualNamespace++);

                return dummy;
            }
        }

        if (actualAttribute < numAttribute) {
            sectionType = XMLStreamConstants.ATTRIBUTE;

            retname = !retname;
            if (retname) {
                String dummy = reader.getAttributeLocalName(actualAttribute);

                return dummy;
            } else {
                String dummy = reader.getAttributeValue(actualAttribute++);

                return dummy;
            }
        }

        String text = "";
        while (reader.hasNext()) {
            sectionType = reader.next();

            switch (sectionType) {
                case XMLStreamReader.CHARACTERS: {
                    if (!reader.getText().trim().isEmpty()) {
                        text = reader.getText().trim();

                        return text;
                    }
                    break;
                }
                case XMLStreamReader.START_ELEMENT: {

                    numAttribute = reader.getAttributeCount();
                    numNamespace = reader.getNamespaceCount();
                    String local = reader.getLocalName();

                    actualAttribute = 0;
                    actualNamespace = 0;
                    return local;
                }
                case XMLStreamReader.END_ELEMENT: {

                    String local = reader.getLocalName();

                    return local;
                }
                case XMLStreamReader.END_DOCUMENT: {
                    return "$";
                }
            }
        }
        return null;
    }

    public void close() throws XMLStreamException {
        reader.close();
    }
}

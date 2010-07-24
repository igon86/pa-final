package Features;

import java.io.*;
import javax.xml.stream.*;

public class PaFacesTokenizer {

    private int actualAttribute, numAttribute;
    private XMLStreamReader reader;
    boolean retname = false;
    public int sectionType;

    public PaFacesTokenizer(String filename) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        inputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
        
        File f = new File(filename);

        FileInputStream fin = new FileInputStream(f);

        reader = inputFactory.createXMLStreamReader(fin);
    }

    public String next() throws XMLStreamException {

        if (actualAttribute < numAttribute) {
            sectionType = XMLStreamConstants.ATTRIBUTE;

            retname = !retname;
            if (retname) {
                return reader.getAttributeLocalName(actualAttribute);

            } else {
                return reader.getAttributeValue(actualAttribute++);

            }
        }


        while (reader.hasNext()) {
            sectionType = reader.next();

            switch (sectionType) {
                case XMLStreamReader.CHARACTERS: {
                    if (!reader.getText().trim().isEmpty()) {
                        return reader.getText().trim();
                    }
                    break;
                }
                case XMLStreamReader.START_ELEMENT: {
                    actualAttribute = 0;
                    numAttribute = reader.getAttributeCount();
                    return reader.getLocalName();
                }
                case XMLStreamReader.END_ELEMENT: {

                    return reader.getLocalName();

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

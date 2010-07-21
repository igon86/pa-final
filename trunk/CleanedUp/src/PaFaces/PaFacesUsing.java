package PaFaces;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import PaFaces.Code;
import java.util.Iterator;
/**
 *
 * @author andrealottarini
 */
public class PaFacesUsing extends PaFacesMarkup {

    public PaFacesUsing() {
        super();
    }

    public PaFacesUsing(String id) {
        super(id);
    }

    @Override
    public void getCode(Code code) {
        //System.out.println("Ho beccato using");
        //POTREBBE NON AVERE SENSO
        String temp = this.attr.getFirst().id;
        code.getHead().append("import " + temp + ".*;\n");
        Iterator<PaFacesObject> i = children.iterator();
        
        while(i.hasNext()) i.next().getCode(code);
        
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}

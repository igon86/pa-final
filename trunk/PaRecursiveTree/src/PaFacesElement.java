/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.LinkedList;

/**
 *
 * @author andrealottarini
 */
public abstract class PaFacesElement extends PaFacesObject{
    public LinkedList<PaFacesAttributes> attr;

    public PaFacesElement(){
        super();
        attr = new LinkedList<PaFacesAttributes>();
    }

    public PaFacesElement(String id){
        super(id);
        attr = new LinkedList<PaFacesAttributes>();
    }

    @Override
    public LinkedList<PaFacesAttributes> getAttr() {
        return this.attr;
    }


}

package PaFaces;

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
    public LinkedList<PaFacesAttribute> attr;

    public PaFacesElement(){
        super();
        attr = new LinkedList<PaFacesAttribute>();
    }

    public PaFacesElement(String id){
        super(id);
        attr = new LinkedList<PaFacesAttribute>();
    }

}

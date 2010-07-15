/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.LinkedList;

/**
 *
 * @author andrealottarini
 */
public abstract class PaFacesObject {

    public String id;
    public LinkedList<PaFacesObject> children;
    Iterable<PaFacesAttributes> getAttr;

    PaFacesObject(){
        this.children = new LinkedList<PaFacesObject>();
    }

    PaFacesObject(String id){
        this();
        this.id = id;
    }

    //da eliminare
    public abstract LinkedList<PaFacesAttributes> getAttr();

    public abstract void getCode(Code code);

}

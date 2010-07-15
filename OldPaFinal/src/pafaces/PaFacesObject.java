/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pafaces;

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

    public abstract LinkedList<PaFacesAttributes> getAttr();

    public abstract String getUsing();
    public abstract String getVar();
    public abstract String getConstr();
    public abstract String getPreRender();
    public abstract String getRender();

}

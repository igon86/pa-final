/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pafinal;

import java.util.LinkedList;

/**
 *
 * @author andrealottarini
 */
public class PaFacesObject {

    public String id;
    public LinkedList<PaFacesObject> children;
    public LinkedList<PaFacesAttributes> attr;
    public boolean onlyText;

    PaFacesObject(String id){
        this.id = id;
        this.onlyText = false;
    }

}

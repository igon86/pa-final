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

    private String id;
    private LinkedList<PaFacesObject> children;

    PaFacesObject(String id){
        this.id = id;
        children = new LinkedList<PaFacesObject>();
    }
}

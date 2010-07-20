package PaFaces;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import PaFaces.Code;

import java.util.LinkedList;

/**
 *
 * @author andrealottarini
 */
public abstract class PaFacesObject {

    public String id;
    public LinkedList<PaFacesObject> children;
    
    PaFacesObject(){
        this.children = new LinkedList<PaFacesObject>();
    }

    PaFacesObject(String id){
        this();
        this.id = id;
    }

    public abstract void getCode(Code code);

    public abstract String getName();

}

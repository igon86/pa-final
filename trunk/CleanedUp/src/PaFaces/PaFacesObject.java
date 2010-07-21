package PaFaces;

import Features.Code;

import java.util.LinkedList;

public abstract class PaFacesObject {

    public String id;
    public LinkedList<PaFacesObject> children;

    PaFacesObject() {
        this.children = new LinkedList<PaFacesObject>();
    }

    PaFacesObject(String id) {
        this();
        this.id = id;
    }

    public abstract void getCode(Code code);

    public abstract String getName();
}

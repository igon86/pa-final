package PaFaces;

import java.util.LinkedList;

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

package PaFaces;

import java.util.Iterator;

public class PaFacesUsing extends PaFacesMarkup {

    public PaFacesUsing() {
        super();
    }

    public PaFacesUsing(String id) {
        super(id);
    }

    @Override
    public void getCode(Code code) {
        String temp = this.attr.getFirst().id;
        code.getHead().append("import " + temp + ".*;\n");
        Iterator<PaFacesObject> i = children.iterator();
        
        while(i.hasNext()) i.next().getCode(code);
        
    }

    @Override
    public String getName() {
        return id;
    }


}

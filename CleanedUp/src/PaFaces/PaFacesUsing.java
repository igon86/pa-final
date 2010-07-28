package PaFaces;

import Features.Code;

public class PaFacesUsing extends PaFacesElement {

    public PaFacesUsing() {
        super();
    }

    public PaFacesUsing(String id) {
        super(id);
    }

    @Override
    public void getCode(Code code) {
        code.getHead().append("import " + this.attr.getFirst().id + ".*;\n");
    }

    @Override
    public String getName() {
        return id;
    }


}

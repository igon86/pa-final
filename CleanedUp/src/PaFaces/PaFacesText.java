package PaFaces;

import Features.Code;

public class PaFacesText extends PaFacesObject {

    public PaFacesText() {
        super();
    }

    public PaFacesText(String id) {
        super(id);
    }

    @Override
    public void getCode(Code code) {
        code.getRender().append("\t\toutput.println(\"" + this.id + "\");\n");
    }

    @Override
    public String getName() {
        return this.id;
    }
}

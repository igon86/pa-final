package PaFaces;

import Features.Code;

public class PaFacesInsert extends PaFacesMarkup {

    public PaFacesInsert() {
        super();
    }

    public PaFacesInsert(String id) {
        super(id);
    }

    @Override
    public void getCode(Code code) {

        code.getRender().append("\t\tfor ( String s : headText) output.println(s);\n");
    }

    @Override
    public String getName() {
        return "";
    }
}

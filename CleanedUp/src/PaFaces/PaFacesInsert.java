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
        code.getVar().append("\tpublic List<String> preRendered;\n");
        code.getRender().append("\t\tfor ( String s : preRendered) output.println(s);\n");
    }

    @Override
    public String getName() {
        return "";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author andrealottarini
 */
public class PaFacesInsert extends PaFacesMarkup{

    public PaFacesInsert() {
        super();
    }

    public PaFacesInsert(String id) {
        super(id);
    }

    @Override
    public void getCode(Code code) {
        Main.outGen.println("GEN: "+this.id);
        code.render = code.render.concat("\t\tfor ( string s : headText) output.println(s);\n");
    }

    @Override
    public String getName() {
        return "";
    }

}

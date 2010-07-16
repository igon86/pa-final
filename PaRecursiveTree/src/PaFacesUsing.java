/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrealottarini
 */
public class PaFacesUsing extends PaFacesMarkup {

    public PaFacesUsing() {
        super();
    }

    public PaFacesUsing(String id) {
        super(id);
    }

    @Override
    public void getCode(Code code) {
        //System.out.println("Ho beccato using");
        //POTREBBE NON AVERE SENSO
        String temp = this.attr.getFirst().id;
        code.head = code.head.concat("import " + temp + ".*;\n");
        // se faccio il namespace a garbo ci sono i figlioli
        for ( PaFacesObject child : children ){
            child.getCode(code);
        }
        //System.out.print(header);
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}

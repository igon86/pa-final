/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pafaces;

/**
 *
 * @author andrealottarini
 */
public class PaFacesUsing extends PaFacesMarkup{

    public PaFacesUsing(){
        super();
    }

    public PaFacesUsing(String id){
        super(id);
    }

    @Override
    public String getUsing() {
        String temp = this.attr.getFirst().id;
        return "using" +temp +";\n";
    }

    @Override
    public String getVar() {
        return "";
    }

    @Override
    public String getConstr() {
        return "";
    }

    @Override
    public String getPreRender() {
        return "";
    }

    @Override
    public String getRender() {
        return "";
    }
}

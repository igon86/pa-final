/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pafaces;

import java.util.LinkedList;

/**
 *
 * @author andrealottarini
 */
public class PaFacesText extends PaFacesObject{

    public PaFacesText(){
        super();
    }

    public PaFacesText(String id){
        super(id);
    }

    @Override
    public LinkedList<PaFacesAttributes> getAttr() {
        return new LinkedList<PaFacesAttributes>();
    }

    @Override
    public String getUsing() {
        return "";
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
        return this.id;
    }
}

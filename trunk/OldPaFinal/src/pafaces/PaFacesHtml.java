/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pafaces;

/**
 *
 * @author andrealottarini
 */
public class PaFacesHtml extends PaFacesElement{

    public PaFacesHtml(){
        super();
    }

    public PaFacesHtml(String id){
        super(id);
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
        return "output.println(\"<"+this.id+"\">);";
    }
}

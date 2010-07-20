package PaFaces;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import PaFaces.Code;

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
    public void getCode(Code code) {
        
        if (this.children.size() > 0){
            code.getRender().append("\t\toutput.println(\"<"+id+">\");\n");
            for ( PaFacesObject child : this.children ) child.getCode(code);
            code.getRender().append("\t\toutput.println(\"</"+id+">\");\n");
        }
        else{
            code.getRender().append("\t\toutput.println(\"<"+id+"/>\");\n");
        }

    }

    @Override
    public String getName() {
        return id;
    }
}

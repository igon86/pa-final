package PaFaces;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import PaFaces.Code;


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
    public void getCode(Code code) {
        code.getRender().append("\t\toutput.println(\""+this.id.trim()+"\");\n");
    }

    @Override
    public String getName() {
        return this.id;
    }

    
}

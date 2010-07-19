/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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

    //DA BUTTARE
    @Override
    public LinkedList<PaFacesAttributes> getAttr() {
        return new LinkedList<PaFacesAttributes>();
    }

    @Override
    public void getCode(Code code) {
        code.render.append("\t\toutput.println(\""+this.id.trim()+"\");\n");
    }

    @Override
    public String getName() {
        return this.id;
    }

    
}

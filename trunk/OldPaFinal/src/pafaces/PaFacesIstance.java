/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pafaces;

/**
 *
 * @author andrealottarini
 */
public class PaFacesIstance extends PaFacesMarkup {

    public PaFacesIstance() {
        super();
    }

    public PaFacesIstance(String id) {
        super(id);
    }

    @Override
    public String getUsing() {
        return "";
    }

    @Override
    public String getVar() {
        String Class = this.id.substring(id.lastIndexOf(":") + 1, id.length());
        String name = "";
        for (PaFacesAttributes attribute : this.attr) {
            if (attribute.id.equals("id")) {
                name = attribute.value;
                break;
            }
        }
        return "protected " + Class + " " + name + ";\n";
    }

    @Override
    //SBAGLIATO
    public String getConstr() {
        String name = "";
        for (PaFacesAttributes attribute : this.attr) {
            if (attribute.id.equals("id")) {
                id = attribute.value;
                break;

            }
        }
        return id + " = new " + name + ";\n";
    }

    @Override
    public String getPreRender() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getRender() {
        String ret = "";
        String name;
        for (PaFacesAttributes attribute : this.attr) {
            //devo riprendere il nome della variabile di istanza
            if (attribute.id.equals("id")) {
                name = attribute.id;
                
            }
            else if(attribute.id.equals("code")){
                continue;
            }
            //E` un assegnamento
            else{
                ret = ret.concat(id + "." + attribute.id + "=" + attribute.value.substring(attribute.value.indexOf("{") + 1, attribute.value.lastIndexOf("}")) + ";\n");
            }
        }
        //metto la chiamata di render dell'oggetto embedded
        ret = ret.concat(id + ".Render(output);\n");
        return ret;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

    private String getClasse(){
        return this.id.substring(this.id.lastIndexOf(":") + 1, this.id.length());
    }

    @Override
    public void getCode(Code code) {
        //nome della variabile di istanza analizzata
        String name = "";
        for (PaFacesAttributes attribute : this.attr) {
            //variabile
            if (attribute.id.equals("id")) {
                name = attribute.value;
                //Classe
                String classe = this.getClasse();
                code.var = code.var.concat("protected " + classe + " " + name + ";\n");
                code.constr = code.constr.concat("\t"+name + " = new " + classe + ";\n");
                if (! code.preRendered.contains(classe)){
                    code.preRender = code.preRender.concat("\t"+name+".preRender(headtext);\n");
                    code.preRendered.add(classe);
                }
            } else if (attribute.equals(new PaFacesAttributes("code", "generate"))) {
                continue;
            }
            else {
                code.render = code.render.concat("\t"+name + "." + attribute.id + "=" + attribute.value.substring(attribute.value.indexOf("{") + 1, attribute.value.lastIndexOf("}")) + ";\n");
            }
        }
        //metto la chiamata di render dell'oggetto embedded
        code.render = code.render.concat("\t"+name + ".Render(output);\n");
    }
}

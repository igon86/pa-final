/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrealottarini
 */
public class PaFacesIstance extends PaFacesMarkup {

    // nome della variabile di istanza associata al nodo.
    String name = "";

    public PaFacesIstance() {
        super();
    }

    public PaFacesIstance(String id) {
        super(id);
    }

    private String getClasse() {
        return this.id.substring(this.id.lastIndexOf(":") + 1, this.id.length());
    }

    @Override
    public void getCode(Code code) {
        Main.outGen.println("GEN: " + this.id);
        //nome della variabile di istanza analizzata
        for (PaFacesAttributes attribute : this.attr) {
            //variabile
            if (attribute.id.equals("id")) {
                name = attribute.value;
                //Classe
                String classe = this.getClasse();
                code.var = code.var.concat("\tprotected " + classe + " " + name + ";\n");
                code.constr = code.constr.concat("\t\t" + name + " = new " + classe + "();\n");
                if (!code.preRendered.contains(classe)) {
                    code.preRender = code.preRender.concat("\t\t" + name + ".preRender(headtext);\n");
                    code.preRendered.add(classe);
                }
            } else if (attribute.equals(new PaFacesAttributes("code", "generate"))) {
                continue;
            } else {
                if (attribute.value.contains("$")) {
                    code.render = code.render.concat("\t\t" + name + "." + attribute.id + "=" + attribute.value.substring(attribute.value.indexOf("{") + 1, attribute.value.lastIndexOf("}")) + ";\n");
                }
                else{
                    code.render = code.render.concat("\t\t" + name + "." + attribute.id + "=\"" + attribute.value +"\";\n");
                }
            }
        }
        
        if (children.size() > 0) {
            //NESTED
            for (PaFacesObject child : children) {
                //prendo il nome dell'attributo
                String attrName = child.id;
                // prendo un riferimento al figlio
                PaFacesObject son = child.children.getFirst();
                // invoco la getCode sull'istanza associata
                son.getCode(code);
                //richiedo il nome del componente nested
                String childName = son.getName();
                //devo togliere il render del figlio
                //System.out.println(code.render.lastIndexOf("\n", code.render.length() -2));
                code.render = code.render.substring(0,code.render.lastIndexOf("\n", code.render.length() -2)+1);
                // creo l'associazione
                code.render = code.render.concat("\t\t" + name + "." + attrName + "=" + childName + ";\n");
            }
        }

        //metto la chiamata di render dell'oggetto embedded
        code.render = code.render.concat("\t\t" + name + ".render(output,headText);\n");
    }

    @Override
    public String getName() {
        return this.name;
    }
}

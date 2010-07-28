package PaFaces;

import Features.Code;
import java.util.Iterator;

public class PaFacesInstance extends PaFacesElement {

    String name = ""; // name of the instance

    public PaFacesInstance() {
        super();
    }

    public PaFacesInstance(String id) {
        super(id);
    }

    public PaFacesInstance(String id, String name) {
        super(id);
        this.name = name;
    }

    public void getCode(Code code) {
        Iterator<PaFacesAttribute> h = attr.iterator();
        PaFacesAttribute attribute;
        //nome della variabile di istanza analizzata
        while (h.hasNext()) {
            attribute = h.next();

            if (attribute.id.equals("id")) {
                name = attribute.value;
                //Classe
                String className = this.id.substring(this.id.lastIndexOf(":") + 1, this.id.length());
                code.getVar().append("\tprotected " + className + " " + name + ";\n");
                code.getConstr().append("\t\t" + name + " = new " + className + "();\n");
                if (!code.getPreRendered().contains(className)) {
                    code.getPreRender().append("\t\t" + name + ".preRender(headtext);\n");
                    code.getPreRendered().add(className);
                }
            } else if (attribute.id.equals("code") && attribute.value.equals("generate")) {
                continue;
            } else {
                if (attribute.value.contains("$")) {
                    code.getRender().append("\t\t" + name + "." + attribute.id + "=" + attribute.value.substring(attribute.value.indexOf("{") + 1, attribute.value.lastIndexOf("}")) + ";\n");
                } else {
                    code.getRender().append("\t\t" + name + "." + attribute.id + "=\"" + attribute.value + "\";\n");
                }
            }
        }

        Iterator<PaFacesObject> i = children.iterator();
        PaFacesObject child;
        while (i.hasNext()) {
            child = i.next();
            //prendo il nome dell'attributo
            String attrName = child.id;
            // prendo un riferimento al figlio
            PaFacesObject son = child.children.getFirst();
            // invoco la getCode sull'istanza associata
            son.getCode(code);
            //richiedo il nome del componente nested
            String childName = son.getName();
            //devo togliere il render del figlio
            code.getRender().delete(code.getRender().lastIndexOf("\n", code.getRender().length() - 2) + 1, code.getRender().length());
            // creo l'associazione
            code.getRender().append("\t\t" + name + "." + attrName + "=" + childName + ";\n");
        }
        code.getRender().append("\t\t" + name + ".render(output);\n");
    }

    public String getName() {
        return this.name;
    }
}

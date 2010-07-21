package PaFaces;

import java.util.Iterator;

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
            Iterator<PaFacesObject> i = children.iterator();
            PaFacesObject child;
            while ( i.hasNext() ){
                child = i.next();
                child.getCode(code);
            }
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

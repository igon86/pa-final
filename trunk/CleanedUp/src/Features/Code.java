package Features;

import java.util.LinkedList;

public class Code {

    private StringBuilder head;
    private StringBuilder var;
    private StringBuilder constr;
    private StringBuilder render;
    private StringBuilder preRender;
    private LinkedList<String> preRendered;

    public Code() {
        head = new StringBuilder();
        var = new StringBuilder();
        constr = new StringBuilder();
        render = new StringBuilder();
        preRender = new StringBuilder();
        preRendered = new LinkedList<String>();
    }

    public StringBuilder getConstr() {
        return constr;
    }

    public StringBuilder getHead() {
        return head;
    }

    public StringBuilder getPreRender() {
        return preRender;
    }

    public LinkedList<String> getPreRendered() {
        return preRendered;
    }

    public StringBuilder getRender() {
        return render;
    }

    public StringBuilder getVar() {
        return var;
    }
}

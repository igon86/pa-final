
import java.util.LinkedList;


/**
 *
 * @author andrealottarini
 */
public class Code {

    StringBuilder head;
    StringBuilder var;
    StringBuilder constr;
    StringBuilder render;
    StringBuilder preRender;
    LinkedList<String> preRendered;

    public Code(){
        head = new StringBuilder();
        var = new StringBuilder();
        constr = new StringBuilder();
        render = new StringBuilder();
        preRender = new StringBuilder();
        preRendered = new LinkedList<String>();
    }
}

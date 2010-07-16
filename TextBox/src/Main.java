/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author andrealottarini
 */
public class Main {

    public static final String PA_DIRECTORY = "/Users/andrealottarini/Desktop/PA_stuff/";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Page page = new Page();

        File html = new File(PA_DIRECTORY+"out.html");
        PrintStream outHtml = new PrintStream(html);

        List<String> headText = new LinkedList<String>();

        page.preRender(headText);
        page.render(outHtml,headText);
        
    }

}

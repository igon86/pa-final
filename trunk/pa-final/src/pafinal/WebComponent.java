/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pafinal;

import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author andrealottarini
 */
public interface WebComponent {
    void PreRender(List<String> headtext);
    void Render(PrintStream output);
}

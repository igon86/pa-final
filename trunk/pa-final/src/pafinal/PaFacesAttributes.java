/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pafinal;

/**
 *
 * @author andrealottarini
 */
public class PaFacesAttributes {
    public String id;
    public String value;

    public PaFacesAttributes(String id, String value){
        this.id = id;
        this.value = value;
    }
    public String toString(){
        return this.id.concat("="+value);
    }

    public boolean equals(PaFacesAttributes x){
        return (id.equals(x.id) && value.equals(x.value) );
    }
}

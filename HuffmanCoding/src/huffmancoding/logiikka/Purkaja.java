/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancoding.logiikka;

/**
 *
 * @author Eveliina
 */
public class Purkaja {
    
    private Syotekasittelija syotekasittelija;
    private Pakkaaja pakkaaja;
    
    
    public Purkaja(Syotekasittelija syotekasittelija){
        this.syotekasittelija = syotekasittelija;
        this.pakkaaja = new Pakkaaja(this.syotekasittelija);
    }
    
    public void kaynnistaPurku(){
        String tiedostopolku = this.syotekasittelija.lueTiedostopolku("purku");
    }
    
    
}

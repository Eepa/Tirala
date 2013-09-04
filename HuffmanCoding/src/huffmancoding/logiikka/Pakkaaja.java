
package huffmancoding.logiikka;

import huffmancoding.logiikka.Syotekasittelija;

/**
 * Pakkaaja, joka pakkaa k�ytt�j�n antaman tekstin.
 * @author Eveliina
 */
public class Pakkaaja {
    
    /**
     * K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    
    private Syotekasittelija syotekasittelija;
    
    /**
     * Sis�lt�� sy�tteen merkkien esiintymism��r�t.
     */
    
    private int[] frekvenssitaulukko;
    
    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka k�sittelee tiedon pakkaamista.
     * @param syotekasittelija K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    
    public Pakkaaja (Syotekasittelija syotekasittelija){
        this.syotekasittelija = syotekasittelija;
    }
    
    /**
     * K�ynnist�� pakkaajan toiminnan ja k�ytt�j�n sy�tteen kyselyn.
     */
    
    public void kaynnista(){
        String teksti  = this.syotekasittelija.lueKayttajanSyote("pakkaus");
                
        this.frekvenssitaulukko = this.syotekasittelija.luoFrekvenssitaululukko(teksti);
    }
    
    
}

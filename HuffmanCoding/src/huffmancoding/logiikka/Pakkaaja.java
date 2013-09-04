
package huffmancoding.logiikka;

import huffmancoding.logiikka.Syotekasittelija;

/**
 * Pakkaaja, joka pakkaa käyttäjän antaman tekstin.
 * @author Eveliina
 */
public class Pakkaaja {
    
    /**
     * Käyttäjän antamien syötteiden käsittelijä.
     */
    
    private Syotekasittelija syotekasittelija;
    
    /**
     * Sisältää syötteen merkkien esiintymismäärät.
     */
    
    private int[] frekvenssitaulukko;
    
    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka käsittelee tiedon pakkaamista.
     * @param syotekasittelija Käyttäjän antamien syötteiden käsittelijä.
     */
    
    public Pakkaaja (Syotekasittelija syotekasittelija){
        this.syotekasittelija = syotekasittelija;
    }
    
    /**
     * Käynnistää pakkaajan toiminnan ja käyttäjän syötteen kyselyn.
     */
    
    public void kaynnista(){
        String teksti  = this.syotekasittelija.lueKayttajanSyote("pakkaus");
                
        this.frekvenssitaulukko = this.syotekasittelija.luoFrekvenssitaululukko(teksti);
    }
    
    
}

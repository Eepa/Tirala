
package huffmancoding.koodaaja;

import huffmancoding.logiikka.Syotekasittelija;
import java.util.Scanner;

/**
 * Luokka kuvaa Huffman koodauksen toteuttavaa luokkaa.
 * @author Eveliina
 */
public class HuffmanCoding {
    
    /**
     * Lukija, joka lukee käyttäjän syötteet.
     */
    
    private Scanner lukija;
    
    private Syotekasittelija syotekasittelija;
    
    /**
     * Konstruktorissa luodaan uusi Huffman koodaaja.
     * @param lukija Käyttäjän syötteiden lukija.
     */
    
    public HuffmanCoding(Scanner lukija) {
        this.lukija = lukija;
        this.syotekasittelija = new Syotekasittelija(lukija);
    }

    public void kaynnista() {
        String toiminto = this.syotekasittelija.toiminnonValinta();
        String teksti  = this.syotekasittelija.lueKayttajanSyote(toiminto);
        int[] frekvenssitaulukko = this.syotekasittelija.luoFrekvenssitaululukko(teksti);
    }
    
}

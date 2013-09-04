
package huffmancoding.huffmancoding;

import huffmancoding.logiikka.Syotekerailija;
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
    
    private Syotekerailija syotekerailija;
    
    /**
     * Konstruktorissa luodaan uusi Huffman koodaaja.
     * @param lukija Käyttäjän syötteiden lukija.
     */
    
    public HuffmanCoding(Scanner lukija) {
        this.lukija = lukija;
        this.syotekerailija = new Syotekerailija(lukija);
    }

    public void kaynnista() {
        String teksti  = this.syotekerailija.lueKayttajanSyote();
        
    }
    
}

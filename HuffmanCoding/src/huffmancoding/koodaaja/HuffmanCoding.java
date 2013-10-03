package huffmancoding.koodaaja;

import huffmancoding.logiikka.Syotekasittelija;
import java.util.Scanner;

/**
 * Luokka kuvaa Huffman koodauksen toteuttavaa luokkaa.
 *
 * @author Eveliina
 */
public class HuffmanCoding {

    /**
     * Käyttäjän antamien syötteiden käsittelijä.
     */
    private Syotekasittelija syotekasittelija;

    /**
     * Konstruktorissa luodaan uusi Huffman koodaaja.
     *
     * @param lukija Käyttäjän syötteiden lukija.
     */
    public HuffmanCoding(Scanner lukija) {

        this.syotekasittelija = new Syotekasittelija(lukija);
    }

    /**
     * Käynnistää pakkaajan tai purkajan toiminnan.
     */
    public void kaynnista() {

        String toiminto = this.syotekasittelija.toiminnonValinta();

        if (toiminto.equals("pakkaus")) {
            Pakkaaja pakkaaja = new Pakkaaja(this.syotekasittelija);
            pakkaaja.kaynnistaPakkaus();
        } else {
            Purkaja purkaja = new Purkaja(this.syotekasittelija);
            purkaja.kaynnistaPurku();
        }



    }
}

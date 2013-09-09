package huffmancoding.koodaaja;

import huffmancoding.logiikka.Pakkaaja;
import huffmancoding.logiikka.Syotekasittelija;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Luokka kuvaa Huffman koodauksen toteuttavaa luokkaa.
 *
 * @author Eveliina
 */
public class HuffmanCoding {

    /**
     * Lukija, joka lukee k�ytt�j�n sy�tteet.
     */
    private Scanner lukija;
    /**
     * K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    private Syotekasittelija syotekasittelija;

    /**
     * Konstruktorissa luodaan uusi Huffman koodaaja.
     *
     * @param lukija K�ytt�j�n sy�tteiden lukija.
     */
    public HuffmanCoding(Scanner lukija) {
        this.lukija = lukija;
        this.syotekasittelija = new Syotekasittelija(lukija);
    }

    /**
     * K�ynnist�� pakkaajan tai purkajan toiminnan.
     */
    public void kaynnista() {

        String toiminto = this.syotekasittelija.toiminnonValinta();

        if (toiminto.equals("pakkaus")) {
            Pakkaaja pakkaaja = new Pakkaaja(this.syotekasittelija);
            pakkaaja.kaynnistaPakkaus();
        }



    }
}

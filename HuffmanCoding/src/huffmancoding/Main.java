package huffmancoding;

import huffmancoding.koodaaja.HuffmanCoding;
import java.util.Scanner;

/**
 * Huffman koodauksella tuotettu tiedonpakkausohjelma.
 *
 * @author Eveliina
 */
public class Main {

    /**
     * Pääohjelma, josta ohjelman suoritus käynnistetään.
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner lukija = new Scanner(System.in);

        // Tekstikäyttöliittymä

        HuffmanCoding pakkaaja = new HuffmanCoding(lukija);
        pakkaaja.kaynnista();

    }
}

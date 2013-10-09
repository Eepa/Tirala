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
     * P��ohjelma, josta ohjelman suoritus k�ynnistet��n.
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner lukija = new Scanner(System.in);

        // Tekstik�ytt�liittym�
        
        //  /cs/fs/home/evpa/Pictures/kissa3.jpg
        //  /cs/fs/home/evpa/Pictures/pakattukissa3.jpg.ep

        HuffmanCoding huffmancoding = new HuffmanCoding(lukija);
        huffmancoding.kaynnista();

    }
}

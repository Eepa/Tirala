package huffmancoding.koodaaja;

import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tesaa luokkaa HuffmanCoding.
 *
 * @author Eveliina
 */
public class HuffmanCodingTest {

    private Scanner lukija;
    private HuffmanCoding huffmancoding;

    @Before
    public void setUp() {
        this.lukija = new Scanner("Alussa annettu 1 teksti, дц?");
        this.huffmancoding = new HuffmanCoding(this.lukija);
    }

    @Test
    public void luotuSyotekasittelijaOlemassa() {
        assertTrue(this.huffmancoding != null);
    }
}
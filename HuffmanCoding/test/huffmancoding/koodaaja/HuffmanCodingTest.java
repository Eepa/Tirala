package huffmancoding.koodaaja;

import huffmancoding.logiikka.Syotekasittelija;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
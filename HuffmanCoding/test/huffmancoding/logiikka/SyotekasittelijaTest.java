package huffmancoding.logiikka;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokkaa Syotekasittelija.
 *
 * @author Eveliina
 */
public class SyotekasittelijaTest {

    private Scanner lukija;
    private Syotekasittelija syotekasittelija;

    @Before
    public void setUp() {
        this.lukija = new Scanner("Alussa annettu 1 teksti, дц?");
        this.syotekasittelija = new Syotekasittelija(this.lukija);
    }

    @Test
    public void luotuSyotekasittelijaOlemassa() {
        assertTrue(this.syotekasittelija != null);
    }

//    @Test
//    public void toimiikoKayttajanSyotteenLukeminen() {
//        String teksti = this.syotekasittelija.lueKayttajanSyote("pakkaus");
//        assertEquals(teksti, "Alussa annettu 1 teksti, дц?");
//    }
    @Test
    public void toimiikoToiminnonValinta() {
        this.lukija = new Scanner("pakkaus");
        this.syotekasittelija = new Syotekasittelija(this.lukija);
        String teksti = this.syotekasittelija.toiminnonValinta();
        assertEquals(teksti, "pakkaus");
    }
}
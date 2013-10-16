package huffmancoding.logiikka;

import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void toimiikoToiminnonValinta() {
        this.lukija = new Scanner("pakkaus");
        this.syotekasittelija = new Syotekasittelija(this.lukija);
        String teksti = this.syotekasittelija.toiminnonValinta();
        assertEquals("pakkaus", teksti);
    }

    @Test
    public void toimiikoTiedostopolunLukeminen() {
        this.lukija = new Scanner("home/src/testi.pdf");
        this.syotekasittelija = new Syotekasittelija(this.lukija);
        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");
        assertEquals("home/src/testi.pdf", teksti);
    }

    @Test
    public void laskeekoFrekvenssitOikein() {
        byte[] tavut = new byte[6];
        tavut[0] = (byte) 15;
        tavut[1] = (byte) 16;
        tavut[2] = (byte) 17;
        tavut[3] = (byte) 15;
        tavut[4] = (byte) 15;
        tavut[5] = (byte) 16;
        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tavut);

        boolean tosi = false;
        if (frekvenssit[143] == 3 && frekvenssit[144] == 2 && frekvenssit[145] == 1) {
            tosi = true;
        }
        assertTrue(tosi);
    }

    @Test
    public void toimiikoTiedostonimenEtsiminen() {


        String teksti = this.syotekasittelija.etsiTiedostonimi("home/src/testi.pdf");
        assertEquals("testi.pdf", teksti);
    }

    @Test
    public void toimiikoTiedostopolunEtsiminen() {


        String teksti = this.syotekasittelija.etsiTiedostopolku("home/src/testi.pdf");
        assertEquals("home/src/", teksti);
    }

    @Test
    public void toimiikoTiedostopolunKasaaminen() {

        String[] taulukko = new String[3];
        taulukko[0] = "home";
        taulukko[1] = "src";
        taulukko[2] = "testi.pdf";
        String teksti = this.syotekasittelija.kasaaTiedostopolku(taulukko);
        assertEquals("home/src/", teksti);
    }
}
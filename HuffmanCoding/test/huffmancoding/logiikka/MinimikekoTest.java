package huffmancoding.logiikka;

import java.util.Scanner;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Testaa luokkaa Minimikeko.
 *
 * @author Eveliina
 */
public class MinimikekoTest {

    private Minimikeko minimikeko;
    private Syotekasittelija syotekasittelija;

    @Before
    public void setUp() {
        byte[] tavut = new byte[6];
        tavut[0] = (byte) 15;
        tavut[1] = (byte) 16;
        tavut[2] = (byte) 17;
        tavut[3] = (byte) 15;
        tavut[4] = (byte) 15;
        tavut[5] = (byte) 16;
        this.syotekasittelija = new Syotekasittelija(new Scanner("Tama on testi"));
        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tavut);
        this.minimikeko = new Minimikeko(frekvenssit);
    }

    @Test
    public void luotuMinimikekoOlemassa() {
        assertTrue(this.minimikeko != null);
    }
}
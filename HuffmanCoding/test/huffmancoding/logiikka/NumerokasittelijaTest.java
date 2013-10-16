package huffmancoding.logiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa luokkaa Numerokasittelija.
 *
 * @author Eveliina
 */
public class NumerokasittelijaTest {

    private Numerokasittelija numerokasittelija;

    @Before
    public void setUp() {
        this.numerokasittelija = new Numerokasittelija();
    }

    @Test
    public void luotuNumerokasittelijaOlemassa() {
        assertTrue(this.numerokasittelija != null);
    }

    @Test
    public void muuttaakoIntinTavuksiOikein() {
        byte[] tavut = this.numerokasittelija.intToByteArray(1125);
        boolean tosi = false;
        if (tavut[0] == 0 && tavut[1] == 0 && tavut[2] == 4 && tavut[3] == 101) {
            tosi = true;
        }
        assertTrue(tosi);
    }

    @Test
    public void muuttaakoTavunOikeinInt() {
        byte[] tavut = new byte[4];
        tavut[0] = 0;
        tavut[1] = 0;
        tavut[2] = 4;
        tavut[3] = 101;
        assertEquals(1125, this.numerokasittelija.byteArrayToInt(tavut));
    }

    @Test
    public void kirjoittaaFrekvenssitaulukonOikeinTiedostoon() {
        int[] numero = new int[1];
        numero[0] = 1125;
        byte[] tiedosto = new byte[4];
        boolean tosi = false;
        tiedosto = this.numerokasittelija.kirjoitaFrekvenssitaulukkoTiedostoon(numero, tiedosto);
        if (tiedosto[0] == 0 && tiedosto[1] == 0 && tiedosto[2] == 4 && tiedosto[3] == 101) {
            tosi = true;
        }
        assertTrue(tosi);
    }

    @Test
    public void kopioiTavunOikeinTiedostoon() {
        byte[] numero = this.numerokasittelija.intToByteArray(1125);
        byte[] tiedosto = new byte[4];
        tiedosto = this.numerokasittelija.kopioiTavunumeroTaulukkoon(numero, tiedosto, 0);
        boolean tosi = false;

        if (tiedosto[0] == 0 && tiedosto[1] == 0 && tiedosto[2] == 4 && tiedosto[3] == 101) {
            tosi = true;
        }
        assertTrue(tosi);
    }

    @Test
    public void poimiiNumeronOikeinTaulukosta() {
        byte[] tavut = this.numerokasittelija.intToByteArray(1125);
        byte[] tiedosto = this.numerokasittelija.poimiNumeroTaulukosta(0, tavut);
        boolean tosi = false;

        if (tiedosto[0] == 0 && tiedosto[1] == 0 && tiedosto[2] == 4 && tiedosto[3] == 101) {
            tosi = true;
        }
        assertTrue(tosi);
    }

    @Test
    public void poimiiFrekvenssiOikeinTaulukosta() {
        byte[] tavut = new byte[1024];
        tavut[4] = 0;
        tavut[5] = 0;
        tavut[6] = 4;
        tavut[7] = 101;
        int[] frekvenssit = this.numerokasittelija.muodostaFrekvenssitaulukkoUudestaan(tavut);
        assertEquals(1125, frekvenssit[1]);
    }
}
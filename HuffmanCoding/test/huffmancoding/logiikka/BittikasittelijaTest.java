package huffmancoding.logiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa luokkaa bittikasittelija.
 *
 * @author Eveliina
 */
public class BittikasittelijaTest {

    private Bittikasittelija bittikasittelija;

    @Before
    public void setUp() {
        this.bittikasittelija = new Bittikasittelija();
    }

    @Test
    public void luotuBittikasittelijaOlemassa() {
        assertTrue(this.bittikasittelija != null);
    }

    @Test
    public void muuttaakoTavunOikeinBiteiksi() {
        boolean[] bitit = this.bittikasittelija.byteToBits(142);
        boolean tosi = true;
        boolean vaarin = false;
        boolean oikein = false;

        if (bitit[0] == tosi && bitit[1] == vaarin && bitit[2] == vaarin && bitit[3] == vaarin && bitit[4] == tosi && bitit[5] == tosi && bitit[6] == tosi && bitit[7] == vaarin) {
            oikein = true;
        }
        assertTrue(oikein);
    }

    @Test
    public void muuttaaBititOikeinTavuksi() {
        boolean[] bitit = new boolean[8];
        boolean tosi = true;
        boolean vaarin = false;
        bitit[0] = tosi;
        bitit[1] = vaarin;
        bitit[2] = vaarin;
        bitit[3] = vaarin;
        bitit[4] = tosi;
        bitit[5] = tosi;
        bitit[6] = tosi;
        bitit[7] = vaarin;
        assertEquals(142, this.bittikasittelija.bitsToByte(bitit));
    }

    @Test
    public void muuttaakoOikeinOikeiksiTavuiksi() {
        boolean[][] bittitavut = new boolean[2][8];
        boolean[] tavuyksi = this.bittikasittelija.byteToBits(142);
        boolean[] tavukaksi = this.bittikasittelija.byteToBits(143);
        bittitavut[0] = tavuyksi;
        bittitavut[1] = tavukaksi;
        byte[] tavut = this.bittikasittelija.muunnaOikeiksiTavuiksi(bittitavut, 1);

        boolean oikein = false;
        if (tavut[1024] == (byte) 14 && tavut[1025] == (byte) 15) {
            oikein = true;
        }
        assertTrue(oikein);
    }

    @Test
    public void muodostaaOikeinLuettavatTavut() {
        boolean[][] bittitavut = new boolean[2][8];
        boolean[] tavuyksi = this.bittikasittelija.byteToBits(142);
        boolean[] tavukaksi = this.bittikasittelija.byteToBits(143);
        bittitavut[0] = tavuyksi;
        bittitavut[1] = tavukaksi;
        byte[] tavut = this.bittikasittelija.muunnaOikeiksiTavuiksi(bittitavut, 1);

        boolean[] luettavatTavut = this.bittikasittelija.muodostaLuettavatTavut(tavut);

        int numerolaskuri = 0;
        int[] numerot = new int[2];
        int laskuri = 0;
        for (int i = 0; i < 2; i++) {
            boolean[] tavu = new boolean[8];
            for (int j = 0; j < 8; j++) {

                tavu[j] = luettavatTavut[laskuri];
                laskuri++;
            }
            numerot[numerolaskuri] = this.bittikasittelija.bitsToByte(tavu);
            numerolaskuri++;
        }

        boolean tosi = false;
        if (numerot[0] == 142 && numerot[1] == 143) {
            tosi = true;
        }
        assertTrue(tosi);
    }
}
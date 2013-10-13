package huffmancoding.koodaaja;

import huffmancoding.logiikka.Bittikasittelija;
import huffmancoding.logiikka.Kirjoittaja;
import huffmancoding.logiikka.Numerokasittelija;
import huffmancoding.logiikka.Syotekasittelija;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa luokkaa Purkaja.
 *
 * @author Eveliina
 */
public class PurkajaTest {

    private Scanner lukija;
    private Syotekasittelija syotekasittelija;
    private Purkaja purkaja;
    private Pakkaaja pakkaaja;
    private Bittikasittelija bittikasittelija;
    private Kirjoittaja kirjoittaja;
    private Numerokasittelija numerokasittelija;

    @Before
    public void setUp() {
        this.lukija = new Scanner("Alussa annettu 1 teksti, дц?");
        this.syotekasittelija = new Syotekasittelija(this.lukija);
        this.pakkaaja = new Pakkaaja(this.syotekasittelija);
        this.purkaja = new Purkaja(this.syotekasittelija);
        this.bittikasittelija = new Bittikasittelija();
        this.numerokasittelija = new Numerokasittelija();
    }

    @Test
    public void luotuPurkajaOlemassa() {
        assertTrue(this.purkaja != null);
    }

    @Test
    public void toimiikoTiedostonimenEtsinta() {
        String tiedostonimi = this.purkaja.etsiTiedostonimi("pakattuKOKEILUtiedosto.pdf.ep");
        assertEquals("KOKEILUtiedosto.pdf", tiedostonimi);
    }

    @Test
    public void toimiikoTavujenMuodostaminenUudestaan() {
        byte[] tavut = new byte[6];
        tavut[0] = (byte) 15;
        tavut[1] = (byte) 16;
        tavut[2] = (byte) 17;
        tavut[3] = (byte) 15;
        tavut[4] = (byte) 15;
        tavut[5] = (byte) 16;

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tavut);

        this.pakkaaja.luoMinimikeko(frekvenssit);
        Tree puu = this.pakkaaja.muodostaPuu();
        this.purkaja.setPuu(puu);

        this.kirjoittaja = new Kirjoittaja(puu.muodostaUudetKoodit(new String[256], "", puu.getJuuri()), puu.getJuuri().getMaara());

        boolean[][] booleantavut = this.kirjoittaja.muodostaUusiEsitys(tavut);
        byte[] oikeatTavut = this.bittikasittelija.muunnaOikeiksiTavuiksi(booleantavut, this.kirjoittaja.getOsoitin());
        byte[] uudetTavut = this.numerokasittelija.kirjoitaFrekvenssitaulukkoTiedostoon(frekvenssit, oikeatTavut);

        boolean[] bitit = this.bittikasittelija.muodostaBittijonoPurkamiseen(uudetTavut);
        byte[] valmiitTavut = this.purkaja.muodostaTavutUudestaan(bitit);


        boolean tosi = false;
        if (valmiitTavut[0] == 15 && valmiitTavut[1] == 16 && valmiitTavut[2] == 17 && valmiitTavut[3] == 15
                && valmiitTavut[4] == 15 && valmiitTavut[5] == 16) {
            tosi = true;
        }

        assertTrue(tosi);
    }
}
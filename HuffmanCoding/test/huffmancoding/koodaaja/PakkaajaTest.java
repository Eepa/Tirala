package huffmancoding.koodaaja;

import huffmancoding.logiikka.Syotekasittelija;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa luokkaa Pakkaaja.
 *
 * @author Eveliina
 */
public class PakkaajaTest {

    private Pakkaaja pakkaaja;
    private Scanner lukija;
    private Syotekasittelija syotekasittelija;

    @Before
    public void setUp() {
        this.lukija = new Scanner("Alussa annettu 1 teksti, дц?");
        this.syotekasittelija = new Syotekasittelija(this.lukija);
        this.pakkaaja = new Pakkaaja(this.syotekasittelija);
    }

    @Test
    public void luotuPakkaajaOlemassa() {
        assertTrue(this.pakkaaja != null);
    }

    @Test
    public void toimiikoMinimikeonLuominen() {
        byte[] tavut = new byte[6];
        tavut[0] = (byte) 15;
        tavut[1] = (byte) 16;
        tavut[2] = (byte) 17;
        tavut[3] = (byte) 15;
        tavut[4] = (byte) 15;
        tavut[5] = (byte) 16;

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tavut);

        this.pakkaaja.luoMinimikeko(frekvenssit);

        Node[] solmu = this.pakkaaja.getKeko();

        boolean tosi = false;
        if (solmu[0].getTavu() == 16 && solmu[1].getTavu() == 15 && solmu[2].getTavu() == 17) {
            tosi = true;
        }

        assertTrue(tosi);
    }

    @Test
    public void pituudenTarkistusToimiiTyhjalle() {
        boolean tosi = this.pakkaaja.tarkistaLoytyikoTiedosto(0);
        assertTrue(tosi);
    }

    @Test
    public void pituudenTarkistusToimiiTaydelle() {
        boolean tosi = this.pakkaaja.tarkistaLoytyikoTiedosto(5);
        assertFalse(tosi);
    }

    @Test
    public void puunMuodostaminenToimii() {
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
        
        String sana = "-100015-10001617";
        String uusiSana = "" + puu.getJuuri().getTavu() + puu.getJuuri().getVasenLapsi().getTavu() + 
                puu.getJuuri().getOikeaLapsi().getTavu() + puu.getJuuri().getOikeaLapsi().getVasenLapsi().getTavu() +
                puu.getJuuri().getOikeaLapsi().getOikeaLapsi().getTavu();
        
        assertEquals(sana, uusiSana);
    }
}
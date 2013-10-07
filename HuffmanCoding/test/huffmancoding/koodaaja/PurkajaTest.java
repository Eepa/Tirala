package huffmancoding.koodaaja;

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

    @Before
    public void setUp() {
        this.lukija = new Scanner("Alussa annettu 1 teksti, дц?");
        this.syotekasittelija = new Syotekasittelija(this.lukija);

        this.purkaja = new Purkaja(this.syotekasittelija);
    }

    @Test
    public void luotuPurkajaOlemassa() {
        assertTrue(this.purkaja != null);
    }
    
    @Test
    public void toimiikoTiedostonimenEtsinta(){
        String tiedostonimi = this.purkaja.etsiTiedostonimi("pakattuKOKEILUtiedosto.pdf.ep");
        assertEquals("KOKEILUtiedosto.pdf", tiedostonimi);
    }
}
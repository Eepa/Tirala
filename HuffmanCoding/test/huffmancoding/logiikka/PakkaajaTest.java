
package huffmancoding.logiikka;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokkaa Pakkaaja.
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
}
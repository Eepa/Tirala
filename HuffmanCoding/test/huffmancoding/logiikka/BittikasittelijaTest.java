package huffmancoding.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eveliina
 */
public class BittikasittelijaTest {

    private Bittikasittelija bittikaisttelija;

    @Before
    public void setUp() {
        this.bittikaisttelija = new Bittikasittelija();
    }

    @Test
    public void luotuBittikasittelijaOlemassa() {
        assertTrue(this.bittikaisttelija != null);
    }
}
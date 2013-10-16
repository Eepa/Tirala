package huffmancoding.koodaaja;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa luokkaa Node.
 *
 * @author Eveliina
 */
public class NodeTest {

    private Node solmu;
    private Node uusiSolmu;

    @Before
    public void setUp() {
        this.solmu = new Node(125, 100);
        this.uusiSolmu = new Node(125, 100, new Node(10, 15), new Node(15, 10));
    }

    @Test
    public void luotuNodeOlemassa() {
        assertTrue(this.solmu != null);
    }

    @Test
    public void toinenKonstruktoritesti() {

        assertTrue(this.uusiSolmu != null);
    }

    @Test
    public void asettaaOikeinOikeanLapsenEkaKonstruktori() {
        assertTrue(this.solmu.getOikeaLapsi() == null);
    }

    @Test
    public void asettaaOikeinVasemmanLapsenEkaKonstruktori() {
        assertTrue(this.solmu.getVasenLapsi() == null);
    }

    @Test
    public void palauttaaOikeinOikeanLapsenJaMaaran() {
        assertEquals(10, this.uusiSolmu.getOikeaLapsi().getMaara());
    }

    @Test
    public void palauttaaOikeinVasemmanLapsenJaTavun() {
        assertEquals(10, this.uusiSolmu.getVasenLapsi().getTavu());
    }

    @Test
    public void muuttaaOikeinOikeanLapsenMaaraa() {
        this.uusiSolmu.getOikeaLapsi().muutaMaaraa(5);
        assertEquals(15, this.uusiSolmu.getOikeaLapsi().getMaara());
    }
}
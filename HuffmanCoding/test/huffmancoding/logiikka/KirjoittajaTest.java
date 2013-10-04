
package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;
import huffmancoding.koodaaja.Tree;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokkaa Kirjoittaja.
 * @author Eveliina
 */
public class KirjoittajaTest {
    
    private Kirjoittaja kirjoittaja;
    private Tree puu;

    
    @Before
    public void setUp() {
        
        Node ekaSolmu = new Node(10, 11, new Node(12, 13), new Node(14, 15));
        Node tokaSolmu = new Node(16, 17, ekaSolmu, new Node(18, 19));
        this.puu = new Tree(tokaSolmu);
        String[] uudetEsitykset = new String[255];
        uudetEsitykset = this.puu.muodostaUudetKoodit(uudetEsitykset, "", this.puu.getJuuri());
        this.kirjoittaja = new Kirjoittaja(uudetEsitykset, 5);
    }

    @Test
    public void luotuKirjoittajaOlemassa(){
        assertTrue(this.kirjoittaja != null);
    }
}
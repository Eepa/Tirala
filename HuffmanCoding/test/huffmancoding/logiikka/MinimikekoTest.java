package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;
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

    @Test
    public void toimiikoSolmunLuominen() {
        Node[] solmu = this.minimikeko.luoSolmut();

        assertEquals(2, solmu[256].getMaara());
    }

    @Test
    public void toimiikoParentPariton() {
        assertEquals(2, this.minimikeko.parent(5));
    }

    @Test
    public void toimiikoParentParillinen() {
        assertEquals(4, this.minimikeko.parent(8));
    }

    @Test
    public void toimiikoVasenlapsi() {
        assertEquals(11, this.minimikeko.vasenLapsi(5));
    }

    @Test
    public void toimiikoOikealapsi() {
        assertEquals(12, this.minimikeko.oikeaLapsi(5));
    }
     @Test
    public void toimiikoHeapify() {
        Node[] solmu = this.minimikeko.luoSolmut();
        for (int i = (solmu[256].getMaara() / 2) - 1; i >= 0; i--) {
            solmu = minimikeko.heapify(solmu, i, solmu[256].getMaara());
        }
        boolean tosi = false;System.out.println(solmu[0].getTavu());
        System.out.println(solmu[1].getTavu());
        System.out.println(solmu[2].getTavu());
        
        if(solmu[0].getTavu() == 15 && solmu[1].getTavu() == 16 && solmu[2].getTavu() == 17){
            tosi = true;
        }

        assertTrue(tosi);
    }
}
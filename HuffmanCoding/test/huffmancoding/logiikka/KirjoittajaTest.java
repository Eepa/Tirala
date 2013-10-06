package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;
import huffmancoding.koodaaja.Tree;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokkaa Kirjoittaja.
 *
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
    public void luotuKirjoittajaOlemassa() {
        assertTrue(this.kirjoittaja != null);
    }

    @Test
    public void osoitinPalautetaanOikeinJaSeKasvaaOikein() {
        this.kirjoittaja.kasvataOsoitinta();
        assertEquals(1, this.kirjoittaja.getOsoitin());
    }

    @Test
    public void taulukkoKasvaaOikein() {
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();
        this.kirjoittaja.kasvataOsoitinta();

        assertEquals(20, this.kirjoittaja.getTavutaulukko().length);
    }

    @Test
    public void bittiKirjoitetaanOikein() {
        this.kirjoittaja.kirjoitaBitti(true);

        assertEquals(true, this.kirjoittaja.getTavutaulukko()[0][0]);
    }

    @Test
    public void kirjoitetaankoTavuOikein() {
        this.kirjoittaja.kirjoitaTavu(142);
        assertEquals(true, this.kirjoittaja.getTavutaulukko()[0][1]);
    }

    @Test
    public void bittiKasvaaOikein() {
        this.kirjoittaja.kasvataBittia();
        assertEquals(1, this.kirjoittaja.getBitti());
    }

    @Test
    public void bittiKasvaaOikeinYliKahdeksan() {
        this.kirjoittaja.kasvataBittia();
        this.kirjoittaja.kasvataBittia();
        this.kirjoittaja.kasvataBittia();
        this.kirjoittaja.kasvataBittia();
        this.kirjoittaja.kasvataBittia();
        this.kirjoittaja.kasvataBittia();
        this.kirjoittaja.kasvataBittia();
        this.kirjoittaja.kasvataBittia();

        assertEquals(0, this.kirjoittaja.getBitti());
    }

    @Test
    public void muodostaaKoodinOikein() {
        byte[] tavut = new byte[2];
        tavut[0] = (byte) (140-128);
        tavut[1] = (byte) (142-128);
        boolean[][] bitit = this.kirjoittaja.muodostaUusiEsitys(tavut);
        String sana = "";
        for(int i = 0; i < 1; i++){
            for(int j = 0; j < 4; j++){
                if(bitit[i][j]){
                    sana += "1";
                }else {
                    sana += "0";
                }
            }
        }
        assertEquals("0001", sana);
    }
}
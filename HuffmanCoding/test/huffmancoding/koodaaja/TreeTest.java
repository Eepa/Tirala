/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancoding.koodaaja;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eveliina
 */
public class TreeTest {

    private Tree puu;

    @Before
    public void setUp() {
        Node ekaSolmu = new Node(10, 11, new Node(12, 13), new Node(14, 15));
        Node tokaSolmu = new Node(16, 17, ekaSolmu, new Node(18, 19));
        this.puu = new Tree(tokaSolmu);
    }

    @Test
    public void luotuPuuOlemassa() {
        assertTrue(this.puu != null);
    }
    
    @Test
    public void palauttaaJuurenOikein(){
        assertEquals(17, this.puu.getJuuri().getMaara());
    }
    
    @Test
    public void muodostaaUudetKoodit(){
        String[] taulukko = new String[255];
        taulukko = this.puu.muodostaUudetKoodit(taulukko, "", this.puu.getJuuri());
         boolean tosi = false;
        if(taulukko[138].equals("0")&& taulukko[140].equals("00") && taulukko[142].equals("01") && taulukko[146].equals("1")){
            tosi = true;
        }
        assertTrue(tosi);
    }
}
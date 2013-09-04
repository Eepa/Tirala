package huffmancoding.logiikka;

import java.util.Scanner;

/**
 * Kerää käyttäjän antaman syötteen.
 *
 * @author Eveliina
 */
public class Syotekerailija {

    /**
     * Lukija, joka lukee käyttäjän syötteet.
     */
    private Scanner lukija;
    
    /**
     * Konstruktori luo uuden Syotekerailijan.
     * @param lukija Käyttäjän syötteiden lukija.
     */

    public Syotekerailija(Scanner lukija) {
        this.lukija = lukija;
    }
    
    public String lueKayttajanSyote(){
        
        System.out.println("Syötä pakattava teksti: ");
        
        String teksti = this.lukija.nextLine();
        
        return teksti;
    }
}

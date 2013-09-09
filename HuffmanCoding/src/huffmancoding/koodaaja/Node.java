package huffmancoding.koodaaja;

/**
 * Kuvaa yhtä puun solmua, joka on tietty tavu. Solmu sisältää myös tiedon tavun
 * esiintymistiheydestä.
 *
 * @author evpa
 */
public class Node {

    /**
     * Kertoo, mikä tavu solmu on.
     */
    private int tavu;
    /**
     * Kertoo, kuinka monta kertaa tavu esiintyy.
     */
    private int maara;
    
    /**
     * Solmun vasen lapsi.
     */
    
    private Node vasenLapsi;
    
    /**
     * Solmun oikea lapsi.
     */
    private Node oikeaLapsi;
    
    

    public Node(int tavu, int maara) {
        this.tavu = tavu;
        this.maara = maara;

        this.vasenLapsi = null;
        this.oikeaLapsi = null;
    }

    public Node(int tavu, int maara, Node vasenLapsi, Node oikeaLapsi) {
        this(tavu, maara);

        this.vasenLapsi = vasenLapsi;
        this.oikeaLapsi = oikeaLapsi;
    }

    public int getTavu() {
        return this.tavu;
    }

    public int getMaara() {
        return this.maara;
    }

    public void muutaMaaraa(int muutos) {
        this.maara += muutos;
    }

    public void setVasenLapsi(Node vasenLapsi) {
        this.vasenLapsi = vasenLapsi;
    }

    public void setOikeaLapsi(Node oikeaLapsi) {
        this.vasenLapsi = oikeaLapsi;
    }
    
    public Node getVasenLapsi(){
        return this.vasenLapsi;
    }
    
    public Node getOikeaLapsi(){
        return this.oikeaLapsi;
    }
}

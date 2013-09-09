package huffmancoding.koodaaja;

/**
 * Kuvaa yht‰ puun solmua, joka on tietty tavu. Solmu sis‰lt‰‰ myˆs tiedon tavun
 * esiintymistiheydest‰.
 *
 * @author evpa
 */
public class Node {

    /**
     * Kertoo, mik‰ tavu solmu on.
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
    
    /**
     * Konstruktori alustaa solmun ilman lapsia.
     * @param tavu Kertoo, mit‰ tavua solmu kuvaa ("solmun nimi").
     * @param maara Kertoo kyseisen tavun esiintymiskertojen m‰‰r‰n.
     */

    public Node(int tavu, int maara) {
        this.tavu = tavu;
        this.maara = maara;

        this.vasenLapsi = null;
        this.oikeaLapsi = null;
    }
    
    /**
     * Alustaa solmun, kun lapset tiedet‰‰n. K‰ytt‰‰ toista konstruktoria apuna.
     * @param tavu Kertoo, mit‰ tavua solmu kuvaa ("solmun nimi").
     * @param maara Kertoo kyseisen tavun esiintymiskertojen m‰‰r‰n.
     * @param vasenLapsi Solmun uusi vasen lapsi.
     * @param oikeaLapsi Solmun uusi oikea lapsi.
     */

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

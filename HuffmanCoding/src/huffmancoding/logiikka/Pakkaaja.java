package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;

/**
 * Pakkaaja, joka pakkaa k�ytt�j�n antaman tekstin.
 *
 * @author Eveliina
 */
public class Pakkaaja {

    /**
     * K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    private Syotekasittelija syotekasittelija;
    /**
     * Sis�lt�� sy�tteen merkkien esiintymism��r�t.
     */
    private byte[] tavujenFrekvenssitaulukko;
    private Minimikeko minimikeko;
    private Node[] keko;

    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka k�sittelee tiedon
     * pakkaamista.
     *
     * @param syotekasittelija K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    public Pakkaaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
    }

    /**
     * K�ynnist�� pakkaajan toiminnan.
     */
    public void kaynnistaPakkaus() {


        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");
        byte[] tiedostonTavut = this.syotekasittelija.muutaTiedostoTavutaulukoksi(teksti);

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tiedostonTavut);

//        for(int i = 0; i < frekvenssit.length; i++){
//            System.out.println("Tavu on: " + (i-128) + " Esiintymiskertojen m��r� on: " + frekvenssit[i] + "\n");
//        }

        this.luoMinimikeko(frekvenssit);
//        
//        this.minimikeko.poistaPienin(keko);
//        this.minimikeko.poistaPienin(keko);
//        this.minimikeko.poistaPienin(keko);
//
//        this.minimikeko.lisaaAlkioKekoon(keko, new Node(800, 1));

        for (Node n : this.keko) {
            System.out.println("Solmu: " + n.getTavu() + " Tavun m��r�: " + n.getMaara());

        }

    }

    /**
     * Luo uuden minimikeon annettujen frekvenssien perusteella.
     * @param frekvenssit Taulukko, jonka tietojen perusteella uusi keko luodaan.
     */
    public void luoMinimikeko(int[] frekvenssit) {
        this.minimikeko = new Minimikeko(frekvenssit);

        this.keko = minimikeko.luoSolmut();

        for (int i = (this.keko[256].getMaara() / 2) - 1; i >= 0; i--) {
            this.keko = minimikeko.heapify(this.keko, i, this.keko[256].getMaara());
        }

    }
}

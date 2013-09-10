package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;

/**
 * Pakkaaja, joka pakkaa käyttäjän antaman tekstin.
 *
 * @author Eveliina
 */
public class Pakkaaja {

    /**
     * Käyttäjän antamien syötteiden käsittelijä.
     */
    private Syotekasittelija syotekasittelija;
    /**
     * Sisältää syötteen merkkien esiintymismäärät.
     */
    private byte[] tavujenFrekvenssitaulukko;
    private Minimikeko minimikeko;
    private Node[] keko;

    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka käsittelee tiedon
     * pakkaamista.
     *
     * @param syotekasittelija Käyttäjän antamien syötteiden käsittelijä.
     */
    public Pakkaaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
    }

    /**
     * Käynnistää pakkaajan toiminnan.
     */
    public void kaynnistaPakkaus() {


        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");
        byte[] tiedostonTavut = this.syotekasittelija.muutaTiedostoTavutaulukoksi(teksti);

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tiedostonTavut);

//        for(int i = 0; i < frekvenssit.length; i++){
//            System.out.println("Tavu on: " + (i-128) + " Esiintymiskertojen määrä on: " + frekvenssit[i] + "\n");
//        }

        this.luoMinimikeko(frekvenssit);
//        
//        this.minimikeko.poistaPienin(keko);
//        this.minimikeko.poistaPienin(keko);
//        this.minimikeko.poistaPienin(keko);
//
//        this.minimikeko.lisaaAlkioKekoon(keko, new Node(800, 1));

        for (Node n : this.keko) {
            System.out.println("Solmu: " + n.getTavu() + " Tavun määrä: " + n.getMaara());

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

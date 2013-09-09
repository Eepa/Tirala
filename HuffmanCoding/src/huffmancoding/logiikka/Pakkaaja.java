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
        


    }
    
    /**
     * 
     * @param frekvenssit 
     */

    public void luoMinimikeko(int[] frekvenssit) {
        this.minimikeko = new Minimikeko(frekvenssit);

        Node[] solmut = minimikeko.luoSolmut();

        for (int i = (solmut.length / 2) - 1; i >= 0; i--) {
            solmut = minimikeko.heapify(solmut, i);
        }

        for (Node n : solmut) {
            System.out.println("Solmu: " + n.getTavu() + " Tavun määrä: " + n.getMaara());

        }

    }
}

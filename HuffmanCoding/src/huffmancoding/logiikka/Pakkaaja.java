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
    
    private Maksimikeko maksimikeko;

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

//        String teksti  = this.syotekasittelija.lueKayttajanSyote("pakkaus");
//                
//        this.frekvenssitaulukko = this.syotekasittelija.luoFrekvenssitaululukko(teksti);

        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");
        byte[] tiedostonTavut = this.syotekasittelija.muutaTiedostoTavutaulukoksi(teksti);

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tiedostonTavut);

//        for(int i = 0; i < frekvenssit.length; i++){
//            System.out.println("Tavu on: " + (i-128) + " Esiintymiskertojen m��r� on: " + frekvenssit[i] + "\n");
//        }

        this.luoMaksimikeko(frekvenssit);



    }
    
    /**
     * 
     * @param frekvenssit 
     */

    public void luoMaksimikeko(int[] frekvenssit) {
        this.maksimikeko = new Maksimikeko(frekvenssit);

        Node[] solmut = maksimikeko.luoSolmut();

        for (int i = (solmut.length / 2) - 1; i >= 0; i--) {
            solmut = maksimikeko.heapify(solmut, i);
        }

        for (Node n : solmut) {
            System.out.println("Solmu: " + n.getTavu() + " Tavun m��r�: " + n.getMaara());

        }

    }
}

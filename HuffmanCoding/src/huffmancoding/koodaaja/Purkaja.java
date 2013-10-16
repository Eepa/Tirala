package huffmancoding.koodaaja;

import huffmancoding.logiikka.Bittikasittelija;
import huffmancoding.logiikka.Numerokasittelija;
import huffmancoding.logiikka.Syotekasittelija;

/**
 * Tiedostojen purkamista toteuttava luokka.
 *
 * @author Eveliina
 */
public class Purkaja {

    /**
     * Käyttäjän antamien syötteiden käsittelijä.
     */
    private Syotekasittelija syotekasittelija;
    /**
     * Tiedoston pakkaajan ilmentymä.
     */
    private Pakkaaja pakkaaja;
    /**
     * Puu, joka sisältää tavujen uudet koodit.
     */
    private Tree puu;
    /**
     * Bittioperaatioita käsittelevä luokka.
     */
    private Bittikasittelija bittikasittelija;
    /**
     * Käsittelee numeroita ja muuttaa ne tavuiksi ja toisinpäin.
     */
    private Numerokasittelija numerokasittelija;

    /**
     * Konstruktori alustaa tarvittavat apuluokat, kuten syötekäsittelijän ja
     * bittikäsittelijän.
     *
     * @param syotekasittelija Syötteitä käsittelevä luokka.
     */
    public Purkaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
        this.pakkaaja = new Pakkaaja(this.syotekasittelija);
        this.bittikasittelija = new Bittikasittelija();
        this.numerokasittelija = new Numerokasittelija();
    }

    /**
     * Käynnistää purkamisen ja suorittaa tarvittavat operaatiot.
     */
    public void kaynnistaPurku() {

        //Alustetaan muuttujia tiedoston nimeen ja polkuun liittyen ja tarkistetaan, 
        //onko tiedosto olemassa.

        String tiedostopolku = this.syotekasittelija.lueTiedostopolku("purku");

        String haettuNimi = this.syotekasittelija.etsiTiedostonimi(tiedostopolku);

        String tiedostonimi = this.etsiTiedostonimi(haettuNimi);

        if (tiedostonimi.isEmpty()) {
            System.out.println("\nAlkuperäistä tiedostonimeä ei löytynyt. Purkaminen keskeytettiin.");
            return;
        }

        System.out.println("\nAlkuperäinen tiedosto löydettiin. Tiedostonimi: " + tiedostonimi);

        String alkuperainenTiedostopolku = this.syotekasittelija.etsiTiedostopolku(tiedostopolku);
        
        
        
        //Luetaan pakattu tiedosto ja muodostetaan uudestaan uudet bittikoodit.

        byte[] tiedostoTavutaulukkona = this.syotekasittelija.muutaTiedostoTavutaulukoksi(tiedostopolku);

        if (tiedostoTavutaulukkona.length == 0) {
            System.out.println("\nTiedosto oli tyhjä tai sitä ei löytynyt. Purkaminen keskeytettiin.");
            return;
        }

        int[] frekvenssit = numerokasittelija.muodostaFrekvenssitaulukkoUudestaan(tiedostoTavutaulukkona);

        this.pakkaaja.luoMinimikeko(frekvenssit);
        this.puu = this.pakkaaja.muodostaPuu();
        
        
        
        //Muodostetaan uudestaan alkuperäisen tiedoston tavut ja kirjoitetaan alkuperäinen tiedosto.

        boolean[] luettavatTavut = this.bittikasittelija.muodostaBittijonoPurkamiseen(tiedostoTavutaulukkona);


        byte[] tavut = this.muodostaTavutUudestaan(luettavatTavut);

        this.syotekasittelija.luoPurettuTiedosto(tavut, tiedostonimi, alkuperainenTiedostopolku);
    }

    /**
     * Muodostaa annetun koodin perusteella uudestaan alkuperäisen tiedoston
     * tavut.
     *
     * @param koodi Koodi (bittimuodossa true = 1 ja false = 0).
     * @return Palauttaa alkuperäisen tiedoston tavut.
     */
    public byte[] muodostaTavutUudestaan(boolean[] koodi) {

        byte[] tavut = new byte[this.puu.getJuuri().getMaara()];
        int tavulaskuri = 0;

        Node lahtopiste = this.puu.getJuuri();
        Node solmu = this.puu.getJuuri();

        int i = 0;

        while (tavulaskuri < this.puu.getJuuri().getMaara()) {

            if (solmu.getVasenLapsi() == null && solmu.getOikeaLapsi() == null) {

                byte tavu = (byte) solmu.getTavu();
                tavut[tavulaskuri] = tavu;
                tavulaskuri++;
                solmu = lahtopiste;
                continue;
            }

            if (!koodi[i]) {
                if (solmu.getVasenLapsi() != null) {
                    solmu = solmu.getVasenLapsi();
                }

            } else if (koodi[i]) {
                if (solmu.getOikeaLapsi() != null) {
                    solmu = solmu.getOikeaLapsi();
                }
            }

            i++;

        }

        return tavut;
    }

    public void setPuu(Tree puu) {
        this.puu = puu;
    }

    /**
     * Etsii annetuista tiedostoista alkuperäisen tiedostonimen. Jos
     * tiedostonimi ei ole tarpeeksi pitkä, palautetaan tyhjä sana.
     *
     * @param tiedostopolku Pakatun tiedoston polku.
     * @return Palauttaa alkuperäisen tiedostonimen tai tyhjän merkkijonon.
     */
    public String etsiTiedostonimi(String tiedostopolku) {
        
        String alkuosa = tiedostopolku.substring(0, 7);
        String tiedostonimi = "";
        
        if (!alkuosa.equals("pakattu")) {
            return "";
        }
        
        for (int i = 7; i < tiedostopolku.length() - 3; i++) {
            tiedostonimi = tiedostonimi + tiedostopolku.charAt(i);
        }

        return tiedostonimi;
    }
}

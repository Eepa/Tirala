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


        String tiedostopolku = this.syotekasittelija.lueTiedostopolku("purku");

        String haettuNimi = this.syotekasittelija.etsiTiedostonimi(tiedostopolku);
        String tiedostonimi = this.etsiTiedostonimi(haettuNimi);


        if (tiedostonimi.isEmpty()) {
            System.out.println("\nAlkuperäistä tiedostonimeä ei löytynyt. Purkaminen keskeytettiin.");
            return;
        }

        System.out.println("\nAlkuperäinen tiedosto löydettiin. Tiedostonimi: " + tiedostonimi);

        String alkuperainenTiedostopolku = this.syotekasittelija.etsiTiedostopolku(tiedostopolku);


        byte[] tiedostoTavutaulukkona = this.syotekasittelija.muutaTiedostoTavutaulukoksi(tiedostopolku);

        if (tiedostoTavutaulukkona.length == 0) {
            System.out.println("\nTiedosto oli tyhjä tai sitä ei löytynyt. Purkaminen keskeytettiin.");
            return;
        }


        int[] frekvenssit = numerokasittelija.muodostaFrekvenssitaulukkoUudestaan(tiedostoTavutaulukkona);

        this.pakkaaja.luoMinimikeko(frekvenssit);
        this.puu = this.pakkaaja.muodostaPuu();

//        this.puu.tulostaAlkiotPreorder(this.puu.getJuuri());
//        String[] uusienKoodienTaulukko = new String[256];
//
//        uusienKoodienTaulukko = this.puu.muodostaUudetKoodit(uusienKoodienTaulukko, "", this.puu.getJuuri());
//
//        for (int i = 0; i < uusienKoodienTaulukko.length; i++) {
//            if (uusienKoodienTaulukko[i] != null) {
//                System.out.println("Tavun nimi: " + (i - 128) + " Uusi koodi: " + uusienKoodienTaulukko[i]);
//            }
//
//        }




        boolean[] luettavatTavut = this.bittikasittelija.muodostaLuettavatTavut(tiedostoTavutaulukkona);


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
//                System.out.println(solmu.getTavu());

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

//        for (int j = 0; j < tavut.length; j++) {
//            System.out.println(tavut[j]);
//        }

        return tavut;

    }

    /**
     * Etsii annetuista tiedostoista alkuperäisen tiedostonimen. Jos
     * tiedostonimi ei ole tarpeeksi pitkä, palautetaan tyhjä sana.
     *
     * @param tiedostopolku Pakatun tiedoston polku.
     * @return Palauttaa alkuperäisen tiedostonimen tai tyhjän merkkijonon.
     */
    public String etsiTiedostonimi(String tiedostopolku) {

        String tiedostonimi = "";
        if (tiedostopolku.length() <= 7) {
            return "";
        }
        for (int i = 7; i < tiedostopolku.length() - 3; i++) {
            tiedostonimi = tiedostonimi + tiedostopolku.charAt(i);
        }


        return tiedostonimi;
    }
}

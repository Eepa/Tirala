/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancoding.koodaaja;

import huffmancoding.logiikka.Bittikasittelija;
import huffmancoding.logiikka.Syotekasittelija;
import java.io.File;
import java.util.Scanner;

/**
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
     * Konstruktori alustaa tarvittavat apuluokat, kuten syötekäsittelijän ja
     * bittikäsittelijän.
     *
     * @param syotekasittelija Syötteitä käsittelevä luokka.
     */
    public Purkaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
        this.pakkaaja = new Pakkaaja(this.syotekasittelija);
        this.bittikasittelija = new Bittikasittelija();
    }

    /**
     * Käynnistää purkamisen ja suorittaa tarvittavat operaatiot.
     */
    public void kaynnistaPurku() {
        System.out.println("Valitaan ensiksi haluttu frekvenssitiedosto.");
        String tiedostopolkufrekvenssit = this.syotekasittelija.lueTiedostopolku("purku");

        System.out.println("Valitaan sitten haluttu purettava tiedosto.");
        String tiedostopolku = this.syotekasittelija.lueTiedostopolku("purku");



        String tiedostonimi = this.etsiTiedostonimi(tiedostopolkufrekvenssit, tiedostopolku);

        if (tiedostonimi.isEmpty()) {
            System.out.println("Tiedostolle ei löytynyt nimeä.");
            return;
        }

        System.out.println("onnistui " + tiedostonimi);

        String[] frekvenssisisalto = this.lueTiedosto(tiedostopolkufrekvenssit);

//        System.out.println(frekvenssisisalto[0]);



        int[] frekvenssit = this.muodostaFrekvenssitaulukko(frekvenssisisalto[0]);

        this.pakkaaja.luoMinimikeko(frekvenssit);
        this.puu = this.pakkaaja.muodostaPuu();

//        this.puu.tulostaAlkiotPreorder(this.puu.getJuuri());
//        String[] uusienKoodienTaulukko = new String[256];

//        uusienKoodienTaulukko = this.puu.muodostaUudetKoodit(uusienKoodienTaulukko, "", this.puu.getJuuri());
//
//        for (int i = 0; i < uusienKoodienTaulukko.length; i++) {
//            if (uusienKoodienTaulukko[i] != null) {
//                System.out.println("Tavun nimi: " + (i - 128) + " Uusi koodi: " + uusienKoodienTaulukko[i]);
//            }
//
//        }

        System.out.println("Tavutaulukko alkaa");

        byte[] tavutaulukko = this.syotekasittelija.muutaTiedostoTavutaulukoksi(tiedostopolku);
//        for(int i = 0; i < tavutaulukko.length; i++){
//            System.out.println(tavutaulukko[i]);
//        }


        int[] numerotavut = this.bittikasittelija.muunnaNumerotavuiksi(tavutaulukko);
//        for(int i = 0; i< numerotavut.length; i++){
//            System.out.println(numerotavut[i]);
//        }

        boolean[] luettavatTavut = this.bittikasittelija.muodostaLuettavatTavut(numerotavut);

//        for(int i = 0; i < luettavatTavut.length; i++){
//            System.out.println(luettavatTavut[i]);
//        }

//
        byte[] tavut = this.muodostaTavutUudestaan(luettavatTavut);
//
        this.syotekasittelija.luoPurettuTiedosto(tavut, tiedostonimi);
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
     * Muodostaa annetusta String-muotoisesta frekvenssitaulukosta uuden
     * numeroista koostuvan frekvenssitaulukon.
     *
     * @param lahtosana String-muotoinen frekvenssitaulukko.
     * @return Palauttaa uuden numeroista koostuvan frekvensitaulukon.
     */
    public int[] muodostaFrekvenssitaulukko(String lahtosana) {
        int[] frekvenssit = new int[256];
        String[] osat = this.jaaPaaosiin(lahtosana);

        for (int i = 0; i < osat.length; i++) {
            int numero = this.muutaNumeroksi(osat[i]);
            frekvenssit[i] = numero;
        }

        return frekvenssit;
    }

    /**
     * Jakaa frekvenssisanan pienempiin osiin muotoa "tavu" "frekvenssi" ja
     * muuttaa ne numeroiksi.
     *
     * @param sana Jaettava sana.
     * @return Palauttaa osat integer-taulukossa.
     */
    public int muutaNumeroksi(String sana) {
        int numero = 0;


        try {
            numero = Integer.parseInt(sana);
        } catch (Exception e) {
            System.out.println("Numeron muutos epäonnistui");
        }



        return numero;
    }

    /**
     * Etsii annetuista tiedostoista alkuperäisen tiedostonimen. Jos samaa
     * tiedostonimeä ei löydy, palauttaa metodi tyhjän sanan.
     *
     * @param frekvenssitTiedostopolku Frekvenssitiedoston polku.
     * @param tiedostopolku Pakatun tiedoston polku.
     * @return Palauttaa alkuperäisen tiedostonimen tai tyhjän merkkijonon.
     */
    public String etsiTiedostonimi(String frekvenssitTiedostopolku, String tiedostopolku) {
        String frekvenssisana = "";

        for (int i = 17; i < frekvenssitTiedostopolku.length() - 3; i++) {
            frekvenssisana = frekvenssisana + frekvenssitTiedostopolku.charAt(i);
        }

        String tiedostonimi = "";

        for (int i = 7; i < tiedostopolku.length() - 3; i++) {
            tiedostonimi = tiedostonimi + tiedostopolku.charAt(i);
        }

        if (frekvenssisana.equals(tiedostonimi)) {
            return tiedostonimi;
        }


        return "";
    }

    /**
     * Jakaa frekvenssitaulukon pienempiin osiin muotoa "tavu*frekvenssi".
     *
     * @param jaettavaSana Sana, joka jaetaan osiin.
     * @return Palauttaa jaetun sanan osat.
     */
    public String[] jaaPaaosiin(String jaettavaSana) {
        String[] osat = jaettavaSana.split(";");

        return osat;
    }

    /**
     * Lukee annetun tiedoston frekvenssitaulukon.
     *
     * @param tiedostopolku Polku, josta luettava tiedosto löytyy.
     * @return Palauttaa luetun tiedoston sisällön.
     */
    public String[] lueTiedosto(String tiedostopolku) {

        String[] sisalto = new String[1];

        File tiedosto = new File(tiedostopolku);

        try {
            Scanner lukija = new Scanner(tiedosto);

            int i = 0;

            while (lukija.hasNext()) {
                String seurSana = lukija.next();
                sisalto[i] = seurSana;
                i++;
            }
            lukija.close();
        } catch (Exception e) {
            System.out.println("Lukeminen epäonnistui");
        }


        return sisalto;
    }
}

package huffmancoding.logiikka;

import java.util.Scanner;

/**
 * Käsittelee käyttäjän antamia syötteitä.
 *
 * @author Eveliina
 */
public class Syotekasittelija {

    /**
     * Lukija, joka lukee käyttäjän syötteet.
     */
    private Scanner lukija;

    /**
     * Konstruktori luo uuden Syotekerailijan.
     *
     * @param lukija Käyttäjän syötteiden lukija.
     */
    public Syotekasittelija(Scanner lukija) {
        this.lukija = lukija;
    }

    /**
     * Lukee tekstin, jonka käyttäjä haluaa pakata.
     *
     * @return Palauttaa käyttäjän tekstin.
     */
    public String lueKayttajanSyote(String toiminto) {

        System.out.println("Seuraava toiminto on " + toiminto + ". Syötä haluamasi teksti: ");

        String teksti = this.lukija.nextLine();

        return teksti;
    }

    /**
     * Valitaan, haluaako käyttäjä purkaa vai pakata tekstiä.
     *
     * @return Palauttaa käyttäjän valitseman toiminnon.
     */
    public String toiminnonValinta() {
        System.out.println("Kirjoita 'pakkaus', jos haluat pakata tekstiä, ja 'purku', jos haluat purkaa.");

        while (true) {
            String toiminto = this.lukija.nextLine();

            if (toiminto.equals("pakkaus") || toiminto.equals("purku")) {
                return toiminto;
            }

            System.out.println("Syöte oli väärä.");
        }

    }

    /**
     * Laskee annetusta tekstistä kunkin merkin esiintymiskerrat.
     *
     * @param teksti Käyttäjän antama teksti.
     * @return Palauttaa valmiin frekvenssitaulukon.
     */
    public int[] luoFrekvenssitaululukko(String teksti) {

        int[] frekvenssit = new int[403];

        char[] merkkitaulukko = this.teeMerkkitaulukko(teksti);

        for (char c : merkkitaulukko) {

            System.out.println("Vanha numero: " + frekvenssit[c] + " Kirjain oli: " + c);
            frekvenssit[c]++;
            System.out.println("Uusi numero: " + frekvenssit[c]);
            System.out.println("");
        }
        for (int i = 0; i < frekvenssit.length; i++) {
            char merkki = (char) i;
            System.out.println("Merkki on: " + merkki + " ja määrä " + frekvenssit[i]);
        }


        return frekvenssit;
    }

    /**
     * Tekee annetusta tekstistä merkkitaulukon.
     *
     * @param teksti Käyttäjän antama teksti.
     * @return Palauttaa valmiin merkkitaulukon.
     */
    public char[] teeMerkkitaulukko(String teksti) {
        char[] taulukko = new char[teksti.length()];

        for (int i = 0; i < teksti.length(); i++) {
            taulukko[i] = teksti.charAt(i);
        }
        System.out.println(taulukko);
        return taulukko;
    }
}

package huffmancoding.logiikka;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

//    /**
//     * Lukee tekstin, jonka käyttäjä haluaa pakata.
//     *
//     * @return Palauttaa käyttäjän tekstin.
//     */
//    public String lueKayttajanSyote(String toiminto) {
//
//        System.out.println("Seuraava toiminto on " + toiminto + ". Syötä haluamasi teksti: ");
//
//        String teksti = this.lukija.nextLine();
//
//        return teksti;
//    }
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

//    /**
//     * Laskee annetusta tekstistä kunkin merkin esiintymiskerrat.
//     *
//     * @param teksti Käyttäjän antama teksti.
//     * @return Palauttaa valmiin frekvenssitaulukon.
//     */
//    public int[] luoFrekvenssitaululukko(String teksti) {
//
//        int[] frekvenssit = new int[403];
//
//        char[] merkkitaulukko = this.teeMerkkitaulukko(teksti);
//
//        for (char c : merkkitaulukko) {
//
//            System.out.println("Vanha numero: " + frekvenssit[c] + " Kirjain oli: " + c);
//            frekvenssit[c]++;
//            System.out.println("Uusi numero: " + frekvenssit[c]);
//            System.out.println("");
//        }
//        for (int i = 0; i < frekvenssit.length; i++) {
//            char merkki = (char) i;
//            System.out.println("Merkki on: " + merkki + " ja määrä " + frekvenssit[i]);
//        }
//
//
//        return frekvenssit;
//    }
//    /**
//     * Tekee annetusta tekstistä merkkitaulukon.
//     *
//     * @param teksti Käyttäjän antama teksti.
//     * @return Palauttaa valmiin merkkitaulukon.
//     */
//    public char[] teeMerkkitaulukko(String teksti) {
//        char[] taulukko = new char[teksti.length()];
//
//        for (int i = 0; i < teksti.length(); i++) {
//            taulukko[i] = teksti.charAt(i);
//        }
//        System.out.println(taulukko);
//        return taulukko;
//    }
    /**
     * Lukee käyttäjän antaman tiedoston polun.
     *
     * @param toiminto Toiminto, jota suoritetaan
     * @return Palauttaa tiedoston polun String-muodossa.
     */
    public String lueTiedostopolku(String toiminto) {
        System.out.println("Seuraava toiminto on " + toiminto + ". Syötä haluamasi tiedoston polku: ");

        String polku = this.lukija.nextLine();

        return polku;
    }

    /**
     * Muuttaa halutun tiedoston tavutaulukoksi. Antaa virheilmoituksen, jos
     * tiedostoa ei löydy ja palauttaa tyhjän taulukon.
     *
     * @param polku Polku haluttuun tiedostoon.
     * @return Palauttaa tiedoston tavutaulukkona.
     */
    public byte[] muutaTiedostoTavutaulukoksi(String polku) {

        File file = new File(polku);

        ByteArrayOutputStream byteArrayOutputStream;

        try {
//            System.out.println(file.length());

            byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());

            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] aputaulukko = new byte[(int) file.length()];

            for (int maara; (maara = fileInputStream.read(aputaulukko)) != -1;) {
                byteArrayOutputStream.write(aputaulukko, 0, maara);
                System.out.println("Tiedoston pituus " + maara + " tavua.");
            }


        } catch (Exception e) {
            System.out.println("VIRHE! \n" + e.getMessage());
            return new byte[0];
        }

        byte[] tavut = byteArrayOutputStream.toByteArray();
//        System.out.println(tavut[1] + " ja " + tavut[3]);



        return tavut;
    }

    /**
     * Laskee jokaisen tavun esiintymiskertojen määrän.
     *
     * @param tavut Tavutaulukko, josta esiintymiskerrat lasketaan.
     * @return Palautaa frekvenssit kullekin tavulle.
     */
    public int[] luoTavuistaFrekvenssitaululukko(byte[] tavut) {

        int[] frekvenssit = new int[256];

        for (byte tavu : tavut) {

            int arvo = tavu + 128;

//            System.out.println("Vanha määrä: " + frekvenssit[arvo] + " Tavu oli: " + tavu);
            frekvenssit[arvo]++;
//            System.out.println("Uusi määrä: " + frekvenssit[arvo]);
//            System.out.println("");
        }

        return frekvenssit;
    }
}

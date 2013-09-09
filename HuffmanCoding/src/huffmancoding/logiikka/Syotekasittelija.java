package huffmancoding.logiikka;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * K�sittelee k�ytt�j�n antamia sy�tteit�.
 *
 * @author Eveliina
 */
public class Syotekasittelija {

    /**
     * Lukija, joka lukee k�ytt�j�n sy�tteet.
     */
    private Scanner lukija;

    /**
     * Konstruktori luo uuden Syotekerailijan.
     *
     * @param lukija K�ytt�j�n sy�tteiden lukija.
     */
    public Syotekasittelija(Scanner lukija) {
        this.lukija = lukija;
    }

//    /**
//     * Lukee tekstin, jonka k�ytt�j� haluaa pakata.
//     *
//     * @return Palauttaa k�ytt�j�n tekstin.
//     */
//    public String lueKayttajanSyote(String toiminto) {
//
//        System.out.println("Seuraava toiminto on " + toiminto + ". Sy�t� haluamasi teksti: ");
//
//        String teksti = this.lukija.nextLine();
//
//        return teksti;
//    }
    /**
     * Valitaan, haluaako k�ytt�j� purkaa vai pakata teksti�.
     *
     * @return Palauttaa k�ytt�j�n valitseman toiminnon.
     */
    public String toiminnonValinta() {
        System.out.println("Kirjoita 'pakkaus', jos haluat pakata teksti�, ja 'purku', jos haluat purkaa.");

        while (true) {
            String toiminto = this.lukija.nextLine();

            if (toiminto.equals("pakkaus") || toiminto.equals("purku")) {
                return toiminto;
            }

            System.out.println("Sy�te oli v��r�.");
        }

    }

//    /**
//     * Laskee annetusta tekstist� kunkin merkin esiintymiskerrat.
//     *
//     * @param teksti K�ytt�j�n antama teksti.
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
//            System.out.println("Merkki on: " + merkki + " ja m��r� " + frekvenssit[i]);
//        }
//
//
//        return frekvenssit;
//    }
//    /**
//     * Tekee annetusta tekstist� merkkitaulukon.
//     *
//     * @param teksti K�ytt�j�n antama teksti.
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
     * Lukee k�ytt�j�n antaman tiedoston polun.
     *
     * @param toiminto Toiminto, jota suoritetaan
     * @return Palauttaa tiedoston polun String-muodossa.
     */
    public String lueTiedostopolku(String toiminto) {
        System.out.println("Seuraava toiminto on " + toiminto + ". Sy�t� haluamasi tiedoston polku: ");

        String polku = this.lukija.nextLine();

        return polku;
    }

    /**
     * Muuttaa halutun tiedoston tavutaulukoksi. Antaa virheilmoituksen, jos
     * tiedostoa ei l�ydy ja palauttaa tyhj�n taulukon.
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
     * Laskee jokaisen tavun esiintymiskertojen m��r�n.
     *
     * @param tavut Tavutaulukko, josta esiintymiskerrat lasketaan.
     * @return Palautaa frekvenssit kullekin tavulle.
     */
    public int[] luoTavuistaFrekvenssitaululukko(byte[] tavut) {

        int[] frekvenssit = new int[256];

        for (byte tavu : tavut) {

            int arvo = tavu + 128;

//            System.out.println("Vanha m��r�: " + frekvenssit[arvo] + " Tavu oli: " + tavu);
            frekvenssit[arvo]++;
//            System.out.println("Uusi m��r�: " + frekvenssit[arvo]);
//            System.out.println("");
        }

        return frekvenssit;
    }
}

package huffmancoding.logiikka;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
//        System.out.println("Tulostetaan tavut");
//        for(int i = 0; i < tavut.length; i++){
//            System.out.println(tavut[i]);
//        }
//        System.out.println("Tavutulostus loppui");

        for (byte tavu : tavut) {

            int arvo = tavu + 128;

//            System.out.println("Vanha määrä: " + frekvenssit[arvo] + " Tavu oli: " + tavu);
            frekvenssit[arvo]++;
//            System.out.println("Uusi määrä: " + frekvenssit[arvo]);
//            System.out.println("");
        }

        return frekvenssit;
    }

    public String[] etsiTiedostopaate(String osoite) {
        String[] osat = osoite.split("\\.");

        return osat;
    }

    public String etsiTiedostonimi(String osoite) {

        String[] osat;

        if (osoite.contains("/")) {
            osat = osoite.split("\\/");

        } else {
            osat = osoite.split("\\\\");
        }

        return osat[osat.length - 1];
    }

    public void luoPakattuTiedosto(String tiedostonimi, String frekvenssitSana, byte[] tavut) {
//        String osoite = "C:\\Users\\Public\\Downloads\\pakattu" + tiedostonimi + ".txt";
        this.luoPakattuFrekvenssitiedosto(tiedostonimi, frekvenssitSana);

        String osoite = "pakattu" + tiedostonimi + ".ep";
        File file = new File(osoite);

        FileOutputStream fileOutputStream;
        try {

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(tavut);
            fileOutputStream.close();

        } catch (Exception e) {
            System.out.println("VIRHE! \n" + e.getMessage());

        }

    }

    public void luoPakattuFrekvenssitiedosto(String tiedostonimi, String frekvenssitSana) {
        String osoite = "pakattufrekvenssi" + tiedostonimi + ".ep";

        File file = new File(osoite);

        FileOutputStream fileOutputStream;

        String frekvenssitieto = frekvenssitSana;

        byte[] frekvenssitietoarray = frekvenssitieto.getBytes();


        try {

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(frekvenssitietoarray);
            fileOutputStream.close();

        } catch (Exception e) {
            System.out.println("VIRHE! \n" + e.getMessage());

        }
    }

    public void luoPurettuTiedosto(byte[] tavut, String tiedostonimi) {

//        String osoite = "C:\\Users\\Public\\Downloads\\" + tiedostonimi ;

        String osoite = tiedostonimi;

        File file = new File(osoite);

        FileOutputStream fileOutputStream;

        try {

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(tavut);
            fileOutputStream.close();


        } catch (Exception e) {
            System.out.println("VIRHE! \n" + e.getMessage());

        }



    }
}

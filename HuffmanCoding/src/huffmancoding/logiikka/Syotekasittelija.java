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

    public void luoPakattuTiedosto(String tiedostonimi, String frekvenssit, String uusiMuoto) {

        String osoite = "C:\\Users\\Public\\Downloads\\pakattu" + tiedostonimi + ".txt";

        File file = new File(osoite);

        FileOutputStream fileOutputStream;
        
        String tieto = tiedostonimi + " " + frekvenssit + " " + uusiMuoto;
        
        byte[] tietoarray = tieto.getBytes();


        try {
            
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(tietoarray);
            fileOutputStream.close();


        } catch (Exception e) {
            System.out.println("VIRHE! \n" + e.getMessage());
            
        }
   


    }
//        public void luoPakattuTiedosto(String tiedostonimi, String frekvenssit, String uusiMuoto){
//            
//            String[] osat = this.etsiTiedostopaate(tiedostonimi);
//            
//            String wanhaNimi = osat[0];
//            String paate = osat[osat.length -1];
//                        
//            String osoite = "C:\\Users\\Public\\Downloads\\pakattu" + wanhaNimi + ".txt";
//                   
//            
//            File uusiTiedosto  = new File(osoite);
//            
//            
//            try{
//
//                FileWriter kirjoittaja = new FileWriter(uusiTiedosto);
//                kirjoittaja.write(tiedostonimi + " ");
//                kirjoittaja.write(frekvenssit + " ");
//                kirjoittaja.write(uusiMuoto);
//                kirjoittaja.close();
//                
//            }catch (Exception e){
//                System.out.println("Virhe sattui! " + e.toString());
//            }
//            
//            
//        }
    
        public void luoTiedosto(byte[] tavut, String tiedostonimi) {

        String osoite = "C:\\Users\\Public\\Downloads\\" + tiedostonimi ;

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

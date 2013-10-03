package huffmancoding.logiikka;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
     * tiedostoa ei löydy ja palauttaa tyhjän taulukon. Samoin palauttaa tyhjän 
     * taulukon, jos haluttu tiedosto on tyhjä.
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
            
            if(file.length() == 0){
                return new byte[0];
            }

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
    
    /**
     * Palauttaa pakattavan tiedoston alkuperäisen nimen.
     * @param osoite Osoite, josta alkuperäinen tiedosto haettiin.
     * @return Palauttaa alkuperäisen tiedoston nimen.
     */

    public String etsiTiedostonimi(String osoite) {

        String[] osat;
        String merkki = "\\";

        if (osoite.contains("/")) {
            merkki = "\\/";
            osat = osoite.split(merkki);
            return osat[osat.length - 1];

        } else if(osoite.contains(merkki)) {
            osat = osoite.split(merkki);
            return osat[osat.length - 1];
        }

        return "";
    }
    
    public String etsiTiedostopolku(String osoite) {

        String[] osat;
        String merkki = "\\";

        if (osoite.contains("/")) {
            merkki = "\\/";
            osat = osoite.split(merkki);
            return this.kasaaTiedostopolku(osat);

        } else if(osoite.contains(merkki)) {
            osat = osoite.split(merkki);
            return this.kasaaTiedostopolku(osat);
        }

        return "";
    }
    
    public String kasaaTiedostopolku(String[] osat){
        String polku = "";
        for(int i = 0; i < osat.length-1; i++){
            polku += osat[i] + "/";
        }
        return polku;
    }
    
    /**
     * Luo pakatun tiedoston annettujen tietojen pohjalta. Ensiksi luodaan oma 
     * tiedosto frekvenssitaulukkoa varten. Sen jälkeen luodaan varsinainen pakattu 
     * tiedosto.
     * @param tiedostonimi Alkuperäisen tiedoston nimi.
     * @param frekvenssitSana Frekvenssitaulukko String-muodossa.
     * @param tavut Pakattuun tiedostoon kirjoitettavat tavut.
     */

    public void luoPakattuTiedosto(String tiedostonimi, String frekvenssitSana, byte[] tavut, String tiedostopolku) {
//        String osoite = "C:\\Users\\Public\\Downloads\\pakattu" + tiedostonimi + ".txt";
        
        String uusiKansio = this.luoKansio(tiedostopolku, tiedostonimi);
        
        this.luoPakattuFrekvenssitiedosto(tiedostonimi, frekvenssitSana, uusiKansio);

        String osoite = uusiKansio + "/" +"pakattu" + tiedostonimi + ".ep";
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
    
    public String luoKansio(String tiedostopolku, String tiedostonimi){
        String osoite = tiedostopolku + "p"+tiedostonimi;
        File kansio = new File(osoite);
        System.out.println(osoite);
        if(!kansio.exists()){
            
            boolean arvo = kansio.mkdir();
            if(arvo){
                System.out.println("Kansion luonti onnistui");
                return osoite;
            }
            else {
                System.out.println("Kansion luominen epäonnistui");
                return "";
            }
        }
        return "";
    }
    
    /**
     * Luo frekvenssitaulukon sisältävän tiedoston annettujen tietojen pohjalta. 
     * 
     * @param tiedostonimi Alkuperäisen tiedoston nimi.
     * @param frekvenssitSana Frekvenssitaulukko String-muodossa.
     */

    public void luoPakattuFrekvenssitiedosto(String tiedostonimi, String frekvenssitSana, String tiedostopolku) {
        String osoite = tiedostopolku + "/"+ "pakattufrekvenssi" + tiedostonimi + ".ep";

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
    
    
    /**
     * Luo pakatusta tiedostostosta uudestaan alkuperäisen tiedoston eli 
     * purkaa pakatun tiedoston.
     * @param tavut Purettavaan tiedostoon kirjoitettavat tavut.
     * @param tiedostonimi Alkuperäisen tiedoston nimi.
     */

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

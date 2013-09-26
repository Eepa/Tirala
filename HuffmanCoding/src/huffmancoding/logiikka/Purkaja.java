/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;
import huffmancoding.koodaaja.Tree;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Eveliina
 */
public class Purkaja {

    private Syotekasittelija syotekasittelija;
    private Pakkaaja pakkaaja;
    private Tree puu;
    private Bittikasittelija bittikasittelija;

    public Purkaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
        this.pakkaaja = new Pakkaaja(this.syotekasittelija);
        this.bittikasittelija = new Bittikasittelija();
    }

    public void kaynnistaPurku() {
        System.out.println("Valitaan ensiksi haluttu frekvenssitiedosto.");
        String tiedostopolkufrekvenssit = this.syotekasittelija.lueTiedostopolku("purku");

        System.out.println("Valitaan sitten haluttu purettava tiedosto.");
        String tiedostopolku = this.syotekasittelija.lueTiedostopolku("purku");



        String tiedostonimi = this.etsiTiedostonimi(tiedostopolkufrekvenssit, tiedostopolku);

        if (tiedostonimi.isEmpty()) {
            return;
        }

        System.out.println("onnistui " + tiedostonimi);

        String[] frekvenssisisalto = this.lueTiedosto(tiedostopolkufrekvenssit);
        
//        System.out.println(frekvenssisisalto[0]);



        int[] frekvenssit = this.muodostaFrekvenssitaulukko(frekvenssisisalto[0]);

        this.pakkaaja.luoMinimikeko(frekvenssit);
        this.puu = this.pakkaaja.muodostaPuu();

//        this.puu.tulostaAlkiotPreorder(this.puu.getJuuri());
        String[] uusienKoodienTaulukko = new String[256];

//        uusienKoodienTaulukko = this.puu.muodostaUudetKoodit(uusienKoodienTaulukko, "", this.puu.getJuuri());

//        for (int i = 0; i < uusienKoodienTaulukko.length; i++) {
//            if (uusienKoodienTaulukko[i] != null) {
//                System.out.println("Tavun nimi: " + (i - 128) + " Uusi koodi: " + uusienKoodienTaulukko[i]);
//            }
//
//        }
        
        System.out.println("Tavutaulukko alkaa");
        
        byte[] tavutaulukko = this.syotekasittelija.muutaTiedostoTavutaulukoksi(tiedostopolku);
        for(int i = 0; i < tavutaulukko.length; i++){
            System.out.println(tavutaulukko[i]);
        }
        
        int roskabititMaara = tavutaulukko[0];
        
        int[] numerotavut = this.bittikasittelija.muunnaNumerotavuiksi(tavutaulukko);
//        for(int i = 0; i< numerotavut.length; i++){
//            System.out.println(numerotavut[i]);
//        }
        
        boolean[] luettavatTavut = this.bittikasittelija.muodostaLuettavatTavut(numerotavut);
        
        for(int i = 0; i < luettavatTavut.length; i++){
            System.out.println(luettavatTavut[i]);
        }

//
        byte[] tavut = this.muodostaTavutUudestaan(luettavatTavut);
//
        this.syotekasittelija.luoPurettuTiedosto(tavut, tiedostonimi);
    }

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

        for (int j = 0; j < tavut.length; j++) {
            System.out.println(tavut[j]);
        }

        return tavut;

    }

    public int[] muodostaFrekvenssitaulukko(String lahtosana) {
        int[] frekvenssit = new int[256];
        String[] osat = this.jaaPaaosiin(lahtosana);

        for (int i = 0; i < osat.length; i++) {
            int[] osanumerot = this.numeroOsat(osat[i]);
            frekvenssit[osanumerot[0]] = osanumerot[1];
        }

//        for (int i = 0; i < frekvenssit.length; i++) {
//            if (frekvenssit[i] != 0) {
//                System.out.println(frekvenssit[i]);
//            }
//        }

        return frekvenssit;
    }

    public int[] numeroOsat(String sana) {
        String[] kirjainosat = sana.split("\\*");

        int[] numerot = new int[2];

        for (int i = 0; i < kirjainosat.length; i++) {
            try {
                numerot[i] = Integer.parseInt(kirjainosat[i]);
            } catch (Exception e) {
                System.out.println("Numeron muutos epäonnistui");
            }

        }

        return numerot;
    }

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

    public String[] jaaPaaosiin(String sana) {
        String[] osat = sana.split(";");

        return osat;
    }

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancoding.logiikka;

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

    public Purkaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
        this.pakkaaja = new Pakkaaja(this.syotekasittelija);
    }

    public void kaynnistaPurku() {
        String tiedostopolku = this.syotekasittelija.lueTiedostopolku("purku");
        String[] sisalto = this.lueTiedosto(tiedostopolku);

        for (int i = 0; i < sisalto.length; i++) {
            System.out.println(sisalto[i]);

        }
        int[] frekvenssit = this.muodostaFrekvenssitaulukko(sisalto[1]);

        this.pakkaaja.luoMinimikeko(frekvenssit);
        Tree puu = this.pakkaaja.muodostaPuu();

        puu.tulostaAlkiotPreorder(puu.getJuuri());
        String[] uusienKoodienTaulukko = new String[256];

        uusienKoodienTaulukko = puu.muodostaUudetKoodit(uusienKoodienTaulukko, "", puu.getJuuri());

        for (int i = 0; i < uusienKoodienTaulukko.length; i++) {
            if (uusienKoodienTaulukko[i] != null) {
                System.out.println("Tavun nimi: " + (i - 128) + " Uusi koodi: " + uusienKoodienTaulukko[i]);
            }

        }
    }

    public int[] muodostaFrekvenssitaulukko(String lahtosana) {
        int[] frekvenssit = new int[256];
        String[] osat = this.jaaPaaosiin(lahtosana);

        for (int i = 0; i < osat.length; i++) {
            int[] osanumerot = this.numeroOsat(osat[i]);
            frekvenssit[osanumerot[0]] = osanumerot[1];
        }

        for (int i = 0; i < frekvenssit.length; i++) {
            if (frekvenssit[i] != 0) {
                System.out.println(frekvenssit[i]);
            }
        }

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

    public String[] jaaPaaosiin(String sana) {
        String[] osat = sana.split(";");

        return osat;
    }

    public String[] lueTiedosto(String tiedostopolku) {

        String[] sisalto = new String[3];

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
            System.out.println("Lukeminen Epäonnistui");
        }


        return sisalto;
    }
}

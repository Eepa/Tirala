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
        this.puu = this.pakkaaja.muodostaPuu();

        this.puu.tulostaAlkiotPreorder(this.puu.getJuuri());
        String[] uusienKoodienTaulukko = new String[256];

        uusienKoodienTaulukko = this.puu.muodostaUudetKoodit(uusienKoodienTaulukko, "", this.puu.getJuuri());

        for (int i = 0; i < uusienKoodienTaulukko.length; i++) {
            if (uusienKoodienTaulukko[i] != null) {
                System.out.println("Tavun nimi: " + (i - 128) + " Uusi koodi: " + uusienKoodienTaulukko[i]);
            }

        }
        
        byte[] tavut = this.muodostaTavutUudestaan(sisalto[2]);
        
        this.syotekasittelija.luoPurettuTiedosto(tavut, sisalto[0]);
    }
    
    public byte[] muodostaTavutUudestaan(String koodi){
        
        byte[] tavut = new byte[this.puu.getJuuri().getMaara()];
        int tavulaskuri = 0;
        
        Node lahtopiste = this.puu.getJuuri();
        Node solmu = this.puu.getJuuri();
        
        int i = 0;
        
        while(tavulaskuri < this.puu.getJuuri().getMaara()){
            
            if(solmu.getVasenLapsi() == null && solmu.getOikeaLapsi() == null){
//                System.out.println(solmu.getTavu());
                
                byte tavu = (byte) solmu.getTavu();
                tavut[tavulaskuri] = tavu;
                tavulaskuri++;
                solmu = lahtopiste;
                continue;
            }
            
            char kirjain = koodi.charAt(i);
            
            if(kirjain == '0'){
                if(solmu.getVasenLapsi() != null){
                    solmu = solmu.getVasenLapsi();
                }
                
            } else if(kirjain == '1'){
                 if(solmu.getOikeaLapsi() != null){
                    solmu = solmu.getOikeaLapsi();
                }
            }
            
            i++;
                        
        }
        
        for(int j = 0; j < tavut.length; j++){
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

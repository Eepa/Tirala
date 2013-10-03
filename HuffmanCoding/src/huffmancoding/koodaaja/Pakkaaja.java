package huffmancoding.koodaaja;

import huffmancoding.logiikka.Bittikasittelija;
import huffmancoding.logiikka.Kirjoittaja;
import huffmancoding.logiikka.Minimikeko;
import huffmancoding.logiikka.Numerokasittelija;
import huffmancoding.logiikka.Syotekasittelija;

/**
 * Pakkaaja, joka pakkaa k�ytt�j�n antaman tekstin.
 *
 * @author Eveliina
 */
public class Pakkaaja {

    /**
     * K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    private Syotekasittelija syotekasittelija;
    /**
     * Sis�lt�� sy�tteen merkkien esiintymism��r�t.
     */
    
    private byte[] tiedostonTavut;
    /**
     * Minimikeon operaatioita k�sittelev� luokka.
     */
    private Minimikeko minimikeko;
    /**
     * Keko, joka sis�lt�� tavut Node-luokan ilmentymin�.
     */
    private Node[] keko;
    
     /**
     * Puu, joka sis�lt�� tavujen uudet koodit.
     */
    
    private Tree puu;
    
    /**
     * Tavujen uudet bittikoodit sis�lt�v� taulukko.
     */
    private String[] uusienKoodienTaulukko;
    
    /**
     * Alkuper�isen tiedoston nimi.
     */
    private String tiedostonimi;
    
     /**
     * Bittioperaatioita k�sittelev� luokka.
     */
    
    private Bittikasittelija bittikasittelija;
    
    private Kirjoittaja kirjoittaja;

    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka k�sittelee tiedon
     * pakkaamista.
     *
     * @param syotekasittelija K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    public Pakkaaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
        this.uusienKoodienTaulukko = new String[256];
        this.bittikasittelija = new Bittikasittelija();
    }

    /**
     * K�ynnist�� pakkaajan toiminnan.
     */
    public void kaynnistaPakkaus() {


        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");


        this.tiedostonTavut = this.syotekasittelija.muutaTiedostoTavutaulukoksi(teksti);
        
        
        

        if (this.tiedostonTavut.length == 0) {
            System.out.println("Tiedosto oli tyhj� tai sit� ei l�ytynyt. Pakattua tiedostoa ei luotu.");
            return;
        }

        this.tiedostonimi = this.syotekasittelija.etsiTiedostonimi(teksti);
//        System.out.println(this.tiedostonimi);
        String tiedostopolku = this.syotekasittelija.etsiTiedostopolku(teksti);

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tiedostonTavut);
//        System.out.println(frekvenssit.length);

//        for(int i = 0; i < frekvenssit.length; i++){
//            System.out.println("Tavu on: " + (i-128) + " Esiintymiskertojen m��r� on: " + frekvenssit[i] + "\n");
//        }

        this.luoMinimikeko(frekvenssit);


        this.puu = this.muodostaPuu();

//        this.puu.tulostaAlkiotPreorder(this.puu.getJuuri());
//        System.out.println("\n");


        this.uusienKoodienTaulukko = this.puu.muodostaUudetKoodit(this.uusienKoodienTaulukko, "", this.puu.getJuuri());

        for (int i = 0; i < this.uusienKoodienTaulukko.length; i++) {
            if (this.uusienKoodienTaulukko[i] != null) {
                System.out.println("Tavun nimi: " + (i) + " Uusi koodi: " + this.uusienKoodienTaulukko[i]);
            }

        }


        this.kirjoittaja = new Kirjoittaja(this.uusienKoodienTaulukko, this.puu.getJuuri().getMaara());
                     
        boolean[][] tavut = this.kirjoittaja.muodostaUusiEsitys(this.tiedostonTavut);

        int[] numerotavut = this.bittikasittelija.muodostaNumerotavut(tavut, this.kirjoittaja.getOsoitin());

        byte[] tavuja = this.bittikasittelija.muunnaOikeiksiTavuiksi(numerotavut);
       
//        String frekvenssitSana = this.muodostaFrekvenssitString(frekvenssit);
        
        Numerokasittelija numerokasittelija = new Numerokasittelija();
        byte[] uudetTavut = numerokasittelija.kirjoitaFrekvenssitaulukkoTiedostoon(frekvenssit, tavuja);

//        this.syotekasittelija.luoPakattuTiedosto(this.tiedostonimi, frekvenssitSana, tavuja, tiedostopolku);
        
        this.syotekasittelija.luoPakattuTiedosto(this.tiedostonimi, uudetTavut, tiedostopolku);

    }
    
    /**
     * Muodostaa frekvenssitaulukosta String-muotoisen esityksen.
     * @param frekvenssit Frekvenssitaulukko, josta uusi esitys muodostetaan.
     * @return Palauttaa taulukon String-muotoisen esityksen.
     */

    public String muodostaFrekvenssitString(int[] frekvenssit) {

        String sana = "";

        for (int i = 0; i < frekvenssit.length-1; i++) {

//            if (frekvenssit[i] != 0) {
//                sana += i + "*" + 
                        sana += frekvenssit[i] + ";";
//            }

        }
        
        sana += frekvenssit[frekvenssit.length-1];


        return sana;
    }

    public Minimikeko getMinimikeko() {
        return this.minimikeko;
    }

    /**
     * Luo uuden minimikeon annettujen frekvenssien perusteella.
     *
     * @param frekvenssit Taulukko, jonka tietojen perusteella uusi keko
     * luodaan.
     */
    public void luoMinimikeko(int[] frekvenssit) {
        this.minimikeko = new Minimikeko(frekvenssit);

        this.keko = minimikeko.luoSolmut();

        for (int i = (this.keko[256].getMaara() / 2) - 1; i >= 0; i--) {
            this.keko = minimikeko.heapify(this.keko, i, this.keko[256].getMaara());
        }

    }

    /**
     * Muodostaa Huffman koodauksen puun annetusta keosta.
     */
    public Tree muodostaPuu() {

//        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun m��r�: " + this.keko[i].getMaara());
//
//        }

//        System.out.println(this.keko[256].getMaara());


        int laskuri = 0;

        int lahtoarvo = this.keko[256].getMaara() * 3;

        while (laskuri < lahtoarvo) {


            Node ensimmainenSolmu = this.minimikeko.poistaPienin(this.keko);
            laskuri++;


            Node toinenSolmu = this.minimikeko.poistaPienin(this.keko);
            laskuri++;



            Node uusiParentSolmu = new Node(-1000, ensimmainenSolmu.getMaara() + toinenSolmu.getMaara(), ensimmainenSolmu, toinenSolmu);

            this.minimikeko.lisaaAlkioKekoon(this.keko, uusiParentSolmu);
            laskuri++;



        }

//        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun m��r�: " + this.keko[i].getMaara());
//
//        }
//
//        System.out.println(this.keko[256].getMaara());

        return new Tree(this.minimikeko.poistaPienin(this.keko));

    }



}

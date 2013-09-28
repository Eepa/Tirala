package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;
import huffmancoding.koodaaja.Tree;

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
    private byte[] tavujenFrekvenssitaulukko;
    private byte[] tiedostonTavut;
    private Minimikeko minimikeko;
    private Node[] keko;
    private Tree puu;
    private String[] uusienKoodienTaulukko;
    private String tiedostonimi;
    private Bittikasittelija bittikasittelija;

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
            return;
        }

        this.tiedostonimi = this.syotekasittelija.etsiTiedostonimi(teksti);
//        System.out.println(this.tiedostonimi);

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tiedostonTavut);

//        for(int i = 0; i < frekvenssit.length; i++){
//            System.out.println("Tavu on: " + (i-128) + " Esiintymiskertojen m��r� on: " + frekvenssit[i] + "\n");
//        }

        this.luoMinimikeko(frekvenssit);


        this.puu = this.muodostaPuu();

//        this.puu.tulostaAlkiotPreorder(this.puu.getJuuri());
//        System.out.println("\n");


        this.uusienKoodienTaulukko = this.puu.muodostaUudetKoodit(this.uusienKoodienTaulukko, "", this.puu.getJuuri());

//        for (int i = 0; i < this.uusienKoodienTaulukko.length; i++) {
//            if (this.uusienKoodienTaulukko[i] != null) {
//                System.out.println("Tavun nimi: " + (i - 128) + " Uusi koodi: " + this.uusienKoodienTaulukko[i]);
//            }
//
//        }


        String sana = this.muodostaUusiStringEsitys("");


//        System.out.println("\n" + sana);

//        int roskabittienMaara = this.bittikasittelija.etsiRoskabittimaara(sana);
//        System.out.println(roskabittienMaara);

        sana = this.bittikasittelija.lisaaRoskabititSanaan(sana);

//        System.out.println(sana);

//
//        boolean[] bittitaulukko = this.bittikasittelija.muodostaBittitaulukko(sana.toCharArray());
//
//        boolean[][] tavut = this.bittikasittelija.jaaBittitaulukkoTavuihin(bittitaulukko);
//        
        boolean[][] tavut = this.bittikasittelija.jaaTavuihin(sana.toCharArray());

        int[] numerotavut = this.bittikasittelija.muodostaNumerotavut(tavut);
//        for(int i = 0; i < numerotavut.length; i++){
//            System.out.println("numerotavu " + numerotavut[i]);
//        }
//        byte[] tavuja = this.bittikasittelija.muodostaTavut(tavut);
        byte[] tavuja = this.bittikasittelija.muunnaOikeiksiTavuiksi(numerotavut);
       
        //ALLA MUODOSTETAAN PAKATTU ESITYS --> ENNEN SIT� TEE MUUTOKSIA SANAAN!
//
        String frekvenssitSana = this.muodostaFrekvenssitString(frekvenssit);

//        byte[] kirjoitettavatTavut = this.bittikasittelija.kopioiTavutLisaaRoskabittienMaara(tavuja, roskabittienMaara);



////        System.out.println(frekvenssitSana);
        this.syotekasittelija.luoPakattuTiedosto(this.tiedostonimi, frekvenssitSana, tavuja);

    }

    public String muodostaFrekvenssitString(int[] frekvenssit) {

        String sana = "";

        for (int i = 0; i < frekvenssit.length; i++) {

            if (frekvenssit[i] != 0) {
                sana += i + "*" + frekvenssit[i] + ";";
            }

        }


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

    public String muodostaUusiStringEsitys(String sana) {

        for (int i = 0; i < this.tiedostonTavut.length; i++) {


            String koodi = this.uusienKoodienTaulukko[this.tiedostonTavut[i] + 128];

            sana = sana + koodi;

        }


        return sana;
    }
}

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
    private Minimikeko minimikeko;
    private Node[] keko;
    private Tree puu;

    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka k�sittelee tiedon
     * pakkaamista.
     *
     * @param syotekasittelija K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    public Pakkaaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
    }

    /**
     * K�ynnist�� pakkaajan toiminnan.
     */
    public void kaynnistaPakkaus() {


        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");
        byte[] tiedostonTavut = this.syotekasittelija.muutaTiedostoTavutaulukoksi(teksti);

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tiedostonTavut);

//        for(int i = 0; i < frekvenssit.length; i++){
//            System.out.println("Tavu on: " + (i-128) + " Esiintymiskertojen m��r� on: " + frekvenssit[i] + "\n");
//        }

        this.luoMinimikeko(frekvenssit);
//        
//        this.minimikeko.poistaPienin(keko);
//        this.minimikeko.poistaPienin(keko);
//        this.minimikeko.poistaPienin(keko);
//
//        this.minimikeko.lisaaAlkioKekoon(keko, new Node(800, 1));
//
//        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun m��r�: " + this.keko[i].getMaara());
//
//        }
//        System.out.println(this.keko[256].getMaara());
//
//
//        this.minimikeko.poistaPienin(keko);
//        this.minimikeko.poistaPienin(keko);
//
//        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun m��r�: " + this.keko[i].getMaara());
//
//        }
//        System.out.println(this.keko[256].getMaara());

//        
        this.muodostaPuu();

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

    public void muodostaPuu() {

        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun m��r�: " + this.keko[i].getMaara());

        }

        System.out.println(this.keko[256].getMaara());


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

        for (int i = 0; i <= this.keko[256].getMaara()+5; i++) {
            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun m��r�: " + this.keko[i].getMaara());

        }

        System.out.println(this.keko[256].getMaara());

        this.puu = new Tree(this.minimikeko.poistaPienin(this.keko));
        this.puu.tulostaAlkiotPreorder(this.puu.getJuuri());

    }
}

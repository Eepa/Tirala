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
    /**
     * Kirjoittaa tiedoston tavuja uuteen muotoon bittiesitykseen.
     */
    private Kirjoittaja kirjoittaja;
    /**
     * K�sittelee numeroita ja muuttaa ne tavuiksi ja toisinp�in.
     */
    private Numerokasittelija numerokasittelija;

    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka k�sittelee tiedon
     * pakkaamista, ja alustetaan tarvittavat apuluokat.
     *
     * @param syotekasittelija K�ytt�j�n antamien sy�tteiden k�sittelij�.
     */
    public Pakkaaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
        this.uusienKoodienTaulukko = new String[256];
        this.bittikasittelija = new Bittikasittelija();
        this.numerokasittelija = new Numerokasittelija();
    }

    /**
     * K�ynnist�� pakkaajan toiminnan.
     */
    public void kaynnistaPakkaus() {

        //Alustetaan muutujia tiedoston nimeen ja polkuun liittyen ja tehd��n tarkastus,
        //onko tiedostoa olemassa.
        System.out.println("Varoitus! Tiedosto pakataan samaan kansioon, jossa alkuper�inen tiedosto sijaitsee. \n"
                + "Pakkaaminen korvaa kaikki uuden pakatun tiedoston nimiset tiedostot t�ss� kansiossa. \n"
                + "Pakatun tiedoston nimi muodossa: pakattu[alkuper�isenTiedostonNimi].ep");
        
        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");

        this.tiedostonTavut = this.syotekasittelija.muutaTiedostoTavutaulukoksi(teksti);

        if (this.tarkistaLoytyikoTiedosto(this.tiedostonTavut.length)) {
            return;
        }

        this.tiedostonimi = this.syotekasittelija.etsiTiedostonimi(teksti);

        String tiedostopolku = this.syotekasittelija.etsiTiedostopolku(teksti);



        //K�sitell��n uutta frekvenssitaulukkoa ja muodostetaan siit� uudet bittikoodit.

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(this.tiedostonTavut);

        this.luoMinimikeko(frekvenssit);

        this.puu = this.muodostaPuu();

        this.uusienKoodienTaulukko = this.puu.muodostaUudetKoodit(this.uusienKoodienTaulukko, "", this.puu.getJuuri());

        this.tulostaUudetKoodit();



        //Muodostetaan uusien koodien avulla uusi esitys tiedostolle ja kirjoitetaan 
        //se lopuksi tiedostoon.

        this.kirjoittaja = new Kirjoittaja(this.uusienKoodienTaulukko, this.puu.getJuuri().getMaara());

        boolean[][] tavut = this.kirjoittaja.muodostaUusiEsitys(this.tiedostonTavut);

        byte[] tavuja = this.bittikasittelija.muunnaOikeiksiTavuiksi(tavut, this.kirjoittaja.getOsoitin());

        byte[] uudetTavut = this.numerokasittelija.kirjoitaFrekvenssitaulukkoTiedostoon(frekvenssit, tavuja);


        this.syotekasittelija.luoPakattuTiedosto(this.tiedostonimi, uudetTavut, tiedostopolku);
    }

    /**
     * Tarkistaa l�ytyik� tiedostoa annetun polun avulla. Jos pituus on 0 niin
     * tiedostoa ei l�ytynyt.
     *
     * @param pituus Tiedoston pituus
     * @return Palauttaa true, jos tiedostoa ei l�ytynyt, muuten false.
     */
    public boolean tarkistaLoytyikoTiedosto(int pituus) {
        if (pituus == 0) {
            System.out.println("Tiedosto oli tyhj� tai sit� ei l�ytynyt. Pakattua tiedostoa ei luotu.");
            return true;
        }
        return false;
    }

    /**
     * Tulostaa tavujen uudet koodit.
     */
    public void tulostaUudetKoodit() {
        for (int i = 0; i < this.uusienKoodienTaulukko.length; i++) {
            if (this.uusienKoodienTaulukko[i] != null) {
                System.out.println("Tavun nimi: " + (i) + " Uusi koodi: " + this.uusienKoodienTaulukko[i]);
            }
        }
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

    public Node[] getKeko() {
        return this.keko;
    }

    /**
     * Muodostaa Huffman koodauksen puun annetusta keosta.
     */
    public Tree muodostaPuu() {

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

        return new Tree(this.minimikeko.poistaPienin(this.keko));
    }
}

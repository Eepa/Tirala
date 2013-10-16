package huffmancoding.koodaaja;

import huffmancoding.logiikka.Bittikasittelija;
import huffmancoding.logiikka.Kirjoittaja;
import huffmancoding.logiikka.Minimikeko;
import huffmancoding.logiikka.Numerokasittelija;
import huffmancoding.logiikka.Syotekasittelija;

/**
 * Pakkaaja, joka pakkaa käyttäjän antaman tekstin.
 *
 * @author Eveliina
 */
public class Pakkaaja {

    /**
     * Käyttäjän antamien syötteiden käsittelijä.
     */
    private Syotekasittelija syotekasittelija;
    /**
     * Sisältää syötteen merkkien esiintymismäärät.
     */
    private byte[] tiedostonTavut;
    /**
     * Minimikeon operaatioita käsittelevä luokka.
     */
    private Minimikeko minimikeko;
    /**
     * Keko, joka sisältää tavut Node-luokan ilmentyminä.
     */
    private Node[] keko;
    /**
     * Puu, joka sisältää tavujen uudet koodit.
     */
    private Tree puu;
    /**
     * Tavujen uudet bittikoodit sisältävä taulukko.
     */
    private String[] uusienKoodienTaulukko;
    /**
     * Alkuperäisen tiedoston nimi.
     */
    private String tiedostonimi;
    /**
     * Bittioperaatioita käsittelevä luokka.
     */
    private Bittikasittelija bittikasittelija;
    /**
     * Kirjoittaa tiedoston tavuja uuteen muotoon bittiesitykseen.
     */
    private Kirjoittaja kirjoittaja;
    /**
     * Käsittelee numeroita ja muuttaa ne tavuiksi ja toisinpäin.
     */
    private Numerokasittelija numerokasittelija;

    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka käsittelee tiedon
     * pakkaamista, ja alustetaan tarvittavat apuluokat.
     *
     * @param syotekasittelija Käyttäjän antamien syötteiden käsittelijä.
     */
    public Pakkaaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
        this.uusienKoodienTaulukko = new String[256];
        this.bittikasittelija = new Bittikasittelija();
        this.numerokasittelija = new Numerokasittelija();
    }

    /**
     * Käynnistää pakkaajan toiminnan.
     */
    public void kaynnistaPakkaus() {

        //Alustetaan muutujia tiedoston nimeen ja polkuun liittyen ja tehdään tarkastus,
        //onko tiedostoa olemassa.
        System.out.println("Varoitus! Tiedosto pakataan samaan kansioon, jossa alkuperäinen tiedosto sijaitsee. \n"
                + "Pakkaaminen korvaa kaikki uuden pakatun tiedoston nimiset tiedostot tässä kansiossa. \n"
                + "Pakatun tiedoston nimi muodossa: pakattu[alkuperäisenTiedostonNimi].ep");
        
        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");

        this.tiedostonTavut = this.syotekasittelija.muutaTiedostoTavutaulukoksi(teksti);

        if (this.tarkistaLoytyikoTiedosto(this.tiedostonTavut.length)) {
            return;
        }

        this.tiedostonimi = this.syotekasittelija.etsiTiedostonimi(teksti);

        String tiedostopolku = this.syotekasittelija.etsiTiedostopolku(teksti);



        //Käsitellään uutta frekvenssitaulukkoa ja muodostetaan siitä uudet bittikoodit.

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
     * Tarkistaa löytyikö tiedostoa annetun polun avulla. Jos pituus on 0 niin
     * tiedostoa ei löytynyt.
     *
     * @param pituus Tiedoston pituus
     * @return Palauttaa true, jos tiedostoa ei löytynyt, muuten false.
     */
    public boolean tarkistaLoytyikoTiedosto(int pituus) {
        if (pituus == 0) {
            System.out.println("Tiedosto oli tyhjä tai sitä ei löytynyt. Pakattua tiedostoa ei luotu.");
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

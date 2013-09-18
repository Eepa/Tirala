package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;
import huffmancoding.koodaaja.Tree;

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
    private byte[] tavujenFrekvenssitaulukko;
    private byte[] tiedostonTavut;
    private Minimikeko minimikeko;
    private Node[] keko;
    private Tree puu;
    private String[] uusienKoodienTaulukko;
    private String tiedostonimi;
    private Bittikasittelija bittikasittelija;

    /**
     * Konstruktorissa luodaan uusi pakkaaja, joka käsittelee tiedon
     * pakkaamista.
     *
     * @param syotekasittelija Käyttäjän antamien syötteiden käsittelijä.
     */
    public Pakkaaja(Syotekasittelija syotekasittelija) {
        this.syotekasittelija = syotekasittelija;
        this.uusienKoodienTaulukko = new String[256];
        this.bittikasittelija = new Bittikasittelija();
    }

    /**
     * Käynnistää pakkaajan toiminnan.
     */
    public void kaynnistaPakkaus() {


        String teksti = this.syotekasittelija.lueTiedostopolku("pakkaus");


        this.tiedostonTavut = this.syotekasittelija.muutaTiedostoTavutaulukoksi(teksti);

        if (this.tiedostonTavut.length == 0) {
            return;
        }

        this.tiedostonimi = this.syotekasittelija.etsiTiedostonimi(teksti);
        System.out.println(this.tiedostonimi);

        int[] frekvenssit = this.syotekasittelija.luoTavuistaFrekvenssitaululukko(tiedostonTavut);

//        for(int i = 0; i < frekvenssit.length; i++){
//            System.out.println("Tavu on: " + (i-128) + " Esiintymiskertojen määrä on: " + frekvenssit[i] + "\n");
//        }

        this.luoMinimikeko(frekvenssit);

//                for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());
//
//        }
//        System.out.println(this.keko[256].getMaara());
//        
//        this.minimikeko.poistaPienin(keko);
//        
//                for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());
//
//        }
//        System.out.println(this.keko[256].getMaara());
//        this.minimikeko.poistaPienin(keko);
//        
//                for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());
//
//        }
//        System.out.println(this.keko[256].getMaara());
//        
//         this.minimikeko.lisaaAlkioKekoon(keko, new Node(800, 2));
//
//        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());
//
//        }
//        System.out.println(this.keko[256].getMaara());
//        
//                this.minimikeko.poistaPienin(keko);
//
//        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());
//
//        }
//        System.out.println(this.keko[256].getMaara());
//        
//                this.minimikeko.poistaPienin(keko);
////
//       
////
//        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
//            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());
//
//        }
//        System.out.println(this.keko[256].getMaara());
////
////
////        this.minimikeko.poistaPienin(keko);
////        this.minimikeko.poistaPienin(keko);
////
////        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
////            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());
////
////        }
////        System.out.println(this.keko[256].getMaara());


        this.puu = this.muodostaPuu();

        this.puu.tulostaAlkiotPreorder(this.puu.getJuuri());
        System.out.println("\n");

//        this.puu.setSolmumaara(this.puu.laskeSolmut(this.puu.getJuuri()));
//        System.out.println(this.puu.getSolmumaara());


//        this.puu.tulostaAlkiotPostorder(this.puu.getJuuri() );
//        System.out.println("\n");
//        
//        this.puu.tulostaAlkiotInorder(this.puu.getJuuri() );
//        System.out.println("\n");


        this.uusienKoodienTaulukko = this.puu.muodostaUudetKoodit(this.uusienKoodienTaulukko, "", this.puu.getJuuri());

        for (int i = 0; i < this.uusienKoodienTaulukko.length; i++) {
            if (this.uusienKoodienTaulukko[i] != null) {
                System.out.println("Tavun nimi: " + (i - 128) + " Uusi koodi: " + this.uusienKoodienTaulukko[i]);
            }

        }

        boolean[] bitit = this.bittikasittelija.byteToBits(219);

        for (boolean b : bitit) {
            System.out.println(b);
        }




//        int[] numerokeko = this.muodostaPuustaKeko();
//        
//        for(int i = 0; i < numerokeko.length; i++){
//            System.out.println(numerokeko[i]);
//        }

        String sana = this.muodostaUusiStringEsitys("");


        System.out.println("\n" + sana);
//
//        String frekvenssitSana = this.muodostaFrekvenssitString(frekvenssit);
////        System.out.println(frekvenssitSana);
//        this.syotekasittelija.luoPakattuTiedosto(this.tiedostonimi, frekvenssitSana, sana);

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

        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());

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

        for (int i = 0; i <= this.keko[256].getMaara(); i++) {
            System.out.println("Solmu: " + this.keko[i].getTavu() + " Tavun määrä: " + this.keko[i].getMaara());

        }

        System.out.println(this.keko[256].getMaara());

        return new Tree(this.minimikeko.poistaPienin(this.keko));

    }

//    public int[] muodostaPuustaKeko() {
//
//        Node[] keko = new Node[this.puu.getSolmumaara()];
//
//        keko[0] = this.puu.getJuuri();
//    
//
//        for (int i = 0; i < this.puu.getSolmumaara(); i++) {
//           
//            Node nykSolmu = keko[i];
//            System.out.println(nykSolmu.getTavu());
//
//            int vasenLapsiIndeksi = i * 2 + 1;
//            int oikeaLapsiIndeksi = i * 2 + 2;
//
//            Node vasenLapsi = nykSolmu.getVasenLapsi();
//            
//            Node oikeaLapsi = nykSolmu.getVasenLapsi();
//
//            if (vasenLapsiIndeksi < keko.length) {
//                keko[vasenLapsiIndeksi] = vasenLapsi;
//            }
//
//            if (oikeaLapsiIndeksi < this.puu.getSolmumaara()) {
//                keko[oikeaLapsiIndeksi] = oikeaLapsi;
//            }
//
//        }
//        
//        int[] numerokeko = new int[keko.length];
//        
//        for(int j = 0; j < numerokeko.length; j++){
//            numerokeko[j] = keko[j].getTavu();
//        }
//        
//
//        return numerokeko;
//    }
    public String muodostaUusiStringEsitys(String sana) {

        for (int i = 0; i < this.tiedostonTavut.length; i++) {


            String koodi = this.uusienKoodienTaulukko[this.tiedostonTavut[i] + 128];

            sana = sana + koodi;

        }


        return sana;
    }
}

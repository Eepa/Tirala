package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;

/**
 * Minimikeko.
 *
 * @author evpa
 */
public class Minimikeko {

    /**
     * Kertoo tavujen esiintymistiheyden.
     */
    private int[] frekvenssit;

    /**
     * Luodaan uusi minimikeko.
     *
     * @param frekvenssit Tavujen esiintymistiheystaulukko.
     */
    public Minimikeko(int[] frekvenssit) {
        this.frekvenssit = frekvenssit;
    }

    /**
     * Luo minimikekon kuuluvat solmut jokaiselle tavulle.
     *
     * @return Palauttaa solmutaulukon.
     */
    public Node[] luoSolmut() {

        Node[] solmut = new Node[257];


        for (int i = 0; i < this.frekvenssit.length; i++) {
            Node uusiSolmu = new Node(i - 128, frekvenssit[i]);
            solmut[i] = uusiSolmu;
        }

        Node kekoPituussolmu = new Node(1000, 256);
        solmut[256] = kekoPituussolmu;

        return solmut;
    }

    /**
     * Palauttaa annetun indeksin vanhemman indeksin.
     *
     * @param indeksi Lähtösolmun indeksi.
     * @return Vanhemman indeksi.
     */
    public int parent(int indeksi) {
        return (int) Math.ceil(indeksi / 2);
    }

    /**
     * Palauttaa annetun indeksin vasemman lapsen indeksin.
     *
     * @param indeksi Lähtösolmun indeksi.
     * @return Vasemman lapsen indeksi.
     */
    public int vasenLapsi(int indeksi) {
        return 2 * indeksi + 1;
    }

    /**
     * Palauttaa annetun indeksin oikean lapsen indeksin.
     *
     * @param indeksi Lähtösolmun indeksi.
     * @return Oikean lapsen indeksi.
     */
    public int oikeaLapsi(int indeksi) {
        return (2 * indeksi) + 2;
    }

    /**
     * Muodostaa annetusta taulukosta minimikeon.
     *
     * @param keko Taulukko, josta keko muodostetaan.
     * @param indeksi Indeksi, josta heapify-operaatio aloittaa toiminnan.
     * @return Palauttaa valmiin minimikeon.
     */
    public Node[] heapify(Node[] keko, int indeksi, int keonPituus) {

        while ((this.vasenLapsi(indeksi)) <= keonPituus - 1) {
            int pienin = indeksi;

            if (keko[this.vasenLapsi(indeksi)].getMaara() <= keko[indeksi].getMaara()) {
                pienin = this.vasenLapsi(indeksi);
            }

            if (this.oikeaLapsi(indeksi) <= keonPituus - 1 && keko[this.oikeaLapsi(indeksi)].getMaara() < keko[pienin].getMaara()) {
                pienin = this.oikeaLapsi(indeksi);
            }

            if (pienin != indeksi) {
                Node pieninSolmu = keko[pienin];
                Node indeksiSolmu = keko[indeksi];
                keko[pienin] = indeksiSolmu;
                keko[indeksi] = pieninSolmu;
                indeksi = pienin;
            } else {
                indeksi = keonPituus + 1;
            }
        }

        return keko;
    }

    /**
     * Poistaa keosta pienimmän alkion.
     *
     * @param keko Keko, josta alkio poistetaan.
     * @return Palauttaa viitten poistettuun alkioon.
     */
    public Node poistaPienin(Node[] keko) {
        Node pienin = keko[0];

        keko[0] = keko[keko[256].getMaara()-1];

        keko[256].muutaMaaraa(-1);

        this.heapify(keko, 0, keko[256].getMaara());

        return pienin;
    }

    /**
     * Lisää annettuun kekoon uuden alkion ja korjaa keon.
     *
     * @param keko Keko, johon uusi alkio lisätään.
     * @param uusiSolmu Solmu, joka lisätään kekoon.
     */
    public void lisaaAlkioKekoon(Node[] keko, Node uusiSolmu) {
        keko[256].muutaMaaraa(1);
        int indeksi = keko[256].getMaara();

        while (indeksi > 0 && keko[this.parent(indeksi)].getMaara() > uusiSolmu.getMaara()) {
            keko[indeksi] = keko[this.parent(indeksi)];
            indeksi = this.parent(indeksi);
        }
        keko[indeksi] = uusiSolmu;
    }
}

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

        Node[] solmut = new Node[256];


        for (int i = 0; i < this.frekvenssit.length; i++) {
            Node uusiSolmu = new Node(i - 128, frekvenssit[i]);
            solmut[i] = uusiSolmu;
        }

        return solmut;
    }

    /**
     * Muodostaa annetusta taulukosta minimikeon.
     *
     * @param keko Taulukko, josta keko muodostetaan.
     * @param indeksi Indeksi, josta heapify-operaatio aloittaa toiminnan.
     * @return Palauttaa valmiin minimikeon.
     */
    public Node[] heapify(Node[] keko, int indeksi) {

        while ((2 * indeksi + 1) <= keko.length - 1) {
            int pienin = indeksi;

            if (keko[2 * indeksi + 1].getMaara() <= keko[indeksi].getMaara()) {
                pienin = 2 * indeksi + 1;
            }

            if ((2 * indeksi) + 2 <= keko.length - 1 && keko[(2 * indeksi) + 2].getMaara() < keko[pienin].getMaara()) {
                pienin = (2 * indeksi) + 2;
            }

            if (pienin != indeksi) {
                Node pieninSolmu = keko[pienin];
                Node indeksiSolmu = keko[indeksi];
                keko[pienin] = indeksiSolmu;
                keko[indeksi] = pieninSolmu;
                indeksi = pienin;
            } else {
                indeksi = keko.length + 1;
            }
        }

        return keko;

    }

    public Node poistaPienin(Node[] keko) {
        Node pienin = keko[0];

        keko[0] = keko[keko.length - 1];



        return pienin;
    }
}

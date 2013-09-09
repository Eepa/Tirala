package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;

/**
 * Maksimikeko.
 *
 * @author evpa
 */
public class Maksimikeko {

    /**
     * Kertoo tavujen esiintymistiheyden.
     */
    private int[] frekvenssit;

    /**
     * Luodaan uusi maksimikeko.
     *
     * @param frekvenssit Tavujen esiintymistiheystaulukko.
     */
    public Maksimikeko(int[] frekvenssit) {
        this.frekvenssit = frekvenssit;
    }

    /**
     * Luo maksimikekoon kuuluvat solmut jokaiselle tavulle.
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
     * Muodostaa annetusta taulukosta maksimikeon.
     * @param keko Taulukko, josta keko muodostetaan.
     * @param indeksi Indeksi, josta heapify-operaatio aloittaa toiminnan
     * @return Palauttaa valmiin maksimikeon.
     */

        public Node[] heapify(Node[] keko, int indeksi) {
        
        while((2 * indeksi) <= keko.length -1){
            int suurin = indeksi;
            
            if(keko[2 * indeksi].getMaara() >= keko[indeksi].getMaara()){
                suurin = 2 * indeksi;
            }
            
            if((2 * indeksi) + 1 <= keko.length-1 && keko[(2 * indeksi) + 1].getMaara() > keko[suurin].getMaara()){
                suurin = (2 * indeksi) + 1;
            }
            
            if(suurin != indeksi){
                Node suurinSolmu = keko[suurin];
                Node indeksiSolmu = keko[indeksi];
                keko[suurin] = indeksiSolmu;
                keko[indeksi] = suurinSolmu;
                indeksi = suurin;
            }
            else {
                indeksi = keko.length;
            }
        }

//        int vasenLapsi = 2 * indeksi;
//        int oikea = 2 * indeksi + 1;
//        int suurin;
//
//        if (vasenLapsi < keko.length-1 && keko[vasenLapsi].getMaara() > keko[indeksi].getMaara()) {
//            suurin = vasenLapsi;
//          
//        } else {
//            suurin = indeksi;
//        }
//        
//        if(oikea < keko.length-1 && keko[oikea].getMaara() > keko[suurin].getMaara() ){
//            suurin = oikea;
//        }
//        
//        if(suurin != indeksi){
//            Node indeksiN = keko[indeksi];
//            Node suurinN = keko[suurin];
//            keko[indeksi] = new Node(suurinN.getTavu(), suurinN.getMaara());
//            keko[suurin] = new Node(indeksiN.getTavu(), indeksiN.getMaara());
//            heapify(keko, suurin);
//        }
        
        return keko;

    }
}

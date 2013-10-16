package huffmancoding.koodaaja;

/**
 * Kuvaa bin‰‰rihakupuuta.
 *
 * @author evpa
 */
public class Tree {

    /**
     * Bin‰‰rihakupuun juuri;
     */
    private Node juuri;

    /**
     * Konstruktori alustaa puulle juuren.
     *
     * @param juuri
     */
    public Tree(Node juuri) {
        this.juuri = juuri;

    }

    /**
     * Palautta puun juuren.
     *
     * @return
     */
    public Node getJuuri() {
        return this.juuri;
    }

//    /**
//     * Tulostaa puun alkiot esij‰rjestyksess‰.
//     *
//     * @param solmu Puun juurisolmu.
//     */
//    public void tulostaAlkiotPreorder(Node solmu) {
//
//        if (solmu != null) {
//            System.out.println("Tavun nimi:" + solmu.getTavu() + " M‰‰r‰: " + solmu.getMaara());
//
//            tulostaAlkiotPreorder(solmu.getVasenLapsi());
//
//            tulostaAlkiotPreorder(solmu.getOikeaLapsi());
//        }
//    }
    /**
     * Muodostaa uudet bittikoodit tavuille.
     *
     * @param taulukko Taulukko, johon uudet koodit tallennetaan.
     * @param uusiKoodi Uusi koodi tietylle tavulle.
     * @param solmu Puun solmu, jossa kullakin hetkell‰ ollaan.
     * @return Palauttaa valmiin uusien koodien taulukon.
     */
    public String[] muodostaUudetKoodit(String[] taulukko, String uusiKoodi, Node solmu) {
        if (solmu != null) {
            if (solmu.getTavu() != -1000) {
                taulukko[solmu.getTavu() + 128] = uusiKoodi;
            }
            muodostaUudetKoodit(taulukko, uusiKoodi + "0", solmu.getVasenLapsi());
            muodostaUudetKoodit(taulukko, uusiKoodi + "1", solmu.getOikeaLapsi());
        }

        return taulukko;
    }
}

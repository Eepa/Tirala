package huffmancoding.koodaaja;

/**
 * Kuvaa binäärihakupuuta.
 *
 * @author evpa
 */
public class Tree {

    /**
     * Binäärihakupuun juuri;
     */
    private Node juuri;
    private int solmumaara;

    /**
     * Konstruktori alustaa puulle juuren.
     *
     * @param juuri
     */
    public Tree(Node juuri) {
        this.juuri = juuri;
        this.solmumaara = 0;
    }

    /**
     * Palautta puun juuren.
     *
     * @return
     */
    public Node getJuuri() {
        return this.juuri;
    }

    /**
     * Asettaa uuden juuren puulle.
     *
     * @param uusiJuuri
     */
    public void setJuuri(Node uusiJuuri) {
        this.juuri = uusiJuuri;
    }

    /**
     * Tulostaa puun alkiot esijärjestyksessä.
     *
     * @param solmu Puun juurisolmu.
     */
    public void tulostaAlkiotPreorder(Node solmu) {

        if (solmu != null) {
            System.out.println("Tavun nimi:" + solmu.getTavu() + " Määrä: " + solmu.getMaara());

            tulostaAlkiotPreorder(solmu.getVasenLapsi());

            tulostaAlkiotPreorder(solmu.getOikeaLapsi());
        }
    }

//        public void tulostaAlkiotInorder(Node solmu){
//        if(solmu != null){
//            
//            
//            tulostaAlkiotPreorder(solmu.getVasenLapsi());
//            System.out.println("Tavun nimi:" + solmu.getTavu() + " Määrä: " + solmu.getMaara());
//            tulostaAlkiotPreorder(solmu.getOikeaLapsi());
//        }
//    }
//        
//            public void tulostaAlkiotPostorder(Node solmu){
//        if(solmu != null){
//           
//            
//            tulostaAlkiotPreorder(solmu.getVasenLapsi());
//            
//            tulostaAlkiotPreorder(solmu.getOikeaLapsi());
//             System.out.println("Tavun nimi:" + solmu.getTavu() + " Määrä: " + solmu.getMaara());
//        }
//    }
    public String[] muodostaUudetKoodit(String[] taulukko, String uusiSana, Node solmu) {
        if (solmu != null) {
            if (solmu.getTavu() != -1000) {
                taulukko[solmu.getTavu() + 128] = uusiSana;
            }
            muodostaUudetKoodit(taulukko, uusiSana + "0", solmu.getVasenLapsi());
            muodostaUudetKoodit(taulukko, uusiSana + "1", solmu.getOikeaLapsi());
        }

        return taulukko;
    }

    public void setSolmumaara(int maara) {
        this.solmumaara = maara;
    }

    public int getSolmumaara() {
        return this.solmumaara;
    }

    public int laskeSolmut(Node juuri) {

        int maara = 0;

        if (juuri != null) {
            maara = 1 + laskeSolmut(juuri.getVasenLapsi()) + laskeSolmut(juuri.getOikeaLapsi());
        }

        return maara;
    }
}

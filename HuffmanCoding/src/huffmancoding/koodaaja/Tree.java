
package huffmancoding.koodaaja;

/**
 * Kuvaa binäärihakupuuta.
 * @author evpa
 */
public class Tree {
    
    /**
     * Binäärihakupuun juuri;
     */
    
    private Node juuri;
    
    /**
     * Konstruktori alustaa puulle juuren.
     * @param juuri 
     */
    
    public Tree(Node juuri){
        this.juuri = juuri;
    }
    
    /**
     * Palautta puun juuren.
     * @return 
     */
    
    public Node getJuuri(){
        return this.juuri;
    }
    
    /**
     * Asettaa uuden juuren puulle.
     * @param uusiJuuri 
     */
    
    public void setJuuri(Node uusiJuuri){
        this.juuri = uusiJuuri;
    }
    
    /**
     * Tulostaa puun alkiot esijärjestyksessä.
     * @param solmu Puun juurisolmu.
     */
    
    public void tulostaAlkiotPreorder(Node solmu){
        if(solmu != null){
            System.out.println("Tavun nimi:" + solmu.getTavu() + " Määrä: " + solmu.getMaara());
            
            tulostaAlkiotPreorder(solmu.getVasenLapsi());
            
            tulostaAlkiotPreorder(solmu.getOikeaLapsi());
        }
    }
    
}

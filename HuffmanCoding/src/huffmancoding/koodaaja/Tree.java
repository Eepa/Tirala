
package huffmancoding.koodaaja;

/**
 * Kuvaa bin‰‰rihakupuuta.
 * @author evpa
 */
public class Tree {
    
    /**
     * Bin‰‰rihakupuun juuri;
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
     * Tulostaa puun alkiot esij‰rjestyksess‰.
     * @param solmu Puun juurisolmu.
     */
    
    public void tulostaAlkiotPreorder(Node solmu){
//        if(solmu.getOikeaLapsi() != null || solmu.getVasenLapsi() != null){}
        
            if(solmu != null){
            System.out.println("Tavun nimi:" + solmu.getTavu() + " M‰‰r‰: " + solmu.getMaara());
            
            tulostaAlkiotPreorder(solmu.getVasenLapsi());
            
            tulostaAlkiotPreorder(solmu.getOikeaLapsi());
        }
    }
    
//        public void tulostaAlkiotInorder(Node solmu){
//        if(solmu != null){
//            
//            
//            tulostaAlkiotPreorder(solmu.getVasenLapsi());
//            System.out.println("Tavun nimi:" + solmu.getTavu() + " M‰‰r‰: " + solmu.getMaara());
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
//             System.out.println("Tavun nimi:" + solmu.getTavu() + " M‰‰r‰: " + solmu.getMaara());
//        }
//    }
    
    public String[] muodostaUudetKoodit(String[] taulukko, String uusiSana, Node solmu){
        if(solmu != null){
            if(solmu.getTavu() != -1000){
                taulukko[solmu.getTavu()+128] = uusiSana;
            }
            muodostaUudetKoodit(taulukko, uusiSana + "0", solmu.getVasenLapsi());
            muodostaUudetKoodit(taulukko, uusiSana + "1", solmu.getOikeaLapsi());
        }
        
        
        return taulukko;
    }
    
}


package huffmancoding.logiikka;

import huffmancoding.koodaaja.Node;

/**
 * Maksimikeko.
 * @author evpa
 */
public class Maksimikeko {
    
    /**
     * Kertoo tavujen esiintymistiheyden.
     */
    
    private int[] frekvenssit;
    
    /**
     * Luodaan uusi maksimikeko.
     * @param frekvenssit Tavujen esiintymistiheystaulukko. 
     */
    
    public Maksimikeko(int[] frekvenssit){
        this.frekvenssit = frekvenssit;
    }
    
    /**
     * Luo maksimikekoon kuuluvat solmut jokaiselle tavulle.
     * @return Palauttaa solmutaulukon.
     */
    
    public Node[] luoSolmut(){
        
        Node[] solmut = new Node[256];
        
                
        for(int i = 0;  i < this.frekvenssit.length; i++){
            Node uusiSolmu = new Node(i-128, frekvenssit[i]);
            solmut[i] = uusiSolmu;
        }
        
        return solmut;
    }
    
}

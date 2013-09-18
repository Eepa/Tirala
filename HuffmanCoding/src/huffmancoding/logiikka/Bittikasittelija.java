package huffmancoding.logiikka;

/**
 * Bittejä käsittelevä luokka.
 *
 * @author Eveliina
 */
public class Bittikasittelija {

    public Bittikasittelija() {
    }

    public boolean[] byteToBits(int data) {

        if (data < 0 || 255 < data) {
            throw new IllegalArgumentException("Vääränlainen luku: " + data);
        }

        boolean[] bits = new boolean[8];
        for (int i = 0; i < 8; i++) {
            bits[i] = ((data & (1 << (7 - i))) != 0);

        }
        return bits;

    }

    public int bitsToByte(boolean[] bits) {
        if (bits == null || bits.length != 8) {
            throw new IllegalArgumentException("Vääränlainen taulukko.");
        }
        int data = 0;

        for (int i = 0; i < 8; i++) {
            if (bits[i]) {
                data += (1 << (7 - i));
            }
        }
        return data;


    }

    public int etsiRoskabittimaara(String sana) {
        return 8 - (sana.length() % 8);
    }
    
    public String lisaaRoskabititSanaan(String sana){
        
        int maara = this.etsiRoskabittimaara(sana);
        
        for(int i = 0; i < maara; i++){
            sana += "0";
        }
        
        return sana;
    }
    
      public boolean[] muodostaBittitaulukko(char[] merkit){
        
        boolean[] bitit = new boolean[merkit.length];
        
        for(int i = 0; i < merkit.length; i++){
            
            if(merkit[i] == '1'){
                bitit[i] = true;
            } else {
                bitit[i] = false;
            }
            
            
        }
        return bitit;
                
    }
}

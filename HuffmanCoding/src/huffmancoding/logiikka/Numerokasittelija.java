package huffmancoding.logiikka;

/**
 * K‰sittelee erilaisia syˆtteit‰ ja muokkaa niit‰ uusiin muotoihin tarpeen
 * mukaan.
 *
 * @author Eveliina
 */
public class Numerokasittelija {

    public Numerokasittelija() {
    }

    /**
     * Integerin muuttaminen tavutaulukoksi. Koodi t‰‰lt‰:
     * http://dzone.com/snippets/convert-int-byte-array
     */
    public byte[] intToByteArray(int numero) {

        byte[] taulukko = new byte[]{
            (byte) (numero >>> 24),
            (byte) (numero >>> 16),
            (byte) (numero >>> 8),
            (byte) numero
        };

        return taulukko;

        /**
         * ByteArray Integeriksi. Koodi t‰‰lt‰:
         * http://dzone.com/snippets/convert-int-byte-array
         */
    }

    public int byteArrayToInt(byte[] tavut) {
        int numero = (tavut[0] << 24) + ((tavut[1] & 0xFF) << 16) + ((tavut[2] & 0xFF) << 8) + (tavut[3] & 0xFF);
        return numero;
    }
    
    /**
     * Lis‰‰ frekvenssit tiedostoon.
     * @param frekvenssit
     * @param taulukko
     * @return 
     */
    
    public byte[] kirjoitaFrekvenssitaulukkoTiedostoon(int[] frekvenssit, byte[] taulukko){
        int laskuri = 0;
        for(int i = 0; i< frekvenssit.length; i++){
            
            byte[] numeroTavuina = this.intToByteArray(frekvenssit[i]);
            taulukko = this.kopioiTavunumeroTaulukkoon(numeroTavuina, taulukko, laskuri);
            
            laskuri += 4;
        }
        
        return taulukko;
    }
    
    /**
     * Kopioi tavutaulukko tiedostoon.
     */
    
    public byte[] kopioiTavunumeroTaulukkoon(byte[] numero, byte[] taulukko, int indeksi){
     
        for(int i = 0; i < numero.length; i++){
            taulukko[indeksi] = numero[i];
            indeksi++;
        }
        
        
        return taulukko;
    }
    
    public int[] muodostaFrekvenssitaulukkoUudestaan(byte[] tavut){
        int laskuri  = 0;
        
        int[] frekvenssit = new int[256];
        
        for(int i = 0; i < 256; i++){
            byte[] numero = this.poimiNumeroTaulukosta(laskuri, tavut);
            int frekvenssi = this.byteArrayToInt(numero);
            frekvenssit[i] = frekvenssi;
            
            laskuri += 4;
            
        }
        return frekvenssit;
    }
    
    public byte[] poimiNumeroTaulukosta(int indeksi, byte[] tavut){
        byte[] numero = new byte[4];
        
        for(int i = 0; i < 4; i++){
            numero[i] = tavut[indeksi];
            
            indeksi++;
        }
        return numero;
    }
    
    
}

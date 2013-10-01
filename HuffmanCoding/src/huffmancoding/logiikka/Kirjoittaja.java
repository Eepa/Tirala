package huffmancoding.logiikka;

/**
 * Luokka kirjoittaa tavuja tiedostoon.
 *
 * @author Eveliina
 */
public class Kirjoittaja {

    private String[] uudetEsitykset;
    private int bitti;
    private int osoitin;
//    private boolean[][] tiedostonTavut;
    private boolean[] tiedostonTavut;
    private Bittikasittelija bittikasittelija;

    public Kirjoittaja(String[] uudetEsitykset, int koko) {
        this.uudetEsitykset = uudetEsitykset;
        this.bitti = 0;
        this.osoitin = 0;
//        this.tiedostonTavut = new boolean[koko][8];
        this.tiedostonTavut = new boolean[(koko * 8)];
        this.bittikasittelija = new Bittikasittelija();
    }

    public void kirjoitaTavu(int tavu) {

        String tavunEsitys = this.uudetEsitykset[tavu];

        for (int i = 0; i < tavunEsitys.length(); i++) {
            
            System.out.println("Osoitin oli: " + this.osoitin);
            if (tavunEsitys.charAt(i) == '0') {
                this.kirjoitaBitti(false);
            } else {
                this.kirjoitaBitti(true);
            }
            this.kasvataOsoitinta();
        }
    }

//    public void kirjoitaBitti(boolean bitti){
//        this.tiedostonTavut[this.osoitin][this.bitti] = bitti;
//        
//    }
    public void kirjoitaBitti(boolean bitti) {
        this.tiedostonTavut[this.osoitin] = bitti;
    }

    public void kasvataOsoitinta() {
        this.osoitin += 1;
    }

    public void kasvataBittia() {

        if (this.bitti == 7) {
            this.bitti = 0;
            this.kasvataOsoitinta();
        } else {
            this.bitti += 1;
        }
    }

    public int getOsoitin() {
        return this.osoitin;
    }

    public boolean[] muodostaUusiEsitys(byte[] alkuperaisenTiedostonTavut) {
        System.out.println("Alkuperäisen tiedoston koko " + alkuperaisenTiedostonTavut.length);
        System.out.println("Bittijonon pituus " + this.tiedostonTavut.length);
        for (int i = 0; i < alkuperaisenTiedostonTavut.length; i++) {
            System.out.println("Tavu oli: " + (alkuperaisenTiedostonTavut[i] + 128));

            this.kirjoitaTavu(alkuperaisenTiedostonTavut[i] + 128);

        }

//        this.tiedostonTavut = this.bittikasittelija.lisaaRoskabititSanaan(this.tiedostonTavut, this.osoitin);

        return this.tiedostonTavut;
    }
}

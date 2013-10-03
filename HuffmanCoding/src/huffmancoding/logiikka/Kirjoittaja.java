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
    private boolean[][] tiedostonTavut;

    public Kirjoittaja(String[] uudetEsitykset, int koko) {
        this.uudetEsitykset = uudetEsitykset;
        this.bitti = 0;
        this.osoitin = 0;
        this.tiedostonTavut = new boolean[koko][8];

    }

    public void kirjoitaTavu(int tavu) {

        String tavunEsitys = this.uudetEsitykset[tavu];

        for (int i = 0; i < tavunEsitys.length(); i++) {

            if (tavunEsitys.charAt(i) == '0') {
                this.kirjoitaBitti(false);
            } else {
                this.kirjoitaBitti(true);
            }
            this.kasvataBittia();
        }
    }

    public void kirjoitaBitti(boolean bitti) {
        try{
            this.tiedostonTavut[this.osoitin][this.bitti] = bitti;
        } catch (Exception e){
            System.out.println("Osoitin: " + this.osoitin + " ja bitti " + this.bitti);
        }
        

    }

    public void kasvataOsoitinta() {
        this.osoitin += 1;
        if(this.osoitin >= tiedostonTavut.length){
            this.kasvataTavuja();
        }
    }
    
    public void kasvataTavuja(){
        boolean[][] uusiTaulukko = new boolean[this.tiedostonTavut.length*2][8];
        for(int i = 0; i < this.tiedostonTavut.length; i++){
            for(int j = 0; j < this.tiedostonTavut[i].length; j++){
                uusiTaulukko[i][j] = this.tiedostonTavut[i][j];
            }
        }
        this.tiedostonTavut = uusiTaulukko;
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

    public boolean[][] muodostaUusiEsitys(byte[] alkuperaisenTiedostonTavut) {

        for (int i = 0; i < alkuperaisenTiedostonTavut.length; i++) {


            this.kirjoitaTavu(alkuperaisenTiedostonTavut[i] + 128);

        }


        return this.tiedostonTavut;
    }
}

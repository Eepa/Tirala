package huffmancoding.logiikka;

/**
 * Luokka kirjoittaa tavuja tiedostoon.
 *
 * @author Eveliina
 */
public class Kirjoittaja {

    /**
     * Tavujen uudet String-muotoiset "bittiesitykset".
     */
    private String[] uudetEsitykset;
    /**
     * Kertoo, monennessako bitiss‰ menn‰‰n nykyisess‰ tavussa.
     */
    private int bitti;
    /**
     * Kertoo, miss‰ eli monennessa tavussa menn‰‰n tiedostossa.
     */
    private int osoitin;
    /**
     * Tavut, joista muodostetaan uusi tiedosto. Sis‰lt‰‰ bititt‰isen esityksen
     * tavuista.
     */
    private boolean[][] tiedostonTavut;

    /**
     * Konstruktorissa alustetaan bitti ja osoitin tiedoston alkuun ja luodaan
     * uusi tavut sis‰lt‰v‰ tiedosto.
     *
     * @param uudetEsitykset Tavujen bittimuotoiset esitykset, jotka lis‰t‰‰n
     * oliomuuttujaan.
     * @param koko Alkuper‰isen tiedoston tavum‰‰r‰.
     */
    public Kirjoittaja(String[] uudetEsitykset, int koko) {
        this.uudetEsitykset = uudetEsitykset;
        this.bitti = 0;
        this.osoitin = 0;
        int kerrottuKoko = koko * 2;
        this.tiedostonTavut = new boolean[kerrottuKoko][8];

    }

    /**
     * Kirjoittaa yhden uuden tavun tiedostoon biteitt‰in.
     *
     * @param tavu Tavu, joka tiedostoon pit‰‰ kirjoittaa.
     */
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

    /**
     * Bitti, joka kirjoitetaan tavuun.
     *
     * @param bitti Kirjoitettava bitti.
     */
    public void kirjoitaBitti(boolean bitti) {
        try {
            this.tiedostonTavut[this.osoitin][this.bitti] = bitti;
        } catch (Exception e) {
            System.out.println("Osoitin: " + this.osoitin + " ja bitti " + this.bitti);
        }
    }
    
    public boolean[][] getTavutaulukko(){
        return this.tiedostonTavut;
    }

    /**
     * Kasvattaa osoitinta, kun tavuja muodostetaan lis‰‰. Kasvattaa
     * tavutaulukon kokoa, jos uusia tavuja tulee enemm‰n kuin vanhoja.
     */
    public void kasvataOsoitinta() {
        this.osoitin += 1;
        if (this.osoitin >= tiedostonTavut.length) {
            this.kasvataTavutaulukkoa();
        }
    }

    /**
     * Kasvattaa tavutaulukon kokoa kaksinkertaiseksi ja kopioi vanhan taulukon
     * uuteen taulukkoon.
     */
    public void kasvataTavutaulukkoa() {
        boolean[][] uusiTaulukko = new boolean[this.tiedostonTavut.length * 2][8];
        for (int i = 0; i < this.tiedostonTavut.length; i++) {
            for (int j = 0; j < this.tiedostonTavut[i].length; j++) {
                uusiTaulukko[i][j] = this.tiedostonTavut[i][j];
            }
        }
        this.tiedostonTavut = uusiTaulukko;
    }

    /**
     * Kasvattaa bitti‰, joka kertoo, miss‰ kohtaa ollaan tavussa. Jos tavun
     * koko ylittyy, siirryt‰‰n uuteen tavuun.
     */
    public void kasvataBittia() {

        if (this.bitti == 7) {
            this.bitti = 0;
            this.kasvataOsoitinta();
        } else {
            this.bitti += 1;
        }
    }

    public int getBitti(){
        return this.bitti;
    }
    public int getOsoitin() {
        return this.osoitin;
    }

    /**
     * Muodostaa uuden bittimuotoisen esityksen alkuper‰isen tiedoston tavuista.
     * @param alkuperaisenTiedostonTavut Tavut, joista uusi esitys muodostetaan.
     * @return Palauttaa bittimuotoisen esityksen.
     */
    public boolean[][] muodostaUusiEsitys(byte[] alkuperaisenTiedostonTavut) {

        for (int i = 0; i < alkuperaisenTiedostonTavut.length; i++) {

            this.kirjoitaTavu(alkuperaisenTiedostonTavut[i] + 128);

        }
        return this.tiedostonTavut;
    }
}

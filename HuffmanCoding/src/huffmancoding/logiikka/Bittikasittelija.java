package huffmancoding.logiikka;

/**
 * Bittej‰ ja samalla tavuja k‰sittelev‰ luokka.
 *
 * @author Eveliina
 */
public class Bittikasittelija {

    public Bittikasittelija() {
    }

    /**
     * Muuttaa tavun biteiksi. Koodi t‰‰lt‰:
     * http://www.cs.helsinki.fi/u/ejunttil/opetus/tiraharjoitus/bittiohje.txt
     *
     * @param data Tavu (v‰lilt‰ 0-255), joka muutetaan biteiksi (true = 1,
     * false = 0).
     * @return Palauttaa tavun bittiesityksen.
     */
    public boolean[] byteToBits(int data) {

        if (data < 0 || 255 < data) {
            throw new IllegalArgumentException("V‰‰r‰nlainen luku: " + data);
        }

        boolean[] bits = new boolean[8];
        for (int i = 0; i < 8; i++) {
            bits[i] = ((data & (1 << (7 - i))) != 0);

        }
        return bits;

    }

    /**
     * Muuttaa bitit tavuksi. Koodi t‰‰lt‰:
     * http://www.cs.helsinki.fi/u/ejunttil/opetus/tiraharjoitus/bittiohje.txt
     *
     * @param bits Bitit, joista muodostetaan tavu v‰lilt‰ 0-255.
     * @return Palauttaa bittej‰ vastaavan tavun.
     */
    public int bitsToByte(boolean[] bits) {
        if (bits == null || bits.length != 8) {
            throw new IllegalArgumentException("V‰‰r‰nlainen taulukko.");
        }
        int data = 0;

        for (int i = 0; i < 8; i++) {
            if (bits[i]) {
                data += (1 << (7 - i));
            }
        }
        return data;


    }

    /**
     * Muodostaa annetuista bittimuotoisista tavuista niit‰ vastaavat
     * numerotavut.
     *
     * @param tavut Bittimuotoiset tavut, jotka muutetaan numeroiksi.
     * @return
     */
    public int[] muodostaNumerotavut(boolean[][] tavut, int osoitin) {

        int[] numerotavut = new int[osoitin + 1];

        for (int i = 0; i < osoitin + 1; i++) {
            int numero = this.bitsToByte(tavut[i]);
            numerotavut[i] = numero;
        }

        return numerotavut;
    }

    /**
     * Muuntaa numeromuotoiset tavut oikeiksi Javan tavuiksi (v‰lill‰ -127-128).
     *
     * @param numerotavut Numerotavut, jotka muutetaan byte-muotoon.
     * @return Palauttaa numerotavuja vastaavat byte-tavut.
     */
    public byte[] muunnaOikeiksiTavuiksi(int[] numerotavut) {
        byte[] tavuja = new byte[numerotavut.length + 1024];

        int laskuri = 0;

        for (int i = 1024; i < tavuja.length; i++) {

            int k = numerotavut[laskuri] - 128;
            byte tavu = (byte) k;

            tavuja[i] = tavu;
            laskuri++;
        }
        return tavuja;
    }

    /**
     * Muuntaa byte-muotoiset tavut numerotavuiksi v‰lille 0-255.
     *
     * @param tavuja Tavut, jotka muunnetaan numeroiksi.
     * @return Palauttaa annettuja tavuja vastaavat numerotavut.
     */
    public int[] muunnaNumerotavuiksi(byte[] tavuja) {
        int[] numerotavut = new int[tavuja.length - 1024];
        int laskuri = 0;
        for (int i = 1024; i < tavuja.length; i++) {
//            System.out.println("Tavu oli " + tavuja[i]);
            int k = tavuja[i] + 128;

            numerotavut[laskuri] = k;
            laskuri++;
        }
        return numerotavut;


    }

    /**
     * Muodostaa annetuista numerotavuista ensin bittimuotoiset tavut ja kopioi
     * ne sitten yhteen boolean-taulukkoon lukemista varten. Taulukko vastaa
     * vanhan tiedoston bittiesityst‰.
     *
     * @param numerotavut Numerotavut, joista bittiesitys muodostetaan.
     * @return Palauttaa tiedoston bittiesityksen (true = 1, false = 0).
     */
    public boolean[] muodostaLuettavatTavut(int[] numerotavut) {

        boolean[] luettavatTavut = new boolean[numerotavut.length * 8];
        boolean[][] valiaikaisetTavut = new boolean[numerotavut.length][8];

        for (int i = 0; i < numerotavut.length; i++) {
            boolean[] uudetTavut = this.byteToBits(numerotavut[i]);


            for (int j = 0; j < uudetTavut.length; j++) {
                valiaikaisetTavut[i][j] = uudetTavut[j];
            }

        }

        int laskuri = 0;

        for (int h = 0; h < valiaikaisetTavut.length; h++) {
            for (int k = 0; k < valiaikaisetTavut[h].length; k++) {
                luettavatTavut[laskuri] = valiaikaisetTavut[h][k];
                laskuri++;
            }
        }



        return luettavatTavut;
    }
}

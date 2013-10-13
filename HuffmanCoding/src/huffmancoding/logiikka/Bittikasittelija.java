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
     * Muuntaa numeromuotoiset tavut oikeiksi Javan tavuiksi (v‰lill‰ -127-128).
     *
     * @param numerotavut Numerotavut, jotka muutetaan byte-muotoon.
     * @return Palauttaa numerotavuja vastaavat byte-tavut.
     */
    public byte[] muunnaOikeiksiTavuiksi(boolean[][] tavut, int osoitin) {
        int tavumaara = osoitin + 1;
        byte[] tavuja = new byte[tavumaara + 1024];

        int laskuri = 0;

        for (int i = 1024; i < tavuja.length; i++) {
            int muunnettuTavu = this.bitsToByte(tavut[laskuri]);
            int k = muunnettuTavu - 128;
            byte tavu = (byte) k;

            tavuja[i] = tavu;
            laskuri++;
        }
        return tavuja;
    }


    /**
     * Muodostaa annetuista numerotavuista ensin bittimuotoiset tavut ja kopioi
     * ne sitten yhteen boolean-taulukkoon lukemista varten. Taulukko vastaa
     * vanhan tiedoston bittiesityst‰.
     *
     * @param numerotavut Numerotavut, joista bittiesitys muodostetaan.
     * @return Palauttaa tiedoston bittiesityksen (true = 1, false = 0).
     */

    public boolean[] muodostaBittijonoPurkamiseen(byte[] tavuja) {

        int pituus = tavuja.length - 1024;
        boolean[] luettavatTavut = new boolean[pituus * 8];

        int tavulaskuri = 1024;
        int laskuri = 0;

        for (int i = 0; i < pituus; i++) {
            int numero = tavuja[tavulaskuri] + 128;
            boolean[] uudetTavut = this.byteToBits(numero);

            for (int j = 0; j < uudetTavut.length; j++) {
                luettavatTavut[laskuri] = uudetTavut[j];
                laskuri++;
            }
            tavulaskuri++;
        }

        return luettavatTavut;
    }
}

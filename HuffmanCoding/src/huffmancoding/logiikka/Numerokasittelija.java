package huffmancoding.logiikka;

/**
 * Käsittelee numeroita ja muuntaa niitä tavuiksi ja toisinpäin.
 *
 * @author Eveliina
 */
public class Numerokasittelija {

    public Numerokasittelija() {
    }

    /**
     * Muuttaa Integerin tavutaulukoksi. Koodi täältä:
     * http://dzone.com/snippets/convert-int-byte-array
     *
     * @param numero Numero, josta tehdään tavutaulukko.
     */
    public byte[] intToByteArray(int numero) {
        if(numero > Integer.MAX_VALUE){
            throw new IllegalArgumentException("Luku oli liian suuri: " + numero);
        }
        byte[] taulukko = new byte[]{
            (byte) (numero >>> 24),
            (byte) (numero >>> 16),
            (byte) (numero >>> 8),
            (byte) numero
        };

        return taulukko;

    }

    /**
     * Muuttaa tavutaulukon Integeriksi. Koodi täältä:
     * http://dzone.com/snippets/convert-int-byte-array
     *
     * @param tavut Tavut, joista tehdään numero.
     */
    public int byteArrayToInt(byte[] tavut) {
        int numero = (tavut[0] << 24) + ((tavut[1] & 0xFF) << 16) + ((tavut[2] & 0xFF) << 8) + (tavut[3] & 0xFF);
        return numero;
    }

    /**
     * Muuttaa frekvenssitaulukon tavuiksi ja kirjoittaa sen tiedostoon.
     *
     * @param frekvenssit Frekvenssit, jotka lisätään tiedostoon.
     * @param tiedosto Tiedosto, johon frekvenssitaulukko kirjoitetaan.
     * @return Palauttaa tiedoston, johon on lisätty frekvenssitaulukko.
     */
    public byte[] kirjoitaFrekvenssitaulukkoTiedostoon(int[] frekvenssit, byte[] tiedosto) {
        int laskuri = 0;
        for (int i = 0; i < frekvenssit.length; i++) {

            byte[] numeroTavuina = this.intToByteArray(frekvenssit[i]);
            tiedosto = this.kopioiTavunumeroTaulukkoon(numeroTavuina, tiedosto, laskuri);

            laskuri += 4;
        }

        return tiedosto;
    }

    /**
     * Kopioi yhden tavumuotoisen numeron tiedosto.
     *
     * @param numero Tavumuotoinen numero, joka kirjoitetaan tiedostoon.
     * @param tiedosto Tiedosto, johon numero kirjoitetaan.
     * @param indeksi Indeksi, josta kirjoittaminen aloitetaan.
     * @return Palauttaa tiedoston, johon on lisätty numero.
     */
    public byte[] kopioiTavunumeroTaulukkoon(byte[] numero, byte[] tiedosto, int indeksi) {

        for (int i = 0; i < numero.length; i++) {
            tiedosto[indeksi] = numero[i];
            indeksi++;
        }


        return tiedosto;
    }

    /**
     * Muodostaa alkuperäisen frekvenssitaulukon uudestaan tiedoston tavuista.
     *
     * @param tavut Tiedoston tavut, joista frekvenssitaulukko muodostetaan.
     * @return Palauttaa frekvenssitaulukon.
     */
    public int[] muodostaFrekvenssitaulukkoUudestaan(byte[] tavut) {
        int laskuri = 0;

        int[] frekvenssit = new int[256];

        for (int i = 0; i < 256; i++) {
            byte[] numero = this.poimiNumeroTaulukosta(laskuri, tavut);
            int frekvenssi = this.byteArrayToInt(numero);
            frekvenssit[i] = frekvenssi;

            laskuri += 4;

        }
        return frekvenssit;
    }

    /**
     * Poimii yksittäisen numeron tavutaulukosta. Numeroon kuuluu neljä tavua.
     *
     * @param indeksi Indeksi, josta tavun poimiminen aloitetaan.
     * @param tavut Tavut, joista numero poimitaan.
     * @return Palauttaa numeron (4 tavua) tavutaulukkona.
     */
    public byte[] poimiNumeroTaulukosta(int indeksi, byte[] tavut) {
        byte[] numero = new byte[4];

        for (int i = 0; i < 4; i++) {
            numero[i] = tavut[indeksi];

            indeksi++;
        }
        return numero;
    }
}

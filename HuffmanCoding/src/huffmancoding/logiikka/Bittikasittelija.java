package huffmancoding.logiikka;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

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
        int roskabittienMaara = 8 - (sana.length() % 8);
        if (roskabittienMaara == 8) {
            return 0;
        }

        return roskabittienMaara;
    }

    public String lisaaRoskabititSanaan(String sana) {

        int maara = this.etsiRoskabittimaara(sana);

        for (int i = 0; i < maara; i++) {
            sana += "0";
        }

        return sana;
    }

    public boolean[] muodostaBittitaulukko(char[] merkit) {

        boolean[] bitit = new boolean[merkit.length];

        for (int i = 0; i < merkit.length; i++) {

            if (merkit[i] == '1') {
                bitit[i] = true;
            } else {
                bitit[i] = false;
            }


        }
        return bitit;

    }

    public int[] muodostaNumerotavut(boolean[][] tavut) {

        int[] numerotavut = new int[tavut.length];

        for (int i = 0; i < tavut.length; i++) {
            int numero = this.bitsToByte(tavut[i]);
            numerotavut[i] = numero;
        }

        return numerotavut;
    }

    public byte[] muunnaOikeiksiTavuiksi(int[] numerotavut) {
        byte[] tavuja = new byte[numerotavut.length];

        for (int i = 0; i < numerotavut.length; i++) {

            int k = numerotavut[i] - 128;
            byte tavu = (byte) k;

            tavuja[i] = tavu;
        }
        return tavuja;
    }

    public int[] muunnaNumerotavuiksi(byte[] tavuja) {
        int[] numerotavut = new int[tavuja.length - 1];
        for (int i = 1; i < tavuja.length; i++) {
//            System.out.println("Tavu oli " + tavuja[i]);
            int k = tavuja[i] + 128;

            numerotavut[i - 1] = k;
        }
        return numerotavut;


    }

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

    public byte[] kopioiTavutLisaaRoskabittienMaara(byte[] vanhatTavut, int roskabitit) {
        byte[] kirjoitettavatTavut = new byte[vanhatTavut.length + 1];
        kirjoitettavatTavut[0] = (byte) roskabitit;

        for (int i = 1; i < kirjoitettavatTavut.length; i++) {
            kirjoitettavatTavut[i] = vanhatTavut[i - 1];
        }
        return kirjoitettavatTavut;
    }

    public boolean[][] jaaBittitaulukkoTavuihin(boolean[] bitit) {

        boolean[][] tavut = new boolean[bitit.length / 8][8];

        int laskuri = 0;

        for (int i = 0; i < tavut.length; i++) {
            for (int j = 0; j < tavut[i].length; j++) {
                tavut[i][j] = bitit[laskuri];
                laskuri++;
            }

        }

//          for(int i = 0; i < tavut.length; i++){
//              for(int j = 0; j < tavut[i].length; j++){
//                  System.out.print(tavut[i][j] + " ");
//                
//              }
//              System.out.println("");
//          }





        return tavut;
    }

    public byte[] intToByte(int[] input) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(input.length * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(input);
        byte[] array = byteBuffer.array();
        return array;
    }

    public int[] byteToInt(byte[] input) {
        IntBuffer intBuf = ByteBuffer.wrap(input).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
        int[] array = new int[intBuf.remaining()];
        intBuf.get(array);
        return array;
    }
}

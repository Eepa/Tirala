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
        int[] numerotavut = new int[tavuja.length ];
        for (int i = 0; i < tavuja.length; i++) {
//            System.out.println("Tavu oli " + tavuja[i]);
            int k = tavuja[i] + 128;

            numerotavut[i ] = k;
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

    
        public boolean[][] jaaTavuihin(char[] bitit) {

        boolean[][] tavut = new boolean[bitit.length / 8][8];

        int laskuri = 0;

        for (int i = 0; i < tavut.length; i++) {
            for (int j = 0; j < tavut[i].length; j++) {
                
                boolean merkki;
                
                if(bitit[laskuri] == '0'){
                    merkki = false;
                } else {
                    merkki = true;
                }
                
                tavut[i][j] = merkki;
                laskuri++;
            }

        }

        return tavut;
    }


}

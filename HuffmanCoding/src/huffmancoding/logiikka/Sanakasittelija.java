package huffmancoding.logiikka;

/**
 * K�sittelee erilaisia sy�tteit� ja muokkaa niit� uusiin muotoihin tarpeen
 * mukaan.
 *
 * @author Eveliina
 */
public class Sanakasittelija {

    public Sanakasittelija() {
    }

    public String muodostaFrekvenssitString(int[] frekvenssit) {

        String sana = "";

        for (int i = 0; i < frekvenssit.length; i++) {

            if (frekvenssit[i] != 0) {
                sana += i + "*" + frekvenssit[i] + ";";
            }

        }

        return sana;
    }
}

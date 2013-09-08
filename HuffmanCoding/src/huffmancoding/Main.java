package huffmancoding;

import huffmancoding.koodaaja.HuffmanCoding;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Huffman koodauksella tuotettu tiedonpakkausohjelma.
 *
 * @author Eveliina
 */
public class Main {

    /**
     * Pääohjelma, josta ohjelman suoritus käynnistetään.
     *
     * @param args
     */
    public static void main(String[] args) {

//        Scanner lukija = new Scanner(System.in);
//                
//        // Tekstikäyttöliittymä
//        
//        HuffmanCoding pakkaaja = new HuffmanCoding(lukija);
//        pakkaaja.kaynnista();

        File file = new File("testi.txt");

        ByteArrayOutputStream byteAOutStream;

        try {
            System.out.println(file.length());
            byteAOutStream = new ByteArrayOutputStream((int) file.length());
            FileInputStream fileIStream = new FileInputStream(file);
//            byte[] bufferi = new byte[(int) file.length()];
//            for (int lueNro; (lueNro = fileIStream.read(bufferi)) != -1;) {
//                byteAOutStream.write(bufferi, 0, lueNro);
//                System.out.println("lue " + lueNro + " tavuja");
//            }


        } catch (Exception e) {
            System.out.println("ERRORI!! " + e.getMessage());
            return;
        }

        byte[] tavut = byteAOutStream.toByteArray();

    }
}

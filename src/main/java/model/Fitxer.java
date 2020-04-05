package model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.ArrayList;

public class Fitxer {

    File fitxer,carpeta;
    FileWriter fwriter;
    BufferedWriter buffWriter;
    FileReader freader;
    BufferedReader buffReader;

    public ArrayList<String> llegirFitxer(File fitxerTmp) {
        ArrayList<String> contingutFitxer = new ArrayList<>();
        try {
            freader = new FileReader(fitxerTmp);
            buffReader = new BufferedReader(freader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String currentLine="";
        while (true) {
            try {
                if (!((currentLine = buffReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            contingutFitxer.add(currentLine);
            System.out.println(currentLine);
        }

        try {
            buffReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return contingutFitxer;
    }

    public void escriureAFitxer(ArrayList<String> coses, String nomFitxer) {
        fitxer = new File("dades" + File.separator + nomFitxer+".txt");
        try {
            fwriter = new FileWriter(fitxer,false);
            buffWriter = new BufferedWriter(fwriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (String cosa: coses) {
                buffWriter.write(cosa+System.getProperty("line.separator"));
            }
            buffWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File crearFitxer(String nomFitxer) {
        System.out.println(nomFitxer);
        fitxer = new File("dades" + File.separator + nomFitxer+".txt");
        if (fitxer.exists()) {
            System.out.println("El fitxer ja existeix");
        }else{
            try {
                if(fitxer.createNewFile()) {
                    System.out.println("Fitxer creat correctament");
                }else{
                    System.out.println("Error al crear el fitxer...");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el fitxer: "+e);
            }
        }
        return  fitxer;
    }

    public void creaDirectori() {
        carpeta = new File("dades");
        if (!carpeta.exists()) {
            carpeta.mkdir();
            System.out.println("Carpeta creada correctament");
        } else {
            System.err.println("Error al crear el directori, potser ja estava creat..");
        }
    }
}

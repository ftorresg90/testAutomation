package org.example.Utils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Utils {

    public static void generateTextPlain(List<String> nombres, List<String> precios, List<String> links){
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        try
        {
            String filename= "target/file.txt";
            FileWriter fw = new FileWriter(filename,false);
            //appends the string to the file

            for(int i=0; i<nombres.size();i++){
                fw.write("Nombre Producto "+i+": " + nombres.get(i)+"\n");
                fw.write("Precio Producto"+i+": " + precios.get(i)+"\n");
                fw.write("Link   Producto"+i+": " + links.get(i)+"\n");
                fw.write("\n");
                fw.write("\n");
            }
            fw.close();
            BufferedReader br = new BufferedReader(new FileReader("target/file.txt"));
            //read the file content
            while (strLine != null)
            {
                sb.append(strLine);
                sb.append(System.lineSeparator());
                strLine = br.readLine();
                System.out.println(strLine);
            }
            br.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

}

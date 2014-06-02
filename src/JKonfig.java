package jkonfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public abstract class JKonfig {
    
    private static boolean initialized = false;
    static String kfgtitle = "";
    static File konfigFile;
    public static Map<String, String> dataMap = new TreeMap<>();
    
    private static void init(){
        if(initialized)
            return;
        
        File tmp = new File("C:/JKonfig/konfigs");
        tmp.mkdirs();
        initialized = true;
    }
    
    public static void loadKonfig(String title){
        init();
        kfgtitle = title;
        konfigFile = new File("C:/JKonfig/konfigs/"+title+".kfg");
        if(konfigFile.exists())
            readData(konfigFile);
        else
            try {
                konfigFile.createNewFile();
        } catch (IOException ex) {}
    }
    
    public static void saveKonfig(){
        try {
            PrintWriter pw = new PrintWriter(konfigFile);
            Object[] keys = dataMap.keySet().toArray();
            Object[] vals = dataMap.values().toArray();
            for(int i = 0 ; i < keys.length;i++){
                pw.println((String)keys[i]);
                pw.println((String)vals[i]);
            }
            pw.close();
        } catch (FileNotFoundException ex) {}
    }

    private static void readData(File f) {
        try {
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                dataMap.put(s.nextLine(), s.nextLine());
            }
        } catch (Exception ex) {}
    }
    
    public static void set(String index, String val){
        dataMap.put(index, val);
    }
    
    public static String get(String index){
        return dataMap.get(index);
    }
}

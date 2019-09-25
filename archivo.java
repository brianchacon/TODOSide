import java.io.*;

public class archivo{

    String proyectName ="";
    Tarjeta [] l = null;
    int indL = -1;
    String defaultFile = "";
    String getLastFile(){
        String exists = null;
        try{
            FileReader fr = new FileReader("findme");
            BufferedReader entrada = new BufferedReader(fr);
            defaultFile = entrada.readLine();
            exists = defaultFile;
            entrada.close();
        }
        catch(IOException iox){
          System.out.println(iox.getMessage());// return;
        } 
        return exists;
    }
    void saveLastFile(){
        try{
            File f = new File("findme");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            salida.println(defaultFile); 
            salida.close();
        }
        catch(IOException iox){
            System.out.println(iox.getMessage());// return;
        }
    }
    void escritor(String dire){
        if(l !=null)
            escrito(dire);
        else
           System.out.println("Lista l sin inicializar"); 
    }  
    void escrito(String dire){
        try{
            File f = new File(dire);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            //fffffffffffffffffffff          
            salida.println(proyectName);  
            salida.println(l.length);
            salida.println(indL);
            for(int i =0;i<l.length;i++){
                if(l[i]!= null){
                    salida.println(l[i].texto);
                    salida.println(l[i].comp);
                    salida.println(l[i].impor);    
                }         
            }
            //fffffffffffffffffffff
            salida.close();
            // Modo append bw = new BufferedWriter(new FileWriter("nuevo", true));
        }
        catch(IOException iox){
            System.out.println(iox.getMessage());// return;
        }    
    }

    void lector(String dire){
    //if(is_format(dire) ){
        try{
         
          //leer desde fichero
          //new FileReader("nuevo");

          FileReader fr = new FileReader(dire);
          BufferedReader entrada = new BufferedReader(fr);
          proyectName = entrada.readLine();
          int len = Integer.parseInt(entrada.readLine());
          indL = Integer.parseInt(entrada.readLine());
          String [] original = new String[len*3];
          l = new Tarjeta[len];
          
          for(int i=0;(original[i] = entrada.readLine()) != null;i++){}  
          entrada.close();
     
          for(int i=0,j=0;i<indL*3 && j<len;i+=3,j++){
            if(original[i]!= null && original[i+1]!= null && original[i+2]!= null){
                l[j] = new Tarjeta();
                System.out.println("o.O"+original[i] + original[i+1] +original[i+2]);
                l[j].texto = original[i];
                l[j].comp = Integer.parseInt(original[i+1]);
                l[j].impor = Integer.parseInt(original[i+2]);
            }
            defaultFile = dire;
            saveLastFile();
          }  
          
         // System.out.println(">.<"+original);
        }
        catch(IOException iox){
          System.out.println(iox.getMessage());// return;
        } 
    }
    
    boolean is_format(String d){
        boolean result = false;
        if(d.length()>4 && d.charAt(d.length()-1)== '.' && d.charAt(d.length()-2)== 't' && d.charAt(d.length()-3)== 'o' && d.charAt(d.length()-4)== 'd')
            result = true;
        return  result;
    }

}

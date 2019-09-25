import java.awt.Color;
class Formato{
    boolean negrita;
    boolean cursiva;
    boolean subrayado;
    boolean tachado;
    boolean resaltar;
    Color color;
    String fuente;
    int size; 

    public Formato(){
        negrita   = false;
        cursiva   = false;
        subrayado = false;
        tachado   = false;
        resaltar  = false;
        color     = null;
        fuente    = null;
        size      = 0; 
    }

    public Formato(boolean n,boolean c,boolean s,boolean t,boolean r,Color co,String f,int si){
        negrita   = n;
        cursiva   = c;
        subrayado = s;
        tachado   = t;
        resaltar  = r;
        color     = co;
        fuente    = f;
        size      = si; 
    }
}

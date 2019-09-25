class Tarjeta{
    String texto;
    int comp;
    int impor;
    int posX;
    int posY;
    public Tarjeta(){
        texto = null;
        comp  = 0;
        impor = -1;
        posX = -1;
        posY = -1;      
    }
    
    public Tarjeta(String text,int complex,int importancia){
        texto = text;
        comp  = complex;
        impor = importancia;
        posX = -1;
        posY = -1;      
    }
    
    public Tarjeta(String text,int complex,int importancia, int x, int y){
        texto = text;
        comp  = complex;
        impor = importancia;
        posX = x;
        posY = y;      
    }
}

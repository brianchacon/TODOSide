import java.awt.Color;
import java.awt.Font;
class Estilo{
//  ######### INICIO DE TAMAÑOS  #########
    int width = 400;
    int height = 800;
// ++ LA BARRA SE PONE AUTOMATICAMENTE, ESTO ES POR SI HAGO UNA PROPIA++
// ++ en otro caso IGNORAR ++    
    int widthBar = width;//%100
    int heightBar = height/32;//la mitad del menu de iconado
    
    int widthMenu = width;//%100
    int heightMenu = height/14 ;

    int  widthBarInf = width;
    int  heightBarInf = 0;
    
    int widthLienzo = width;
    //TODO: el alto de la barra no esta garantizado si no es de tamaño propio, puede qdar mal ajustado
    int heightLienzo = height - heightBar - heightMenu - heightBarInf;
    int widthIcon =45;
    int heightIcon =45;
    
    int altoTarjeta = 50;
    int anchoTarjeta = 360;
//  ######### FIN DE TAMAÑOS  #########

//  ######### INICIO DE MARGENES  #########   
    int marginWindowsX = 0;//respecto a la pantalla general 
    int marginWindowsY = 10;
    
    int marginMenuX = 0;
    int marginMenuY = 0;

    int marginLienzoX = 0;
    int marginLienzoY = heightMenu+2;
    
    int marginIconTop = 5;
    int marginIconLeft = 5;
    
    int marginLeftCard = 10;
    int marginTopCard = 10; 
//  ######### FIN DE MARGENES  #########

//  ######### INICIO DE COLORES  #########    
    Color background = new Color(198,198,198);
    Color colorBar = new Color(255,255,255);
     Color colorBarItem = new Color(255,255,255);
      Color colorMenuBarItem = new Color(255,255,255);
    Color colorMenu = new Color(60,59,55);
    Color colorLienzo = new Color(60,59,55);//Color(20,198,100);
    Color colorBarInf = new Color(255,255,255);
//  ######### FIN DE COLORES  #########

//  #########TODO: INICIO DE FORMATO TEXTO  #########    
    int sizeFont = 20;
    Font tituloFont = new Font( "Tahoma", Font.BOLD, sizeFont*2 ) ;
    String tituloFontFlia = "Tahoma";
    Font textoFont = new Font( "Tahoma", Font.BOLD, sizeFont ) ;//Default
    String textoFontStr = "Tahoma";
    Font textoFont2 = new Font( "Tahoma", Font.BOLD, sizeFont ) ;
    String textoFont2Str = "Tahoma";
    Font textoFont3 = new Font( "Tahoma", Font.BOLD, sizeFont ) ;
    String textoFont3Str = "Tahoma";

    Color tituloColor = Color.green ;
    Color textoColor = Color.black ;

//  ######### FIN DE FORMATO TEXTO  #########
      
    
/*JMenu meme = this.getJMenuBar().getMenu(0);
  menuBarHeight = meme.getHeight();*/  
    
//*********
//  ######### INICIO otros  #########    
    Color tarjetaColor = Color.white; 
    
    
    int margin_box_X = 5;
    int margin_box_Y = 5;
    int marginTop_text_X = 5;
    int marginTop_text_Y = 20;
    int margin_text_X = 10;
    int margin_text_Y = sizeFont;
    float margin_text_Above = 1;
    float margin_text_Below = 1;
    //Redondeo de las tarjetas:
    int arcAncho = 10;
    int arcAlto = 10;
   
//  ######### FIN otros #########
    
}

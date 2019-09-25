import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.*;
import java.awt.Component;
class  Lienzo extends JPanel{
    int width;
    int height;
    int widthBox;
    int heightBox;
    Tarjeta []lista = new Tarjeta[20];
    int indL = 0;
    int elemSelectedID = -1;//cuando un elem es seleccionado este mantiene su ind del array
    boolean do_not_draw = false;
    Estilo estilo;
    int SELECTED_IND = -1;
    mouseOps moOp =new mouseOps();
    public Lienzo(){}
    
    public Lienzo(Estilo e){
       /* if(w < wb + estilo.margin_box_X || h < hb + estilo.margin_box_Y){
            do_not_draw = true;
            System.out.println("Tamaño del panel muy pequeño para almacenar cajas");
        }    */
        estilo = e;
        width     = estilo.width;
        height    = estilo.height;
        widthBox  = estilo.anchoTarjeta;
        heightBox = estilo.altoTarjeta;
        
        setLayout(null);
        
        setBackground(estilo.colorLienzo);
        setVisible(true);
        load_lista();
        
        
        //print_list();
    }
    /*JPanel lienzo(){
        //JPanel l = new JPanel();
        return panel;
    }*/
    void handle(int xMouse, int yMouse){
        int pos = search_elem(xMouse,yMouse);
        if(pos >= 0){
            SELECTED_IND = pos;
            System.out.println("elem a sel: #"+pos+" "+lista[pos].texto);
        }
        else{
            SELECTED_IND = -1;
            System.out.println("fuera de cualquier elemento");
        }
        repaint();
    }
//+++++++++++++++++++Funciones de lista (array)+++++++++++++++     
    void print_list(){
        if(lista!=null){
            String name = "n:";
            String compx = "c:";
            String imp = "i:";
            int len=0;
            for(int i=0;i<lista.length;i++){
                if(lista[i]!=null){
                    len += lista[i].texto.length()+1;
                    name  += lista[i].texto+" ";
                    compx += lista[i].comp+offSet(lista[i].texto.length(),lista[i].comp);
                    imp   += lista[i].impor+offSet(lista[i].texto.length(),lista[i].impor);
                    
                }
                else{
                    name  += "NULL ";
                    compx += "0    ";
                    imp   += "0    ";
                    len += 5;
                }
                if(len>70){
                    System.out.println(name);
                    System.out.println(compx);
                    System.out.println(imp);
                    name = "n:";
                    compx = "c:";
                    imp = "i:";
                    len=0;
                }
            }
            System.out.println(name);
            System.out.println(compx);
            System.out.println(imp);
            System.out.println("Pos ind:"+indL);
        }
    }
    String offSet(int len,int numero){
        String result = " ";
        int dig = 0;
        for(int i=10;numero >= i;i*=10){         
                dig++;
        }          
        for(int j=len-dig-1;j>0;j--)
            result += " ";
      //  System.out.println("valor de j:"+(len-dig));
        return result;
    }
    void load_lista(){
        
        if(lista != null){
            System.out.println("elem:"+lista.length);
            indL = 0;
            update_pos2();
            //print_list();
            repaint();
        }
        else
            System.out.println(">> NO se puede carga lista, lista nula");
    }
       
    
void add_elem(Tarjeta n){
    if(n != null){   
        if(indL +1 >= lista.length){
            Tarjeta [] tmp = lista;
            lista = new Tarjeta[tmp.length +50];
            indL = 0;
            for(int i=0;i < tmp.length;i++){
                if(tmp[i]!= null){
                    lista[indL] = tmp[i];
                    indL++;
                }
            }
            //System.out.println("len:"+lista.length);    
        }
        lista[indL] = n;
        lista[indL].posX = estilo.marginLeftCard;//todos debe estar alineados
        if(indL == 0)   
            lista[indL].posY =  estilo.marginTopCard;    
        else           
            lista[indL].posY = lista[indL-1].posY + heightBox + estilo.marginTopCard;
        indL++;
        //estilo.altoTarjeta + estilo.marginTopCard;
        // System.out.println(lista[indL-1].posX +" "+ lista[indL-1].posY ); 
        // repaint();
    }       
}
   
// ubica por posicion en pantalla, divide la pos Y por el ALTO de la tarjeta y objtiene el index de la lista
// 
// marginLeftCard <= posX < estilo.anchoTarjeta + marginLeftCard
    int search_elem(int posX, int posY){
        int res = -1;
        if(lista!=null){
            boolean ends = false;
            if(posX >= estilo.marginLeftCard && posX < estilo.marginLeftCard + widthBox){
                for(int i =0;i < lista.length && !ends;i++){
                    if(lista[i]!= null && posY >= lista[i].posY && posY < lista[i].posY+ heightBox){
                        res= i;
                        ends = true;
                    }    
                }
            }
        }
        return res;    
    }
    
    void remove_elem(int pos){
        if(pos>=0 && lista != null){
            lista[pos] = null;  
        }      
    }
    void remove_and_pushDown(int pos, int newPos){
        if(pos>=0 && newPos>=0){
            remove_elem(pos);
            moveDown(pos,newPos);
            repaint();
        }
    }
     
    void move_elems(int posSel,int newPos){
        if(lista != null && lista[posSel].posX != lista[newPos].posX && lista[posSel].posY!= lista[newPos].posY){
            Tarjeta elem = lista[posSel];
            if(elem.posY < lista[newPos].posY)
                remove_and_pushDown(posSel,newPos);
            else
                remove_elem(posSel);
            moveUpAndInsert(newPos,elem);//subimos los elems en posicion dejando la "newPos" con elem como valor
        }
    }
    void moveDown(int pos, int newPos){// muevo incluida esta posicion
        if(lista != null){
            for(int i = pos;i+1< lista.length && i<newPos;i++){
                lista[i] = lista[i+1];
            }
            indL--;
            update_pos();
        }
        
    }
    void moveUpAndInsert(int pos, Tarjeta elem){//mover e insertar
        if(lista != null && pos >=0 && elem!=null){
            boolean ends = false;
            Tarjeta tmp = elem;
            Tarjeta aux = null;
            tmp.posX = estilo.marginLeftCard;
            for(int i = pos;i<lista.length && !ends && tmp != null;i+=2){
                if(lista[i] == null ){//si encontro un hueco ya no debe empujar
                    lista[i] = tmp;
                    ends = true;
                }    
                else{
                    if(i+1 >= lista.length){
                        add_elem(tmp);
                        ends = true;
                    }
                    else{
                        aux = lista[i+1];
                        lista[i+1] = lista[i];
                        lista[i]   = tmp; 
                        tmp = aux;
                    }    
                   
                }
            }
            indL++;
            update_pos();
        }    
    }
    void posicionaINDL(){
        int i=lista.length-1;
        for(;i>0 && lista[i]!=null;i--){}
        indL= i;    
    }
    void  initX(){
        for(int i=0;i<indL ;i++){
            if(lista[i] != null)
                lista[i].posX = estilo.marginLeftCard; 
        }
        if(lista[0]!=null)
            lista[0].posY = estilo.marginTopCard;
    }
    void update_pos2(){
        posicionaINDL();
        initX();
        update_pos();
    }
       
        
    void update_pos(){
        if(lista != null && indL >0){
            if(lista[0]!=null && lista[0].posY == estilo.marginTopCard){
                for(int i=1;i<indL ;i++){
                    if(lista[i] != null && lista[i].posY != lista[i-1].posY+ heightBox + estilo.marginTopCard){
                        lista[i].posY = lista[i-1].posY+ heightBox + estilo.marginTopCard;
                    }
                }
            }
            else if(lista[0]!=null){
                lista[0].posY = estilo.marginTopCard;
                update_pos();
            }
        }
         
    }
    //{pre:debe ser llamdo search antes para obtener posicion}
    void create_and_insert(int pos,String text, int complex,int importancia){
        
        Tarjeta elem = new Tarjeta(text,complex,importancia,estilo.marginLeftCard, 0);
        moveUpAndInsert(pos, elem);
    }
//+++++++++++++++++++Funciones de dibujo+++++++++++++++   
    void drawCard(Graphics g, Tarjeta card,boolean selected){     
    //Formas de tarjeta
        if(selected)
            g.setColor( Color.green);
        else
            g.setColor( Color.white);
        g.fillRoundRect(card.posX, card.posY, widthBox, heightBox,estilo.arcAncho, estilo.arcAlto);
     /*   g.setColor(convertir_a_color(card.color));
        g.fillRect(x, y, widthIdColor+overlapCards, heightBox);*/
    //Contenido de la tarjeta (Strings)
        if(selected)
            g.setColor( Color.white);
        else
            g.setColor( estilo.textoColor );
        g.setFont( estilo.textoFont );       
        g.drawString(card.texto, card.posX + estilo.marginTop_text_X, card.posY +estilo.marginTop_text_Y);
    }
    
   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //////////////////////////      
        //width 
        //height
        if(!do_not_draw && lista != null){
            int locY = estilo.marginTopCard;
            int len = lista.length;           
            for(int i = 0;i<lista.length ;i++){
                if(lista[i]!=null){//saltamos los borrados                  
                    g2.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, 
                                            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB );
                    if(i == SELECTED_IND)
                        drawCard(g, lista[i],true);
                    else
                        drawCard(g, lista[i],false);
                 // System.out.println("index:"+i+"msg:"+lista[i].texto); 
                  
                }
            }
        } 
    }
    
  class mouseOps extends MouseAdapter implements MouseListener {

    public mouseOps(){
        System.out.println("constructor");
        addMouseListener(this);
    }
        
    public void mousePressed(MouseEvent e) {
    //Componentes
        Component[] rel = getComponents();
        //posicion relativa a la ventana (offseteada para esto)
        //int offx =  rel[0].getLocationOnScreen().x;
        int x = e.getX();//MouseInfo.getPointerInfo().getLocation().x ;
        
        int y = e.getY();//MouseInfo.getPointerInfo().getLocation().y;
        // - rel[0].getLocationOnScreen().y;
        System.out.println("MouseClicked "+x+" : "+y);
        
            handle(x,y);
            /*if(SELECTED_IND == -1)
        else{
            SELECTED_IND = -1;    
        }*/
            
    }      
  }  
    
}

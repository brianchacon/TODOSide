import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;  
import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
class popUp extends JDialog/*JFrame*/ implements ActionListener{
    
    int imp  = -1;
    int comp = -1;
    String tarea = null;
    
    private JLabel lTit, lTarea, lComplex, lImport;
    private JTextField iTarea, iComplex, iImport;  
    JButton guardar,cancelar;

    int red = (int) Math.floor(Math.random()*256);//[M,N] = (N-M+1)+M) = (255-0+1)+0)
    int green = (int) Math.floor(Math.random()*256);
    int blue = (int) Math.floor(Math.random()*256);
    boolean DATES_SAVED = false;

    int marginLeft = 25;
    int widthSample = 20;
    int xSample = 240;
    int ySample = 150;
   //(230-marginLeft,110, 30,20)
   //[205,110][235,130]
   
    public popUp(int w,int h,Tarjeta tar){
        setLayout(null);
        if(tar == null)
            setTitle("Nueva tarea");
        else
            setTitle("Editar");    

        setModal(true);
        System.out.println("Colores: "+red+","+green+","+blue);
        
        if(tar == null)
            lTit = new JLabel("DESCRIPCION DE TAREA");
        else
            lTit = new JLabel("  EDITAR LA TAREA");    
        lTit.setBounds(75-marginLeft,10,170,40);
        add(lTit);
        lTarea = new JLabel("Tarea:");
        lTarea.setBounds(marginLeft,70,170,20);
        add(lTarea);
        lComplex = new JLabel("Complejidad:");
        lComplex.setBounds(marginLeft,90,170,20);
        add(lComplex);
        lImport = new JLabel("Importancia:");
        lImport.setBounds(marginLeft,110,170,20);
        add(lImport);
        
        if(tar == null)
            iTarea   = new JTextField("");
        else
            iTarea   = new JTextField(tar.texto);    
        iTarea.setBounds(170-marginLeft,70,90,20);
        add(iTarea);
        if(tar == null)
            iComplex = new JTextField("-1");
        else
            iComplex = new JTextField(""+tar.comp);    
        iComplex.setBounds(170-marginLeft,90,90,20);
        add(iComplex); 
        if(tar == null)
            iImport  = new JTextField("-1");
        else
            iImport  = new JTextField(""+tar.impor);
        iImport.setBounds(170-marginLeft,110,90,20);
        add(iImport);
        
        sampleT sample = new sampleT(red,green,blue);
        sample.setBounds(marginLeft,ySample,230,widthSample+5);
        add(sample);

        guardar =  new JButton("Guardar");
        guardar.setBounds(marginLeft,200,100,20);
        guardar.addActionListener(this);
        add(guardar);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(marginLeft+100,200,100,20);
        cancelar.addActionListener(this);
        add(cancelar);       
        
        setBounds(w/2-150,h/2-150,300,300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
       // repaint();
    }
class sampleT extends JPanel implements ChangeListener{
            JSpinner red, green, blue;
            int redc, greenc, bluec;
            int xv = 0;
            public sampleT (int r, int g, int b){
                setLayout(null);

                red = new JSpinner();
                red.setValue(r);
                red.setBounds(xv,0,50,20);
                red.addChangeListener(this);
                add(red);
                
                xv +=60;
                     
                green = new JSpinner();
                green.setValue(g);
                green.setBounds(xv,0,50,20);
                green.addChangeListener(this);
                add(green);
                
                xv +=60;
                
                blue = new JSpinner();
                blue.setValue(b);
                blue.setBounds(xv,0,50,20);
                blue.addChangeListener(this);
                add(blue); 
                
                xv +=60;
                redc   = r;
                greenc = g;
                bluec  = b;
            }
            
            @Override
            public void stateChanged(ChangeEvent e) {
                redc   = Integer.parseInt( red.getValue().toString()   );
                greenc = Integer.parseInt( green.getValue().toString() );
                bluec  = Integer.parseInt( blue.getValue().toString() );
                repaint();
                
            }
            Color genRandomColor(){   
                return (new Color(redc,greenc,bluec));
            }
           
            @Override
            public void paintComponent( Graphics g ) {
                super.paintComponent(g);
                g.setColor(genRandomColor());
                g.fillRect(xv +5, 0, widthSample, widthSample);
            }
}    


    
    
    boolean getDatos(){
        return DATES_SAVED;
    }
    
    boolean isNumberChar(char c){
        boolean result = false;
        //numeros 48-57  "," 44   "." 46
        if(c>47 && c<58 )
            result =  true;
        return result;    
    }
    boolean isPunto(char c){
        boolean result = false;
        if( c == 44 || c == 46)
            result = true;
        return result;    
    }
    boolean isNumberStr(String s,boolean is_float){
        boolean result = false;
        if(s.length()>0){   
            boolean r = true;
            boolean mutex = false;
            if(is_float)
                 mutex = true;
            for(int i=0;i<s.length() && r;i++){
                if(!isNumberChar(s.charAt(i)))
                    r = false;
                if( mutex && isPunto(s.charAt(i)) ){
                    r = true; //pisar el valor de arriba,si, lo se, es una logica mierdera :D  
                    mutex = false;
                }    
            }
            result = r;
        }
        return result;
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==guardar) {
            String s = iImport.getText();
            if(isNumberStr(s,false))
                imp = Integer.parseInt(s);
            else{
                imp = -1;
               System.out.println("!  El valor ingresado para importancia NO ES UN NúMERO");
            }    
            
            s = iComplex.getText();
            if(isNumberStr(s,false))
                comp = Integer.parseInt( s );
            else{
                comp = -1;
               System.out.println("!  El valor ingresado para complejidad NO ES UN NúMERO");
            }     
            
            tarea = iTarea.getText();;
            DATES_SAVED = true;
            this.dispose();     
        }
        if (e.getSource()==cancelar) {
            this.dispose();
        }    
  }

 
}

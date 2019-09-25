import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;  
import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
class newProyect extends JDialog implements ActionListener{
    
    String nombre = null;
    
    private JLabel lTit;
    private JTextField iNombre;  
    JButton guardar,cancelar;

    boolean DATES_SAVED = false;

    int marginLeft = 25;
    int widthSample = 20;
    int xSample = 240;
    int ySample = 150;
   
    public newProyect(int w,int h){
        setLayout(null);
        setTitle("New Proyect");
        setModal(true);
        
        lTit = new JLabel("NOMBRE DEL PROYECTO");       
        lTit.setBounds(75-marginLeft,10,170,40);
        add(lTit);      

        iNombre   = new JTextField("");  
        iNombre .setBounds(170-marginLeft,70,90,20);
        add(iNombre );

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
    } 
    
    boolean getDatos(){
        return DATES_SAVED;
    }   
     
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==guardar) {
            nombre = iNombre.getText();
            DATES_SAVED = true;
            this.dispose();     
        }
        if (e.getSource()==cancelar) {
            this.dispose();
        }    
    }
}

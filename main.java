import java.awt.EventQueue;
public class main{

    public static void main(String[] ar) {
        EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
            Ventana ventana1=new Ventana();
            //ventana1.setBounds(10,10,600,400);
           // ventana1.setVisible(true);
           
          }
        });
       
    }
}

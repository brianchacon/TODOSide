import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.awt.MouseInfo;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Dimension;
class Ventana extends JFrame implements ActionListener{
    
    boolean CAMBIOS_SIN_GUARDAR = false;
    boolean  CANT_WRITE = false;
    Estilo estilo = new Estilo();
    //EstiloDark  estilo = new EstiloDark();
    //EstiloLight  estilo = new EstiloLight();

    Menu menu     = new Menu(estilo);//(anchoMenu, altoMenu);
    Lienzo lienzo = new Lienzo(estilo);// (anchoLienzo, altoLienzo);
    String PATH_FILE = null;
    //String nombre = null;
    //ParStr [] recientes = null;
    String proyectName = "";
    
    int menuBarHeight;
    
    //mouseOps moOp =new mouseOps();
    
   JScrollPane scroll = new JScrollPane(lienzo);
   /*JPanel canvas = new JPanel();
   //canvas.setBounds(10,0,e.widthLienzo-e.marginLienzoX,e.heightLienzo*2);
        canvas.setPreferredSize(new Dimension(e.widthLienzo-e.marginLienzoX, e.heightLienzo*2));//alto = lista.length * (heightBox+marginTopBox)
        canvas.setBackground(e.colorLienzo);
   */
    public Ventana(){
        //lienzo.setBounds(estilo.marginLienzoX, estilo.marginLienzoY, estilo.widthLienzo, estilo.heightLienzo);
        lienzo.setPreferredSize(new Dimension(estilo.widthLienzo-estilo.marginLienzoX, estilo.heightLienzo*2));
        scroll.setBounds(estilo.marginLienzoX, estilo.marginLienzoY, estilo.widthLienzo, estilo.heightLienzo);
        archivo a = new archivo();
        String def = a.getLastFile();
        if(def!=null){
            PATH_FILE = def;
            load();
        }
        else//lastFile used no exists or it has not been saved correctly its name 
            CANT_WRITE = true;
        
        setLayout(null);
        setJMenuBar(menu.barraMenu());
        add(menu.menuOpciones());
        add(scroll);
        JMenu meme = this.getJMenuBar().getMenu(0);
        menuBarHeight = meme.getHeight() + estilo.heightMenu;
        //add(lienzo);lienzo.repaint();
       // scroll = new JScrollPane(lienzo.lienzo());
        //add(scroll);
        setIconImage(menu.icon);
        actionSettings();
        
        /*archivo a = new archivo();
        recientes = a.load_recientes();
        System.out.println("reciente inicial: "+recientes[recientes.length-1].clave);
        */
        /*if(recientes != null){
            ParStr actual = recientes[recientes.length-1];
            proyectName = actual.clave;       
            System.out.println("direcion de actual: "+actual.valor);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           	                                                                                                                                                                                                                                                                                                                            
            list = a.loadFormatedString(actual.valor);//cargamos el last de la lista 
        }    
        else
            list = a.loadFormatedString(null);

        */
        setBounds(estilo.marginWindowsX,estilo.marginWindowsY,estilo.width,estilo.height);
        setVisible(true);
        setTitle(proyectName);
        setBackground(estilo.background);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setResizable(false);   

        
    }
    void actionSettings(){ 
        menu.nuevo.addActionListener(this);
        menu.abrir.addActionListener(this);
        menu.guardar.addActionListener(this);       
        menu.guardarComo.addActionListener(this);
       
        menu.botAdd.addActionListener(this);
        menu.botRemove.addActionListener(this);
        menu.botEdit.addActionListener(this);
        menu.botDuplicar.addActionListener(this);               
    }

/*
    void newReciente(String PATH_FILE){
        if(recientes == null){
            recientes = new ParStr[1];
            recientes[0] = new ParStr();
            recientes[0].valor = PATH_FILE;  
        }
        else{
            ParStr [] tmp = recientes;
            recientes = new ParStr[tmp.length+1];
            for(int i=0;i<tmp.length;i++)
                recientes[i] = tmp[i];
            recientes[tmp.length] = new ParStr();    
            recientes[tmp.length].valor = proyectName;    
            recientes[tmp.length].valor = PATH_FILE;   
        }
    }
    
*/

    private void guardar(){
        if(PATH_FILE != null){

            System.out.println("Selected file: " + PATH_FILE);
            if(lienzo.lista == null)
                System.out.println("Pre; llamda nula");
            archivo a = new archivo();
            a.proyectName = proyectName;
            a.l = lienzo.lista;
            a.indL = lienzo.indL;
            a.escritor(PATH_FILE);
            //TODO a.saveRecientes(recientes);
            CAMBIOS_SIN_GUARDAR = false;
        }        
    }
    private void guardarArchivoNuevo(){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);
           
            if (result == JFileChooser.APPROVE_OPTION /*&& PATH_FILE == null*/) {
                File selectedFile = fileChooser.getSelectedFile();
                PATH_FILE = selectedFile.getAbsolutePath();
                guardar();
                //TODO CAMBIOS_SIN_GUARDAR = false;//nuevo, q comenzo a ser editado.
            }    
    }
    void load(){
    System.out.println("Selected file: " + PATH_FILE);
        archivo arch = new archivo();
        arch.lector(PATH_FILE);
        lienzo.lista = arch.l;
        proyectName = arch.proyectName;
        lienzo.indL = arch.indL;
        lienzo.load_lista();
        setTitle(proyectName);
    }


    void cursorDefault(){
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    public void actionPerformed(ActionEvent e) {
      System.out.println("Cambios."+CAMBIOS_SIN_GUARDAR);
        if (e.getSource()==menu.nuevo ){
            if(CAMBIOS_SIN_GUARDAR){
                int eleccion = JOptionPane.showConfirmDialog (null, "Guardar datos antes de salir?","Warning",JOptionPane.YES_NO_OPTION);
                if(eleccion == JOptionPane.YES_OPTION){
                    if(PATH_FILE == null)
                         guardarArchivoNuevo(); 
                    else
                         guardar();  
                }
            }
            newProyect p = new newProyect(estilo.width,estilo.height);
            if(p.getDatos()){
                proyectName = p.nombre;
                setTitle(proyectName);
                lienzo.lista= new Tarjeta[20];
                lienzo.indL = 0;
                lienzo.SELECTED_IND = -1;
                lienzo.print_list();
                PATH_FILE = null;
                System.out.println("HA sido presionado: nuevo");
                lienzo.repaint();
            }
        }    
        if (e.getSource()==menu.abrir ){
            
            if(CAMBIOS_SIN_GUARDAR){
                int eleccion = JOptionPane.showConfirmDialog (null, "Guardar datos antes de salir?","Warning",JOptionPane.YES_NO_OPTION);
                if(eleccion == JOptionPane.YES_OPTION){
                    if(PATH_FILE == null)
                         guardarArchivoNuevo(); 
                    else
                         guardar();  
                }
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                PATH_FILE = selectedFile.getAbsolutePath();
                
                load();
            }             
        }
        if (e.getSource()==menu.guardar){
            if(PATH_FILE == null)
                guardarArchivoNuevo();
            else
                guardar();    
        }
        if (e.getSource()==menu.guardarComo){
            guardarArchivoNuevo();  
        }
        
        if (e.getSource()==menu.botAdd && !CANT_WRITE){
            popUp p = new popUp(estilo.width,estilo.height,null);
            if(p.getDatos()){
                String task = p.tarea;
                int importan  = p.imp;
                int complex = p.comp;
                System.out.println(">> Nueva tarea: "+task+":"+complex+":"+importan);
                lienzo.add_elem(new Tarjeta(task,importan,complex));
                lienzo.SELECTED_IND = lienzo.indL-1;
                lienzo.repaint(); 
                CAMBIOS_SIN_GUARDAR = true;
            }
            
            System.out.println("HA sido presionado: add");
        }
        if(lienzo.SELECTED_IND >=0){
            
            if (e.getSource()==menu.botRemove){
                System.out.println("HA sido presionado: rem");
                lienzo.remove_and_pushDown( lienzo.SELECTED_IND, lienzo.lista.length);
                lienzo.SELECTED_IND = -1;
                CAMBIOS_SIN_GUARDAR = true;
            }
            if (e.getSource()==menu.botEdit){
                //Tarjeta tar = lienzo.lista[lienzo.SELECTED_IND];
                popUp p = new popUp(estilo.width,estilo.height, lienzo.lista[lienzo.SELECTED_IND]);
                if(p.getDatos()){
                    lienzo.lista[lienzo.SELECTED_IND].texto = p.tarea;
                    lienzo.lista[lienzo.SELECTED_IND].impor = p.imp;
                    lienzo.lista[lienzo.SELECTED_IND].comp  = p.comp;
                    System.out.println(">> Modif: "+p.tarea+":"+p.imp+":"+p.comp);
                    lienzo.repaint(); 
                    CAMBIOS_SIN_GUARDAR = true;
                }
                System.out.println("HA sido presionado: Edit");
            }
            if (e.getSource()==menu.botDuplicar){
                
                
                    System.out.println("HA sido presionado: Dup");
                    Tarjeta doble = new Tarjeta();
                    doble.texto = lienzo.lista[lienzo.SELECTED_IND].texto +" doble";
                    doble.comp = lienzo.lista[lienzo.SELECTED_IND].comp ;
                    doble.impor = lienzo.lista[lienzo.SELECTED_IND].impor ;
                    
                    lienzo.moveUpAndInsert(lienzo.SELECTED_IND, doble);
                   // lienzo.lista[lienzo.SELECTED_IND] = doble;
                    lienzo.repaint();
                    lienzo.SELECTED_IND++;//posiciona en el elem original
                    CAMBIOS_SIN_GUARDAR = true;
                
                
            }
        }
    }
    //TODO este debe pedir el color de un popUp
    Color selectedColor(){
        return (new Color(255,30,30));
    }
    


}

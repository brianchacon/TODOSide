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
class Menu implements ActionListener{
//TODO elegir botones
//TODO implementar botones
//TODO dar funcionalidades basicas 
    JMenuBar barra; 
    JMenu   file, edit, format;
    JMenuItem nuevo, abrir, guardar,guardarComo;
    JButton botAdd, botRemove, botEdit, botDuplicar;//,botSubir,botBajar;
//    JTextField search;
    JPanel panelMenu= new JPanel();
    Image icon ;
    public Menu(Estilo e){
        //Barra
        barra = new JMenuBar(); 
        barra.setPreferredSize(new Dimension(e.widthBar,e.heightBar));
        
        file = new JMenu("File");       
        //file.setFont();
        edit = new JMenu("Edit");       
        //edit.setFont();
        format = new JMenu("Format");       
        //format.setFont();
        
        nuevo = new JMenuItem("Nuevo");
        file.add(nuevo);
        
        
        abrir = new JMenuItem("Abrir");
        abrir.addActionListener(this);
        file.add(abrir);
        
        guardar = new JMenuItem("Guardar");
        file.add(guardar);
        
        guardarComo = new JMenuItem("Guardar como..");
        file.add(guardarComo);

        barra.add(file);
        barra.add(edit);
        barra.add(format);
        //Colores de la barra y sus miembros
        barra.setBackground(e.colorBar);
        file.setBackground(e.colorBarItem);
        edit.setBackground(e.colorBarItem);
        format.setBackground(e.colorBarItem);
        nuevo.setBackground(e.colorMenuBarItem);
        abrir.setBackground(e.colorMenuBarItem);
        guardar.setBackground(e.colorMenuBarItem);
        guardarComo.setBackground(e.colorMenuBarItem);         
        //Menu iconado
        panelMenu.setLayout(null);
        panelMenu.setBounds(e.marginMenuX, e.marginMenuY, e.widthMenu, e.heightMenu);
        panelMenu.setBackground(e.colorMenu);
        //Creamos botones , , , 
        botAdd       = new JButton();
        botRemove       = new JButton();
        botEdit     = new JButton();
        botDuplicar = new JButton();
//        search         = new JTextField("Search");
        //cargar imagenes
        ImageIcon add = new ImageIcon("add.png");
        ImageIcon rem     = new ImageIcon("remove.png");
        ImageIcon edi  = new ImageIcon("editar.png");
        ImageIcon dupl       = new ImageIcon("duplicar.png");
        icon                   = new ImageIcon(getClass().getResource("LOGO.png")).getImage();
        
        //localizamos botones de menu 
        
        int pMenu = e.marginIconLeft;
        botAdd.setBounds(pMenu,e.marginIconTop,e.widthIcon, e.heightIcon);
        pMenu += e.widthIcon + e.marginIconLeft;
        botRemove.setBounds(pMenu,e.marginIconTop,e.widthIcon, e.heightIcon);
        pMenu += e.widthIcon + e.marginIconLeft;
        botEdit.setBounds(pMenu,e.marginIconTop,e.widthIcon, e.heightIcon);
        pMenu += e.widthIcon + e.marginIconLeft;
        botDuplicar.setBounds(pMenu,e.marginIconTop,e.widthIcon, e.heightIcon);  
             
        //Asignamos imagenes a botones
        botAdd.setIcon(add);
        botRemove.setIcon(rem);
        botEdit.setIcon(edi);
        botDuplicar.setIcon(dupl);
        //listener
        
        //agregamos al menu
        panelMenu.add(botAdd);
        panelMenu.add(botRemove);
        panelMenu.add(botEdit);
        panelMenu.add(botDuplicar);
    }
    JMenuBar barraMenu(){
        return barra;
    }
    JPanel menuOpciones(){
        return panelMenu;
    }
    
    public void actionPerformed(ActionEvent e) {
       
        
    }
/*    private void guardar(){
        if(direccion != null){
            System.out.println("Selected file: " + direccion);
            archivo a = new archivo();
            if(list == null)
                System.out.println("Pre; llamda nula");
            a.saveFormatedString(list, proyectName, direccion);
            a.saveRecientes(recientes);
            CAMBIOS_SIN_GUARDAR = false;
        }
    }
    
    private void guardarArchivoNuevo(){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION && direccion == null) {
                File selectedFile = fileChooser.getSelectedFile();
                direccion = selectedFile.getAbsolutePath();
                guardar();
                CAMBIOS_SIN_GUARDAR = false;
            }    
    }
*/
}


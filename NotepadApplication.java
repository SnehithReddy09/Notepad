/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import javax.swing.JFileChooser;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
//import javax.swing.JScrollPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
/**
 *
 * @author Snehith
 */
public class NotepadApplication {
    JMenu jmFile;
    JMenu jmEdit;
    JMenu jmFormat;
    JMenu jmOptions;
    JMenuBar jmb;
    JFrame jfrm;
    JLabel jlbl;
    Font f;
    JFileChooser fileOpen;
    JFileChooser fileSave;
    JTextArea text;
    JScrollPane jsp;
    String fname;
    String faddress;
    String test="";
    UndoManager u;
    NotepadApplication()
    {
        jfrm = new JFrame("NOTEPAD");
        jfrm.setSize(700, 700);

        jlbl = new JLabel("");
        jfrm.add(jlbl);

        f = jlbl.getFont();
        text=new JTextArea();
        text.setRows(50);
        text.setColumns(50);
        u=new UndoManager();
        text.getDocument().addUndoableEditListener(new UndoableEditListener(){
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                u.addEdit(e.getEdit());
            }
            
        });
        
        jsp=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setSize(10,10);
        jfrm.add(jsp);
        //jfrm.add(text);

        jmFile = new JMenu("File");
        JMenuItem jmiNew=new JMenuItem("New");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiSave = new JMenuItem("Save");
        JMenuItem jmiSaveAs = new JMenuItem("Save As");
        JMenuItem jmiExit = new JMenuItem("Exit");
        //jmFile.setMnemonic(KeyEvent.VK_F);

        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
        InputEvent.CTRL_DOWN_MASK));
        jmFile.add(jmiNew);
        jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
        InputEvent.CTRL_DOWN_MASK));
        jmFile.add(jmiOpen);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
        InputEvent.CTRL_DOWN_MASK));
        jmFile.add(jmiSave);
        jmiSave.setMnemonic(KeyEvent.VK_S);
        jmFile.add(jmiSaveAs);
        jmFile.addSeparator();
        jmFile.add(jmiExit);

        jmb = new JMenuBar();
        jmb.add(jmFile);
        jfrm.setJMenuBar(jmb);

        jmEdit=new JMenu("Edit");
        JMenuItem jmiUndo=new JMenuItem("Undo");
        JMenuItem jmiRedo=new JMenuItem("Redo");
        JMenuItem jmiCut=new JMenuItem("Cut");
        JMenuItem jmiCopy=new JMenuItem("Copy");
        JMenuItem jmiPaste=new JMenuItem("Paste");
        JMenuItem jmiSelectAll=new JMenuItem("Select All");
        jmEdit.add(jmiUndo);
        jmiUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
        InputEvent.CTRL_DOWN_MASK));
        jmEdit.add(jmiRedo);
        jmEdit.add(jmiCut);
        jmiCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
        InputEvent.CTRL_DOWN_MASK));
        jmEdit.add(jmiCopy);
        jmiCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
        InputEvent.CTRL_DOWN_MASK));
        jmEdit.add(jmiPaste);
        jmiPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
        InputEvent.CTRL_DOWN_MASK));
        jmEdit.add(jmiSelectAll);
        jmiSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
        InputEvent.CTRL_DOWN_MASK));
        jmb.add(jmEdit);
        
        jmOptions = new JMenu("Options");
        JMenu jmiColor= new JMenu("Color");
   //     JFontchooser font=new JFontChooser();
        JMenu jmiFontSize = new JMenu("FontSize");
        JMenuItem jmi8 = new JMenuItem("8");
        JMenuItem jmi9 = new JMenuItem("9");
        JMenuItem jmi10 = new JMenuItem("10");
        JMenuItem jmi12=new JMenuItem("12");
        JMenuItem jmi14=new JMenuItem("14");
        JMenuItem jmiRedColor = new JMenuItem("Red Color");
        JMenuItem jmiGreenColor = new JMenuItem("Green Color");
        JMenuItem jmiBlueColor = new JMenuItem("Blue Color");
        JMenuItem jmiOtherColors=new JMenuItem("Other Colors");
        JMenuItem jmiBackgroundColor=new JMenuItem("Background Color");
        
        jmiFontSize.add(jmi8);
        jmiFontSize.add(jmi9);
        jmiFontSize.add(jmi10);
        jmiFontSize.add(jmi12);
        jmiFontSize.add(jmi14);
        jmOptions.add(jmiFontSize);
        jmiColor.add(jmiRedColor);
        jmiColor.add(jmiGreenColor);
        jmiColor.add(jmiBlueColor);
        jmiColor.add(jmiOtherColors);
        jmiColor.add(jmiBackgroundColor);
        jmOptions.add(jmiColor);
        jmb.add(jmOptions);
        
        JMenu jmihelp=new JMenu("Help");
        JMenuItem jmiAboutNotepad=new JMenuItem("About Notepad");
        jmihelp.add(jmiAboutNotepad);
        jmb.add(jmihelp);

        jmiOpen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change
            //openFile();
            FileDialog fileOpen=new FileDialog(jfrm,"Open",FileDialog.LOAD);
            fileOpen.setVisible(true);
            if(fileOpen.getFile()!=null)
            {
                fname=fileOpen.getFile();
                faddress=fileOpen.getDirectory();
                jfrm.setTitle(fname);
                text.setText("");
                String res="";
                try{
                    Scanner s=new Scanner(new FileReader(faddress+fname));
                    while(s.hasNext())
                    {
                        res += s.nextLine() + "\n";
                    }
                    //System.out.println(res);
                    text.append(res);
                    test=text.getText();
                }
                catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }

        });
        
        
        jmiNew.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(fname==null && test.compareTo(text.getText()) !=0)
                    {
                         int x=JOptionPane.showConfirmDialog(jfrm,"Save The File"); 
                         if(x==JOptionPane.YES_OPTION)
                         {
                            FileDialog fileSave=new FileDialog(jfrm,"Save",FileDialog.SAVE);
                            fileSave.setVisible(true);
                            if(fileSave.getFile()!=null)
                            {
                                fname=fileSave.getFile();
                                faddress=fileSave.getDirectory();
                                jfrm.setTitle(fname);
                                try {
                                    FileWriter s= new FileWriter(faddress+fname);
                                    s.write(text.getText());
                                    s.close();
                                    text.setText("");
                                    fname=null;
                                    faddress=null;
                                    test="";

                                 }
                                catch(Exception e2)
                                {
                                    System.out.println(e2.getMessage());
                                }
                            }
                        }
                         else if(x==JOptionPane.NO_OPTION)
                         {
                             text.setText("");
                             fname=null;
                             faddress=null;
                             test="";
                         }
                    }
                    else
                    {
                        if( test.compareTo(text.getText()) !=0){
                        int y=JOptionPane.showConfirmDialog(jfrm,"Save Changes");
                        if(y==JOptionPane.YES_OPTION)
                        {
                            try {
                            FileWriter s= new FileWriter(faddress+fname);
                            s.write(text.getText());
                            s.close();
                            text.setText("");
                            fname=null;
                            faddress=null;
                            test="";
                            }
                            catch(Exception e2)
                            {
                            System.out.println(e2.getMessage());
                            }
                        }
                        
                       else if(y==JOptionPane.NO_OPTION)
                        {
                            text.setText("");
                            fname=null;
                            faddress=null;
                            test="";
                        }
                        }
                        else
                        {
                            text.setText("");
                            fname=null;
                            faddress=null;
                            test="";
                        }
                    }
            }
            
            
        });

        jmiSaveAs.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            FileDialog fileSave=new FileDialog(jfrm,"Save",FileDialog.SAVE);
            fileSave.setVisible(true);
            if(fileSave.getFile()!=null)
            {
                fname=fileSave.getFile();
                faddress=fileSave.getDirectory();
                jfrm.setTitle(fname);
                try {
                    FileWriter s= new FileWriter(faddress+fname);
                    s.write(text.getText());
                    test=text.getText();
                    s.close();
                }
                catch(Exception e2)
                {
                    System.out.println(e2.getMessage());
                }
            }
            }
        });

        jmiSave.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change
            if(fname==null)
            {
                FileDialog fileSave=new FileDialog(jfrm,"Save",FileDialog.SAVE);
                fileSave.setVisible(true);
                if(fileSave.getFile()!=null)
                {
                    fname=fileSave.getFile();
                    faddress=fileSave.getDirectory();
                    jfrm.setTitle(fname);
                    try {
                        FileWriter s= new FileWriter(faddress+fname);
                        s.write(text.getText());
                        test=text.getText();
                        s.close();
                        }
                    catch(Exception e2)
                    {
                        System.out.println(e2.getMessage());
                    }
                }
            }
            else
            {
                try {
                    FileWriter s= new FileWriter(faddress+fname);
                    s.write(text.getText());
                    test=text.getText();
                    s.close();
                    }
                    catch(Exception e2)
                    {
                        System.out.println(e2.getMessage());
                    }
            }
        }
        });

        jmiExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change
            System.exit(0);
        }
        });
        
        jmiUndo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                u.undo();
            }
            
           
        }
        );

        jmiRedo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                u.redo();
            }
        }
        );
        
        jmiCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.cut();
            }
        });
        
        jmiCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.copy();
            }
        });       

        jmiPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.paste();
            }
        });
        
        jmiSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.selectAll();
            }
        });        
        
        jmiFontSize.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change
            text.setFont(new Font(text.getName(), Font.PLAIN, 30));
            }

        });
        
        jmi8.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.setFont(new Font(text.getName(), Font.PLAIN,8));
            }
            
        });        
        
        jmi9.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.setFont(new Font(text.getName(), Font.PLAIN,9));
            }
        });   
        jmi10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.setFont(new Font(text.getName(), Font.PLAIN,10));
            }
        }); 
        jmi12.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.setFont(new Font(text.getName(), Font.PLAIN,12));
            }
        }); 
        
        jmi14.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                text.setFont(new Font(text.getName(), Font.PLAIN,14));
            }
        }); 
        jmiRedColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change
            text.setForeground(Color.red);
            
        }
        });

        jmiGreenColor.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change
       // text.setForeground(Color.green);
          text.setForeground(Color.green);
        }
        });

        jmiBlueColor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change
            text.setForeground(Color.blue);
        }
        });
        
        jmiOtherColors.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);
                text.setForeground(color);
            }
            
        });
        
        jmiBackgroundColor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);
                text.setBackground(color);
            }
            
        });
        
        jmiAboutNotepad.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               String msg="Microsoft Windows \n"
                       + "Version 6.3 (Version 6.3) \n"
                       + "@2013 Microsoft Coorporation. All Rights Reserved \n"
                       + " The Windows 8.1 operating system and its user interface are protected by trademark \n"
                       + "\n"
                       + "This product is licensed under the MiCrosoft Software License";
                JOptionPane.showMessageDialog(jfrm,msg);
            }
            
        });
        
        jfrm.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent w)
            {
                if(test.compareTo(text.getText())!=0)
                {
                    if(fname==null)
                    {
                         int x=JOptionPane.showConfirmDialog(jfrm,"Do u want to Save The File?"); 
                         if(x==JOptionPane.YES_OPTION)
                         {
                            FileDialog fileSave=new FileDialog(jfrm,"Save",FileDialog.SAVE);
                            fileSave.setVisible(true);
                            if(fileSave.getFile()!=null)
                            {
                                fname=fileSave.getFile();
                                faddress=fileSave.getDirectory();
                                jfrm.setTitle(fname);
                                try {
                                    FileWriter s= new FileWriter(faddress+fname);
                                    s.write(text.getText());
                                    s.close();
                                    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                 }
                                catch(Exception e2)
                                {
                                    System.out.println(e2.getMessage());
                                }
                            }
                        }
                         else if(x==JOptionPane.NO_OPTION)
                         {
                             jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                         }
                         else
                         {
                             jfrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                         }
                    }
                    else
                    {
                        int y=JOptionPane.showConfirmDialog(jfrm,"Do u Want To Save Changes?");
                        if(y==JOptionPane.YES_OPTION)
                        {
                            try {
                            FileWriter s= new FileWriter(faddress+fname);
                            s.write(text.getText());
                            s.close();
                            jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }
                            catch(Exception e2)
                            {
                            System.out.println(e2.getMessage());
                            }
                        }
                        
                       else if(y==JOptionPane.NO_OPTION)
                        {
                            jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                        else
                       {
                           jfrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                       }
                    }
            }
            else 
            { jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            }
        });
        jfrm.setVisible(true);
     }

       public static void main(String[] args) {
        // TODO code application logic here
        new NotepadApplication();
 }
    
}




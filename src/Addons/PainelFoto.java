package Addons;

import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.geom.Rectangle2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.util.logging.Level;  
import java.util.logging.Logger;  
import javax.imageio.ImageIO;  
import javax.swing.JFileChooser;  
import javax.swing.JMenuItem;  
import javax.swing.JOptionPane;  
import javax.swing.JPanel;  
import javax.swing.JPopupMenu;  
import javax.swing.filechooser.FileFilter;  
  
  
/** 
 *   
 * @author Sekkuar  
 */    
public final class PainelFoto extends JPanel {  
  
    private BufferedImage image;  
    private File file;  
    private JPopupMenu popup;  
  
    public PainelFoto() {  
        setPopup();  
        this.addMouseListener(new Popup());  
    }  
  
    private void setPopup() {  
        popup = new JPopupMenu();  
        JMenuItem item = new JMenuItem("Escolher foto...");  
        item.addActionListener(new ActionListener() {  
  
            public void actionPerformed(ActionEvent e) {  
                escolherFoto();  
            }  
        });  
       popup.add(item);  
    }  
  
    public void escolherFoto() {  
        JFileChooser fc = new JFileChooser();          
        fc.setFileFilter(getFilter());  
        fc.setFileHidingEnabled(true);  
        int resp = fc.showDialog(this,"Selecione uma foto");  
  
        if (resp == JFileChooser.APPROVE_OPTION) {  
            file = fc.getSelectedFile();  
            if (file.isDirectory() || !file.exists()) {  
                JOptionPane.showMessageDialog(null, "Selecione uma imagem válida!",  
                        "Erro!", JOptionPane.ERROR_MESSAGE);  
                setImage((File)null);  
                return;  
            } else {  
                setImage(file);  
            }  
        } else {  
            setImage((File)null);  
        }  
    }  
  
    @Override  
    public void paintComponent(Graphics g) {  
  
        if (image != null) {  
            Image image = this.image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);  
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);  
           // Out.println((double)getWidth()/(double)getHeight());  
        } else {  
            g.setColor(Color.white);  
            g.fillRect(0, 0, getWidth(), getHeight());  
            g.setColor(Color.black);  
            Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);  
            g.setFont(f);  
  
            Rectangle2D rect1 = g.getFontMetrics().getStringBounds("Sem Foto", g);  
            Rectangle2D rect2 = g.getFontMetrics().getStringBounds("Disponível", g);  
            g.drawString("Sem Foto", (int) ((getWidth() / 2) - (rect1.getWidth() / 2)), (int) ((getHeight() / 2) - (rect1.getHeight() / 2)));  
            g.drawString("Disponível", (int) ((getWidth() / 2) - (rect2.getWidth() / 2)), (int) ((getHeight() / 2) - (rect2.getHeight() / 2) + rect1.getHeight()));  
        }  
  
    }  
  
    public File getImage() {  
        return file;  
    }  
  
    public void setImage(File f) {  
        if (f == null || !f.exists()) {  
            image = null;  
            file = null;  
            return;  
        }  
        this.file = f;  
        try {  
            this.image = ImageIO.read(f);  
  
        } catch (IOException ex) {  
            Logger.getLogger(PainelFoto.class.getName()).log(Level.SEVERE, null, ex);  
        }  
        repaint();  
    }  
      
    public void setImage(String f) {  
        if (f != null) {  
            setImage(new File(f));  
        } else {  
            setImage((File) null);  
        }  
    }  
  
    private FileFilter getFilter() {  
        FileFilter ff = new FileFilter() {  
  
            public boolean accept(File f) {  
                if (f.isDirectory()) {  
                    return true;  
                }  
  
                String extension = getExtension(f);  
                if (extension != null) {  
                    if (extension.equals(".gif")  
                            || extension.equals(".jpg")  
                            || extension.equals(".jpeg")  
                            || extension.equals(".png")) {  
                        return true;  
                    } else {  
                        return false;  
                    }  
                }  
                return false;  
            }  
  
            private String getExtension(File f) {  
                String ext = null;  
                String s = f.getName();  
                int i = s.lastIndexOf('.');  
  
                if (i > 0 && i < s.length() - 1) {  
                    ext = s.substring(i).toLowerCase();  
                }  
                return ext;  
            }  
  
            @Override  
            public String getDescription() {  
                return "Arquivos de Imagens (.gif, .jpg, .jpeg, .png)";  
            }  
        };  
  
        return ff;  
    }  
  
    class Popup extends MouseAdapter {  
  
        @Override  
        public void mousePressed(MouseEvent e) {  
            if (e.getButton() == MouseEvent.BUTTON3) {  
                popup.show(e.getComponent(),  
                        e.getX(), e.getY());  
            }  
        }  
  
        @Override  
        public void mouseReleased(MouseEvent e) {  
            if (e.getButton() == MouseEvent.BUTTON3) {  
                popup.show(e.getComponent(),  
                        e.getX(), e.getY());  
            }  
        }  
    }  
}  
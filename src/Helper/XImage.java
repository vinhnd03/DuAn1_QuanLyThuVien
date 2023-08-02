/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class XImage {
    public static Image getAppicon(){
        URL url = XImage.class.getResource("/com/edusys/icon/fpt.png");
        return new ImageIcon(url).getImage();
    }
    public static void save(File src){
        File dst = new File("logos",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdir();
        }
        try{
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public static ImageIcon read(String fileName){
        File path = new File("logos",fileName);
        return new ImageIcon(path.getAbsolutePath());  
    }
}

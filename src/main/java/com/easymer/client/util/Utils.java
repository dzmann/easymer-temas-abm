package com.easymer.client.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class Utils {

    public static ImageIcon getImagen(byte[] imageBytes) {
        byte[] btDataFile = Base64.getDecoder().decode(imageBytes);
        BufferedImage image = null;
        ImageIcon imageIcon = null;
        if(imageBytes.length == 0){
            return null;
        }
        try {
            image = ImageIO.read(new ByteArrayInputStream(btDataFile));
            imageIcon = new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return imageIcon;
    }
}

package com.easymer.client.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class Utils {

    public static ImageIcon getImagen(byte[] imageBytes) throws IOException {
        byte[] btDataFile = Base64.getDecoder().decode(imageBytes);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(btDataFile));
        ImageIcon imageIcon = new ImageIcon(image);
        return imageIcon;
    }
}

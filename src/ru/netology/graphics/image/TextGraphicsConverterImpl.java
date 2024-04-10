package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterImpl implements TextGraphicsConverter {
    private int maxWidth;
    private int maxHeight;
    private double maxRatio;
    private TextColorSchema textColorSchema;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();
        if (maxRatio != 0.0) {
            double imgRatio = (double) imgWidth / imgHeight;
            if (imgRatio > maxRatio) {
                throw new BadImageSizeException(imgRatio, maxRatio);
            }
        }
        if (maxHeight != 0) {
            if (imgHeight > maxHeight) {
                double valForDecrease = (double) imgHeight / maxHeight;
                imgHeight = (int) (imgHeight / valForDecrease);
                imgWidth = (int) (imgWidth / valForDecrease);
            }
        }
        if (maxWidth != 0) {
            if (imgWidth > maxWidth) {
                double valForDecrease = (double) imgWidth / maxWidth;
                imgHeight = (int) (imgHeight / valForDecrease);
                imgWidth = (int) (imgWidth / valForDecrease);
            }
        }
        Image scaledImage = img.getScaledInstance(imgWidth, imgHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);


        WritableRaster bwRaster = bwImg.getRaster();

        char[][] chars = new char[imgHeight][imgWidth];
        for (int h = 0; h < imgHeight; h++) {
            for (int w = 0; w < imgWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = textColorSchema.convert(color);
                chars[h][w] = c;
            }
        }

        StringBuilder imgString = new StringBuilder();

        for(int h = 0; h < imgHeight; h++){
            for(int w = 0; w < imgWidth; w++ ){
                char symbol = chars[h][w];
                imgString.append(symbol);
                imgString.append(symbol);
            }
            imgString.append("\n");
        }
        return imgString.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.textColorSchema = schema;
    }
}

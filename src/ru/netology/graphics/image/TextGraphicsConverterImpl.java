package ru.netology.graphics.image.impl;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
        int imgWeight = img.getWidth();
        if(maxRatio != 0.0){
            double imgRatio = (double) imgWeight / imgHeight;
            if(imgRatio != maxRatio){
                throw new BadImageSizeException(imgRatio, maxRatio);
            }
        }


        if(img.getHeight() <= maxHeight && img.getWidth() <= maxWidth){

        }
            // Если конвертер попросили проверять на максимально допустимое
            // соотношение сторон изображения, то вам здесь нужно сделать эту проверку,
            // и, если картинка не подходит, выбросить исключение BadImageSizeException.
            // Чтобы получить ширину картинки, вызовите img.getWidth(), высоту - img.getHeight()

            // Если конвертеру выставили максимально допустимые ширину и/или высоту,
            // вам нужно по ним и по текущим высоте и ширине вычислить новые высоту
            // и ширину.
            // Соблюдение пропорций означает, что вы должны уменьшать ширину и высоту
            // в одинаковое количество раз.
            // Пример 1: макс. допустимые 100x100, а картинка 500x200. Новый размер
            // будет 100x40 (в 5 раз меньше).
            // Пример 2: макс. допустимые 100x30, а картинка 150x15. Новый размер
            // будет 100x10 (в 1.5 раза меньше).
            // Подумайте, какими действиями можно вычислить новые размеры.
            // Не получается? Спросите вашего руководителя по курсовой, поможем.

        return null;
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

/**
 * @author Maik Basso <maik@maikbasso.com.br>
 */

package ndvi.viewer;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class NDVICalc {
    
    private final BufferedImage image;

    public NDVICalc(BufferedImage image) {
        this.image = image;
    }
    
    /*
    public float getNDVI(){
        Color pixel;
        float ndvi = 0;
        for (int w = 0; w < this.image.getWidth(); w++) {
            for (int h = 0; h < this.image.getHeight(); h++) {
                pixel = new Color(this.image.getRGB(w, h));
                int R = pixel.getRed();
                int G = pixel.getGreen();
                int B = pixel.getBlue();
                int NIR = ?; //my images don't have NIR and R channel simultaneously
                //calculate NDVI
                ndvi += (NIR - R)/(NIR + R);
            }
        }
        //calculate the average
        float average = ndvi / (this.image.getWidth() * this.image.getHeight());
        
        return average;
    }
    */
    
    private float divide(float a, float b){
        if(b == 0){
            b = (float) 0.000000001;
        }
        return a/b;
    }
    
    public float getGNDVI(){
        Color pixel;
        int W = this.image.getWidth();
        int H = this.image.getHeight();
        float gndvi = 0;
        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                pixel = new Color(this.image.getRGB(x, y));
                int NIR = pixel.getRed(); //NIR is the RED channel in raspbery camera images
                int G = pixel.getGreen();
                int B = pixel.getBlue();
                //calculate NDVI
                gndvi += this.divide((NIR - G),(NIR + G));
            }
        }
        //calculate the average
        float average = gndvi / (W * H);
        return average;
    }
    
    public float getGNDVI(int x1, int y1, int x2, int y2){
        Color pixel;
        int numberOfpixeis = 0;
        float gndvi = 0;
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                pixel = new Color(this.image.getRGB(x, y));
                int NIR = pixel.getRed(); //NIR is the RED channel in raspbery camera images
                int G = pixel.getGreen();
                int B = pixel.getBlue();
                //calculate NDVI
                gndvi += this.divide((NIR - G),(NIR + G));
                //save the number of pixes
                numberOfpixeis++;
            }
        }
        //calculate the average
        float average = gndvi / numberOfpixeis;
        return average;
    }
    
    public float getBNDVI(){
        Color pixel;
        int W = this.image.getWidth();
        int H = this.image.getHeight();
        float bndvi = 0;
        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                pixel = new Color(this.image.getRGB(x, y));
                int NIR = pixel.getRed(); //NIR is the RED channel in raspbery camera images
                int G = pixel.getGreen();
                int B = pixel.getBlue();
                //calculate NDVI
                bndvi += this.divide((NIR - B),(NIR + B));
            }
        }
        //calculate the average
        float average = bndvi / (W * H);
        return average;
    }
    
    public float getBNDVI(int x1, int y1, int x2, int y2){
        Color pixel;
        int numberOfpixeis = 0;
        float bndvi = 0;
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                pixel = new Color(this.image.getRGB(x, y));
                int NIR = pixel.getRed(); //NIR is the RED channel in raspbery camera images
                int G = pixel.getGreen();
                int B = pixel.getBlue();
                //calculate NDVI
                bndvi += this.divide((NIR - B),(NIR + B));
                //save the number of pixes
                numberOfpixeis++;
            }
        }
        //calculate the average
        float average = bndvi / numberOfpixeis;
        return average;
    }
}
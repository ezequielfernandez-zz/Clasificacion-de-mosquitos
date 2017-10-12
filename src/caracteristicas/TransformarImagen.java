/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caracteristicas;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Luis
 */
public class TransformarImagen extends BaseImagenes{
    
    public TransformarImagen(){}
    private int colorRGBaSRGB(Color colorRGB){
        int colorSRGB;
        colorSRGB=(colorRGB.getRed() << 16) | (colorRGB.getGreen() << 8) | colorRGB.getBlue();
        return colorSRGB;
    }
    private BufferedImage clonarBufferedImage(BufferedImage bufferImage){
        BufferedImage copiaImagen=new BufferedImage (bufferImage.getWidth(),bufferImage.getHeight(),bufferImage.getType());
        copiaImagen.setData(bufferImage.getData());
        return copiaImagen;
    }
    private int calcularMediaColor(Color color){
        int averageColor;
        averageColor=(int)((color.getRed()+color.getGreen()+color.getBlue())/3);
        return averageColor;
    }
    private Color chequearUmbral(Color color, int umbral){
        Color colorSalida;
        if (this.calcularMediaColor(color)>=umbral){
            colorSalida=new Color(255,255,255, color.getAlpha());
        }else{
            colorSalida=new Color(0, 0, 0, color.getAlpha());
        }
        return colorSalida;
    }
        
    public BufferedImage blancoNegro(BufferedImage imagen, int umbral){
        BufferedImage imagenRetorno=this.clonarBufferedImage(imagen);
        Color auxColor;
        for( int i = 0; i < imagenRetorno.getWidth(); i++ ){
            for( int j = 0; j < imagenRetorno.getHeight(); j++ ){
                auxColor=new Color(imagenRetorno.getRGB(i, j));
                imagenRetorno.setRGB(i, j,this.colorRGBaSRGB(this.chequearUmbral(auxColor,umbral)));
            }
        }
        super.actualizarImagen(imagenRetorno,"Transformación: la imagen se ha pasado a blanco y negro. Umbral: "
                + umbral);
        return imagenRetorno;
    }
    
    public BufferedImage escalaGrises(BufferedImage imagen){
        BufferedImage imagenRetorno=this.clonarBufferedImage(imagen);
        Color auxColor;
        int mediaColor;
        for( int i = 0; i < imagenRetorno.getWidth(); i++ ){
            for( int j = 0; j < imagenRetorno.getHeight(); j++ ){
                auxColor=new Color(imagenRetorno.getRGB(i, j));
                mediaColor=this.calcularMediaColor(auxColor);
                imagenRetorno.setRGB(i, j,this.colorRGBaSRGB(new Color(mediaColor,mediaColor,mediaColor,auxColor.getAlpha())));
            }
        }
        super.actualizarImagen(imagenRetorno,"Transformación: la imagen se ha pasado a escala de grises");
        return imagenRetorno;
    }
    
    public BufferedImage invertirImagen(BufferedImage imagen){
        BufferedImage imagenRetorno=this.clonarBufferedImage(imagen);
        Color auxColor;
        for( int i = 0; i < imagenRetorno.getWidth(); i++ ){
            for( int j = 0; j < imagenRetorno.getHeight(); j++ ){
                auxColor=new Color(imagenRetorno.getRGB(i, j));
                imagenRetorno.setRGB(i, j,this.colorRGBaSRGB(new Color(255-auxColor.getRed(), 
                        255-auxColor.getGreen(), 255-auxColor.getBlue(),auxColor.getAlpha())));
            }
        }
        super.actualizarImagen(imagenRetorno,"Transformación: la imagen se ha invertido");
        return imagenRetorno;
    }
    
    public BufferedImage filtroRojo(BufferedImage imagen){
        BufferedImage imagenRetorno=this.clonarBufferedImage(imagen);
        Color auxColor;
        int mediaColor;
        for( int i = 0; i < imagenRetorno.getWidth(); i++ ){
            for( int j = 0; j < imagenRetorno.getHeight(); j++ ){
               auxColor=new Color(imagenRetorno.getRGB(i, j));
               mediaColor=this.calcularMediaColor(auxColor);
               imagenRetorno.setRGB(i, j,this.colorRGBaSRGB(new Color(mediaColor,0,0,auxColor.getAlpha())));
            }
        }
        super.actualizarImagen(imagenRetorno,"Transformación: se ha aplicado un filtro rojo a la imagen");
        return imagenRetorno;
    }
    
    public BufferedImage filtroVerde(BufferedImage imagen){
        BufferedImage imagenRetorno=this.clonarBufferedImage(imagen);
        Color auxColor;
        int mediaColor;
        for( int i = 0; i < imagenRetorno.getWidth(); i++ ){
            for( int j = 0; j < imagenRetorno.getHeight(); j++ ){
               auxColor=new Color(imagenRetorno.getRGB(i, j));
               mediaColor=this.calcularMediaColor(auxColor);
               imagenRetorno.setRGB(i, j,this.colorRGBaSRGB(new Color(0,mediaColor,0,auxColor.getAlpha())));
            }
        }
        super.actualizarImagen(imagenRetorno,"Transformación: se ha aplicado un filtro verde a la imagen");
        return imagenRetorno;
    }
    
    public BufferedImage filtroAzul(BufferedImage imagen){
        BufferedImage imagenRetorno=this.clonarBufferedImage(imagen);
        Color auxColor;
        int mediaColor;
        for( int i = 0; i < imagenRetorno.getWidth(); i++ ){
            for( int j = 0; j < imagenRetorno.getHeight(); j++ ){
               auxColor=new Color(imagenRetorno.getRGB(i, j));
               mediaColor=this.calcularMediaColor(auxColor);
               imagenRetorno.setRGB(i, j,this.colorRGBaSRGB(new Color(0,0,mediaColor,auxColor.getAlpha())));
            }
        }
        super.actualizarImagen(imagenRetorno,"Transformación: se ha aplicado un filtro azul a la imagen");
        return imagenRetorno;
    }
}

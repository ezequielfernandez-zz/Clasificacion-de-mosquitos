package caracteristicas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Luis Marcos
 */
public class BaseImagenes {
   
    private static ArrayList<BufferedImage> todasImagenes=new ArrayList<>();
    private static ArrayList<String> infoTodasImagenes=new ArrayList<>();
    static private int contadorImagen=-1;

    /**
     * Devuelve el listado completo de todas las imágenes
     */
    public static ArrayList<BufferedImage> getTodasImagenes() {
        return todasImagenes;
    }

    /**
     * Devuelve el listado completo de toda la información de las imágenes almacenadas
     * en todasImagenes
     * @return 
     */
    public static ArrayList<String> getInfoTodasImagenes() {
        return infoTodasImagenes;
    }
    
    /**
     * Almacena una imagen junto con su información para que luego sea accesible
     * @param imagen imagen que se quiere almacenar
     * @param informacion información de la imagen almacenada
     */
    protected void actualizarImagen(BufferedImage imagen, String informacion){
        todasImagenes.add(imagen);
        infoTodasImagenes.add(informacion);
        BaseImagenes.contadorImagen+=1;
    }
    
    /**
     * Retorna la imagen anterior con respecto a la actual
     * En caso de no haber imagen posterior, retorna null
     */
    public BufferedImage deshacerImagen(){
        //Comprueba si hay imágenes anteriores a la actual
         if((BaseImagenes.contadorImagen)>0){
            BaseImagenes.contadorImagen-=1;
            return BaseImagenes.todasImagenes.get(BaseImagenes.contadorImagen);
        }else{
            return null;
        }
        
    }
    
    /**
     * Retorna la imagen posterior a la actual. 
     * En caso de no haber imagen posterior, retorna null
     * @return 
     */
    public BufferedImage rehacerImagen(){
        //Comprueba si hay imágenes posteriores a la actual
        if((BaseImagenes.contadorImagen)<BaseImagenes.todasImagenes.size()-1){
            BaseImagenes.contadorImagen+=1;
            return BaseImagenes.todasImagenes.get(BaseImagenes.contadorImagen);
        }else{
            return null;
        }
    }
    
    /**
     * Devuelve la información de la imagen actual
     */
    public String informacionImagenActual(){
        return BaseImagenes.infoTodasImagenes.get(BaseImagenes.contadorImagen);
    }
}

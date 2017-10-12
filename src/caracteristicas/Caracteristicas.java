/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caracteristicas;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;


/**
 *
 * @author ezee
 */
public class Caracteristicas{
    
MultilayerPerceptron red;
JLabel panel;

public  Caracteristicas(JLabel panel) {
    this.panel=panel;
    crearClasificador();
    //clasificar();
    //funcion(panel);
}


public void funcion(JLabel panel){
int r, g, b, x, y, i=0; 
int constante=100;
int ancho,alto,tamaño; 
int factor=1;
BufferedImage imageActual;
ColorModel color; 
TransformarImagen t=new TransformarImagen();
BufferedImage[] todasImagenes=new BufferedImage[101];
String[] infoImagenes=new String[101];
File textura=null;
boolean entro=false;
try{
    for(int img=1;img<101;img++){
        if(img<31 && !entro){
            textura = new File("aa"+img+".jpg"); 
            infoImagenes[img]="te";
            entro=true;
        }
        if(img-30<31 && !entro){
            int actual=img-30;
            textura = new File("ae"+actual+".jpg"); 
            infoImagenes[img]="te";
            entro=true;
        }
        if(img-60<101 && !entro){
            int actual=img-60;
            textura = new File("mc"+actual+".jpg"); 
            infoImagenes[img]="mc";
            entro=true;
        }/*
        if(img-50<30 && !entro){
            int actual=img-50;
            textura = new File("ae"+actual+".jpg"); 
            infoImagenes[img]="ae";
            entro=true;
        }
        if(img-79<25 && !entro){
            int actual=img-79;
            textura = new File("mc"+actual+".jpg"); 
            infoImagenes[img]="mc";
            entro=true;
        }*/
        /*if(img<11 && !entro){
            textura = new File("s"+img+".jpg"); 
            infoImagenes[img]="s";
            entro=true;
        }
        if(img-10<11 && !entro){
            int actual=img-10;
            textura = new File("l"+actual+".jpg"); 
            infoImagenes[img]="l";
            entro=true;
        }
        if(img-20<11 && !entro){
            int actual=img-20;
            textura = new File("b"+actual+".jpg"); 
            infoImagenes[img]="b";
            entro=true;
        }
        if(img-30<11 && !entro){
            int actual=img-30;
            textura = new File("bo"+actual+".jpg"); 
            infoImagenes[img]="bo";
            entro=true;
        }
        if(img-40<11 && !entro){
            int actual=img-40;
            textura = new File("d"+actual+".jpg"); 
            infoImagenes[img]="d";
            entro=true;
        }
        if(img-50<11 && !entro){
            int actual=img-50;
            textura = new File("si"+actual+".jpg"); 
            infoImagenes[img]="si";
            entro=true;
        }*/
        todasImagenes[img]=ImageIO.read(textura); 
            
        entro=false;
    }

        FileWriter fw=new FileWriter("baseImagenes.arff", false);
        BufferedWriter bw=new BufferedWriter(fw);
        System.out.println("@relation imagenes");
        //bw.newLine();
    for(int entero=0;entero<constante*1+1;entero++){
        System.out.println("@attribute p"+entero+" integer");
        //bw.newLine();
    }
    System.out.println("@attribute resultado {'te','mc'}");
    System.out.println("@data");
    //bw.newLine();

    for(int numero=1;numero<101;numero++){
            BufferedImage bfi = todasImagenes[numero]; 
            //BufferedImage bfi = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_RGB); 
            //BufferedImage bfi = t.escalaGrises(bi);
           // bfi = t.escalaGrises(bfi);

            panel.setIcon(new ImageIcon(bfi));
            Graphics2D big = bfi.createGraphics(); 
            big.drawImage(bfi, 0, 0, panel); 

            ancho = bfi.getWidth(); 
            alto = bfi.getHeight();

            factor=100;
            color = bfi.getColorModel(); 
            int cant=0;
            int a1=0;
            int a2=0;
            for(x=0; x<ancho; x++){	
                for(y=0; y<alto; y++){ 
                    if((x%factor==0 && y%factor==0) && cant<=constante){
                        r = bfi.getRGB(x,y)>> 16 & 0xff;
                        //System.out.print(r+","); 
                        g = bfi.getRGB(x,y)>> 8 & 0xff; 
                        //System.out.print(g+","); 
                        b = bfi.getRGB(x,y) & 0xff; 
                        //System.out.print(b+","); 
                        //System.out.println("Escribio: "+r+g+b);
                        i+=3; 
                        cant++;
                    //Calculamos la media de los tres canales (rojo, verde, azul)
                    int mediaPixel=(int)((r+g+b)/3);
                    System.out.print(mediaPixel+",");
                    }
                    a2++;
                    }
                a1++;
                }
            i=0;
            System.out.println("'"+infoImagenes[numero]+"'");
            //bw.newLine();
            //System.out.println("numero: "+numero+" Cant: "+cant);
            cant=0;
            }

        }
catch(Exception e){e.printStackTrace();};

}

public void crearClasificador(){
 try{
 BufferedReader reader = new BufferedReader(new FileReader("imagenes.arff"));
 Instances data = new Instances(reader);
 reader.close();
 // setting class attribute
 data.setClassIndex(data.numAttributes() - 1);
 String[] options = new String[6];
 options[0] = "-L";            // learning rate
 options[1] = "0.6";            // n° learning rate
 options[2] = "-M";            // momentum 
 options[3] = "0.2";            // n° momentum
 options[4] = "-N";            // instancias
 options[5] = "-200";            // n°
 red = new MultilayerPerceptron();         // new instance of red

 red.setOptions(options);     // set the options
 red.buildClassifier(data);   // build classifier

 }
 catch(Exception e){e.printStackTrace();}
}


public void clasificar(JLabel resultado,JLabel icono){
try{
    // load unlabeled data
     Instances unlabeled = new Instances(new BufferedReader(new FileReader("imagen.arff")));
     // set class attribute
     unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
     // create copy
     Instances labeled = new Instances(unlabeled);
     // label instances
     for (int i = 0; i < unlabeled.numInstances(); i++) {
       double clsLabel = red.classifyInstance(unlabeled.instance(i));
       labeled.instance(i).setClassValue(clsLabel);
       if(clsLabel==0.0){
           resultado.setText("<html><body>Se estima que es un mosquito Aedes, y puede <br>transmitir zika, dengue, fiebre amarilla y encefalitis </body></html>");
           ImageIcon imic=new ImageIcon("peligro.png");
           Icon icon = new ImageIcon(imic.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_DEFAULT));
           icono.setIcon(icon);
       }
       else{
           resultado.setText("<html><body>Se estima que es un mosquito comun, <br>no representa peligro</body></html>");
           ImageIcon imic=new ImageIcon("ok.png");
           Icon icon = new ImageIcon(imic.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_DEFAULT));
           icono.setIcon(icon);
           
       }
     }
     // save labeled data
     BufferedWriter writer = new BufferedWriter(new FileWriter("labeled.arff"));
     writer.write(labeled.toString());
     writer.newLine();
     writer.flush();
     writer.close();
}
catch(Exception e){e.printStackTrace();}
}

public void armar(String ruta,JLabel resultado,JLabel icono){
  
int r, g, b, x, y, i=0; 
int constante=100;
int ancho,alto,tamaño; 
int factor=1;
BufferedImage imageActual;
ColorModel color; 
TransformarImagen t=new TransformarImagen();
BufferedImage todasImagenes=null;
File textura=null;
try{
        textura = new File(ruta); 
        todasImagenes=ImageIO.read(textura); 
            
        FileWriter fw=new FileWriter("imagen.arff", false);
        BufferedWriter bw=new BufferedWriter(fw);
        bw.append("@relation imagenes");
        bw.newLine();
    for(int entero=0;entero<constante*1+1;entero++){
        bw.append("@attribute p"+entero+" integer");
        bw.newLine();
    }
    bw.append("@attribute resultado {'te','mc'}");
    bw.newLine();
    bw.append("@data");
    bw.newLine();
    String aux="";
            BufferedImage bfi = todasImagenes; 
            
            ImageIcon imic=new ImageIcon(bfi);
            Icon icon = new ImageIcon(imic.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_DEFAULT));
            panel.setIcon(icon);
            Graphics2D big = bfi.createGraphics(); 
            big.drawImage(bfi, 0, 0, panel); 

            ancho = bfi.getWidth(); 
            alto = bfi.getHeight();

            factor=10;
            color = bfi.getColorModel(); 
            int cant=0;
            int a1=0;
            int a2=0;
            for(x=0; x<ancho; x++){	
                for(y=0; y<alto; y++){ 
                    if((x%factor==0 && y%factor==0) && cant<=constante){
                        r = bfi.getRGB(x,y)>> 16 & 0xff;
                        //System.out.print(r+","); 
                        g = bfi.getRGB(x,y)>> 8 & 0xff; 
                        //System.out.print(g+","); 
                        b = bfi.getRGB(x,y) & 0xff; 
                        //System.out.print(b+","); 
                        //System.out.println("Escribio: "+r+g+b);
                        i+=3; 
                        cant++;
                    //Calculamos la media de los tres canales (rojo, verde, azul)
                    int mediaPixel=(int)((r+g+b)/3);
                    aux=aux+(mediaPixel+",");
                    bw.write((mediaPixel+","));
                    }
                    a2++;
                    }
                a1++;
                }
            i=0;
            
            
    //aux=aux.substring(0, aux.length()-1);
    //aux=aux.substring(0, aux.lastIndexOf(","));
    //bw.write(aux);
    //bw.newLine();
    bw.write("'te'");
    bw.close();
    fw.close();
}
    catch(Exception e){e.printStackTrace();};
    clasificar(resultado,icono);
    
    
}

}



    


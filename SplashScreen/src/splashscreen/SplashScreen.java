package splashscreen;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import sun.misc.FormattedFloatingDecimal;

public class SplashScreen extends RoundedPanel implements Serializable {
    private static ArrayList listeners;
    
    private File rutaFoto;//perte especificar la ruta de nuestra foto
    private Dimension dimensionFoto;//como de grande sera la foto
    
    private JLabel icono;//contenedor de la imagen
    private Border margenFoto;//margenes de nuestra imagen
    private JProgressBar barra;//barra de progreso que al terminar salta un evento
    
    private int tiempoCarga = 20;//cuento tiempo va a demorar la barra en millisegundos
    private Timer t;//bucle que tarda en finalizar los segundo que se le especifiquen
    
    
    
    
    public SplashScreen (){
        
        listeners = new ArrayList();//creamos el array 
                
        //configuramos el contenedor
        this.setLayout(new BoxLayout(this, 2));//un tipo de layout que nos permite concatenar los objetos de izq a derecha
        
        
        //creamos los objetos que iran dentro del contendor
        icono = new JLabel();
        barra = new JProgressBar();
        barra.setStringPainted(true);
        
        //configuramos el objeto icono que un un Jlabel
        rutaFoto = new File("E:\\2DAMD\\DI (Desarrollo de Interfaces)\\Actividades\\Entregas\\CUARTA ENTREGA\\Jbeans\\Splash Screen\\SplashScreen\\src\\imagen\\descarga.png");//ruta de la imagen por defecto
        icono.setSize(200, 200);//dimensiones predeterminadas de la imagen
        actualizarFoto();//metodo que ajusta la imagen
        
        //configuramos el objeto barra
        ActionListener al = new ActionListener() {//listener usado para el timer
            @Override
            public void actionPerformed(ActionEvent ae) {//lo que pasara cada vez que se cambie el valor de nuestro timer 
                
                if(barra.getValue() <100){
                    barra.setValue(barra.getValue() +1);//sumamos uno a la barra
                }else{
                    
                    t.stop();
                    triggerValorBarraEvent();//salta el trigger de nuestro evento para que 
                    
                    //System.exit(1);
                }
                
            }
        };
        t = new Timer(tiempoCarga,al);//creamos el timer y le ponemos el listener
        t.start();//iniciamos le timer
        
        
        
        //agregamos los componentes
        this.add(icono);//agregamos el icono al contenedor
        
        this.add(barra);//agregamos la barra al contendor
        
        //los hacemos visibles
        icono.setVisible(true);
        barra.setVisible(true);
        
    }
    //metodo que agrega un escuchador a nuestro objeto
    public void addEventListener(changeEventListener listener){
        listeners.add(listener);
    }
    //este es el metodo que permite accionar el evento, es decir donde pongamos este metodo se va a producir nuestro evento
    private int triggerValorBarraEvent(){
        ListIterator  li = listeners.listIterator();//cogemos el escuchador del array
        while(li.hasNext()){
            changeEventListener listener = (changeEventListener) li.next();
            changeEvent readerEvObj = new changeEvent(this,this);//creamos un evento correspondiente
            return listener.onValorBarraChange(readerEvObj);//ejecutamos el metodo del listener
        }
        return 1;
    }
    
    
    public void actualizarFoto(){
        ImageIcon imagen = new ImageIcon(rutaFoto.getAbsolutePath());//objeto que permite poner una imagen en un JLabel
        Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_DEFAULT));//escalamos la imagen
        icono.setBorder(margenFoto);//aplicamos los bordes en caso de que los halla
        icono.setIcon(icon);//ponemos la foto
        
        
    }

    public File getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(File rutaFoto) {
        this.rutaFoto = rutaFoto;
        actualizarFoto();
    }

    public Dimension getDimensionFoto() {
        return dimensionFoto;
    }
    //permite ajustar el tamaño de la foto
    public void setDimensionFoto(Dimension dimensionFoto) {
        this.dimensionFoto = dimensionFoto;
        icono.setSize(dimensionFoto);
        actualizarFoto();
    }

    public int getTiempoCarga() {
        return tiempoCarga;
    }
    //ajusta el timpo de carga
    public void setTiempoCarga(int tiempoCarga) {
        this.tiempoCarga = tiempoCarga;
        t.setDelay(this.tiempoCarga);
        
        
    }
    public int valorBarraActual(){
        return barra.getValue();
    }

    public Border getMargenFoto() {
        return margenFoto;
    }

    public void setMargenFoto(Border margenFoto) {
        this.margenFoto = margenFoto;
        actualizarFoto();
    }

   
    
    
    
    
    
    
    
    
    
    
}

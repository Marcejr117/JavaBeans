/*
se trata de un JBean que permite crear un JPanel con los bordes redondeados 
para usarlo debemos hacer lo siguiente: 
1. agregar este bean a la paleta
2. ajustar los atributos: 
    - opacidad: ajusta la transparencia
    - radioEsquinas: ajusta el angulo de las esquinas
    - bgcolor: cambia el color de fondo  'no usar el BackGround color'
 */
package roundedpanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class RoundedPanel extends JPanel
    {
    
        private int opacidad =255;
        public Color backgroundColor = new Color(0,0,0,0);
        public int radioEsquinas = 15;
        
        
        
        public RoundedPanel(){
            
        }

        @Override
        public void setBackground(Color color) {
            
            super.setBackground(new Color(0, 0, 0, 0)); //To change body of generated methods, choose Tools | Templates.
        }
        
        
        public int getOpacidad() {
            return opacidad;
        }

        public void setOpacidad(int opacidad) {
            this.opacidad = opacidad;
        }
        
        

        public RoundedPanel(LayoutManager layout, int radioEsquinas) {
            super(layout);
            radioEsquinas = radioEsquinas;
        }

        public RoundedPanel(LayoutManager layout, int radioEsquinas, Color bgColor) {
            super(layout);
            radioEsquinas = radioEsquinas;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radioEsquinas) {
            super();
            radioEsquinas = radioEsquinas;
        }

        public RoundedPanel(int radioEsquinas, Color bgColor) {
            super();
            radioEsquinas = radioEsquinas;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(radioEsquinas, radioEsquinas);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //pinta el panel cno el borde dando color de fondo al interior junto con la opacidad
            if( opacidad>=0 && opacidad <=255){
                if (backgroundColor != null) {
                    graphics.setColor(new Color(backgroundColor.getRed(),backgroundColor.getGreen(),backgroundColor.getBlue(),opacidad));//permite tener opacidad
                } else {
                    graphics.setColor(getBackground());
                }  
            }else{
                JOptionPane.showMessageDialog(this,"la opacidad debe estar entre 0 y 255");
            }
            
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //pinta el borde con las esquinas redondeadas
            
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //crea las esquinas redondeadas
            
            
        }
        
        public void setradioEsquinas(int radioEsquinas){
            this.radioEsquinas = radioEsquinas;
        }
        
        public void setBgColor(Color bgColor) {
            this.backgroundColor = bgColor;
        }
    }

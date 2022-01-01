/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package splashscreen;

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
    
        private int opacidad =0;
        public Color backgroundColor = new Color(0,0,0,0);
        public int cornerRadius = 15;
        
        
        
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
        
        

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
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
        
        public void setCornerRadius(int cornerRadius){
            this.cornerRadius = cornerRadius;
        }
        
        public void setBgColor(Color bgColor) {
            this.backgroundColor = bgColor;
        }
    }

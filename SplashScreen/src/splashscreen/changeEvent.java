/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splashscreen;

import java.util.EventObject;

/**
 *
 * @author Marce
 */
public class changeEvent extends EventObject{
    SplashScreen aux;
    
    public changeEvent(Object o,SplashScreen aux) {
        super(o);
        this.aux = aux;
    }
    
}

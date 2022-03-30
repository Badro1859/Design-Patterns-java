/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.core.launcher;

import java.beans.PropertyChangeEvent;

import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * @author tina
 */
public class CompteARebour implements TimerChangeListener{

    private TimerService timer;
    private int compteur;

    public CompteARebour(int nbrSeconde) {
        timer = Lookup.getInstance().getService(TimerService.class);
        timer.addTimeChangeListener(this, TimerChangeListener.SECONDE_PROP);
        compteur = nbrSeconde;
    }


    @Override
    public void propertyChange(String propertyName, Object oldValue, Object newValue) {
        if (propertyName == TimerChangeListener.SECONDE_PROP) {
            if (--compteur != 0)
                System.out.println("Compteur : " + compteur);
            else {
                System.out.println("Fin");
                timer.removeTimeChangeListener(this, TimerChangeListener.SECONDE_PROP);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == TimerChangeListener.SECONDE_PROP) {
            if (--compteur != 0)
                System.out.println("Compteur : " + compteur);
            else {
                System.out.println("Fin");
                timer.removeTimeChangeListener(this, TimerChangeListener.SECONDE_PROP);
            }
        }
    }
}

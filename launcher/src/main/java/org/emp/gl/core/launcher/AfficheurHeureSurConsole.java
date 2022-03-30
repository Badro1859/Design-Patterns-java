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
 *
 * @author MADANI BADER EDDINE RSI-B-
 */
public class AfficheurHeureSurConsole implements TimerChangeListener {

    private TimerService timer;

    public AfficheurHeureSurConsole() {
        timer =  Lookup.getInstance().getService(TimerService.class);
        timer.addTimeChangeListener(this);
    }


    @Override
    public void propertyChange(String propertyName, Object oldValue, Object newValue) {
        if (propertyName == TimerChangeListener.SECONDE_PROP)
            System.out.println(timer.getHeures() + ":" + timer.getMinutes() + ":" + timer.getSecondes());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

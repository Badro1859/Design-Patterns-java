/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import lombok.experimental.Delegate;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeSupport;

/**
 * @author Bader Eddine
 */

public class DummyTimeServiceImpl
        extends TimerTask
        implements TimerService {

    private int dixiemeDeSeconde;
    private int minutes;
    private int secondes;
    private int heures;

    @Delegate
    private PropertyChangeSupport support = new PropertyChangeSupport(this);


    /**
     * Constructeur du DummyTimeServiceImpl Ici, nnous avons hérité de la classe
     * TimerTask, et nous nous avons utilisé un objet Timer, qui permet de
     * réaliser des tocs à chaque N millisecondes
     */
    public DummyTimeServiceImpl() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 100, 100);
        setTimeValues();
    }

    @Override
    public void run() {
        timeChanged();
    }

    private void timeChanged() {
        setTimeValues();
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }


    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;

        // informer les listeners !
        support.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,oldValue,dixiemeDeSeconde);
    }

    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;

        support.firePropertyChange(TimerChangeListener.SECONDE_PROP,oldValue,secondes);
    }

    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;

        support.firePropertyChange(TimerChangeListener.MINUTE_PROP,oldValue,minutes);
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;

        support.firePropertyChange(TimerChangeListener.HEURE_PROP,oldValue,heures);

    }


    // override TimeChangeProvider's methods
    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {

        support.addPropertyChangeListener(pl);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl, String prop) {

        support.addPropertyChangeListener(prop, pl);

    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {

        support.removePropertyChangeListener(pl);

    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl, String prop) {

        support.removePropertyChangeListener(prop, pl);

    }


    // override TimerService's methods
    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}

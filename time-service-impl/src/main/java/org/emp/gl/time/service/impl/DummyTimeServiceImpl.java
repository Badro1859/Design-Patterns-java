/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

/**
 * @author tina
 */

public class DummyTimeServiceImpl
        extends TimerTask
        implements TimerService {

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;

    private List<TimerChangeListener> listeners = new LinkedList<>();
    private List<TimerChangeListener> SecondeListeners = new LinkedList<>();
    private List<TimerChangeListener> MinutesListeners = new LinkedList<>();
    private List<TimerChangeListener> HeurListeners = new LinkedList<>();


    @Delegate
    private PropertyChangeSupport var = new PropertyChangeSupport(this);




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
        //dixiemeDeSecondesChanged(oldValue, dixiemeDeSeconde);
        var.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,oldValue,dixiemeDeSeconde);
    }
    private void dixiemeDeSecondesChanged(int oldValue, int newValue) {
        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                    oldValue, dixiemeDeSeconde);
        }
    }


    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;

        //secondesChanged(oldValue, secondes);
        var.firePropertyChange(TimerChangeListener.SECONDE_PROP,oldValue,secondes);
    }
    private void secondesChanged(int oldValue, int secondes) {

        for (TimerChangeListener l : listeners) {
            //l.propertyChange(TimerChangeListener.SECONDE_PROP,oldValue, secondes);
            l.propertyChange(new PropertyChangeEvent(this , TimerChangeListener.SECONDE_PROP, oldValue,secondes));
        }
        for (TimerChangeListener l : SecondeListeners) {
            //l.propertyChange(TimerChangeListener.SECONDE_PROP,oldValue, secondes);
            l.propertyChange(new PropertyChangeEvent(this , TimerChangeListener.SECONDE_PROP, oldValue,secondes));
        }
    }


    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;

        minutesChanged (oldValue, minutes) ;
    }
    private void minutesChanged(int oldValue, int minutes) {
        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.MINUTE_PROP,
                    oldValue, minutes);
        }
        for (TimerChangeListener l : MinutesListeners) {
            l.propertyChange(TimerChangeListener.MINUTE_PROP,
                    oldValue, minutes);
        }
    }


    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;

        heuresChanged (oldValue, heures) ;
    }
    private void heuresChanged(int oldValue, int heures) {
        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.HEURE_PROP,
                    oldValue, heures);
        }
        for (TimerChangeListener l : HeurListeners) {
            l.propertyChange(TimerChangeListener.HEURE_PROP,
                    oldValue, heures);
        }
    }

    // override TimeChangeProvider's methods
    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        //listeners.add(pl);

        var.addPropertyChangeListener(pl);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl, String prop) {
        // TODO: implementation of addTimeChangeListner

        var.addPropertyChangeListener(prop, pl);

        /*
        switch(prop){
            case TimerChangeListener.SECONDE_PROP:
                SecondeListeners.add(pl);
                break;
            case TimerChangeListener.MINUTE_PROP:
                MinutesListeners.add(pl);
                break;
            case TimerChangeListener.HEURE_PROP:
                HeurListeners.add(pl);
                break;
            default:
                listeners.add(pl);
                break;
        }*/
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {

        //listeners.remove(pl);
        var.removePropertyChangeListener(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl, String prop) {

        var.removePropertyChangeListener(prop, pl);
        /*
        switch(prop){
            case TimerChangeListener.SECONDE_PROP:
                SecondeListeners.remove(pl);
                break;
            case TimerChangeListener.MINUTE_PROP:
                MinutesListeners.remove(pl);
                break;
            case TimerChangeListener.HEURE_PROP:
                HeurListeners.remove(pl);
                break;
            default:
                listeners.remove(pl);
                break;
        }*/
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

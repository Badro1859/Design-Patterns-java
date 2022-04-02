/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.timer.service;

/**
 *
 * @author BADER EDDINE
 */
public interface TimeChangeProvider {

    void addTimeChangeListener(TimerChangeListener pl);

    void addTimeChangeListener(TimerChangeListener pl, String prop);

    void removeTimeChangeListener(TimerChangeListener pl);

    void removeTimeChangeListener(TimerChangeListener pl, String prop);
}

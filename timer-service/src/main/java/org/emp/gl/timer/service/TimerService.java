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
public interface TimerService extends TimeChangeProvider {

    int getMinutes();

    int getHeures();

    int getSecondes();

    int getDixiemeDeSeconde();

}

package org.emp.gl.core.launcher;

import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class DisplayTimeGUI extends JFrame
    implements TimerChangeListener {

    TimerService timer;

    JLabel time;

    public DisplayTimeGUI() {
        timer = Lookup.getInstance().getService(TimerService.class);
        timer.addTimeChangeListener(this, TimerChangeListener.SECONDE_PROP);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420,420);

        setLayout(new FlowLayout(FlowLayout.LEFT, 150, 10));
        add( new JLabel("The Time Now :"));

        time = new JLabel("00:00:00");
        add(time);

        setVisible(true);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int s = timer.getSecondes();
        int m = timer.getMinutes();
        int h = timer.getHeures();

        String str = (h>9 ? h :"0"+h) +":"+ (m>9 ? m :"0"+m) +":"+ (s>9 ? s :"0"+s);
        time.setText(str);

        repaint(0);
    }

}

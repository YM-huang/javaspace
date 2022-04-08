package com.edu.zjut.cn.Test;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * @program: observer2
 * @description: JFrame绘制
 * @author: hym(huangyimiao666 @ gmail.com)
 * @create: 2022-04-07 20:29
 **/
class Drawing extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private static final int MAX_SAMPLES = 5000;
    private int index = 0;
    private long[] time = new long[MAX_SAMPLES];
    private float[] val = new float[MAX_SAMPLES];
    DateFormat fmt = DateFormat.getDateTimeInstance();

    private JPanel dataPanel;
    private JTextArea dataTextArea;
    private JScrollPane jScrollPane1;

    /**
    * @Description: 
    * @Param: []
    * @return: 
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:31 2022/4/7
    */
    public Drawing() {
        initComponents();
    }

    /**
    * @Description:
    * @Param: [title]
    * @return:
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 21:11 2022/4/7
    */
    public Drawing(String title) {
        initComponents();
        setTitle(title);
    }

    /**
    * @Description: 
    * @Param: [g]
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:31 2022/4/7
    */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // get size of pane
        int left = dataPanel.getX() + 30;
        int top = dataPanel.getY() + 50;
        int right = left + dataPanel.getWidth() - 20;
        int bottom = top + dataPanel.getHeight() - 20;

        // leave some room for margins
        int y0 = bottom - 20;
        int yn = top;
        int x0 = left + 33;
        int xn = right;
        // light values range from 0 to 800
        double vscale = (yn - y0) / 120.0;
        // 1 pixel = 2 seconds = 2000 milliseconds
        double tscale = 1.0 / 2000.0;

        // draw X axis = time
        g.setColor(Color.BLACK);
        g.drawLine(x0, yn, x0, y0);
        g.drawLine(x0, y0, xn, y0);
        int tickInt = 60 / 2;
        // tick every 1 minute
        for (int xt = x0 + tickInt; xt < xn; xt += tickInt) {
            g.drawLine(xt, y0 + 5, xt, y0 - 5);
            int min = (xt - x0) / (60 / 2);
            g.drawString(Integer.toString(min), xt - (min < 10 ? 3 : 7) , y0 + 20);
        }
        g.setColor(Color.BLUE);
        // tick every 200
        for (int vt = 120; vt > 0; vt -= 20) {
            int v = y0 + (int)(vt * vscale);
            g.drawLine(x0 - 5, v, x0 + 5, v);
            g.drawString(Integer.toString(vt), x0 - 38 , v + 5);
        }

        // graph sensor values
        int xp = -1;
        int vp = -1;
        for (int i = 0; i < index; i++) {
            int x = x0 + (int)((time[i] - time[0]) * tscale);
            int v = y0 +(int)(val[i] * vscale);
            if (xp > 0) {
                g.drawLine(xp, vp, x, v);
            }
            xp = x;
            vp = v;
        }
    }

    /**
    * @Description: 
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:32 2022/4/7
    */
    private void initComponents() {

        dataPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTextArea = new javax.swing.JTextArea();

        dataPanel.setBackground(new java.awt.Color(255, 255, 255));
        dataPanel.setMinimumSize(new java.awt.Dimension(400, 250));
        dataPanel.setPreferredSize(new java.awt.Dimension(400, 250));
        getContentPane().add(dataPanel, java.awt.BorderLayout.CENTER);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(400, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 100));

        dataTextArea.setColumns(20);
        dataTextArea.setEditable(false);
        dataTextArea.setRows(4);
        jScrollPane1.setViewportView(dataTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.SOUTH);

        pack();
    }

    /**
    * @Description:
    * @Param: [t, v]
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:32 2022/4/7
    */
    public void addData(long t, float v) {
        time[index] = t;
        val[index++] = v;
        dataTextArea.append(fmt.format(new Date(t)) + "    value = " + v + "\n");
        dataTextArea.setCaretPosition(dataTextArea.getText().length());
        repaint();
    }
}

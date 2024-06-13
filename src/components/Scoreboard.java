package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import constants.CustomColors;

public class Scoreboard {
    public JPanel main;
    CustomPanel box;
    JLabel test;

    public Scoreboard(JFrame frame){

        main = new JPanel(new BorderLayout());
        main.setBorder(new EmptyBorder(0,20,0,20));

        box  = new CustomPanel(40);
        box.setPreferredSize(new Dimension(0, 250));
        box.setMinimumSize(new Dimension(0, 250));
        box.setMaximumSize(new Dimension(500, 250));

        main.contains(20, 0);

        test = new JLabel("Test");

        box.setBackground(CustomColors.lightBlue);

        main.add(box, BorderLayout.NORTH);

        box.add(test);
    }
}

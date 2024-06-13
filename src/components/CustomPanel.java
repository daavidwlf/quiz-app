package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager2;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 * This class redefines the way panels are built to specify border radiuses
 * 
 * @author David Wolf
 * @version 2024-06-13
 */

public class CustomPanel extends JPanel {
    private int borderRadius = 0;

    public CustomPanel(){
        setOpaque(false);
    }

    public CustomPanel(int borderRadius){
        setOpaque(false);
        this.borderRadius = borderRadius;
    };

    public CustomPanel(int borderRadius,  LayoutManager2 layout){
        setOpaque(false);
        this.borderRadius = borderRadius;
        this.setLayout(layout);
    };

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setColor(getBackground());
        graphics2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
        System.out.println("Pint");
    }
}

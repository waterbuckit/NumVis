/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author waterbucket
 */
public class GridVisualiser extends JPanel {

    private final int[] digits;
    private final int columns;
    private final int rows;
    private final int scaleFactorY;
    private final int scaleFactorX;

    public GridVisualiser(int[] digits) {
        this.digits = digits;
        this.columns = (int) Math.sqrt(digits.length);
        this.rows = (int) Math.ceil(digits.length / (float) columns);
        this.scaleFactorX = NumVis.WIDTH / this.columns;
        this.scaleFactorY = NumVis.HEIGHT / this.rows;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int digitIndex = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                g2d.setColor(Color.getHSBColor(mapToRange(this.digits[digitIndex],
                        0, 9, 0.0f, 1.0f), 0.8f, 1.0f));
                g2d.fillRect(x*scaleFactorX, y*scaleFactorY, this.scaleFactorX, this.scaleFactorY);
                digitIndex++;
                if(digitIndex >= this.digits.length){
                    digitIndex = this.digits.length - 1;
                }
            }
        }
    }

    private float mapToRange(float value, float leftMin, float leftMax, float rightMin, float rightMax) {
        return (value - leftMin) / (leftMax - value) * (rightMax - rightMin) + rightMin;
    }

}

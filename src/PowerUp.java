/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
import java.awt.*;

public class PowerUp extends VectorSprite
{
    int size = 8;
    public PowerUp()
    {
    shape = new Polygon();
    drawShape = new Polygon();
    for(int i = 0;i<360;i++)
        {
            shape.addPoint((int)Math.round(size * Math.cos(i*Math.PI/180)),(int)Math.round(size * Math.sin(i*Math.PI/180)));
            drawShape.addPoint((int)Math.round(size * Math.cos(i*Math.PI/180)),(int)Math.round(size * Math.sin(i*Math.PI/180)));
        }
    counter = 200;
    xpos = Math.random()*900;
    ypos = Math.random()*600;
    active = true;

}
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
import java.awt.*;

public class SpaceShip extends VectorSprite
{


    public SpaceShip()
    {
        //draws our ship
        shape = new Polygon();
        shape.addPoint(0,-30);
        shape.addPoint(8,-17);
        shape.addPoint(0,0);
        shape.addPoint(-8,-17);
        shape.addPoint(0,-30);
        shape.addPoint(-12,18);
        shape.addPoint(0, 8);
        shape.addPoint(0,-15);
        shape.addPoint(-18, 12);//upper wing tip
        shape.addPoint(-15, 12);
        shape.addPoint(-15, 5);
        shape.addPoint(-18, 5);
        shape.addPoint(-18, 12);
        shape.addPoint(0, 8);
        shape.addPoint(18, 12);//upper wing tip
        shape.addPoint(15, 12);
        shape.addPoint(15, 5);
        shape.addPoint(18, 5);
        shape.addPoint(18, 12);
        shape.addPoint(0,-15);
        shape.addPoint(0, 8);
        shape.addPoint(12,18);

        xpos = 450;
        ypos = 300;
        thrust = 3;
        rotation = 0.1;
        active = true;

        drawShape = new Polygon();
        drawShape.addPoint(0,-30);
        drawShape.addPoint(8,-17);
        drawShape.addPoint(0,0);
        drawShape.addPoint(-8,-17);
        drawShape.addPoint(0,-30);
        drawShape.addPoint(-12,18);
        drawShape.addPoint(0, 8);
        drawShape.addPoint(0, -15);
        drawShape.addPoint(-18, 12);//upper wing tip
        drawShape.addPoint(-15, 12);
        drawShape.addPoint(-15, 5);
        drawShape.addPoint(-18, 5);
        drawShape.addPoint(-18, 12);
        drawShape.addPoint(0, 8);
        drawShape.addPoint(18, 12);//upper wing tip
        drawShape.addPoint(15, 12);
        drawShape.addPoint(15, 5);
        drawShape.addPoint(18, 5);
        drawShape.addPoint(18, 12);
        drawShape.addPoint(0, -15);
        drawShape.addPoint(0, 8);
        drawShape.addPoint(12,18);
    }

    public void rotateLeft()
    {
        angle -= rotation;
    }

    public void rotateRight()
    {
        angle += rotation;
    }


    public void accelerate(int val)
    {

        //calculates speed via the angle of ship
        xspeed = val * Math.cos(angle-Math.PI/2)*thrust;
        yspeed = val * Math.sin(angle-Math.PI/2)*thrust;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
import java.awt.*;

public class SpaceRock extends VectorSprite
{
int size;
    public void updatePosition()
    {
        angle += rotation;
        super.updatePosition();
    }

    public SpaceRock()
    {
        makeAsteroid(6);
    }
    public SpaceRock(double x, double y,int s)
    {
        makeAsteroid(s);
        xpos = x;
        ypos = y;

    }


    public void makeAsteroid(int s)
    {
        
        shape = new Polygon();
        drawShape = new Polygon();
        double a,h;
        size = s;
        rotation = 0.05;

        a = 2*Math.random()*Math.PI;
        h = Math.random()*2+1;

        xspeed = Math.cos(a)*h;
        yspeed = Math.sin(a)*h;

        active = true;
        a = Math.random()*2*Math.PI;
        h = Math.random()*400 + 100;

        xpos = Math.cos(a)*h + 450;
        ypos = Math.sin(a)*h + 300;


        shape = new Polygon();
        drawShape = new Polygon();


        
        //awesome smiley
        shape.addPoint(size*-2,size*8);
        shape.addPoint(size*2,size*8);
        shape.addPoint(size*6,size*6);
        shape.addPoint(size*8,size*2);
        shape.addPoint(size*-7,size*2);
        shape.addPoint(size*-5,size*5);
        shape.addPoint(size*-5,size*4);
        shape.addPoint(size*-3,size*4);
        shape.addPoint(size*-2,size*5);
        shape.addPoint(size*-2,size*7);
        shape.addPoint(size*-5,size*5);
        shape.addPoint(size*-2,size*7);
        shape.addPoint(size*2,size*7);
        shape.addPoint(size*5,size*5);
        shape.addPoint(size*7,size*2);
        shape.addPoint(size*-8,size*2);
        shape.addPoint(size*-8,size*-2);
        shape.addPoint(size*-6,size*-2);
        shape.addPoint(size*-6,size*-5);
        shape.addPoint(size*-5,size*-6);
        shape.addPoint(size*-3,size*-6);
        shape.addPoint(size*-2,size*-5);
        shape.addPoint(size*-2,size*-3);
        shape.addPoint(size*-2,size*-2);
        shape.addPoint(size*-3,size*-2);
        shape.addPoint(size*-3,size*-3);
        shape.addPoint(size*-2,size*-3);
        shape.addPoint(size*-2,size*-2);
        shape.addPoint(size*-8,size*-2);
        shape.addPoint(size*-6,size*-6);
        shape.addPoint(size*-2,size*-8);
        shape.addPoint(size*2,size*-8);
        shape.addPoint(size*6,size*-6);
        shape.addPoint(size*8,size*-2);
        shape.addPoint(size*6,size*-2);
        shape.addPoint(size*6,size*-3);
        shape.addPoint(size*6,size*-5);
        shape.addPoint(size*5,size*-6);
        shape.addPoint(size*3,size*-6);
        shape.addPoint(size*2,size*-5);
        shape.addPoint(size*2,size*-2);
        shape.addPoint(size*5,size*-2);
        shape.addPoint(size*5,size*-3);
        shape.addPoint(size*6,size*-3);
        shape.addPoint(size*6,size*-2);
        shape.addPoint(size*5,size*-2);
        shape.addPoint(size*8,size*-2);
        shape.addPoint(size*8,size*2);
        shape.addPoint(size*-8,size*2);
        shape.addPoint(size*-6,size*6);
        shape.addPoint(size*-2,size*8);
        shape.addPoint(size*2,size*8);

        drawShape.addPoint(size*-2,size*8);
        drawShape.addPoint(size*2,size*8);
        drawShape.addPoint(size*6,size*6);
        drawShape.addPoint(size*8,size*2);
        drawShape.addPoint(size*-7,size*2);
        drawShape.addPoint(size*-5,size*5);
        drawShape.addPoint(size*-5,size*4);
        drawShape.addPoint(size*-3,size*4);
        drawShape.addPoint(size*-2,size*5);
        drawShape.addPoint(size*-2,size*7);
        drawShape.addPoint(size*-5,size*5);
        drawShape.addPoint(size*-2,size*7);
        drawShape.addPoint(size*2,size*7);
        drawShape.addPoint(size*5,size*5);
        drawShape.addPoint(size*7,size*2);
        drawShape.addPoint(size*-8,size*2);
        drawShape.addPoint(size*-8,size*-2);
        drawShape.addPoint(size*-6,size*-2);
        drawShape.addPoint(size*-6,size*-5);
        drawShape.addPoint(size*-5,size*-6);
        drawShape.addPoint(size*-3,size*-6);
        drawShape.addPoint(size*-2,size*-5);
        drawShape.addPoint(size*-2,size*-3);
        drawShape.addPoint(size*-2,size*-2);
        drawShape.addPoint(size*-3,size*-2);
        drawShape.addPoint(size*-3,size*-3);
        drawShape.addPoint(size*-2,size*-3);
        drawShape.addPoint(size*-2,size*-2);
        drawShape.addPoint(size*-8,size*-2);
        drawShape.addPoint(size*-6,size*-6);
        drawShape.addPoint(size*-2,size*-8);
        drawShape.addPoint(size*2,size*-8);
        drawShape.addPoint(size*6,size*-6);
        drawShape.addPoint(size*8,size*-2);
        drawShape.addPoint(size*6,size*-2);
        drawShape.addPoint(size*6,size*-3);
        drawShape.addPoint(size*6,size*-5);
        drawShape.addPoint(size*5,size*-6);
        drawShape.addPoint(size*3,size*-6);
        drawShape.addPoint(size*2,size*-5);
        drawShape.addPoint(size*2,size*-2);
        drawShape.addPoint(size*5,size*-2);
        drawShape.addPoint(size*5,size*-3);
        drawShape.addPoint(size*6,size*-3);
        drawShape.addPoint(size*6,size*-2);
        drawShape.addPoint(size*5,size*-2);
        drawShape.addPoint(size*8,size*-2);
        drawShape.addPoint(size*8,size*2);
        drawShape.addPoint(size*-8,size*2);
        drawShape.addPoint(size*-6,size*6);
        drawShape.addPoint(size*-2,size*8);
        drawShape.addPoint(size*2,size*8);
        
        
        /*
        shape.addPoint(size*2,size*-10);
        shape.addPoint(size*13,size*-12);
        shape.addPoint(size*18,size*0);
        shape.addPoint(size*17,size*13);
        shape.addPoint(size*3,size*17);
        shape.addPoint(size*-7,size*5);
        shape.addPoint(size*-17,size*0);
        shape.addPoint(size*-9,size*-7);
        shape.addPoint(size*2,size*-14);
        drawShape.addPoint(size*2,size*-10);
        drawShape.addPoint(size*13,size*-12);
        drawShape.addPoint(size*18,size*0);
        drawShape.addPoint(size*17,size*13);
        drawShape.addPoint(size*3,size*17);
        drawShape.addPoint(size*-7,size*5);
        drawShape.addPoint(size*-17,size*0);
        drawShape.addPoint(size*-9,size*-7);
        drawShape.addPoint(size*2,size*-14);
        }
        
        //circle
        for(int i = 0;i<360;i++)
        {
            shape.addPoint((int)Math.round(asteroidSize * Math.sin(i*Math.PI/180)),(int)Math.round(asteroidSize * Math.cos(i*Math.PI/180)));
            drawShape.addPoint((int)Math.round(asteroidSize * Math.sin(i*Math.PI/180)),(int)Math.round(asteroidSize * Math.cos(i*Math.PI/180)));
        }
         */
    }




}

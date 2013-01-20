/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
import java.awt.*;

public class Bullet extends VectorSprite
{
    int size = 2;
    public Bullet(double x,double y, double a)
    {
        shape = new Polygon();
        drawShape = new Polygon();

        shape.addPoint(size*-6,size*0);
        shape.addPoint(size*0,size*2);
        shape.addPoint(size*0,size*-2);
        shape.addPoint(size*6,size*-2);
        shape.addPoint(size*6,size*2);
        shape.addPoint(size*0,size*2);
        shape.addPoint(size*6,size*2);
        shape.addPoint(size*10,size*1);
        shape.addPoint(size*6,size*0);
        shape.addPoint(size*10,size*-1);
        shape.addPoint(size*6,size*-2);
        shape.addPoint(size*0,size*-2);

        drawShape.addPoint(size*-6,size*0);
        drawShape.addPoint(size*0,size*2);
        drawShape.addPoint(size*0,size*-2);
        drawShape.addPoint(size*6,size*-2);
        drawShape.addPoint(size*6,size*2);
        drawShape.addPoint(size*0,size*2);
        drawShape.addPoint(size*6,size*2);
        drawShape.addPoint(size*10,size*1);
        drawShape.addPoint(size*6,size*0);
        drawShape.addPoint(size*10,size*-1);
        drawShape.addPoint(size*6,size*-2);
        drawShape.addPoint(size*0,size*-2);
        

        xpos = x;
        ypos = y;
        angle = a;
        thrust = 7;
        active = true;
        counter = 70;
        xspeed = Math.cos(angle)*thrust;
        yspeed = Math.sin(angle)*thrust;
        
    }

    public void updatePosition()
    {

        xpos += xspeed;
        ypos += yspeed;

        int x,y;
        for(int i = 0;i<shape.npoints;i++)
        {
            //x = x*cos@ - y*sin@
            //y = x*sin + y*cos@
            x = shape.xpoints[i];
            y = shape.ypoints[i];
            drawShape.xpoints[i] = (int)Math.round(x * Math.cos(angle-Math.PI) - y * Math.sin(angle-Math.PI));
            drawShape.ypoints[i] = (int)Math.round(x * Math.sin(angle-Math.PI) + y * Math.cos(angle-Math.PI));
        }
        drawShape.invalidate();
        drawShape.translate((int)xpos,(int)ypos);

        wrapAround();
    }

}

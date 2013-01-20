/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
import java.awt.*;


public class VectorSprite 
{
    //General Declarations
    double ypos;
    double xpos;
    double yspeed;
    double xspeed; 
    double angle;
    double rotation;
    double thrust;
    boolean active;
    int counter;
    int delay;
    Polygon shape;
    Polygon drawShape;



    public VectorSprite()
    {
        
    }

    public void wrapAround()
    {

        //Now you're thinking with portals.
        if(xpos <0)
        {
            xpos = 900;
        }

        if (xpos > 900)
        {
            xpos = 0;
        }

        if (ypos > 600)
        {
            ypos = 0;
        }

        if (ypos < 0)
        {
            ypos = 600;
        }
    }

    public void paint(Graphics g)
    {
        g.drawPolygon(drawShape);

    }

    public void updatePosition()
    {
        counter--;
        xpos += xspeed;
        ypos += yspeed;

        int x,y;
        for(int i = 0;i<shape.npoints;i++)
        {
            //x = x*cos@ - y*sin@
            //y = x*sin + y*cos@
            x = shape.xpoints[i];
            y = shape.ypoints[i];
            drawShape.xpoints[i] = (int)Math.round(x * Math.cos(angle) - y * Math.sin(angle));
            drawShape.ypoints[i] = (int)Math.round(x * Math.sin(angle) + y * Math.cos(angle));
        }
        drawShape.invalidate();
        drawShape.translate((int)xpos,(int)ypos);

        wrapAround();
    }
    
}

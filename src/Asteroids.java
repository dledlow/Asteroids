/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.Color.*;
import javax.swing.Timer;
/**
 *
 * @author RP4K
 */
public class Asteroids extends Applet implements KeyListener,ActionListener
{

    

    //General Declarations
    
    SpaceShip ship;
    boolean bulletTime,shotOffset;
    boolean showIntro,sndBoom,sndEnd;


    AudioClip bgm,pewpew,boom1,boom2,end,dead,powGet;
    
    
    int rndColor = 0;
    PowerUp pow;
    
    int score,high,total;
    int powCount;
    int xparty,yparty;
    int red,blue,green;
    Color powcolor;
    BufferedReader br;
    BufferedWriter bw;

    
    int fireSpeed;
    int partycount;
    boolean invuln;
    Color shpcolor;
    Color rckcolor;
    ArrayList <Fire> fireList;
    ArrayList <Explosion> boomList2;
    ArrayList <Explosion> boomList;
    ArrayList <SpaceRock> asteroidList;
    ArrayList <Bullet> bulletList;
    long  fpsCount;
    
    Timer timer;
    Image offScreen;
    Graphics offg;
    boolean upkey,downkey,leftkey,rightkey,spacekey,qkey,wkey,rkey;
    int shipMove = 0;

    
    
    public boolean isRespawnSafe()
    {
        double a,b,c;
        for(int i = 0;i<asteroidList.size();i++)
            {
                a = asteroidList.get(i).xpos-ship.xpos;
                b = asteroidList.get(i).ypos-ship.ypos;
                c = Math.sqrt(a*a+b*b);
                
                if(c<150)
                {
                    return false;
                }
            }
        return true;
        
    }

    //x = x*cos@ - y*sin@
    //y = x*sin + y*cos@

    public void reset()
    {
        ship.ypos = 300;
        ship.xpos = 450;
        ship.active = true;
        ship.xspeed = 0;
        ship.yspeed = 0;
        ship.angle = 0;
        showIntro = true;
        
        score = 0;
        invuln = true;
        end.stop();
        bgm.play();
        bulletTime = false;
        asteroidList.clear();


        for(int i = 0;i<10;i++)
        {
            asteroidList.add(new SpaceRock());
        }
        for(int i = 0;i<asteroidList.size();i++)
        {
            asteroidList.get(i).rotation = Math.random()*0.1;
            asteroidList.get(i).thrust = Math.random()*2+1;
        }


    }

    public void removeDeadBullets()
    {
        for(int i = 0;i<bulletList.size();i++)
        {
            bulletList.get(i).counter--;
            if(bulletList.get(i).counter <0)
            {
                bulletList.remove(i);
            }
        }

        for(int i = 0;i<boomList.size();i++)
        {
            boomList.get(i).counter--;
            if(boomList.get(i).counter <0)
            {
                boomList.remove(i);
            }
        }

        for(int i = 0;i<boomList2.size();i++)
        {
            boomList2.get(i).counter--;
            if(boomList2.get(i).counter <0)
            {
                boomList2.remove(i);
            }
        }

        for(int i = 0;i<fireList.size();i++)
        {
            fireList.get(i).counter--;
            if(fireList.get(i).counter <0)
            {
                fireList.remove(i);
            }
        }
    }

    public void respawnShip()
    {
        
        if(ship.counter < 0 && !ship.active /*&& isRespawnSafe()*/)
        {
            reset();

        }
    }

    public void actionPerformed(ActionEvent e)
    {
        
        if(System.currentTimeMillis()>fpsCount)
        {
            fpsCount = System.currentTimeMillis()+(1000/60);
            ship.updatePosition();
            pow.updatePosition();
            ship.delay--;

            red = (int)Math.round(Math.random()*255);
            blue = (int)Math.round(Math.random()*255);
            green = (int)Math.round(Math.random()*255);
            powcolor = new Color(red,blue,green);
            
            if (pow.counter < 0)
            {
                pow.active = true;
            }
            
             

            for(int i = 0;i<asteroidList.size();i++)
            {
                asteroidList.get(i).updatePosition();
            }
            for(int i = 0;i<bulletList.size();i++)
            {
                bulletList.get(i).updatePosition();
            }
            for(int i = 0;i<boomList.size();i++)
            {
                boomList.get(i).updatePosition();
            }
            for(int i = 0;i<boomList2.size();i++)
            {
                boomList2.get(i).updatePosition();
            }
            for(int i = 0;i<fireList.size();i++)
            {
                fireList.get(i).updatePosition();
            }
            
            if (bulletTime)
            {
                timer.setDelay(50);
            }
            else
            {
                timer.setDelay(20);
            }
            
            if(asteroidList.isEmpty() && partycount == 0)
            {
                sndEnd = true;
                xparty = (int)Math.round(Math.random()*900);
                yparty = (int)Math.round(Math.random()*600);
                for(int i = 0;i<500;i++)
                {                    
                    boomList2.add(new Explosion(xparty,yparty));
                }
                for(int i = 0;i<500;i++)
                {
                    boomList.add(new Explosion(xparty, yparty));
                }
                partycount = 30;
            }
            else if(asteroidList.isEmpty())
            {
                partycount--;
                sndEnd = true;
                
                bgm.stop();
            }

            if(sndEnd && ship.thrust == 3)
            {
                if(score>high)
                {
                    high = score;
                }
                writeScore();
                end.loop();
                sndEnd = false;
                ship.thrust = 4;
            }
            
            if(qkey && wkey && rkey)
            {
                invuln = true;

            }

            checkCollision();
            respawnShip();
            removeDeadBullets();
            
            keyCheck();
        }
        
    }

    public boolean collision(VectorSprite s1,VectorSprite s2)
    {
        int x,y;  
        for (int i = 0;i < s1.drawShape.npoints;i++)
        {
            x = s1.drawShape.xpoints[i];
            y = s1.drawShape.ypoints[i];
            
            if(s2.drawShape.contains(x,y))
            {
                return true;
            }
        }
        for (int i = 0; i < s2.drawShape.npoints; i++)
        {
            x = s2.drawShape.xpoints[i];
            y = s2.drawShape.ypoints[i];
            
            if(s1.drawShape.contains(x,y))
            {
                return true;
            }
        }

        return false;
        
    }
    
    
    public void keyCheck()
    {
        if(upkey)
        {
            ship.accelerate(1);
            if(ship.active)
            {
                fireList.add(new Fire(ship.xpos,ship.ypos,ship.angle-Math.PI*0.5));
            }
        }

        if (rightkey)
        {
            ship.rotateRight();
        }

        if (leftkey)
        {
            ship.rotateLeft();
        }

        if (downkey)
        {
            ship.accelerate(-1);
            if(ship.active)
            {
                fireList.add(new Fire(ship.xpos,ship.ypos,ship.angle-Math.PI*0.5));
            }
        }
        
        if(spacekey && ship.active && ship.delay <= 0)
        {
            pewpew.play();
            //alternating shots
            if (shotOffset)
            {
                bulletList.add(new Bullet(ship.xpos + Math.cos(ship.angle)*16, (int)ship.ypos + Math.sin(ship.angle)*16,ship.angle-Math.PI*0.5));
                shotOffset = false;
            }
            else
            {
                bulletList.add(new Bullet(ship.xpos - Math.cos(ship.angle)*16, (int)ship.ypos - Math.sin(ship.angle)*16,ship.angle-Math.PI*0.5));
                shotOffset = true;
            }

            if(powCount > 0)
            {
                powCount--;
                fireSpeed++;
            }
            else
            {
                fireSpeed = 25;
            }

            ship.delay = fireSpeed;
        }
    }


    public void keyPressed(KeyEvent e)
    {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {            
            rightkey = true;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftkey = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            showIntro = false;
            upkey = true;
            invuln = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            showIntro = false;
            downkey = true;
            invuln = false;
          
        }

        if (e.getKeyCode() == KeyEvent.VK_B)
        {
            bulletTime = true;
            invuln = false;
            
            showIntro = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            qkey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            wkey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_R)
        {
            rkey = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            spacekey = true;
            invuln = false;
            showIntro = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_1)
        {
            ship.thrust = 3;
            end.stop();
            bgm.loop();
            for(int i = 0;i<1;i++)
            {
                asteroidList.add(new SpaceRock());
                
            }

            for(int i = 0;i<asteroidList.size();i++)
            {
                asteroidList.get(i).rotation = Math.random()*0.1;
                asteroidList.get(i).thrust = Math.random()*2+1;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_2)
        {
            ship.thrust = 3;
            end.stop();
            bgm.loop();
            
            for(int i = 0;i<2;i++)
            {
                asteroidList.add(new SpaceRock());
            }

            for(int i = 0;i<asteroidList.size();i++)
            {
                asteroidList.get(i).rotation = Math.random()*0.1;
                asteroidList.get(i).thrust = Math.random()*2+1;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_3)
        {   
            ship.thrust = 3;
            end.stop();
            bgm.loop();
            
            for(int i = 0;i<3;i++)
            {
                asteroidList.add(new SpaceRock());
            }

            for(int i = 0;i<asteroidList.size();i++)
            {
                asteroidList.get(i).rotation = Math.random()*0.1;
                asteroidList.get(i).thrust = Math.random()*2+1;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_4)
        {
            ship.thrust = 3;
            end.stop();
            bgm.loop();
            
            for(int i = 0;i<4;i++)
            {
                asteroidList.add(new SpaceRock());
            }

            for(int i = 0;i<asteroidList.size();i++)
            {
                asteroidList.get(i).rotation = Math.random()*0.1;
                asteroidList.get(i).thrust = Math.random()*2+1;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_5)
        {
            ship.thrust = 3;
            end.stop();
            bgm.loop();
            
            for(int i = 0;i<5;i++)
            {
                asteroidList.add(new SpaceRock());
            }

            for(int i = 0;i<asteroidList.size();i++)
            {
                asteroidList.get(i).rotation = Math.random()*0.1;
                asteroidList.get(i).thrust = Math.random()*2+1;
            }

        }

        

    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            upkey = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downkey = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftkey = false;
        }



        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightkey = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_B)
        {
            bulletTime = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            spacekey = false;

        }
    }

    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void writeScore()
    {
        try
        {
            bw = new BufferedWriter(new FileWriter("AsteroidScore.txt"));
            bw.write(Integer.toString(high));
            bw.newLine();
            bw.append(Integer.toString(total));


            bw.close();

        }
        catch (Exception e)
        {

        }
    }

    public void init()
    {
        // TODO start asynchronous download of heavy resources
        this.addKeyListener(this);
        this.setSize(900,600); // (Width,Height)
        powCount = 0;
        pow = new PowerUp();
        fireSpeed = 25;
        invuln = true;
        partycount = 60;

        try//catches errors
        {
            br = new BufferedReader(new FileReader("AsteroidScore.txt"));

            String line = br.readLine();
            high = Integer.parseInt(line);
            line = br.readLine();
            total = Integer.parseInt(line);
            br.close();

        }
        catch (Exception e)
        {

        }
        showIntro = true;
        powGet = getAudioClip(getCodeBase(),"gemcollect.wav");
        dead = getAudioClip(getCodeBase(),"gameover.wav");
        end = getAudioClip(getCodeBase(),"portal_no_cake_for_you.wav");
        bgm = getAudioClip(getCodeBase(),"portal_4000_degrees_kelvin.wav");
        boom1 = getAudioClip(getCodeBase(),"Expolsion.wav");
        boom2 = getAudioClip(getCodeBase(),"Explosion.wav");
        pewpew = getAudioClip(getCodeBase(),"hit.wav");
        powcolor = new Color(red,blue,green);
        bgm.loop();

        shpcolor = new Color(7,180,255);
        rckcolor = new Color(153,110,55);
        pow.active = true;
        fireList = new ArrayList();
        boomList2 = new ArrayList();
        boomList = new ArrayList();
        asteroidList = new ArrayList();
        bulletList = new ArrayList();
        
        for(int i = 0;i<10;i++)
        {
            asteroidList.add(new SpaceRock());
        }

        for(int i = 0;i<asteroidList.size();i++)
        {
            asteroidList.get(i).rotation = Math.random()*0.1;
            asteroidList.get(i).thrust = Math.random()*2+1;
        }



        
        ship = new SpaceShip();
        timer = new Timer(20,this);
        timer.start();
        offScreen = createImage(this.getWidth(),this.getHeight());
        offg = offScreen.getGraphics();
        
    }


    public void checkCollision()
    {
        
        if (collision(pow,ship))
        {
            ship.delay = 0;
            powCount = 150;
            pow.counter = 500;
            fireSpeed = -50;
            pow.xpos = Math.round(Math.random() * 900);
            pow.ypos = Math.round(Math.random() * 600);
            pow.active = false;
            powGet.play();
        }

        for(int i = 0;i<asteroidList.size();i++)
        {
            if(collision(ship,asteroidList.get(i)) && ship.active && !invuln)
            {
                ship.active = false;
                ship.counter = 140;
                bgm.stop();
                dead.play();
                if(score>high)
                {
                    high = score;
                }
                writeScore();
                for(int ii = 0;ii<1000;ii++)
                {
                    
                    boomList2.add(new Explosion(ship.xpos, ship.ypos));
                    
                }
                
            }

            for(int ii = 0;ii<bulletList.size();ii++)
            {
                if(collision(bulletList.get(ii),asteroidList.get(i)))
                {
                    bulletList.get(ii).active = false;
                    asteroidList.get(i).active = false;
                    score +=10;
                    total +=10;
                }
            }
        }
        for(int i = 0;i<asteroidList.size();i++)
        {
            if(!asteroidList.get(i).active)
            {
                if (sndBoom)
                {
                    boom1.play();
                    sndBoom = false;
                }
                else
                {
                    boom2.play();
                    sndBoom = true;
                }
                for(int ii = 0;ii<100;ii++)
                {
                    boomList.add(new Explosion(asteroidList.get(i).xpos, asteroidList.get(i).ypos));
                }
                
                
                if(asteroidList.get(i).size > 2)
                {
                    
                    asteroidList.add(new SpaceRock(asteroidList.get(i).xpos,asteroidList.get(i).ypos,asteroidList.get(i).size-2));
                    asteroidList.add(new SpaceRock(asteroidList.get(i).xpos,asteroidList.get(i).ypos,asteroidList.get(i).size-2));


                }
                asteroidList.remove(i);
            }
        }
        for(int i = 0;i<bulletList.size();i++)
        {
            if(!bulletList.get(i).active)
            {
                bulletList.remove(i);
            }
        }

    }

    public void paint(Graphics g)
    {
       
        offg.setColor(Color.BLACK);
        offg.fillRect(0, 0, 900, 600);
        if(showIntro)
        {
            offg.setColor(Color.GREEN);
            offg.drawString("Welcome to Mah Game.", 10, 200);
            offg.drawString("UP - Foreward" , 10, 225);
            offg.drawString("LEFT - Turn Left" , 10, 250);
            offg.drawString("RIGHT - Turn Right" , 10, 275);
            offg.drawString("DOWN - Backward" , 10, 300);
            offg.drawString("SPACE - Shoot" , 10, 325);
        }
        offg.setColor(Color.yellow);
        for(int i = 0;i<asteroidList.size();i++)
        {
            asteroidList.get(i).paint(offg);
        }
        offg.setColor(shpcolor);
        for(int i = 0;i<bulletList.size();i++)
        {
            bulletList.get(i).paint(offg);
        }
        
            for(int i = 0;i<boomList.size();i++)
            {
                rndColor = (int)Math.round(Math.random()*2);

                if (rndColor == 0)
                {
                    offg.setColor(Color.RED);
                }
                if (rndColor == 1)
                {
                    offg.setColor(Color.ORANGE);
                }
                if (rndColor == 2)
                {
                    offg.setColor(Color.DARK_GRAY);
                }
                
                boomList.get(i).paint(offg);
            }
        for(int i = 0;i<boomList2.size();i++)
            {
                rndColor = (int)Math.round(Math.random()*2);

                if (rndColor == 0)
                {
                    offg.setColor(shpcolor);
                }
                if (rndColor == 1)
                {
                    offg.setColor(Color.GREEN);
                }
                if (rndColor == 2)
                {
                    offg.setColor(Color.BLUE);
                }
            

                boomList2.get(i).paint(offg);
            }
            
        
            for(int i = 0;i<boomList.size();i++)
            {
                rndColor = (int)Math.round(Math.random()*2);

                if (rndColor == 0)
                {
                    offg.setColor(Color.RED);
                }
                if (rndColor == 1)
                {
                    offg.setColor(Color.ORANGE);
                }
                if (rndColor == 2)
                {
                    offg.setColor(Color.DARK_GRAY);
                }


                boomList.get(i).paint(offg);
            
        }
        for(int i = 0;i<fireList.size();i++)
        {
            rndColor = (int)Math.round(Math.random()*2);

            if (rndColor == 0)
            {
                offg.setColor(Color.MAGENTA);
            }
            if (rndColor == 1)
            {
                offg.setColor(shpcolor);
            }
            if (rndColor == 2)
            {
                offg.setColor(Color.blue);
            }
            

            fireList.get(i).paint(offg);
        }

        if (pow.active)
        {
            offg.setColor(powcolor);
            pow.paint(offg);
        }
        offg.setColor(Color.green);
        offg.drawString("Score: "+score, 10, 10);
        offg.drawString("HighScore: "+high, 10, 30);
        offg.drawString("TotalScore: "+total, 10, 50);
        if(ship.active)
        {
            offg.setColor(shpcolor);
            ship.paint(offg);

        }
        if(asteroidList.isEmpty())
        {
            offg.drawString("A Winner Is You!", 400, 300);
            offg.drawString("Press 5 For more Asteroids", 375, 320);
        }

        g.drawImage(offScreen, 0, 0, this);
        repaint();

        

        
    }

    public void update(Graphics g)
    {
        paint(g);
        
    }

    // TODO overwrite start(), stop() and destroy() methods
}

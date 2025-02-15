import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.lang.Math;
import javax.swing.JOptionPane;

public class YesGraphics extends JPanel implements MouseListener
{
    Grid theGrid;
    File leftArrow = new File("src/long-arrow-left.png");
    File rightArrow = new File("src/green-2304007_640.png");
    BufferedImage leftImage;
    BufferedImage rightImage;
    public static int layerValue = 0;
    public YesGraphics()
    {
        addMouseListener(this);
    }
    int mouseY;
    int mouseX;
    private int displaySlide = 1;
    //int mouseY = (int)Math.floor(e.getY()/50);
    // int mouseX = (int)Math.floor(e.getX()/50);
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (displaySlide == 1)
        {
            drawMenu(g);
        }
        if (displaySlide == 2)
        {
            g.setColor(new Color(100, 100, 100));
            g.fillRect(0, 0, 800, 800);
            g.setColor(new Color(0, 0, 0));
            drawGrid(g);
            g.setFont(new Font("Arial", Font.PLAIN, 100));
            g.drawString("Layer " + (layerValue + 1), 10, 700);
            if (theGrid != null) layerDisplayer(g, layerValue);
            try {
                layerBelayer(g);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (displaySlide == 3)
        {
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.drawString("Disclaimer: when you lose you see your IP.", 10, 50);
            g.drawString("This information does not get stored at any point in the code,", 10, 75);
            g.drawString ("and you may go check for yourself.", 10, 100);
            g.drawString ("The objective of the game is to reveal all the points that are not mines", 10, 150);
            g.drawString ("The numbers represented by the spaces represent the amount of mines directly next to them,", 10, 175);
            g.drawString ("(though not diagonally) as well as directly above and/or below them.", 10, 200);
        }
    }
    private void drawMenu(Graphics g)
    {
        g.setColor(new Color(0, 0, 0));
        g.drawRect(200, 400, 400, 100);
        g.setFont(new Font("Arial", Font.PLAIN, 100));
        g.drawString("Play", 300, 475);
        g.setFont(new Font("Arial", Font.PLAIN, 75));
        g.drawRect(200, 600, 400, 100);
        g.drawString("How to Play", 200, 675);
    }
    private void drawGrid(Graphics g)
    {
        for (int i = 0; i < 9; i++)
        {
          g.drawLine(i*50, 0, i*50, 400);
          g.drawLine(0, i*50, 400, i*50);
        }
    }
    private void layerDisplayer(Graphics g, int i)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        for (Point P: Grid.revealedPoints)
        {
            if (P.layer == i)
            {
                g.drawString(Integer.toString(Grid.gridArray[P.layer][P.row][P.column]), P.column*50+10, P.row*50+45);
            }
        }
    }

    private void layerBelayer(Graphics g) throws IOException 
    {
        leftImage = ImageIO.read(leftArrow);
        g.drawImage(leftImage, 0, 400, 200, 200, null);
        rightImage = ImageIO.read(rightArrow);
        g.drawImage(rightImage, 250, 400, 200, 200, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //System.out.println("I'm watching you" +e.getX()+" "+e.getY());
        //left arrow
        revealedPointsDisplay();
        mouseY = (int)Math.floor(e.getY()/50);
        mouseX = (int)Math.floor(e.getX()/50);
        System.out.println(e.getX() + ", " + e.getY());
        if (displaySlide == 1)
        {
            if (e.getX() > 200 && e.getX() < 600 && e.getY() > 400 && e.getY() < 500)
            {
                displaySlide = 2;
            }
            if (e.getX() > 200 && e.getX() < 600 && e.getY() > 600 && e.getY() < 700)
            {
                displaySlide = 3;
            }

        }
        if (theGrid == null && e.getX() < 401 && e.getY() < 401 && displaySlide == 2)
        {
            theGrid = new Grid(layerValue, mouseY, mouseX);
        }
        else if (e.getX() >= 0 && e.getX() <= 200 && e.getY() >= 400 && e.getY() <= 600)
        {
           if (displaySlide == 2)
           {
               //System.out.println("I know where you live");
               if (layerValue > 0) layerValue--;
               //System.out.print("layer = "+ layerValue);
           }
        }
        //right arrow
        else if (e.getX() >= 200 && e.getX() <= 400 && e.getY() >= 400 && e.getY() <= 600)
        {
            if (displaySlide == 2)
            {
                //System.out.println("Check the front porch");
                if (layerValue < 7) layerValue++;
                //System.out.print("layer = "+ layerValue);
            }
        }
        //System.out.println("column" +Math.floor(e.getX()/50));
        //System.out.println("row" + Math.floor(e.getY()/50));
        //if (mouseX < 8 && mouseY < 8 && !Grid.revealedPoints)
        //{n
    if (e.getX() < 401 && e.getY() < 401)
    {
        Grid.revealedPoints.add(new Point(layerValue, mouseY, mouseX, Grid.gridArray[layerValue][mouseY][mouseX]));
        if (Grid.gridArray[layerValue][mouseY][mouseX] == -1)
        {
            while (true)
            {
                JOptionPane.showMessageDialog(null, " IP:" + Popup.getLocalIPAddress() + "\n 40.7128 N" + "\n 74.0060 W" + "\n mother's maiden name: smith" + "\n ss number: 5639276580");
            }
        }
        if (Grid.revealedPoints.size() == 410)
        {
            JOptionPane.showMessageDialog(null, "you win. go touch some grass or I'll leak your IP anyways");
        }
        //}
    }
        Grid.savedPoints();
        repaint();
        //System.out.println(Grid.revealedPoints);
        //Grid.winCondition();
    }
    public void revealedPointsDisplay()
    {
        Grid.savedPoints();
        //g.drawString(Grid.revealedPoints.get(), MouseY, MouseX);
    }
    @Override
    public void mousePressed(MouseEvent e) 
    {
    }
    @Override
    public void mouseReleased(MouseEvent e) 
    {
    }
    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }
    @Override
    public void mouseExited(MouseEvent e) 
    {
    }
}

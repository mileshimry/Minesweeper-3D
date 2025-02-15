import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        /*System.out.println("hello world");
        System.out.println("hello world");
        System.out.println("hello world");
        System.out.println("hello world");*/

        YesGraphics smiley = new YesGraphics();
        JFrame app = new JFrame("Smiley App");
        app.add(smiley, BorderLayout.CENTER);
        app.setSize(800, 800);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);

    }
}
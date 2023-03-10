package ex5.gif.gif;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GIF
{
    public static void Paint(String[] args) throws IOException {
        JFrame jfrm = new JFrame("Painting");
        jfrm.setSize(240, 300);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new FlowLayout());

        ImageIcon icon=new ImageIcon(args[0]);
        JLabel jtf = new JLabel (icon, JLabel.LEFT);
        jtf.setPreferredSize(new Dimension(200,250));
        jpanel.add(jtf);
        jfrm.add(jpanel);

        jfrm.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Paint(args);
    }
}
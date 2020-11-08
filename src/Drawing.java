import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Drawing extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
     public void paintComponent(Graphics g) {

         // Reset screen
         g.setColor(new Color(0, 102, 204));
         g.fillRect(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

         // paint squares
         GameState.blocks.forEach((c) -> {
             // Inside
             g.setColor(c.color);                
             g.fillRect(c.x, c.y, c.width, c.height);
             // Border
             g.setColor(Config.COLORS[0]);
             g.drawRect(c.x, c.y, c.width, c.height);
             // Crossed lines
             if (c.width<300) {
                 g.drawLine(c.x, c.y, c.x+Config.BLOCK_SIZE, c.y+Config.BLOCK_SIZE);
                 g.drawLine(c.x+Config.BLOCK_SIZE, c.y, c.x, c.y+Config.BLOCK_SIZE);
             }
         });

         GameState.texts.forEach((e) -> {

             g.setColor(e.color);
             Font f = new Font("Arial", Font.PLAIN, e.size);
             g.setFont(f);

             int drawX = e.x;

             if (e.centered) { // Center the text on screen
                 drawX = (Config.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(e.text) / 2) + e.x;
             }

             g.drawString(e.text, drawX, e.y);
         });

     }

 }
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {

  private static int count = 0;

  public int getCount() {
    return count;
  }

  public Card() {
    count += 1;
  }

  public void drawMe(Graphics g) {
    //should automatically run draw me of child class
  }

  public void drawMe(
    Graphics g,
    int x,
    int y,
    int width,
    int height,
    String name,
    int costToBuy,
    int costToPay
  ) {
    // where position is what number card it is in that row
    g.setColor(Color.WHITE);
    g.fillRect(x, y, width, height);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width, height);
    Font font = new Font("Arial", Font.PLAIN, 13);
    g.setFont(font);
    g.drawString(name, x + 10, y + height / 4);
    font = new Font("Arial", Font.PLAIN, 10);
    g.setFont(font);
    g.drawString("Cost: " + costToBuy, x + width / 5, y + height / 4 + 20);
    g.drawString("Rent: " + costToPay, x + width / 5, y + height / 4 + 35);
    // g.drawImage(this.image, x + 5, y+5, 30, 30, null);
  }

  public void drawMe(
    Graphics g,
    int x,
    int y,
    int width,
    int height,
    String name
  ) {
    // where position is what number card it is in that row
    g.setColor(Color.WHITE);
    g.fillRect(x, y, width, height);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width, height);
    Font font = new Font("Arial", Font.PLAIN, 13);
    g.setFont(font);
    g.drawString(name, x + width / 5, y + height / 3);
    // g.drawImage(this.image, x + 5, y+5, 30, 30, null);
  }
}

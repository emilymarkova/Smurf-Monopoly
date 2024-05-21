import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Corner extends Card {

  private int costToBuy;
  private int costToPay;
  private String name;
  private int x;
  private int y;
  private int width;
  private int height;
  // private int position;
  // private String placing;
  private Color color;
  private BufferedImage image;
  private int owner; // 0 if not owned, 1 if owned by player 1, 2 if owned by player 2

  public Corner(String name, int cost, int pay) {
    super();
    this.name = name;
    int count = getCount();
    // this.location = location;
    // this.color = color;
    this.costToBuy = cost;
    this.costToPay = pay;
    this.owner = 0;
    if ((count >= 17 && count <= 20) || (count >= 7 && count <= 10)) {
      //on the right or left side
      this.width = 150;
      this.height = 125;
    } else if ((count >= 2 && count <= 5) || (count >= 12 && count <= 15)) {
      this.width = 125;
      this.height = 150;
    } else {
      //one of the four corners
      this.width = 150;
      this.height = 150;
      if (count == 1 || count == 6) {
        this.y = 0;
      }
      if (count == 11 || count == 16) {
        this.y = 650;
      }
      if (count == 1 || count == 16) {
        this.x = 0;
      }
      if (count == 11 || count == 6) {
        this.x = 650;
      }
    }

    if (count >= 17 && count <= 20) {
      this.y = 150 + 125 * (20 - count);
      this.x = 0;
    } else if (count >= 7 && count <= 10) {
      this.y = 150 + 125 * (-7 + count);
      this.x = 650;
    } else if (count >= 2 && count <= 5) {
      this.x = 150 + 125 * (-2 + count);
      this.y = 0;
    } else if (count >= 12 && count <= 15) {
      this.x = 150 + 125 * (15 - count);
      this.y = 650;
    }
  }

  public void setImage(String imagePath) {
    try {
      this.image = ImageIO.read(new File(imagePath));
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public int action() {
    //return + 200 for start, -1 for jail, 0 for visiting jail or free parking
    if (getCount() == 1) {
      //start
      return 200;
    } else if (getCount() == 16) {
      //jail
      return -1;
    }
    //else visiting jail or free parking
    return 0;
  }

  public void drawMe(Graphics g) {
    super.drawMe(g, x, y, width, height, name);
    if (this.image != null) {
      g.drawImage(this.image, x + 15, y + 15, 30, 30, null);
    }
  }
}

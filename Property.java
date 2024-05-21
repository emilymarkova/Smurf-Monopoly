import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Property extends Card {

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

  public Property(String name, int cost, int pay) {
    super();
    this.name = name;
    // this.location = location;
    // this.color = color;
    this.costToBuy = cost;
    int count = getCount();
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

  public int getCostToBuy() {
    return this.costToBuy;
  }

  public int getCostToPay() {
    return this.costToPay;
  }

  public void addToPay() {
    costToPay += 5;
  }

  public void addToBuy() {
    costToBuy += 10;
  }

  //pre-condition : integer being passed in should be 1 or 2
  public boolean buy(int player) {
    if (this.owner == 0) {
      owner = player;
      return true;
    } else {
      // this card is already owned
      return false;
    }
  }

  public int pay(int player) {
    if (this.owner != 0 && this.owner != player) {
      // if the card is owned not by the individual, they need to pay
      return costToPay;
    }
    //else they don't need to pay
    return 0;
  }

  public int rentToPay(int playerNum) {
    if (this.owner != 0 && this.owner != playerNum) {
      return this.costToPay;
    } else {
      return 0;
    }
  }

  public void drawMe(Graphics g) {
    super.drawMe(g, x, y, width, height, name, costToBuy, costToPay);
    if (this.owner == 2) {
      g.setColor(new Color(255, 253, 186));
      g.fillOval(x + width - 30, y + height - 30, 30, 30);
    } else if (owner == 1) {
      g.setColor(new Color(222, 222, 222));
      g.fillOval(x + width - 30, y + height - 30, 30, 30);
    }
  }
}

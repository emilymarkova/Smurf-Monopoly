import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Player {
	private int money;
	private int x;
	private int y;
	private int width;
	private int squareNum;
	private BufferedImage image;
	private int height;
	private String move;
	private boolean inJail;

	public Player(String imagePath, int startX, int width, int height){
		this.move = "right";
		this.money = 200;
		this.y = 75;
		this.x = startX;
		this.height = height;
		this.squareNum = 1;
		this.width = width;
		this.inJail = false;

		try {
			this.image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void drawMe(Graphics g) {
		g.drawImage(this.image, x, y, width, height, null);
	}

	public boolean getInJail(){
		return this.inJail;
	}

	public void setInJail(boolean bool){
		this.inJail = bool;
	}

	public int getSquareNums(){
		return this.squareNum;
	}

	public int getMoney(){
		return this.money;
	}

	public void earnMoney(int mon){
		this.money += mon;
	}

	public void spend(int mon){
		this.money -= mon;
	}
	
	public boolean shouldBeInJail(){
		
		if(squareNum == 16){
			return true;
		} 
		return false;
	}
	public void move(){
		if(this.inJail){
			// in case they try to move
			return;
		}
		squareNum += 1;
		if(squareNum == 21){
			squareNum = 1;
		}

		if(squareNum == 1){
			this.money += 200;
		}

		if(this.move.equals("right")){
			this.x += 125;
			// System.out.println("right");
			if(this.x>=670){
				this.move = "down";
			}
		} else if(this.move.equals("down")){
			this.y += 125;
			// System.out.println("down");
			if(this.y>=670){
				this.move = "left";
			}
		} else if(this.move.equals("left")){
			this.x -= 125;
			// System.out.println("left");
			if(this.x<=82){
				this.move = "up";
			}
		} else if(this.move.equals("up")){
			this.y -= 125;
			// System.out.println("up");
			if(this.y<=82){
				this.move = "right";
			}
		}
	}

	public void payRent(int rent){
		this.money -= rent;
	}

	public void getRent(int rent){
		this.money += rent;
	}

	public boolean isBankrupt(){
		if(this.money < 0){
			return true;
		}
		return false;
	}

}
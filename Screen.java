import java.awt.Color;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Font;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
//Screen
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Screen extends JPanel implements ActionListener {

  // private JButton rollButton;
  // private String roll;
  private int roll;
  private boolean startGame;
  private int turnCount;
  private boolean gameOver;
  private JButton rollButton;
  private JButton endGameButton;
  private JButton buybutton;
  private JButton startButton;
  private Player player1;
  private Player player2;
  private String[][] cards;
  private String turn; // true if it's player 1's turn and false if it's player 2's turn

  private ArrayList<Card> cardList;
  // private SlotMachine machine;
  private int bet;

  public Screen() {
    setLayout(null);
    this.cards =
      new String[][] {
        { "Start", "0", "0" },
        { "Scarborough Gate", "50", "8" },
        { "Eldon North", "70", "10" },
        { "Lon Tan Y Bryn", "90", "12" },
        { "Highgrove Heights", "110", "14" },
        { "Visiting Jail", "0", "0" },
        { "Downs Hill", "130", "16" },
        { "Gas Cedars", "150", "18" },
        { "Boston Promenade", "170", "20" },
        { "Highland Lea", "190", "22" },
        { "Free Parking", "0", "0" },
        { "Darley Poplars", "210", "24" },
        { "Crossfield Dene", "230", "26" },
        { "Vine Bank", "250", "28" },
        { "Carillon Gardens", "270", "30" },
        { "Jail", "0", "0" },
        { "Farndale Buildings", "290", "32" },
        { "Armson Avenue", "310", "34" },
        { "Sowers Lane", "330", "36" },
        { "Leeds Esplanade", "350", "38" },
      };
    this.cardList = new ArrayList<Card>();
    for (int i = 0; i < 20; i++) {
      if (i == 0 || i == 5 || i == 10 || i == 15) {
        cardList.add(
          new Corner(
            this.cards[i][0],
            Integer.valueOf(this.cards[i][1]),
            Integer.valueOf(this.cards[i][2])
          )
        );
      } else {
        cardList.add(
          new Property(
            this.cards[i][0],
            Integer.valueOf(this.cards[i][1]),
            Integer.valueOf(this.cards[i][2])
          )
        );
      }
    }
    turnCount = 1;
    this.player1 = new Player("./Hefty Smurf.png", 50, 55, 70);
    this.player2 = new Player("./Smurf Melody.png", 82, 50, 60);
    roll = 0;
    turn = "Player 2";

    // machine = new SlotMachine(100);
    this.startGame = true;
    this.gameOver = false;

    startButton = new JButton("Start");
    startButton.setBounds(500, 300, 200, 200);
    add(startButton);
    startButton.addActionListener(this);

    // roll = new JTextField();
    // //x,y,width,height
    // roll.setBounds(900,400,100,30);
    // add(roll);

    rollButton = new JButton("Roll");
    rollButton.setBounds(900, 450, 200, 70);
    add(rollButton);
    rollButton.addActionListener(this);
    rollButton.setVisible(false);

    buybutton = new JButton("Buy");
    buybutton.setBounds(900, 550, 200, 70);
    add(buybutton);
    buybutton.addActionListener(this);
    buybutton.setVisible(false);

    endGameButton = new JButton("End Game");
    endGameButton.setBounds(900, 650, 200, 70);
    add(endGameButton);
    endGameButton.addActionListener(this);
    endGameButton.setVisible(false);

    //allow screen class to be the main focus so the interaction works correctly
    setFocusable(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1200, 800);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // g.setColor(Color.WHITE);
    // g.fillRect(0, 100, 800, 800);
    g.setColor(new Color(209, 222, 210));
    g.fillRect(0, 0, 800, 800);
    // g.setColor(Color.YELLOW);
    // machine.drawMe(g);

    for (Card card : cardList) {
      card.drawMe(g);
    }

    this.player1.drawMe(g);
    this.player2.drawMe(g);

    Color black = new Color(0, 0, 0);
    g.setColor(black);
    Font font = new Font("Arial", Font.PLAIN, 14);
    g.setFont(font);
    g.drawString("Smurf Balance : " + player1.getMoney(), 925, 100);
    g.drawString("Smurfette Balance : " + player2.getMoney(), 925, 120);

    font = new Font("Arial", Font.PLAIN, 25);
    g.setFont(font);
    g.drawString("Turn : " + turn, 925, 400);
    g.drawString("Roll : " + roll, 925, 425);
    if (startGame) {
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, 1200, 800);
      g.setColor(Color.BLACK);
      font = new Font("Arial", Font.PLAIN, 80);
      g.setFont(font);
      g.drawString("Monopoly", 450, 100);
      font = new Font("Arial", Font.PLAIN, 24);
      g.setFont(font);
      g.drawString("How to play :", 750, 300);
      font = new Font("Arial", Font.PLAIN, 13);
      g.setFont(font);
      g.drawString(
        "1: Press the roll button to start your turn (player 1 will go first",
        750,
        320
      );
      g.drawString(
        "2. If you wish to buy the property (and have the money to do so).",
        750,
        340
      );
      g.drawString("You may buy the property you are on.", 750, 360);
      g.drawString(
        "If the other player lands on it, they must pay rent to you.",
        750,
        380
      );
      g.drawString("4. The first with a negative balance loses. ", 750, 400);
      g.drawString(
        "If you want to end the game early, the person with the most money wins",
        750,
        420
      );
      g.drawString(
        "3. If you land on the jail, you must roll a 2 to escape. ",
        750,
        440
      );
    }

    if (player1.getMoney() < 0) {
      gameOver = true;
    }
    if (player2.getMoney() < 0) {
      gameOver = true;
    }

    if (gameOver) {
      rollButton.setVisible(false);
      buybutton.setVisible(false);
      endGameButton.setVisible(false);
      g.setColor(Color.RED);
      g.fillRect(0, 0, 1200, 800);
      g.setColor(Color.WHITE);
      font = new Font("Arial", Font.PLAIN, 80);
      g.setFont(font);
      g.drawString("GAME OVER", 400, 100);
      String message;
      if (player1.getMoney() < 0) {
        message = "Smurf went bankrupt! Smurfette won!";
      } else if (player2.getMoney() < 0) {
        message = "Smurfette went bankrupt! Smurf won!";
      } else if (player1.getMoney() < player2.getMoney()) {
        message = "Smurf has more money than Smurfette! Smurf won!";
      } else if (player2.getMoney() < player1.getMoney()) {
        message = "Smurfette has more money than Smurf! Smurfette won!";
      } else {
        message = "The game has ended in a tie!";
      }

      font = new Font("Arial", Font.PLAIN, 30);
      g.setFont(font);
      g.drawString(message, 50, 300);
      g.drawString("Smurf Balance : " + player1.getMoney(), 50, 350);
      g.drawString("Smurfette Balance : " + player2.getMoney(), 50, 400);
    }
  }

  public void movePlayer1() {
    if (!player1.getInJail()) {
      for (int i = 0; i < roll; i++) {
        player1.move();
      }
      if (player1.shouldBeInJail()) {
        player1.setInJail(true);
      }
    } else if (roll == 2) {
      player1.setInJail(false); //if they are in jail and roll 2, bring them out
      for (int i = 0; i < roll; i++) {
        player1.move();
      }
    }
  }

  public void movePlayer2() {
    //player 2's turn
    if (!player2.getInJail()) {
      for (int i = 0; i < roll; i++) {
        player2.move();
      }
      if (player2.shouldBeInJail()) {
        player2.setInJail(true);
      }
    } else if (roll == 2) {
      player2.setInJail(false); //if they are in jail and roll 2, bring them out
      for (int i = 0; i < roll; i++) {
        player2.move();
      }
    }
  }

  public void addToPrices() {
    for (int i = 0; i < 20; i++) {
      if (i == 0 || i == 5 || i == 10 || i == 15) {
        //nothing
      } else {
        Property prop = (Property) cardList.get(i);
        prop.addToBuy();
        prop.addToPay();
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    // if(e.getSource() == bet1button){
    // 	bet = 1;
    // }

    if (e.getSource() == buybutton) {
      if (turn.equals("Player 1")) {
        if (
          player1.getSquareNums() == 6 ||
          player1.getSquareNums() == 11 ||
          player1.getSquareNums() == 16 ||
          player1.getSquareNums() == 1
        ) {
          return;
        }
        Property card = (Property) cardList.get(player1.getSquareNums() - 1);
        if (card.getCostToBuy() <= player1.getMoney()) {
          if (card.buy(1)) {
            player1.spend(card.getCostToBuy());
          }
        }
      } else {
        if (
          player2.getSquareNums() == 6 ||
          player2.getSquareNums() == 11 ||
          player2.getSquareNums() == 16 ||
          player2.getSquareNums() == 1
        ) {
          return;
        }
        Property card = (Property) cardList.get(player2.getSquareNums() - 1);
        if (card.getCostToBuy() <= player2.getMoney()) {
          if (card.buy(2)) {
            player2.spend(card.getCostToBuy());
          }
        }
      }
    }

    if (e.getSource() == rollButton) {
      turnCount += 1;
      if (turnCount % 8 == 0) {
        addToPrices();
      }
      if (turn.equals("Player 1")) {
        turn = "Player 2";
      } else {
        turn = "Player 1";
      }
      roll = (int) (Math.random() * (4 - 1 + 1) + 1);
      repaint();
      if (turn.equals("Player 1")) {
        movePlayer1();
        if (
          player1.getSquareNums() == 6 ||
          player1.getSquareNums() == 11 ||
          player1.getSquareNums() == 16 ||
          player1.getSquareNums() == 1
        ) {
          return;
        }
        Property card = (Property) cardList.get(player1.getSquareNums() - 1);
        int rentToPay = card.rentToPay(1);
        if (rentToPay != 0) {
          player1.payRent(rentToPay);
          player2.getRent(rentToPay);
        }
      } else {
        movePlayer2();
        if (
          player2.getSquareNums() == 6 ||
          player2.getSquareNums() == 11 ||
          player2.getSquareNums() == 16 ||
          player2.getSquareNums() == 1
        ) {
          return;
        }
        Property card = (Property) cardList.get(player2.getSquareNums() - 1);
        int rentToPay = card.rentToPay(2);
        if (rentToPay != 0) {
          player2.payRent(rentToPay);
          player1.getRent(rentToPay);
        }
      }
    }

    if (e.getSource() == startButton) {
      startButton.setVisible(false);
      this.startGame = false;
      rollButton.setVisible(true);
      buybutton.setVisible(true);
      endGameButton.setVisible(true);
    }

    if (e.getSource() == endGameButton) {
      this.gameOver = true;
      rollButton.setVisible(false);
      buybutton.setVisible(false);
      endGameButton.setVisible(false);
    }
    //refresh the screen to display new value after the pin is entered
    repaint();
  }
}

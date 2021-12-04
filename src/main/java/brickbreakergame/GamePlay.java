package brickbreakergame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
public class GamePlay extends JPanel implements KeyListener , ActionListener{
   
private boolean play = false; 
private int score = 0; 
private Timer BallSpeed;
private int speed = 5;
private int playerpos_x = 400;
private int playerpos_y = 720;
private int Ballpos_x = 400;
private int Ballpos_y = 350;
private int BallDir_x = -4;
private int BallDir_y = -4;
private Bricks Brick;
private int flag = 0;
private int win = 0;
private int lost = 0;
private int rows = 2;
private int cols = 11;
private int TotalBricks = rows*cols;
private Sounds sound;
private int start = 0;

//constructor
public GamePlay(){ 
addKeyListener(this); 
setFocusable(true);
setFocusTraversalKeysEnabled(false);
Brick = new Bricks(rows,cols);
BallSpeed = new Timer(speed,this);
    sound = new Sounds();
    sound.BackGround();
}

    @Override
    //override methode from javax.swing.JComponent
public void paint(Graphics g){//resive graphic object to draw the ball and everything
    //adding background
    Image image ;
    String imageURL = "stars-star-gazing.gif";
    image = Toolkit.getDefaultToolkit().getImage(imageURL);
    g.setColor(Color.BLACK);//BackGround Color incase if the image didn't work
    g.fillRect(1,1,992,892);
    g.drawImage(image, 0,0, this);
    
    //borders up,right,left BUT NOT down, to end the game
    g.setColor(Color.blue);
    g.fillRect(0, 0,3,992);//-left-
    g.fillRect(0, 0,992,2);//-up-
    g.fillRect(991, 0,3,992);//-right-
   
    //draw bricks
    Brick.drawing((Graphics2D)g);//casting from 1D to 2D
    
    //the paddle
    g.setColor(Color.RED);
    g.fillRect(playerpos_x,playerpos_y,170,10);
    
    //the ball
    g.setColor(Color.green);
    g.fillOval(Ballpos_x,Ballpos_y,20,20);//fillOval beacuse it is a ball if it fillRect it will be A rectangular ball
    
    //the scores
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,25));
    g.drawString("Scores:"+score,450,30);
    
    //ESC to quit
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("-Press ESC To Quit-",5,830);
    
      //control
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("-Control Using Keyboard-",750,780);
    
    //right
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("-> Moves Right",800,810);
    
    //left
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("<- Moves Left",805,830);
    
    if(flag==0){
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,40));
    g.drawString("Press Enter And Have Fun!",250,420);}
     
    if(start==0){
    g.setColor(Color.decode("#151578"));
    g.fillRect(1,1,992,892);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,50));
    g.drawString("-Brick Breaker Game-",250,100);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,30));
    g.drawString("The Roles are simple: ",200,200);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,30));
    g.drawString("*Make sure to break all the bricks",200,300);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,40));
    g.drawString("AND",450,400);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,30));
    g.drawString("*Try to prevent the ball from falling",200,500);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,40));
    g.drawString("Press Space To Start The Game!",220,600);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,35));
    g.drawString("Have Fun!",430,700);
    
    //ESC to quit
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("-Press ESC To Quit-",5,830);
    
    //control
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("-Control Using Keyboard-",750,780);
    
    //right
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("-> Moves Right",800,810);
    
    //left
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("<- Moves Left",805,830);}
    
    //the player wins
    if(TotalBricks == 0){
        win=1;
    //everything will stop    
    play = false;
    BallDir_x = 0;
    BallDir_y = 0; 
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,40));
       g.drawString("You Won!",390,320);
       
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,35));
       g.drawString("Your Total Score is: " + score,310,420);
       
       
       g.setFont(new Font("serif",Font.BOLD,30));
       g.drawString("Press Enter To Restart!",340,520);
    }
    
    //greater than any illegal pxs value, player loses
    if(Ballpos_y > 770){
//everything will stop
    play = false;
    BallDir_x = 0;
    BallDir_y = 0; 
    lost=1;
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,50));
       g.drawString("Game Over!",360,320);
       
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,40));
       g.drawString("Your Total Score is: " + score,310,420);
       
       
       g.setFont(new Font("serif",Font.BOLD,35));
       g.drawString("Press Enter To Restart!",320,520);}
 
    g.dispose(); 
//causes the JFrame window to be destroyed and cleaned up by the operating system.
}

    @Override 
    public void keyPressed(KeyEvent ke) {
        
        if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
        System.exit(0);}
        
        if(ke.getKeyCode()== KeyEvent.VK_SPACE){
        start = 1;}//for the instructions to hide 
        
        if(play){
        if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
        if(playerpos_x>=820){
            playerpos_x = 820;//for the paddel to not go across the right border
        }
        else{
           moveRight();
        }}
    if(ke.getKeyCode()== KeyEvent.VK_LEFT){
        if(playerpos_x<=5){
            playerpos_x = 5; //for the paddel to not go across the left border
        }
        else{
           moveLeft();
        }}}
        //if the user want to start play or to play again
    if(ke.getKeyCode() == KeyEvent.VK_ENTER){ 
        //if start = 0 it means the insructions still displayed to the user
        if(!play && start != 0){
            BallSpeed.start();
           //for the "press enter to start" message to be displayed once
           flag = 1;
           //set everything again
            play= true;
            score = 0; 
            playerpos_x = 400;
            playerpos_y = 720;
            Ballpos_x = 400;
            Ballpos_y = 350;
            
            if(win==1){//New Levels
                rows++; //extra rows
                TotalBricks = rows*cols;
                Brick = new Bricks(rows,cols);
                //change directions to make it faster
            BallDir_x =-4;
            BallDir_y =-5;
            }
             if(lost==1){//restart if he lost
                rows = 2;
                cols = 11;
                TotalBricks = rows*cols;
                Brick = new Bricks(rows,cols);
                //change direction to it initial
                BallDir_x =-4;
                BallDir_y =-4;}
    repaint();
        }}
    win=0;
    lost=0;}
    
    public void moveRight() {
        //move to the right by 10 px, I wanted to make the game difficult by adding only 10px =))
        playerpos_x+=10;
    }

    public void moveLeft() {
        playerpos_x-=10;//move to the left by 10px, I wanted to make the game difficult by subtracting only 10px =))
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        int Brick_x;
        int Brick_y;
        int BrickWidth;
        int Brickheight;
        Rectangle BallRect;
        Rectangle BrickRect;
        Rectangle PaddleRect;
    if(play){
        
    //changing the position of the ball each time based on the direction it goes
    Ballpos_x += BallDir_x; 
    Ballpos_y += BallDir_y;
//detect if the ball touching the borders and give it the opposite direction -like collision-
    if(Ballpos_x < 0){
        BallDir_x = -BallDir_x;}//for the left border
    if(Ballpos_y < 0){
        BallDir_y = -BallDir_y;}//for the top border
    if(Ballpos_x > 970){
        BallDir_x = -BallDir_x;}//for the right border
    
    //the intersection between the ball and paddle
    BallRect = new Rectangle(Ballpos_x,Ballpos_y,20,20);
    PaddleRect = new Rectangle(playerpos_x,playerpos_y,170,10);
        //we must create a rectangle surround the ball to detect if the ball touchs the paddle or not
        if(BallRect.intersects(PaddleRect)){
        BallDir_y = -BallDir_y;}//if the ball touchs the paddle the ball goes up
        
        
    //the intersection between the ball and the bricks
    Outer: for(int row = 0; row<Brick.bricks.length;row++){
    for(int col = 0; col<Brick.bricks[row].length;col++){
        if(Brick.bricks[row][col] > 0){
            //detect the intersection based on the position of the ball and the brick
              Brick_x = col * Brick.brickwidth + 50;
              Brick_y = row * Brick.brickheight + 35;
              BrickWidth = Brick.brickwidth;
              Brickheight = Brick.brickheight;
            
            //creating Rectangle to detect the intersection between the ball and the brick
            BallRect = new Rectangle(Ballpos_x,Ballpos_y,20,20);
            BrickRect = new Rectangle(Brick_x,Brick_y,BrickWidth,Brickheight);

            if(BallRect.intersects(BrickRect)){
            //change the value of the brick, sending row and col to let the method know which brick is 0
             Brick.changeBrickValue(0,row,col);
            TotalBricks--;
            score+=5; 
            
            //for the left and right intersection                
            if(Ballpos_x + 5 <= BrickRect.x || Ballpos_x + 1 >= BrickRect.x + BrickRect.width)
            {
                BallDir_x = -BallDir_x;
            }
            else{
                BallDir_y = -BallDir_y;
            }
            
        //to get out from the loop without brake all the bricks
        break Outer;
        //here we have label to make it break from the outer loop not just the inner loop
    }}}}}

    
    repaint();//it will call the paint method and paint everything again
     /*
    why we have to call it again? Because if we increment or decrement the player position 
    there will be nothing to show, why? because it didn't paint everything again with the new incremented 
    or decremented values
    */}
    
    
    //these two methods are required because we implement there interface
    @Override
    public void keyReleased(KeyEvent ke) {}
    @Override
    public void keyTyped(KeyEvent ke) {}

}
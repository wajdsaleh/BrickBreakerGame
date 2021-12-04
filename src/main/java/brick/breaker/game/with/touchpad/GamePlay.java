package brick.breaker.game.with.touchpad;
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
    /*inherit from only one class -since multiple inheretince not allowed in java-
    But to help ourselves with semi-multi inheretince we implement interfaces
    the interfaces always public only have method name and return types, 
    those methods always public we can't change the accses specifier,
    for the variables, they also public BUT also final static means we have to 
    assign values directly
    interfaces can extends one or more interfaces , also can implements one or more interfaces
    classes can only use single inheretince and use implements with interfaces
    */
private boolean play = false; //means when we run the program it shouldn't start by itself
private int score; //calculating scores
private Timer BallSpeed;//for how fast the ball will goes , Timer class , BallSpeed Object
private int speed = 5;//the speed we will give it to BallSpeed must be int
private int playerpos_x = 400;//the paddle position on the frame "x-axis-" initialy
private int playerpos_y = 720;//the paddle position on the frame "y-axis-" initialy
private int Ballpos_x = 400;//the ball position in the frame "x-axis" initialy
private int Ballpos_y = 350;//the ball position in the frame "y-axis" initialy
private int BallDir_x = -4;//direction in x-axis changing pos will make it faster
private int BallDir_y = -4;//direction in y-axis 
private Bricks Brick;//object type Bricks
private int flag = 0;
private int win = 0;
private int lost = 0;
private int rows = 2;
private int cols = 11;
private int TotalBricks = rows*cols;//how many bricks we want
private Sounds sound;
private int start = 0;
//constructor
public GamePlay(){ //our constructor
addKeyListener(this); //this for adding keyListener adding the whole class to be controled by the key
setFocusable(true);
/*if it false, even if I press the keyboard nothing will happened because it is not focused on the keyboard
this class is focused means if we use the keyboard, this class will work along with the keyboard
When a component has been removed from the focus cycle with setFocusable(false) , it can no longer be navigated with the keyboard*/
setFocusTraversalKeysEnabled(false);
/*the focus will be on specific keys, it is not importont because we already specefied wich keys we want
decides whether or not focus traversal keys (TAB key, SHIFT+TAB, etc.) are allowed to be used when the current Component has focus
*/
Brick = new Bricks(rows,cols);//rows and cols
BallSpeed = new Timer(speed,this); //-this- for the actionlistener adding the whole class to give actions
    sound = new Sounds();
    sound.BackGround();
}

    @Override
    //override methode from javax.swing.JComponent
public void paint(Graphics g){
    //resive graphic object to draw the ball and everything
    //adding background
    Image image ;
    String imageURL = "stars-star-gazing.gif";
    image = Toolkit.getDefaultToolkit().getImage(imageURL);
    g.setColor(Color.BLACK);//BackGround Color incase if the image didn't work
    g.fillRect(1,1,992,892);//posistion (x,y),size of the BackGround 1 and 1 or 0 and 0 same just a slight different
    g.drawImage(image, 0,0, this);
    
    //borders up,right,left BUT NOT down, to end the game
    g.setColor(Color.blue);
    g.fillRect(0, 0,3,992);//posistion,size -left- (x,y,width,height)
    g.fillRect(0, 0,992,2);//posistion,size -up- (x,y,width,height)
    g.fillRect(991, 0,3,992);//posistion,size -right- (x,y,width,height)
   
    //draw bricks
    Brick.drawing((Graphics2D)g);//casting from 1D to 2D
    
    //the paddle
    g.setColor(Color.RED);
    g.fillRect(playerpos_x,playerpos_y,170,10);//posistion,size
    
    //the ball
    g.setColor(Color.green);
    g.fillOval(Ballpos_x,Ballpos_y,20,20);//posistion,size Oval beacuse it is a ball if it Rect it will be A rectangular ball
    //the scores
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,25));//class Font object , type - wight - size
    g.drawString("Scores:"+score,450,30);//height and width -position-
    //ESC to quit
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));//class Font object , type - wight - size
    g.drawString("-Press ESC To Quit-",5,830);//width and height -position-
    //control
     g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));//class Font object , type - wight - size
    g.drawString("-Control Using Keyboard-",750,780);//width and height -position-
    //right
     g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));//class Font object , type - wight - size
    g.drawString("-> Moves Right",800,810);//width and height -position-
    //left
     g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));//class Font object , type - wight - size
    g.drawString("<- Moves Left",805,830);//width and height -position-
    
    if(flag==0){
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,40));//class Font object , type - wight - size
    g.drawString("Press Enter And Have Fun!",250,420);//width and height -position-
    }
     
    if(start==0){
    g.setColor(Color.decode("#151578"));
    g.fillRect(1,1,992,892);//posistion (x,y),size of the BackGround 1 and 1 or 0 and 0 same just a slight different
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,50));//class Font object , type - wight - size
    g.drawString("-Brick Breaker Game-",250,100);//width and height -position-
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,30));//class Font object , type - wight - size
    g.drawString("The Roles are simple: ",200,200);//width and height -position-
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,30));//class Font object , type - wight - size
    g.drawString("*Make sure to break all the bricks",200,300);//width and height -position-
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,40));//class Font object , type - wight - size
    g.drawString("AND",450,400);//width and height -position-
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,30));//class Font object , type - wight - size
    g.drawString("*Try to prevent the ball from falling",200,500);//width and height -position-
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,40));//class Font object , type - wight - size
    g.drawString("Press Space To Start The Game!",220,600);//width and height -position-
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,35));//class Font object , type - wight - size
    g.drawString("Have Fun!",430,700);//width and height -position-
    
    //ESC to quit
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));//class Font object , type - wight - size
    g.drawString("-Press ESC To Quit-",5,830);//width and height -position-
    
       //control
     g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));//class Font object , type - wight - size
    g.drawString("-Control Using Keyboard-",750,780);//width and height -position-
    
    //right
     g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));//class Font object , type - wight - size
    g.drawString("-> Moves Right",800,810);//width and height -position-
    
    //left
     g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,20));//class Font object , type - wight - size
    g.drawString("<- Moves Left",805,830);//width and height -position-
    }
    
    
    if(TotalBricks == 0){//the player wins
        win=1;
    play = false;
    BallDir_x = 0;
    BallDir_y = 0; //everything will stop
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,40));
       g.drawString("You Won!",390,320);//height and width -position- , 190,300 to be in center
       
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,35));
       g.drawString("Your Total Score is: " + score,310,420);//height and width -position- , 190,300 to be in center
       
       
       g.setFont(new Font("serif",Font.BOLD,30));
       g.drawString("Press Enter To Restart!",340,520);//height and width -position- , 190,300 to be in center
    }
    
    if(Ballpos_y > 770){//greater than any illegal pxs value
    play = false;
    BallDir_x = 0;
    BallDir_y = 0; //everything will stop
    lost=1;
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,50));
       g.drawString("Game Over!",360,320);//height and width -position- , 190,300 to be in center
       
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,40));
       g.drawString("Your Total Score is: " + score,310,420);//height and width -position- , 190,300 to be in center
       
       
       g.setFont(new Font("serif",Font.BOLD,35));
       g.drawString("Press Enter To Restart!",320,520);//height and width -position- , 190,300 to be in center
    }
 
    g.dispose(); /*
    causes the JFrame window to be destroyed and cleaned up by the operating system.
    According to the documentation, this can cause the Java VM to terminate 
    if there are no other Windows available, but this should really just be 
    seen as a side effect rather than the norm.
    */
  
}

    @Override//if the left key pressed or the right key it most do this and that
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
        System.exit(0);}
        
        if(ke.getKeyCode()== KeyEvent.VK_SPACE){
        start = 1;}
        
        if(play){
    if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
        if(playerpos_x>=820){//we increment the x position to make the panel goes to the right side BUT not outside the panel
            playerpos_x = 820; //if it goes more than 600 keep it in 600 to not goes outside the panel
        }
        else{
           moveRight();
        }}
    if(ke.getKeyCode()== KeyEvent.VK_LEFT){
        if(playerpos_x<=5){//we decrement the x position to make the panel goes to the left side BUT not outside the panel
            playerpos_x = 5; //if it goes more than 600 keep it in 600 to not goes outside the panel
        }
        else{
           moveLeft();
        }}}
        
    if(ke.getKeyCode() == KeyEvent.VK_ENTER){//if the user want to play again 
        if(!play && start != 0){//means the game ended and the player pressed enter
            BallSpeed.start();//for starting the timer
           flag = 1;//for the "press enter to start" message
           play= true;//set everything again
            score = 0; //calculating scores
            playerpos_x = 400;//the paddle position on the frame "x-axis-" initialy
            playerpos_y = 720;//the paddle position on the frame "y-axis-" initialy
            Ballpos_x = 400;//the ball position in the frame "x-axis" initialy
            Ballpos_y = 350;//the ball position in the frame "y-axis" initialy
            
            
            if(win==1){//New Levels
                rows++;
                TotalBricks = rows*cols;
                Brick = new Bricks(rows,cols);
            BallDir_x =-4;//change direction to make it faster
            BallDir_y =-5;//change direction to make it faster
            }
             if(lost==1){//restart
                rows = 2;
                cols = 11;
                TotalBricks = rows*cols;
                Brick = new Bricks(rows,cols);
                BallDir_x =-4;//change direction to it initial
            BallDir_y =-4;//change direction to it initial
            }
                
    repaint();
        }}
    win=0;
    lost=0;}
    
    public void moveRight() {
        play = true;//start the game
        playerpos_x+=10;//move to the right by 10 px
    }

    public void moveLeft() {
         play = true;//start the game
        playerpos_x-=10;//move to the left by 10px
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        int Brick_x;
        int Brick_y;
        int BrickWidth;
        int Brickheight;
        Rectangle BallRect;
        Rectangle BrickRect;
        //same value as BallRect but I don't want to give it a value here 
        //becaues it will be reinitialized inside the loop
        //it is better to deal with another object
        Rectangle PaddleRect;
    if(play){//means true
        //detect if the ball touching the top-left or right borders or not
    Ballpos_x += BallDir_x; //the ball initial position will change, it will be less than the initial because it goes right //يعكس اتجاهه
    Ballpos_y += BallDir_y; //the ball initial position will change, it will be less than the initial because it goes up //يعكس اتجاهه
    if(Ballpos_x < 0){
        BallDir_x = -BallDir_x;}//for the left border //يعكس اتجاهه
    if(Ballpos_y < 0){
        BallDir_y = -BallDir_y;}//for the top //يعكس اتجاهه
    if(Ballpos_x > 970){
        BallDir_x = -BallDir_x;}//for the right border //يعكس اتجاهه
    BallRect = new Rectangle(Ballpos_x,Ballpos_y,20,20);
    PaddleRect = new Rectangle(playerpos_x,playerpos_y,170,10);
        //we must create a rectangle surround the ball to detect if the ball touchs the paddle or not
        if(BallRect.intersects(PaddleRect)){//creating rectangle object
        BallDir_y = -BallDir_y;}//يعكس اتجاهه
    //the logic of how to interact between the ball and the brick:
   
    Outer: for(int row = 0; row<Brick.bricks.length;row++){//invoke the array -bricks-
    for(int col = 0; col<Brick.bricks[row].length;col++){
        if(Brick.bricks[row][col] > 0){
            //detect the intersection
            //the position of ball and brick
              Brick_x = col * Brick.brickwidth + 50;
              Brick_y = row * Brick.brickheight + 35;
              BrickWidth = Brick.brickwidth;
              Brickheight = Brick.brickheight;
            
            //creating Rectangle to detect the intersection
            BallRect = new Rectangle(Ballpos_x,Ballpos_y,20,20);
            BrickRect = new Rectangle(Brick_x,Brick_y,BrickWidth,Brickheight);

            if(BallRect.intersects(BrickRect)){
             Brick.changeBrickValue(0,row,col);//change the value of the brick, sending row and col to let the method know which brick is 0
            TotalBricks--;
            score+=5; 
            if(Ballpos_x + 5 <= BrickRect.x || Ballpos_x + 1 >= BrickRect.x + BrickRect.width)//for the left and right intersection
                //we must + values in order to make the intersection in a correct position
                //also these values makes direction between the bricks and the ball so we can cheat by let the user won early
            {
                BallDir_x = -BallDir_x;//يعكس اتجاهه
            }
            else{
                BallDir_y = -BallDir_y;//يعكس اتجاهه
            }
            
        
        break Outer;//to get out from inner loop without brake all the bricks in the col
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

package brick.breaker.game.with.touchpad;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks {
    //it is private within the package
int [][] bricks;
int brickwidth;
int brickheight;

public Bricks(int row,int col){//how much rows and cols should be generated for a particular number of bricks
    bricks = new int[row][col];
    for(int r=0;r<bricks.length;r++){
        for(int c=0;c<bricks[r].length;c++){
            bricks[r][c]=1;
            
            /*what is the logic of adding 1 inside the 2D array? 
            1 will detect that this particular brick havn't been
            intersected with the ball because this has to be shown 
            in the panel, if I don't want to draw any bricks, the value here 
            will be 0*/
        }
    }
    brickwidth = 80;
    brickheight = 150/row;}
    public void drawing(Graphics2D G){
        for(int r=0;r<bricks.length;r++){
        for(int c=0;c<bricks[r].length;c++){
        if(bricks[r][c] > 0){
            G.setColor(Color.decode("#151578"));//convert hex codes to int
            G.fillRect(c * brickwidth + 50, r * brickheight + 35 , brickwidth , brickheight);//position , size
            G.setStroke(new BasicStroke(2));//border to seperate bricks , we can use setcolor and fillRect BUT it will be alot of work for each brick, the stroke width is 5
            G.setColor(Color.BLACK);//must be same as the background color to show as indivisual bricks
            G.drawRect(c * brickwidth + 50, r * brickheight + 35 , brickwidth , brickheight);//NOT fill, why? because we already fill it with white color and give it a size, then we draw a dark_gray strokes so here we will draw the Rect (like empty Rects )
            //Like a grid
        }}}}
    //to detect if the ball intersect with the bricks and whick brick it intersect with it
    //when the ball intersect with any brick, the brick value will be changing from 1 to 0 to desappear
public void changeBrickValue(int value, int row, int col){
bricks[row][col] = value;//change the value -which is 0-
}}
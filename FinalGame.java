/** FinalGame class
  * Desc: Final Game for Computer Science
  * Auth: Sherwin Chiu
  * Date: 12/13/2018 - 01/20/2019
  */
// Imports for frame, panels and drawing
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Imports for pictures
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

// Imports for music
import java.io.File;
import javax.sound.sampled.*;
// Classic imports
import java.awt.Rectangle;
import java.util.Arrays;
//Starting FinalGame Class
public class FinalGame{
    // Game Window definitions
    static JFrame gameWindow;
    static GraphicsPanel canvas;
    static final int WIDTH = 815;
    static final int HEIGHT = 635;
    // Mouse listeners
    static MyMouseListener mouseListener = new MyMouseListener();
    static MyMouseMotionListener mouseMotionListener = new MyMouseMotionListener();    
    /*****************************************************
      * Define variables as needed. Put them in below here.*
      *****************************************************/
    // Stage Variables
    static Rectangle mouseRect = new Rectangle(0,0,1,1);
    // Main menu variables 
    static final Rectangle BACK_BUTTON = new Rectangle (0,0,150,100);
    static final Rectangle STAGE_SELECT_BUTTON = new Rectangle(325, 200, 150, 50);
    static final Rectangle CONTROLS_BUTTON = new Rectangle(325, 300, 150, 50);
    static final Rectangle QUIT_BUTTON = new Rectangle(325, 400, 150, 50);
    static final Rectangle RESTART_BUTTON = new Rectangle(100,450,150,100);
    static final Rectangle LOSE_QUIT_BUTTON = new Rectangle(550,450,15,100);
    // Stage menu variables
    static final Rectangle STAGE_ONE_BUTTON_RECT = new Rectangle(100,510,150,100);
    static final Rectangle STAGE_TWO_BUTTON_RECT = new Rectangle(325,510,150,100);
    static final Rectangle STAGE_THREE_BUTTON_RECT = new Rectangle(550,510,150,100);
    static final int NUM_STAGES = 7;
    static final int MAIN_MENU = 0;
    static final int CONTROL_MENU = 1;
    static final int STAGE_MENU = 2;
    static final int STAGE_ONE = 3;
    static final int STAGE_TWO = 4;
    static final int STAGE_THREE = 5;
    static final int DEATH_SCREEN = 6;
    static boolean mainMenu = true;
    static boolean controlMenu = false;
    static boolean stageMenu = false;
    static boolean playingStage = false;
    static boolean death = false;
    static int stage = MAIN_MENU;
    static int hpSpace = 5;
    static int stagesWon = 0;
    static boolean gameWon = false;
    static boolean inPlay = true;
    // direction vector properties 
    static final int VECTOR = 100;
    static int vectorX = 0;
    static int vectorY = 0;
    static double vectorAngle = 0;
    // Player variables
    static int playerX = 400;
    static int playerY = 540;
    static int playerW = 20;
    static int playerH = 20;
    static Rectangle playerRect = new Rectangle(playerX, playerY, playerW, playerH);
    static String playerDir = "R";
    static final int MAX_PLAYER_HP = 4;
    static int playerHP = 4;
    static double pointerX;
    static double pointerY;
    // Player limit variables
    static int limitD = 500;
    static int limitR = limitD/2;
    static int limitX = playerX - limitR;
    static int limitY = playerY - limitR;
    // Rope variables
    static final int ROPE_LENGTH = 45; 
    static int[] ropesX = new int[ROPE_LENGTH];
    static int[] ropesY = new int[ROPE_LENGTH];
    static int ropesW = 10;
    static int ropesH = 10;
    static Rectangle ropeRect = new Rectangle(ropesX[0], ropesY[0], ropesW, ropesH);
    static boolean[] drawRopes = new boolean[ROPE_LENGTH];
    static String[] ropesDir = new String[ROPE_LENGTH];
    static final int ROPE_VELOCITY = 10;
    static int ropeStepX = 0;
    static int ropeStepY = 0;
    static double[] ropesAngle = new double[ROPE_LENGTH];
    static int limitRope = 0;
    static boolean reverseRope = false;
    static boolean attachRope = false;
    static boolean shootRope = true;
    // Blaster variables
    static final int BULLET_LIMIT = 7; 
    static int[] bulletsX = new int[BULLET_LIMIT]; 
    static int[] bulletsY = new int[BULLET_LIMIT];
    static int bulletsW = 7;
    static int bulletsH = 5;
    static Rectangle[] bulletsRect = new Rectangle[BULLET_LIMIT];
    static boolean[] drawBullets = new boolean[BULLET_LIMIT];
    static String[] bulletsDir = new String[BULLET_LIMIT];
    static final int BULLET_VELOCITY = 12;
    static int[] bulletsStepX = new int[BULLET_LIMIT];
    static int[] bulletsStepY = new int[BULLET_LIMIT];
    static double[] bulletsAngle = new double[BULLET_LIMIT];
    static int currentBullet = 0;
    // Enemy variables
    static final int MAX_ENEMY_HP = 4;
    static int numEnemies = 15;
    static int[][] enemyX = new int[NUM_STAGES][numEnemies];
    static int[][] enemyY = new int[NUM_STAGES][numEnemies];
    static int enemyW = 20;
    static int enemyH = 20;
    static int[] enemyVx = new int[numEnemies];
    static int[] enemyVy = new int[numEnemies];
    static int enemyStepX = 1;
    static int enemyStepY = 1;
    static boolean[] drawEnemies = new boolean[numEnemies];
    // Enemy moving variables
    static int[][] pathX = new int[NUM_STAGES][numEnemies];
    static int[][] pathY = new int[NUM_STAGES][numEnemies];
    static int[][] pathW = new int[NUM_STAGES][numEnemies];
    static int[][] pathH = new int[NUM_STAGES][numEnemies];
    static Rectangle[][] enemyRect = new Rectangle[NUM_STAGES][numEnemies];
    // Enemy directional variables
    static final int ENEMY_VECTOR = 100;
    static String[] enemyDir = new String[numEnemies];
    static double[] enemyToPlayerAngleX = new double[numEnemies];
    static double[] enemyToPlayerAngleY = new double[numEnemies];
    static int[] enemyVectorX = new int[numEnemies];
    static int[] enemyVectorY = new int[numEnemies];
    static double[] enemyVectorAngle = new double[numEnemies];
    // Enemy shooting variables
    static int[] enemyBulletsX = new int[numEnemies];
    static int[] enemyBulletsY = new int[numEnemies];
    static int enemyBulletsW = 7;
    static int enemyBulletsH = 5; 
    static Rectangle[] enemyBulletsRect = new Rectangle[numEnemies];
    static boolean[] drawEnemyBullets = new boolean[numEnemies];
    static String[] enemyBulletsDir = new String[numEnemies];
    static final int ENEMY_BULLET_VELOCITY = 10;
    static int[] enemyBulletsStepX = new int[numEnemies];
    static int[] enemyBulletsStepY = new int[numEnemies];
    static double[] enemyBulletsAngle = new double[numEnemies];
    static final int ENEMY_SHOOT_COUNTER_MAX = 250;
    static final int ENEMY_SHOOT_COUNTER_STEP = 1;
    static int[] enemyShootCounter = new int[numEnemies];
    static int[] enemyHP = new int[numEnemies];
    static int[] killCounter = new int[numEnemies];
    // Platform variables
    static int numPlatforms = 15;
    static final int HITBOX_NUM = 4;
    static int platX[][] = new int[NUM_STAGES][numPlatforms];
    static int platY[][] = new int[NUM_STAGES][numPlatforms];
    static int platW[] = new int[numPlatforms];
    static int platH[] = new int[numPlatforms];
    static Rectangle[] hitBoxTop = new Rectangle[numPlatforms];
    static Rectangle[] hitBoxRight = new Rectangle[numPlatforms];
    static Rectangle[] hitBoxBottom = new Rectangle[numPlatforms];
    static Rectangle[] hitBoxLeft = new Rectangle[numPlatforms];
    static Rectangle[][][] platHitBoxes = new Rectangle[NUM_STAGES][numPlatforms][HITBOX_NUM];
    // Top, Right, Bottom, Left hitbox variables
    static final int[] BORDERX = {0,775,0,0};
    static final int[] BORDERY = {0,0,575,0};
    static final int[] BORDERW = {800,25,800,25};
    static final int[] BORDERH = {25,575,25,575};
    static Rectangle[] borderRect = new Rectangle[HITBOX_NUM];  
    // All images
    static BufferedImage title;
    static BufferedImage background;
    static BufferedImage start;
    static BufferedImage controls;
    static BufferedImage quit;
    static BufferedImage back;
    static BufferedImage stage1Button;
    static BufferedImage stage2Button;
    static BufferedImage stage3Button;
    static BufferedImage stage1Preview;
    static BufferedImage stage2Preview;
    static BufferedImage stage3Preview;
    static BufferedImage checkmark;
    static BufferedImage player;
    static BufferedImage grapplingHook;
    static BufferedImage rope;
    static BufferedImage bullet;
    static BufferedImage enemy;
    static BufferedImage enemyBullet;
    static BufferedImage platform;
    static BufferedImage win;
    static BufferedImage gameOver;
    static BufferedImage restartButton;
    static BufferedImage loseQuit;
    static BufferedImage controlsBackground;
    
    
    /*--------------------------------------------------------------*
     * Main Method - control frame, call other methods              *
     *--------------------------------------------------------------*/
    public static void main(String[] args){
        gameWindow = new JFrame("Game Window");
        gameWindow.setSize(WIDTH, HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new GraphicsPanel();
        // Mouse Listener
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseMotionListener);
        gameWindow.add(canvas); 
        gameWindow.setVisible(true);
        
        
        try {                
            title = ImageIO.read(new File("picture/title.png"));
            background = ImageIO.read(new File("picture/background.png"));
            start = ImageIO.read(new File("picture/startButton.png"));
            controls = ImageIO.read(new File("picture/controlsButton.png"));
            quit = ImageIO.read(new File("picture/quitButton.png"));
            back = ImageIO.read(new File("picture/backButton.png"));
            stage1Button = ImageIO.read(new File("picture/stage1Button.png"));
            stage2Button = ImageIO.read(new File("picture/stage2Button.png"));
            stage3Button = ImageIO.read(new File("picture/stage3Button.png"));
            stage1Preview = ImageIO.read(new File("picture/stage1design.png"));
            stage2Preview = ImageIO.read(new File("picture/stage2design.png"));
            stage3Preview = ImageIO.read(new File("picture/stage3design.png"));
            checkmark = ImageIO.read(new File("picture/checkmark.png"));
            player = ImageIO.read(new File("picture/player.png"));
            grapplingHook = ImageIO.read(new File("picture/grapplingHook.png"));
            rope = ImageIO.read(new File("picture/rope.png"));
            bullet = ImageIO.read(new File("picture/bullet.png"));
            enemy = ImageIO.read(new File("picture/enemy.png"));
            enemyBullet = ImageIO.read(new File("picture/enemyBullet.png"));
            platform = ImageIO.read(new File("picture/platform.png"));
            win = ImageIO.read(new File("picture/win.png"));
            gameOver = ImageIO.read(new File("picture/gameOver.png"));
            restartButton = ImageIO.read(new File("picture/restartButton.png"));
            loseQuit = ImageIO.read(new File("picture/quitButton.png"));
            controlsBackground = ImageIO.read(new File("picture/controlsBackground.png"));
        } catch (IOException ex){} 
        for(int i = 0; i < HITBOX_NUM; i++)
            borderRect[i] = new Rectangle(BORDERX[i], BORDERY[i], BORDERW[i], BORDERH[i]); 
        runGameLoop();
    } // main method end 
    /*--------------------------------------------------------------*
     * Side Methods - calculate within the game                     *
     *--------------------------------------------------------------*/
    public static void runGameLoop(){
        while (inPlay){
            gameWindow.repaint();
            try  {Thread.sleep(16);} catch(Exception e){}
            while (playingStage){
                gameWindow.repaint();
                try  {Thread.sleep(16);} catch(Exception e){}
                resetLimit(); // Resets the limiting circle around the player back to player coordinates
// Move rope
                checkRopeStep(); // Checks the rope's step relative to the angle of the mouse to the player
                followRope(); // Has the sections of rope follow the initial section   
                reverseRope(); // Has the rope go back to the player once it reaches the limit
                undrawRope(); // Undraws the rope once it reaches the player
                shootRopeTimer(); // Checks if rope can be shot again by player
// Check movement of player, using the rope 
                grappleMove(platHitBoxes[stage]); // Allows player to grapple to plats       
                grappleMove(borderRect); // Allows player to grapple to border
                moveObjects(ropesX, ropesY, ropeStepX, ropeStepY, 0); // Moves the rope 
                updateHitBoxes(); // Update all hit boxes back to coordinates 
                takeDamage(); // Allows enemies and players to take damage
                collideObjects(); // Checks for all collisions     
                checkBulletStep(); // Checks the bullet's step relative to the angle of the mouse to the player
                moveObjects(bulletsX, bulletsY, bulletsStepX, bulletsStepY, BULLET_LIMIT); // Moves the bullets
                checkEnemyBulletStep(); // Checks the enemy's bullet's step relative to to the angle of the enemy to player 
                moveObjects(enemyBulletsX, enemyBulletsY, enemyBulletsStepX, enemyBulletsStepY, numEnemies); // Moves enemy bullets
                moveEnemy(); // Moves enemy based on path given
                shootEnemy(); // Shoots for enemy based on time
                checkWin();
                checkDeathScreen(); // Check if player dies
            }
        }
    } // runGameLoop method end
///********************************************************************************************************************
//                                               Player methods                                                       *
//*********************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------
    public static int collidePlayer(Rectangle platRect, int playerCord, int objectCord, int playerDim, int objectDim){
        if (playerRect.intersects(platRect) && drawRopes[0]){
            playerCord = objectCord + playerDim + objectDim;
            resetRope(0, false);
        }
        return playerCord;
    } // collidePlayer method end  
//---------------------------------------------------------------------------------------------------------------------
    public static void grappleMove(Rectangle[][] rect){
        for (int i = 0; i < numPlatforms; i++){
            for(int j = 0; j < HITBOX_NUM; j++){
                checkPlatformHitBoxes(i);
                collideRope(rect[i][j]);
            }
        }
    } // grappleMove method end
    public static void grappleMove(Rectangle[] rect){
        for (int i = 0; i < HITBOX_NUM; i++){
            checkBorderHitBoxes();
            collideRope(rect[i]);
        }
    } // grappleMove method end
//---------------------------------------------------------------------------------------------------------------------
    public static void checkPlatformHitBoxes(int i){
        // Checking for plat hitboxes
        playerY = collidePlayer(platHitBoxes[stage][i][0], playerY, platY[stage][i], -playerH/2-1, 0);
        playerX = collidePlayer(platHitBoxes[stage][i][1], playerX, platX[stage][i], playerW/2+1, platW[i]);
        playerY = collidePlayer(platHitBoxes[stage][i][2], playerY, platY[stage][i], playerH/2+1, platH[i]);
        playerX = collidePlayer(platHitBoxes[stage][i][3], playerX, platX[stage][i], -playerW/2-1, 0);
    } // checkPlatformHitBoxes method end
    public static void checkBorderHitBoxes(){
        // Checking for border
        playerY = collidePlayer(borderRect[0], playerY, BORDERY[0], playerH/2+1, BORDERH[0]);
        playerX = collidePlayer(borderRect[1], playerX, BORDERX[1], -playerW/2-1, 0);
        playerY = collidePlayer(borderRect[2], playerY, BORDERY[2], -playerH/2-1, 0);
        playerX = collidePlayer(borderRect[3], playerX, BORDERX[3], playerW/2+1, BORDERW[3]);
    } // checkBorderHitBoxes method end
//---------------------------------------------------------------------------------------------------------------------
    public static void takeDamage(){
        for(int i = 0; i < numEnemies; i++){
            //Hits for player
            if (playerRect.intersects(enemyRect[stage][i]) && drawEnemies[i])
                playerHP = playerHP - 1;
            if (playerRect.intersects(enemyBulletsRect[i]) && drawEnemyBullets[i])
                playerHP = playerHP - 1;
            for(int j = 0; j < BULLET_LIMIT; j++){
                if (bulletsRect[j].intersects(enemyRect[stage][i]) && drawBullets[j])
                    enemyHP[i] = enemyHP[i] - 1;
                if (enemyHP[i] <= 0){
                    drawEnemies[i] = false;
                    killCounter[i] = 1;
                }
            }
        }
    } // takeDamage method end
//---------------------------------------------------------------------------------------------------------------------
//*********************************************************************************************************************
//                                                 Rope methods                                                       *
//*********************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------
    public static void checkRopeStep(){
        if (reverseRope){      
            ropeStepX = -directAngleX(ropeStepX, ropesDir[0], ropesAngle[0], ROPE_VELOCITY);
            ropeStepY = -directAngleY(ropeStepY, ropesDir[0], ropesAngle[0], ROPE_VELOCITY);    
        } else {
            ropeStepX = directAngleX(ropeStepX, ropesDir[0], ropesAngle[0], ROPE_VELOCITY);
            ropeStepY = directAngleY(ropeStepY, ropesDir[0], ropesAngle[0], ROPE_VELOCITY);
        } 
    } // checkRopeStep method end
//---------------------------------------------------------------------------------------------------------------------
    public static void reverseRope(){
        limitRope = calcDistance(playerX, ropesX[0], playerY, ropesY[0]);
        if (limitRope >= limitR-ropesW*2)
            reverseRope = true;
        for (int i = 0; i < ROPE_LENGTH; i++){
            if (ropesX[i] == playerX && ropesY[i] == playerY)
                attachRope = true;
            else
                attachRope = false;     
        }
    } // reverseRope method end 
//---------------------------------------------------------------------------------------------------------------------
    public static void collideRope(Rectangle objectRect){
        if (ropeRect.intersects(objectRect) && drawRopes[0]){
            ropeStepX = 0;
            ropeStepY = 0;           
            for (int r = 0; r < ROPE_LENGTH; r++){
                playerX = ropesX[r];
                playerY = ropesY[r];    
            }
        }
    } // collideRope method end
//---------------------------------------------------------------------------------------------------------------------
    public static void followRope(){
        for (int i = ROPE_LENGTH-1; i > 0; i--){
            ropesX[i] = ropesX[i-1];
            ropesY[i] = ropesY[i-1]; 
        }  
    } // followRope method end
//---------------------------------------------------------------------------------------------------------------------
    public static void undrawRope(){
        for (int i = 0; i < ROPE_LENGTH; i++){
            if (ropesX[i] == playerX && ropesY[i] == playerY && !attachRope)
                drawRopes[i] = false;     
        }
    } // undrawRope method end
//---------------------------------------------------------------------------------------------------------------------
    public static void shootRopeTimer(){
        if (!drawRopes[ROPE_LENGTH - 2])
            shootRope = true;
    } // shootRopeTimer method end
//---------------------------------------------------------------------------------------------------------------------
//*********************************************************************************************************************
//                                               Bullet methods                                                       *
//*********************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------
    public static void checkBulletStep(){
        for (int i = 0; i < BULLET_LIMIT; i++){            
            bulletsStepX[i] = directAngleX(bulletsStepX[i], bulletsDir[i], bulletsAngle[i], BULLET_VELOCITY);
            bulletsStepY[i] = directAngleY(bulletsStepY[i], bulletsDir[i], bulletsAngle[i], BULLET_VELOCITY);
        }
    } // checkBulletStep method end
//---------------------------------------------------------------------------------------------------------------------
    public static void collideBullet(Rectangle rect, boolean condition){
        for(int i = 0; i < BULLET_LIMIT; i++){
            if (bulletsRect[i].intersects(rect) && drawBullets[i] && condition)
                drawBullets[i] = false;
        }
    } // collideBullet method end
//---------------------------------------------------------------------------------------------------------------------
//*********************************************************************************************************************
//                                               Enemy methods                                                        *
//*********************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------
    public static void moveEnemy(){
        for(int i = 0; i < numEnemies; i++){
            if (enemyX[stage][i] == pathX[stage][i]+pathW[stage][i] && enemyY[stage][i] == pathY[stage][i]){
                enemyVx[i] = 0;
                enemyVy[i] = enemyStepY;
            } else if (enemyX[stage][i] == pathX[stage][i]+pathW[stage][i] && enemyY[stage][i] == pathY[stage][i] +pathH[stage][i]){
                enemyVx[i] = -enemyStepX;
                enemyVy[i] = 0;
            } else if (enemyX[stage][i] == pathX[stage][i] && enemyY[stage][i] == pathY[stage][i]+pathH[stage][i]){
                enemyVx[i] = 0;
                enemyVy[i] = -enemyStepY;
            } else if(enemyX[stage][i] == pathX[stage][i] && enemyY[stage][i] == pathY[stage][i]){
                enemyVx[i] = enemyStepX; 
                enemyVy[i] = 0;
            }
            enemyX[stage][i] = enemyX[stage][i] + enemyVx[i];
            enemyY[stage][i] = enemyY[stage][i] + enemyVy[i];
        }
    } // moveEnemy method end
//---------------------------------------------------------------------------------------------------------------------
    public static void checkEnemyBulletStep(){
        for(int i = 0; i < numEnemies; i++){
            enemyBulletsStepX[i] = directAngleX(enemyBulletsStepX[i], enemyBulletsDir[i], enemyBulletsAngle[i], ENEMY_BULLET_VELOCITY);
            enemyBulletsStepY[i] = directAngleY(enemyBulletsStepY[i], enemyBulletsDir[i], enemyBulletsAngle[i], ENEMY_BULLET_VELOCITY);
        }
    } // checkEnemyBulletStep method end
//---------------------------------------------------------------------------------------------------------------------
    public static void collideEnemyBullets(Rectangle rect){
        for(int i = 0; i < numEnemies; i++){
            if (enemyBulletsRect[i].intersects(rect) && drawEnemyBullets[i])
                drawEnemyBullets[i] = false;
        }
    } // collideEnemyBullets method end
//---------------------------------------------------------------------------------------------------------------------
    public static void calcEnemyAngle(){
        for (int i = 0; i < numEnemies; i++){
            enemyToPlayerAngleX[i] = (playerX+playerW/2.0) - (enemyX[stage][i]+enemyW/2.0);
            enemyToPlayerAngleY[i] = (playerY+playerH/2.0) - (enemyY[stage][i]+enemyH/2.0);
            enemyVectorAngle[i] = Math.atan(enemyToPlayerAngleY[i]/enemyToPlayerAngleX[i]);
            enemyDir[i] = calcDirection(enemyX[stage][i], enemyToPlayerAngleX[i]);
            enemyVectorX[i] = directAngleX(enemyVectorX[i], enemyDir[i], enemyVectorAngle[i], ENEMY_VECTOR);
            enemyVectorY[i] = directAngleY(enemyVectorY[i], enemyDir[i], enemyVectorAngle[i], ENEMY_VECTOR);
        }
    } // calcEnemyAngle method end
//---------------------------------------------------------------------------------------------------------------------
    public static void shootEnemyCounter(){
        for(int i = 0; i < numEnemies; i++){
            enemyShootCounter[i] = enemyShootCounter[i] + ENEMY_SHOOT_COUNTER_STEP;  
        }
    } // shootEnemyCounter method end
//---------------------------------------------------------------------------------------------------------------------
    public static void shootEnemy(){
        calcEnemyAngle();
        shootEnemyCounter();
        for(int i = 0; i < numEnemies; i++){
            if (enemyShootCounter[i] >= ENEMY_SHOOT_COUNTER_MAX && drawEnemies[i])
                resetEnemyBullet(i);
        }       
    } // shootEnemy method end
//---------------------------------------------------------------------------------------------------------------------
//*********************************************************************************************************************
//                                               Object methods                                                       *
//*********************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------
    public static String calcDirection(int x, double adder){
        String dir = "R";
        if (x + adder > x)
            dir = "R";
        else if (x + adder < x)
            dir = "L";
        return dir;
    } // calcDirection method end
//---------------------------------------------------------------------------------------------------------------------
    public static int directAngleX(int x, String dir, double angle,int multiplier){
        if (dir.equals("R"))
            x = (int)(Math.round(multiplier*Math.cos(angle)));
        else if (dir.equals("L"))
            x = -(int)(Math.round(multiplier*Math.cos(angle)));
        return x;
    } // directAngleX method end 
//---------------------------------------------------------------------------------------------------------------------
    public static int directAngleY(int y, String dir, double angle,int multiplier){
        if (dir.equals("R"))
            y = (int)(Math.round(multiplier*Math.sin(angle)));
        else if (dir.equals("L"))
            y = -(int)(Math.round(multiplier*Math.sin(angle)));
        return y;
    } // directAngleY method end 
//---------------------------------------------------------------------------------------------------------------------
    public static void updateHitBoxes(){
        playerRect.setLocation(playerX-playerW/2, playerY-playerH/2);
        ropeRect.setLocation(ropesX[0]-ropesW/2, ropesY[0]-ropesH/2);
        for(int i = 0; i < BULLET_LIMIT; i++)
            bulletsRect[i].setLocation(bulletsX[i]-bulletsW/2, bulletsY[i] - bulletsH/2);
        for(int i = 0; i < numEnemies; i++){
            enemyRect[stage][i].setLocation(enemyX[stage][i]-enemyW/2, enemyY[stage][i]-enemyH/2);
            enemyBulletsRect[i].setLocation(enemyBulletsX[i], enemyBulletsY[i]);
        }
    } // updateHitBoxes method end 
//---------------------------------------------------------------------------------------------------------------------
    public static int calcDistance(int x1, int x2, int y1, int y2){
        int distance = (int)Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1, 2));
        return distance;
    } // calcDistance method end
//---------------------------------------------------------------------------------------------------------------------
    public static void moveObject(int objectX, int objectY, int stepX, int stepY){
        objectX = objectX + stepX;
        objectY = objectY + stepY;
    } // moveObject method end
    public static void moveObjects(int[] objectX, int[] objectY, int stepX, int stepY,int i){
        objectX[i] = objectX[i] + stepX;
        objectY[i] = objectY[i] + stepY;
        
    } // moveObjects method end
    public static void moveObjects(int[] objectX, int[] objectY, int[] stepX, int[] stepY, int limit){
        for(int i = 0; i < limit; i++){
            objectX[i] = objectX[i] + stepX[i];
            objectY[i] = objectY[i] + stepY[i];
        }
    } // moveObjects method end
//---------------------------------------------------------------------------------------------------------------------
    public static void collideObjects(){
        for(int i = 0; i < numPlatforms; i++){
            for(int j = 0; j < HITBOX_NUM; j++){
                collideBullet(platHitBoxes[stage][i][j], true);
                collideEnemyBullets(platHitBoxes[stage][i][j]);
            }
        }
        collideEnemyBullets(playerRect);
        for(int i = 0; i < numEnemies; i++){
            collideBullet(enemyRect[stage][i], drawEnemies[i]);
            
        }
    } // collideObjects method end
//---------------------------------------------------------------------------------------------------------------------
//*********************************************************************************************************************
//                                                Stage methods                                                       *
//*********************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------
    public static void checkStage(){
        if (stage == MAIN_MENU)
            startMenuScreen();
        else if (stage == CONTROL_MENU)
            startControlScreen();
        else if (stage == STAGE_MENU)
            startStageScreen();
    } // checkStage method end
//---------------------------------------------------------------------------------------------------------------------
    public static void startMenuScreen(){
        mainMenu = true;
        controlMenu = false;
        stageMenu = false;
        playingStage = false;
        stage = MAIN_MENU;
    } // startMenuScreen method end
//---------------------------------------------------------------------------------------------------------------------
    public static void startControlScreen(){
        mainMenu = false;
        controlMenu = true;
        stageMenu = false;
        playingStage = false;
        stage = CONTROL_MENU;
    } // startControlScreen method end
//---------------------------------------------------------------------------------------------------------------------
    public static void startStageScreen(){
        mainMenu = false;
        controlMenu = false;
        stageMenu = true;
        playingStage = false;
        stage = STAGE_MENU;
    } // startStageScreen method end
//---------------------------------------------------------------------------------------------------------------------
    public static void startStageOne(){
        resetMenu();
        fillArrays();
        numPlatforms = 14;
        platX[STAGE_ONE][0] = 100;
        platX[STAGE_ONE][1] = 200;
        platX[STAGE_ONE][2] = 300;
        platX[STAGE_ONE][3] = 400;
        platX[STAGE_ONE][4] = 500;
        platX[STAGE_ONE][5] = 600;
        platX[STAGE_ONE][6] = 100;
        platX[STAGE_ONE][7] = 200;
        platX[STAGE_ONE][8] = 300;
        platX[STAGE_ONE][9] = 400;
        platX[STAGE_ONE][10] = 500;
        platX[STAGE_ONE][11] = 600;
        platX[STAGE_ONE][12] = 200;
        platX[STAGE_ONE][13] = 500;
        platY[STAGE_ONE][0] = 100;
        platY[STAGE_ONE][1] = 100;
        platY[STAGE_ONE][2] = 100;
        platY[STAGE_ONE][3] = 100;
        platY[STAGE_ONE][4] = 100;
        platY[STAGE_ONE][5] = 100;
        platY[STAGE_ONE][6] = 400;
        platY[STAGE_ONE][7] = 400;
        platY[STAGE_ONE][8] = 400;
        platY[STAGE_ONE][9] = 400;
        platY[STAGE_ONE][10] = 400;
        platY[STAGE_ONE][11] = 400;
        platY[STAGE_ONE][12] = 250;
        platY[STAGE_ONE][13] = 250;
        resetPlatHitboxes(STAGE_ONE);
        numEnemies= 5;
        enemyX[STAGE_ONE][0] = 75;
        enemyX[STAGE_ONE][1] = 75;
        enemyX[STAGE_ONE][2] = 175;
        enemyX[STAGE_ONE][3] = 475;
        enemyX[STAGE_ONE][4] = 75;
        enemyY[STAGE_ONE][0] = 75;
        enemyY[STAGE_ONE][1] = 65;
        enemyY[STAGE_ONE][2] = 225;
        enemyY[STAGE_ONE][3] = 225;
        enemyY[STAGE_ONE][4] = 370;
        pathW[STAGE_ONE][0] = 650;
        pathW[STAGE_ONE][1] = 650;
        pathW[STAGE_ONE][2] = 150;
        pathW[STAGE_ONE][3] = 150;
        pathW[STAGE_ONE][4] = 650;
        pathH[STAGE_ONE][0] = 100;
        pathH[STAGE_ONE][1] = 425;
        pathH[STAGE_ONE][2] = 100;
        pathH[STAGE_ONE][3] = 100;
        pathH[STAGE_ONE][4] = 100;
        playerX = 400;
        playerY = 565;
        playerHP = MAX_PLAYER_HP;
        for(int i = 0; i < numEnemies; i++)
            killCounter[i] = 0;
        resetEnemyHitboxes(STAGE_ONE);
        stage = STAGE_ONE;
    } // startStageOne method end
//---------------------------------------------------------------------------------------------------------------------
    public static void startStageTwo(){
        resetMenu();
        fillArrays();
        numPlatforms = 8;
        platX[STAGE_TWO][0] = 125;
        platX[STAGE_TWO][1] = 575;
        platX[STAGE_TWO][2] = 315;
        platX[STAGE_TWO][3] = 415;
        platX[STAGE_TWO][4] = 125;
        platX[STAGE_TWO][5] = 575;
        platX[STAGE_TWO][6] = 315;
        platX[STAGE_TWO][7] = 415;
        platY[STAGE_TWO][0] = 100;
        platY[STAGE_TWO][1] = 100;
        platY[STAGE_TWO][2] = 200;
        platY[STAGE_TWO][3] = 200;
        platY[STAGE_TWO][4] = 300;
        platY[STAGE_TWO][5] = 300;
        platY[STAGE_TWO][6] = 400;
        platY[STAGE_TWO][7] = 400;
        resetPlatHitboxes(STAGE_TWO);
        numEnemies = 7;
        enemyX[STAGE_TWO][0] = 100;
        enemyX[STAGE_TWO][1] = 550;
        enemyX[STAGE_TWO][2] = 295;
        enemyX[STAGE_TWO][3] = 100;
        enemyX[STAGE_TWO][4] = 550;
        enemyX[STAGE_TWO][5] = 295;
        enemyX[STAGE_TWO][6] = 50;
        enemyY[STAGE_TWO][0] = 75;
        enemyY[STAGE_TWO][1] = 75;
        enemyY[STAGE_TWO][2] = 175;
        enemyY[STAGE_TWO][3] = 275;
        enemyY[STAGE_TWO][4] = 275;
        enemyY[STAGE_TWO][5] = 375;
        enemyY[STAGE_TWO][6] = 50;
        pathW[STAGE_TWO][0] = 150;
        pathW[STAGE_TWO][1] = 150;
        pathW[STAGE_TWO][2] = 250;
        pathW[STAGE_TWO][3] = 150;
        pathW[STAGE_TWO][4] = 150;
        pathW[STAGE_TWO][5] = 250;
        pathW[STAGE_TWO][6] = 700;
        pathH[STAGE_TWO][0] = 100;
        pathH[STAGE_TWO][1] = 100;
        pathH[STAGE_TWO][2] = 100;
        pathH[STAGE_TWO][3] = 100;
        pathH[STAGE_TWO][4] = 100;
        pathH[STAGE_TWO][5] = 100;
        pathH[STAGE_TWO][6] = 450;
        playerX = 400;
        playerY = 565;
        playerHP = MAX_PLAYER_HP;
        for(int i = 0; i < numEnemies; i++)
            killCounter[i] = 0;
        resetEnemyHitboxes(STAGE_TWO);
        stage = STAGE_TWO;
    }
//---------------------------------------------------------------------------------------------------------------------
    public static void startStageThree(){
        resetMenu();
        fillArrays();
        numPlatforms = 6;
        platX[STAGE_THREE][0] = 100;
        platX[STAGE_THREE][1] = 300;
        platX[STAGE_THREE][2] = 100;
        platX[STAGE_THREE][3] = 300;
        platX[STAGE_THREE][4] = 100;
        platX[STAGE_THREE][5] = 300;
        platY[STAGE_THREE][0] = 100;
        platY[STAGE_THREE][1] = 100;
        platY[STAGE_THREE][2] = 250;
        platY[STAGE_THREE][3] = 250;
        platY[STAGE_THREE][4] = 400;
        platY[STAGE_THREE][5] = 400;
        resetPlatHitboxes(STAGE_THREE);
        numEnemies= 5;
        enemyX[STAGE_THREE][0] = 575;
        enemyX[STAGE_THREE][1] = 675;
        enemyX[STAGE_THREE][2] = 575;
        enemyX[STAGE_THREE][3] = 675;
        enemyX[STAGE_THREE][4] = 575;
        enemyY[STAGE_THREE][0] = 50;
        enemyY[STAGE_THREE][1] = 50;
        enemyY[STAGE_THREE][2] = 325;
        enemyY[STAGE_THREE][3] = 325;
        enemyY[STAGE_THREE][4] = 50;
        pathW[STAGE_THREE][0] = 75;
        pathW[STAGE_THREE][1] = 75;
        pathW[STAGE_THREE][2] = 75;
        pathW[STAGE_THREE][3] = 75;
        pathW[STAGE_THREE][4] = 150;
        pathH[STAGE_THREE][0] = 200;
        pathH[STAGE_THREE][1] = 200;
        pathH[STAGE_THREE][2] = 200;
        pathH[STAGE_THREE][3] = 200;
        pathH[STAGE_THREE][4] = 400;
        playerX = 50;
        playerY = 565;
        playerHP = MAX_PLAYER_HP;
        for(int i = 0; i < numEnemies; i++)
            killCounter[i] = 0;
        resetEnemyHitboxes(STAGE_THREE);
        stage = STAGE_THREE;
    } // startStageThree method end
//---------------------------------------------------------------------------------------------------------------------
    public static void resetMenu(){
        mainMenu = false;
        stageMenu = false;
        controlMenu = false;
        playingStage = true;
    } // resetMenu method end
//---------------------------------------------------------------------------------------------------------------------
    public static void startDeathScreen(){
        playingStage = false;
        death = true;
    }
//---------------------------------------------------------------------------------------------------------------------
    public static void checkDeathScreen(){
        if (playerHP <= -1){
            stage = DEATH_SCREEN;
            startDeathScreen();
        }
    } // startEndScreen method end
//---------------------------------------------------------------------------------------------------------------------
    public static void checkWin(){
        int wonCounter = 0;
        for(int i = 0; i < numEnemies; i++)
            wonCounter = wonCounter + killCounter[i];
        if (wonCounter >= numEnemies){
            playingStage = false;
            stageMenu = true;
            stagesWon = stagesWon + 1;
        } 
        if (stagesWon == 3)
            gameWon = true;
        
    } // checkWin method end
//---------------------------------------------------------------------------------------------------------------------
//*********************************************************************************************************************
//                                                Fill methods                                                        *
//*********************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------
    public static void fillArrays(){
        fillRopes(); // Filling ropes arrays
        fillBullets(); // Filling bullets arrays
        fillEnemyBullets(); // Filling enemy bullets arrays
        fillEnemy(); // Filling enemy array
        fillPlat(); // Filling platform arrays
    } // fillArrays method end
//---------------------------------------------------------------------------------------------------------------------
    public static void fillRopes(){
        Arrays.fill(ropesX,playerX);
        Arrays.fill(ropesY,playerY);
        Arrays.fill(drawRopes,false);
        Arrays.fill(ropesDir,"R");
        Arrays.fill(ropesAngle,0);
    } // fillRopes method end 
//---------------------------------------------------------------------------------------------------------------------
    public static void fillBullets(){
        Arrays.fill(bulletsX,0);
        Arrays.fill(bulletsY,0);
        Arrays.fill(drawBullets,false);
        Arrays.fill(bulletsDir,"R");
        Arrays.fill(bulletsStepX,0);
        Arrays.fill(bulletsStepY,0);
        Arrays.fill(bulletsAngle,0);
        for (int i = 0; i < BULLET_LIMIT; i++)
            bulletsRect[i] = new Rectangle(bulletsX[i], bulletsY[i], bulletsW, bulletsH);
    } // fillBullets method end
//---------------------------------------------------------------------------------------------------------------------
    public static void fillEnemyBullets(){
        Arrays.fill(enemyBulletsX, enemyX[stage][0]);
        Arrays.fill(enemyBulletsY, enemyY[stage][0]);
        Arrays.fill(drawEnemyBullets, false);
        Arrays.fill(enemyBulletsDir,"R");
        Arrays.fill(enemyBulletsStepX, 0);
        Arrays.fill(enemyBulletsStepY, 0);
        Arrays.fill(enemyBulletsAngle, 0);
        for(int i = 0; i < numEnemies; i++){
            enemyBulletsRect[i] = new Rectangle(enemyBulletsX[i], enemyBulletsY[i], bulletsW, bulletsH);
            enemyShootCounter[i] = (int)(Math.random() * ENEMY_SHOOT_COUNTER_MAX);  
        }
    } // fillEnemyBullets method end
//---------------------------------------------------------------------------------------------------------------------
    public static void fillEnemy(){
        Arrays.fill(drawEnemies, true);
        Arrays.fill(enemyVx, 0);
        Arrays.fill(enemyVy, 0);
        Arrays.fill(enemyHP, MAX_ENEMY_HP);
        for(int i = 0; i < numEnemies; i++){
            pathX[stage][i] = enemyX[stage][i];
            pathY[stage][i] = enemyY[stage][i];
        }  
    } // fillEnemy method end
//---------------------------------------------------------------------------------------------------------------------        
    public static void fillPlat(){
        Arrays.fill(platW, 100);
        Arrays.fill(platH, 50);
    } // fillPlat method end
//---------------------------------------------------------------------------------------------------------------------
//*********************************************************************************************************************
//                                                Reset methods                                                       *
//*********************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------
    public static void resetRope(int j, boolean draw){
        ropesX[j] = playerX;
        ropesY[j] = playerY;
        ropesAngle[j] = vectorAngle;
        ropesDir[j] = calcDirection(ropesX[j], pointerX);
        reverseRope = false; 
        attachRope = false;
        for (int i = 0; i < ROPE_LENGTH-1; i++)
            drawRopes[i] = draw;
        shootRope = false;
    } // resetRope method end
//---------------------------------------------------------------------------------------------------------------------
    public static void resetBullet(){
        bulletsX[currentBullet] = playerX;
        bulletsY[currentBullet] = playerY;
        bulletsDir[currentBullet] = calcDirection(bulletsX[currentBullet], pointerX);
        bulletsAngle[currentBullet] = vectorAngle;
        drawBullets[currentBullet] = true;
        currentBullet = (currentBullet + 1)%BULLET_LIMIT; 
    } // resetBullet method end
//---------------------------------------------------------------------------------------------------------------------
    public static void resetEnemyHitboxes(int stage){
        for(int i = 0; i < numEnemies; i++){
            enemyRect[stage][i] = new Rectangle(enemyX[stage][i], enemyY[stage][i], enemyW, enemyH);
            pathX[stage][i] = enemyX[stage][i];
            pathY[stage][i] = enemyY[stage][i];
        }
    } // resetEnemyHitboxes method end 
//---------------------------------------------------------------------------------------------------------------------
    public static void resetEnemyBullet(int i){
        enemyBulletsX[i] = enemyX[stage][i];
        enemyBulletsY[i] = enemyY[stage][i];
        enemyBulletsDir[i] = calcDirection(enemyX[stage][i], enemyToPlayerAngleX[i]);
        enemyBulletsAngle[i] = enemyVectorAngle[i];
        drawEnemyBullets[i] = true;
        enemyShootCounter[i] = 0;
    } // resetEnemyBullet method end
//---------------------------------------------------------------------------------------------------------------------
    public static void resetPlatHitboxes(int stage){
        for(int i = 0; i < numPlatforms; i++){
            hitBoxTop[i] = new Rectangle(platX[stage][i],platY[stage][i],platW[i],platH[i]/10);
            hitBoxRight[i] = new Rectangle(platX[stage][i]+platW[i]-5, platY[stage][i], platW[i]/20, platH[i]);
            hitBoxBottom[i] = new Rectangle(platX[stage][i],platY[stage][i]+platH[i]-5,platW[i],platH[i]/10);
            hitBoxLeft[i] = new Rectangle(platX[stage][i], platY[stage][i], platW[i]/20, platH[i]);
            platHitBoxes[stage][i][0] = hitBoxTop[i];
            platHitBoxes[stage][i][1] = hitBoxRight[i];
            platHitBoxes[stage][i][2] = hitBoxBottom[i];
            platHitBoxes[stage][i][3] = hitBoxLeft[i];
        }
    } // resetPlatHitboxes method end
//---------------------------------------------------------------------------------------------------------------------
    public static void resetLimit(){
        limitX = playerX - limitD/2;
        limitY = playerY - limitD/2;
    }
    /*--------------------------------------------------------------*
     * Classes - control inputs and outputs                         *
     *--------------------------------------------------------------*/    
//------------------------------------------------------------------------------  
    static class GraphicsPanel extends JPanel{
        public GraphicsPanel(){
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g){ 
            super.paintComponent(g); //required
            /*****************************************************
              * Define painted and drawn things here as needed.    *
              *****************************************************/
// draw enemies
            g.drawImage(background, 0, 0, this);
            if (mainMenu){
                g.drawImage(title, 250,50, this);
                g.drawImage(start, 325,200, this);
                g.drawImage(controls, 325,300, this);
                g.drawImage(quit, 325,400, this);
            } 
            else if (stageMenu){
                g.drawImage(stage1Button, 100,510,this);
                g.drawImage(stage2Button, 325,510,this);
                g.drawImage(stage3Button, 550,510, this);
                g.drawImage(back, 0, 0, this);
                for(int i = 0; i < stagesWon; i++)
                    g.drawImage(checkmark,150 + i*225,450,this);
                if (mouseRect.intersects(STAGE_ONE_BUTTON_RECT) && !gameWon)
                    g.drawImage(stage1Preview, 100, 50, this);
                if (mouseRect.intersects(STAGE_TWO_BUTTON_RECT) && !gameWon)
                    g.drawImage(stage2Preview, 100, 50, this);
                if (mouseRect.intersects(STAGE_THREE_BUTTON_RECT) && !gameWon)
                    g.drawImage(stage3Preview, 100, 50, this);
                if (gameWon)
                    g.drawImage(win, 200, 100, this);
            }
            else if (controlMenu){
                g.drawImage(controlsBackground, 0, 0, this);
                g.drawImage(back, 0, 0, this);
            }
            
            else if (playingStage){
                g.setColor(Color.blue);
                for(int i = 0; i < numEnemies; i++){
                    if (drawEnemies[i]){
                        g.drawImage(enemy, enemyX[stage][i]-enemyW/2, enemyY[stage][i]-enemyH/2, this);
                        for(int j = 0; j < enemyHP[i]; j++)
                            g.drawRect(enemyX[stage][i] + j*5 - enemyW/2, enemyY[stage][i] - enemyH/2-8, 5, 5);
                        g.drawLine(enemyX[stage][i], enemyY[stage][i], enemyX[stage][i] + enemyVectorX[i], enemyY[stage][i] + enemyVectorY[i]);
                    }
                }            
// draw player
                g.setColor(Color.red);
                g.drawImage(player, playerX-playerW/2,playerY-playerH/2, this);
                for(int i = 0; i < playerHP; i++)
                    g.drawRect(playerX + i*5-playerW/2, playerY - playerH/2-8, 5,5);
// draw limiting circle
                g.drawOval(limitX, limitY, limitD, limitD);
// draw the direction vector
                g.setColor(Color.black);
                g.drawLine(playerX,playerY,playerX+vectorX,playerY+vectorY);
// draw rope 
                for (int i = 0; i < ROPE_LENGTH; i++){
                    if (drawRopes[i])
                        g.drawImage(rope, ropesX[i] - ropesW/2,ropesY[i]-ropesH/2, this);
                }
// draw bullet
                for(int i = 0; i < BULLET_LIMIT; i++){
                    if (drawBullets[i])
                        g.drawImage(bullet, bulletsX[i], bulletsY[i], this);
                }
// draw enemy bullet 
                for(int i = 0; i < numEnemies; i++){
                    if (drawEnemyBullets[i])
                        g.drawImage(enemyBullet,enemyBulletsX[i], enemyBulletsY[i], this);
                }
                for(int i = 0; i < numPlatforms; i++)
                    g.drawImage(platform, platX[stage][i], platY[stage][i], this);
            }
            else if (death){
                g.drawImage(gameOver, 200, 100, this);
                g.drawImage(restartButton, 100, 450, this);
                g.drawImage(loseQuit, 550, 450, this);
            }
        } // paintComponent method end
    } // GraphicsPanel class end
//------------------------------------------------------------------------------ 
    static class MyMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
        }
        public void mousePressed(MouseEvent e){ 
            // Shoot blaster
            if (e.getButton() == MouseEvent.BUTTON1){
                if (mouseRect.intersects(BACK_BUTTON) && !mainMenu && !playingStage){
                    if (stage == STAGE_MENU)
                        stage = stage - 2;
                    else if (stage == CONTROL_MENU)
                        stage = stage - 1;
                    
                    checkStage();
                }
                if (mainMenu){
                    if (mouseRect.intersects(STAGE_SELECT_BUTTON)){
                        startStageScreen();
                    }
                    else if (mouseRect.intersects(CONTROLS_BUTTON)){
                        startControlScreen();
                    }
                    else if (mouseRect.intersects(QUIT_BUTTON)){
                        mainMenu = false;
                        stageMenu = false;
                        controlMenu = false;
                        playingStage = false;
                        inPlay = false;
                    }
                } else if (stageMenu){
                    if (mouseRect.intersects(STAGE_ONE_BUTTON_RECT))
                        startStageOne();
                    else if (mouseRect.intersects(STAGE_TWO_BUTTON_RECT) && stagesWon == 1)
                        startStageTwo();
                    else if (mouseRect.intersects(STAGE_THREE_BUTTON_RECT) && stagesWon == 2)
                        startStageThree();
                } else if (death){
                    if (mouseRect.intersects(RESTART_BUTTON)){
                        death = false;
                        startStageScreen();
                        stagesWon = 0;
                    }
                    else if (mouseRect.intersects(LOSE_QUIT_BUTTON)){
                        mainMenu = false;
                        stageMenu = false;
                        controlMenu = false;
                        playingStage = false;
                        inPlay = false;
                    }
                }
                
                
                else if (playingStage)
                    resetBullet();   
                
            }
            // Shoot rope
            if (e.getButton() == MouseEvent.BUTTON3 && shootRope){
                for(int i = 0; i < ROPE_LENGTH; i++)
                resetRope(i, true);
            }
        }
        public void mouseReleased(MouseEvent e){  
        }
        public void mouseEntered(MouseEvent e){
        }
        public void mouseExited(MouseEvent e){
        }
        
    } // MyMouseListener class end      
//------------------------------------------------------------------------------     
    static class MyMouseMotionListener implements MouseMotionListener{
        public void mouseMoved(MouseEvent e){
            pointerX = e.getX() - playerX;
            pointerY = e.getY() - playerY;
            mouseRect.setLocation(e.getX(), e.getY());
            vectorAngle = Math.atan(pointerY/pointerX );
            playerDir = calcDirection(playerX, pointerX);
            vectorX = directAngleX(vectorX, playerDir, vectorAngle, VECTOR);
            vectorY = directAngleY(vectorY, playerDir, vectorAngle, VECTOR);
        }
        public void mouseDragged(MouseEvent e){      
        }        
    } // MyMouseMotionListener class end
    
}// FinalGame class end






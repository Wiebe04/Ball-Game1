//Nathan Wiebe
//Mood
//CSC1054
//23 March 2023
//Project

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;

public class TheBallGame extends Application
{
   GamePane[][] board = new GamePane[4][4];
   Label topLabel = new Label();
   
   public void start(Stage stage)
   {
      //skeleton of the application
      BorderPane root = new BorderPane();
      root.setPrefSize(600,600);  
      
      //gridpane that goes in the center that will be full of piecess
      GridPane gp = new GridPane();
      gp.setPrefSize(400,400);     
      
      //adds each gamepane into the center gridpane
      for(int i=0;i<4;i++)
      {
         for(int j=0;j<4;j++)
         {
            GamePane piece = new GamePane(i,j);
            piece.draw();
            gp.add(piece,i,j);
            board[i][j]=piece; 
         }
      }
      
      board[0][2].setVisible(false);
      moveability(board);
      
      //hbox and vbox to create spacing
      VBox leftBox = new VBox();
      VBox rightBox = new VBox();
      HBox bottomBox = new HBox();   
      
      //spacing for the sides
      leftBox.setPrefSize(50,600);
      rightBox.setPrefSize(50,600);
      bottomBox.setPrefSize(600,100);
      
      topLabel.setText("Balls left: "+15+"   Possible Moves: "+2);
      
      //Label alignment
      root.setAlignment(topLabel,Pos.CENTER);
      
      //setting the different parts of the borderpane
      root.setCenter(gp);
      root.setTop(topLabel);
      root.setLeft(leftBox);
      root.setRight(rightBox);
      root.setBottom(bottomBox);
      
      
      //scene stuff
      Scene scene = new Scene(root, 600, 600); //other scene code here
      stage.setScene(scene);
      stage.setTitle("The Ball Game");
      stage.show();
   }
    
   public class GamePane extends GridPane
   {
      private int x;
      private int y;
      
      //canvas for circle        
      private Canvas circleCanvas = new Canvas(80,80);
      private GraphicsContext gc = circleCanvas.getGraphicsContext2D();
      
      //buttons for each side
      private Button topB = new Button();
      private Button bottomB = new Button();
      private Button leftB = new Button();
      private Button rightB = new Button(); 
 
      public GamePane(int x, int y) 
      {
         //super for gridpane
         super();
         
         this.x=x;
         this.y=y;
         
         
         gc.setFill(Color.BLACK);
         gc.fillOval(0,0,80,80);
         
         //setting the size of each button
         topB.setPrefSize(20,80);
         bottomB.setPrefSize(20,80);
         leftB.setPrefSize(80,20);
         rightB.setPrefSize(80,20);
         
         //adding each compontent to the gamepane/gridpane
         add(topB,0,1);
         add(bottomB,2,1);
         add(leftB,1,0);
         add(rightB,1,2);
         add(circleCanvas,1,1);
         
         topB.setOnAction(new GameHandler());
         bottomB.setOnAction(new GameHandler());
         leftB.setOnAction(new GameHandler());
         rightB.setOnAction(new GameHandler());
         
      }//end of GamePane constructor
      
      //accessors for buttons   
      public Button getTop()
      {
         return topB;
      }
      
      public Button getBottom()
      {
         return bottomB;
      }
      
      public Button getLeft()
      {
         return leftB;
      }
      
      public Button getRight()
      {
         return rightB;
      }
      
      //ball booleans           
      boolean ballVisible;
      //button booleans
      boolean topVisible;
      boolean bottomVisible;
      boolean leftVisible;
      boolean rightVisible;
      
      //drawing method for the balls   
      public void draw()
      {
         //initially make all buttons invisible
         topB.setVisible(false);
         bottomB.setVisible(false);
         leftB.setVisible(false);
         rightB.setVisible(false);
         
         //changes boolean variables based on visibility
         if(circleCanvas.isVisible()==true)
         {
            ballVisible = true;
         }
         else
         {
            ballVisible = false;
         }
         if(topB.isVisible()==true)
         {
            topVisible = true;
         }
         else
         {
            topVisible = false;
         }
         if(bottomB.isVisible()==true)
         {
            bottomVisible = true;
         }
         else
         {
            bottomVisible = false;
         }
         if(leftB.isVisible()==true)
         {
            leftVisible = true;
         }
         else
         {
            leftVisible = false;
         }
         if(rightB.isVisible()==true)
         {
            rightVisible = true;
         }
         else
         {
            rightVisible = false;
         }

         //makes balls and buttons visible based on boolean variables
         if(ballVisible == true)
         {
            circleCanvas.setVisible(true);
         }
         else
         {
            circleCanvas.setVisible(false);
         }
         if(topVisible == true)
         {
            topB.setVisible(true);
         }
         else
         {
            topB.setVisible(false);
         }
         if(bottomVisible == true)
         {
            bottomB.setVisible(true);
         }
         else
         {
            bottomB.setVisible(false);
         }
         if(leftVisible == true)
         {
            leftB.setVisible(true);
         }
         else
         {
            leftB.setVisible(false);
         }
         if(rightVisible == true)
         {
            rightB.setVisible(true);
         }
         else
         {
            rightB.setVisible(false);
         }
         
         
      }//end of draw method  
      
   }//end of gamepane class
    
   //puts buttons where buttons should be
   public void moveability(GamePane[][] board)
   {
      //loops through array to check moveability
      for(int i=0;i<4;i++)
      {
         for(int j=0;j<4;j++)
          {
            int left=j-2;
            int right=j+2;
            int up=i-2;
            int down=i+2;
               
            if(left>-1)
            {
               if(board[i][left].isVisible()==false && board[i][j-1].isVisible()==true && board[i][j].isVisible()==true)
               {
                  board[i][j].getRight().setVisible(true); 
               }
               else
               {
                  board[i][j].getRight().setVisible(false); 
               }
            }
            if(right<4)
            {
               if(board[i][right].isVisible()==false && board[i][j+1].isVisible()==true && board[i][j].isVisible()==true)
               {
                  board[i][j].getLeft().setVisible(true);
               }
               else
               {
                  board[i][j].getLeft().setVisible(false); 
               }
            }
            if(up>-1)
            {
               if(board[up][j].isVisible()==false && board[i-1][j].isVisible()==true && board[i][j].isVisible()==true)
               {
                  board[i][j].getBottom().setVisible(true);
               }
               else
               {
                  board[i][j].getBottom().setVisible(false); 
               }
            }
            if(down<4)
            {
               if(board[down][j].isVisible()==false && board[i+1][j].isVisible()==true && board[i][j].isVisible()==true)
               {
                  board[i][j].getTop().setVisible(true);
               }
               else
               {
                  board[i][j].getTop().setVisible(false); 
               }
            } 
         }
      }
         
   }//end of moveability

   private int moveCount = 0;
   private int ballCount = 15;
   
   //listener
   public class GameHandler implements EventHandler<ActionEvent>  
   {
      public void handle(ActionEvent e) 
      {
         for(int i=0;i<4;i++)
         {
            for(int j=0;j<4;j++)
            {
               if(board[i][j].getTop() == e.getSource())
               {
                  click(board[i][j].getTop());
                  ballCount--;
               }
               if(board[i][j].getBottom() == e.getSource())
               {
                  click(board[i][j].getBottom());
                  ballCount--;
               }
               if(board[i][j].getRight() == e.getSource())
               {
                  click(board[i][j].getRight());
                  ballCount--;
               }
               if(board[i][j].getLeft() == e.getSource())
               {
                  click(board[i][j].getLeft());
                  ballCount--;
               }
            }
         }
         for(int i=0;i<4;i++)
         {
            for(int j=0;j<4;j++)
            {
               if(board[i][j].getTop().isVisible()==true)
               {
                  moveCount++;
               }
               if(board[i][j].getBottom().isVisible()==true)
               {
                  moveCount++;
               }
               if(board[i][j].getRight().isVisible()==true)
               {
                  moveCount++;
               }
               if(board[i][j].getLeft().isVisible()==true)
               {
                  moveCount++;
               }
            }
         }

     
         //label stuff
         if(moveCount == 0 && ballCount == 1)
         {
            topLabel.setText("YOU WIN!!!");
         }
         else if(moveCount == 0)
         {
            topLabel.setText("YOU LOSE!!!");
         }
         else
         {
            topLabel.setText("Balls left: "+ballCount+"   Possible Moves: "+moveCount);
         
         }
         moveCount=0;  
      }
   }//end of GameHandler
   
   public void  click(Button clicked)
   {
      for(int i=0;i<4;i++)
      {
            for(int j=0;j<4;j++)
            {
               if(clicked == board[i][j].getTop())
               {
                  board[i][j].setVisible(false);
                  board[i+1][j].setVisible(false);
                  board[i+2][j].setVisible(true);
               }
               if(clicked == board[i][j].getBottom())
               {
                  board[i][j].setVisible(false);
                  board[i-1][j].setVisible(false);
                  board[i-2][j].setVisible(true);
               }
               if(clicked == board[i][j].getRight())
               {
                  board[i][j].setVisible(false);
                  board[i][j-1].setVisible(false);
                  board[i][j-2].setVisible(true);
               }
               if(clicked == board[i][j].getLeft())
               {
                  board[i][j].setVisible(false);
                  board[i][j+1].setVisible(false);
                  board[i][j+2].setVisible(true);

               }
               for(int x=0;x<4;x++)
               {
                  for(int y=0;y<4;y++)
                  {
                     moveability(board);
                  }
               }
            }
      }
   } 
   public static void main(String[] args)
   {
      launch(args);
   }
} 
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.control.Button;

public class MatchingImagegame extends Application{
	
	
    @Override
    public void start(Stage stage) {
    	
    	
    	Pane canvas = new Pane(); //판생성
    	Scene scene = new Scene(canvas, 500, 500); //씬에 판 올림 ( 그림 px값 다서 화면 크기 지정함 )
    	
    	
    	Image image = new Image("eyeno.jpg"); 
    	Image image2 = new Image("eye.png");
    	canvas.getChildren().add(new ImageView(image));
    	ImagePattern imagePattern = new ImagePattern(image2);//이미지 생성
    	
    	
    	Circle ball = new Circle(80);
    	ball.setFill(imagePattern);
    	int startX = (int)(Math.random()*10);
    	int startY = (int)(Math.random()*10);
    	ball.relocate(startX, startY); //공 시작 위치 --> 랜덤하게 설정 
        canvas.getChildren().add(ball); // ball 생성
        
        stage.setResizable(false); //윈도우 크기 변경 불가하게 하기!
        
        stage.setTitle("Match the Image Game!");
        stage.setScene(scene);
        stage.show();
        
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), 
                new EventHandler<ActionEvent>() {

        	double dx = 7; //x속도
        	double dy = 3; //y속도
        	
            @Override
            public void handle(ActionEvent t) {
            
            	ball.setLayoutX(ball.getLayoutX() + dx);
            	ball.setLayoutY(ball.getLayoutY() + dy);

                Bounds bounds = canvas.getBoundsInLocal();
                
                //If the ball reaches the left or right border make the step negative
                if(ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius()) || 
                        ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()) ){

                	dx = -dx;

                }

                //If the ball reaches the bottom or top border make the step negative
                if((ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) || 
                        (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius()))){

                	dy = -dy;

                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
       
        
        double x = ball.getCenterX();
        double y = ball.getCenterX();//ball의 x, y좌표 변수 설정
        
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            		
            	timeline.stop();
            	//마우스 클릭하면 눈알 움직임 멈춤
            	
            	if((x<=350&&x>=300)&&(y<=200&&y>=150)) {
            		
            		//눈알 이미지 맞추기 성공 허용 범위 설정
            		
            		Stage win = new Stage(); //성공했을 시 띄울 창 만들기
            		Pane winPane = new Pane(); 
                	Scene winScene = new Scene(winPane, 300, 150); 
                	Text textWin = new Text(43,75,"와우~! 딱 맞췄어요!");
                	textWin.setFill(Color.RED);
                	textWin.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                	winPane.getChildren().add(textWin);
                	
                    win.setTitle("Congratulation!");
                    win.setScene(winScene);
                    win.show();
                    
                    
                	
            	}else {
            		
            		Stage lose = new Stage(); //성공했을 시 띄울 창 만들기
            		Pane losePane = new Pane(); 
                	Scene loseScene = new Scene(losePane, 300, 150); 
                	Text textLose = new Text(60,75,"땡! 다시 도전하세요~!");
                	textLose.setFill(Color.RED);
                	textLose.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                	losePane.getChildren().add(textLose);
                	
                	Button tryAgain = new Button("다시 도전!");
                	Button nextTime = new Button("그만 할래요");
                	tryAgain.setLayoutX(50);
                	tryAgain.setLayoutY(100);
                	nextTime.setLayoutX(160);
                	nextTime.setLayoutY(100);
                	
                	OKHandlerClass handler1 = new OKHandlerClass();
                    tryAgain.setOnAction(handler1);
                    CancelHandlerClass handler2 = new CancelHandlerClass();
                    nextTime.setOnAction(handler2);
                    losePane.getChildren().addAll(tryAgain, nextTime);
                	
                    lose.setTitle("Try again!");
                    lose.setScene(loseScene);
                    lose.show();
                  
                    
                    
            	}
            	
            }
        });
    	
    }
    
    
    public static void main(String[] args) {
        launch();
    }
  
}
    
    //버튼 핸들러 클래스
    class OKHandlerClass implements EventHandler<ActionEvent> {
    	  @Override
    	  public void handle(ActionEvent e) {
    		
    	    System.exit(0);
    		
    	  
    	  }
    	}

    	class CancelHandlerClass implements EventHandler<ActionEvent> {
    	  @Override
    	  public void handle(ActionEvent e) {
    	    
    	    System.exit(0);
    	    
    	  }
    	}
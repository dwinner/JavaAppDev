/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
Button btn;
Button btnON;    
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREEN);
        btn = new Button();
        btn.setLayoutX(20);
        btn.setLayoutY(20);
        btn.setText("Тестировать свойства");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
        System.out.println("Свойства, унаследованные от класса Node:"+"\n"+
        "Свойство blendMode: "+btn.blendModeProperty().getValue()+"\n"+
        "Свойство boundsInLocal: "+btn.boundsInLocalProperty().getValue()+"\n"+
        "Свойство boundsInParent: "+btn.boundsInParentProperty().getValue()+"\n"+
        "Свойство cacheHint: "+btn.cacheHintProperty().getValue()+"\n"+
        "Свойство cache: "+btn.cacheProperty().getValue()+"\n"+
        "Свойство clip: "+btn.clipProperty().getValue()+"\n"+
        "Свойство cursor: "+btn.cursorProperty().getValue()+"\n"+
        "Свойство depthTest: "+btn.depthTestProperty().getValue()+"\n"+
        "Свойство disabled: "+btn.disabledProperty().getValue()+"\n"+
        "Свойство disable: "+btn.disableProperty().getValue()+"\n"+
        "Свойство effect: "+btn.effectProperty().getValue()+"\n"+
        "Свойство eventDispatcher: "+btn.eventDispatcherProperty().getValue()+"\n"+
        "Свойство focused: "+btn.focusedProperty().getValue()+"\n"+
        "Свойство focusTraversable: "+btn.focusTraversableProperty().getValue()+"\n"+
        "Свойство hover: "+btn.hoverProperty().getValue()+"\n"+
        "Свойство id: "+btn.idProperty().getValue()+"\n"+
        "Свойство inputMethodRequests: "+btn.inputMethodRequestsProperty().getValue()+"\n"+
        "Свойство layoutBounds: "+btn.layoutBoundsProperty().getValue()+"\n"+
        "Свойство layoutX: "+btn.layoutXProperty().getValue()+"\n"+
        "Свойство layoutY: "+btn.layoutYProperty().getValue()+"\n"+
        "Свойство managed: "+btn.managedProperty().getValue()+"\n"+
        "Свойство mouseTransparent: "+btn.mouseTransparentProperty().getValue()+"\n"+
        "Свойство onDragDetected: "+btn.onDragDetectedProperty().getValue()+"\n"+
        "Свойство onDragDone: "+btn.onDragDoneProperty().getValue()+"\n"+
        "Свойство onDragDropped: "+btn.onDragDroppedProperty().getValue()+"\n"+
        "Свойство onDragEntered: "+btn.onDragEnteredProperty().getValue()+"\n"+
        "Свойство onDragExited: "+btn.onDragExitedProperty().getValue()+"\n"+
        "Свойство onDragOver: "+btn.onDragOverProperty().getValue()+"\n"+
        "Свойство onInputMethodTextChanged: "+btn.onInputMethodTextChangedProperty().getValue()+"\n"+
        "Свойство onKeyPressed: "+btn.onKeyPressedProperty().getValue()+"\n"+
        "Свойство onKeyReleased: "+btn.onKeyReleasedProperty().getValue()+"\n"+
        "Свойство onKeyTyped: "+btn.onKeyTypedProperty().getValue()+"\n"+
        "Свойство onMouseClicked: "+btn.onMouseClickedProperty().getValue()+"\n"+
        "Свойство onMouseDragged: "+btn.onMouseDraggedProperty().getValue()+"\n"+  
        "Свойство onMouseEntered: "+btn.onMouseEnteredProperty().getValue()+"\n"+   
        "Свойство onMouseExited: "+btn.onMouseExitedProperty().getValue()+"\n"+        
        "Свойство onMouseMoved: "+btn.onMouseMovedProperty().getValue()+"\n"+
        "Свойство onMousePressed: "+btn.onMousePressedProperty().getValue()+"\n"+   
        "Свойство onMouseReleased: "+btn.onMouseReleasedProperty().getValue()+"\n"+ 
        "Свойство opacity: "+btn.opacityProperty().getValue()+"\n"+       
        "Свойство parent: "+btn.parentProperty().getValue()+"\n"+ 
        "Свойство pickOnBounds: "+btn.pickOnBoundsProperty().getValue()+"\n"+   
        "Свойство pressed: "+btn.pressedProperty().getValue()+"\n"+ 
        "Свойство rotate: "+btn.rotateProperty().getValue()+"\n"+  
        "Свойство rotationAxis: "+btn.rotationAxisProperty().getValue()+"\n"+        
        "Свойство scaleX: "+btn.scaleXProperty().getValue()+"\n"+
        "Свойство scaleY: "+btn.scaleYProperty().getValue()+"\n"+     
        "Свойство scaleZ: "+btn.scaleZProperty().getValue()+"\n"+        
        "Свойство scene: "+btn.sceneProperty().getValue()+"\n"+     
        "Свойство style: "+btn.styleProperty().getValue()+"\n"+        
        "Свойство translateX: "+btn.translateXProperty().getValue()+"\n"+    
        "Свойство translateY: "+btn.translateYProperty().getValue()+"\n"+        
        "Свойство translateZ: "+btn.translateZProperty().getValue()+"\n"+  
        "Свойство visible: "+btn.visibleProperty().getValue()+"\n"+            
        "\n"+        
        "Свойства, унаследованные от класса Parent:"+"\n"+ 
        "Свойство needsLayout: "+btn.needsLayoutProperty().getValue()+"\n"+  
        "\n"+        
        "Свойства, унаследованные от класса Control:"+"\n"+ 
        "Свойство contextMenu: "+btn.contextMenuProperty().getValue()+"\n"+ 
        "Свойство height: "+btn.heightProperty().getValue()+"\n"+        
        "Свойство maxHeight: "+btn.maxHeightProperty().getValue()+"\n"+  
        "Свойство maxWidth: "+btn.maxWidthProperty().getValue()+"\n"+        
        "Свойство minHeight: "+btn.minHeightProperty().getValue()+"\n"+    
        "Свойство minWidth: "+btn.minWidthProperty().getValue()+"\n"+        
        "Свойство prefHeight: "+btn.prefHeightProperty().getValue()+"\n"+ 
        "Свойство prefWidth: "+btn.prefWidthProperty().getValue()+"\n"+                 
        "Свойство skin: "+btn.skinProperty().getValue()+"\n"+
        "Свойство tooltip: "+btn.onMouseClickedProperty().getValue()+"\n"+
        "Свойство width: "+btn.widthProperty().getValue()+"\n"+ 
        "\n"+        
        "Свойства, унаследованные от класса Labeled"+"\n"+ 
        "Свойство alignment: "+btn.alignmentProperty().getValue()+"\n"+        
        "Свойство contentDisplay: "+btn.contentDisplayProperty().getValue()+"\n"+    
        "Свойство font: "+btn.fontProperty().getValue()+"\n"+        
        "Свойство graphic: "+btn.graphicProperty().getValue()+"\n"+    
        "Свойство graphicTextGap: "+btn.graphicTextGapProperty().getValue()+"\n"+        
        "Свойство labelPadding: "+btn.labelPaddingProperty().getValue()+"\n"+  
        "Свойство mnemonicParsing: "+btn.mnemonicParsingProperty().getValue()+"\n"+        
        "Свойство textAlignment: "+btn.textAlignmentProperty().getValue()+"\n"+ 
        "Свойство textFill: "+btn.textFillProperty().getValue()+"\n"+        
        "Свойство textOverrun: "+btn.textOverrunProperty().getValue()+"\n"+  
        "Свойство text: "+btn.textProperty().getValue()+"\n"+        
        "Свойство underline: "+btn.underlineProperty().getValue()+"\n"+   
        "Свойство wrapText: "+btn.wrapTextProperty().getValue()+"\n"+  
        "\n"+       
        "Свойства, унаследованные от класса ButtonBase:"+"\n"+   
        "Свойство armed: "+btn.armedProperty().getValue()+"\n"+        
        "Свойство onAction: "+btn.onActionProperty().getValue()+"\n"+   
        "\n"+       
        "Свойства класса Button"+"\n"+        
        "Свойство cancelButton: "+btn.cancelButtonProperty().getValue()+"\n"+        
        "Свойство defaultButton: "+btn.defaultButtonProperty().getValue()+"\n"+        
                "\n");         
            }
        });
        //Button btnON = new Button();
        btnON=ButtonBuilder.create().build();
        btnON.setLayoutX(20);
        btnON.setLayoutY(150);
        btnON.setText("Установить свойства");
        btnON.setStyle("-fx-font:  bold italic 12pt Arial;-fx-text-fill: #660000; -fx-background-color: #ff99ff; -fx-border-width: 3px; -fx-border-radius: 30;-fx-background-radius: 30;-fx-border-color: #660066;" );       
        btnON.setPrefSize(200,30);       
        btnON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {               
        btn.setBlendMode(BlendMode.DARKEN);        
        javafx.scene.shape.Circle clip =new javafx.scene.shape.Circle(75,53,80);         
        //btn.setClip(clip);        
        btn.setCursor(Cursor.CLOSED_HAND);  
        DropShadow effect=new DropShadow();
        effect.setOffsetX(10);
        effect.setOffsetY(10);        
        btn.setEffect(effect);
        //btn.setManaged(false); 
        //btn.setMouseTransparent(true);
        btn.setOpacity(0.5);        
        btn.setRotate(10);
        btn.setLayoutX(80);        
        btn.setScaleX(1.8);         
        btn.setLayoutY(170);        
        btn.setTranslateZ(-50);               
        btn.setPrefSize(150,100); 
        btn.setTooltip(new Tooltip("Это кнопка тестирования свойств класса Button"));
        Image im=new Image(this.getClass().getResource("image.png").toString());
        ImageView imv=new ImageView(im);
        imv.setFitHeight(50);
        imv.setFitWidth(50);         
        btn.setGraphic(imv);
        btn.setStyle("-fx-font:  bold italic 10pt Helvetica;");       
        //btn.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 10));
        btn.setAlignment(Pos.CENTER);
        btn.setContentDisplay(ContentDisplay.RIGHT);
        btn.setUnderline(true);               
        btn.setWrapText(true); 
       // btn.setCancelButton(true); 
       //btn.toBack();
          }
        });          
        root.getChildren().add(btnON); 
        root.getChildren().add(btn);         
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

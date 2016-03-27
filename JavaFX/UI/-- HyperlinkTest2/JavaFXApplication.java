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
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
    
 Hyperlink hlink; 
 Stage primaryStage; 
 
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primary) {
        primaryStage=primary;
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene sceneOne = new Scene(root, 300, 300, Color.LIGHTGREEN);              
        primaryStage.setScene(sceneOne);            
        primaryStage.show();        
        hlink = new Hyperlink("Тестировать свойства");
        //hlink=HyperlinkBuilder.create().build();
        hlink.setLayoutX(20);
        hlink.setLayoutY(20);              
        hlink.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        Group root = new Group();    
        Scene sceneTwo = new Scene(root, 300, 300, Color.AQUAMARINE);
        ScrollPane scp = new ScrollPane();
        Text text = new Text(
        "Свойства, унаследованные от класса Node:"+"\n"+
        "Свойство blendMode: "+hlink.blendModeProperty().getValue()+"\n"+
        "Свойство boundsInLocal: "+hlink.boundsInLocalProperty().getValue()+"\n"+
        "Свойство boundsInParent: "+hlink.boundsInParentProperty().getValue()+"\n"+
        "Свойство cacheHint: "+hlink.cacheHintProperty().getValue()+"\n"+
        "Свойство cache: "+hlink.cacheProperty().getValue()+"\n"+
        "Свойство clip: "+hlink.clipProperty().getValue()+"\n"+
        "Свойство cursor: "+hlink.cursorProperty().getValue()+"\n"+
        "Свойство depthTest: "+hlink.depthTestProperty().getValue()+"\n"+
        "Свойство disabled: "+hlink.disabledProperty().getValue()+"\n"+
        "Свойство disable: "+hlink.disableProperty().getValue()+"\n"+
        "Свойство effect: "+hlink.effectProperty().getValue()+"\n"+
        "Свойство eventDispatcher: "+hlink.eventDispatcherProperty().getValue()+"\n"+
        "Свойство focused: "+hlink.focusedProperty().getValue()+"\n"+
        "Свойство focusTraversable: "+hlink.focusTraversableProperty().getValue()+"\n"+
        "Свойство hover: "+hlink.hoverProperty().getValue()+"\n"+
        "Свойство id: "+hlink.idProperty().getValue()+"\n"+
        "Свойство inputMethodRequests: "+hlink.inputMethodRequestsProperty().getValue()+"\n"+
        "Свойство layoutBounds: "+hlink.layoutBoundsProperty().getValue()+"\n"+
        "Свойство layoutX: "+hlink.layoutXProperty().getValue()+"\n"+
        "Свойство layoutY: "+hlink.layoutYProperty().getValue()+"\n"+
        "Свойство managed: "+hlink.managedProperty().getValue()+"\n"+
        "Свойство mouseTransparent: "+hlink.mouseTransparentProperty().getValue()+"\n"+
        "Свойство onDragDetected: "+hlink.onDragDetectedProperty().getValue()+"\n"+
        "Свойство onDragDone: "+hlink.onDragDoneProperty().getValue()+"\n"+
        "Свойство onDragDropped: "+hlink.onDragDroppedProperty().getValue()+"\n"+
        "Свойство onDragEntered: "+hlink.onDragEnteredProperty().getValue()+"\n"+
        "Свойство onDragExited: "+hlink.onDragExitedProperty().getValue()+"\n"+
        "Свойство onDragOver: "+hlink.onDragOverProperty().getValue()+"\n"+
        "Свойство onInputMethodTextChanged: "+hlink.onInputMethodTextChangedProperty().getValue()+"\n"+
        "Свойство onKeyPressed: "+hlink.onKeyPressedProperty().getValue()+"\n"+
        "Свойство onKeyReleased: "+hlink.onKeyReleasedProperty().getValue()+"\n"+
        "Свойство onKeyTyped: "+hlink.onKeyTypedProperty().getValue()+"\n"+
        "Свойство onMouseClicked: "+hlink.onMouseClickedProperty().getValue()+"\n"+
        "Свойство onMouseDragged: "+hlink.onMouseDraggedProperty().getValue()+"\n"+  
        "Свойство onMouseEntered: "+hlink.onMouseEnteredProperty().getValue()+"\n"+   
        "Свойство onMouseExited: "+hlink.onMouseExitedProperty().getValue()+"\n"+        
        "Свойство onMouseMoved: "+hlink.onMouseMovedProperty().getValue()+"\n"+
        "Свойство onMousePressed: "+hlink.onMousePressedProperty().getValue()+"\n"+   
        "Свойство onMouseReleased: "+hlink.onMouseReleasedProperty().getValue()+"\n"+ 
        "Свойство opacity: "+hlink.opacityProperty().getValue()+"\n"+       
        "Свойство parent: "+hlink.parentProperty().getValue()+"\n"+ 
        "Свойство pickOnBounds: "+hlink.pickOnBoundsProperty().getValue()+"\n"+   
        "Свойство pressed: "+hlink.pressedProperty().getValue()+"\n"+ 
        "Свойство rotate: "+hlink.rotateProperty().getValue()+"\n"+  
        "Свойство rotationAxis: "+hlink.rotationAxisProperty().getValue()+"\n"+        
        "Свойство scaleX: "+hlink.scaleXProperty().getValue()+"\n"+
        "Свойство scaleY: "+hlink.scaleYProperty().getValue()+"\n"+     
        "Свойство scaleZ: "+hlink.scaleZProperty().getValue()+"\n"+        
        "Свойство scene: "+hlink.sceneProperty().getValue()+"\n"+     
        "Свойство style: "+hlink.styleProperty().getValue()+"\n"+        
        "Свойство translateX: "+hlink.translateXProperty().getValue()+"\n"+    
        "Свойство translateY: "+hlink.translateYProperty().getValue()+"\n"+        
        "Свойство translateZ: "+hlink.translateZProperty().getValue()+"\n"+  
        "Свойство visible: "+hlink.visibleProperty().getValue()+"\n"+            
        "\n"+        
        "Свойства, унаследованные от класса Parent:"+"\n"+ 
        "Свойство needsLayout: "+hlink.needsLayoutProperty().getValue()+"\n"+  
        "\n"+        
        "Свойства, унаследованные от класса Control:"+"\n"+ 
        "Свойство contextMenu: "+hlink.contextMenuProperty().getValue()+"\n"+ 
        "Свойство height: "+hlink.heightProperty().getValue()+"\n"+        
        "Свойство maxHeight: "+hlink.maxHeightProperty().getValue()+"\n"+  
        "Свойство maxWidth: "+hlink.maxWidthProperty().getValue()+"\n"+        
        "Свойство minHeight: "+hlink.minHeightProperty().getValue()+"\n"+    
        "Свойство minWidth: "+hlink.minWidthProperty().getValue()+"\n"+        
        "Свойство prefHeight: "+hlink.prefHeightProperty().getValue()+"\n"+ 
        "Свойство prefWidth: "+hlink.prefWidthProperty().getValue()+"\n"+                 
        "Свойство skin: "+hlink.skinProperty().getValue()+"\n"+
        "Свойство tooltip: "+hlink.onMouseClickedProperty().getValue()+"\n"+
        "Свойство width: "+hlink.widthProperty().getValue()+"\n"+ 
        "\n"+        
        "Свойства, унаследованные от класса Labeled:"+"\n"+ 
        "Свойство alignment: "+hlink.alignmentProperty().getValue()+"\n"+        
        "Свойство contentDisplay: "+hlink.contentDisplayProperty().getValue()+"\n"+    
        "Свойство font: "+hlink.fontProperty().getValue()+"\n"+        
        "Свойство graphic: "+hlink.graphicProperty().getValue()+"\n"+    
        "Свойство graphicTextGap: "+hlink.graphicTextGapProperty().getValue()+"\n"+        
        "Свойство labelPadding: "+hlink.labelPaddingProperty().getValue()+"\n"+  
        "Свойство mnemonicParsing: "+hlink.mnemonicParsingProperty().getValue()+"\n"+        
        "Свойство textAlignment: "+hlink.textAlignmentProperty().getValue()+"\n"+ 
        "Свойство textFill: "+hlink.textFillProperty().getValue()+"\n"+        
        "Свойство textOverrun: "+hlink.textOverrunProperty().getValue()+"\n"+  
        "Свойство text: "+hlink.textProperty().getValue()+"\n"+        
        "Свойство underline: "+hlink.underlineProperty().getValue()+"\n"+   
        "Свойство wrapText: "+hlink.wrapTextProperty().getValue()+"\n"+  
        "\n"+       
        "Свойства, унаследованные от класса ButtonBase:"+"\n"+   
        "Свойство armed: "+hlink.armedProperty().getValue()+"\n"+        
        "Свойство onAction: "+hlink.onActionProperty().getValue()+"\n"+   
        "\n"+       
        "Свойства класса Hyperlink:"+"\n"+        
        "Свойство visited: "+hlink.visitedProperty().getValue()+"\n"+                 
                "\n");  
        scp.setContent(text);
        scp.setPrefSize(290, 250); 
        scp.setLayoutX(5);
        scp.setLayoutY(5);
        Button btn =new Button();
        btn.setLayoutX(20);
        btn.setLayoutY(270);
        btn.setText("Вернуться");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        hlink.setTextFill(Color.RED);
        Group root = new Group();
        root.getChildren().add(hlink);
        Scene sceneOne = new Scene(root, 300, 300, Color.LIGHTGREEN);       
        primaryStage.setScene(sceneOne);         
        }}); 
        root.getChildren().add(scp);
        root.getChildren().add(btn);
        primaryStage.setScene(sceneTwo);          
            }
        });
        
        hlink.setBlendMode(BlendMode.HARD_LIGHT);             
        hlink.setCursor(Cursor.CLOSED_HAND);  
        DropShadow effect=new DropShadow();
        effect.setOffsetX(10);
        effect.setOffsetY(10);        
        hlink.setEffect(effect);                         
        hlink.setPrefSize(200,50); 
        hlink.setTooltip(new Tooltip("Это ссылка тестирования свойств класса Hyperlink"));
        Image im=new Image(this.getClass().getResource("image.png").toString());
        ImageView imv=new ImageView(im);
        imv.setFitHeight(50);
        imv.setFitWidth(50);         
        hlink.setGraphic(imv);
        hlink.setStyle("-fx-font:  bold italic 14pt Georgia;");     
        hlink.setAlignment(Pos.CENTER);
        hlink.setTextAlignment(TextAlignment.CENTER);
        hlink.setContentDisplay(ContentDisplay.RIGHT);
        hlink.setWrapText(true);
        hlink.setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(!hlink.isVisited()){
        hlink.setTextFill(Color.BLUE);  
        hlink.setStyle("-fx-font:  bold italic 16pt Georgia;");  }  
        }}); 
        hlink.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
             if(!hlink.isVisited()){
        hlink.setTextFill(Color.RED);  
        hlink.setStyle("-fx-font:  bold italic 16pt Georgia;"); }   
        }}); 
        hlink.setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
             if(!hlink.isVisited()){
        hlink.setTextFill(Color.BLACK);  
        hlink.setStyle("-fx-font:  bold italic 14pt Georgia;");  }  
        }});         
        root.getChildren().add(hlink); 
    }
}

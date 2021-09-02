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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
CheckBox ckb; 
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREEN);
        ckb = new CheckBox("Тестировать свойства");
        //ckb=CheckBoxBuilder.create().build();
        ckb.setLayoutX(20);
        ckb.setLayoutY(20);              
        ckb.setOnMousePressed(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
        System.out.println("Свойства, унаследованные от класса Node:"+"\n"+
        "Свойство blendMode: "+ckb.blendModeProperty().getValue()+"\n"+
        "Свойство boundsInLocal: "+ckb.boundsInLocalProperty().getValue()+"\n"+
        "Свойство boundsInParent: "+ckb.boundsInParentProperty().getValue()+"\n"+
        "Свойство cacheHint: "+ckb.cacheHintProperty().getValue()+"\n"+
        "Свойство cache: "+ckb.cacheProperty().getValue()+"\n"+
        "Свойство clip: "+ckb.clipProperty().getValue()+"\n"+
        "Свойство cursor: "+ckb.cursorProperty().getValue()+"\n"+
        "Свойство depthTest: "+ckb.depthTestProperty().getValue()+"\n"+
        "Свойство disabled: "+ckb.disabledProperty().getValue()+"\n"+
        "Свойство disable: "+ckb.disableProperty().getValue()+"\n"+
        "Свойство effect: "+ckb.effectProperty().getValue()+"\n"+
        "Свойство eventDispatcher: "+ckb.eventDispatcherProperty().getValue()+"\n"+
        "Свойство focused: "+ckb.focusedProperty().getValue()+"\n"+
        "Свойство focusTraversable: "+ckb.focusTraversableProperty().getValue()+"\n"+
        "Свойство hover: "+ckb.hoverProperty().getValue()+"\n"+
        "Свойство id: "+ckb.idProperty().getValue()+"\n"+
        "Свойство inputMethodRequests: "+ckb.inputMethodRequestsProperty().getValue()+"\n"+
        "Свойство layoutBounds: "+ckb.layoutBoundsProperty().getValue()+"\n"+
        "Свойство layoutX: "+ckb.layoutXProperty().getValue()+"\n"+
        "Свойство layoutY: "+ckb.layoutYProperty().getValue()+"\n"+
        "Свойство managed: "+ckb.managedProperty().getValue()+"\n"+
        "Свойство mouseTransparent: "+ckb.mouseTransparentProperty().getValue()+"\n"+
        "Свойство onDragDetected: "+ckb.onDragDetectedProperty().getValue()+"\n"+
        "Свойство onDragDone: "+ckb.onDragDoneProperty().getValue()+"\n"+
        "Свойство onDragDropped: "+ckb.onDragDroppedProperty().getValue()+"\n"+
        "Свойство onDragEntered: "+ckb.onDragEnteredProperty().getValue()+"\n"+
        "Свойство onDragExited: "+ckb.onDragExitedProperty().getValue()+"\n"+
        "Свойство onDragOver: "+ckb.onDragOverProperty().getValue()+"\n"+
        "Свойство onInputMethodTextChanged: "+ckb.onInputMethodTextChangedProperty().getValue()+"\n"+
        "Свойство onKeyPressed: "+ckb.onKeyPressedProperty().getValue()+"\n"+
        "Свойство onKeyReleased: "+ckb.onKeyReleasedProperty().getValue()+"\n"+
        "Свойство onKeyTyped: "+ckb.onKeyTypedProperty().getValue()+"\n"+
        "Свойство onMouseClicked: "+ckb.onMouseClickedProperty().getValue()+"\n"+
        "Свойство onMouseDragged: "+ckb.onMouseDraggedProperty().getValue()+"\n"+  
        "Свойство onMouseEntered: "+ckb.onMouseEnteredProperty().getValue()+"\n"+   
        "Свойство onMouseExited: "+ckb.onMouseExitedProperty().getValue()+"\n"+        
        "Свойство onMouseMoved: "+ckb.onMouseMovedProperty().getValue()+"\n"+
        "Свойство onMousePressed: "+ckb.onMousePressedProperty().getValue()+"\n"+   
        "Свойство onMouseReleased: "+ckb.onMouseReleasedProperty().getValue()+"\n"+ 
        "Свойство opacity: "+ckb.opacityProperty().getValue()+"\n"+       
        "Свойство parent: "+ckb.parentProperty().getValue()+"\n"+ 
        "Свойство pickOnBounds: "+ckb.pickOnBoundsProperty().getValue()+"\n"+   
        "Свойство pressed: "+ckb.pressedProperty().getValue()+"\n"+ 
        "Свойство rotate: "+ckb.rotateProperty().getValue()+"\n"+  
        "Свойство rotationAxis: "+ckb.rotationAxisProperty().getValue()+"\n"+        
        "Свойство scaleX: "+ckb.scaleXProperty().getValue()+"\n"+
        "Свойство scaleY: "+ckb.scaleYProperty().getValue()+"\n"+     
        "Свойство scaleZ: "+ckb.scaleZProperty().getValue()+"\n"+        
        "Свойство scene: "+ckb.sceneProperty().getValue()+"\n"+     
        "Свойство style: "+ckb.styleProperty().getValue()+"\n"+        
        "Свойство translateX: "+ckb.translateXProperty().getValue()+"\n"+    
        "Свойство translateY: "+ckb.translateYProperty().getValue()+"\n"+        
        "Свойство translateZ: "+ckb.translateZProperty().getValue()+"\n"+  
        "Свойство visible: "+ckb.visibleProperty().getValue()+"\n"+            
        "\n"+        
        "Свойства, унаследованные от класса Parent:"+"\n"+ 
        "Свойство needsLayout: "+ckb.needsLayoutProperty().getValue()+"\n"+  
        "\n"+        
        "Свойства, унаследованные от класса Control:"+"\n"+ 
        "Свойство contextMenu: "+ckb.contextMenuProperty().getValue()+"\n"+ 
        "Свойство height: "+ckb.heightProperty().getValue()+"\n"+        
        "Свойство maxHeight: "+ckb.maxHeightProperty().getValue()+"\n"+  
        "Свойство maxWidth: "+ckb.maxWidthProperty().getValue()+"\n"+        
        "Свойство minHeight: "+ckb.minHeightProperty().getValue()+"\n"+    
        "Свойство minWidth: "+ckb.minWidthProperty().getValue()+"\n"+        
        "Свойство prefHeight: "+ckb.prefHeightProperty().getValue()+"\n"+ 
        "Свойство prefWidth: "+ckb.prefWidthProperty().getValue()+"\n"+                 
        "Свойство skin: "+ckb.skinProperty().getValue()+"\n"+
        "Свойство tooltip: "+ckb.onMouseClickedProperty().getValue()+"\n"+
        "Свойство width: "+ckb.widthProperty().getValue()+"\n"+ 
        "\n"+        
        "Свойства, унаследованные от класса Labeled:"+"\n"+ 
        "Свойство alignment: "+ckb.alignmentProperty().getValue()+"\n"+        
        "Свойство contentDisplay: "+ckb.contentDisplayProperty().getValue()+"\n"+    
        "Свойство font: "+ckb.fontProperty().getValue()+"\n"+        
        "Свойство graphic: "+ckb.graphicProperty().getValue()+"\n"+    
        "Свойство graphicTextGap: "+ckb.graphicTextGapProperty().getValue()+"\n"+        
        "Свойство labelPadding: "+ckb.labelPaddingProperty().getValue()+"\n"+  
        "Свойство mnemonicParsing: "+ckb.mnemonicParsingProperty().getValue()+"\n"+        
        "Свойство textAlignment: "+ckb.textAlignmentProperty().getValue()+"\n"+ 
        "Свойство textFill: "+ckb.textFillProperty().getValue()+"\n"+        
        "Свойство textOverrun: "+ckb.textOverrunProperty().getValue()+"\n"+  
        "Свойство text: "+ckb.textProperty().getValue()+"\n"+        
        "Свойство underline: "+ckb.underlineProperty().getValue()+"\n"+   
        "Свойство wrapText: "+ckb.wrapTextProperty().getValue()+"\n"+  
        "\n"+       
        "Свойства, унаследованные от класса ButtonBase:"+"\n"+   
        "Свойство armed: "+ckb.armedProperty().getValue()+"\n"+        
        "Свойство onAction: "+ckb.onActionProperty().getValue()+"\n"+   
        "\n"+       
        "Свойства класса CheckBox:"+"\n"+        
        "Свойство allowIndeterminate: "+ckb.allowIndeterminateProperty().getValue()+"\n"+        
        "Свойство indeterminate: "+ckb.indeterminateProperty().getValue()+"\n"+ 
        "Свойство selected: "+ckb.selectedProperty().getValue()+"\n"+         
                "\n");         
            }
        });
        Button btnON = new Button();        
        btnON.setLayoutX(20);
        btnON.setLayoutY(100);
        btnON.setText("Установить свойства");
        btnON.setStyle("-fx-font: bold italic 12pt Arial;-fx-text-fill: white;-fx-background-color: #0000cc;-fx-border-width: 3px; -fx-border-color:#6699ff #000066 #000066 #6699ff;" );       
        btnON.setPrefSize(200,30);       
        btnON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) { 
                if(ckb.selectedProperty().getValue()==true){
        ckb.setBlendMode(BlendMode.HARD_LIGHT);        
        Rectangle clip =new Rectangle(0,15,15,20);         
        //ckb.setClip(clip);        
        ckb.setCursor(Cursor.CROSSHAIR);  
        DropShadow effect=new DropShadow();
        effect.setOffsetX(5);
        effect.setOffsetY(10);        
        ckb.setEffect(effect);
        //ckb.setManaged(false); 
        //ckb.setMouseTransparent(true);
        //ckb.setOpacity(0.5);            
        ckb.setLayoutX(80);        
        ckb.setScaleX(1.8);              
        ckb.setTranslateZ(-50);               
        ckb.setPrefSize(150,50); 
        ckb.setTooltip(new Tooltip("Это переключатель тестирования свойств класса CheckBox"));
        Image im=new Image(this.getClass().getResource("image.png").toString());
        ImageView imv=new ImageView(im);
        imv.setFitHeight(50);
        imv.setFitWidth(50);         
        ckb.setGraphic(imv);
        ckb.setStyle("-fx-font:  bold italic 10pt Helvetica;");         
        ckb.setAlignment(Pos.CENTER);
        ckb.setContentDisplay(ContentDisplay.RIGHT);
        ckb.setUnderline(true);               
        ckb.setWrapText(true); 
        ckb.setAllowIndeterminate(true); 
        //ckb.toBack();
        //ckb.setTranslateY(50);
        //ckb.setRotate(30);
          }}
        });             
        root.getChildren().add(btnON);
        root.getChildren().add(ckb);         
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

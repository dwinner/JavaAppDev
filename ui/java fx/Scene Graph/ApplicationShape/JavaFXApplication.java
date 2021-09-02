/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
  
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.WHITESMOKE);
        primaryStage.setScene(scene);
        primaryStage.show();      
        
        FlowPane flow=new FlowPane();
        flow.setLayoutX(20);
        flow.setLayoutY(20);
        flow.setCursor(Cursor.CROSSHAIR);        
        flow.setOrientation(Orientation.HORIZONTAL);
        flow.setPrefWrapLength(500);
        flow.setHgap(10);
        flow.setVgap(10);
        
        Arc arcOpen=new Arc();
        arcOpen.setFill(Color.KHAKI);
        arcOpen.setStroke(Color.OLIVE);
        arcOpen.setStrokeWidth(5);
        arcOpen.setStrokeLineCap(StrokeLineCap.ROUND);       
        arcOpen.setStrokeType(StrokeType.OUTSIDE);
        arcOpen.setSmooth(true);        
        arcOpen.setCenterX(50);
        arcOpen.setCenterY(50);
        arcOpen.setLength(130);
        arcOpen.setRadiusX(80);
        arcOpen.setRadiusY(50);
        arcOpen.setStartAngle(90);
        arcOpen.setType(ArcType.OPEN);
        
        Arc arcChord=new Arc();
        arcChord.setFill(Color.KHAKI);
        arcChord.setStroke(Color.OLIVE);
        arcChord.setStrokeWidth(5);        
        arcChord.setStrokeType(StrokeType.OUTSIDE);
        arcChord.setStrokeLineJoin(StrokeLineJoin.MITER);
        arcChord.setSmooth(true);
        arcChord.setCenterX(50);
        arcChord.setCenterY(50);
        arcChord.setLength(130);
        arcChord.setRadiusX(50);
        arcChord.setRadiusY(30);
        arcChord.setStartAngle(90);
        arcChord.setType(ArcType.CHORD);
        
        Arc arcRound=new Arc();
        arcRound.setFill(Color.KHAKI);
        arcRound.setStroke(Color.OLIVE);
        arcRound.setStrokeWidth(5);            
        arcRound.setStrokeType(StrokeType.OUTSIDE);
        arcRound.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        arcRound.setSmooth(true);
        arcRound.setCenterX(50);
        arcRound.setCenterY(50);
        arcRound.setLength(130);
        arcRound.setRadiusX(50);
        arcRound.setRadiusY(30);
        arcRound.setStartAngle(90);
        arcRound.setType(ArcType.ROUND);
        
        Line line = new Line();        
        line.setStroke(Color.OLIVE);
        line.setStrokeWidth(3);
        line.setStrokeLineCap(StrokeLineCap.ROUND);       
        line.setStrokeType(StrokeType.OUTSIDE);        
        line.getStrokeDashArray().addAll(5.0,10.0);
        line.setStrokeDashOffset(5);        
        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(100);
        line.setEndY(100);
        
        Circle circle=new Circle();
        circle.setFill(Color.KHAKI);
        circle.setStroke(Color.OLIVE);
        circle.setStrokeWidth(5);            
        circle.setStrokeType(StrokeType.OUTSIDE);
        circle.setCenterX(50);
        circle.setCenterY(50);
        circle.setRadius(50);
        
        CubicCurve cubic = new CubicCurve();
        cubic.setFill(Color.KHAKI);
        cubic.setStroke(Color.OLIVE);
        cubic.setStrokeWidth(3);
        cubic.setStrokeLineCap(StrokeLineCap.ROUND);       
        cubic.setStrokeType(StrokeType.CENTERED);
        cubic.setStartX(0);
        cubic.setStartY(80);
        cubic.setEndX(150);
        cubic.setEndY(80);
        cubic.setControlX1(30);
        cubic.setControlY1(10);
        cubic.setControlX2(70);
        cubic.setControlY2(150);
        
        QuadCurve quad = new QuadCurve();
        quad.setFill(Color.KHAKI);
        quad.setStroke(Color.OLIVE);
        quad.setStrokeWidth(3);
        quad.setStrokeLineCap(StrokeLineCap.ROUND);       
        quad.setStrokeType(StrokeType.CENTERED);
        quad.setStartX(0);
        quad.setStartY(80);
        quad.setEndX(150);
        quad.setEndY(80);
        quad.setControlX(70);
        quad.setControlY(10);
        
        Ellipse ellipse = new Ellipse();        
        ellipse.setFill(Color.KHAKI);
        ellipse.setStroke(Color.OLIVE);
        ellipse.setStrokeWidth(5);            
        ellipse.setStrokeType(StrokeType.OUTSIDE);
        ellipse.setCenterX(50);
        ellipse.setCenterY(50);
        ellipse.setRadiusX(80);
        ellipse.setRadiusY(25);
        
        Rectangle rec = new Rectangle();
        rec.setFill(Color.KHAKI);
        rec.setStroke(Color.OLIVE);
        rec.setStrokeWidth(5);            
        rec.setStrokeType(StrokeType.OUTSIDE);
        rec.setHeight(50);
        rec.setWidth(100);
        rec.setX(20);
        rec.setY(20);
        rec.setArcHeight(10);
        rec.setArcWidth(20);
        
        Polyline polyline = new Polyline();
        polyline.setFill(Color.KHAKI);
        polyline.setStroke(Color.OLIVE);
        polyline.setStrokeWidth(5);
        polyline.setStrokeLineCap(StrokeLineCap.ROUND);
        polyline.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        polyline.setStrokeType(StrokeType.CENTERED);
        polyline.getPoints().addAll(10.0,10.0,30.0,50.0,60.0,10.0,100.0,80.0,150.0,0.0);
        
        Polygon polygon = new Polygon();
        polygon.setFill(Color.KHAKI);
        polygon.setStroke(Color.OLIVE);
        polygon.setStrokeWidth(5);        
        polygon.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        polygon.setStrokeType(StrokeType.CENTERED);
        polygon.getPoints().addAll(10.0,10.0,30.0,50.0,60.0,10.0,100.0,80.0,150.0,0.0);
        
        Path path = new Path();
        path.setFill(Color.KHAKI);
        path.setStroke(Color.OLIVE);
        path.setStrokeWidth(5);            
        path.setStrokeType(StrokeType.OUTSIDE);        
        MoveTo moveTo = new MoveTo();
        moveTo.setX(50);
        moveTo.setY(100);
        CubicCurveTo cubicToL = new CubicCurveTo();
        cubicToL.setControlX1(0);
        cubicToL.setControlY1(30);
        cubicToL.setControlX2(25);
        cubicToL.setControlY2(0);
        cubicToL.setX(50);
        cubicToL.setY(30);
        CubicCurveTo cubicToR = new CubicCurveTo();
        cubicToR.setControlX1(75);
        cubicToR.setControlY1(0);
        cubicToR.setControlX2(100);
        cubicToR.setControlY2(30);
        cubicToR.setX(50);
        cubicToR.setY(100);
        ClosePath close=new ClosePath();
        path.getElements().addAll(moveTo, cubicToL, cubicToR,close);
        
       /* Path path = new Path();
        path.setFill(Color.KHAKI);
        path.setStroke(Color.OLIVE);
        path.setStrokeWidth(5);            
        path.setStrokeType(StrokeType.OUTSIDE); 
        path.setFillRule(FillRule.EVEN_ODD);        
        MoveTo moveTo = new MoveTo();
        moveTo.setX(0);
        moveTo.setY(30);
        LineTo line1 = new LineTo();
        line1.setX(100);
        line1.setY(30);
        LineTo line2 = new LineTo();
        line2.setX(0);
        line2.setY(100);
        LineTo line3 = new LineTo();
        line3.setX(50);
        line3.setY(0);
        LineTo line4 = new LineTo();
        line4.setX(75);
        line4.setY(100);
        ClosePath close=new ClosePath();
        path.getElements().addAll(moveTo,line1,line2,line3,line4,close);*/
        
        SVGPath svg = new SVGPath();
        svg.setFill(Color.KHAKI);
        svg.setStroke(Color.OLIVE);
        svg.setStrokeWidth(5);            
        svg.setStrokeType(StrokeType.OUTSIDE);         
        svg.setFillRule(FillRule.EVEN_ODD);  
        svg.setContent("M0,30 L100,30 L0,100 L50,0 L75,100 Z");
        
        flow.getChildren().addAll(arcOpen,arcChord,arcRound,line,circle,cubic,quad,ellipse,rec,polyline,polygon,path,svg);
        
        root.getChildren().addAll(flow);
    }
}

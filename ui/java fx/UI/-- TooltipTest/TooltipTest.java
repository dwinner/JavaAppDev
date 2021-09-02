package pkg20.tooltiptest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * TooltipTest JavaFX компонент.
 * <p/>
 * @author JavaFx
 */
public class TooltipTest extends Application
{
    final static String[] rooms = new String[]
    {
        "Accomodation (BB)",
        "Half Board",
        "Late Check-out",
        "Extra Bed"
    };
    final static Integer[] rates = new Integer[]
    {
        100, 20, 10, 30
    };
    final CheckBox[] cbs = new CheckBox[rooms.length];
    final Label total = new Label("Total: $0");
    protected Integer sum = 0;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Tooltip Sample");
        primaryStage.setWidth(300);
        primaryStage.setHeight(150);

        total.setFont(new Font("Arial", 20));

        for (int i = 0; i < rooms.length; i++)
        {
            final CheckBox cb = cbs[i] = new CheckBox(rooms[i]);
            final Integer rate = rates[i];
            final Tooltip tooltip = new Tooltip("$" + rates[i].toString());
            tooltip.setFont(new Font("Arial", 16));
            cb.setTooltip(tooltip);
            cb.selectedProperty().addListener(new ChangeListener<Boolean>()
            {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean oldValue,
                    Boolean newValue)
                {                    
                    // sum += (cb.isSelected()) ? rate : -rate;
                    if (cb.isSelected())
                    {
                        sum += rate;
                    }
                    else
                    {
                        sum -= rate;
                    }
                    total.setText("Total: $" + sum.toString());
                }
            });
        }
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(cbs);
        vBox.setSpacing(5);
        HBox root = new HBox();
        root.getChildren().add(vBox);
        root.getChildren().add(total);
        root.setSpacing(40);
        root.setPadding(new Insets(20, 10, 10, 20));
        
        ((Group) scene.getRoot()).getChildren().add(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

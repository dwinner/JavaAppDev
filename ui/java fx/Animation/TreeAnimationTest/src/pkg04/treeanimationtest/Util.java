package pkg04.treeanimationtest;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;

public class Util
{
    public static void addChildToParent(final Group parent, final Node child)
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                parent.getChildren().add(child);
            }
        });
    }
}

package firstlineserviceapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Простая реализация потока, который не конфликтует с JavaFX Application Thread.
 * @author dwinner@inbox.ru
 * @see http://docs.oracle.com/javafx/2/api/index.html
 */
public class FirstLineServiceApp extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        final FirstLineService service = new FirstLineService();
        service.setUrl("http://www.google.com");
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>()
        {
            @Override
            public void handle(WorkerStateEvent workerStateEvent)
            {
                System.out.println("done: " + workerStateEvent.getSource().getValue());
            }
        });
        service.start();        
    }

    private static class FirstLineService extends Service<String>
    {
        private StringProperty url = new SimpleStringProperty();

        public final void setUrl(String value)
        {
            url.set(value);
        }

        public final String getUrl()
        {
            return url.get();
        }

        public final StringProperty urlProperty()
        {
            return url;
        }

        @Override
        protected Task<String> createTask()
        {
            final String _url = getUrl();
            return new Task<String>()
            {
                @Override
                protected String call() throws Exception
                {
                    String result = null;
                    try (BufferedReader in =
                          new BufferedReader(
                          new InputStreamReader(new URL(_url).openStream())))
                    {
                        return in.readLine();
                    }
                }
            };
        }
    }

}

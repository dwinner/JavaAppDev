package playvolumeviewtest;

import javafx.scene.layout.Pane;

/**
 * Интерфейс расположения UI-компонентов JFX.
 *
 * @author dwinner@inbox.ru
 */
public interface PlacedInPane
{
   /**
    * Расположение компонентов в контейнере.
    *
    * @return Контейнер.
    */
   Pane layoutComponents();
}

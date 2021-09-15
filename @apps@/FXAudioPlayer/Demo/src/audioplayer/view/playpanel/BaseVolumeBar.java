package audioplayer.view.playpanel;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

/**
 * Панель управления громкостью звука.
 *
 * @author oracle_pr1
 * @version 1.0.0 21.06.2012
 */
public class BaseVolumeBar extends HBox
{
    private Label volumeLabel;  // Метка звука
    private Slider volumeSlider;    // Слайдер управления звуком
    private ToggleButton soundOnOffButton;  // Кнопка включения/выключения звука

    /**
     * Получение метки звука.
     *
     * @return Метка звука
     */
    public Label getVolumeLabel()
    {
        return volumeLabel;
    }

    /**
     * Получение слайдера управления звуком.
     *
     * @return Слайдер управления звуком
     */
    public Slider getVolumeSlider()
    {
        return volumeSlider;
    }

    /**
     * Получение кнопки включения/выключения звука.
     *
     * @return Кнопка включения/выключения звука
     */
    public ToggleButton getSoundOnOffButton()
    {
        return soundOnOffButton;
    }

    /**
     * Инициализация панели управления громкостью звука.
     *
     * @param volume Метка звука
     * @param volumeSlider Слайдер управления звуком
     * @param switchOnOff Кнопка включения/выключения звука
     */
    public BaseVolumeBar(Label volume, Slider volumeSlider, ToggleButton switchOnOff)
    {
        volumeLabel = volume;
        this.volumeSlider = volumeSlider;
        soundOnOffButton = switchOnOff;
        getChildren().addAll(volumeLabel, volumeSlider, switchOnOff);
        configureVolumeBar();
    }

    /**
     * Настройка панели управления громкостью звука.
     */
    private void configureVolumeBar()
    {
        setSpacing(10);
        for (Node aNode : getChildrenUnmodifiable())
        {
            setMargin(aNode, new Insets(10, 10, 10, 10));
        }
    }
}

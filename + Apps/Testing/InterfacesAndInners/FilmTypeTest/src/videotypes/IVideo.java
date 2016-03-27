/**
 * Интрерфейс для Видео-форматов. 
 */
package videotypes;

/**
 * @author dwinner@inbox.ru
 *
 */
public interface IVideo
{
	/**
	 * Метод начала проигрывания.
	 * @param delay Задержка по времени перед началом.
	 */
	void startPlay(int delay);
	
	/**
	 * Окончание проигрывания. 
	 */
	void stopPlay();
	
	/**
	 * Перемотка на начало.
	 */
	void resetPlay();
}

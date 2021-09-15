/**
 * ���������� ��� �����-��������. 
 */
package videotypes;

/**
 * @author dwinner@inbox.ru
 *
 */
public interface IVideo
{
	/**
	 * ����� ������ ������������.
	 * @param delay �������� �� ������� ����� �������.
	 */
	void startPlay(int delay);
	
	/**
	 * ��������� ������������. 
	 */
	void stopPlay();
	
	/**
	 * ��������� �� ������.
	 */
	void resetPlay();
}

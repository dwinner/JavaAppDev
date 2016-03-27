package runner;

import java.io.IOException;
import videotypes.*;
import static java.lang.System.out;
import static java.lang.System.in;

public class VideoRunner
{
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException
	{
		int durat = 25;
		FilmType movie = new FilmType(FilmType.Genre.MYSTIC, durat);
		FilmType.ProducerType prod = movie.new ProducerType(3);
		prod.addProducer("David Barron");
		prod.addProducer("Devid Haiman");
		prod.addProducer("Tim Lewes");
		
		// Выводим информацию о фильме
		out.println("Movie info: " + movie);
		out.println("Producer info: " + prod);
		
		// Запускаем имитацию показа через 2 секунды
		movie.startPlay(2);
		Thread.sleep(10000);
		movie.stopPlay();
		Thread.sleep(5000);
		movie.startPlay();
		
		out.print("Press enter key to exit");
		in.read();
	}

}

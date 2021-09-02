package com.androidbook.triviaquiz;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class QuizScoresActivity extends QuizActivity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.scores);

      // Установка вкладок

      TabHost host = (TabHost) findViewById(R.id.TabHost1);
      host.setup();

      // Вкладка "All Scores"

      TabSpec allScoresTab = host.newTabSpec("allTab");
      allScoresTab.setIndicator(getResources().getString(R.string.all_scores),
            getResources().getDrawable(android.R.drawable.star_on));
      allScoresTab.setContent(R.id.ScrollViewAllScores);
      host.addTab(allScoresTab);

      // Вкладка "Friends scores tab"

      TabSpec friendScoresTab = host.newTabSpec("friendsTab");
      friendScoresTab.setIndicator(getResources().getString(R.string.friends_scores),
            getResources().getDrawable(android.R.drawable.star_on));
      friendScoresTab.setContent(R.id.ScrollViewFriendScores);
      host.addTab(friendScoresTab);

      host.setCurrentTabByTag("allTab"); // Установка вкладки по умолчанию

      // Извлекаем ссылки таблицы TableLayout

      TableLayout allScoresTable = (TableLayout) findViewById(R.id.TableLayout_AllScores);
      TableLayout friendsScoresTable = (TableLayout) findViewById(R.id.TableLayout_FriendScores);

      // Добавим к каждой таблице заголовок желтого цвета с именами столбцов

      initializeHeaderRow(allScoresTable);
      initializeHeaderRow(friendsScoresTable);

      XmlResourceParser mockAllScores = getResources().getXml(R.xml.allscores);
      XmlResourceParser mockFriendScores = getResources().getXml(R.xml.friendscores);
      try
      {
         processScores(allScoresTable, mockAllScores);
         processScores(friendsScoresTable, mockFriendScores);
      }
      catch (XmlPullParserException xmlPullParserEx)
      {
         Log.e(QuizScoresActivity.class.getName(), xmlPullParserEx.getLocalizedMessage(),
               xmlPullParserEx);
      }
      catch (IOException ioEx)
      {
         Log.e(QuizScoresActivity.class.getName(), ioEx.getLocalizedMessage(), ioEx);
      }
   }

   /**
    * Добавление заголовка TableRow к таблице TableLayout
    * 
    * @param scoreTable
    *           Таблица, к которой добавляется заголовок
    */
   private void initializeHeaderRow(TableLayout scoreTable)
   {
      // Создание заголовка таблицы

      TableRow headerRow = new TableRow(this);
      int textColor = getResources().getColor(R.color.logo_color);
      float textSize = getResources().getDimension(R.dimen.help_text_size);
      addTextToRowWithValues(headerRow, getResources().getString(R.string.username),
            textColor, textSize);
      addTextToRowWithValues(headerRow, getResources().getString(R.string.score),
            textColor, textSize);
      addTextToRowWithValues(headerRow, getResources().getString(R.string.rank),
            textColor, textSize);
      scoreTable.addView(headerRow);
   }

   /**
    * Заполнение информации таблицы из анализатора XML
    * 
    * @param scoreTable
    *           Таблица для заполнения
    * @param scores
    *           Стандартный парсер ресурсов XML, содержащий информацию о счетах
    * @throws XmlPullParserException
    *            Если в XML были ошибки разбора
    * @throws IOException
    *            Если возникли ошибки при чтении XML
    */
   private void processScores(TableLayout scoreTable, XmlResourceParser scores)
         throws XmlPullParserException, IOException
   {
      int eventType = -1;
      boolean foundScores = false;

      // Ищем записи счета в XML-файле

      while (eventType != XmlResourceParser.END_DOCUMENT)
      {
         if (eventType == XmlResourceParser.START_TAG)
         {
            // Получаем имя тэга (или scores или score)

            String strName = scores.getName();
            if (strName.equals("score"))
            {
               foundScores = true;
               String scoreValue = scores.getAttributeValue(null, "score");
               String scoreRank = scores.getAttributeValue(null, "rank");
               String scoreUserName = scores.getAttributeValue(null, "username");
               insertScoreRow(scoreTable, scoreValue, scoreRank, scoreUserName);
            }
         }
         eventType = scores.next();
      }

      // Обработка ситуации отсутствия счета

      if (!foundScores)
      {
         TableRow newTableRow = new TableRow(this);
         TextView noResultsTextView = new TextView(this);
         noResultsTextView.setText(getResources().getString(R.string.no_scores));
         newTableRow.addView(noResultsTextView);
         scoreTable.addView(newTableRow);
      }
   }

   /**
    * Добавление новой записи TableRow в таблицу TableLayout
    * 
    * @param scoreTableLayout
    *           Таблица, к которой добавляется новая запись
    * @param scoreValue
    *           Значение оценки
    * @param scoreRank
    *           Рейтинг
    * @param scoreUserName
    *           Имя (Ник) пользователя
    */
   private void insertScoreRow(TableLayout scoreTableLayout, String scoreValue,
         String scoreRank, String scoreUserName)
   {
      TableRow newTableRow = new TableRow(this);
      int textColor = getResources().getColor(R.color.title_color);
      float textSize = getResources().getDimension(R.dimen.help_text_size);
      addTextToRowWithValues(newTableRow, scoreUserName, textColor, textSize);
      addTextToRowWithValues(newTableRow, scoreValue, textColor, textSize);
      addTextToRowWithValues(newTableRow, scoreRank, textColor, textSize);
      scoreTableLayout.addView(newTableRow);
   }

   /**
    * Добавление к строке таблицы TableRow текста
    * 
    * @param aTableRow
    *           Строка таблицы, в которую нужно добавить текст
    * @param text
    *           Текст для добавления
    * @param textColor
    *           Идентификатор цвета текста
    * @param textSize
    *           Размер текста
    */
   private void addTextToRowWithValues(TableRow aTableRow, String text, int textColor,
         float textSize)
   {
      TextView textView = new TextView(this);
      textView.setTextSize(textSize);
      textView.setTextColor(textColor);
      textView.setText(text);
      aTableRow.addView(textView);
   }

}

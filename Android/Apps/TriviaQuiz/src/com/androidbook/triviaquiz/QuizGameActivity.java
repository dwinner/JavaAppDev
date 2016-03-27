package com.androidbook.triviaquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class QuizGameActivity extends QuizActivity
{
   @SuppressLint("UseSparseArrays")
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.game);

      // -- Обработка кнопки Yes

      Button yesButton = (Button) findViewById(R.id.Button_Yes);
      yesButton.setOnClickListener(new View.OnClickListener()
      {
         public void onClick(View v)
         {
            handleAnswerAndShowNextQuestion(true);
         }

      });

      // -- Обработка кнопки No

      Button noButton = (Button) findViewById(R.id.Button_No);
      noButton.setOnClickListener(new View.OnClickListener()
      {
         public void onClick(View v)
         {
            handleAnswerAndShowNextQuestion(false);
         }

      });

      // -- Извлечение общих настроек приложения

      mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);

      // -- Инициализация набора вопросов

      mQuestions = new HashMap<Integer, Question>(QUESTION_BATCH_SIZE);

      // -- Загрузка вопросов

      int startingQuestionNumber = mGameSettings.getInt(GAME_PREFERENCES_CURRENT_QUESTION, 0);

      // -- Если мы в только начинаем викторину, инициализируем общие настройкм

      if (startingQuestionNumber == 0)
      {
         Editor editor = mGameSettings.edit();
         editor.putInt(GAME_PREFERENCES_CURRENT_QUESTION, 1);
         editor.commit();
         startingQuestionNumber = 1;
      }
      try
      {
         loadQuestionBatch(startingQuestionNumber);
      }
      catch (XmlPullParserException ex)
      {
         Log.e(DEBUG_TAG, "Loading initial question batch failed", ex);
      }
      catch (IOException ex)
      {
         Log.e(DEBUG_TAG, "Loading initial question batch failed", ex);
      }

      Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
      Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

      // -- Установка Text Switcher

      TextSwitcher questionTextSwitcher =
        (TextSwitcher) findViewById(R.id.TextSwitcher_QuestionText);
      questionTextSwitcher.setInAnimation(in);
      questionTextSwitcher.setOutAnimation(out);
      questionTextSwitcher.setFactory(new MyTextSwitcherFactory());

      // -- Установка Image Switcher

      ImageSwitcher questionImageSwitcher =
        (ImageSwitcher) findViewById(R.id.ImageSwitcher_QuestionImage);
      questionImageSwitcher.setInAnimation(in);
      questionImageSwitcher.setOutAnimation(out);
      questionImageSwitcher.setFactory(new MyImageSwitcherFactory());

      // -- Если вопрос корректно загружен, отображаем его
      if (mQuestions.containsKey(startingQuestionNumber))
      {
         // -- Установка текста для TextSwitcher

         questionTextSwitcher.setCurrentText(getQuestionText(startingQuestionNumber));

         // -- Установка изображения для ImageSwitcher

         Drawable image = getQuestionImageDrawable(startingQuestionNumber);
         questionImageSwitcher.setImageDrawable(image);
      }
      else
      {
         // -- В данный момент вопросов больше нет
         handleNoQuestions();
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
      super.onCreateOptionsMenu(menu);

      getMenuInflater().inflate(R.menu.gameoptions, menu);
      menu.findItem(R.id.help_menu_item).setIntent(new Intent(this, QuizHelpActivity.class));
      menu.findItem(R.id.settings_menu_item).setIntent(new Intent(this, QuizSettingsActivity.class));
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      super.onOptionsItemSelected(item);
      startActivity(item.getIntent());
      return true;
   }

   /**
    * Фабрика переключателя для использования с изображением вопроса. Создает следующий {@code ImageView}
    * объект для анимации
    */
   private class MyImageSwitcherFactory implements ViewSwitcher.ViewFactory
   {
      public View makeView()
      {
         ImageView imageView = new ImageView(QuizGameActivity.this);
         imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
         imageView.setLayoutParams(
           new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,
                                          LayoutParams.WRAP_CONTENT));
         return imageView;
      }

   }

   /**
    * Фабрика переключателя для использования с текстом вопроса. Создает следующий {@code TextView} объект для
    * анимации
    */
   private class MyTextSwitcherFactory implements ViewSwitcher.ViewFactory
   {
      public View makeView()
      {
         TextView textView = new TextView(QuizGameActivity.this);
         textView.setGravity(Gravity.CENTER);
         Resources res = getResources();
         float dimension = res.getDimension(R.dimen.game_question_size);
         int titleColor = res.getColor(R.color.title_color);
         int shadowColor = res.getColor(R.color.title_glow);
         textView.setTextSize(dimension);
         textView.setTextColor(titleColor);
         textView.setShadowLayer(10, 5, 5, shadowColor);
         return textView;
      }

   }

   /**
    * Вспомогательный метод для записи ответа и занрузки нового вопроса
    *
    * @param bAnswer true, если пользователь дал положительный ответ, false в противном случае
    */
   private void handleAnswerAndShowNextQuestion(boolean bAnswer)
   {
      int curScore = mGameSettings.getInt(GAME_PREFERENCES_SCORE, 0);
      int nextQuestionNumber = mGameSettings.getInt(GAME_PREFERENCES_CURRENT_QUESTION, 1) + 1;

      Editor editor = mGameSettings.edit();
      editor.putInt(GAME_PREFERENCES_CURRENT_QUESTION, nextQuestionNumber);

      // -- Учитываем ответ в случае Yes
      if (bAnswer)
      {
         editor.putInt(GAME_PREFERENCES_SCORE, curScore + 1);
      }
      editor.commit();

      if (!mQuestions.containsKey(nextQuestionNumber))
      {
         try
         {  // -- Загружаем следующую серию вопросов
            loadQuestionBatch(nextQuestionNumber);
         }
         catch (Exception e)
         {
            Log.e(DEBUG_TAG, "Loading updated question batch failed", e);
         }
      }

      if (mQuestions.containsKey(nextQuestionNumber))
      {
         // -- Обновляем текст вопроса
         TextSwitcher questionTextSwitcher =
           (TextSwitcher) findViewById(R.id.TextSwitcher_QuestionText);
         questionTextSwitcher.setText(getQuestionText(nextQuestionNumber));

         // -- Обновляем изображение вопроса
         ImageSwitcher questionImageSwitcher =
           (ImageSwitcher) findViewById(R.id.ImageSwitcher_QuestionImage);
         Drawable image = getQuestionImageDrawable(nextQuestionNumber);
         questionImageSwitcher.setImageDrawable(image);
      }
      else
      {
         handleNoQuestions();
      }

   }

   /**
    * Вспомогательный метод, отвечающий за ситуацию отсутствия вопросов
    */
   private void handleNoQuestions()
   {
      TextSwitcher questionTextSwitcher =
        (TextSwitcher) findViewById(R.id.TextSwitcher_QuestionText);
      questionTextSwitcher.setText(
        getResources().getText(R.string.no_questions));
      ImageSwitcher questionImageSwitcher =
        (ImageSwitcher) findViewById(R.id.ImageSwitcher_QuestionImage);
      questionImageSwitcher.setImageResource(R.drawable.noquestion);

      // -- Отключаем кнопку Yes
      Button yesButton = (Button) findViewById(R.id.Button_Yes);
      yesButton.setEnabled(false);

      // -- Отключаем кнопку No
      Button noButton = (Button) findViewById(R.id.Button_No);
      noButton.setEnabled(false);
   }

   /**
    * Возвращает строку с текстом вопроса по его номеру
    *
    * @param questionNumber Номер вопроса
    * @return Текст вопроса
    */
   private String getQuestionText(Integer questionNumber)
   {
      String text = null;
      Question curQuestion = mQuestions.get(questionNumber);
      if (curQuestion != null)
      {
         text = curQuestion.getmText();
      }
      return text;
   }

   /**
    * Возвращает строку URL для изображения по номеру вопроса
    *
    * @param questionNumber Номер вопроса
    * @return URL изображения
    */
   private String getQuestionImageUrl(Integer questionNumber)
   {
      String url = null;
      Question curQuestion = mQuestions.get(questionNumber);
      if (curQuestion != null)
      {
         url = curQuestion.getmImageUrl();
      }
      return url;
   }

   /**
    * Извлекает объект Drawable для вопроса
    *
    * @param questionNumber Номер вопроса
    * @return Объект Drawable
    */
   @SuppressWarnings("deprecation")
   private Drawable getQuestionImageDrawable(int questionNumber)
   {
      Drawable image;
      URL imageUrl;

      try
      {
         // -- Создание объекта Drawable через декодирование потока из URL
         imageUrl = new URL(getQuestionImageUrl(questionNumber));
         Bitmap bitmap = BitmapFactory.decodeStream(imageUrl.openStream());
         image = new BitmapDrawable(bitmap);
      }
      catch (Exception e)
      {
         Log.e(DEBUG_TAG, "Decoding Bitmap stream failed.");
         image = getResources().getDrawable(R.drawable.noquestion);
      }
      return image;
   }

   /**
    * Загрузка XML в поле класса
    *
    * @param startQuestionNumber TODO: Пока не используется
    * @throws XmlPullParserException
    * @throws IOException
    */
   private void loadQuestionBatch(int startQuestionNumber) throws XmlPullParserException, IOException
   {
      // Удаляем старую серию вопросов
      mQuestions.clear();

      // TODO: Устанавливаем связь с сервером и извлекаем новую серию вопросов
      XmlResourceParser questionBatch;

      questionBatch = startQuestionNumber < 16
        ? getResources().getXml(R.xml.samplequestions)
        : getResources().getXml(R.xml.samplequestions2);

      int eventType = -1;

      // Ищем записи для score из XML
      while (eventType != XmlResourceParser.END_DOCUMENT)
      {
         if (eventType == XmlResourceParser.START_TAG)
         {
            // Получаем имя тэга
            String strName = questionBatch.getName();

            if (strName.equals(XML_TAG_QUESTION))
            {
               String questionNumber = questionBatch.
                 getAttributeValue(null, XML_TAG_QUESTION_ATTRIBUTE_NUMBER);
               Integer questionNum = Integer.valueOf(questionNumber);
               String questionText =
                 questionBatch.getAttributeValue(null, XML_TAG_QUESTION_ATTRIBUTE_TEXT);
               String questionImageUrl =
                 questionBatch.getAttributeValue(null, XML_TAG_QUESTION_ATTRIBUTE_IMAGEURL);

               // Сохранение данных с карту
               mQuestions.put(
                 questionNum, new Question(questionNum, questionText, questionImageUrl));
            }
         }
         eventType = questionBatch.next();
      }
   }

   private SharedPreferences mGameSettings;
   private Map<Integer, Question> mQuestions;

   /**
    * Класс для управления данными вопроса викторины.
    */
   private class Question
   {
      /**
       * Конструктор создания вопроса викторины.
       *
       * @param questionNum The number of this question
       * @param questionText The text for this question
       * @param questionImageUrl A valid image Url to display with this question
       */
      Question(int questionNum, String questionText, String questionImageUrl)
      {
         mNumber = questionNum;
         mText = questionText;
         mImageUrl = questionImageUrl;
      }

      public int getmNumber()
      {
         return mNumber;
      }

      public String getmText()
      {
         return mText;
      }

      public String getmImageUrl()
      {
         return mImageUrl;
      }

      private int mNumber;
      private String mText;
      private String mImageUrl;
   }

}

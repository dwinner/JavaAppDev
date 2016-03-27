package com.androidbook.triviaquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class QuizSettingsActivity extends QuizActivity
{
   static final int DATE_DIALOG_ID = 0;
   static final int PASSWORD_DIALOG_ID = 1;
   static final int TAKE_AVATAR_CAMERA_REQUEST = 1;
   static final int TAKE_AVATAR_GALLERY_REQUEST = 2;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.settings);

      // -- Извлекаем общие настройки приложения

      mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);

      // -- Инициализируем UI-элементы активности

      initAvatar();
      initNicknameEntry();
      initEmailEntry();
      initPasswordChooser();
      initDatePicker();
      initGenderSpinner();
   }

   @Override
   protected void onDestroy()
   {
      Log.d(DEBUG_TAG, "SHARED PREFERENCES");
      Log.d(
        DEBUG_TAG, "Nickname is: " + mGameSettings.getString(GAME_PREFERENCES_NICKNAME, "Not set"));
      Log.d(
        DEBUG_TAG, "Email is: " + mGameSettings.getString(GAME_PREFERENCES_EMAIL, "Not set"));
      Log.d(
        DEBUG_TAG, "Gender (M=1, F=2, U=0) is: " + mGameSettings.getInt(GAME_PREFERENCES_GENDER, 0));
      Log.d(
        DEBUG_TAG, "Password is: " + mGameSettings.getString(GAME_PREFERENCES_PASSWORD, "Not set"));
      Log.d(
        DEBUG_TAG, "DOB is: "
        + DateFormat.format("MMMM dd, yyyy", mGameSettings.getLong(GAME_PREFERENCES_DOB, 0)));
      super.onDestroy();
   }

   @Override
   @SuppressWarnings("deprecation")
   protected Dialog onCreateDialog(int id)
   {
      switch (id)
      {
         case DATE_DIALOG_ID:
            final TextView dob = (TextView) findViewById(R.id.TextView_DOB_Info);
            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
            {
               public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
               {
                  Time dateOfBirth = new Time();
                  dateOfBirth.set(dayOfMonth, monthOfYear, year);
                  long dtDob = dateOfBirth.toMillis(true);
                  dob.setText(DateFormat.format("MMMM dd, yyyy", dtDob));
                  Editor editor = mGameSettings.edit();
                  editor.putLong(GAME_PREFERENCES_DOB, dtDob);
                  editor.commit();
               }

            }, 0, 0, 0);
            return dateDialog;

         case PASSWORD_DIALOG_ID:
            LayoutInflater inflater =
              (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout =
              inflater.inflate(R.layout.password_dialog, (ViewGroup) findViewById(R.id.root));
            final EditText p1 = (EditText) layout.findViewById(R.id.EditText_Pwd1);
            final EditText p2 = (EditText) layout.findViewById(R.id.EditText_Pwd2);
            final TextView error = (TextView) layout.findViewById(R.id.TextView_PwdProblem);
            p2.addTextChangedListener(new TextWatcher()
            {
               public void beforeTextChanged(CharSequence s, int start, int count, int after)
               {
               }

               public void onTextChanged(CharSequence s, int start, int before, int count)
               {
               }

               public void afterTextChanged(Editable s)
               {
                  String strPass1 = p1.getText().toString();
                  String strPass2 = p2.getText().toString();
                  if (strPass1.equals(strPass2))
                  {
                     error.setText(R.string.settings_pwd_equal);
                  }
                  else
                  {
                     error.setText(R.string.settings_pwd_not_equal);
                  }
               }

            });

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(layout);

            // -- Настраиваем AlertDialog

            builder.setTitle(R.string.settings_button_pwd);
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface dialog, int which)
               {
                  QuizSettingsActivity.this.removeDialog(PASSWORD_DIALOG_ID); // Не кэшируем диалог
               }

            });

            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface dialog, int which)
               {
                  TextView passwordInfo = (TextView) findViewById(R.id.TextView_Password_Info);
                  String strPassword1 = p1.getText().toString();
                  String strPassword2 = p2.getText().toString();
                  if (strPassword1.equals(strPassword2))
                  {
                     Editor editor = mGameSettings.edit();
                     editor.putString(GAME_PREFERENCES_PASSWORD, strPassword1);
                     editor.commit();
                     passwordInfo.setText(R.string.settings_pwd_set);
                  }
                  else
                  {
                     Log.d(DEBUG_TAG, "Password do not match. Not saving. Keeping old password (if set).");
                  }

                  QuizSettingsActivity.this.removeDialog(PASSWORD_DIALOG_ID); // Не кэшируем диалог
               }

            });

            // -- Создаем AlertDialog и возвращаем его

            AlertDialog passwordDialog = builder.create();
            return passwordDialog;

         default:
            return null;
      }
   }

   @Override
   @SuppressWarnings("deprecation")
   protected void onPrepareDialog(int id, Dialog dialog)
   {
      super.onPrepareDialog(id, dialog);
      switch (id)
      {
         case DATE_DIALOG_ID:

            // -- Инициализация диалога DatePickerDialog

            DatePickerDialog dateDialog = (DatePickerDialog) dialog;
            int iDay, iMonth, iYear;

            // -- Проверка существования параметра даты рождения

            if (mGameSettings.contains(GAME_PREFERENCES_DOB))
            {
               // -- Извлекаем дату рождения

               long msBirthDate = mGameSettings.getLong(GAME_PREFERENCES_DOB, 0);
               Time dateOfBirth = new Time();
               dateOfBirth.set(msBirthDate);
               iDay = dateOfBirth.monthDay;
               iMonth = dateOfBirth.month;
               iYear = dateOfBirth.year;
            }
            else
            {
               Calendar cal = Calendar.getInstance();

               // -- Поля текущей даты

               iDay = cal.get(Calendar.DAY_OF_MONTH);
               iMonth = cal.get(Calendar.MONTH);
               iYear = cal.get(Calendar.YEAR);
            }

            // -- Установка даты в DatePicker датой рождения или текущей датой

            dateDialog.updateDate(iYear, iMonth, iDay);
      }
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data)
   {
      switch (requestCode)
      {
         case TAKE_AVATAR_CAMERA_REQUEST:
            if (resultCode == Activity.RESULT_CANCELED)
            {
               // Режим съёмки отменён
            }
            else if (resultCode == Activity.RESULT_OK)
            {
               // Обработка отснятой фотографии
               Bitmap cameraPic = (Bitmap) data.getExtras().get("data");
               if (cameraPic != null)
               {
                  saveAvatar(cameraPic);
               }
            }
            break;
         case TAKE_AVATAR_GALLERY_REQUEST:
            if (resultCode == Activity.RESULT_CANCELED)
            {
               // Режим выбора аватара в галерее изображений отменен
            }
            else if (resultCode == Activity.RESULT_OK)
            {
               // Обработка выбранного изображения
               Uri photoUri = data.getData();
               if (photoUri != null)
               {
                  try
                  {
                     int maxLength = 75;
                     // Полный размер изображения обычно велик. Масштабируем его для изображения аватара
                     Bitmap galleryPic = Media.getBitmap(getContentResolver(), photoUri);                     
                     Bitmap scaledGalleryPic = createScaledBitmapKeepingAspectRatio(galleryPic, maxLength);
                     saveAvatar(scaledGalleryPic);
                  }
                  catch (FileNotFoundException ex)
                  {
                     Log.e(DEBUG_TAG, "saveAvatar() with gallery picker failed", ex);
                  }
                  catch (IOException ex)
                  {
                     Log.e(DEBUG_TAG, "saveAvatar() with gallery picker failed", ex);
                  }
               }
            }
            break;
         default:
            break;
      }
   }

   /**
    * Инициализация имени (НИК'а).
    */
   private void initNicknameEntry()
   {
      // -- Отображаем имя в поле ввода, если оно уже есть

      final EditText nicknameText = (EditText) findViewById(R.id.EditText_Nickname);
      if (mGameSettings.contains(GAME_PREFERENCES_NICKNAME))
      {
         nicknameText.setText(mGameSettings.getString(GAME_PREFERENCES_NICKNAME, ""));
      }

      // -- Сохраняем текущее значение при нажатии Enter Android'а

      nicknameText.setOnKeyListener(new View.OnKeyListener()
      {
         public boolean onKey(View v, int keyCode, KeyEvent event)
         {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
            {
               String strNickName = nicknameText.getText().toString();
               Editor editor = mGameSettings.edit();
               editor.putString(GAME_PREFERENCES_NICKNAME, strNickName);
               editor.commit();
               return true;
            }
            return false;
         }

      });
   }

   /**
    * Инициализация поля ввода email.
    */
   private void initEmailEntry()
   {
      // -- Показываем текущее значение в EditText, если оно есть

      final EditText emailText = (EditText) findViewById(R.id.EditText_Email);
      if (mGameSettings.contains(GAME_PREFERENCES_EMAIL))
      {
         emailText.setText(mGameSettings.getString(GAME_PREFERENCES_EMAIL, ""));
      }

      // -- Сохраняем текущее значение при нажатии Enter Android'а

      emailText.setOnKeyListener(new View.OnKeyListener()
      {
         public boolean onKey(View v, int keyCode, KeyEvent event)
         {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
            {
               Editor editor = mGameSettings.edit();
               editor.putString(GAME_PREFERENCES_EMAIL, emailText.getText().toString());
               editor.commit();
               return true;
            }
            return false;
         }

      });
   }

   /**
    * Инициализация элементов управления для установки/получения пароля.
    */
   private void initPasswordChooser()
   {
      // -- Установка информации о существовании пароля

      TextView passwordInfo = (TextView) findViewById(R.id.TextView_Password_Info);
      if (mGameSettings.contains(GAME_PREFERENCES_PASSWORD))
      {
         passwordInfo.setText(R.string.settings_pwd_set);
      }
      else
      {
         passwordInfo.setText(R.string.settings_pwd_not_set);
      }

      // -- Вызов диалогового окна для установки пароля

      Button callPassDlgButton = (Button) findViewById(R.id.Button_Password);
      callPassDlgButton.setOnClickListener(new View.OnClickListener()
      {
         @SuppressWarnings("deprecation")
         public void onClick(View v)
         {
            showDialog(PASSWORD_DIALOG_ID);
            /* Toast.makeText(
             QuizSettingsActivity.this, "Demo: launch password dialog", Toast.LENGTH_LONG).show(); */
         }

      });
   }

   /**
    * Инициализация элементов управления для установки/получения даты.
    */
   private void initDatePicker()
   {
      // -- Установка существующего значения даты в TextView

      TextView dobInfo = (TextView) findViewById(R.id.TextView_DOB_Info);
      if (mGameSettings.contains(GAME_PREFERENCES_DOB))
      {
         dobInfo.setText(
           DateFormat.format("MMMM dd, yyy", mGameSettings.getLong(GAME_PREFERENCES_DOB, 0)));
      }
      else
      {
         dobInfo.setText(R.string.settings_dob_not_set);
      }

      // -- Вызов диалогового окна выбора даты

      Button pickDate = (Button) findViewById(R.id.Button_DOB);
      pickDate.setOnClickListener(new View.OnClickListener()
      {
         @SuppressWarnings("deprecation")
         public void onClick(View v)
         {
            showDialog(DATE_DIALOG_ID);
            /* Toast.makeText(
             QuizSettingsActivity.this, "Demo: launch DatePickerDialog", Toast.LENGTH_LONG).show(); */
         }

      });
   }

   /**
    * Инициализация Spinner'а Android.
    */
   private void initGenderSpinner()
   {
      // -- Заполняем элемент управления Spinner

      Spinner spinner = (Spinner) findViewById(R.id.Spinner_Gender);
      ArrayAdapter<?> adapter =
        ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(adapter);

      if (mGameSettings.contains(GAME_PREFERENCES_GENDER))
      {
         spinner.setSelection(mGameSettings.getInt(GAME_PREFERENCES_GENDER, 0));
      }

      // -- Обработка выбора в UI-спиннере андроида

      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
      {
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
         {
            Editor editor = mGameSettings.edit();
            editor.putInt(GAME_PREFERENCES_GENDER, position);
            editor.commit();
         }

         public void onNothingSelected(AdapterView<?> parent)
         {
         }

      });
   }

   /**
    * Инициализация аватара.
    */
   private void initAvatar()
   {
      ImageButton avatarButton = (ImageButton) findViewById(R.id.ImageButton_Avatar);

      if (mGameSettings.contains(GAME_PREFERENCES_AVATAR))
      {
         String strAvatarUri =
           mGameSettings.getString(GAME_PREFERENCES_AVATAR,
                                   "android.resource://com.androidbook.triviaquiz/drawable/avatar");
         Uri imageUri = Uri.parse(strAvatarUri);
         avatarButton.setImageURI(imageUri);
      }
      else
      {
         avatarButton.setImageResource(R.drawable.avatar);
      }

      avatarButton.setOnClickListener(new OnClickListener()
      {
         public void onClick(View v)
         {
            String strAvatarPrompt = "Take your picture to store as your avatar!";
            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(Intent.createChooser(pictureIntent, strAvatarPrompt),
                                   TAKE_AVATAR_CAMERA_REQUEST);

         }

      });

      avatarButton.setOnLongClickListener(new OnLongClickListener()
      {
         public boolean onLongClick(View v)
         {
            String strAvatarPrompt = "Choose a picture to use as your avatar!";
            Intent pickPhoto = new Intent(Intent.ACTION_PICK);
            pickPhoto.setType("image/*");
            startActivityForResult(Intent.createChooser(pickPhoto, strAvatarPrompt),
                                   TAKE_AVATAR_GALLERY_REQUEST);
            return true;
         }

      });
   }

   /**
    * Сохранение аватара
    *
    * @param avatar Растровое изображение.
    */
   private void saveAvatar(Bitmap avatar)
   {
      String strAvatarFilename = "avatar.jpg";
      try
      {
         avatar.compress(CompressFormat.JPEG, 100, openFileOutput(strAvatarFilename, MODE_PRIVATE));
      }
      catch (FileNotFoundException ex)
      {
         Log.e(DEBUG_TAG, "Avatar compression and save failed", ex);
      }

      Uri imageUriToSaveCameraImageto = Uri.fromFile(new File(getFilesDir(), strAvatarFilename));

      Editor editor = mGameSettings.edit();
      editor.putString(GAME_PREFERENCES_AVATAR, imageUriToSaveCameraImageto.getPath());
      editor.commit();

      // Обновление экрана настроек
      ImageButton avatarButton = (ImageButton) findViewById(R.id.ImageButton_Avatar);
      String strAvatarUri = mGameSettings.getString(GAME_PREFERENCES_AVATAR,
                                                    "android.resource://com.androidbook.triviaquiz/drawable/avatar");
      Uri imageUri = Uri.parse(strAvatarUri);
      avatarButton.setImageURI(null);  // force no cache
      avatarButton.setImageURI(imageUri);
   }
   
   /**
    * Масштабирование изображения с сохранением пропорций исходного
    *
    * @param bitmap Растровое изображение
    * @param maxSide Квадрат масштабирования
    * @return Новое растровое изображение
    */
   private Bitmap createScaledBitmapKeepingAspectRatio(Bitmap bitmap, int maxSide)
   {
      int orgHeight = bitmap.getHeight();
      int orgWidth = bitmap.getWidth();

      // Масштабируем в квадрате 75px
      int scaledWidth = (orgWidth >= orgHeight)
        ? maxSide
        : (int) ((float) maxSide * ((float) orgWidth / (float) orgHeight));
      int scaledHeight = (orgHeight >= orgWidth)
        ? maxSide
        : (int) ((float) maxSide * ((float) orgHeight / (float) orgWidth));

      // Создание масштабированного растра
      Bitmap scaledGalleryPic = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
      return scaledGalleryPic;
   }

   private SharedPreferences mGameSettings;
}

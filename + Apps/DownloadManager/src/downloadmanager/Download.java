package downloadmanager;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Этот класс загружает файл с указанного URL.
 * @author dwinner@inbox.ru
 */
class Download extends Observable implements Runnable {
    // Максимальный размер буфера загрузок.
    private static final int MAX_BUFFER_SIZE = 1024;

    // Имена состояний загрузок.
    public static final String STATUSES[] = {
        "Downloading",
        "Paused",
        "Complete",
        "Cancelled",
        "Error"
    };

    // Коды состояния.
    public static final int DOWNLOADING = 0;
    public static final int PAUSED = 1;
    public static final int COMPLETE = 2;
    public static final int CANCELLED = 3;
    public static final int ERROR = 4;

    private URL url; // Загрузка URL.
    private int size; // Размер загрузки в байтах.
    private int downloaded; // Количество загруженных байтов.
    private int status; // Текущее состояние загрузки.

    // Конструктор для класса Download.
    public Download(URL url) {
        this.url = url;
        size = -1;
        downloaded = 0;
        status = DOWNLOADING;
        // Начало загрузки.
        download();
    }

    // Получить URL
    public String getUrl() {
        return url.toString();
    }

    // Получить размер загрузки.
    public int getSize() {
        return size;
    }

    // Получить процесс загрузки.
    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }

    // Получить состояние загрузки.
    public int getStatus() {
        return status;
    }

    // Приостановить загрузку
    public void pause() {
        status = PAUSED;
        stateChanged();
    }

    // Повторить загрузку.
    public void resume() {
        status = DOWNLOADING;
        stateChanged();
        download();
    }

    // Прекратить загрузку.
    public void cancel() {
        status = CANCELLED;
        stateChanged();
    }

    // Отметить загрузку как имеющую ошибку.
    private void error() {
        status = ERROR;
        stateChanged();
    }

    // Начать или повторить загрузки.
    private void download() {
        Thread thread = new Thread(this);
        thread.start();
    }

    // Получить имя файла из URL.
    private String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    // Загрузить файл.
    @Override
    public void run() {
        RandomAccessFile file = null;
        InputStream stream = null;

        try {
            // Открыть соединение с URL.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Определить часть загруженного файла.
            connection.setRequestProperty("Range", "bytes=" + downloaded + "-");

            // Соединиться с сервером.
            connection.connect();

            // Убедиться, что код находится в диапазоне 200.
            if (connection.getResponseCode() / 100 != 2) {
                error();
            }

            // Проверить допустимую длину содержимого.
            int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                error();
            }

            // Установить размер для загрузки, если он ещё не установлен.
            if (size == -1) {
                size = contentLength;
                stateChanged();
            }

            // Открыть файл и найти конец файла.
            file = new RandomAccessFile(getFileName(url), "rw");
            file.seek(downloaded);

            stream = connection.getInputStream();
            while (status == DOWNLOADING) {
                // Размер буфера в соответствии с размером части файла, который осталось загрузить.
                byte buffer[] = (size - downloaded > MAX_BUFFER_SIZE) ? new byte[MAX_BUFFER_SIZE] : new byte[size - downloaded];
                // Считать с сервера в буфер.
                int read = stream.read(buffer);
                if (read == -1) {
                    break;
                }
                // Записать буфер в файл.
                file.write(buffer, 0, read);
                downloaded += read;
                stateChanged();
            }

            // Изменить статус завершения, если эта точка уже достигнута,
            // поскольку загрузка закончена.
            if (status == DOWNLOADING) {
                status = COMPLETE;
                stateChanged();
            }
        }
        catch (Exception e) {
            error();
        }
        finally {
            // Закрыть файл.
            if (file != null) {
               try {
                   file.close();
               }
               catch (Exception e) {
               }
            }
            // Закрыть соединение с сервером.
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (Exception e) {
                }
            }
        }
    }

    // Уведомить, что статус этой загрузки был изменен.
    private void stateChanged() {
        setChanged();
        notifyObservers();
    }
}
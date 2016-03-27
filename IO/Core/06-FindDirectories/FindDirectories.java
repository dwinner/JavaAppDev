import java.io.File;
import java.io.IOException;

/**
 * @version 1.00 05 Sep 1997
 * @author Cay Horstmann
 */
public class FindDirectories
{
    public static void main(String[] args)
    {

        // В случае отсутствия аргументов начинаем с родительского каталога
        if (args.length == 0)
        {
            args = new String[]
            {
                ".."
            };
        }

        try
        {
            File pathName = new File(args[0]);
            String[] fileNames = pathName.list();

            // Перечисляем все файлы в каталоге
            for (int i = 0; i < fileNames.length; i++)
            {
                File f = new File(pathName.getPath(), fileNames[i]);

                // Если файл опять-таки оказывается каталогом,
                // вызываем главный метод рекурсивным образом
                if (f.isDirectory())
                {
                    System.out.println(f.getCanonicalPath());
                    main(new String[]
                        {
                            f.getPath()
                        });
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

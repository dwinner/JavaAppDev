package classesb;

import java.util.ArrayList;

/**
 * Логика работы с классом BookType
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class ClassesB
{
    private ClassesB() {}

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(String[] args)
    {
        // Создаем книги
        BookType book1 = new BookType(978, "Design Patterns", "Piter", 2007, 328, 331.00, CoverEnum.SOLID);
        book1.addAuthor("Gamma");
        
        BookType book2 = new BookType(501, "Core Java1", "Prentice Hall", 2007, 890, 529.00, CoverEnum.SOLID);
        book2.addAuthor("Horstmann");
        book2.addAuthor("Cornell");
        
        BookType book3 = new BookType(502, "Core Java2", "Prentice Hall", 2007, 1120, 750.00, CoverEnum.SOLID);
        book3.addAuthor("Horstmann");
        book3.addAuthor("Cornell");
        
        BookType book4 = new BookType(421, "JavaSE6", "Ozon", 2007, 1063, 820.00, CoverEnum.SOLID);
        book4.addAuthor("Shildt");
        
        BookType book5 = new BookType(5, "JSF", "Prentice Hall", 2010, 550, 610.00, CoverEnum.LIMP);
        book5.addAuthor("Horstmann");
        book5.addAuthor("GaryM");
        
        BookType book6 = new BookType(905, "JSP", "BruceOPery", 2006, 738, 592.00, CoverEnum.LIMP);
        book6.addAuthor("BruceOPery");
        
        // Создаем список книг
        BookTypeArray bookList = new BookTypeArray();
        bookList.addBook(book1);
        bookList.addBook(book2);
        bookList.addBook(book3);
        bookList.addBook(book4);
        bookList.addBook(book5);
        bookList.addBook(book6);
        
        // Выводим список книг заданного автора
        String authorToFind = "Horstmann";
        ArrayList<BookType> bookListByAuthor = bookList.bookListByAuthor(authorToFind);
        for (BookType bType : bookListByAuthor)
        {
            System.out.println(bType);
        }
        System.out.println();
        
        // Выводим список книг заданного издательства
        String publishedToFind = "Prentice Hall";
        ArrayList<BookType> bookListByPublished = bookList.bookListByPublished(publishedToFind);
        for (BookType bType : bookListByPublished)
        {
            System.out.println(bType);
        }
        System.out.println();
        
        // Выводим список книг, изданных после 2007 года
        int afterYear = 2007;
        ArrayList<BookType> bookListAfterYear = bookList.bookListAfter(afterYear);
        for (BookType bType : bookListAfterYear)
        {
            System.out.println(bType);
        }
    }

}

package classesb;

import java.util.ArrayList;

/**
 * Тип, инкапсулирующий набор объектов BookType
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class BookTypeArray
{
    private ArrayList<BookType> bookTypes;
    private static final short INITIAL_CAPACITY = 32;

    public BookTypeArray()
    {
        bookTypes = new ArrayList<BookType>(INITIAL_CAPACITY);
    }
    
    public void addBook(BookType book)
    {
        bookTypes.add(book);
    }
    
    public void removeBook(int bookIndex)
    {
        if (bookIndex < 0 || bookIndex > bookTypes.size() - 1)
        {
            return;
        }
        bookTypes.remove(bookIndex);
    }
    
    public ArrayList<BookType> bookListByAuthor(String author)
    {
        ArrayList<BookType> bookList = null;
        for (BookType bType : bookTypes)
        {
            ArrayList<String> authors = bType.getBookAuthors();
            for (String bookAuthor : authors)
            {
                if (author.equalsIgnoreCase(bookAuthor))
                {
                    if (bookList == null)
                    {
                        bookList = new ArrayList<BookType>(INITIAL_CAPACITY);
                    }
                    bookList.add(bType);
                    break;
                }
            }
        }
        return bookList;
    }
    
    public ArrayList<BookType> bookListByPublished(String bookPublish)
    {
        ArrayList<BookType> bookList = null;
        for (BookType bType : bookTypes)
        {
            if (bookPublish.equalsIgnoreCase(bType.getBookPublish()))
            {
                if (bookList == null)
                {
                    bookList = new ArrayList<BookType>(INITIAL_CAPACITY);
                }
                bookList.add(bType);
            }
        }
        return bookList;
    }
    
    public ArrayList<BookType> bookListAfter(int year)
    {
        ArrayList<BookType> bookList = null;
        for (BookType bType : bookTypes)
        {
            if (bType.getPublishedYear() > year)
            {
                if (bookList == null)
                {
                    bookList = new ArrayList<BookType>(INITIAL_CAPACITY);
                }
                bookList.add(bType);
            }
        }
        return bookList;
    }
        
}

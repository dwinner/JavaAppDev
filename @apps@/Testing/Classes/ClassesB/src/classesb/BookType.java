package classesb;

import java.util.ArrayList;

/**
 * Тип для описания книг
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class BookType
{
    private int bookId;
    private String bookTitle;
    private final ArrayList<String> bookAuthors;
    private String bookPublish;
    private int publishedYear;
    private int pageCount;
    private double bookCost;
    private CoverEnum bookCover;
    
    private static final short INITIAL_CAPACITY = 4;
    
    {
        bookAuthors = new ArrayList<String>(INITIAL_CAPACITY);
    }

    public BookType(int bookId, String bookTitle, String bookPublish, int publishedYear, int pageCount, double bookCost, CoverEnum bookCover)
    {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookPublish = bookPublish;
        this.publishedYear = publishedYear;
        this.pageCount = pageCount;
        this.bookCost = bookCost;
        this.bookCover = bookCover;
    }
    
    public void addAuthor(String author)
    {
        bookAuthors.add(author);
    }

    public double getBookCost()
    {
        return bookCost;
    }
    public void setBookCost(double bookCost)
    {
        this.bookCost = bookCost;
    }

    public CoverEnum getBookCover()
    {
        return bookCover;
    }
    public void setBookCover(CoverEnum bookCover)
    {
        this.bookCover = bookCover;
    }

    public int getBookId()
    {
        return bookId;
    }
    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }

    public String getBookPublish()
    {
        return bookPublish;
    }
    public void setBookPublish(String bookPublish)
    {
        this.bookPublish = bookPublish;
    }

    public String getBookTitle()
    {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle)
    {
        this.bookTitle = bookTitle;
    }

    public int getPageCount()
    {
        return pageCount;
    }
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getPublishedYear()
    {
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear)
    {
        this.publishedYear = publishedYear;
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public ArrayList<String> getBookAuthors() {
        return bookAuthors;
    }

    @Override public String toString()
    {
        return "BookType{" + "bookId=" + bookId + ", bookTitle=" + bookTitle +
               ", bookAuthors=" + bookAuthors + ", bookPublish=" + bookPublish +
               ", publishedYear=" + publishedYear + ", pageCount=" + pageCount +
               ", bookCost=" + bookCost + ", bookCover=" + bookCover + '}';
    }
    
}

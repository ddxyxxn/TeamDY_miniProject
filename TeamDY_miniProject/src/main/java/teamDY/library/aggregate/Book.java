package teamDY.library.aggregate;

import java.io.Serializable;

public class Book implements Serializable {
    private int bookId;
    private String title;
    private String author;
    private Category category;
    private BookStatus bookStatus;

    public Book() {
    }

    public Book(int bookId, String title, String author, Category category, BookStatus bookStatus) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category=" + category +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
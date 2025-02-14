package teamDY.library.aggregate;

import java.io.Serializable;
import java.time.LocalDate;

public class BorrowingBooks implements Serializable {
    private int bookId;
    private String title;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingBooks() {
    }

    public BorrowingBooks(int bookId, String title, LocalDate borrowDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.title = title;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
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

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "BorrowingBooks{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

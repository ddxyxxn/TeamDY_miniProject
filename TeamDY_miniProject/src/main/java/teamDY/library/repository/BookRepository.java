package teamDY.library.repository;

import teamDY.library.aggregate.Book;
import teamDY.library.aggregate.BookStatus;
import teamDY.library.aggregate.BorrowingBooks;
import teamDY.library.aggregate.Category;
import teamDY.library.stream.MyObjectOutput;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookRepository {

    private final ArrayList<Book> bookList = new ArrayList<>();
    private final ArrayList<BorrowingBooks> borrowingList = new ArrayList<>();

    private final File file = new File(
            "src/main/java/teamDY/library/db/bookDB.dat"
    );

    private final File borrowingFile = new File(
            "src/main/java/teamDY/library/db/borrowingBookDB.dat"
    );

    public BookRepository() {
        if (!file.exists()) {
            ArrayList<Book> books = new ArrayList<>();
            books.add((new Book(1, "삥삥이의 모험", "이성준", Category.CHILDREN, BookStatus.IN_LIBRARY)));
            books.add((new Book(2, "김랑랑과 조랑랑", "김기종", Category.FICTION, BookStatus.IN_LIBRARY)));
            books.add((new Book(3, "도은이와 초콜릿 공장", "강이도은", Category.CHILDREN, BookStatus.IN_LIBRARY)));
            books.add((new Book(4, "집사와 고양이", "주아현", Category.SCIENCE, BookStatus.IN_LIBRARY)));
            books.add((new Book(5, "고구마에 관한 고찰", "고도연", Category.SOCIETY, BookStatus.IN_LIBRARY)));
            books.add((new Book(6, "락스타의 삶", "한윤상", Category.ART, BookStatus.IN_LIBRARY)));

            saveBooks(books);

        }

        loadBooks();

    }

    private void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(file)
                )
        )) {
            while (true) {
                bookList.add((Book)ois.readObject());
            }
        } catch (EOFException e) {
            System.out.println("도서 정보 읽어옴");
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    private void saveBooks(ArrayList<Book> books) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );

            for (Book book : books) {
                oos.writeObject(book);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 대출 과정
    public Book checkAndBorrowBook(String title) {
        Book returnBook = null;

        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                returnBook = book;
            }
        }
        return returnBook;
    }

    public ArrayList<Book> selectAllBooks() {
        return bookList;
    }


    public void borrowedBooks(Book finedBook) {
        BorrowingBooks borrowingBook = new BorrowingBooks(
                finedBook.getBookId(), finedBook.getTitle(), LocalDate.now(), LocalDate.now().plusDays(14)
        );
        System.out.println("<" + borrowingBook.getTitle() + ">" + " 대출 완료!");
        borrowingList.add(borrowingBook);
        updateBooks(borrowingBook);
    }

    public void updateBooks(BorrowingBooks borrowingBook) {
        ObjectOutputStream oos = null;
        MyObjectOutput moo = null;
        int result = 0;

        try {
            if (!borrowingFile.exists()) {
                oos = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(borrowingFile)));
            } else {
                oos = new MyObjectOutput(
                        new BufferedOutputStream(
                                new FileOutputStream(borrowingFile)
                        )
                );
            }
            oos.writeObject(borrowingBook);
            result = 1;
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            try {
                if(moo != null) moo.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

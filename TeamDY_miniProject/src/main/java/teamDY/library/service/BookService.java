package teamDY.library.service;

import teamDY.library.aggregate.Book;
import teamDY.library.aggregate.BookStatus;
import teamDY.library.aggregate.Category;
import teamDY.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class BookService {

    private BookRepository br = new BookRepository();
//    private Book book = new Book();

    public BookService() {
    }

    public void findAllBooks() {
        ArrayList<Book> findBooks = br.selectAllBooks();

        System.out.println("Service에서 조회 확인: ");
        for (Book book : findBooks) {
            System.out.println(book);
        }
    }

    public void borrowBooks(String bookName) {
        Book findBook = br.checkAndBorrowBook(bookName);

        if (findBook != null) {
            if (findBook.getBookStatus() == BookStatus.IN_LIBRARY) {
                br.borrowedBooks(findBook);     // 대출 처리
            } else {
                System.out.println("<" + findBook.getTitle() + ">" + " 은(는) 현재 대출 중입니다.");
            }
        } else {
            System.out.println("도서관에 등록된 책이 아닙니다.");
        }
    }

    public void searchBook() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("===== 검색 메뉴 =====");
            System.out.println("1. 제목");
            System.out.println("2. 저자");
            System.out.println("3. 카테고리");
            System.out.println("9. 메인 메뉴");
            System.out.print("검색 메뉴 선택: ");
            int searchMenuNum = sc.nextInt();

            sc.nextLine();

            switch (searchMenuNum) {

                case 1:
                    System.out.print("제목 입력: ");
                    String titleKeyword = sc.nextLine();
                    searchByTitle(titleKeyword);
                    break;
                case 2:
                    System.out.print("저자 입력: ");
                    String authorKeyword = sc.nextLine();
                    searchByAuthor(authorKeyword);
                    break;
                case 3:
                    System.out.print("카테고리 입력(소설, 사회, 과학, 예술, 어린이): ");
                    String categoryKeyword = sc.nextLine();
                    Category ctg = null;
                    switch (categoryKeyword) {
                        case "소설":
                            ctg = Category.FICTION;
                            break;
                        case "사회":
                            ctg = Category.SOCIETY;
                            break;
                        case "과학":
                            ctg = Category.SCIENCE;
                            break;
                        case "예술":
                            ctg = Category.ART;
                            break;
                        case "어린이":
                            ctg = Category.CHILDREN;
                            break;
                        default:
                            System.out.println("카테고리를 정확히 입력하세요.");
                            break;
                    }
                    if (ctg != null) {
                        searchByCategory(ctg);
                    }
                    break;
                case 9:
                    System.out.println("메인 메뉴로 이동");
                    return;
            }
        }

    }

    private void searchByCategory(Category ctg) {
        ArrayList<Book> foundBook = new ArrayList<>();

        for (Book book : br.selectAllBooks()) {
            if (ctg.equals(book.getCategory())) {
                foundBook.add(book);
            }
        }
        System.out.println(foundBook);
    }

    private void searchByAuthor(String authorKeyword) {
        ArrayList<Book> foundBook = new ArrayList<>();

        for (Book book : br.selectAllBooks()) {
            if (authorKeyword.equals(book.getAuthor())) {
                foundBook.add(book);
            }
        }
        System.out.println(foundBook);
    }

    private void searchByTitle(String titleKeyword) {
        ArrayList<Book> foundBook = new ArrayList<>();

        for (Book book : br.selectAllBooks()) {
            if (titleKeyword.equals(book.getTitle())) {
                foundBook.add(book);
            }
        }
        System.out.println(foundBook);
    }
}

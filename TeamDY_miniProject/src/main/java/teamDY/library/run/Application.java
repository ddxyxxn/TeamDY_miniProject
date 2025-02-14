package teamDY.library.run;

import teamDY.library.service.BookService;

import java.util.Scanner;

public class Application {

    private static final BookService bs = new BookService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== 도서 관리 시스템! =====");
            System.out.println("1. 도서 목록 조회");
            System.out.println("2. 도서 검색");
            System.out.println("3. 도서 대출");
            System.out.println("4. 도서 반납");
            System.out.println("5. 대출 기간 연장");
            System.out.println("6. 도서 추가");
            System.out.println("7. 도서 삭제");
            System.out.println("9. 종료");
            System.out.print("메뉴를 선택해주세요: ");

            int input = sc.nextInt();

            switch (input) {
                case 1: bs.findAllBooks(); break;
                case 2: bs.searchBook(); break;
                case 3: bs.borrowBooks(bookName()); break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("메뉴를 다시 선택하세요.");
                    break;
            }

        }

    }

    private static String bookName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("대출할 책 제목을 입력하세요: ");
        return sc.nextLine();
    }

}

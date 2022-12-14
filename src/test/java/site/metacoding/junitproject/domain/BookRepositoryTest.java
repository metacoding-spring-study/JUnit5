package site.metacoding.junitproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        // given
        String title = "junit5";
        String author = "겟인데어";
        Book javaBook = Book.builder()
                .author(author)
                .title(title)
                .build();
        bookRepository.save(javaBook);

        bookRepository.findAll().forEach(book -> System.out.println("setup id : " + book.getId()));
    }

    // 1. 책 등록
    @Test
    @DisplayName("책 등록")
    public void book_save_test() {
        System.out.println("1번 메서드 시작");
        // given (데이터 준비) : 클라이언트에게 받을 데이터를 내가 만들어줌.
        String title = "junit5";
        String author = "메타코딩";
        Book javaBook = Book.builder()
                .author(author)
                .title(title)
                .build();

        // when (테스트 실행)
        Book bookPS = bookRepository.save(javaBook); // db에 영속화된 book

        // then (검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());

    } // 트랜잭션 종료 (저장된 데이터를 초기화 함)

    // 2. 책 목록보기
    @Test
    @DisplayName("책 목록 보기")
    public void book_findAll_test() {
        System.out.println("2번 메서드 시작");
        // given
        String title = "junit5";
        String author = "겟인데어";

        // when
        List<Book> books = bookRepository.findAll();

        // then
        assertEquals(title, books.get(0).getTitle());
        assertEquals(author, books.get(0).getAuthor());

    }

    // 3. 책 한건보기
    @Test
    @DisplayName("책 한건 보기")
    public void book_find_test() {
        System.out.println("3번 메서드 시작");
        // given
        String title = "junit5";
        String author = "겟인데어";

        // when
        System.out.println("error@@@@@@@@@@@@@@@@@@@@@@@@");
        Book bookPS = bookRepository.findById(5L).get();

        // then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());

    }


    // 4. 책 삭제

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("책 삭제")
    public void book_delete_test() {
        System.out.println("4번 메서드 시작");

        // given
        Long id = 1L;

        // when
        bookRepository.deleteById(id);

        // then
        assertFalse(bookRepository.findById(id).isPresent()); // 삭제를 했기때문에 값이 없어야 test 성

    }

    // 5. 책 수정
    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("책 수정")
    public void book_modify_test() {
        System.out.println("5번 메서드 시작");

        // given
        Long id = 1L;
        String title = "junit5";
        String author = "메타코딩";
        Book book = new Book(id, title, author);

        // when
        Book bookPS = bookRepository.save(book);

//        bookRepository.findAll().
//                forEach(book1 -> {
//                    System.out.println(book1.getId());
//                    System.out.println(book1.getTitle());
//                    System.out.println(book1.getAuthor());
//                    System.out.println("========================");
//                });
        // then

        assertEquals(id, book.getId());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
    }

}
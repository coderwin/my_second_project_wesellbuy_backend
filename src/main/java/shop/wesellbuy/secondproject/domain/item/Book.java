package shop.wesellbuy.secondproject.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.web.item.BookForm;
import shop.wesellbuy.secondproject.web.item.FurnitureForm;

@Entity
@DiscriminatorValue("B")
@Getter
@Slf4j
public class Book extends Item {

    private String author; // 저자
    private String publisher; // 출판사

    // ** setter ** //

    public void addAuthor(String author) {
        this.author = author;
    }

    public void addPublisher(String publisher) {
        this.publisher = publisher;
    }

    // ** 생성 메서드 ** //
    // item controller 만들 때, 나중에 다시 생각
    public static Book createBook(BookForm bookForm, Member member) {
        Book book = new Book();

        book.addStock(bookForm.getStock());
        book.addPrice(bookForm.getPrice());
        book.addName(bookForm.getName());
        book.addContent(bookForm.getContent());
        book.addStatus(ItemStatus.R);
        book.addMember(member);
        book.addAuthor(bookForm.getAuthor());
        book.addPublisher(bookForm.getPublisher());
        // 각각의 itemPicture에 item 등록
        if(bookForm.getItemPictureList() != null) {
            log.info("ItemPicureList iterater 실행 중...");
//            bookForm.getItemPictureList().forEach((ip) -> ip.addItem(book));
            bookForm.getItemPictureList().forEach((ip) -> book.addItemPictures(ip));
        }
        return book;
    }
}

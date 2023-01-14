package shop.wesellbuy.secondproject.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.web.item.BookForm;
import shop.wesellbuy.secondproject.web.item.FurnitureForm;

@Entity
@DiscriminatorValue("B")
@Getter
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
    public static Book createBookForm(BookForm BookForm, Member member) {
        Book book = new Book();

        book.addStock(BookForm.getStock());
        book.addPrice(BookForm.getPrice());
        book.addContent(BookForm.getContent());
        book.addMember(member);
        book.addAuthor(BookForm.getAuthor());
        book.addPublisher(BookForm.getPublisher());
        // 각각의 itemPicture에 item 등록
        book.getItemPictureList().forEach((ip) -> book.addItemPictures(ip));

        return book;
    }
}

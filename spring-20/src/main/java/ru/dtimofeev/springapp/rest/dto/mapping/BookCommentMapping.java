package ru.dtimofeev.springapp.rest.dto.mapping;

import org.springframework.stereotype.Component;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.rest.dto.BookCommentDto;

@Component
public class BookCommentMapping {

    public BookCommentDto toDto(BookComment bookComment) {
        return new BookCommentDto(bookComment.getId(), bookComment.getCommentText());
    }

    public BookComment toEntity(BookCommentDto bookCommentdto) {
        return new BookComment(bookCommentdto.getId(), bookCommentdto.getCommentText());
    }

}

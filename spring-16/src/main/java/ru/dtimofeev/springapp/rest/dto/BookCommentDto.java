package ru.dtimofeev.springapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dtimofeev.springapp.models.BookComment;

@Data
@AllArgsConstructor
public class BookCommentDto {

    private long id;
    private String commentText;

    public static BookCommentDto toDto(BookComment bookComment) {
        return new BookCommentDto(bookComment.getId(), bookComment.getCommentText());
    }
}

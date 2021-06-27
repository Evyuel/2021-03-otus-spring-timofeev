package ru.dtimofeev.springapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dtimofeev.springapp.models.BookComment;

@Data
@AllArgsConstructor
public class BookCommentDto {

    private long id;
    private String commentText;


}

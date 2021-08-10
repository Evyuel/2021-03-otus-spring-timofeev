package ru.dtimofeev.springapp.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CommentExistIndicator implements HealthIndicator {

    private final CommentExistIndicatorService commentExistIndicatorService;

    @Autowired
    public CommentExistIndicator(CommentExistIndicatorService commentExistIndicatorService) {
        this.commentExistIndicatorService = commentExistIndicatorService;
    }

    @Override
    @Transactional
    public Health health() {
        return commentExistIndicatorService.isThereBooksWithoutComments()
                ? Health.down().status(Status.DOWN).withDetail("Приказ", "Есть книги без комментариев! Срочно исправлять ситуацию!").build()
                : Health.up().status(Status.UP).withDetail("Сообщение", "Все пучком, не переживаем").build();
    }
}

package ru.dtimofeev.springapp;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.dtimofeev.springapp.domain.Rice;

@MessagingGateway
public interface DecisionMaker {

    @Gateway(requestChannel = "ingredientChannel", replyChannel = "foodChannel")
    Object cook(Rice rice);
}

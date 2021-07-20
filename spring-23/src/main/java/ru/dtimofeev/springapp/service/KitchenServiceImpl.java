package ru.dtimofeev.springapp.service;

import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.domain.Pilaf;
import ru.dtimofeev.springapp.domain.Rice;
import ru.dtimofeev.springapp.domain.RicePorridge;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Override
    public Object cook(Rice rice) {
        System.out.println("Is there enough milk: " + (rice.isThereEnoughMilk() ? " Yes " : " No "));
        System.out.println("Cooking " + (rice.isThereEnoughMilk() ? " rice porridge " : " pilaf "));
        return rice.isThereEnoughMilk() ? new RicePorridge("Good") : new Pilaf("Good");
    }
}

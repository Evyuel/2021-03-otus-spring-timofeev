package ru.dtimofeev.springapp.domain;

public class Rice {

    private final boolean IsThereEnoughMilk;

    public Rice(boolean isThereEnoughMilk) {
        IsThereEnoughMilk = isThereEnoughMilk;
    }

    public boolean isThereEnoughMilk() {
        return IsThereEnoughMilk;
    }
}

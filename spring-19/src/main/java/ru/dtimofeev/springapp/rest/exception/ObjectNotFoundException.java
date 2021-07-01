package ru.dtimofeev.springapp.rest.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String object, String attribute, Object attributeValue) {
        super(object + " with " + attribute + ": " + attributeValue + " not found");
    }
}

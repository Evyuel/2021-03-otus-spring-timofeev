package ru.dtimofeev.springapp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.repositories.AuthorRepository;
import ru.dtimofeev.springapp.rest.dto.AuthorDto;
import ru.dtimofeev.springapp.rest.dto.mapping.AuthorMapping;
import ru.dtimofeev.springapp.rest.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapping authorMapping;
    private final String objectName = "Author";

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapping authorMapping) {
        this.authorRepository = authorRepository;
        this.authorMapping = authorMapping;
    }

    @HystrixCommand(commandKey="authorKey", fallbackMethod="getSingleAuthor")
    @Override
    public AuthorDto getById(long id) {
        sleepRandomly();
        Author author = authorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectName, "id", id));
        return authorMapping.toDto(author);
    }

    @HystrixCommand(commandKey="authorKey", fallbackMethod="getListOfAuthors")
    @Override
    public List<AuthorDto> getAll() {
        sleepRandomly();
        return authorRepository.findAll().stream().map(author -> authorMapping.toDto(author)).collect(Collectors.toList());
    }

    @Override
    public AuthorDto save(AuthorDto authordto) {
        Author a = authorRepository.save(authorMapping.toEntity(authordto));
        return authorMapping.toDto(a);
    }

    public AuthorDto getSingleAuthor(long id){
        return new AuthorDto(0,"This_default_Author");
    }

    public List<AuthorDto> getListOfAuthors(){
        return List.of(new AuthorDto(0,"This_Author_1"),new AuthorDto(0,"This_Author_2"));
    }

    private void sleepRandomly() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if(randomNum == 3) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

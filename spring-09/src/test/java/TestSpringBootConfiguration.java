import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ru.dtimofeev.spring.service.AuthorProcessingServiceImpl;

@ComponentScan(basePackageClasses = {AuthorProcessingServiceImpl.class})
@SpringBootConfiguration
public class TestSpringBootConfiguration {
}

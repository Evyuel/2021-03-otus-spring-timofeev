import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.config.TestLocaleResolver;
import ru.dtimofeev.spring.dao.DataReaderCSV;
import ru.dtimofeev.spring.dao.QuestionCSV;

@ComponentScan(basePackageClasses = {Config.class, DataReaderCSV.class, TestLocaleResolver.class, QuestionCSV.class})
@SpringBootConfiguration
public class TestSpringBootConfiguration {

}

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.dao.DataReaderCSV;
import ru.dtimofeev.spring.dao.QuestionResourceDaoCSV;
import ru.dtimofeev.spring.service.localization.QuestionLocaleResolver;

@ComponentScan(basePackageClasses = {Config.class, DataReaderCSV.class, QuestionLocaleResolver.class, QuestionResourceDaoCSV.class})
@SpringBootConfiguration
public class TestSpringBootConfiguration {

}

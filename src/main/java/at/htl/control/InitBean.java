package at.htl.control;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class InitBean {
    @ConfigProperty(name = "carfile")
    String carfileName;

    @ConfigProperty(name = "personfile")
    String personfileName;

    void startUp(@Observes StartupEvent event){
        importFile(carfileName);
        importFile(personfileName);
    }

    List<String> importFile(String fileName) {
        System.out.println(fileName);
        try{
            var path = Paths.get(getClass().getClassLoader()
                    .getResource(fileName).toURI());
            //var path = Path.of(carfileName);
            Stream<String> lines = Files.lines(path);
            //throw new RuntimeException("xy");
            return lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}

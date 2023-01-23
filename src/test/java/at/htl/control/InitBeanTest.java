package at.htl.control;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class InitBeanTest {
    @Inject
    InitBean initBean;

    @ConfigProperty(name = "carfile")
    String carfileName;

    @ConfigProperty(name = "personfile")
    String personfileName;
    @Test
    void importFile_importCars_ok() {
        //arange
        //act
        List<String> lines = initBean.importFile(carfileName);
        //assert
        assertThat(lines).isNotEmpty();
        for (int i = 0; i < 10; i++) {
            System.out.println(lines.get(i));
        }
    }

    @Test
    void importFile_importPerson_ok() {
        List<String> lines = initBean.importFile(personfileName);
        //assert
        assertThat(lines).isNotEmpty();
        for (int i = 0; i < 10; i++) {
            System.out.println(lines.get(i));
        }
    }
}
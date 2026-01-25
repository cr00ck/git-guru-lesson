package tetingPractices;

import JsonModelClasses.DataForJsonCompilingThroughJackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PracticeObjectToJson {


    @Test
    void fromObjectToJsonWithJackson() throws Exception {
        ObjectMapper om = new ObjectMapper();

        // Create and populate the object
        DataForJsonCompilingThroughJackson person = new DataForJsonCompilingThroughJackson();
        person.setAge(25);
        person.setGender("male");
        person.setLastname("Green");
        person.setHairColor("black");
        person.setHobbies(Arrays.asList("Ps", "coding", "reading", "cooking"));
        person.setName("Mike");

        String json = om.writeValueAsString(person);
        System.out.println(json);

        String prettyJson = om.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println(prettyJson);

    }

    @Test
    void fromJsonToObjectWithJackson() throws Exception {
        ObjectMapper om = new ObjectMapper();

        // Create and populate the object

    }
}


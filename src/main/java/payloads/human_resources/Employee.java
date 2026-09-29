package payloads.human_resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    public Employee(String name, String job) {
        this.name = name;
        this.job = job;
    }

    private String name;
    private String job;
    private String id;
    private String createdAt;

}

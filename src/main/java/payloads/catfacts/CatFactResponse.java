package payloads.catfacts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatFactResponse {

    private String fact;
    private int length;

}

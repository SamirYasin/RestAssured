package api.pojo_classes.go_rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TGWithLombok {

    private String firstName;
    private String lastName;
    private String email;
    private String dob;

}

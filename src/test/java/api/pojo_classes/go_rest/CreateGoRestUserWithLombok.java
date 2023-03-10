package api.pojo_classes.go_rest;

import lombok.Builder;
import lombok.Data;

@Data

@Builder

public class CreateGoRestUserWithLombok {
    /*
    {
    "name": "{{$randomFullName}}",
    "gender": "male",
    "email": "{{$randomExampleEmail}}",
    "status": "active"
}
     */
    private String name;
    private String gender;
    private String email;
    private String status;



}

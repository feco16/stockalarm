package stockalarm.to;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StockUserRegistrationDTO {

    private String userUUID;
    private String lastName;
    private String firstName;
    @NotNull
    private String email;
    @NotNull
    private String password;

}

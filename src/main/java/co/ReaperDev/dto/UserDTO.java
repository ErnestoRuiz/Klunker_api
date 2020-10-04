package co.ReaperDev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String email;
    private String userName;
    private String password;

    public UserDTO(String e, String p){
        this.userName = e;
        this.password = p;
    }

    public UserDTO(int i, String e){
        this.userId = i;
        this.userName = e;
    }
}

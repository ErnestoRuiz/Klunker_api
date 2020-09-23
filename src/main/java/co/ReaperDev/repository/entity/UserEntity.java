package co.ReaperDev.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int userId;
    private String email;
    private String userName;
    private String password;

    public UserEntity(String e, String p){
        this.userName = e;
        this.password = p;
    }

    public UserEntity(int i, String e){
        this.userId = i;
        this.userName = e;
    }

    public UserEntity(String e, String u, String p){
        this.email = e;
        this.userName = u;
        this.password = p;
    }
}

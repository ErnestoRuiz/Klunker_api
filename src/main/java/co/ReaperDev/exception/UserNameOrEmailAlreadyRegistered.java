package co.ReaperDev.exception;

public class UserNameOrEmailAlreadyRegistered extends RuntimeException{
    public UserNameOrEmailAlreadyRegistered(){
        super("Username or email already registered");
    }
}

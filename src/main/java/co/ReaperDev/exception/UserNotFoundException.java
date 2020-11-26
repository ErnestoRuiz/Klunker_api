package co.ReaperDev.exception;

public class UserNotFoundException extends RuntimeException{
        public UserNotFoundException(String UserName){
            super(String.format("Username: %s not found", UserName));
        }

}

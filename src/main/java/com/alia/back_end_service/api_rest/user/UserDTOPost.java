package com.alia.back_end_service.api_rest.user;

/*public class UserDTOPost {
    private String username;

    private String mail;

    private String password;

    //Algo con foto que hacemos
}*/
public record UserDTOPost(String username, String mail, String password, String photo) {}


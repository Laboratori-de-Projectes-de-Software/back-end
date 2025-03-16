package com.alia.back_end_service.domain.user.ports;

import com.alia.back_end_service.api_rest.user.LoginDTO;
import com.alia.back_end_service.api_rest.user.UserDTOGet;
import com.alia.back_end_service.api_rest.user.UserDTOPost;

public interface UserPortAPI {
    UserDTOGet registerUser(UserDTOPost userDTOPost);
    UserDTOGet loginUser(LoginDTO userDTOPost);
}

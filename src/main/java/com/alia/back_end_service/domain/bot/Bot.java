package com.alia.back_end_service.domain.bot;

import com.alia.back_end_service.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bot {
    private String name;

    private String description;

    private String endpoint;

    private String token;

    private User user;
}

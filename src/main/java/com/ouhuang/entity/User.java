package com.ouhuang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ouhuang.utils.Generator;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull
    private Long user_id;

    @NotEmpty
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    private String mobile;

    private String username;
    private String password;
    private String avatar;

    @NotEmpty
    @Email
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;

    public static User getRandom() {
        User user = new User();
        user.setUsername(Generator.username(6));
        user.setPassword(Generator.password(8));
        user.setMobile(Generator.mobile());
        user.setEmail(Generator.email(11));
        return user;
    }

    public UserDTO DTO() {
        return new UserDTO(user_id, mobile, username, avatar, email);
    }
}

package com.ouhuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long user_id;
    private String mobile;
    private String username;
    private String avatar;
    private String email;

}

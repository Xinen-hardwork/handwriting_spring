package org.xinen.proxy.test;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserService {

    public String name;


    public void queryUserInfo(){
        System.out.println("查询用户信息"+ name);
    }
}

package com.springboot.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String gotoWelcomePage(ModelMap model) {
        // 모델에 "name" 속성을 추가하고, 현재 로그인한 사용자의 이름을 할당합니다.
        model.put("name", getLoggedInUsername());
        return "welcome";
    }

    // 현재 로그인한 사용자의 이름을 반환하는 메서드
    private String getLoggedInUsername() {
        // 현재 사용자의 인증 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 인증 정보에서 사용자 이름을 반환합니다.
        return authentication.getName();
    }

}

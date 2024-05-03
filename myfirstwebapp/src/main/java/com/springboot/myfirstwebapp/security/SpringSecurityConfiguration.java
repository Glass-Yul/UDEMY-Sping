package com.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security가 웹 보안을 활성화하고 웹 보안을 구성할 수 있도록 지시함
public class SpringSecurityConfiguration {

    // LDAP or Database
    // In Memory

    // InMemoryUserDetailsManager 빈을 생성하여 사용자 정보를 메모리에 보관하는 방식을 선택합니다.
    //InMemoryUserDetailsManager(UserDetails... users)

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        // 새로운 사용자 정보를 생성하여 UserDetails 객체로 변환합니다.
        UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
        UserDetails userDetails2 = createNewUser("ranga", "dummydummy");

        // 생성된 UserDetails 객체들을 사용하여 InMemoryUserDetailsManager를 생성하고 반환합니다.
        //  InMemoryUserDetailsManager :  Spring Security에서 제공 => 메모리에 사용자 정보를 보관하고 관리하는 데 사용
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    // 새로운 사용자 정보를 생성하는 메서드
    private UserDetails createNewUser(String username, String password) {
        // 비밀번호를 암호화하는 함수를 정의합니다. 여기서는 BCryptPasswordEncoder를 사용하여 비밀번호를 암호화합니다.
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        // 사용자 정보를 UserDetails 객체로 생성합니다. 사용자 이름, 암호, 역할(롤)을 설정합니다.
        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder) // 암호화 함수를 지정합니다.
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    // 비밀번호를 암호화하는 데 사용할 PasswordEncoder 빈을 생성합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //All URLs are protected
    //A login form is shown for unauthorized requests
    //CSRF disable
    //Frames

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        http.formLogin(withDefaults());

        // CSRF 보호를 비활성화합니다.
        http.csrf().disable();
        // X-Frame-Options 헤더를 비활성화합니다.
        http.headers().frameOptions().disable();

        return http.build();
    }


}

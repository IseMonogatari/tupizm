package org.preproject.CRUDSecAndFront.config;

import org.preproject.CRUDSecAndFront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //именно из-за этого Сервис должен наслед от класса UserDetailService
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .cors().disable()
                .csrf().disable()
                    .authorizeRequests()
                    //Доступ только для не зарегистрированных пользователей
                    .antMatchers("/registration").permitAll()
                    .antMatchers("/user/**").permitAll()
                    .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                    .successHandler(successUserHandler) // подключаем наш SuccessHandler для перенеправления по ролям
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();

    }
}
//.antMatchers("/login").not().fullyAuthenticated()
//                    //Доступ только для пользоавтелей с ролью USER
//                    .antMatchers("/user").hasRole("USER")
//                    .anyRequest().authenticated()



//.and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    // Перенаправление на страницу User
//                    .defaultSuccessUrl("/user")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .invalidateHttpSession(true)
//                    .clearAuthentication(true)
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/login?logout")
//                    .permitAll();



//.csrf().disable()
//                    .authorizeRequests()
//                    //Доступ только для не зарегистрированных пользователей
//                    .antMatchers("/registration").not().fullyAuthenticated()
//                    //Доступ только для пользователей с ролью Администратор
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/user").hasRole("USER")
//                    //Доступ разрешен всем пользователей
//                    .antMatchers("/", "/resources/**").permitAll()
//                    //Все остальные страницы требуют аутентификации
//                    .anyRequest().authenticated()
//                .and()
//                    //Настройка для входа в систему
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                    ///Перенарпавление на главную страницу после успешного входа
//                    //.defaultSuccessUrl("/")
//                    //Перенаправление на страницу User
//                    //.defaultSuccessUrl("/user?name")
//                    .formLogin()
//                    .loginPage("/user")
//                    .loginProcessingUrl("/user")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .invalidateHttpSession(true)
//                    .clearAuthentication(true)
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/login?logout")
//                    .permitAll();
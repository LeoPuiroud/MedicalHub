package projet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import projet.service.MyUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private DataSource dataSource;

	@Autowired
	private MyUserDetailsService muds;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//avec webservice
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).anonymous();
		http.authorizeRequests().antMatchers("/rest/**").authenticated().and().httpBasic().and().csrf().disable();
		// définir les accès aux pages
		// accès à tout
		//http.authorizeRequests().anyRequest().permitAll();

		// accès libre à toutes les listes
		//http.authorizeRequests().antMatchers("/").permitAll();
		
		//http.authorizeRequests().antMatchers("/rest/**").permitAll();
		
		//http.authorizeRequests().antMatchers("/**/list").authenticated();
		//http.authorizeRequests().antMatchers("/**/edit").hasAnyRole("ROLE_ADMIN");
		// accès à tous les utilisateurs authentifiés
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// authentification en mémoire
		//auth.inMemoryAuthentication().withUser("toto").password("{noop}tutu").roles("ADMIN");

		// authentification avec une base de données
		// avec jdbc
		// auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select
		// username,password,enable from users where
		// username=?").authoritiesByUsernameQuery("select username,role from user_role
		// where username=?");
		auth.userDetailsService(muds).passwordEncoder(getPasswordEncoder());
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

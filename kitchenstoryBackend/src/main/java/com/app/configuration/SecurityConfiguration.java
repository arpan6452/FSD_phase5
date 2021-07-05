package com.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


/*
 * public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * //.antMatchers("/**").permitAll().anyRequest().authenticated()
 * http.authorizeRequests().anyRequest().permitAll() .and().csrf().disable(); }
 * }
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	public void init(WebSecurity web) throws Exception {
	// Filters will not get executed for the resources
	super.init(web);
	web.ignoring().antMatchers("/", "/resources/**", "/static/**", "/public/**", "/webui/**", "/h2-console/**",
	"/configuration/**", "/swagger-ui/**", "/swagger-resources/**", "/api-docs", "/api-docs/**",
	"/v2/api-docs/**", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
	"/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf", "/**/*.woff", "/**/*.otf");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {  
        http.cors().and().csrf().disable().exceptionHandling()
        	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and().authorizeRequests()
        	.and().antMatcher("/**/*").csrf().and().formLogin()  
            .permitAll();
    }  
}
//public class SecurityConfiguration{
//	
//}
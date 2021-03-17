package ifrn.projeto.curriculos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll()
		.antMatchers("formCurriculo").hasRole("USU_CADASTRADO")
		.antMatchers("listarCurriculo").hasRole("USU_EMPRESA")
		.antMatchers("formEmpresa").hasRole("USU_EMPRESA")
		.antMatchers("listarEmpresa").hasRole("USU_ADM")
		.anyRequest().authenticated().and().formLogin().loginPage("/login")
		.permitAll().and().logout().logoutSuccessUrl("/login?logout").permitAll();
		
	}

}

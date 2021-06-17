package coop.home.bankaccount.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import coop.home.bankaccount.config.constant.Constantes.JwtVariables;
import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.bankaccount.excepcion.UnAuthorizedException;
import coop.home.bankaccount.repository.IFinancialusersRepository;
import coop.home.bankaccount.repository.entity.Financialusers;
import coop.home.bankaccount.service.LoginUsuarioServicio;
import coop.home.bankaccount.util.BCryptUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
class LoginUsuarioServicioImpl implements LoginUsuarioServicio {

	@Autowired
	private IFinancialusersRepository userRepository;
	
	@Autowired
	private MessageSource mensajes;
	

	private static final String BEARER = "Bearer ";

	public TokenBankAccountDTO logInUsuario(FinancialUsersLoginDTO financialUser) {
		
		Financialusers user = userRepository.findByFinancialusersPK_idfinancialcompanyAndFinancialusersPK_Loginuser(
				financialUser.getIdfinancialcompany(),
				financialUser.getLoginuser());
		
//		String salt = BCryptEquidad.gensalt();
//		String contrasena = BCryptEquidad.hashpw(clave, salt);

		if (user != null && user.getFinancialusersPK().getLoginuser() != null) {
			if (BCryptUtil.checkpw(financialUser.getPass(), user.getPass())) {
				return new TokenBankAccountDTO(this.getJWTToken(user.getFinancialusersPK().getLoginuser(),"ROLE_USER",user.getIddocument()));
			}
			else {
				throw new UnAuthorizedException(mensajes.getMessage("app.security.userUnAuthorized.code", null, LocaleContextHolder.getLocale()),
						mensajes.getMessage("app.security.userUnAuthorized.message", null, LocaleContextHolder.getLocale()));
			}	
		}
		else {
			throw new UnAuthorizedException(mensajes.getMessage("app.security.userUnAuthorized.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.security.userUnAuthorized.message", null, LocaleContextHolder.getLocale()));
		}	
	}

	private String getJWTToken(String user, String rol, String identificacion) {
		String secretKey = JwtVariables.LLAVE_SECRETA;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(rol);
		
		Claims subject = Jwts.claims().setId(JwtVariables.ID).setSubject(user);
		subject.put(JwtVariables.AUTHORITIES, grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
			
		subject.put(JwtVariables.NIT, identificacion);
		
		String token = "";
		

			token = Jwts.builder().setId(JwtVariables.ID).setSubject(user)
					.addClaims(subject)
					.setExpiration(new Date(System.currentTimeMillis() + 1800000))
					.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		

		return BEARER + token;
	}

	
	public TokenBankAccountDTO renovarToken(String token) {

		String jwtToken = token.replace(JwtVariables.BEARER, "");
		Claims claims = Jwts.parser().setSigningKey(JwtVariables.LLAVE_SECRETA.getBytes()).parseClaimsJws(jwtToken).getBody();
		String role = claims.get(JwtVariables.AUTHORITIES).toString().replaceAll("\\[|\\]", "");
		String intermediario = claims.get(JwtVariables.NIT, String.class);
		return new TokenBankAccountDTO(getJWTToken(claims.getSubject(),role,intermediario));

	}

}

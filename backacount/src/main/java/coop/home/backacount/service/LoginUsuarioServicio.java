package coop.home.backacount.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import coop.home.backacount.config.constant.Constantes.JwtVariables;
import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.backacount.repository.IFinancialusersRepository;
import coop.home.backacount.repository.entity.Financialusers;
import coop.home.backacount.util.BCryptUtil;
import coop.home.backacount.excepcion.UnAuthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


public interface LoginUsuarioServicio {

	public TokenBackAcountDTO logInUsuario(FinancialUsersLoginDTO financialUser) ;

	public TokenBackAcountDTO renovarToken(String token) ;

}

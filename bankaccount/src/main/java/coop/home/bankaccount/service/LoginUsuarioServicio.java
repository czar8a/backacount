package coop.home.bankaccount.service;

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
import coop.home.bankaccount.util.BCryptUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


public interface LoginUsuarioServicio {

	public TokenBankAccountDTO logInUsuario(FinancialUsersLoginDTO financialUser) ;

	public TokenBankAccountDTO renovarToken(String token) ;

}

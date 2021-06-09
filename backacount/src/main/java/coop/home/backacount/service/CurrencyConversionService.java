package coop.home.backacount.service;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;


import coop.home.backacount.excepcion.BusinessException;
import lombok.extern.slf4j.Slf4j;

public interface CurrencyConversionService {
	

	public BigDecimal getUSConvertion(String symbol, BigDecimal amount) ;
}

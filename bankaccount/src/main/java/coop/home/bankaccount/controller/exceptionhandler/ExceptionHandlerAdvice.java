package coop.home.bankaccount.controller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import coop.home.bankaccount.excepcion.BusinessException;
import coop.home.bankaccount.excepcion.TechnicalException;
import coop.home.bankaccount.excepcion.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;

 

@RestControllerAdvice
@Slf4j
class ExceptionHandlerAdvice {
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorTemplateDTO handleBusiness(BusinessException runtime) {
		log.error("BusinessException code:{} Message: {}",runtime.getCodigo(),runtime.getMensaje());
		
		return new ErrorTemplateDTO(runtime.getCodigo(), runtime.getMensaje());
	}
	
	@ExceptionHandler(TechnicalException.class)
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	public ErrorTemplateDTO handleTechnical(TechnicalException runtime) {
		log.error("TechnicalException code:{} Message: {}",runtime.getCodigo(),runtime.getMensaje());
		
		return new ErrorTemplateDTO(runtime.getCodigo(), runtime.getMensaje());
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorTemplateDTO handleUnAuthorized(UnAuthorizedException runtime) {
		log.error("UnAuthorizedException code:{} Message: {}",runtime.getCodigo(),runtime.getMensaje());
		
		return new ErrorTemplateDTO(runtime.getCodigo(), runtime.getMensaje());
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorTemplateDTO handleArgumentNotValid(MethodArgumentNotValidException runtime) {
		
		String error;
		FieldError fieldError= runtime.getFieldError();
		
		if(fieldError!=null) {
		
			error = "Field "+fieldError.getField()+ ", "+fieldError.getDefaultMessage();
		}
		else {
			StringBuilder errores = new StringBuilder();
			
			for(ObjectError Oerror: runtime.getAllErrors()) {
				errores.append(Oerror.getCode() +", "+ Oerror.getObjectName() +", "+   Oerror.getDefaultMessage()+"| ");
			}
			
			error =errores.toString();
		}
		
		log.error("MethodArgumentNotValidException code:{} Message: {}",error);
		
		return new ErrorTemplateDTO(Integer.toString(HttpStatus.BAD_REQUEST.value()), error);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorTemplateDTO handleException(Exception runtime) {
		
		String message = runtime.getMessage()==null?" ":runtime.getMessage();
		
		log.error("Exception Message: {}",message);
		
		return new ErrorTemplateDTO(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), message);
	}
	

}

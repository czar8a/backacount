package coop.home.backacount.excepcion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class BusinessException extends  RuntimeException {
	private static final long serialVersionUID = -4480445472031108452L;

	private String codigo;

	private String mensaje;
} 

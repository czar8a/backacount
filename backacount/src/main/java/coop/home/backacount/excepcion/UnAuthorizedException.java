package coop.home.backacount.excepcion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class UnAuthorizedException extends  RuntimeException {
	private static final long serialVersionUID = -4480445345031108452L;

	private String codigo;

	private String mensaje;
} 

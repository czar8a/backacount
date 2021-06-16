package coop.home.backaccount.excepcion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TecnicalException extends  RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String codigo;
	private final String mensaje;
}

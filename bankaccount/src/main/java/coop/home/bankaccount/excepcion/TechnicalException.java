package coop.home.bankaccount.excepcion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TechnicalException extends  RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String codigo;
	private final String mensaje;
}

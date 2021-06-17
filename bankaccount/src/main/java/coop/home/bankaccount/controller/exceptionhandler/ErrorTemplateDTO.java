package coop.home.bankaccount.controller.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Respuesta basica para los diferentes servicios
 * @author joestupinan
 *
 */
@Data
@AllArgsConstructor
class ErrorTemplateDTO {
	private String codigo ;
	private String mensaje ;
}

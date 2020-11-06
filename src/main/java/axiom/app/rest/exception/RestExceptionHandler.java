package axiom.app.rest.exception;



import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle ApiException
	 *
	 * @param ex      the ApiException that is thrown when exception happened
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<Object> handleServiceException(ApiException ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}

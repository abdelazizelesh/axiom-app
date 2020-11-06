package axiom.app.rest.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

public class ApiError implements Serializable {

	private HttpStatus status;
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",
	 timezone="GMT")
	private LocalDateTime timestamp;
	private String message;
	private String responseStatusCode = "0" ; 

	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	public ApiError(HttpStatus status) {
		this();
		this.status = status;
//		 this.status =HttpStatus.OK;
	}

	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
//		 this.status =HttpStatus.OK;
		this.message = message;
	}



	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponseStatusCode() {
		return responseStatusCode;
	}

	public void setResponseStatusCode(String responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}
}

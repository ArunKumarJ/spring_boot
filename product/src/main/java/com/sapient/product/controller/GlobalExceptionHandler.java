package com.sapient.product.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sapient.product.common.ProductException;

//@ControllerAdvice
public class GlobalExceptionHandler {

	/*@ExceptionHandler(ProductException.class)
	public ResponseEntity<Errors> productException(final ProductException e) {
		return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
	}

	private ResponseEntity<Errors> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		return new ResponseEntity<>(new Errors(logRef, message), httpStatus);
	}

	private class Errors {
		String logRef;
		String message;

		public Errors(String logRef, String message) {
			this.logRef = logRef;
			this.message = message;
		}

		public String getLogRef() {
			return logRef;
		}

		public String getMessage() {
			return message;
		}

	}*/
}

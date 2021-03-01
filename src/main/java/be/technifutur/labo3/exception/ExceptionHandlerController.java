package be.technifutur.labo3.exception;

import be.technifutur.labo3.exception.model.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handle(Throwable e){
        if(e.getMessage() != null){
            if (e instanceof NoSuchElementException){
                return ResponseEntity
                        .status(HttpStatus.NOT_ACCEPTABLE)
                        .body(new ErrorDTO(e.getMessage()));
            }
        }

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorDTO("Une erreur inconnue s'est produite. Les développeurs ont été prévenus"));
    }

}

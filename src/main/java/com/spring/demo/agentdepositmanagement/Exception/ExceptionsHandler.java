package com.spring.demo.agentdepositmanagement.Exception;

import com.google.gson.Gson;
import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    private Gson gson = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFound(NotFoundException e) {
        ResponseDto response = new ResponseDto();
        response.setCode("404");
        response.setMessage("Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(gson.toJson(response));
    }
}

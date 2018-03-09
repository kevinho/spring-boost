package io.kvh.springkit.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kvh
 * @date 2017/10/25
 */
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo<Object> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ErrorInfo<Object> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());

        if (e instanceof GeneralException) {
            r.setCode(((GeneralException) e).getCode());
        } else {
            r.setCode(ErrorInfo.ERROR);
        }

        e.printStackTrace();
        log.error("{}", e);

        r.setUrl(req.getRequestURL().toString());
        return r;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorInfo<Object> requestHandlingNoHandlerFound(HttpServletRequest req, Exception e) {
        ErrorInfo<Object> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());

        r.setCode(404);

        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}

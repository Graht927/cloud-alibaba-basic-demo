package cn.graht.exception;

import cn.graht.common.ErrorCode;
import cn.graht.common.ResultApi;
import cn.graht.common.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author GRAHT
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResultApi handlerException(Exception e) {
        e.printStackTrace();
        return ResultUtil.error(ErrorCode.AUTH_ERROR, e.getMessage());
    }
}

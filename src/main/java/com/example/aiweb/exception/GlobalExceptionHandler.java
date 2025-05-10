package com.example.aiweb.exception;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handle404(ResourceNotFoundException ex, Model m) {
        m.addAttribute("errorMessage", ex.getMessage());
        return "error/404";
    }
    @ExceptionHandler(Exception.class)
    public String handle500(Exception ex, Model m) {
        m.addAttribute("errorMessage", "서버 오류가 발생했습니다.");
        return "error/500";
    }
}
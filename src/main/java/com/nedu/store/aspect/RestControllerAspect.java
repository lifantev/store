package com.nedu.store.aspect;

import com.nedu.store.exceptions.RestException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Aspect
@Component
@Slf4j
public class RestControllerAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object throwing(ProceedingJoinPoint pjp) {
        try {

            return pjp.proceed(pjp.getArgs());

        } catch (Throwable throwable) {

            if (!(throwable instanceof RestException)) {
                throwable = new RestException(throwable);
                log.error("Controller error {{}}", throwable.getMessage());
            }

            return ResponseEntity.badRequest()
                    .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                    .body(throwable.toString());
        }
    }
}

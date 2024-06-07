package ua.storozhukk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.storozhukk.dto.HelloResponseDto;

import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    @Value("${env.var1}")
    private String var1;

    @Value("${env.var2}")
    private String var2;

    private static final String BAD_REQUEST_RESPONSE = "How i can say hello if i don't know your name?";

    private static final String OK_RESPONSE = "Hello, %s, how it's going bro ༼ つ ◕_◕ ༽つ?";

    private static final String DESCRIPTION_RESPONSE_TEMPLATE =
            """
                        If you see this text, it means that the deployment was successful!!
                        The following environment variables were passed in:
                            var1 = %s
                            var2 = %s
                    """;

    @GetMapping
    public ResponseEntity<HelloResponseDto> getDescription() {
        HelloResponseDto helloResponseDto = new HelloResponseDto(format(DESCRIPTION_RESPONSE_TEMPLATE, var1, var2));
        return ResponseEntity
                .ok(helloResponseDto);
    }


    @GetMapping("/hello")
    public ResponseEntity<HelloResponseDto> getGreetings(@RequestParam(value = "name") String name) {
        if (name.isEmpty()) {
            log.warn("Name isn't present, throwing error");
            return ResponseEntity
                    .badRequest()
                    .body(new HelloResponseDto(BAD_REQUEST_RESPONSE));
        }
        log.info("Name is present, return OK response");
        HelloResponseDto helloResponseDto = new HelloResponseDto(format(OK_RESPONSE, name));
        return ResponseEntity
                .ok(helloResponseDto);
    }

}


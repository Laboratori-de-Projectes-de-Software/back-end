package uib.lab.api.infraestructure.util.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageConverter {
    private final MessageSource messageSource;

    public String getMessage(MessageCode messageCode, Collection<Object> params, Locale locale) {
        return messageSource.getMessage(messageCode.getCode(), params == null ? null : params.toArray(), locale);
    }
}

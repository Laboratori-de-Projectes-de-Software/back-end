package uib.lab.api.application.errorHandler;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.HttpStatus;

import uib.lab.api.infraestructura.util.message.MessageConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uib.lab.api.application.dto.user.UserDTORegister;
import uib.lab.api.infraestructura.util.ApiMessage;
import uib.lab.api.infraestructura.util.message.MessageCode;
public class RegisterErrorHandler {
    private boolean hasError;
    private StringBuilder messageError;
    private MessageConverter messageConverter;
    private UserDTORegister userRequest;
    private Locale locale;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        NO_NAME("user.no_name_field"),
        NO_EMAIL("user.no_email_field"),
        NO_PASSWORD("user.no_password_field");

        private final String code;
    }

    private Validator validator;

    public RegisterErrorHandler(UserDTORegister lr, Locale l, MessageConverter messageConverter, Validator validator){
        this.userRequest = lr;
        this.locale = l;
        this.messageConverter = messageConverter;
        this.hasError = false;
        this.validator = validator;
    }

    public void checkFieldViolations(){
        Set<ConstraintViolation<UserDTORegister>> violations = validator.validate(userRequest);

        if (!violations.isEmpty()) {
            this.hasError = true;
            messageError = new StringBuilder();
    
            for (ConstraintViolation<UserDTORegister> v : violations) {
                String campo = v.getPropertyPath().toString();
    
                switch (campo) {
                    case "name":
                        messageError.append(messageConverter.getMessage(Message.NO_NAME, null, this.locale));
                        break;
                    case "email":
                        messageError.append(messageConverter.getMessage(Message.NO_EMAIL, null, this.locale));
                        break;
                    case "password":
                        messageError.append(messageConverter.getMessage(Message.NO_PASSWORD, null, this.locale));
                        break;
                    default:
                        messageError.append(campo).append(": ").append(v.getMessage());
                        break;
                }
            }
        }
    }

    public ApiMessage getApiMessageError(){
          return ApiMessage.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(messageError.toString().trim())
                    .build();
    }

    public boolean hasError(){
        return this.hasError;
    }
}

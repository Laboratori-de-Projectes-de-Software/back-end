package uib.lab.api.application.errorHandler;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.http.HttpStatus;

import uib.lab.api.infraestructure.util.message.MessageConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uib.lab.api.application.dto.league.LeagueRequest;
import uib.lab.api.infraestructure.util.ApiMessage;
import uib.lab.api.infraestructure.util.message.MessageCode;

public class LeagueErrorHandler {
    private boolean hasError;
    private StringBuilder messageError;
    private MessageConverter messageConverter;
    private LeagueRequest leagueRequest;
    private Locale locale;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        NO_NAME("league.no_name_field"),
        NO_PLAYTIME("league.no_playtime_field"),
        NO_ROUNDS("league.no_rounds_field");

        private final String code;
    }

    private Validator validator;

    public LeagueErrorHandler(LeagueRequest lr, Locale l, MessageConverter messageConverter, Validator validator){
        this.leagueRequest = lr;
        this.locale = l;
        this.messageConverter = messageConverter;
        this.hasError = false;
        this.validator = validator;
    }

    public void checkFieldViolations(){
        Set<ConstraintViolation<LeagueRequest>> violations = validator.validate(leagueRequest);

        if (!violations.isEmpty()) {
            this.hasError = true;
            messageError = new StringBuilder();
    
            for (ConstraintViolation<LeagueRequest> v : violations) {
                String campo = v.getPropertyPath().toString();
    
                switch (campo) {
                    case "name":
                        messageError.append(messageConverter.getMessage(Message.NO_NAME, null, this.locale));
                        break;
                    case "numRounds":
                        messageError.append(messageConverter.getMessage(Message.NO_ROUNDS, null, this.locale));
                        break;
                    case "playTime":
                        messageError.append(messageConverter.getMessage(Message.NO_PLAYTIME, null, this.locale));
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


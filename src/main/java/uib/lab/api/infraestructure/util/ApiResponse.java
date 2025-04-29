package uib.lab.api.infraestructure.util;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T body;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }
}


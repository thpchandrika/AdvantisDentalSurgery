package cs489.apsd.dentalsurgeryapp.dto;

public record ResponseDto(
       boolean isSuccess,
       Object data,
       Integer status,

       String errorMessage,

       Object fieldError
) {
}

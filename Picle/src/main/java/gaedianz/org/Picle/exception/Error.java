package gaedianz.org.Picle.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Error {

    /**
     * 400 BAD REQUEST
     */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
    INVALID_MULTIPART_EXTENSION_EXCEPTION(HttpStatus.BAD_REQUEST, "확장자가 올바르지 않습니다"),

    /**
     * 404 NOT FOUND
     */
    NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다"),
    NOT_FOUND_TODO_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 투두입니다"),
    NOT_FOUND_ROUTINE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 루틴입니다"),
    NOT_FOUND_SAVE_IMAGE_EXCEPTION(HttpStatus.NOT_FOUND, "저장되지 않은 이미지입니다"),
    NOT_FOUND_IMAGE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 이미지입니다"),


    /**
     * 409 CONFLICT
     */
    ALREADY_EXIST_USER_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 유저입니다"),


    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}

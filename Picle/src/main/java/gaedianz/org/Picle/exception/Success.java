package gaedianz.org.Picle.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum Success {

    /**
     * 201 CREATED
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    CREATE_TODO_SUCCESS(HttpStatus.CREATED, "투두 생성이 완료됐습니다."),
    CREATE_ROUTINE_SUCCESS(HttpStatus.CREATED, "루틴 생성이 완료됐습니다."),


    /**
     * 200 OK
     */
    GET_TODOLIST_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 투두 리스트 조회에 성공했습니다."),
    GET_ROUTINELIST_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 루틴 리스트 조회에 성공했습니다."),
    GET_COMPLETED_TODOLIST_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 완료된 투두 리스트 조회에 성공했습니다."),
    GET_COMPLETED_ROUTINELIST_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 완료된 루틴 리스트 조회에 성공했습니다."),
    UPDATE_TODO_SUCCESS(HttpStatus.OK, "투두 수정이 완료됐습니다."),
    UPDATE_ROUTINE_SUCCESS(HttpStatus.OK, "루틴 수정이 완료됐습니다."),
    DELETE_TODO_SUCCESS(HttpStatus.OK, "투두 삭제가 완료됐습니다."),
    DELETE_ROUTINE_SUCCESS(HttpStatus.OK, "루틴 삭제가 완료됐습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
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
    SIGNUP_SIGNIN_SUCCESS(HttpStatus.CREATED, "회원가입/로그인이 완료됐습니다."),
    CREATE_TODO_SUCCESS(HttpStatus.CREATED, "투두 생성이 완료됐습니다."),
    CREATE_PREVIEW_SUCCESS(HttpStatus.CREATED, "루틴 미리보기 생성이 완료됐습니다."),
    CREATE_ROUTINE_SUCCESS(HttpStatus.CREATED, "루틴 생성이 완료됐습니다."),


    /**
     * 200 OK
     */
    GET_TODOLIST_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 투두 리스트 조회에 성공했습니다."),
    GET_PREVIEW_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 루틴 미리보기 리스트입니다."),
    GET_MY_FEEDS_SUCCESS(HttpStatus.OK, "내 피드 불러오기에 성공했습니다."),
    GET_ALL_FEEDS_SUCCESS(HttpStatus.OK, "탐색 피드 불러오기에 성공했습니다."),
    GET_ROUTINELIST_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 루틴 리스트 조회에 성공했습니다."),
    GET_COMPLETED_TODOLIST_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 완료된 투두 리스트 조회에 성공했습니다."),
    GET_COMPLETED_ROUTINELIST_BY_DATE_SUCCESS(HttpStatus.OK, "해당 날짜의 완료된 루틴 리스트 조회에 성공했습니다."),
    UPDATE_TODO_SUCCESS(HttpStatus.OK, "투두 수정이 완료됐습니다."),
    UPDATE_ROUTINE_SUCCESS(HttpStatus.OK, "루틴 수정이 완료됐습니다."),
    DELETE_TODO_SUCCESS(HttpStatus.OK, "투두 삭제가 완료됐습니다."),
    DELETE_ROUTINE_SUCCESS(HttpStatus.OK, "루틴 삭제가 완료됐습니다."),
    VERIFY_ROUTINE_SUCCESS(HttpStatus.OK, "루틴 검증이 완료됐습니다."),
    ALREADY_VERIFIED_ROUTINE(HttpStatus.OK, "이미 검증된 루틴입니다."),
    FINISH_ROUTINE_SUCCESS(HttpStatus.OK, "루틴 종료가 완료됐습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
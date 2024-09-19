package finalproject.leejeonmoon.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Default
    ERROR(400, "요청 처리에 실패했습니다."),

    // 400 Bad Request
    // 입력 에러
    INVALID_INPUT_FORMAT(400, "유효하지 않은 형식입니다."),
    INVALID_INPUT_LENGTH(400, "입력 길이가 잘못되었습니다."),
    INVALID_INPUT_VALUE(400, "입력값이 잘못되었습니다."),
    MISSING_PARAMETER(400, "필수 파라미터가 누락되었습니다."),
    // 비밀번호, 비밀번호 확인이 서로 불일치
    PASSWORD_MISMATCH(400, "confirmPassword:비밀번호가 일치하지 않습니다."),


    // 401 Unauthorized
    // 로그인 상태여야 하는 요청
    NOT_AUTHENTICATED(401, "로그인 상태가 아닙니다."),
    // 권한이 없는 요청을 보냄
    UNAUTHORIZED_REQUEST(401,"권한이 없습니다."),
    // 로그인 시 잘못된 패스워드 입력
    LOGIN_FAIL(401, "로그인에 실패했습니다.")

    ;


    private final int status;
    private final String message;


}

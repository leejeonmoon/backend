package finalproject.leejeonmoon.domain.member.dto;

import finalproject.leejeonmoon.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequestDto(
        @Email(message = "INVALID_INPUT_FORMAT-유효한 이메일 형식이 아닙니다.")
        @Size(max = 50, message = "INVALID_INPUT_LENGTH-이메일은 50자 이내여야 합니다.")
        @NotEmpty(message = "INVALID_INPUT_VALUE-이메일을 입력하세요.")
        String email,
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()]+$", message = "INVALID_INPUT_FORMAT-비밀번호는 영문 대소문자, 숫자, 특수문자 !@#$%^&*()만 사용 가능합니다.")
        @Size(min = 8, max = 20, message = "INVALID_INPUT_LENGTH-비밀번호는 최소 8자 이상, 20자 이내여야 합니다.")
        @NotEmpty(message = "INVALID_INPUT_VALUE-비밀번호를 입력하세요.")
        String password,
        @NotEmpty(message = "INVALID_INPUT_VALUE-비밀번호 확인을 입력하세요.")
        String confirmPassword) {

    public Member toEntity(String encodedPassword){
        return Member.builder()
                .email(this.email)
                .encodedPassword(encodedPassword)
                .build();
    }
}

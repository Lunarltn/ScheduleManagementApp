package org.example.schedulemanagementapp.global.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.schedulemanagementapp.domain.user.dto.UserBaseResponse;
import org.example.schedulemanagementapp.global.constant.Const;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionUtils {
    /**
     * 세션 유저 아이디 조회
     *
     * @param session 현재 세션
     * @return 로그인된 유저의 아이디
     */
    public static Long getLoginUserIdBySession(HttpSession session) {
        return ((UserBaseResponse) session.getAttribute(Const.LOGIN_USER)).getId();
    }

    /**
     * 로그인 유저 아이디 확인
     *
     * @param request HTTP 정보
     * @return 로그인된 유저의 아이디
     */
    public static Long GetLoginUserIdBySelvet(HttpServletRequest request) {
        return getLoginUserIdBySession(request.getSession(false));
    }
}

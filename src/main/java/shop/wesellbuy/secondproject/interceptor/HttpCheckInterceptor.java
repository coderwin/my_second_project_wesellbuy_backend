package shop.wesellbuy.secondproject.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * HttpCheck Interceptor
 * writer : 이호진
 * init : 2023.02.14
 * updated by writer :
 * update :
 * description : HttpCheck Interceptor 처리 로직 구현
 *               -> restful api 대한 접근 httpMethod 체크
 */
public class HttpCheckInterceptor implements HandlerInterceptor {

    /**
     * writer : 이호진
     * init : 2023.02.14
     * updated by writer : 이호진
     * update :
     * description : restful api에 대한 httpMethod 체크
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 클라이언트 요청 httpMethod 불러오기
        String httpMethod = request.getMethod();

        // 그외 통과 못함
        if(!HttpMethod.GET.matches(httpMethod)) {
            return false;
        }
        // GET이면 통과
        return true;
    }
}

package shop.wesellbuy.secondproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.wesellbuy.secondproject.interceptor.CrosCheckInterceptor;
import shop.wesellbuy.secondproject.interceptor.HttpCheckInterceptor;
import shop.wesellbuy.secondproject.interceptor.LoginCheckInterceptor;

import java.util.List;

/**
 * 사용자 정의 Interceptor 등록
 * writer : 이호진
 * init : 2023.02.14
 * updated by writer :
 * update :
 * description : 사용자 정의 Interceptor 등록
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";

    /**
     * writer : 이호진
     * init : 2023.02.14
     * updated by writer :
     * update :
     * description : 인터셉터들을 등록
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        // 로그인 인증 인터셉터 등록
        // 순서 현재 1번
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/members/login", "/*/images/{savedFileName}", "/members", "/orders", "/error", "/members/id/**"
                );

//        // Cors 처리 인터셉터 등록
//        registry.addInterceptor(new CrosCheckInterceptor())
//                .order(2)
//                .addPathPatterns("/**");
    }

    /**
     * writer : 이호진
     * init : 2023.03.02
     * updated by writer :
     * update :
     * description : Access-Control-Allow-Origin 문제 해결위한 설정
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","));
//                .maxAge(3600); // 3600초 동안 preflight 결과를 캐시에 저장
    }
}

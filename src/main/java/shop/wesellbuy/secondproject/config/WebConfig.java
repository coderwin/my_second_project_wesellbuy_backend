package shop.wesellbuy.secondproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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

    /**
     * writer : 이호진
     * init : 2023.02.14
     * updated by writer :
     * update :
     * description : 인터셉터들을 등록
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // HttpMethod에 따른 요청 처리 인터셉터 등록
        registry.addInterceptor(new HttpCheckInterceptor())
                .order(1)
                .addPathPatterns()
                .excludePathPatterns();


        // 로그인 인증 인터셉터 등록
        // 순서 현재 1번
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/members/login", "/*/images/{savedFileName}", "/members", "/orders", "/error"
                );
    }

}

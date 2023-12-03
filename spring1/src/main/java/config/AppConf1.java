package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap3.spring.MemberDao;
import chap3.spring.MemberPrinter;

// 작성해논 AppCtx.java 파일을 너눠서 설정.

@Configuration
public class AppConf1 {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
    
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
    
}

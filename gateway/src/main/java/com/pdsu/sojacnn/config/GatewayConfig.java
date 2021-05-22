package com.pdsu.sojacnn.config;

import com.pdsu.sojacnn.bean.CrossConfig;
import com.pdsu.sojacnn.bean.EncryptConfig;
import com.pdsu.sojacnn.shiro.LoginRealm;
import com.pdsu.sojacnn.shiro.UserLogoutFilter;
import com.pdsu.sojacnn.shiro.WebCookieRememberMeManager;
import com.pdsu.sojacnn.shiro.WebSessionManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 半梦
 * @create 2021-05-07 20:25
 */
@Log4j2
//如果有多个配置文件，以这个为准
@Primary
@Configuration
public class GatewayConfig {

    @Bean
    public DataSource hikariCP(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    /**
     * 跨域
     */
    @Bean
    public CorsConfiguration buildConfig(CrossConfig crossConfig) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(format(crossConfig.getAllowIpOrigin()));
        log.info("系统初始化...允许以下 IP 进行访问: " + corsConfiguration.getAllowedOrigins());
        corsConfiguration.setAllowedHeaders(format(crossConfig.getAllowHeaderOrigin()));
        log.info("系统初始化...允许添加以下请求头: " + corsConfiguration.getAllowedHeaders());
        corsConfiguration.setAllowedMethods(format(crossConfig.getAllowMethodOrigin()));
        log.info("系统初始化...允许以下请求方式访问: " + corsConfiguration.getAllowedMethods());
        corsConfiguration.setExposedHeaders(format(crossConfig.getExposedHeaderOrigin()));
        log.info("系统初始化...允许以下请求头暴露: " + corsConfiguration.getExposedHeaders());
        corsConfiguration.setAllowCredentials(true);
        log.info("系统初始化...是否允许保持用户认证状态: " + corsConfiguration.getAllowCredentials());
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(CorsConfiguration configuration) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); //注册
        return new CorsFilter(source);
    }

    private List<String> format(String ... all) {
        return Arrays.asList(all);
    }

    @Bean
    public ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站-新闻网")
                .description("本文档描述了新闻网全体服务")
                .version("1.0.0")
                .contact(new Contact("半梦Oo", "https://github.com/pyb1430501241", "1430501241@qq.com"))
                .build();
    }

    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    @Bean
    public net.sf.ehcache.CacheManager cacheManager(EhCacheManagerFactoryBean cacheManagerFactoryBean) {
        return cacheManagerFactoryBean.getObject();
    }

    @Bean
    public ModularRealmAuthenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
        return authenticator;
    }

    @Bean
    public Realm loginRealm(EncryptConfig encrypt) {
        LoginRealm realm = new LoginRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(encrypt.getMode());
        log.info("系统初始化...使用默认加密规则: " + encrypt.getMode());
        credentialsMatcher.setHashIterations(encrypt.getNumber());
        log.info("系统初始化...使用默认加密规则, 默认加密次数: " + encrypt.getNumber());
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean
    public SecurityManager securityManager(net.sf.ehcache.CacheManager cacheManager, Realm loginRealm) {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(loginRealm);
        webSecurityManager.setSessionManager(new WebSessionManager());
        webSecurityManager.setRememberMeManager(new WebCookieRememberMeManager());
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
        webSecurityManager.setCacheManager(ehCacheManager);
        return webSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("logout", new UserLogoutFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}

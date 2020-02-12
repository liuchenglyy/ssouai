package com.sxnx.uam.sever.config;

import com.sxnx.uam.sever.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * 公司：普元金融—F2E
 * 文件名：ShiroRedisConfig
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 10:46
 * 描述：显示自定义注入配置shiro+redis的相关组件
 */
@Configuration
public class ShiroRedisConfig implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    //securityManager-管理subject
    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(null);

        //自定义缓存管理器-redis
        securityManager.setCacheManager(cacheManager());

        //自定义一个存储session的管理器
        securityManager.setSessionManager(sessionManager());

        return securityManager;
    }

    //自定义session缓存管理器
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(env.getProperty("spring.redis.host"));
        redisManager.setPort(env.getProperty("spring.redis.port", Integer.class));
        //链接的超时
        redisManager.setTimeout(env.getProperty("spring.redis.timeout", Integer.class));
        //缓存key的超时
        redisManager.setExpire(env.getProperty("spring.redis.expire", Integer.class));
        return redisManager;
    }


    //自定义session管理器
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    //shiro sessionDao层的实现，通过redis - 使用的是shiro-redis开源插件
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());

        //设置存储在缓存中session的Key的前缀
        redisSessionDAO.setKeyPrefix("spring_shiro_redis_session:");
        return redisSessionDAO;
    }


    //过滤链配置
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //用户访问没有经过授权的接口或者url时跳转的链接
        shiroFilter.setUnauthorizedUrl("/error/403");

        //设定用户没有经过登录认证时的跳转链接
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/success/index");
        Map<String, String> filterChainDefinitionMap = new HashMap();

        filterChainDefinitionMap.put("/uam/login", "anon");
        filterChainDefinitionMap.put("/static/*", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/font-awesome/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/tools/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilter;
    }

    //shiro bean生命周期的管理
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}




































package com.yangjq.mpcommons;

import com.yangjq.commons.CommonsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * commons模块自动配置类
 *
 * ConfigurationProperties 注解不包含@Component注解，因此不会被spring管理。需要
 * 在@EnableConfigurationProperties注解中配置才能被spring管理
 *
 * @author yangjq
 * @since 2022/7/14
 */
@Configuration
@ComponentScan("com.yangjq.mpcommons")
@EnableConfigurationProperties(CommonsProperties.class)
public class MpCommonsAutoConfiguration {


}

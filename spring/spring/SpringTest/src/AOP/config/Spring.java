package AOP.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 党
 * @version 1.0
 * 2022/5/15   14:21
 */
@Configuration
@ComponentScan(basePackages = "AOP")
@EnableAspectJAutoProxy(proxyTargetClass = true) //开启AOP功能
public class Spring {
}

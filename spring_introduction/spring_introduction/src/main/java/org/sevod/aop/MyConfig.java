package org.sevod.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("org.sevod.aop")
@EnableAspectJAutoProxy
public class MyConfig {

}

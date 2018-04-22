运行jsp打包方式必须是war
Application的main方法启动服务器 必须注释scope
原因：加了provided的scope 部署到嵌入式tomcat时会缺失这个jar包
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<!-- <scope>provided</scope>-->
		</dependency>
springmvc的核心控制器 配置映射时
/ 表示拦截所有请求 包括静态资源 但是不包括jsp
/* 拦截所有资源 包括jsp也会被拦截
/** 表示 /user或者/user/xxx 这两种路径都被拦截
拦截器可以配置拦截路径/*或者/**

如果没有配置tomcat对jsp的支持jar包 在应用启动时不能转到jsp 会出现不断的定位/error
就会出现不断重定向到/error
        <dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
		</dependency>


记录日志利用拦截器 根据request和response获得日志的相关信息 比如请求url，请求方法等
注意 拦截器没加在spring工厂里 是不可以注入dao的

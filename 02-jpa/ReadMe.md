##jpa结合Springboot
1.引入jar包，jpa,mysql,lombook等等

2.dao层 继承 JpaRepository(基本增删改查), 继承JpaSpecificationExecutor 复杂增删改查

3 核心控制层代码的书写

##使用druid数据源
需要导入druid的jar包 配置durid数据源的属性
项目启动时应用的就是druid数据源
[com.alibaba.druid.pool:name=dataSource,type=DruidDataSource]
配置druid监控
添加配置类 注册servlet和filter
servlet是StatViewServlet
filter是WebStatFilter
配置黑白名单 登录名和密码 以及过滤的排除的url路径即可

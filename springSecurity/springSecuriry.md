[TOC]



# 1、SpringSecurity 框架介绍

​		Spring 是非常流行和成功的 Java 应用开发框架，Spring Security 正是 Spring 家族中的成员。Spring Security 基于 Spring 框架，提供了一套 Web 应用安全性的完整解决方案。
​		正如你可能知道的关于安全方面的两个主要区域是“认证”和“授权”（或者访问控制），一般来说，Web 应用的安全性包括**用户认证（Authentication）**和**用户授权（Authorization）**两个部分，这两点也是 Spring Security 重要核心功能。
​	（1）**用户认证**指的是：验证某个用户是否为系统中的合法主体，也就是说用户能否访问该系统。用户认证一般要求用户提供用户名和密码。系统通过校验用户名和密码来完成认证过程。通俗点说就是系统认为用户是否能登录
​	（2）**用户授权**指的是验证某个用户是否有权限执行某个操作。在一个系统中，不同用户所具有的权限是不同的。比如对一个文件来说，有的用户只能进行读取，而有的用户可以进行修改。一般来说，系统会为不同的用户分配不同的角色，而每个角色则对应一系列的权限。通俗点讲就是系统判断用户是否有权限去做某些事情。





# 2、SpringSecurity 入门案例

## 2.1、依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```



## 2.2、Controller

```java
/**
 * @author 党
 * @version 1.0
 * 2022/7/12   13:56
 */
@RequestMapping("/test")
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello security";
    }
}
```



## 2.3、页面展示

![](image/Snipaste_2022-07-12_13-59-52.png)



```
默认账号是user

密码：控制台=>Using generated security password: f2d283c3-a68d-455b-a168-cf66b32792da
```



## 2.4、SpringSecurity 基本原理

​	**SpringSecurity 本质是一个过滤器链：**
​	**从启动是可以获取到过滤器链：**



代码底层流程：重点看三个过滤器：

**FilterSecurityInterceptor**：

​		是一个方法级的权限过滤器, 基本位于过滤链的最底部。

**ExceptionTranslationFilter**：

​		是个异常过滤器，用来处理在认证授权过程中抛出的异常

**UsernamePasswordAuthenticationFilter** ：

​		对/login 的 POST 请求做拦截，校验表单中用户名，密码。



## 2.5、UserDetailsService接口

​		当什么也没有配置的时候，账号和密码是由 Spring Security 定义生成的。而在实际项目中账号和密码都是从数据库中查询出来的。 所以我们要通过自定义逻辑控制认证逻辑。
​		**如果需要自定义逻辑时，只需要实现 UserDetailsService 接口即可。接口定义如下：**

【Server层】

```java
public interface UserDetailsService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
```

【pojo层】

```java
public interface UserDetails extends Serializable {

	// 表示获取登录用户所有权限
	Collection<? extends GrantedAuthority> getAuthorities();
    // 表示获取密码
    String getPassword();
    // 表示获取用户名
    String getUsername();
    // 表示判断账户是否过期
    boolean isAccountNonExpired();
    // 表示判断账户是否被锁定
    boolean isAccountNonLocked();
    // 表示凭证{密码}是否过期
    boolean isCredentialsNonExpired();
    // 表示当前用户是否可用
    boolean isEnabled();

}
```

【**校验过程**】

```
自定义 过滤器 继承   UsernamePasswordAuthenticationFilter
重写	
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)方法【校验】

重写  successfulAuthentication  成功

重写 unsuccessfulAuthentication  失败



```





## 2.6、PasswordEncoder接口

【**对密码进行加密**】

```java
// 表示把参数按照特定的解析规则进行解析
String encode(CharSequence rawPassword);
// 表示验证从存储中获取的编码密码与编码后提交的原始密码是否匹配。如果密码匹配，则返回 true；如果不匹配，则返回 false。第一个参数表示需要被解析的密码。第二个参数表示存储的密码。
boolean matches(CharSequence rawPassword, String encodedPassword);
// 表示如果解析的密码能够再次进行解析且达到更安全的结果则返回 true，否则返回false。默认返回 false。
default boolean upgradeEncoding(String encodedPassword) {
return false;
}
```



实现类：

​	**BCryptPasswordEncoder**



```java
@Test
public void test01(){
//
创建密码解析器
BCryptPasswordEncoder bCryptPasswordEncoder = new
BCryptPasswordEncoder();
//
对密码进行加密
String atguigu = bCryptPasswordEncoder.encode("atguigu");
//
打印加密之后的数据
System.out.println("加密之后数据：\t"+atguigu);
//
判断原字符加密后和加密之前是否匹配
boolean result = bCryptPasswordEncoder.matches("atguigu", atguigu);
//
打印比较结果
System.out.println("比较结果：\t"+result);
}

```







# 3、Security Web 权限方案



## 3.1、设置登录系统的账号、密码

### 3.1.1、配制文件

```yaml
spring:
  security:
    user:
      name: dhf
      password: dhf200827
```



### 3.1.2、通过配置类

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //加密
        String encode = passwordEncoder.encode("dhf200827");
        //添加账户，密码
        auth.inMemoryAuthentication().withUser("danghf").password(encode).roles("");
    }
    @Bean//加密必须放入PasswordEncoder对象，默认没有，不然怎么解码
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
```







### 3.1.3、自定义编写实现类

【实现UserDateilsService接口】

```java
@Service("userDetailsService")
public class UserService implements UserDetailsService {
    private final String USERNAME = "danghf";
    private final String PASSWORD = "dhf200827";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过username获取一个UserDetails
        //...查数据库，获取账号密码
        //User实现了userDetails接口
        // select password form user where username = username
        //
        String password = new BCryptPasswordEncoder().encode(PASSWORD);
        //权限集合
        List<GrantedAuthority> authorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(USERNAME, password, authorities);
    }
}
```





【配置】

```java
@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
```





## 3.2、查询数据库完成登录

### 3.2.1、application.yaml

```yaml
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/security
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver

mybatis-plus:
  configuration:
    aggressive-lazy-loading: true
  mapper-locations: classpath:mapper/**/*.xml
```



### 3.2.2、 pojo

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String username;
    private String password;

}
```



### 3.2.3、mapper

```java
@Mapper
public interface UserMapper {
    @Select("select * from users where username = #{username}")
    User getUserByUsername(@Param("username") String username);
}
```



### 3.2.4、service



```java
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.getUserByUsername(username);
        if (user!=null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encode = passwordEncoder.encode(user.getPassword());
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("role");

            return new org.springframework.security.core.userdetails.User(
                    username,encode,authorities
            );
        }else {
            throw new UsernameNotFoundException("用户未找到");
        }
    }
}
```



## 3.3、自定义登录页面



【配置】

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置没有访问权限
        http.exceptionHandling().accessDeniedPage("/unlogin.html");
        http.formLogin()
                .loginPage("/login.html") //登录页面
                .loginProcessingUrl("/user/login") //页面提交地址
                .defaultSuccessUrl("/hello").permitAll() //成功后跳转
                .and()
                .authorizeRequests() //请求批注
                .antMatchers("/","/index").permitAll()//不需要权限
                .antMatchers("/hello").hasAuthority("admin")//需要权限
                // 多个权限
                .antMatchers("/d","/a").hasAnyAuthority("admin","role")
                .antMatchers("/hello").hasRole("admin")//需要角色
                // 多个角色
                .antMatchers("/a","/b").hasAnyRole("admin","role")
                .anyRequest().authenticated() //剩余请求，登陆后访问
                .and()
                .csrf().disable(); //关闭csrf防护
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```



【登录页面】

```html
<body>
    <form method="post" action="/user/login">
        <table border="1">
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <input type="submit" value="login">
            </tr>
        </table>
    </form>
</body>
```



## 3.4、基于权限访问控制



### 3.4.1、hasAuthority方法

**如果当前的主体具有指定的权限，则返回 true,否则返回 false**

【配置类】

```java
@Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置没有访问权限
        http.exceptionHandling().accessDeniedPage("/unlogin.html");
        http.formLogin()
                .loginPage("/login.html") //登录页面
                .loginProcessingUrl("/user/login") //页面提交地址
                .defaultSuccessUrl("/hello").permitAll() //成功后跳转
                .and()
                .authorizeRequests() //请求批注
                .antMatchers("/","/index").permitAll()//不需要权限
                .antMatchers("/hello").hasAuthority("admin")//需要权限
                // 多个权限
                .antMatchers("/d","/a").hasAnyAuthority("admin","role")
                .antMatchers("/hello").hasRole("admin")//需要角色
                // 多个角色，用户拥有任何一个角色都可以访问
                .antMatchers("/a","/b").hasAnyRole("admin","role")
                .anyRequest().authenticated() //剩余请求，登陆后访问
                .and()
                .csrf().disable(); //关闭csrf防护
    }
```



【UserDetailsService】

```java
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.getUserByUsername(username);
//        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username",username);
//        User user1 = mapper.selectOne(wrapper);
        if (user!=null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encode = passwordEncoder.encode(user.getPassword());
            List<GrantedAuthority> authorities;
            if (user.getUsername().equals("admin")){
                // 赋予 admin 权限和  admin角色 ，角色需要 ROLE_前缀
                 authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_admin");
            }else {
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("");
            }

            return new org.springframework.security.core.userdetails.User(
                    username,encode,authorities
            );
        }else {
            throw new UsernameNotFoundException("用户未找到");
        }
    }
}
```



### 3.4.2、hasAnyAuthority方法

**用于允许多个权限访问**

**只要满足某个权限就可以访问**

```java
用于多个权限
.antMatchers("/test/hello").hasAuthority("admin,manager") //admin和manager都可以访问
```





### 3.4.3、hasRole方法

**如果用户具备给定角色就允许访问,否则出现 403。
如果当前主体具有指定的角色，则返回 true。**

【配置类】

```java
.antMatchers("/test/hello").hasRole("admin") // 在hasRole方法中，变成 ROLE_admin
```



【service】

```java
List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_admin");
```



### 3.4.3、hasAnyRole方法



**类似hasRole()**

**满足任意角色**



## 3.5、自定义403页面



```java
@Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置没有访问权限
        http.exceptionHandling().accessDeniedPage("/unlogin.html");
        http.formLogin()
                .loginPage("/login.html") //登录页面
                .loginProcessingUrl("/user/login") //页面提交地址
                .defaultSuccessUrl("/hello").permitAll() //成功后跳转
                .and()
                .authorizeRequests() //请求批注
                .antMatchers("/","/index").permitAll()//不需要权限
                .antMatchers("/hello").hasAuthority("admin")//需要权限
                // 多个权限
                .antMatchers("/d","/a").hasAnyAuthority("admin","role")
                .antMatchers("/hello").hasRole("admin")//需要角色
                // 多个角色，用户拥有任何一个角色都可以访问
                .antMatchers("/a","/b").hasAnyRole("admin","role")
                .anyRequest().authenticated() //剩余请求，登陆后访问
                .and()
                .csrf().disable(); //关闭csrf防护
    }
```



## 3.6、注解使用

### 3.6.1、@Secured

判断是否具有角色，另外需要注意的是这里匹配的字符串需要添加前缀 “**ROLE_**”

使用注解要先开启注解功能

```java
@EnableGlobalMethodSecurity(securedEnabled = true)
```

```java
public @interface Secured {

   /**
    * Returns the list of security configuration attributes (e.g. ROLE_USER,ROLE_ADMIN).
    * @return String[] The secure method attributes
    */
   String[] value();

}
```

【**在控制器上使用注解**】控制器上的就是路径



```java
	@RequestMapping("/Secured")
    @Secured({"ROLE_ADMIN","ROLE_ALL"})
    public String test(){
        return "@Secured 角色注解";
    }
```



【**在登陆时，为用户添加权限，角色**】

```java
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUser(username);
        if (user!=null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(user.getPassword());
            //权限
            List<GrantedAuthority> authorities;
            if (username.equals("admin")){
                // 角色
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin");
            }else {
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("");
            }
            return new org.springframework.security.core.userdetails.User(
                    username,
                    encode,
                    authorities
            );
        }
        return null;
    }
}
```



### 3.6.2、@PreAuthorize

**开启注解功能**

```java
@EnableGlobalMethodSecurity(prePostEnabled = true)
```



```java
public @interface PreAuthorize {

   /**
    * @return the Spring-EL expression to be evaluated before invoking the protected
    * method
    */
   String value();

}
```



@PreAuthorize：注解适合进入方法前的权限验证，@PreAuthorize可以将登录用户的roles/premissons参数传到方法

![](image/Snipaste_2022-07-14_15-48-55.png)



【**Service**】

```java
authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("LUCY");
//用户拥有LUCY权限
```



```java
@RequestMapping("/PreAuthorize")
@ResponseBody
@PreAuthorize("hasAuthority('LUCY')")
public String test2(){
    return "@PreAuthorize 注解";
}
```



### 3.6.3、@PostAuthorize

**开启注解功能**

```java
@EnableGlobalMethodSecurity(prePostEnabled = true)
```



```java
public @interface PostAuthorize {

   /**
    * @return the Spring-EL expression to be evaluated after invoking the protected
    * method
    */
   String value();

}
```



![](image/Snipaste_2022-07-14_16-00-24.png)

在方法执行之后在校验

```java
@RequestMapping("/PostAuthorize")
@PostAuthorize("hasRole('ROLE_ADMIN')")
public String test3(){
    System.err.println("PostAuthorize.......");
    return "PostAuthorize 注解";
}
```



**没有权限的人照样可以执行方法，可以看见控制台输出，但是在返回时检验权限，没有权限，403**



### 3.6.4、@PostFilter

```java
public @interface PostFilter {

   /**
    * @return the Spring-EL expression to be evaluated after invoking the protected
    * method
    */
   String value();

}
```

**权限验证之后对数据进行过滤，留下用户名为admin的数据**

```java
@RequestMapping("/PostFilter")
@ResponseBody
@PostFilter("filterObject.username == 'admin'")
public String test4(){
    List<User> users = new ArrayList<>();
    users.add(new User());
    users.add(new User());
    return "";
}
```



### 3.6.5、@PreFilter

**对传入方法的数据进行过滤**



## 3.7、用户注销



### 9.7.1、在配置类中添加退出映射地址



```java
http.logout().logoutUrl("/login").logoutSuccessUrl("/test/hello").permitAll();


/login ==> 设置退出的地址 随意写
/test/hello ==> 退出后跳转的位置
```



## 3.8、基于数据库的  记住（自动登录）



![](image/Snipaste_2022-07-14_16-34-17.png)





![](image/Snipaste_2022-07-14_16-35-46.png)





### 3.8.1、创建表

```sql
CREATE TABLE `persistent_logins` (
`username` varchar(64) NOT NULL,
`series` varchar(64) NOT NULL,
`token` varchar(64) NOT NULL,
`last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE
CURRENT_TIMESTAMP,
PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```



### 3.8.2、配置类



```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //注入数据源
    @Autowired
    public DataSource dataSource;
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动创建表，,第一次执行会创建，以后要执行就要删除掉！
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    @Autowired
    UserService userService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60) //有效时长 单位 s
                .userDetailsService(userService);

        //用户注销
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
        //自定义403
//        http.exceptionHandling().accessDeniedPage("/unlogin.html");
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/test").permitAll()
                .and()
                .csrf().disable(); //关闭csrf防护
    }
}
```



### 3.8.3、登录页面



```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form th:action="@{/user/login}" method="post">
        用户名：<input type="text" name="username"><br>
        密 码：<input type="password" name="password"><br>
        <!--name是固定的-->
        记住：<input type="checkbox" name="remember-me" th:title="记住密码">
        <input type="submit" value="login">
    </form>
</body>
</html>
```







## 3.9、CSRF



​		跨站请求伪造（英语：Cross-site request forgery），也被称为 one-click
attack 或者 session riding，通常缩写为 CSRF 或者 XSRF， 是一种挟制用户在当前已登录的 Web 应用程序上执行非本意的操作的攻击方法。跟跨网站脚本（XSS）相比，XSS利用的是用户对指定网站的信任，CSRF 利用的是网站对用户网页浏览器的信任。跨站请求攻击，简单地说，是攻击者通过一些技术手段欺骗用户的浏览器去访问一个自己曾经认证过的网站并运行一些操作（如发邮件，发消息，甚至财产操作如转账和购买商品）。由于浏览器曾经认证过，所以被访问的网站会认为是真正的用户操作而去运行。这利用了 web 中用户身份验证的一个漏洞：简单的身份验证只能保证请求发自某个用户的浏览器，却不能保证请求本身是用户自愿发出的。从 Spring Security 4.0 开始，默认情况下会启用 CSRF 保护，以防止 CSRF 攻击应用程序，Spring Security CSRF 会针对 PATCH，POST，PUT 和 DELETE 方法进行防护。












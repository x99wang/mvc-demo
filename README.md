#  自定义 MVC-demo

[![](https://jitpack.io/v/x99wang/mvc-demo.svg)](https://jitpack.io/#x99wang/mvc-demo)

在 Java Web (Maven) 项目`pom.xml`中添加仓库及依赖

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.x99wang</groupId>
	    <artifactId>mvc-demo</artifactId>
	    <version>0.1-SNAPSHOT</version>
	</dependency>
```

### 配置xml

1. `web.xml`

   ```xml
   <web-app  xmlns="http://java.sun.com/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
           http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
           version="2.5">
     <display-name>Your Web Application</display-name>
     
       <servlet>
       <servlet-name>DispatcherServlet</servlet-name>
       <servlet-class>pri.wx.mvc.web.servlet.DispatcherServlet</servlet-class>
     </servlet>
   
     <servlet-mapping>
       <servlet-name>DispatcherServlet</servlet-name>
       <url-pattern>*.do</url-pattern>
     </servlet-mapping>
   
   </web-app>
   ```

   拦截后缀为`.do`的请求（仅作为示范）

   这里的“web-app”版本是**2.5**的，否则jsp页面el表达式无效。

   

2. `xxx-config.xml`

   ```xml
   <beans>
   
       <bean id="URLHandlerMapping" class="pri.wx.mvc.web.servlet.handler.URLHandlerMapping"/>
       <bean id="ControllerAdapter" class="pri.wx.mvc.web.servlet.mvc.ControllerHandlerAdapter"/>
       <bean id="HttpRequestAdapter" class="pri.wx.mvc.web.servlet.mvc.HttpRequestHandlerAdapter"/>
   
       <bean id="hello.do" class="test.HelloController"/>
       <bean id="404" class="pri.wx.mvc.web.servlet.mvc.HandlerFor404"/>
   
       <bean id="ViewResolver" class="pri.wx.mvc.web.servlet.mvc.DefaultViewResolver">
           <property name="viewClass" value="pri.wx.mvc.web.servlet.mvc.InternalResourceView"/>
           <property name="predix" value="/WEB-INF/"/>
           <property name="viewClass" value=".jsp"/>
       </bean>
   
   </beans>
   ```

   这里各个`<bean>`的顺序，按原作者的操作是无影响的，实现后发现**相关类**注入操作中，读到第一个有带`<property>`属性`<bean>`时就不再继续注入了。所以这里我将唯一一个带有属性的`<bean>`放到最后。

   上面的`HandlerMapping` 、`HandlerAdapter` 类在自定义mvc包中实现。

   `Controller`类则由自己实现。

### 实现Controller接口

```java
public class HelloController implements Controller {
    //private static final Logger LOGGER = Logger.getLogger(HelloController.class);

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String now = (new Date()).toString();
        
        String view = "hello";//要返回jsp文件的名字，这里返回hello.jsp
        
        //LOGGER.info("Returning hello view with " + now);
	   	
        return new ModelAndView(, "now", now);
    }
}
```

实现`handleRequest()`方法，返回 “视图”。



### 扩展

自定义实现`HandlerMapping`、`HandlerAdapter`接口。
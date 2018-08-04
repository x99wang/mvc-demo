package pri.wx.mvc.web.servlet.handler;

import org.apache.log4j.Logger;
import pri.wx.mvc.web.context.WebApplicationContext;
import pri.wx.mvc.web.context.WebApplicationContextAware;
import pri.wx.mvc.web.servlet.HandlerMapping;
import pri.wx.mvc.web.servlet.mvc.Controller;
import pri.wx.mvc.web.servlet.mvc.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class URLHandlerMapping implements HandlerMapping, WebApplicationContextAware {
    private static final Logger LOGGER = Logger.getLogger(URLHandlerMapping.class);
    private WebApplicationContext wac;
    private Map<String, Object> handlerMap = new ConcurrentHashMap<String, Object>();
    private AtomicBoolean initialize = new AtomicBoolean(false);

    public void setWebApplicationContext(WebApplicationContext wac) {
        this.wac = wac;
    }

    /**
     * 从beans中查找HttpRequestHandler,Controller
     * 并返回响应的Handler
     * @param request 网络请求
     * @return 映射结果
     * @throws Exception
     */
    public Object getHandler(HttpServletRequest request) throws Exception {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Find handler for request " + request.getServletPath());
        }

        if(initialize.compareAndSet(false, true)){
            Map<String, HttpRequestHandler> map1 = wac.beansOfType(HttpRequestHandler.class);
            for(String key : map1.keySet()){
                handlerMap.put(key, map1.get(key));
            }
            Map<String, Controller> map2 = wac.beansOfType(Controller.class);
            for(String key : map2.keySet()){
                handlerMap.put(key, map2.get(key));
            }
        }
        Object handler = handlerMap.get(getHandlerName(request));
        if(handler == null){
            handler = handlerMap.get("404");
        }
        return handler;
    }

    /**
     * 获得URL映射的name
     * @param request
     * @return
     */
    protected String getHandlerName(HttpServletRequest request){
        String path = request.getServletPath();
        int index = path.lastIndexOf('/');
        String handleName = path.substring(index + 1, path.length());
        return handleName;
    }
}

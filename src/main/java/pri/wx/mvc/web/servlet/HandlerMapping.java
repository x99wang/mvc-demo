package pri.wx.mvc.web.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理器映射，他主要包含的是控制器的列表，对于特定的请求，根据HandlerMapping的映射关系，可以找到特定的控制器。最简单的便是url到控制器的映射。
 */
public interface HandlerMapping {
    String PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE = HandlerMapping.class.getName() + ".pathWithinHandlerMapping";

    Object getHandler(HttpServletRequest request) throws Exception;
}

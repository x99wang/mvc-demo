package pri.wx.mvc.web.servlet;

/**
 * 将View名称解析成View对象
 */
public interface ViewResolver {
    View resolveViewName(String viewName) throws Exception;
}

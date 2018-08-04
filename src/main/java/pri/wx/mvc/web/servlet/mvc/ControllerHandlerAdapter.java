package pri.wx.mvc.web.servlet.mvc;

import pri.wx.mvc.web.servlet.HandlerAdapter;
import pri.wx.mvc.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerHandlerAdapter implements HandlerAdapter {

    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return ((Controller) handler).handleRequest(request,response);
    }

    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1L;
    }
}

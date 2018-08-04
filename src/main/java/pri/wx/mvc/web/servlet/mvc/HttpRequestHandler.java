package pri.wx.mvc.web.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpRequestHandler {
    void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

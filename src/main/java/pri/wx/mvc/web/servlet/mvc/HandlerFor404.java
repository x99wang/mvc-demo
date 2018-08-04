package pri.wx.mvc.web.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerFor404 implements HttpRequestHandler {

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect("404.html");
    }
}

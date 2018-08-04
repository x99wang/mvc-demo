package pri.wx.mvc.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 定义response显示的详细内容
 */
public interface View {
    String getContentType();

    void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}

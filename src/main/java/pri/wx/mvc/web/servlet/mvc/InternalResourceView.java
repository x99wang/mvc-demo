package pri.wx.mvc.web.servlet.mvc;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class InternalResourceView extends AbstractView {

    private static final Logger LOGGER = Logger.getLogger(InternalResourceView.class);
    private String url;

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Rendering view with name '" + this + "' with model " + model);
        }

        if(model != null){
            exposeModelAsRequestAttributes(model, request);
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        if (rd == null) {
            throw new ServletException("Could not get RequestDispatcher for ["
                    + getUrl() + "]: check that this file exists within your WAR");
        }

        if (useInclude(request, response)) {
            response.setContentType(getContentType());
            rd.include(request, response);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Included resource [" + getUrl() + "] in InternalResourceView '" + url + "'");
            }
        }else {
            exposeForwardRequestAttributes(request);
            rd.forward(request, response);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Forwarded to resource [" + getUrl() + "] in InternalResourceView '" + url + "'");
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return this.url;
    }
}

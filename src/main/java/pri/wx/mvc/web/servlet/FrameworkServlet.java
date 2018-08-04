package pri.wx.mvc.web.servlet;

import org.apache.log4j.Logger;
import pri.wx.mvc.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrameworkServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(FrameworkServlet.class);
    private WebApplicationContext webApplicationContext;

    @Override
    public void init() throws ServletException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("----------Initializing servlet '" + getServletName() + "'----------");
        }

        this.webApplicationContext = initWebApplicationContext();

        initServletBean();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("----------Servlet '" + getServletName() + "' configured successfully----------/n/n");
        }

    }

    private WebApplicationContext initWebApplicationContext() {
        WebApplicationContext wac = new WebApplicationContext(getServletContext());
        wac.init();
        onRefresh(wac);
        return wac;
    }

    protected void onRefresh(WebApplicationContext context) {
        // For subclasses: do nothing by default.
    }

    protected void initServletBean(){

    }

    protected abstract void doService(HttpServletRequest request, HttpServletResponse response) throws Exception;


    protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        Throwable failureCause = null;

        try {
            doService(request, response);
        } catch (ServletException ex) {
            failureCause = ex;
            throw ex;
        } catch (IOException ex) {
            failureCause = ex;
            throw ex;
        } catch (Throwable ex) {
            failureCause = ex;
            //throw new NestedServletException("Request processing failed", ex);
        } finally {
            if (failureCause != null) {
                LOGGER.error("Could not complete request", failureCause);
            } else {
                long processingTime = System.currentTimeMillis() - startTime;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.info("Successfully completed request, cost " + processingTime + " ms/n");
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        if(LOGGER.isDebugEnabled()){
            LOGGER.info("Servlet destory");
        }
        super.destroy();
    }

    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }



}

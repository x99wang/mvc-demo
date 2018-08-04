package pri.wx.mvc.web.servlet.mvc;

import org.apache.log4j.Logger;
import pri.wx.mvc.web.servlet.View;
import pri.wx.mvc.web.servlet.ViewResolver;

public class DefaultViewResolver implements ViewResolver {

    private static final Logger LOGGER = Logger.getLogger(DefaultViewResolver.class);
    private String prefix = "";
    private String suffix = "";
    private Class<View> viewClass;

    public View resolveViewName(String viewName) throws Exception {
        View view = viewClass.newInstance();
        if(view instanceof InternalResourceView){
            ((InternalResourceView)view).setUrl(prefix + viewName + suffix);
        }
        return view;
    }

    public void setViewClass(String viewClass){
        try {
            this.viewClass = (Class<View>) this.getClass().getClassLoader().loadClass(viewClass);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can't load view class " + viewClass, e);
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}

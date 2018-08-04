package pri.wx.mvc.web.servlet;

import java.util.HashMap;
import java.util.Map;

/**
 * 包含数据和视图的信息
 */
public class ModelAndView {

    private Object view;
    private Map<String, Object> model;
    private boolean cleared;

    public ModelAndView() {
    }

    public ModelAndView(String viewName) {
        this.view = viewName;
    }

    public ModelAndView(View view) {
        this.view = view;
    }

    public ModelAndView(String viewName, Map<String, Object> model) {
        this.view = viewName;
        if (model != null) {
            addAllObjects(model);
        }
    }

    public ModelAndView(View view, Map<String, Object> model) {
        this.view = view;
        if (model != null) {
            addAllObjects(model);
        }
    }

    public ModelAndView(String viewName, String modelName, Object modelObject) {
        this.view = viewName;
        addObject(modelName, modelObject);
    }

    public ModelAndView(View view, String modelName, Object modelObject) {
        this.view = view;
        addObject(modelName, modelObject);
    }

    public void setViewName(String viewName) {
        this.view = viewName;
    }

    public String getViewName() {
        return (this.view instanceof String ? (String) this.view : null);
    }

    public void setView(View view) {
        this.view = view;
    }


    public View getView() {
        return (this.view instanceof View ? (View) this.view : null);
    }

    public boolean hasView() {
        return (this.view != null);
    }

    public boolean isReference() {
        return (this.view instanceof String);
    }

    protected Map<String, Object> getModelInternal() {
        return this.model;
    }

    public Map<String, Object> getModelMap() {
        if (this.model == null) {
            this.model = new HashMap<String, Object>();
        }
        return this.model;
    }

    public Map<String, Object> getModel() {
        return getModelMap();
    }


    public ModelAndView addObject(String attributeName, Object attributeValue) {
        getModelMap().put(attributeName, attributeValue);
        return this;
    }


    public ModelAndView addObject(Object attributeValue) {
        getModelMap().put(attributeValue.toString(), attributeValue);
        return this;
    }


    public ModelAndView addAllObjects(Map<String, Object> modelMap) {
        getModelMap().putAll(modelMap);
        return this;
    }

    public void clear() {
        this.view = null;
        this.model = null;
        this.cleared = true;
    }

    public boolean isEmpty() {
        return (this.view == null && this.model == null);
    }

    public boolean wasCleared() {
        return (this.cleared && isEmpty());
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("ModelAndView: ");
        if (isReference()) {
            buf.append("reference to view with name '").append(this.view).append("'");
        } else {
            buf.append("materialized View is [").append(this.view).append(']');
        }
        buf.append("; model is ").append(this.model);
        return buf.toString();
    }
}

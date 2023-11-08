package fr.efrei.teachfinder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;

public class RequestWrapper extends HttpServletRequestWrapper {

    private final HashMap<String, String> params = new HashMap<>();

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getParameter(String name) {
         if (params.get(name) != null ) {
            return params.get(name);
        }

        HttpServletRequest req = (HttpServletRequest) super.getRequest();
        return req.getParameter(name);
    }

    public void setParameter(String name, String value) {
        params.put(name, value);
    }
}
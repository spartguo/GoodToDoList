package dong.GW.list.Controller.Global;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenManager tokenManager;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader("token");

        if(url.toLowerCase().indexOf("login")>=0){
            return true;
        }

        if(token != null && tokenManager.checkToken(token)){
            return true;
        }

        PrintWriter printWriter = httpServletResponse.getWriter();
        String response = JSON.toJSONString(new Response().failure("Need to login!!!"));
        printWriter.append(response);
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

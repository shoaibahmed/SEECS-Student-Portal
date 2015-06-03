/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"/servlets/*"})
public class LoginCheckFilter implements Filter {
    
    public LoginCheckFilter() {
        
    }    

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
    {
        try
        {
            HttpServletRequest httpRequest=(HttpServletRequest) request;
            
            HttpSession session=httpRequest.getSession(false);
            
            //If user is login (loginstatus will be null if the user is requesting the page first time)
            if(session!=null)
            {
                chain.doFilter(request, response);
            }
            else
            {
                RequestDispatcher requestD=request.getRequestDispatcher("/servlets/LoginServlet");
                requestD.forward(request, response);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Initializing Login Filter");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Login Filter");
    }
}

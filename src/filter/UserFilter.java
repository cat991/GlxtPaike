package filter;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @Auther：BlackCat
 * @Data： 2021/4/24 - 22:38
 * @Descripton: servlet
 */
@WebFilter({"/html/manage/*","/html/user/*"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession(true);
        resp.setContentType("text/html;");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String name = (String)  session.getAttribute("userName");

        if (name != null){

            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            out.print("<script type='text/javascript'>");
            out.print("alert('非法访问，请先登录');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");

        }
    }

    @Override
    public void destroy() {

    }
}

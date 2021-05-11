package servlet;


import com.google.gson.Gson;
import entity.Glxtuser;
import impl.GlxtUserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userSer")
public class userServlet extends BaseServlet {
    GlxtUserDaoImpl userImpl = new GlxtUserDaoImpl();

    public void upUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("userId")).intValue();
        String passwordJiu = req.getParameter("passwordJiu");
        String password = req.getParameter("password");
        String userName = req.getParameter("userName");
        String userType = req.getParameter("userType");
        Glxtuser user = new Glxtuser(id,userName,password,userType,"1");
        Glxtuser uu = userImpl.getByUserId(id);
        if (uu.getUserPwd().equals(passwordJiu)){
            if(userImpl.upUser(user) == 1){
                //成功     重定向到首页
                resp.sendRedirect("./html/user/user.jsp");
            }
        } else {
            //错误
            // 把错误信息保存到Request域中
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("msg", "密码错误！");
            req.getRequestDispatcher("./html/user/user.jsp").forward(req, resp);
        }

    }

    /**
     * 封号操作和修改用户类型为管理员操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void manageDele(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userStId = Integer.valueOf(req.getParameter("userStID")).intValue();
        int id = Integer.valueOf(req.getParameter("id")).intValue();
        userImpl.manageDe(userStId,id);
        manageSelect(req,resp);
    }

    /**
     * 查询所有用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void manageSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Glxtuser> userLists = userImpl.manageSelect();
        req.getSession().setAttribute("userLists", userLists);
        req.getRequestDispatcher("./html/manage/user.jsp").forward(req, resp);
    }

    /**
     * 处理注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void regs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        String userName = req.getParameter("userId");
        String userPwd = req.getParameter("password");

        //2、
        Glxtuser easybuyUser = new Glxtuser(null,userName,userPwd,"0","1");

        if (userImpl.getByUserName(userName) != 0) {//说明该用户不存在可以注册
            //3、保存到数据库
            if (userImpl.registUser(easybuyUser) == 1) {
                // 注册 成功
                //跳到成功页面reg-result.jsp
                req.getRequestDispatcher("./html/reg-result.jsp").forward(req, resp);
            } else {
                // 把错误信息保存到Request域中
                req.setAttribute("msg", "带（*）为必填项，不可为空！");
                req.getRequestDispatcher("./html/register.jsp").forward(req, resp);
            }
        } else {
            // 把错误信息保存到Request域中
            req.setAttribute("msg", "sorry!这个用户名太火爆了已经被抢先注册了呢，换一个试试吧！");
            req.getRequestDispatcher("./html/register.jsp").forward(req, resp);
        }
    }

    /**
     * 注销登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、销毁Session中用户登录的信息（或者销毁Session）

        HttpSession session = req.getSession();
        session.invalidate();
//        2、重定向到首页（或登录页面）。
        resp.sendRedirect("./html/login.jsp");
    }

    /**
     * 处理登录业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取前台的值
        String userName = req.getParameter("userId");
        String userPwd = req.getParameter("password");
        String userCode = req.getParameter("code");
        //验证码比较
        HttpSession session = req.getSession();
        String scode = (String) session.getAttribute("code");

        // 调用 userService.login()登录处理业务
        Glxtuser loginUser = userImpl.userlogin(userName,userPwd);
        if (scode.equals(userCode)) {
            //判断用户有没有注册
            if (userImpl.getByUserName(userName) == 0) {//说明该用户存在
                if (loginUser == null) {
                    // 把错误信息，和回显的表单项信息，保存到Request域中
                    req.setAttribute("msg", "密码错误！");
                    req.setAttribute("returnName", userName);
                    req.getRequestDispatcher("./html/login.jsp").forward(req, resp);
                } else {
                    String userStatus = loginUser.getUserStatus();
                    if (userStatus.equals("1")) {
                        req.getSession().setAttribute("userId",loginUser.getUserId());//把用户保存到session说明用户已经登录
                        req.getSession().setAttribute("userName", loginUser.getUserName());//把用户保存到session说明用户已经登录
                        req.getSession().setAttribute("userType", loginUser.getUserType());//把用户保存到session说明用户已经登录
                        req.getSession().setAttribute("userStatus", loginUser.getUserStatus());//把用户保存到session说明用户已经登录
                        // 登录 成功
                        //跳到首页
                        resp.sendRedirect("./html/index.jsp");
                        //req.getRequestDispatcher("/html/user/index.jsp").forward(req, resp);
                    } else if (userStatus.equals("0")) {
                        // 把错误信息，和回显的表单项信息，保存到Request域中
                        req.setAttribute("msg", "该用户因违反平台管理已被管理员永久封号，感谢您曾经的信赖与支持再见！");
                        req.setAttribute("returnName", userName);
                        req.getRequestDispatcher("./html/login.jsp").forward(req, resp);
                    }
                }
            } else {
                // 把错误信息，和回显的表单项信息，保存到Request域中
                req.setAttribute("msg", "用户名不存在！");
                req.setAttribute("returnName", userName);
                req.getRequestDispatcher("./html/login.jsp").forward(req, resp);
            }
        } else {
            // 把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("returnName", userName);
            req.getRequestDispatcher("./html/login.jsp").forward(req, resp);
        }

    }

    /**
     * 查询用户名是否已被使用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String json = new String();
        boolean existsUsername = true;
        // 把返回的结果封装成为map对象
        Map<String, Object> resultMap = new HashMap<>();
        // 获取请求的参数username
        String userName = req.getParameter("userName");
        // existsUsername();
        if (userImpl.getByUserName(userName) == 0) {//说明用户存在
            existsUsername = false;
        }
        resultMap.put("existsUsername", existsUsername);
        json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}

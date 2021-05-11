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
                //�ɹ�     �ض�����ҳ
                resp.sendRedirect("./html/user/user.jsp");
            }
        } else {
            //����
            // �Ѵ�����Ϣ���浽Request����
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("msg", "�������");
            req.getRequestDispatcher("./html/user/user.jsp").forward(req, resp);
        }

    }

    /**
     * ��Ų������޸��û�����Ϊ����Ա����
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
     * ��ѯ�����û�
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
     * ����ע��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void regs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1����ȡ����Ĳ���
        String userName = req.getParameter("userId");
        String userPwd = req.getParameter("password");

        //2��
        Glxtuser easybuyUser = new Glxtuser(null,userName,userPwd,"0","1");

        if (userImpl.getByUserName(userName) != 0) {//˵�����û������ڿ���ע��
            //3�����浽���ݿ�
            if (userImpl.registUser(easybuyUser) == 1) {
                // ע�� �ɹ�
                //�����ɹ�ҳ��reg-result.jsp
                req.getRequestDispatcher("./html/reg-result.jsp").forward(req, resp);
            } else {
                // �Ѵ�����Ϣ���浽Request����
                req.setAttribute("msg", "����*��Ϊ���������Ϊ�գ�");
                req.getRequestDispatcher("./html/register.jsp").forward(req, resp);
            }
        } else {
            // �Ѵ�����Ϣ���浽Request����
            req.setAttribute("msg", "sorry!����û���̫�����Ѿ�������ע�����أ���һ�����԰ɣ�");
            req.getRequestDispatcher("./html/register.jsp").forward(req, resp);
        }
    }

    /**
     * ע����¼
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1������Session���û���¼����Ϣ����������Session��

        HttpSession session = req.getSession();
        session.invalidate();
//        2���ض�����ҳ�����¼ҳ�棩��
        resp.sendRedirect("./html/login.jsp");
    }

    /**
     * �����¼ҵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // ��ȡǰ̨��ֵ
        String userName = req.getParameter("userId");
        String userPwd = req.getParameter("password");
        String userCode = req.getParameter("code");
        //��֤��Ƚ�
        HttpSession session = req.getSession();
        String scode = (String) session.getAttribute("code");

        // ���� userService.login()��¼����ҵ��
        Glxtuser loginUser = userImpl.userlogin(userName,userPwd);
        if (scode.equals(userCode)) {
            //�ж��û���û��ע��
            if (userImpl.getByUserName(userName) == 0) {//˵�����û�����
                if (loginUser == null) {
                    // �Ѵ�����Ϣ���ͻ��Եı�����Ϣ�����浽Request����
                    req.setAttribute("msg", "�������");
                    req.setAttribute("returnName", userName);
                    req.getRequestDispatcher("./html/login.jsp").forward(req, resp);
                } else {
                    String userStatus = loginUser.getUserStatus();
                    if (userStatus.equals("1")) {
                        req.getSession().setAttribute("userId",loginUser.getUserId());//���û����浽session˵���û��Ѿ���¼
                        req.getSession().setAttribute("userName", loginUser.getUserName());//���û����浽session˵���û��Ѿ���¼
                        req.getSession().setAttribute("userType", loginUser.getUserType());//���û����浽session˵���û��Ѿ���¼
                        req.getSession().setAttribute("userStatus", loginUser.getUserStatus());//���û����浽session˵���û��Ѿ���¼
                        // ��¼ �ɹ�
                        //������ҳ
                        resp.sendRedirect("./html/index.jsp");
                        //req.getRequestDispatcher("/html/user/index.jsp").forward(req, resp);
                    } else if (userStatus.equals("0")) {
                        // �Ѵ�����Ϣ���ͻ��Եı�����Ϣ�����浽Request����
                        req.setAttribute("msg", "���û���Υ��ƽ̨�����ѱ�����Ա���÷�ţ���л��������������֧���ټ���");
                        req.setAttribute("returnName", userName);
                        req.getRequestDispatcher("./html/login.jsp").forward(req, resp);
                    }
                }
            } else {
                // �Ѵ�����Ϣ���ͻ��Եı�����Ϣ�����浽Request����
                req.setAttribute("msg", "�û��������ڣ�");
                req.setAttribute("returnName", userName);
                req.getRequestDispatcher("./html/login.jsp").forward(req, resp);
            }
        } else {
            // �Ѵ�����Ϣ���ͻ��Եı�����Ϣ�����浽Request����
            req.setAttribute("msg", "��֤�����");
            req.setAttribute("returnName", userName);
            req.getRequestDispatcher("./html/login.jsp").forward(req, resp);
        }

    }

    /**
     * ��ѯ�û����Ƿ��ѱ�ʹ��
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
        // �ѷ��صĽ����װ��Ϊmap����
        Map<String, Object> resultMap = new HashMap<>();
        // ��ȡ����Ĳ���username
        String userName = req.getParameter("userName");
        // existsUsername();
        if (userImpl.getByUserName(userName) == 0) {//˵���û�����
            existsUsername = false;
        }
        resultMap.put("existsUsername", existsUsername);
        json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}

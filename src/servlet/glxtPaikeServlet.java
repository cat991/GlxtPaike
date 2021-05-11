package servlet;


import entity.Glxtclass;
import entity.Glxtlab;
import entity.Glxtpaike;
import impl.GlxtPaikeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * @Auther��BlackCat
 * @Data�� 2021/4/25 - 0:55
 * @Descripton: servlet
 */
@WebServlet("/paike")
public class glxtPaikeServlet extends BaseServlet {
    GlxtPaikeDaoImpl glxtPaikeDao = new GlxtPaikeDaoImpl();

    @Override
    public void init() throws ServletException {

    }

    //ɾ���α�
    protected void delGlxtPaike(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        int cont = glxtPaikeDao.delGlxtPaike(id);
        PrintWriter out = resp.getWriter();
        if (cont == 1) {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('�ɹ�ɾ���α�');");
            out.print("    window.location.href='./paike?action=getPaikeList';");
            out.print("</script>");
        } else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('ɾ��ʧ�ܻ���ɾ��');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }

    }


    //�޸��ſ���Ϣ �����޸Ĺ���
    protected void upPaike(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer paikeId = Integer.valueOf(req.getParameter("paikeId"));
        String paikeWeeks = req.getParameter("paikeWeeks");
        Integer jieshu = Integer.valueOf(req.getParameter("jieshu"));
        Integer weeDay = Integer.valueOf(req.getParameter("WeeDay"));
        String paikeCourseName = req.getParameter("paikeCourseName");
        String paikeTeacher = req.getParameter("paikeTeacher");
        Integer paikeLabId = Integer.valueOf(req.getParameter("paikeLabId"));
        Integer paikeClassId = Integer.valueOf(req.getParameter("paikeClassId"));
        Glxtpaike glxtpaike = new Glxtpaike(paikeId, paikeWeeks, jieshu, weeDay, paikeCourseName, paikeTeacher, paikeLabId, paikeClassId);
        int cont = glxtPaikeDao.upGlxtPaike(glxtpaike);
        PrintWriter out = resp.getWriter();
        if (cont == 1) {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('�޸ĳɹ�');");
            out.print("javascript:history.back(0);");
            out.print("</script>");
        } else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('�޸�ʧ�ܻ����޸�');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }
    }


    //���ص����α���Ϣ���޸Ŀα���
    protected void getPaikeById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Glxtpaike glxtpaike = null;
        List<Glxtclass> alist = glxtPaikeDao.GlxtpaikeClass();
        req.getSession().setAttribute("paikeClass", alist);
        List<Glxtlab> aalist = glxtPaikeDao.GlxtpaikeLab();
        req.getSession().setAttribute("paikelab", aalist);
        glxtpaike = glxtPaikeDao.getListById(id);
        PrintWriter out = resp.getWriter();
        if (glxtpaike != null) {
            req.getSession().setAttribute("onepaike", glxtpaike);
            req.getRequestDispatcher("./html/paike/uppaike.jsp").forward(req, resp);
        } else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('�޸ÿα���Ϣ');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }


    }





    //����Ա��ȡ���пα�,����α���
    protected void getPaikeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userType = String.valueOf(req.getSession().getAttribute("userType"));
        PrintWriter out = resp.getWriter();
        List<Glxtpaike> alist = null;
        List<Glxtclass> glxtclasses = glxtPaikeDao.GlxtpaikeClass();
        req.getSession().setAttribute("paikeClass", glxtclasses);
        List<Glxtlab> glxtlabs = glxtPaikeDao.GlxtpaikeLab();
        req.getSession().setAttribute("paikelab", glxtlabs);

        if (userType.equals("1")) {
            alist = glxtPaikeDao.getPaikeList();
            req.getSession().setAttribute("getPaikeList", alist);
            req.getRequestDispatcher("./html/paike/allpaike.jsp").forward(req, resp);
        } else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('����ķ��ʲ���');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }
    }


    //��ȡ�α���ѯ�α���
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String roomVal = req.getParameter("roomVal");
        List<Glxtpaike> alist = null;
        PrintWriter out = resp.getWriter();
        if (id.equals("0")) {

            alist = glxtPaikeDao.GlxtpaikeClass(roomVal);

            if (alist.size() == 0) {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('���޿α���Ϣ');");
                out.print("javascript:history.back(-1);");
                out.print("</script>");
            } else {
                req.getSession().setAttribute("paiKeClassList", alist);
                req.getRequestDispatcher("./html/ClassSchedule.jsp").forward(req, resp);
            }
        } else if (id.equals("1")) {

            alist = glxtPaikeDao.GlxtpaikeLab(roomVal);
            if (alist.size() == 0) {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('���޿α���Ϣ');");
                out.print("javascript:history.back(-1);");
                out.print("</script>");
            } else {
                req.getSession().setAttribute("paikeLabList", alist);
                req.getRequestDispatcher("./html/RoomSchedule.jsp").forward(req, resp);
            }
        } else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('����Ĳ���');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }


    }

    //��ӿα���
    protected void addPaike(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paikeWeeks = req.getParameter("paikeWeeks");
        Integer jieshu = Integer.valueOf(req.getParameter("jieshu"));
        Integer weeDay = Integer.valueOf(req.getParameter("WeeDay"));
        String paikeCourseName = req.getParameter("paikeCourseName");
        String paikeTeacher = req.getParameter("paikeTeacher");
        Integer paikeLabId = Integer.valueOf(req.getParameter("paikeLabId"));
        Integer paikeClassId = Integer.valueOf(req.getParameter("paikeClassId"));
        PrintWriter out = resp.getWriter();
        if (paikeClassId == 888 && paikeClassId == 888) {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('����ʧ��,��ѡ����Ч�İ༶�����');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        } else {
            Glxtpaike glxtpaike = new Glxtpaike(paikeWeeks, jieshu, weeDay, paikeCourseName, paikeTeacher, paikeLabId, paikeClassId);
            int cont = glxtPaikeDao.addGlxtPaike(glxtpaike);
            if (cont == 1) {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('�����ɹ�,�������');");
                out.print("javascript:history.back(0);");
                out.print("</script>");
            } else {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('����ʧ��');");
                out.print("javascript:history.back(-1);");
                out.print("</script>");
            }
        }
    }

    //��ȡ�༶�Ϳ����б�
    protected void AllSroom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setAttribute("aa","1");
        List<Glxtclass> alist = glxtPaikeDao.GlxtpaikeClass();
        req.getSession().setAttribute("paikeClass", alist);
        List<Glxtlab> aalist = glxtPaikeDao.GlxtpaikeLab();
        req.getSession().setAttribute("paikelab", aalist);
        req.getRequestDispatcher("./html/paike/newpaike.jsp").forward(req, resp);
    }


    //��ȡ�༶���߿����б�
    protected void SRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        PrintWriter out = resp.getWriter();
        if (id.equals("0")) {
            List<Glxtclass> alist = glxtPaikeDao.GlxtpaikeClass();
            req.getSession().setAttribute("paikeClass", alist);
            req.getRequestDispatcher("./html/ClassSchedule.jsp").forward(req, resp);
        } else if (id.equals("1")) {
            List<Glxtlab> alist = glxtPaikeDao.GlxtpaikeLab();
            req.getSession().setAttribute("paikelab", alist);
            req.getRequestDispatcher("./html/RoomSchedule.jsp").forward(req, resp);

        } else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('����Ĳ���');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }
    }

}

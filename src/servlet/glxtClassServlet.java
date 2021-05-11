package servlet;

import entity.Glxtclass;
import impl.GlxtClassDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/glxtClass")
public class glxtClassServlet extends BaseServlet {
    GlxtClassDaoImpl classImpl = new GlxtClassDaoImpl();

    //ɾ���α�
    protected void delClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        int cont = classImpl.delGlxtClass(id);
        PrintWriter out = resp.getWriter();
        if (cont == 1){
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('�ɹ�ɾ���༶');");
            out.print("    window.location.href='./glxtClass?action=getClassList';");
            out.print("</script>");
        }else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('ɾ��ʧ�ܻ���ɾ��');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }

    }

    //�޸İ༶��Ϣ����
    protected void upClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classId = Integer.valueOf(req.getParameter("classId"));
        String className = req.getParameter("className");
        String masterName = req.getParameter("masterName");
        int classNum = Integer.valueOf(req.getParameter("classNum"));
        Glxtclass glxtclass = new Glxtclass(classId,className, masterName,classNum);
        PrintWriter out = resp.getWriter();
        if ((masterName == null || masterName.equals("")) ||
                (className == null || className.equals("")) || classNum == 0){
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('�޸�ʧ��,�����κͰ༶���Ʋ���Ϊ������������Ϊ0��');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }else {
            int cont = classImpl.upGlxtClass(glxtclass);
            if (cont == 1) {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('�޸ĳɹ�');");
                out.print("window.location.href='./glxtClass?action=getClassList';");
                out.print("</script>");
            } else {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('�޸�ʧ�ܻ����޸�');");
                out.print("javascript:history.back(-1);");
                out.print("</script>");
            }
        }
    }

    //����id���ذ༶��Ϣ�������޸İ༶��Ϣ
    protected void getClassById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        PrintWriter out = resp.getWriter();
        Glxtclass glxtclass  = classImpl.getGlxtClassOne(id);
        if (glxtclass!=null){
            req.getSession().setAttribute("glxtclass",glxtclass);
            req.getRequestDispatcher("./html/glxtClass/uppclass.jsp").forward(req, resp);
        }else{
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('�޸ÿα���Ϣ');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }
    }

    //��ѯ���а༶��Ϣ
    protected void getClassList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        List<Glxtclass> alist = null;
        List<Glxtclass> glxtclas = classImpl.getGlxtClassList();
        req.getSession().setAttribute("getClassList", glxtclas);

        req.getRequestDispatcher("./html/glxtClass/allclass.jsp").forward(req, resp);
    }


    //�����༶��Ϣ
    protected void addClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classNum = Integer.valueOf(req.getParameter("classNum")).intValue();
        String masterName = req.getParameter("masterName");
        String classNames = req.getParameter("classNames");

        PrintWriter out = resp.getWriter();
        if ((masterName == null || masterName.equals("")) ||
                (classNames == null || classNames.equals("")) || classNum == 0){
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('����ʧ��,�����κͰ༶���Ʋ���Ϊ������������Ϊ0��');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }else {
            Glxtclass glxtclass = new Glxtclass(null, classNames, masterName, classNum);
            int cont = classImpl.addGlxtClass(glxtclass);
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
}

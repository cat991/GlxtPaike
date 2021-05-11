package servlet;

import entity.Glxtclass;
import entity.Glxtlab;
import impl.GlxtClassDaoImpl;
import impl.GlxtLabDaoImpl;
import jdk.nashorn.internal.ir.CallNode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * @Auther：BlackCat
 * @Data： 2021/4/27 - 14:06
 * @Descripton: servlet
 */


@WebServlet("/glxtLab")
public class glxtLabServlet extends BaseServlet {
    GlxtLabDaoImpl glxtLabDao = new GlxtLabDaoImpl();

    //删除机房
    protected void delLab(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        int cont = glxtLabDao.delGlxtLab(id);
        PrintWriter out = resp.getWriter();
        if (cont == 1){
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('成功删除机房');");
            out.print("    window.location.href='./glxtLab?action=getLabList';");
            out.print("</script>");
        }else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('删除失败或已删除');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }

    }

    //修改机房信息与功能
    protected void upLab(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer labId = Integer.valueOf(req.getParameter("labId"));
        String labName = req.getParameter("labName");
        String labType = req.getParameter("labType");
        String labDesc = req.getParameter("labDesc");
        Glxtlab glxtlab = new Glxtlab(labId,labName, labType,labDesc);
        PrintWriter out = resp.getWriter();
        if ((labName == null || labName.equals("")) ||
                (labType == null || labType.equals("")) ){
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('修改失败,机房信息不能为空');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }else {
            int cont = glxtLabDao.upGlxtLab(glxtlab);
            if (cont == 1) {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('修改成功');");
                out.print("window.location.href='./glxtLab?action=getLabList';");
                out.print("</script>");
            } else {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('修改失败或已修改');");
                out.print("javascript:history.back(-1);");
                out.print("</script>");
            }
        }
    }

    //根据id返回班级信息，修改机房信息
    protected void getLabById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        PrintWriter out = resp.getWriter();
        Glxtlab glxtlab  = glxtLabDao.getGlxtLabById(id);
        if (glxtlab!=null){
            req.getSession().setAttribute("glxtlab",glxtlab);
            req.getRequestDispatcher("./html/glxtLab/uplab.jsp").forward(req, resp);
        }else{
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('无该机房信息');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }
    }

    //查询所有班级信息
    protected void getLabList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Glxtlab> glxtlabs = glxtLabDao.getGlxtLabList();
        req.getSession().setAttribute("getLabList", glxtlabs);

        req.getRequestDispatcher("./html/glxtLab/alllab.jsp").forward(req, resp);
    }


    //新增班级信息
    protected void addLab(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String labName = req.getParameter("labName");
        String labType = req.getParameter("labType");
        String labDesc = req.getParameter("labDesc");
        PrintWriter out = resp.getWriter();
        if ((labName == null || labName.equals("")) ||
                (labType == null || labType.equals("")) ){
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('新增失败,机房信息不完全');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }else {
            Glxtlab glxtlab = new Glxtlab(labName, labType, labDesc);
            int cont = glxtLabDao.addGlxtLab(glxtlab);
            if (cont == 1) {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('新增成功,继续添加');");
                out.print("javascript:history.back(0);");
                out.print("</script>");
            } else {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('新增失败');");
                out.print("javascript:history.back(-1);");
                out.print("</script>");
            }
        }
    }
}

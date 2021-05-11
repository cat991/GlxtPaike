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

    //删除课表
    protected void delClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        int cont = classImpl.delGlxtClass(id);
        PrintWriter out = resp.getWriter();
        if (cont == 1){
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('成功删除班级');");
            out.print("    window.location.href='./glxtClass?action=getClassList';");
            out.print("</script>");
        }else {
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('删除失败或已删除');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }

    }

    //修改班级信息功能
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
            out.print("alert('修改失败,班主任和班级名称不能为空且人数不能为0！');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }else {
            int cont = classImpl.upGlxtClass(glxtclass);
            if (cont == 1) {
                req.getRequestURL();
                out.print("<script type='text/javascript'>");
                out.print("alert('修改成功');");
                out.print("window.location.href='./glxtClass?action=getClassList';");
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

    //根据id返回班级信息，用于修改班级信息
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
            out.print("alert('无该课表信息');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }
    }

    //查询所有班级信息
    protected void getClassList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        List<Glxtclass> alist = null;
        List<Glxtclass> glxtclas = classImpl.getGlxtClassList();
        req.getSession().setAttribute("getClassList", glxtclas);

        req.getRequestDispatcher("./html/glxtClass/allclass.jsp").forward(req, resp);
    }


    //新增班级信息
    protected void addClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classNum = Integer.valueOf(req.getParameter("classNum")).intValue();
        String masterName = req.getParameter("masterName");
        String classNames = req.getParameter("classNames");

        PrintWriter out = resp.getWriter();
        if ((masterName == null || masterName.equals("")) ||
                (classNames == null || classNames.equals("")) || classNum == 0){
            req.getRequestURL();
            out.print("<script type='text/javascript'>");
            out.print("alert('新增失败,班主任和班级名称不能为空且人数不能为0！');");
            out.print("javascript:history.back(-1);");
            out.print("</script>");
        }else {
            Glxtclass glxtclass = new Glxtclass(null, classNames, masterName, classNum);
            int cont = classImpl.addGlxtClass(glxtclass);
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

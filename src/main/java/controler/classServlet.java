package controler;


import dao.ClassDAO;
import model.Class;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "controler.classServlet", value = "/class")
public class classServlet extends HttpServlet {
    private ClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        classDAO = new ClassDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = "/" + request.getParameter("action");
//        System.out.println(action);
        String action = "/" + request.getParameter("action");
//        String action = request.getServletPath();
//        String action = "/"+request.getServletPath().split("/")[2];

        System.out.println(action);
        try {
            switch (action){
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCourse(request, response);
                    break;
                case "/delete":
                    deleteCourse(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCourse(request, response);
                    break;
                default:
                    listCourse(request, response);
                    break;
            }
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,SQLException{
        Set<Class> classes = classDAO.getAllCourse();
        request.setAttribute("classes", classes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/class/course-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/class/course-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Class existingClass = classDAO.getCourse(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/class/course-form.jsp");
        request.setAttribute("aClass", existingClass);
        dispatcher.forward(request, response);
    }

    private void insertCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String startMonth = request.getParameter("startMonth");
        String endMonth = request.getParameter("endMonth");
        Class newClass = new Class(name, startMonth, endMonth);
        classDAO.saveCourse(newClass);
        response.sendRedirect("class?action=list");
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
//        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String name = request.getParameter("name");
        String startMonth = request.getParameter("startMonth");
        String endMonth = request.getParameter("endMonth");
        Class aClass = new Class(id, name, startMonth, endMonth);


        classDAO.updateCourse(aClass);
        response.sendRedirect("class?action=list");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        classDAO.deleteCourse(id);
        response.sendRedirect("class?action=list");
    }
}

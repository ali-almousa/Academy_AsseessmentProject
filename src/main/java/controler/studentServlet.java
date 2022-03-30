package controler;

import dao.ClassDAO;
import dao.StudentDAO;
import model.Student;
import model.Class;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "controler.studentServlet", urlPatterns = "/student")
public class studentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                    insertStudent(request, response);
                    break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateStudent(request, response);
                    break;
                case "/report":
                    showReport(request, response);
                    break;
                case "/assignForm":
                    showAssignCourseForm(request, response);
                    break;
                case "/assign":
                    assignCourse(request, response);
                    break;
                default:
                    listStudent(request, response);
                    break;
            }
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void showAssignCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,SQLException{
        ClassDAO classDAO = new ClassDAO();
        Set<Class> classes = classDAO.getAllCourse();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("classes", classes);
        request.setAttribute("studentId", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/assign-form.jsp");
        dispatcher.forward(request, response);
    }

    private void assignCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classId = Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        studentDAO.assignStudent(classId, studentId);
        response.sendRedirect("student?action=list");
    }

    private void showReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,SQLException{
        ClassDAO classDAO = new ClassDAO();
        Set<Class> classes = classDAO.getAllCourse();
        request.setAttribute("classes", classes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/report-list.jsp");
        dispatcher.forward(request, response);
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,SQLException{
        List<Student> students = studentDAO.getAllStudent();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/student-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id is: " + id);
        Student existingStudent = studentDAO.getStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/student-form.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
//        Integer courseId = Integer.parseInt(request.getParameter("course"));
        Student newStudent = new Student(firstName, lastName, email);
//        courseDAO.getCourse(courseId).getStudents().add(newStudent);
        studentDAO.saveStudent(newStudent);
        response.sendRedirect("student?action=list");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
//        Integer courseId = Integer.parseInt(request.getParameter("course"));
        Student student = new Student(firstName, lastName, email);


        studentDAO.updateStudent(student, id);
        System.out.println("updated");
        response.sendRedirect("student?action=list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id: " + id);
        studentDAO.deleteStudent(id);
        response.sendRedirect("student?action=list");
    }
}

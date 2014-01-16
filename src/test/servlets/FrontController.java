package test.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.EmployeeDAO;
import test.dao.EmployeeTO;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Фронт контроллер для операций над сотрудниками
 * 
 * @author Vladimir_Smirnov
 * 
 */
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = -7402360269419087339L;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final EmployeeDAO EMPLOYEE_DAO = EmployeeDAO.getInstance();

    /**
     * Редактирование сотрудника {@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        EmployeeTO emp = MAPPER
                .readValue(request.getReader(), EmployeeTO.class);
        if (emp.getId() != null) {
            EMPLOYEE_DAO.update(emp);
        } else {
            EMPLOYEE_DAO.add(emp);
        }
        String json = MAPPER.writeValueAsString(emp.getId());
        response.getWriter().print(json);
    }

    /**
     * Получение списка сотрудников {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<EmployeeTO> list = EMPLOYEE_DAO.getAll();
        String json = MAPPER.writeValueAsString(list);
        response.getWriter().print(json);
    }

    /**
     * Удаление сотрудника {@inheritDoc}
     */
    @Override
    protected void doDelete(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Integer id = MAPPER.readValue(request.getReader(), Integer.class);
        EMPLOYEE_DAO.delete(id);
    }
}

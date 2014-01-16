package test.spring;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import test.dao.EmployeeDAO;
import test.dao.EmployeeTO;

/**
 * ������ ����������
 * 
 * @author Vladimir_Smirnov
 * 
 */
@Controller
public class SpringController {
    @Resource(name = "employeeDAO") // ���������, ��� ����� ����� ��� �� IoC
    private EmployeeDAO employeeDAO;

    /**
     * ���������� �� ��������� �������� spring.jsp (������ �� views.properties)
     * 
     * @return ���������� ��� ��������
     */
    @RequestMapping(value = "/index.spring")
    public String show() {
        String file = "spring";
        return file;
    }

    /**
     * ��������� ������ �����������
     * 
     * @return ������ ��������-��������
     */
    // ������, ����� ������ ������������ ���� �������
    @RequestMapping(value = "/json.spring", method = RequestMethod.GET)
    // ���������, ��� ������������ �������� �������������� return ����� ������
    // ��-��������� ������������ ������ ������������ view �� viewResolver
    @ResponseBody
    public List<EmployeeTO> getEmployees() {
        return employeeDAO.getAll();
    }

    /**
     * �������� ����������
     * 
     * @param id ���������� ����������
     */
    @RequestMapping(value = "/json.spring", method = RequestMethod.DELETE)
    @ResponseBody
    public void delEmployees(@RequestBody Integer id) {
        employeeDAO.delete(id);
    }

    /**
     * �������������� ���������� {@inheritDoc}
     * 
     * @param employeeTO ����� ������ � ���������� � ���� ��������-�������,
     *            ���������������� ��������� �� ���� id , ���� ��� ������ -
     *            ���������� ���������� ������ ����������
     */
    @RequestMapping(value = "/json.spring", method = RequestMethod.POST)
    @ResponseBody
    public Integer setEmployee(@RequestBody EmployeeTO employeeTO) {
        if (employeeTO.getId() != null) {
            employeeDAO.update(employeeTO);
        } else {
            employeeDAO.add(employeeTO);
        }
        return employeeTO.getId();
    }

}

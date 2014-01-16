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
 * —принг контроллер
 * 
 * @author Vladimir_Smirnov
 * 
 */
@Controller
public class SpringController {
    @Resource(name = "employeeDAO") // указываем, что нужно брать бин из IoC
    private EmployeeDAO employeeDAO;

    /**
     * –едиректит на статичную страницу spring.jsp (задано во views.properties)
     * 
     * @return логическое им€ страницы
     */
    @RequestMapping(value = "/index.spring")
    public String show() {
        String file = "spring";
        return file;
    }

    /**
     * ѕолучение списка сотрудников
     * 
     * @return список трансфер-объектов
     */
    // задаем, какой запрос обрабатывать этим методом
    @RequestMapping(value = "/json.spring", method = RequestMethod.GET)
    // указываем, что пользователю вернетс€ сериализованый return этого метода
    // по-умолчанию пользователю должен возвращатьс€ view от viewResolver
    @ResponseBody
    public List<EmployeeTO> getEmployees() {
        return employeeDAO.getAll();
    }

    /**
     * ”даление сотрудника
     * 
     * @param id удал€емого сотрудника
     */
    @RequestMapping(value = "/json.spring", method = RequestMethod.DELETE)
    @ResponseBody
    public void delEmployees(@RequestBody Integer id) {
        employeeDAO.delete(id);
    }

    /**
     * –едактирование сотрудника {@inheritDoc}
     * 
     * @param employeeTO новые данные о сотруднике в виде трансфер-объекта,
     *            идентифицируетс€ сотрудник по полю id , если оно пустое -
     *            происходит добавление нового сотрудника
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

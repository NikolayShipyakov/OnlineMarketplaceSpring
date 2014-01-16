package test.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Фабрика DAO
 * 
 * @author Vladimir_Smirnov
 * 
 */
@Configuration
public class EmployeeDAOSpringFactory {

    /**
     * Метод получения экземпляра объекта DAO (паттерн синглтон)
     * 
     * @return экземпляр класса EmployeeDAO
     */
    @Bean(name = "employeeDAO")
    public EmployeeDAO getIEmployeeDAO() {
        return EmployeeDAO.getInstance();
    }
}

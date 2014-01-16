package test.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ������� DAO
 * 
 * @author Vladimir_Smirnov
 * 
 */
@Configuration
public class EmployeeDAOSpringFactory {

    /**
     * ����� ��������� ���������� ������� DAO (������� ��������)
     * 
     * @return ��������� ������ EmployeeDAO
     */
    @Bean(name = "employeeDAO")
    public EmployeeDAO getIEmployeeDAO() {
        return EmployeeDAO.getInstance();
    }
}

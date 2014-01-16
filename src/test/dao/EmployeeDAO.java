package test.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ������ ������� � ���� �����������
 * 
 * @author Vladimir_Smirnov
 * 
 */
public final class EmployeeDAO {

    private static volatile EmployeeDAO instance;
    private final Map<Integer, EmployeeTO> epms = new HashMap<Integer, EmployeeTO>();
    // ���������� ���������������
    private int idSequence = 0;

    // �������� ���������� ������� �����������
    // ��������
    private EmployeeDAO() {
        add(new EmployeeTO("Miroshin", "Vasya", "Director"));
        add(new EmployeeTO("Lapshin", "Ilya", "Programmer"));
        add(new EmployeeTO("Alexey", "Kalinin", "Manager"));
    }

    /**
     * ����� ��������� ���������� ������� (������� ��������)
     * 
     * @return ��������� ������
     */
    public static EmployeeDAO getInstance() {
        // TODO thread-safe
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }

    /**
     * ��������� ������ �����������
     * 
     * @return ������ ��������-��������
     */
    public List<EmployeeTO> getAll() {
        List<EmployeeTO> list = null;
        list = new ArrayList<EmployeeTO>(epms.values());
        return list;
    }

    /**
     * ���������� ������ ����������
     * 
     * @param e ��������-������ ���������� (� ������ id)
     * @return id ������������ ����������
     */
    public Integer add(EmployeeTO e) {
        Integer id = idSequence++;
        e.setId(id);
        epms.put(id, e);
        return id;
    }

    /**
     * ��������� ����������
     * 
     * @param e ����� ������ � ���������� � ���� ��������-�������,
     *            ���������������� ��������� �� ���� id
     */
    public void update(EmployeeTO e) {
        Integer id = e.getId();
        epms.put(id, e);
    }

    /**
     * �������� ����������
     * 
     * @param id ���������� ����������
     */
    public void delete(Integer id) {
        epms.remove(id);
    }
}

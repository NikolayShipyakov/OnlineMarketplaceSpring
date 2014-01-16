package test.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Объект доступа к базе сотрудников
 * 
 * @author Vladimir_Smirnov
 * 
 */
public final class EmployeeDAO {

    private static volatile EmployeeDAO instance;
    private final Map<Integer, EmployeeTO> epms = new HashMap<Integer, EmployeeTO>();
    // индексатор идентификаторов
    private int idSequence = 0;

    // создание начального перечня сотрудников
    // синглтон
    private EmployeeDAO() {
        add(new EmployeeTO("Miroshin", "Vasya", "Director"));
        add(new EmployeeTO("Lapshin", "Ilya", "Programmer"));
        add(new EmployeeTO("Alexey", "Kalinin", "Manager"));
    }

    /**
     * Метод получения экземпляра объекта (паттерн синглтон)
     * 
     * @return экземпляр класса
     */
    public static EmployeeDAO getInstance() {
        // TODO thread-safe
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }

    /**
     * Получение списка сотрудников
     * 
     * @return список трансфер-объектов
     */
    public List<EmployeeTO> getAll() {
        List<EmployeeTO> list = null;
        list = new ArrayList<EmployeeTO>(epms.values());
        return list;
    }

    /**
     * Добавление нового сотрудника
     * 
     * @param e трансфер-объект сотрудника (с пустым id)
     * @return id добавленного сотрудника
     */
    public Integer add(EmployeeTO e) {
        Integer id = idSequence++;
        e.setId(id);
        epms.put(id, e);
        return id;
    }

    /**
     * Изменение сотрудника
     * 
     * @param e новые данные о сотруднике в виде трансфер-объекта,
     *            идентифицируется сотрудник по полю id
     */
    public void update(EmployeeTO e) {
        Integer id = e.getId();
        epms.put(id, e);
    }

    /**
     * Удаление сотрудника
     * 
     * @param id удаляемого сотрудника
     */
    public void delete(Integer id) {
        epms.remove(id);
    }
}

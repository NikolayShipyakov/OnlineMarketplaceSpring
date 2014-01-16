package test.dao;

/**
 * Трансфер объект сотрудника
 * 
 * @author Vladimir_Smirnov
 * 
 */
public class EmployeeTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Консруктор без параметров
     */
    public EmployeeTO() {
    }

    /**
     * Консруктор со всеми полями кроме id
     * 
     * @param firstName -фамилия
     * @param lastName - имя
     * @param email - почта
     */
    public EmployeeTO(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the genre to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}

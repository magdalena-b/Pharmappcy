package wt.muppety.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wt.muppety.authentication.LoginData;
import wt.muppety.model.Employee;

import javax.persistence.NoResultException;
import java.util.Optional;

public class EmployeeDao extends BaseDao<Employee> {

    public Optional<Employee> create(String firstname,
                                     String lastname,
                                     String email,
                                     Employee.Position position,
                                     String login,
                                     String password) {
        Employee employee = new Employee(firstname, lastname, email, position, login, password);
        try {
            this.save(employee);
            return findById(employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Employee> create(Employee employee) {
        try {
            this.save(employee);
            return findById(employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Employee> createClient(Employee employee) {
        try {
            employee.setClientPermissions();
            this.save(employee);
            return findById(employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void changeSubscription(Employee employee) {
        try {
            if (employee.getIsSubscribed() == true){
                employee.setIsSubscribed(false);
                this.update(employee);
            }
            else {
                employee.setIsSubscribed(true);
                this.update(employee);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Employee> findById(final int indexNumber) {
        try {
            Employee employee = currentSession()
                    .createQuery("SELECT c FROM Employee c WHERE c.id = :id", Employee.class)
                    .setParameter("id", indexNumber).getSingleResult();
            return Optional.of(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Employee> findByLogin(final LoginData data) {
        try {
            Employee employee = currentSession()
                    .createQuery("SELECT c FROM Employee c WHERE c.login = :login", Employee.class)
                    .setParameter("login", data.getLogin()).getSingleResult();
            return Optional.of(employee);
        } catch (Exception e) {
            if(e instanceof NoResultException) System.out.println("User not found");
            else e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ObservableList<Employee> listAll() {
        try {
            return FXCollections.observableArrayList(
                    currentSession().createQuery("FROM Employee", Employee.class).list()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

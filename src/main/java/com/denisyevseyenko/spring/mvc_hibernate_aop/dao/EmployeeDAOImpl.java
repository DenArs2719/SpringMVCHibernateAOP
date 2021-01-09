package com.denisyevseyenko.spring.mvc_hibernate_aop.dao;

import com.denisyevseyenko.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO
{
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Employee> getAllEmployees()
    {
        Session session = sessionFactory.getCurrentSession();

        List<Employee> allEmployees = session.createQuery("FROM Employee",Employee.class).getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee)
    {
        ///get current session
        Session session = sessionFactory.getCurrentSession();

        //if employee.getId() == 0 we will make insert else we will make update
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id)
    {
        ///get current session
        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class,id);

        return employee;

    }

    @Override
    public void deleteEmployee(Employee employee)
    {
        ///get current session
        Session session = sessionFactory.getCurrentSession();

        session.delete(employee);

    }


}

package com.jspiders.springmvc6.base.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.springmvc6.base.pojo.StudentPojo;

@Repository
public class StudentRepository {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static String jpql;
	private static Query query;

	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("springmvc6");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}

	private static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}

	public StudentPojo add(String name, String email, long contact, String city, String username, String password) {
		openConnection();
		transaction.begin();
		StudentPojo student = new StudentPojo();
		student.setName(name);
		student.setEmail(email);
		student.setContact(contact);
		student.setCity(city);
		student.setUsername(username);
		student.setPassword(password);
		manager.persist(student);
		transaction.commit();
		closeConnection();
		return student;
	}

	public StudentPojo login(String username, String password) {
		openConnection();
		transaction.begin();
		jpql = "from StudentPojo " + "where username = '" + username + "' " + "and password = '" + password + "'";
		query = manager.createQuery(jpql);
		List<StudentPojo> resultList = query.getResultList();
		for (StudentPojo student : resultList) {
			transaction.commit();
			closeConnection();
			return student;
		}
		transaction.commit();
		closeConnection();
		return null;
	}

	public StudentPojo search(int id) {
		openConnection();
		transaction.begin();
		StudentPojo student = manager.find(StudentPojo.class, id);
		if (student != null) {
			transaction.commit();
			closeConnection();
			return student;
		}
		transaction.commit();
		closeConnection();
		return null;
	}

	public List<StudentPojo> searchAll() {
		openConnection();
		transaction.begin();
		jpql = "from StudentPojo";
		query = manager.createQuery(jpql);
		List<StudentPojo> students = query.getResultList();
		transaction.commit();
		closeConnection();
		return students;
	}

	public StudentPojo remove(int id) {
		openConnection();
		transaction.begin();
		StudentPojo student = manager.find(StudentPojo.class, id);
		if (student != null) {
			manager.remove(student);
		}
		transaction.commit();
		closeConnection();
		return student;
	}

	public StudentPojo update(int id, String name, String email, long contact, String city, String username,
			String password) {
		openConnection();
		transaction.begin();
		StudentPojo student = manager.find(StudentPojo.class, id);
		if (student != null) {
			student.setName(name);
			student.setEmail(email);
			student.setContact(contact);
			student.setCity(city);
			student.setUsername(username);
			student.setPassword(password);
			manager.persist(student);
			transaction.commit();
			closeConnection();
			return student;
		}
		transaction.commit();
		closeConnection();
		return null;
	}

}
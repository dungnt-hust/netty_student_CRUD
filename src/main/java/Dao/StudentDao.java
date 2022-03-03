package Dao;

import Entity.Student;
import io.netty.channel.ChannelHandlerContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

import static Utils.HibernateUtil.getSession;

public class StudentDao {
    public static void listStudent(ChannelHandlerContext ctx) {
        //open session
        Session session = getSession();
        
        try{
            //creat query
            String hql = "FROM Student";
            //get all student
            List students = session.createQuery(hql, Student.class).list();
            for (Iterator iterator = students.iterator(); iterator.hasNext();){
                Student student = (Student) iterator.next();
                ctx.writeAndFlush(
                        "ID: " + student.getId() +
                                "  Name: " + student.getName() +
                                "  Sex: " + student.getSex() +
                                "  Point: " + student.getPoint() + "\n"
                );
            }
        }catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
    }

    public static int addStudent(String name, String sex, int point) {
        //open session
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        int id = 0;
        try{
            Student student = new Student(name, sex, point);
            id = (int) session.save(student);
            session.flush();
            session.clear();

        }catch (HibernateException e){
            System.err.println(e);
            if(tx!=null) tx.rollback();

        }finally {
            tx.commit();
            session.close();
            System.out.println("Add success");
        }
        return id;
    }

    public static void updateStudent(int id, int point) {
        //open session
        Session session = getSession();
        Transaction tx = session.beginTransaction();

        try{
            //creat query
            Student student = (Student) session.get(Student.class, id);
            student.setPoint(point);
            session.update(student);
            session.flush();
            session.clear();

        }catch (HibernateException e){
            System.err.println(e);
        }finally {
            tx.commit();
            session.close();
            System.out.println("Update success");
        }
    }

    public static void deleteStudent(int id) {
        //open session
        Session session = getSession();
        Transaction tx = session.beginTransaction();

        try{
            Student student = (Student) session.get(Student.class, id);
           session.delete(student);
            session.flush();
            session.clear();

        }catch (HibernateException e){
            System.err.println(e);
            if (tx!=null) tx.rollback();
        }finally {
            tx.commit();
            session.close();
            System.out.println("Delete success");
        }
    }
}

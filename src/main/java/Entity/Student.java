package Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Student", schema = "netty_test")
public class Student implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "sex", nullable = true, length = 45)
    private String sex;
    @Basic
    @Column(name = "point", nullable = true)
    private int point;
    public Student(){}
    public Student(String name, String sex, int point){
        this.name = name;
        this.point = point;
        this.sex = sex;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}

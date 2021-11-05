package model;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private Date date;
    private int weight;
    private int total;

    @ManyToOne
    private Module module;


    public Long getId() {
        return id;
    }

    public Exam setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Exam setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Exam setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Exam setDate(Date examDate) {
        this.date = examDate;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public Exam setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public Exam setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Module getModule() {
        return module;
    }

    public Exam setModule(Module module) {
        this.module = module;
        return this;
    }


    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", weight=" + weight +
                ", total=" + total +
                '}';
    }
}
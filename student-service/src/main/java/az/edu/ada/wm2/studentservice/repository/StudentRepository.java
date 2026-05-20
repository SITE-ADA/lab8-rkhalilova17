package az.edu.ada.wm2.studentservice.repository;

import az.edu.ada.wm2.studentservice.model.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("""
            select s from Student s
            where lower(s.firstName) like lower(concat('%', :name, '%'))
               or lower(s.lastName) like lower(concat('%', :name, '%'))
               or lower(concat(s.firstName, ' ', s.lastName)) like lower(concat('%', :name, '%'))
            """)
    List<Student> searchByName(@Param("name") String name);
}
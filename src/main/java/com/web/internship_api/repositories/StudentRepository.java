package com.web.internship_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.internship_api.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	List<Student> findByDeleted(byte deleted);
	Optional<Student> findByIdAndDeleted(int id, byte deleted);
	Optional<Student> findByNameAndDeleted(String name,  byte deleted);
	
//	@Query("SELECT a FROM Student a WHERE a.name LIKE %:name% AND a.clazz LIKE %:classId% AND a.yearStudy LIKE %:yearStudy% AND a.sex LIKE %:sex% AND a.deleted = 0")
//	List<Student> searchStudent(@Param("name") String name,@Param("className") String className,@Param("yearStudy") String yearStudy,@Param("sex") String sex);
	
	@Query("SELECT a FROM Student a WHERE a.name LIKE %:name% AND a.clazz.name LIKE %:className% AND a.yearStudy LIKE %:yearStudy% AND a.sex LIKE %:sex% AND a.deleted = 0")
    List<Student> searchStudent(@Param("name") String name, @Param("className") String className, @Param("yearStudy") String yearStudy, @Param("sex") String sex);

	
//	@Query("SELECT a FROM Student a LEFT JOIN a.internshipsStudents b JOIN b.internship c LEFT JOIN c.teacher d WHERE a.name LIKE %:name%"
//			+ " AND a.class_ LIKE %:className%"
//			+ " AND a.yearStudy LIKE %:yearStudy%"
//			+ " AND a.sex LIKE %:sex%"
//			+ " AND d.name LIKE %:nameTeacher%"
//			+ " AND c.nameInternship LIKE %:nameInternship%"
//			+ " AND c.courseInternship = :courseInternship"
//			+ " AND a.deleted = 0")
//	List<Student> statisticalStudent(
//			@Param("name") String name,
//			@Param("className") String className,
//			@Param("yearStudy") String yearStudy,
//			@Param("sex") String sex,
//			@Param("nameTeacher") String nameTeacher,
//			@Param("nameInternship") String nameInternship,
//			@Param("courseInternship") int courseInternship);
	@Query("SELECT a FROM Student a LEFT JOIN a.internshipsStudents b JOIN b.internship c LEFT JOIN c.teacher d WHERE a.name LIKE %:name%"
            + " AND a.clazz.name LIKE %:className%"
            + " AND a.yearStudy LIKE %:yearStudy%"
            + " AND a.sex LIKE %:sex%"
            + " AND d.name LIKE %:nameTeacher%"
            + " AND c.nameInternship LIKE %:nameInternship%"
            + " AND c.courseInternship = :courseInternship"
            + " AND a.deleted = 0")
    List<Student> statisticalStudent(
            @Param("name") String name,
            @Param("className") String className,
            @Param("yearStudy") String yearStudy,
            @Param("sex") String sex,
            @Param("nameTeacher") String nameTeacher,
            @Param("nameInternship") String nameInternship,
            @Param("courseInternship") int courseInternship);
	
//	@Query("SELECT s FROM Student s LEFT JOIN s.internshipsStudents i LEFT JOIN i.internship c LEFT JOIN c.teacher d WHERE s.name LIKE %:name%"
//			+ " AND s.class_ LIKE %:className%"
//			+ " AND s.yearStudy LIKE %:yearStudy%"
//			+ " AND s.sex LIKE %:sex%"
//			+ " AND d.name LIKE %:nameTeacher%"
//			+ " AND c.nameInternship LIKE %:nameInternship%"
//			+ " AND s.deleted = 0")
//	List<Student> statisticalStudentNoCourse(
//			@Param("name") String name,
//			@Param("className") String className,
//			@Param("yearStudy") String yearStudy,
//			@Param("sex") String sex,
//			@Param("nameTeacher") String nameTeacher,
//			@Param("nameInternship") String nameInternship);
	@Query("SELECT s FROM Student s LEFT JOIN s.internshipsStudents i LEFT JOIN i.internship c LEFT JOIN c.teacher d WHERE s.name LIKE %:name%"
            + " AND s.clazz.name LIKE %:className%"
            + " AND s.yearStudy LIKE %:yearStudy%"
            + " AND s.sex LIKE %:sex%"
            + " AND d.name LIKE %:nameTeacher%"
            + " AND c.nameInternship LIKE %:nameInternship%"
            + " AND s.deleted = 0")
    List<Student> statisticalStudentNoCourse(
            @Param("name") String name,
            @Param("className") String className,
            @Param("yearStudy") String yearStudy,
            @Param("sex") String sex,
            @Param("nameTeacher") String nameTeacher,
            @Param("nameInternship") String nameInternship);
	


}

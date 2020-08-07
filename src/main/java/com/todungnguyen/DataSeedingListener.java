package com.todungnguyen;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.todungnguyen.model.Admission;
import com.todungnguyen.model.ClassAHT;
import com.todungnguyen.model.Course;
import com.todungnguyen.model.Role;
import com.todungnguyen.model.Student;
import com.todungnguyen.model.Subject;
import com.todungnguyen.model.Teacher;
import com.todungnguyen.model.Test;
import com.todungnguyen.model.Transcript;
import com.todungnguyen.model.University;
import com.todungnguyen.model.User;
import com.todungnguyen.repository.AdmissionRepository;
import com.todungnguyen.repository.ClassAHTRepository;
import com.todungnguyen.repository.CourseRepository;
import com.todungnguyen.repository.RoleRepository;
import com.todungnguyen.repository.StudentRepository;
import com.todungnguyen.repository.SubjectRepository;
import com.todungnguyen.repository.TeacherRepository;
import com.todungnguyen.repository.TestRepository;
import com.todungnguyen.repository.TranscriptRepository;
import com.todungnguyen.repository.UniversityRepository;
import com.todungnguyen.repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private AdmissionRepository admissionRepository;

	@Autowired
	private ClassAHTRepository classRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private TranscriptRepository transcriptRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}

		if (roleRepository.findByName("ROLE_STUDENT") == null) {
			roleRepository.save(new Role("ROLE_STUDENT"));
		}

		if (roleRepository.findByName("ROLE_TEACHER") == null) {
			roleRepository.save(new Role("ROLE_TEACHER"));
		}

		// Admin account
		if (userRepository.findByEmail("admin@gmail.com") == null) {
			User admin = new User();
			admin.setFullname("admin 1");
			admin.setUsername("admin_1");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("123456"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_STUDENT"));
			roles.add(roleRepository.findByName("ROLE_TEACHER"));
			admin.setRoles(roles);
			userRepository.save(admin);
		}

		// Student account
		if (userRepository.findByEmail("student@gmail.com") == null) {
			User student = new User();
			student.setFullname("student 1");
			student.setUsername("student_1");
			student.setEmail("student@gmail.com");
			student.setPassword(passwordEncoder.encode("123456"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_STUDENT"));
			student.setRoles(roles);
			userRepository.save(student);
		}

		// Teacher account
		if (userRepository.findByEmail("teacher@gmail.com") == null) {
			User teacher = new User();
			teacher.setFullname("teacher 1");
			teacher.setUsername("teacher_1");
			teacher.setEmail("teacher@gmail.com");
			teacher.setPassword(passwordEncoder.encode("123456"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_TEACHER"));
			teacher.setRoles(roles);
			userRepository.save(teacher);
		}

		// University
		University uni = new University();
		if (universityRepository.findByName("FPT") == null) {
			uni.setName("FPT");
			uni.setDepartment("IT");
			uni.setDean("Mr.Dean");
			uni.setPhone("0904367342");
			uni.setEmail("dean@fpt.com");
			universityRepository.save(uni);
		}

		// Student
		Student stu = new Student();
		if (studentRepository.findByName("FPT's Student") == null) {
			stu.setName("FPT's Student");
			stu.setDob(java.sql.Date.valueOf("1995-01-18"));
			stu.setPob("Ha noi");
			stu.setAddress("xx - Dong Da - Ha noi");
			stu.setUniversity(uni);
			studentRepository.save(stu);
		}

		if (admissionRepository.findByName("Summer") == null) {
			Admission ad = new Admission();
			ad.setName("Summer");
			ad.setStart(java.sql.Date.valueOf("2019-05-02"));
			ad.setEnd(java.sql.Date.valueOf("2019-08-16"));
			ad.setCurator("Tony Bui");
			admissionRepository.save(ad);
		}

		if (classRepository.findByName("Class 1") == null) {
			ClassAHT classTest = new ClassAHT();
			classTest.setName("Class 1");
			classTest.setCode("C1");
			classRepository.save(classTest);
		}

		Course course = new Course();
		if (courseRepository.findByName("Backend") == null) {
			course.setName("Backend");
			course.setStart(java.sql.Date.valueOf("2019-05-02"));
			course.setEnd(java.sql.Date.valueOf("2019-08-02"));
			course.setInformation("Learn about Spring Framework and Angular");
			courseRepository.save(course);
		}

		Subject subject = new Subject();
		if (subjectRepository.findByName("Angular") == null) {
			subject.setName("Angular");
			subject.setContent("Angular 5");
			subject.setNote("VSCode");
			subject.setCode("A");
			subjectRepository.save(subject);
		}

		if (teacherRepository.findByName("Tony Bui") == null) {
			Teacher teacher = new Teacher();
			teacher.setName("Tony Bui");
			teacher.setPhone("090547586");
			teacher.setEmail("tony@aht.com");
			teacher.setSkype("tony");
			teacherRepository.save(teacher);
		}

		Test test = new Test();
		if (testRepository.findByName("Angular 001") == null) {
			test.setCode("001");
			test.setName("Angular 001");
			test.setContent("blabla");
			test.setOrder(1);
			testRepository.save(test);
		}

		if (transcriptRepository.findByTestCode("001") == null) {
			Transcript trans = new Transcript();
			trans.setPoint(8);
			trans.setSubject(subject);
			trans.setCourse(course);
			trans.setTest(test);
			trans.setStudent(stu);
			transcriptRepository.save(trans);
		}
	}
}

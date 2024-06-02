 package com.web.internship_api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.AttendanceCheck;
import com.web.internship_api.entities.Class;
import com.web.internship_api.entities.Company;
import com.web.internship_api.entities.Internship;
import com.web.internship_api.entities.InternshipsStudent;
import com.web.internship_api.entities.Major;
import com.web.internship_api.entities.Report;
import com.web.internship_api.entities.Student;
import com.web.internship_api.entities.Teacher;
import com.web.internship_api.models.ClassModel;
import com.web.internship_api.models.CompanyModel;
import com.web.internship_api.models.InternShipModel;
import com.web.internship_api.models.MajorModel;
import com.web.internship_api.models.ResponseAttendance;
import com.web.internship_api.models.ResponseInternship;
import com.web.internship_api.models.ResponseListStudent;
import com.web.internship_api.models.ResponseObject;
import com.web.internship_api.models.ResponseStatistical;
import com.web.internship_api.models.ResponseStudent;
import com.web.internship_api.models.SearchInternship;
import com.web.internship_api.models.SearchStatisticial;
import com.web.internship_api.models.SearchStudentModel;
import com.web.internship_api.models.SearchTeacherModel;
import com.web.internship_api.models.StudentModel;
import com.web.internship_api.models.TeacherModel;
import com.web.internship_api.models.UltilSetModel;
import com.web.internship_api.services.AccountService;
import com.web.internship_api.services.AttendanceCheckService;
import com.web.internship_api.services.ClassService;
import com.web.internship_api.services.CompanyService;
import com.web.internship_api.services.EmailService;
import com.web.internship_api.services.InternshipService;
import com.web.internship_api.services.InternshipStudentService;
import com.web.internship_api.services.MajorService;
import com.web.internship_api.services.StudentService;
import com.web.internship_api.services.TeacherService;
import com.web.internship_api.services.impl.UserDetailServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/manager")
public class ManagerControler {
	@Autowired
	AccountService accountService;
	@Autowired
	StudentService studentService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ClassService classService;
	@Autowired
	MajorService majorService;
	@Autowired
	UserDetailServiceImpl userDetailServiceimpl;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	InternshipService internshipService;
	@Autowired
	EmailService emailService;
	@Autowired
	AttendanceCheckService attendanceCheckService;
	@Autowired
	InternshipStudentService internshipStudentService;
	
	@GetMapping("/deleted/{id}")
	public ResponseEntity<ResponseObject> deleteAccount(@PathVariable int id){
		Account acc = accountService.deleteAccount(id);
		if(acc != null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Delete Completed",acc));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Account not found", ""));
	}
	
	//Student manager
	@PostMapping("/student")
	public ResponseEntity<ResponseObject> registerStudent(@RequestBody StudentModel model){
		Optional<Account> account = accountService.findAccountByUserName(model.getEmail());
		if(account.isEmpty()) {
			String password = randomPassword();
			String subject = "Send Email And Password";
			String body = "Email: "+ model.getEmail() + ". Password: "+password;
			try {
				emailService.sendMail(model.getEmail(), subject , body);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
	        //Gửi mail cho student
			Account studentAccount = new Account();
			studentAccount.setUsername(model.getEmail());
			studentAccount.setPassword(passwordEncoder.encode(password));
			studentAccount = accountService.createAccount(studentAccount,"STUDENT");
			Account findAccount = accountService.findAccountByUserName(studentAccount.getUsername()).get();
			Class findedClass = classService.findByClassId(Integer.valueOf(model.getClass_id())).get();
			Student student = studentService.createStudent(model,findAccount, findedClass);
			return ResponseEntity.status(HttpStatus.OK).
					body( new ResponseObject(200, "Register successfull", student));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Username is exist", ""));
	}
	
	@GetMapping("/liststudent")
	public ResponseEntity<ResponseObject> listStudent(){
		List<Student> students = studentService.findAll();
		List<ResponseListStudent> studentIntern = students.stream().map(student -> {
			ResponseStudent resStudent = UltilSetModel.setResponseStudent(student);
			List<InternshipsStudent> internStudent = student.getInternshipsStudents();
			ResponseInternship internship = null;
			if(internStudent.size() > 0) {
				internship= UltilSetModel.setResponseInternship(student.getInternshipsStudents().get(0).getInternship());
			}
			return new ResponseListStudent(resStudent, internship);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get list student successfull", studentIntern));
	}
	
	@PostMapping("/student/search")
	public ResponseEntity<ResponseObject> searchStudent(@RequestBody SearchStudentModel model){
		List<Student> listStudent = studentService.searchStudent(model);
		List<ResponseListStudent> listRes = listStudent.stream().map(student -> {
			ResponseStudent resStudent = UltilSetModel.setResponseStudent(student);
			List<InternshipsStudent> internStudent = student.getInternshipsStudents();
			ResponseInternship internship = null;
			if(internStudent.size() > 0) {
				internship= UltilSetModel.setResponseInternship(student.getInternshipsStudents().get(0).getInternship());
			}
			return new ResponseListStudent(resStudent, internship);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Search Completed", listRes));
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<ResponseObject> deletedStudent(@PathVariable int id){
		Student student = studentService.deltedStudent(id);
		if(student != null) {
			ResponseStudent resStudent = UltilSetModel.setResponseStudent(student);
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Delete Completed", resStudent));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found student",""));
	}
	
	// Teacher manager
	@PostMapping("/teacher")
	public ResponseEntity<ResponseObject> registerTeacher(@RequestBody TeacherModel model){
		Optional<Account> account = accountService.findAccountByUserName(model.getEmail());
		if(account.isEmpty()) {
			String password = randomPassword();
			String subject = "Send Email And Password";
			String body = "Email: "+ model.getEmail() + ". Password: "+password;
			try {
				emailService.sendMail(model.getEmail(), subject , body);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			Account teacherAccount = new Account();
			teacherAccount.setUsername(model.getEmail());
			teacherAccount.setPassword(passwordEncoder.encode(password));
			teacherAccount = accountService.createAccount(teacherAccount,"TEACHER");
			Account findAccount = accountService.findAccountByUserName(teacherAccount.getUsername()).get();
			Teacher teacher = teacherService.createTeacher(model, findAccount);
			return ResponseEntity.status(HttpStatus.OK).
					body( new ResponseObject(200, "Register successfull", teacher));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Username is exist", ""));
	}
	
	@GetMapping("/listteacher")
	public ResponseEntity<ResponseObject> listTeacher(){
		List<Teacher> teachers = teacherService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get list teacher successfull", teachers));
	}
	@PostMapping("/teacher/search")
	public ResponseEntity<ResponseObject> searchTeacher(@RequestBody SearchTeacherModel model){
		List<Teacher> teachers = teacherService.searchTeacher(model);
		List<TeacherModel> res = teachers.stream().map(teacher -> {
			return UltilSetModel.setTeacherModel(teacher);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Search teacher successfull", res));
	}
	
	@GetMapping("/teacher/{id}")
	public ResponseEntity<ResponseObject> getInforTeacherById(@PathVariable int id){
		Optional<Teacher> teacherOptional = teacherService.findById(id);
		if(teacherOptional.isPresent()) {
			TeacherModel teacher = UltilSetModel.setTeacherModel(teacherOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get teacher successfull", teacher));
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found teacher",""));
	}
	@DeleteMapping("/teacher/{id}")
	public ResponseEntity<ResponseObject> deteledTeacher(@PathVariable int id){
		Teacher teacher = teacherService.deleteTeacher(id);
		if(teacher != null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Delete Completed", teacher));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found teacher",""));
	}
	
	//internship
	
	@PostMapping("/intern")
	public ResponseEntity<ResponseObject> createInternship(@RequestBody InternShipModel model){
		Internship intership =internshipService.createInternShip(model);
		if(intership!= null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Create Successfull", intership));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Create Faile", ""));
	}
	@PostMapping("/intern/search")
	public ResponseEntity<ResponseObject> searchIntership(@RequestBody SearchInternship model ){
		List<Internship> list = null;
		System.err.println(model.getCourseInternship());
		if(model.getCourseInternship() != 0) {
			list = internshipService.searchInternship(model);
		}else {
			list = internshipService.searchInternshipNoCourse(model);
		}
		List<ResponseInternship> responseInternships = list.stream().map(internship ->{
			ResponseInternship responseInternship = UltilSetModel.setResponseInternship(internship);
			responseInternship.setStudents(UltilSetModel.setListResponseStudent(internship.getInternshipsStudents()));
			return responseInternship;
		}).collect(Collectors.toList());;
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Search Successfull", responseInternships));
	}
	
	@PutMapping("/intern/update/{id}")
	public ResponseEntity<ResponseObject> updateInternship(@RequestBody InternShipModel model,@PathVariable int id){
		model.setId(id);
		Internship intership = internshipService.updateInternShip(model);
		if(intership!= null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Update Successfull", intership));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found internship", ""));
	}
	
	@DeleteMapping("/internship/deleted/{id}")
	public ResponseEntity<ResponseObject> updateInternship(@PathVariable int id){
		Internship internship = internshipService.deletedInternShip(id);
		if(internship != null )
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Deleted Successfull", internship));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found internship", ""));
	}
	
	@GetMapping("/attendance/list")
	public ResponseEntity<ResponseObject> getAllAttendanceCheck(){
		List<AttendanceCheck> attendanceChecks = attendanceCheckService.findAll();
		List<ResponseAttendance> res = attendanceChecks.stream().map(attendance -> {
			return UltilSetModel.setResponseAttendance(attendance);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Update Successfull", res));
	}
	
	@PostMapping("/statistical")
	public ResponseEntity<ResponseObject> getStatistical(@RequestBody SearchStatisticial model){
		List<Student> students = studentService.statisticalStudent(model);
		List<ResponseStatistical> res = students.stream().map(student ->{
				ResponseStatistical statistical = new ResponseStatistical();
				ResponseStudent responseStudent = UltilSetModel.setResponseStudent(student);
				statistical.setStudent(responseStudent);
				List<Report> report = student.getReports();
				Optional<InternshipsStudent> internshipsStudentOptional =internshipStudentService.findById(responseStudent.getIdIntershipStudent());
				if(internshipsStudentOptional.isPresent()) {
					InternshipsStudent internshipsStudent = internshipsStudentOptional.get();
					statistical.setTeacher(UltilSetModel.setTeacherModel(internshipsStudent.getInternship().getTeacher()));
					statistical.setInternship(UltilSetModel.setResponseInternship(internshipsStudent.getInternship()));
					statistical.setAttendance(UltilSetModel.setListResponseAttendance(internshipsStudent.getAttendanceChecks()));
					if(internshipsStudent.getEvaluates().size() > 0) {
						statistical.setScore(internshipsStudent.getEvaluates().get(0).getScore());
					}else {
						statistical.setScore(-1);
					}
					if(report.size() > 0) {
						statistical.setFileReport(UltilSetModel.setResponseReport(report.get(0)));
						
					}
				}
				return statistical;
			}
			).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Successfull", res));
	}
	
	private String randomPassword() {
		int length = 8;
	    String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
	    Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
	}
	
	
	// manage company or (companies) :)) 
	@PostMapping("/company")
	public ResponseEntity<ResponseObject> createCompany(@RequestBody CompanyModel model){
		Company company = companyService.createCompany(model);
		if(company!= null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Create Successfull", company));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Create Faile", ""));
	}
	
	@GetMapping("/listcompany")
	public ResponseEntity<ResponseObject> listCompany(){
		List<Company> company = companyService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get list company successfull", company));
	}
	
	@PostMapping("/company/search")
	public ResponseEntity<ResponseObject> searchCompany(@RequestBody CompanyModel model){
		List<Company> companys = companyService.searchCompany(model);
		List<CompanyModel> res = companys.stream().map(company -> {
			return UltilSetModel.setCompanyModel(company);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Search company successfull", res));
	}
	
	@GetMapping("/company/{id}")
	public ResponseEntity<ResponseObject> getInforCompanyById(@PathVariable int id){
		Optional<Company> companyOptional = companyService.findByCompanyId(id);
		if(companyOptional.isPresent()) {
			CompanyModel company = UltilSetModel.setCompanyModel(companyOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get teacher successfull", company));
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found company",""));
	}
	
	@PutMapping("/company/update/{id}")
	public ResponseEntity<ResponseObject> updateCompany(@RequestBody CompanyModel model,@PathVariable int id){
		model.setId(id);
		Company company = companyService.updateCompany(model);
		if(company!= null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Update Successfull", company));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found company", ""));
	}
	
	@DeleteMapping("/company/{id}")
	public ResponseEntity<ResponseObject> deteledCompany(@PathVariable int id){
		Company company = companyService.deleteCompany(id);
		if(company != null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Delete Completed", company));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found company",""));
	}
	
	// manage major
	@PostMapping("/major")
	public ResponseEntity<ResponseObject> createMajor(@RequestBody MajorModel model){
		Major major = majorService.createMajor(model);
		if(major!= null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Create Successfull", major));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Create Faile", ""));
	}

	@GetMapping("/listmajor")
	public ResponseEntity<ResponseObject> listMajor(){
		List<Major> major = majorService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get list major successfull", major));
	}
	
	@PostMapping("/major/search")
	public ResponseEntity<ResponseObject> searchMajor(@RequestBody MajorModel model){
		List<Major> majors = majorService.searchMajor(model);
		List<MajorModel> res = majors.stream().map(major -> {
			return UltilSetModel.setMajorModel(major);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Search major successfull", res));
	}
	
	@GetMapping("/major/{id}")
	public ResponseEntity<ResponseObject> getInforMajorById(@PathVariable int id){
		Optional<Major> majorOptional = majorService.findById(id);
		if(majorOptional.isPresent()) {
			MajorModel major = UltilSetModel.setMajorModel(majorOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get major successfull", major));
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found major",""));
	}
	
	@PutMapping("/major/update/{id}")
	public ResponseEntity<ResponseObject> updateMajor(@RequestBody MajorModel model,@PathVariable int id){
		model.setId(id);
		Major major = majorService.updateMajor(model);
		if(major!= null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Update Successfull", major));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found major", ""));
	}
	
	@DeleteMapping("/major/{id}")
	public ResponseEntity<ResponseObject> deteledMajor(@PathVariable int id){
		Major major = majorService.deleteMajor(id);
		if(major != null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Delete Completed", major));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found major",""));
	}
	
	// class major
	@PostMapping("/classes")
	public ResponseEntity<ResponseObject> createClass(@RequestBody ClassModel model) {
		Class classes = classService.createClass(model);
		if (classes != null)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Create Successfull", classes));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Create Faile", ""));
	}
	
	@GetMapping("/listClasses")
	public ResponseEntity<ResponseObject> listClass(){
		List<Class> classes = classService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get list class successfull", classes));
	}
	@PostMapping("/classes/search")
	public ResponseEntity<ResponseObject> searchClass(@RequestBody ClassModel model){
		List<Class> classes = classService.searchClass(model);
		List<ClassModel> res = classes.stream().map(classz -> {
			return UltilSetModel.setClassModel(classz);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Search classes successfull", res));
	}
	
	@GetMapping("/classes/{id}")
	public ResponseEntity<ResponseObject> getInforClassById(@PathVariable int id){
		Optional<Class> classOptional = classService.findByClassId(id);
		if(classOptional.isPresent()) {
			ClassModel classes = UltilSetModel.setClassModel(classOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Get classes successfull", classes));
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found classes",""));
	}
	
	@PutMapping("/classes/update/{id}")
	public ResponseEntity<ResponseObject> updateClass(@RequestBody ClassModel model,@PathVariable int id){
		model.setId(id);
		Class classes = classService.updateClass(model);
		if(classes!= null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Update Successfull", classes));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found classes", ""));
	}
	
	@DeleteMapping("/classes/{id}")
	public ResponseEntity<ResponseObject> deteledClass(@PathVariable int id){
		Class classes = classService.deleteClass(id);
		if(classes != null)
			return ResponseEntity.status(HttpStatus.OK).body( new ResponseObject(200, "Delete Completed", classes));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseObject(404, "Not found major",""));
	}
	
	

}

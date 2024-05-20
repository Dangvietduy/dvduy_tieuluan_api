package com.web.internship_api.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.AttendanceCheck;
import com.web.internship_api.entities.Evaluate;
import com.web.internship_api.entities.Internship;
import com.web.internship_api.entities.InternshipsStudent;
import com.web.internship_api.entities.Report;
import com.web.internship_api.entities.Student;
import com.web.internship_api.models.InternshipStudentModel;
import com.web.internship_api.models.ResponseAttendance;
import com.web.internship_api.models.ResponseInternship;
import com.web.internship_api.models.ResponseInternshipStudent;
import com.web.internship_api.models.ResponseObject;
import com.web.internship_api.models.ResponseReport;
import com.web.internship_api.models.ResponseStudent;
import com.web.internship_api.models.StudentModel;
import com.web.internship_api.models.UltilSetModel;
import com.web.internship_api.services.AccountService;
import com.web.internship_api.services.AttendanceCheckService;
import com.web.internship_api.services.InternshipService;
import com.web.internship_api.services.InternshipStudentService;
import com.web.internship_api.services.ReportService;
import com.web.internship_api.services.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/student")
public class StudentControllers {
	@Autowired
	AccountService accountService;
	@Autowired
	StudentService studentService;
	@Autowired
	InternshipService internshipService;
	@Autowired
	InternshipStudentService internshipStudentService;
	@Autowired
	ReportService reportService;
	@Autowired
	AttendanceCheckService attendanceCheckService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> getStudentByUserId(@PathVariable int id){
		Optional<Account> account = accountService.findById(id);
		if(account.isPresent()) {
			Student student = account.get().getStudents().get(0);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "sucess",student));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "NOT FOUND STUDENT",""));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseObject> updateStudent(@RequestBody StudentModel model,@PathVariable int id){
		Student student = studentService.updateStudent(model,id);
		if(student != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Comleted!",student));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));
	}
	
	//internship
	
	@GetMapping("/internship/list")
	public ResponseEntity<ResponseObject> listInternship(){
		List<Internship> listInternship = internshipService.findAll();
		List<ResponseInternship> listRes = listInternship.stream().map(internship -> {
			return UltilSetModel.setResponseInternship(internship);
		}).collect(Collectors.toList());;
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Get List InternShip Completed!",listRes));
	}
	
	@GetMapping("/internship/{id}")
	public ResponseEntity<ResponseObject> getInternshipById(@PathVariable int id ){
		Optional<Internship> internship = internshipService.findById(id);
		if(internship.isPresent()) {
			ResponseInternship responseInternship = UltilSetModel.setResponseInternship(internship.get());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Get InternShip Completed!",responseInternship));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));
	}
	
	//internshipStudent
	@GetMapping("/internshipStudent/{id}")
	public ResponseEntity<ResponseObject> getInternshipStudent(@PathVariable int id ){
		Optional<InternshipsStudent>  internStudent = internshipStudentService.findById(id);
		if(internStudent != null) {
			ResponseInternshipStudent res = new ResponseInternshipStudent();
			res.setInternship(UltilSetModel.setResponseInternship(internStudent.get().getInternship()));
			res.setStudent(UltilSetModel.setResponseStudent(internStudent.get().getStudent()));
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Get Comleted!",res));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));
	}
	@PostMapping("/internshipStudent/create")
	public ResponseEntity<ResponseObject> createInternShip(@RequestBody InternshipStudentModel model ){
		InternshipsStudent  internStudent = internshipStudentService.createInternShip(model);
		if(internStudent != null) {
			ResponseInternshipStudent res = new ResponseInternshipStudent();
			res.setInternship(UltilSetModel.setResponseInternship(internStudent.getInternship()));
			res.setStudent(UltilSetModel.setResponseStudent(internStudent.getStudent()));
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Create Comleted!",res));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Internship Student is exist", ""));
	}
	
	@PutMapping("/internshipStudent/update")
	public ResponseEntity<ResponseObject> updateInternShip(@RequestBody InternshipStudentModel model ){
		InternshipsStudent  internStudent = internshipStudentService.updateInternShip(model);
		if(internStudent != null) {
			ResponseInternshipStudent res = new ResponseInternshipStudent();
			res.setInternship(UltilSetModel.setResponseInternship(internStudent.getInternship()));
			res.setStudent(UltilSetModel.setResponseStudent(internStudent.getStudent()));
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Comleted!",res));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Internship Student is not exist", ""));
	}
	
	@DeleteMapping("/internshipStudent/{id}")
	public ResponseEntity<ResponseObject> deleteInternShip(@PathVariable int id ){
		try {
			InternshipsStudent  internStudent = internshipStudentService.deletedInternShip(id);
			if(internStudent != null)
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Delete Comleted!",internStudent));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Internship Student is not exist", ""));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Can't deleted intership student", ""));
		}
		
	}
	
	//report
	@GetMapping("/report/{id}")
	public ResponseEntity<ResponseObject> getReport (@PathVariable int id){
		List<Report> reports = reportService.findByStudentID(id);
		if(reports.size() > 0) {
			ResponseReport res = UltilSetModel.setResponseReport(reports.get(0));
			res.setUrl(null);
			return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Sucessfully",res));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Not exist report by student",""));
	}
	
	@PostMapping("/report/{id}")
	public ResponseEntity<ResponseObject> createReport(@RequestParam("file") MultipartFile file, @PathVariable int id) throws IOException{
		Optional<InternshipsStudent> internStudent = internshipStudentService.findById(id);
		if(internStudent.isPresent()) {
			Report report = new Report();
			report.setStudent(internStudent.get().getStudent());
			report.setInternship(internStudent.get().getInternship());
			report.setReportDay(new Date());
			report.setReportContent(file.getOriginalFilename());
			report.setFileReport(file.getBytes());
			Report reports = reportService.createReport(report);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Create Comleted!",reports));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));	
	}
	@DeleteMapping("/report/{id}")
	public ResponseEntity<ResponseObject> deletedReport( @PathVariable int id) throws IOException{
		Optional<Report> reportOptional = reportService.findById(id);
		if(reportOptional.isPresent()) {
			Report report= reportService.deletedReport(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Delete Comleted!",report));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));	
	}
	//evaluate
	@GetMapping("/evaluate/{id}")
	public ResponseEntity<ResponseObject> getEvaluate(@PathVariable int id){
		Optional<Student> student =  studentService.findById(id);
		if(student.isPresent()) {
			Evaluate evaluate = student.get().getInternshipsStudents().get(0).getEvaluates().get(0);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Successfully",evaluate));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));
	}
	//GET AVART
	@GetMapping("/avatar/{id}")
	public ResponseEntity<ResponseObject> getAvatarByUserId(@PathVariable int id){
		Optional<Account> account = accountService.findById(id);
		if(account.isPresent()) {
			String avatar =account.get().getStudents().get(0).getAvatar();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "sucess",avatar));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "NOT FOUND STUDENT",""));
	}
	//search internship by name
	@GetMapping("/search/intern/{name}")
	public ResponseEntity<ResponseObject> searchInternshipByName(@PathVariable String name){
		List<Internship> listInternship = internshipService.seacheByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "sucess",listInternship));
	}
	
	@GetMapping("/attendance/{id}") 
	public ResponseEntity<ResponseObject> attendanceCheck(@PathVariable int id){
		Optional<Student> student = studentService.findById(id);
		if(student.isPresent()) {
			try {
				AttendanceCheck attendance = new AttendanceCheck();
				attendance.setAttendanceDayCheck(new Date());
				attendance.setInternshipsStudent(student.get().getInternshipsStudents().get(0));
				attendance.setStatus((byte) 0);
				attendance.setDeleted((byte) 0);
				attendanceCheckService.createAttendance(attendance);
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Student not existed Intership Student",""));
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Sucessfully",""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found student",""));
	}
	@GetMapping("/attendance/list/{id}")
	public ResponseEntity<ResponseObject> getListttendanceCheck(@PathVariable int id){
		Optional<Student> studentOptional = studentService.findById(id);
		if(studentOptional.isPresent()) {
			if(studentOptional.get().getInternshipsStudents().size() > 0) {
				int idIntershipStudent = studentOptional.get().getInternshipsStudents().get(0).getId();
				List<AttendanceCheck> attendances = attendanceCheckService.findByInternshipsStudentId(idIntershipStudent);
				List<ResponseAttendance> res = attendances.stream().map(attendance -> {
					return UltilSetModel.setResponseAttendance(attendance);
				}).collect(Collectors.toList());
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Sucessfully",res));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not internship student",""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found student",""));
		
	}
	
	@GetMapping("/infor/{id}")
	public ResponseEntity<ResponseObject> getInforById(@PathVariable int id){
		Optional<Student> studentOptional = studentService.findById(id);
		if(studentOptional.isPresent()) {
			ResponseStudent res = UltilSetModel.setResponseStudent(studentOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "sucess",res));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "NOT FOUND STUDENT",""));
	}
}

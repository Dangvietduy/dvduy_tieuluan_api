package com.web.internship_api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.AttendanceCheck;
import com.web.internship_api.entities.Evaluate;
import com.web.internship_api.entities.Internship;
import com.web.internship_api.entities.Report;
import com.web.internship_api.entities.Student;
import com.web.internship_api.entities.Teacher;
import com.web.internship_api.models.EvaluateModel;
import com.web.internship_api.models.ResponseAttendance;
import com.web.internship_api.models.ResponseInternship;
import com.web.internship_api.models.ResponseObject;
import com.web.internship_api.models.ResponseStudent;
import com.web.internship_api.models.TeacherModel;
import com.web.internship_api.models.UltilSetModel;
import com.web.internship_api.services.AccountService;
import com.web.internship_api.services.AttendanceCheckService;
import com.web.internship_api.services.EvaluteService;
import com.web.internship_api.services.InternshipService;
import com.web.internship_api.services.ReportService;
import com.web.internship_api.services.StudentService;
import com.web.internship_api.services.TeacherService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	AccountService accountService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	EvaluteService evaluteService;
	@Autowired
	ReportService reportService;
	@Autowired
	StudentService studentService;
	@Autowired
	AttendanceCheckService attendanceCheckService;
	@Autowired
	InternshipService internService;
	//teacher
	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> getInforTeacher(@PathVariable int id){
		Optional<Account> account = accountService.findById(id);
		if(account.isPresent()) {
			Teacher teacher = account.get().getTeachers().get(0);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "sucess", teacher));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "NOT FOUND TEACHER",""));
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseObject> updateInforTeacher(@RequestBody TeacherModel model,@PathVariable int id){
		Teacher teacher = teacherService.updateTeacher(model, id);
		if(teacher != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Comleted!",teacher));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));
	}
	//evaluate
	@PostMapping("/evaluate/create")
	public ResponseEntity<ResponseObject> createEvaluate(@RequestBody EvaluateModel model ){
		Evaluate evaluate =  evaluteService.createEvalute(model);
		if(evaluate != null )
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Comleted!",evaluate));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));
	}
	@PutMapping("/evaluate/update")
	public ResponseEntity<ResponseObject> updateEvaluate(@RequestBody EvaluateModel model ){
		Evaluate evaluate =  evaluteService.updateEvalute(model);
		if(evaluate != null )
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Comleted!",evaluate));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not Found", ""));
	}
	//download file report
	@GetMapping("/report/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable int id){
		Optional<Report> optionalFileDB = reportService.findById(id);
		if(optionalFileDB.isPresent()) {
			byte[] data = optionalFileDB.get().getFileReport();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentLength(data.length);
			headers.setContentDisposition(ContentDisposition.attachment().filename(optionalFileDB.get().getReportContent()).build());
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(data);
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@GetMapping("/listInternship/{id}")
	public ResponseEntity<ResponseObject> getListInternShipByIdTeacher(@PathVariable int id){
		Optional<Teacher> teacherOptional = teacherService.findById(id);
		if(teacherOptional.isPresent()) {
			List<ResponseInternship> listInternship = teacherOptional.get().getInternships().stream().map(internship ->{
				return  UltilSetModel.setResponseInternship(internship);
			}).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Successfully!",listInternship));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Successfully!",""));
	}
	
	@GetMapping("/internship/{id}")
	public ResponseEntity<ResponseObject> getListStudentByInternshipId(@PathVariable int id){
			Optional<Internship> internshipOptional = internService.findById(id);
			if(internshipOptional.isPresent()) {
				ResponseInternship res = UltilSetModel.setResponseInternship(internshipOptional.get());
				if(internshipOptional.get().getInternshipsStudents().size() > 0) {
					List<ResponseStudent> students = internshipOptional.get().getInternshipsStudents().stream().map(internshipStudent -> {
						return UltilSetModel.setResponseStudent(internshipStudent.getStudent());
					}).collect(Collectors.toList());
					res.setStudents(students);
				}
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Internship",res));
			}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Not found internship",""));
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
	
	
}

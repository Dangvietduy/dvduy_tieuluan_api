package com.web.internship_api.models;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.internship_api.entities.AttendanceCheck;
import com.web.internship_api.entities.Company;
import com.web.internship_api.entities.Internship;
import com.web.internship_api.entities.InternshipsStudent;
import com.web.internship_api.entities.Major;
import com.web.internship_api.entities.Class;
import com.web.internship_api.entities.Report;
import com.web.internship_api.entities.Student;
import com.web.internship_api.entities.Teacher;

public class UltilSetModel {
	
	public static ResponseStudent setResponseStudent(Student student) {
		ResponseStudent resStudent =  new ResponseStudent();
		resStudent.setId(student.getId());
		resStudent.setEmail(student.getEmail());
		resStudent.setFullname(student.getName());
		resStudent.setSex(student.getSex());
		resStudent.setDob(student.getBirthDay());
		resStudent.setPhone(student.getPhone());
		resStudent.setAvatar(student.getAvatar());
		resStudent.setAddress(student.getAddress());
		resStudent.setDepartment(student.getDepartment());
		resStudent.setYear_study(student.getYearStudy());
		resStudent.setClasses(student.getClazz().getId());
		resStudent.setNameClass(student.getClazz().getName());

		resStudent.setClasses(student.getClazz().getId());
		if(student.getReports().size() > 0 ) {
			resStudent.setIdReport(student.getReports().get(0).getId());
		}
		if(student.getInternshipsStudents().size() > 0 ) {
			resStudent.setIdIntershipStudent(student.getInternshipsStudents().get(0).getId());
		}
		return resStudent;
	}
	public static List<ResponseStudent> setListResponseStudent(List<InternshipsStudent> internshipStudents){
		List<ResponseStudent> list = internshipStudents.stream().map(internshipStudent -> {
			return UltilSetModel.setResponseStudent(internshipStudent.getStudent());
		}).collect(Collectors.toList());
		return list;
	}
	public static ResponseInternship setResponseInternship(Internship internship) {
		ResponseInternship response = new ResponseInternship();
		response.setId(internship.getId());
		response.setNameInternShip(internship.getNameInternship());
		response.setStartDay(internship.getStartDay());
		response.setEndDay(internship.getEndDay());
		response.setCourseInternShip(internship.getCourseInternship());
		response.setDescription(internship.getDescription());
		response.setAddress(internship.getAddress());
		response.setTeacherId(internship.getTeacher().getId());
		response.setTeacherName(internship.getTeacher().getName());
		response.setStudents(internship.getInternshipsStudents().stream().map(internshipStudent -> {
			return setResponseStudent(internshipStudent.getStudent());
		}).collect(Collectors.toList()));
		return response;
	}
	public static TeacherModel setTeacherModel(Teacher teacher) {
		TeacherModel model = new TeacherModel();
		model.setId(teacher.getId());
		model.setFullName(teacher.getName());
		model.setEmail(teacher.getEmail());
		model.setDob(teacher.getBirthDay());
		model.setPhone(teacher.getPhone());
		model.setAddress(teacher.getAddress());
		model.setSex(teacher.getSex());
		model.setSpecialize(teacher.getSpecialize());
		model.setLevel(teacher.getLevel());
		model.setSalary(teacher.getSalary());
		model.setAvatar(teacher.getAvatar());
		return model;
	}
	
	public static CompanyModel setCompanyModel(Company company) {
		CompanyModel model = new CompanyModel();
		model.setId(company.getId());
		model.setEmail(company.getEmail());
		model.setName(company.getName());
		model.setPhone(company.getPhone());
		model.setAddress(company.getAddress());
		model.setIndustry(company.getIndustry());
		return model;
	}
	
	public static ClassModel setClassModel(Class classes) {
		ClassModel model = new ClassModel();
		model.setId(classes.getId());
		model.setName(classes.getName());
		model.setCode(classes.getCode());
		model.setMajorId(classes.getMajor());
		model.setTeacherId(classes.getTeacher());
		return model;
	}
	
	public static MajorModel setMajorModel(Major major) {
		MajorModel model = new MajorModel();
		model.setId(major.getId());
		model.setName(major.getName());
		return model;
	}
	
	public static Teacher setTeacher(TeacherModel model) {
		Teacher teacher = new Teacher();
		teacher.setName(model.getFullName());
		teacher.setEmail(model.getEmail());
		teacher.setBirthDay(model.getDob());
		teacher.setPhone(model.getPhone());
		teacher.setAddress(model.getAddress());
		teacher.setSex(model.getSex());
		teacher.setSpecialize(model.getSpecialize());
		teacher.setLevel(model.getLevel());
		teacher.setSalary(model.getSalary());
		teacher.setAvatar(model.getAvatar());
		return teacher;
	}
	
	
	public static Student setStudent (StudentModel model) {
		Student student = new Student();
		student.setName(model.getFullname());
		student.setEmail(model.getEmail());
		student.setBirthDay(model.getDob());
		student.setPhone(model.getPhone());
		student.setAddress(model.getAddress());
		student.setSex(model.getSex());
		student.setDepartment(model.getDepartment());
		student.setYearStudy(model.getYear_study());
		student.setAvatar(model.getAvatar());
		return  student;
	}
	
	public static ResponseAttendance setResponseAttendance(AttendanceCheck attendance) {
		ResponseAttendance response = new ResponseAttendance();
		response.setAttendaceCheck(attendance.getAttendanceDayCheck());
		return response;
	}
	
	public static List<ResponseAttendance> setListResponseAttendance(List<AttendanceCheck> attendances){
		List<ResponseAttendance> list = attendances.stream().map(attendance -> {
			return setResponseAttendance(attendance);
		}).collect(Collectors.toList());
		return list;
		
	}
	public static ResponseReport setResponseReport (Report report) {
		ResponseReport res = new ResponseReport();
		String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/teacher/report/download/")
                .path(Integer.toString(report.getId()))
                .toUriString();
		res.setId(report.getId());
		res.setName(report.getReportContent());
		res.setReportDay(report.getReportDay());
		res.setUrl(fileDownloadUri);
		return  res;
	}
}

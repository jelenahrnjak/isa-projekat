package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.model.User;
import com.example.WishAndFish.server.AddressService;
import com.example.WishAndFish.server.CityService;
import com.example.WishAndFish.server.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WishAndFish.server.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;


    @GetMapping
    public ResponseEntity<List<UserDTO>> getCourses() {

        List<User> users = userService.findAll();

        // convert courses to DTOs
        List<UserDTO> korisniciDTO = new ArrayList<>();
        for (User u : users) {
            korisniciDTO.add(new UserDTO(u));
        }

        return new ResponseEntity<>(korisniciDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getCourse(@PathVariable Integer id) {

        User user = userService.findOne(id);

        // course must exist
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.save(new User(user));
        addressService.save(user.getAddress());
        cityService.save(user.getAddress().getCity());
        countryService.save(user.getAddress().getCity().getCountry());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

	/*@PutMapping(consumes = "application/json")
	public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO) {

		// a course must exist
		Course course = courseService.findOne(courseDTO.getId());

		if (course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		course.setName(courseDTO.getName());

		course = courseService.save(course);
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {

		Course course = courseService.findOne(id);

		if (course != null) {
			courseService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/{courseId}/exams")
	public ResponseEntity<List<ExamDTO>> getStudentExams(@PathVariable Integer courseId) {

		Course course = courseService.findOneWithExams(courseId);

		Set<Exam> exams = course.getExams();
		List<ExamDTO> examsDTO = new ArrayList<>();

		for (Exam e : exams) {
			ExamDTO examDTO = new ExamDTO();
			examDTO.setId(e.getId());
			examDTO.setGrade(e.getGrade());
			examDTO.setDate(e.getDate());
			examDTO.setStudent(new StudentDTO(e.getStudent()));
			examDTO.setCourse(new CourseDTO(course));

			examsDTO.add(examDTO);
		}

		return new ResponseEntity<>(examsDTO, HttpStatus.OK);
	}*/
}

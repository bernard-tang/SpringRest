package com.example.accessingdatajpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//import org.springframework.data.repository.CrudRepository;

//import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CustomerController {
	
//	@Autowired
//	UserService userService;
	
	@Autowired
	CustomerRepository repository;
	
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
//		return test("jjj", "kkk");
		///
//		String yyy = "fff";
//		Optional optTest = Optional.ofNullable(yyy);
//		return testTwo(optTest );
		///
		
		///
//		ClassA classA = new ClassA();
//		
//		String results = "";
////		List<String> values = new ArrayList<String>();
//		
//		for(ClassB b : classA.getValues()) {
//			for(ClassString classString : b.getValues()) {
//				for(String s : classString.getValues()) {
//					results += " " + s;
//				}
//			}
//		}
//		
//		return results;
		///
		
		///
//		int num = findMaxNum(1, 1000000000, 108000000);
//		
//		return String.valueOf(num);
		///
		
	}
	
	@GetMapping("/testOne")
	public String testOne() {
		return test("jjj", "kkk");
	}
	
	
	@GetMapping("/testTwo")
	public String testTwo() {
		String yyy = "fff";
		Optional optTest = Optional.ofNullable(yyy);
		return testTwo(optTest );
	}
	
	
	
	@GetMapping("/testThree")
	public String testThree() {
		ClassA classA = new ClassA();
		
		String results = "";
//		List<String> values = new ArrayList<String>();
		
		for(ClassB b : classA.getValues()) {
			for(ClassString classString : b.getValues()) {
				for(String s : classString.getValues()) {
					results += " " + s;
				}
			}
		}
		
		return results;
	}
	
	@PostMapping("/testFindMaxNum")
	public String testFindMaxNum() {
		int num = findMaxNum(1, 2, 3);
		
		return String.valueOf(num);
	}
	
	@GetMapping("/testFour")
	public String testFour() {
		String yyy = "fff";
		Optional optTest = Optional.ofNullable(yyy);
		
//		userService.saveUser(new User("Tang", "This is Tang", (long)1111));
		return "hi";
	}
	
	
	@GetMapping("/testFive")
	public String testFive() {
		
		repository.findAll().forEach(customer -> {
			System.out.println(customer.toString());
		});
		
//		userService.saveUser(new User("Tang", "This is Tang", (long)1111));
		return "hi";
	}
	
	
	public String test(String ...strTest) {
		String strResults = "Hi Greetings from Spring Boot!";
		
		for (String temp : strTest) {
			strResults += " " + temp;
		}
		return strResults;
	}
	
	public String testTwo (Optional<String> optStrTest) {
		String strResults = "HI Greetings from Spring Boot!";
		
		if(optStrTest.isPresent())
			return strResults + " " + optStrTest.get();
		
		return strResults;
	}
	
	public int findMaxNum(int x, int y, int z) {
		int maxNum = Integer.MIN_VALUE;
		
		int diff = Math.abs(x - y);
		
		int maxSteps = z / 2;
		
		for(int i = 0; i <= maxSteps; i++) {
			int newX = x+i;
			
			if(Math.abs(newX - y) <= (z - i)) {
				maxNum = Math.max(maxNum, newX);
			}
			
			newX = x - i;
			
			if(Math.abs(newX - y) <= (z - i)) {
				maxNum = Math.max(maxNum, newX);
			}
			
		}
		
		return maxNum == Integer.MIN_VALUE ? -1 : maxNum;
	}

}

class ClassA {
	List<ClassB> values = new ArrayList<ClassB>();
	
	public ClassA() {
		values.add(new ClassB());
		values.add(new ClassB());
		values.add(new ClassB());
		values.add(new ClassB());
		values.add(new ClassB());
	}
	
	public List<ClassB> getValues() {
		return values;
	}
}

class ClassB {
	List<ClassString> values = new ArrayList<ClassString>();
	
	public ClassB() {
		values.add(new ClassString());
		values.add(new ClassString());
		values.add(new ClassString());
		values.add(new ClassString());
		values.add(new ClassString());
	}
	public List<ClassString> getValues() {
		return values;
	}
}

class ClassString {
	List<String> values = new ArrayList<String>();
	
	public ClassString() {
		values.add("a");
		values.add("b");
		values.add("c");
		values.add("d");
		values.add("e");
	}
	
	public List<String> getValues() {
		return values;
	}
}

//@Service
//class UserService {
//	@Autowired
//	private UserRepository userRepository;
//	
//	public User saveUser(User user) {
//		return userRepository.save(user);
//	}
//}
//
//@Repository
//interface UserRepository extends CrudRepository<User, Long>{}

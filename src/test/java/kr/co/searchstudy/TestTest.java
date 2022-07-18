package kr.co.searchstudy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestTest {

	@Autowired
	private TestRepository testRepository;
	
	@Test
	void test() throws Exception {
		testRepository.setTestInsert();
	}

}

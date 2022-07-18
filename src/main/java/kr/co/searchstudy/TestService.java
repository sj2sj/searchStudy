package kr.co.searchstudy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepository;
	
	public Long setTestInsert() throws Exception {
		return testRepository.setTestInsert();
	}
	

}

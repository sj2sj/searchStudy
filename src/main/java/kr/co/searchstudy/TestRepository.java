package kr.co.searchstudy;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TestRepository {
	public Long setTestInsert() throws Exception;
}

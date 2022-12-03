package kr.or.ddit.book.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 * 어노테이션(@) Repository는 데이터에 접근하는 클래스임을 명시한다.
 * 해당 어노테이션이 있는 클래스는 스프링이 데이터를 관리하는 클래스라고 인지하여
 * 자바 빈(Java Bean)으로 등록해서 관리한다.
 * 
 * SqlSessionTemplate 객체를 멤버 변수로 선언하는 이유는 mapper.xml을 실행시키기 위해서이다.
 * 해당 객체 위에 어노테이션(@)을 Inject 또는 어노테이션(@) Autowired를 붙여서
 * sqlSessionTemplate 객체를 사용할 수 있도록 한다.
 * 이러한 형태를 '의존성 주입'이라고 한다. (필드 인젝션 (Field Injection))
 * 
 * SqlsessionTemplate 객체는 new 키워드를 통해 직접 생성하지 않고, 의존성 주입(Dependency Injection - DI)를 통해
 * 주입받는다. 스프링은 미리 만들어놓은 sqlSessionTemplate 타입 객체를 BookDAO 객체에 주입한다.
 * 해당 과정은 스프링에서 자동 실행되며 개발자가 직접 sqlSessionTemplate 객체를 생성하는 일 없이 곧바로 사용할 수 있다.
 * 
 * SqlSessionTemplate 객체는 root-context.xml에서 정의해둔 객체이기도 하고, 서버가 시작될 때 스프링은 미리 xml을 읽어
 * 객체를 인스턴스화 해둔다.
 */
@Repository
public class BookDAO {
	// 의존성 주입
	// Inject라는 어노테이션 => 해당 객체를 싱글톤 패턴을 적용한다 !
	/*
	 * 매퍼 XML을 실행시키기 위해서 SqlSessionTemplate 객체를 멤버 변수로 선언한다.
	 * @Inject 어노테이션을 붙여서 SqlSessionTemplate 객체를 사용할 수 있게한다.
	 * */
	@Inject	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;	
	// root-context.xml에 설정되어있음
	// 서버가 로드될 때 객체를만들어서 메모리에 올려놓는다.
	// Inject / Autowired 어노테이션을 사용하면 메모리에 올려놓은 객체를 불러서 사용한다는 의미 !!
	
	
	/*
	 * sqlSessionTemplate.insert()
	 * 1) 첫번째 파라미터는 SQL Mapper의 id이다.
	 * book_SQL.xml에서 namespace로 설정된 'Book'과 insert 쿼리를 실행하기 위해 만든 insert문의 id값
	 * 'insert'이다. mybatis는 네임스페이스 + id 조합으로 쿼리를 찾아서 실행한다.
	 * 2) 두번째 파라미터는 쿼리에 전달할 데이터이다.
	 * mapper 내 insert 쿼리를 실행하기 위해 전달되어지는 parameterType이 map이다.
	 * 
	 * 외부에서 DAO까지 map에 title, category, price가 담겨져서 넘어온다.
	 * 그리고, useGeneratedKeys와 keyProperty의 설정 덕분에 book 테이블의 pk인 book_id항목이 생긴다.
	 * */
	
	
	public int insert(Map<String, Object> map) {
		
		/*
		 * sqlSessionTemplate.insert() 의 반환값은 쿼리의 영향을 받은 행 수 (row count)이다.
		 * insert 쿼리의 경우 성공하면 1개의 행(row)이 생기므로 1을 리턴하고 실패하면 0을 리턴
		 */
		
		return sqlSessionTemplate.insert("Book.insert", map);
	}
	
	public Map<String, Object> selectBook(Map<String , Object> map){
		/*
		 * sqlSessionTemplate의 selectOne 메소드는 데이터를 한개만 가져올 때 사용
		 * 만약 쿼리 결과 행 수가 0개면 selectOne 메소드는 null을 만환하게 되고
		 * 쿼리 결과가 여러개면 TooManyResultException 예외를 던진다.
		 * 우리가 작성한 쿼리는 조건이 pk이고, pk는 무조건 행이 유일함을 보장하기 때문에
		 * 결과는 0개 아니면 1개이다
		 * */
		return sqlSessionTemplate.selectOne("Book.selectBook", map);
	}
	
	public int update(Map<String, Object> map) {
		/*
		 * sqlSessionTemplate 객체의 update 메소드는 insert 메소드와 사용법이 동일하다
		 * 첫번째 파라미터는 쿼리ID, 두번재 파라미터는 쿼리 파라미터이며 반환값은 영향받은 행 수이다
		 * 
		 * */
		return sqlSessionTemplate.update("Book.update", map);
	}
	
	public int delete(Map<String, Object> map) {
		/*
		 * sqlSessionTemplate 객체의 delete 메소드는 update 메소드와 사용법이 동일하다
		 * 첫번째 파라미터는 쿼리ID, 두번재 파라미터는 쿼리 파라미터이며 반환값은 영향받은 행 수이다
		 * 
		 * */
		return sqlSessionTemplate.delete("Book.delete", map);
	}
	
	public List<Map<String, Object>> selectList(Map<String, Object> map){
		// 쿼리 결과를 목록으로 받기 위해서는 sqlSessionTemplate.selectList를 사용할 수 있습니다.
		// 첫번째 파라미터는 쿼리ID, 두번재 파라미터는 쿼리 파라미터이다
		// 리턴 타입을 List타입으로 설정한 건, selectList 메소드의 결과가 집합 목록을 반환하기 때문이다
		return sqlSessionTemplate.selectList("Book.selectBookList",map);
		
	}
}

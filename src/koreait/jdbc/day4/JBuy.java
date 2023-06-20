package koreait.jdbc.day4;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class JBuy {
	private int buySeq;
	private String custom_id;
	private String pcode;
	private int quantity;
	private Date buyDate;
}
//필드값이 모두 같으면 equals 로 true가 되도록 하고싶다면 equals와 hashcode를 재정의 해야한다.
//결론은 vo(불변객체) 이다. vo는 테스트 케이스에서 객체를 비교할때 유용하게 사용할 수 있다
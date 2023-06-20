package koreait.jdbc.day4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString	
@AllArgsConstructor //커스텀생성자
@NoArgsConstructor //기본생성자
@Builder

public class JProductDto {
	private String pcode;
	private String category;
	private String pname;
	private int price;
	
}

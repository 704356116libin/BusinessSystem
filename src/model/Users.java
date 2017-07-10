package model;
/**
 * 用户信息
 * @author Administrator
 *
 */
public class Users {
	private String id;
	private String name;
	private String problem_id;
	private String mima;
	private String answer;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProblem_id() {
		return problem_id;
	}
	public void setProblem_id(String problem_id) {
		this.problem_id = problem_id;
	}
	public String getMima() {
		return mima;
	}
	public void setMima(String mima) {
		this.mima = mima;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}

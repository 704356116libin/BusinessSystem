package login;

public class Demo {
	public static void main(String[] args) {
		String rex="[\\p{Alnum}[%.+-@_*/]]{8,}";
		System.out.println("68w7".matches(rex));
	}
}

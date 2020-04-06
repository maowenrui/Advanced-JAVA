package Lab3;

@Table("user")
public class User {

	@Column(ColumnName ="id")
	private Integer id = null;
	
	@Column(ColumnName ="username")
	private String username = null;
	
	@Column(ColumnName ="age")
	private Integer age =null;
	
	@Column(ColumnName ="email")
	private String email = null;
	
	@Column(ColumnName ="telephone")
	private String telephone = null;

	public void setId(int i) {
		// TODO Auto-generated method stub
		this.id = i;
		
	}
	
	public  Integer getId() {
		return this.id;
	}

	public void setUsername(String string) {
		// TODO Auto-generated method stub
		this.username = string;
		
	}

	public String getUsername() {
		return this.username;
	}
	
	public void setTelephone(String string) {
		// TODO Auto-generated method stub
		this.telephone = string;
		
	}
	
	public String getTelephone() {
		return this.telephone;
	}

	public void setEmail(String string) {
		// TODO Auto-generated method stub
		this.email = string;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setAge(int i) {
		// TODO Auto-generated method stub
		this.age = i;
		
	}
	
	public Integer getAge() {
		return this.age;
	}
	
}

package ex07;

public class ServiceImpl implements Service {
	
	private Emp emp;
	
	
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}


	@Override
	public void test1() {
		System.out.println("name : "+emp.getName());
		System.out.println("dept : "+emp.getDept());
		System.out.println("salary : "+emp.getSalary());
	}

}

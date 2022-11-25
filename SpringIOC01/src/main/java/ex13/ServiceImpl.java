package ex13;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;


/* @Value :�⺻�ڷ���, String ������ ������ �� ���
 * @Autowired : byType���� �����Ѵ�. �ڷ������� ���� ��ü�� ������ �����Ѵ�. ã�¼���: �ڷ���> @Qualifier("���̸�")
 * @Resource  : byName���� �����Ѵ�.
 * @Inject : ==>pom.xml�� ���̺귯���� ����ؾ� ��� �����ϴ�. byType> @Qualifier
 * 
 * */

public class ServiceImpl implements Service {
	
	//@Autowired
	//@Qualifier("emp1")
	@Resource(name = "emp1")
	private Emp emp;
	
	@Autowired
	private Member user;
	
	@Override
	public void test1() {
		System.out.println("--------emp����----------");
		System.out.println(emp);

	}

	@Override
	public void test2() {
		System.out.println("-------------user����----------");
		user.showInfo();
	}

}

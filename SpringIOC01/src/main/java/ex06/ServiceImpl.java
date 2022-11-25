package ex06;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ServiceImpl implements Service {
	
	private List<String> list;
	private Map<String,Integer> map;
	private Set<String> set;
	private Properties prop;
	
	@Override
	public void test1() {
		System.out.println("----list----");
		for(String str : list) {
			System.out.println(str);
		}
	}

	@Override
	public void test2() {
		System.out.println("----map-----");
		Set<String> keySet=map.keySet();
		for(String str:keySet) {
			System.out.println(str+" : "+map.get(str));
		}

	}

	@Override
	public void test3() {
		System.out.println("----set----");
		for(String str : set) {
			System.out.println(str);
		}
	}

	@Override
	public void test4() {
		System.out.println("----properties----");
		System.out.println("user :"+prop.getProperty("user"));
		System.out.println("passwd :"+prop.getProperty("passwd"));
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
}

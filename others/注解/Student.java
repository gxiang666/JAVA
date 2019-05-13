import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Student {
	private String stuName;
	private String stuAddress;
	private int stuAge;

	public Student(String stuName, String stuAddress, int stuAge) {
		super();
		this.stuName = stuName;
		this.stuAddress = stuAddress;
		this.stuAge = stuAge;
	}

	public Student(String stuName, String stuAddress) {
		super();
		this.stuName = stuName;
		this.stuAddress = stuAddress;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuAddress() {
		return stuAddress;
	}

	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}

	public int getStuAge() {
		return stuAge;
	}

	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}

	@Override
	public String toString() {
		return "Student [stuName=" + stuName + ", stuAddress=" + stuAddress + ", stuAge=" + stuAge + "]";
	}

	@MyAnnotation(paraName = "小明2", paraAddress = "天津")
	public void getStuInfo() {
		parseAnnotation();
	}

	/**
	 * [眼前不做强制要求]
	 * 注解解析的业务需求： 使用注解给定的两个参数的值作为Student类的构造方法的两个参数，构建一个Student类的实例对象，
	 * 并调用该类的toString方法输出该实例对象的内容
	 */
	private void parseAnnotation() {
		// 获取注解所在类的Class实例，通过类的Class实例，获取Mehtod实例，通过Method获取注解对象
		try {
			Class<?> clazzStu = Class.forName("com.yuw.annotationdemo.Student");
			// 获取方法对象
			Method method = clazzStu.getMethod("getStuInfo");
			// 获取注解对象
			MyAnnotation myA = method.getDeclaredAnnotation(MyAnnotation.class);
			// 获取自定义注解的属性值
			System.out.println();
			myA.paraAddress(); // 取到的值为 ：天津
			myA.paraName(); // 取到的值为：小明

			// 获取Student类的构造方法
			Constructor con = clazzStu.getDeclaredConstructor(String.class, String.class);
			// 使用注解的两个属性的值，构建一个Student类的实例对象
			Object stu0 = con.newInstance(myA.paraName(), myA.paraAddress());

			// 获取Student类的toString方法对象
			Method m = clazzStu.getMethod("toString");
			// 反射调用给方法
			Object objResult = m.invoke(stu0);
			// 输出反射调用的方法结果
			System.out.println("学生信息为：" + objResult);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
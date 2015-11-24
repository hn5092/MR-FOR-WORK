package test.singal;

public enum FooEnumSingleton {
	INSTANCE;
	public static FooEnumSingleton getInstance() {
		return INSTANCE;
	}
	
	public void bar() {
		System.out.println("go");
	}
}
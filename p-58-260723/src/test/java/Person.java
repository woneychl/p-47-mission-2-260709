public class Person {
    
    public Person() {
        System.out.println("기본 생성자");
    }

    public Person(int age, String name) {
        System.out.println("매개변수 2개인 생성자");
    }

    public void introduce() {
        System.out.println("안녕하세요");
    }

    public void introduce(String name) {
        System.out.println("안녕하세요 %s입니다.".formatted(name));
    }

    public void introduce(int age) {
        System.out.println("안녕하세요 %d세입니다.".formatted(age));
    }

    public void introduce(String name, int age) {
        System.out.println("안녕하세요 %d세 %s입니다.".formatted(age, name));
    }
}

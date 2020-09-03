package swingy.view;

public class Console extends Output implements Viewable {
	public void welcomeMsg() {
		System.out.println(super.welcome());
	}
}

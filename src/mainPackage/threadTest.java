package assign6package;

public class threadTest extends Thread{

	public static void main(String[] args) {
		recursion("Main thread");
	}

	static int counter = 0;
	
	public static void recursion (String string) {
		System.out.println(string);
		
		
		Thread threadA = new Thread() {
			public void run() {
				recursion("thread A " +counter);
				
			}
		};
		
		Thread threadB = new Thread() {
			public void run() {
				try {
					threadA.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				recursion("thread B " +counter);
				
			}
		};
		
		if (counter < 10) {
			counter++;
			threadA.start();
			threadB.start();
		}
		
			try {
			threadA.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			try {
				threadB.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		
		
		
		
		
	}
}

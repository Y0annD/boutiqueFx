package fr.y0annd.boutique.app;

import javafx.concurrent.Task;

public class CountTask extends Task<Integer> {
	
	private int mTarget;
	
	public CountTask(int target)  {
		mTarget = target;
	}

	@Override
	protected Integer call() throws Exception {
		int value = 0;
		
		while(value < mTarget) {
			value++;
			updateProgress(value, mTarget);
			updateMessage(Integer.valueOf(value).toString());
			if(isCancelled()) {
				return value;
			}
			System.out.println(value);
		}
		return value;
	}

}

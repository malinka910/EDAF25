package model;

public class SlotFactory {
	
	public SlotFactory(){
		
	}
	
	public Slot build(String content){
		Slot s = null;
		char c;
		try{
			if(content.charAt(0) == '#'){
				c = 2;
			}else{
				c = 1;
			}
		}catch(Exception e){
			c = 0;
		}
		
		switch(c){
		
		case 0: s = new DummySlot();
		break;
		
		case 1: s = new ExpressionSlot(content);
		break;
		
		case 2: s = new CommentSlot(content);
		break;
		
		}
		return s;
	}

}

package wordladders;

public class OneLetterDifference implements GraphStrategy {

	@Override
	public boolean adjacent(String word1, String word2) {
		int nbrDifferent = 0;
		char[] w1 = word1.toCharArray();
		char[] w2 = word2.toCharArray();
			for(int i = 0 ; i < 5 ; i++){
				if(w1[i] != w2[i]){
					nbrDifferent++;
				}
				if(nbrDifferent >= 2){
					return false;
				}
			}
		if(nbrDifferent == 1){
			return true;
		}else{
			return false;
		}
	}

}

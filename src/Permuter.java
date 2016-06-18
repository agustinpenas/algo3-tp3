import java.util.ArrayList;
import java.util.List;

public class Permuter {

	private List<List<Integer>> permutations;
	private Long i;
	
	public List<List<Integer>> permute(List<Integer> nums) {
	    List<List<Integer>> results = new ArrayList<List<Integer>>();
	    if(nums == null || nums.size() == 0) return results;
	    List<Integer> result = new ArrayList<>();
	    dfs(nums, results, result);
	    return results;
	}

	public void dfs(List<Integer> nums, List<List<Integer>> results, List<Integer> result){
	    if(nums.size() == result.size()){
	        List<Integer> temp = new ArrayList<>(result);
	        results.add(temp);
	    }        
	    for(int i=0; i<nums.size(); i++){
	        if(result.contains(nums.get(i))) continue; 
	        result.add(nums.get(i));
	        dfs(nums, results, result);
	        result.remove(result.size()-1);
	    }
	}

	public Permuter(List<Integer> ar) {
		super();
		this.permutations = permute(ar);
		this.i= new Long(0);
	}
	public Permuter() {
		super();
		this.i= new Long(0);
	}
	
	public List<Integer> getNextPermutation(){
		if(i>=permutations.size()){
			return null;
		}
		i++;
		return permutations.get(i.intValue()-1);
	}
	
	public boolean hasNextPermutation(){
		return i<permutations.size();
	}
	
	public void reset(){
		i = new Long(0);
	}
	
}

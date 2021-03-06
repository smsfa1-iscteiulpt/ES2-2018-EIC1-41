package generic;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Variable is the class that creates a variable. It has various characteristics:
 * variable name, variable type, variable interval, where the user can choose
 * the minimum value and the maximum value, and the variable weight, because
 * there are variables that are more important then others.
 * 
 * @author Nuno Fialho EIC1 72910
 * @author Sandro Ferreira EIC1 72911
 * @author Duarte Pinto EIC1 73117
 *
 */

@XmlRootElement(name="Variable")
public class Variable {
	private String name;
	private String type;
	private int min;
	private int max;
	private String weight;
	
	
	public Variable() {
	}
	
	public Variable(String name, String type, int min, int max, String weight){
		this.name=name;
		this.type=type;
		this.min = min;
		this.max = max;
		this.weight = weight;
	}
	
	@XmlElement(name="Name")
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@XmlElement(name="Weight")
	public String getWeight() {
		return weight;
	}
   
	@XmlElement(name="Min")
	public int getMin(){
		return min;
	}
   
	
	@XmlElement(name="Max")
	public int getMax(){
		return max;
	}
	
	@XmlElement(name="Type")
	public String getType(){
		return type;
	}  	
	
	public Object[] getVector(){
		Object[] x ={this.name,this.weight} ;
		return x;
	}

}
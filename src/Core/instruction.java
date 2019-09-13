package Core;
public class instruction {
	int stage;
	String name;
	int startTime;
	int finTime;
	boolean New=true;
	boolean finished=false;
	int fetchedTime;
	String rs;
	String rt;
	public instruction(String indBit,String opCode,int start,String rs,String rt) {
		stage=2;
		
		switch(indBit+opCode) {
		case "0000":name="ADD";break;
		case "0001":name="OR";break;
		case "0010":name="NOT";break;
		case "0011":name="SLR";break;
		case "0100":name="KLT";break;
		case "0101":name="BUN dir";break;
		case "1101":name="BUN ind";break;
		case "0110":name="LW";break;
		case "0111":name="SW";break;
		default:name="default";
		}
		startTime=start;
		finTime=start;
		this.rs=rs;
		this.rt=rt;
	}
	
	public instruction(int stage,String name) {
		this.stage=stage;
		this.name=name;
	}
	
	public String toString() {
		return "startTime "+startTime+" / fintime "+finTime+" / stage no : "+stage+" / Instr. name : "+name +" / rs : "+rs+ " / rt : " +rt; 
	}
}

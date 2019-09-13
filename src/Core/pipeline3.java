package Core;
import java.util.LinkedList;
import java.util.Queue;

public class pipeline3 {
	Queue<String> address = new LinkedList<>();
	Queue<String> readData2 = new LinkedList<>();
	Queue<String> MemWrite = new LinkedList<>();
	Queue<String> MemRead = new LinkedList<>();
	Queue<String> ALUResult = new LinkedList<>();
	Queue<String> writeReg = new LinkedList<>();
	Queue<String> MemToReg = new LinkedList<>();
	Queue<String> RegWrite = new LinkedList<>();
	
	public pipeline3() {
		
	}
	public String toString() {
		return "address"+ address+"\n"+
			   "readData2"+ readData2+"\n"+
			   "MemWrite"+MemWrite+"\n"+
			   "MemRead"+MemRead+"\n"+
			   "ALUResult"+ALUResult+"\n"+
			   "writeReg"+writeReg+"\n"+
			   "MemToReg"+MemToReg+"\n"+
			   "RegWrite"+RegWrite+"\n";
	}
}

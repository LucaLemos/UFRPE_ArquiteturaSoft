package arquitetura.mips;

public class decodificador {
	
	private String[][] arrays = { {"000000", "R"}, {"001000", "I", "addi"}, {"001001", "I", "addiu"}, {"001100", "I", "andi"}, 
			{"000111", "I", "bgtz"}, {"000100", "I", "beq"}, {"000001", "I", "bltz"}, {"000110", "I", "blez"}, 
			{"000101", "I", "bne"}, {"000010", "J", "j"}, {"000011", "J", "jal"}, {"100000", "I", "lb"}, 
			{"100100", "I", "lbu"}, {"001111", "I", "lui"}, {"100011", "I", "lw"}, {"001101", "I", "ori"}, 
			{"101000", "I", "sb"}, {"001010", "I", "slti"}, {"101011", "I", "sw"}, {"001110", "I", "xori"} };
	
	private String[][] functionR  = { {"100000", "add"}, {"100001", "addu"}, {"100100", "and"}, {"011010", "div"}, 
			{"011011", "divu"}, {"001000", "jr"}, {"010000", "mfhi"}, {"010010", "mflo"}, 
			{"011000", "mult"}, {"011001", "multu"}, {"100111", "nor"}, {"100101", "or"}, 
			{"000000", "sll"}, {"000100", "sllv"}, {"101010", "slt"}, {"000011", "sra"}, 
			{"000111", "srav"}, {"000010", "srl"}, {"000110", "srlv"}, {"100010", "sub"},
			{"100011", "subu"}, {"001100", "syscall"}, {"100110", "xor"} };
	
	public decodificador() {
	}
	
	public String buscarTipo (String opCode) {
		for(int i = 0; i < arrays.length; i++){
		    if (arrays[i][0].equals(opCode)){
		        return arrays[i][1];
		    }
		}
		return "Null";
	}
	
	public String buscarInst (String opCode) {
		for(int i = 0; i < arrays.length; i++){
		    if (arrays[i][0].contentEquals(opCode)){
		        if(!arrays[i][1].equals("R")) {
		        	return arrays[i][3];
		        }
		    }
		}
		return "Null";
	}
	
	public String buscarInstR (String function) {
		for(int i = 0; i < functionR.length; i++){
		    if (functionR[i][0].equals(function)){
		        return functionR[i][1];
		    }
		}
		return "Null";
	}

}

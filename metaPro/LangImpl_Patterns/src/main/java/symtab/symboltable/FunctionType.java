package symtab.symboltable;

import java.util.List;

public class FunctionType implements Type {
	protected final Type returnType;
	protected final List<Type> argumentTypes;

	public FunctionType(Type returnType, List<Type> argumentTypes) {
		this.returnType = returnType;
		this.argumentTypes = argumentTypes;
	}

	@Override
	public String getName() {
		return toString();
	}

	@Override
	public int getTypeIndex() {
		return -1;
	}

	public List<Type> getArgumentTypes() {
		return argumentTypes;
	}

	@Override
	public String toString() {
		return "*"+ returnType;
	}
}

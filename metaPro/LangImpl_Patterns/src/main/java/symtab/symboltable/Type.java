package symtab.symboltable;

/**
 * Each type has a name.
 *
 * The types are typically things
 * like primitive types and struct.
 *
 * TODO: what are type trees in C?
 */
public interface Type {
	String getName();

	/**
	 * It is useful during type computation and code generation
	 * to assign an int index to the primitive types
	 * and possibly user-defined types like structs.
	 *
	 * @return Return 0-indexed type index,
	 * else -1 if no index.
	 */
	int getTypeIndex();
}
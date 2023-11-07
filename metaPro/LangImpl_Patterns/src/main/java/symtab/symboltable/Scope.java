package symtab.symboltable;

import java.util.List;
import java.util.Set;

/**
 * Each scope has a name and its enclosing scope.
 */
public interface Scope {
	/**
	 * "Global", "Local", function name, struct name, ...
	 */
	String getName();

	Scope getEnclosingScope();

	void setEnclosingScope(Scope s);

	/**
	 * Define a symbol in this scope,
	 * throw IllegalArgumentException
	 * if sym already defined in this scope.
	 *
	 * This sets sym's scope to be the scope.
	 */
	void define(Symbol sym) throws IllegalArgumentException;

	Symbol resolve(String name);

	Symbol getSymbol(String name);

	/**
	 * Add a nested local scope to this scope;
	 * it's like define() but for non SymbolWithScope objects.
	 * E.g., a FunctionSymbol will add a LocalScope for its block
	 * via this method.
	 *
	 *  @throws IllegalArgumentException if you pass in a SymbolWithScope.
	 */
	void nest(Scope scope) throws IllegalArgumentException;

	/**
	 * Return a list of scopes nested within this scope.
	 * It has both ScopedSymbols and scopes without symbols, such as LocalScopes.
	 * This returns a superset or same set as {@link #getNestedScopedSymbols}.
	 * ScopedSymbols come first then all non-ScopedSymbols Scope objects.
	 * Insertion order is used within each sublist.
	 */
	List<Scope> getNestedScopes();

	// ------------ Convenience methods --------------------------------
	/**
	 * Return (inclusive) list of all scopes on path to root scope.
	 * The first element is the current scope and the last is the root scope.
	 */
	List<Scope> getEnclosingPathToRoot();

	/**
	 * Return all immediately enclosed scoped symbols in insertion order.
	 * E.g., a class would return all nested classes and any methods.
	 * There does not have to be an explicit pointer to the nested scopes.
	 * This method generally searches the list of symbols
	 * looking for symbols that implement Scope.
	 * Gets only those scopes that are in the symbols list of "this" scope.
	 * E.g., does not get local scopes within a function.
	 * This returns a subset or same set as {@link #getNestedScopes}.
	 */
	List<Scope> getNestedScopedSymbols();

	List<Symbol> getSymbols();

	/**
	 * Return all symbols found in all nested scopes.
	 * The order of insertion into the scope is the order
	 * returned in this list for each scope.
	 * The scopes are traversed in the order in which they are encountered in the input.
	 */
	List<Symbol> getAllSymbols();

	Set<String> getSymbolNames();

	int getNumberOfSymbols();

	/**
	 * Return scopes from to current with separator in between
	 */
	public String toQualifierString(String separator);
}
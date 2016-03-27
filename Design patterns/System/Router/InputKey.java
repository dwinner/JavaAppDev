public class InputKey implements InputChannel{
    private static int nextValue = 1;
    private int hashVal = nextValue++;
    public int hashCode(){ return hashVal; }
    public boolean equals(Object object){
        if (!(object instanceof InputKey)){ return false; }
        if (object.hashCode() != hashCode()){ return false; }
        return true;
    }
}
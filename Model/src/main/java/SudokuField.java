import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private static final long serialVersionUID = 2381974911264841282L;
    
    private int value;

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return value;
    }

    public String toString() {
        return new ToStringBuilder(this).
                append("value", value).
                toString();
    }

    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder().
                append(value, rhs.value).
                isEquals();
    }

    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(47, 67).
                append(value).
                toHashCode();
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(final SudokuField other) {
        return Integer.compare(this.value, other.value);
    }
}

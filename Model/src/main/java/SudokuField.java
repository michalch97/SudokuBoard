import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SudokuField implements Externalizable, Cloneable, Comparable<SudokuField> {
    private static final long serialVersionUID = 2381974911264841282L;

    private IntegerProperty value;
    private boolean isUserProvidedField;

    public SudokuField() {
        this(0, false);
    }
    
    public SudokuField(int value) {
        this(value, false);
    }

    public SudokuField(int value, boolean isUserProvidedField) {
        this.value = new SimpleIntegerProperty(value);
        this.isUserProvidedField = isUserProvidedField;
    }

    public int getFieldValue() {
        return value.get();
    }

    public boolean getIsUserProvidedField() {
        return isUserProvidedField;
    }
    
    public IntegerProperty getProperty() {
        return value;
    }

    public boolean isValid() {
        if (value.get() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {
        return new ToStringBuilder(this).
                append("value", value).
                append("isUserValue", isUserProvidedField).
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
                append(value.get(), rhs.value.get()).
                append(isUserProvidedField, rhs.isUserProvidedField).
                isEquals();
    }

    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(47, 67).
                append(value.get()).
                append(isUserProvidedField).
                toHashCode();
    }

    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }

    @Override
    public int compareTo(final SudokuField other) {
        return Integer.compare(this.value.get(), other.value.get());
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
         out.writeInt(value.get());
         out.writeBoolean(isUserProvidedField);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        value = new SimpleIntegerProperty(in.readInt());
        isUserProvidedField = in.readBoolean();
    }
}

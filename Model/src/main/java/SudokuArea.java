import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public abstract class SudokuArea {
    protected static final int AREA_SIZE = 9;
    protected List<SudokuField> containedFields;

    public SudokuArea(final List<SudokuField> fields) {
        containedFields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < AREA_SIZE; i++) {
            for (int j = 0; j < AREA_SIZE; j++) {
                if (containedFields.get(i) == null || containedFields.get(j) == null) {
                    continue;
                }

                if (i != j && containedFields.get(i).getFieldValue() == containedFields.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<SudokuField> getFields() {
        return containedFields;
    }

    public String toString() {
        return new ToStringBuilder(this).
                append("AREA_SIZE", AREA_SIZE).
                append("containedFields", containedFields).
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
        SudokuArea rhs = (SudokuArea) obj;
        return new EqualsBuilder().
                append(containedFields, rhs.containedFields).
                isEquals();
    }

    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(27, 47).
                append(containedFields).
                toHashCode();
    }
}

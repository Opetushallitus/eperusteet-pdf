package fi.vm.sade.eperusteet.pdf.dto.eperusteet;
public interface StructurallyComparable<T> {
    boolean structureEquals(T other);
}

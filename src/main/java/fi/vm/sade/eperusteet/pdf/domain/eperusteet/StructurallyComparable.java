package fi.vm.sade.eperusteet.pdf.domain.eperusteet;

public interface StructurallyComparable<T> {
    boolean structureEquals(T other);
}

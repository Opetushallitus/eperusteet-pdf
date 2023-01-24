package fi.vm.sade.eperusteet.pdf.domain;

public interface StructurallyComparable<T> {
    boolean structureEquals(T other);
}

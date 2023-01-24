package fi.vm.sade.eperusteet.eperusteetpdfservice.domain;

public interface StructurallyComparable<T> {
    boolean structureEquals(T other);
}

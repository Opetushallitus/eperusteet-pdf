package fi.vm.sade.eperusteet.pdf.repository;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DokumenttiRepository extends JpaRepository<Dokumentti, Long> {
    Optional<Dokumentti> findById(Long id);

    List<Dokumentti> findBySisaltoIdAndRevisionAndKieli(Long sisaltoId, Integer revision, Kieli kieli, Sort sort);
}

package fi.vm.sade.eperusteet.eperusteetpdfservice.repository;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.Dokumentti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DokumenttiRepository extends JpaRepository<Dokumentti, Long> {
    Optional<Dokumentti> findById(Long id);

//    List<Dokumentti> findByPerusteIdAndKieliAndTilaAndSuoritustapakoodiAndGeneratorVersion(
//            Long perusteId,
//            Kieli kieli,
//            DokumenttiTila tila,
//            Suoritustapakoodi suoritustapakoodi,
//            GeneratorVersion version,
//            Sort sort
//    );
//    List<Dokumentti> findByPerusteIdAndKieliAndTilaAndGeneratorVersion(
//            Long perusteId,
//            Kieli kieli,
//            DokumenttiTila tila,
//            GeneratorVersion version,
//            Sort sort
//    );
}

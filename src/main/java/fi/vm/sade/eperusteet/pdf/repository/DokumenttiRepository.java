package fi.vm.sade.eperusteet.pdf.repository;

import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DokumenttiRepository extends JpaRepository<Dokumentti, Long> {
    Optional<Dokumentti> findById(Long id);

    List<Dokumentti> findBySisaltoIdAndRevisionAndKieli(Long sisaltoId, Integer revision, Kieli kieli, Sort sort);

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

package fi.vm.sade.eperusteet.pdf.utils;

import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019OppiaineJarjestysDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019PerustePaikallinenOppiaineDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019SortableOppiaineDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019SortablePaikallinenOppiaineDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class Lops2019Utils {
    public static void sortOppiaineet(
            Set<Lops2019OppiaineJarjestysDto> oppiaineJarjestykset,
            List<? extends Lops2019SortableOppiaineDto> oppiaineet,
            List<? extends Lops2019SortablePaikallinenOppiaineDto> paikallisetOppiaineet,
            Function<Lops2019SortableOppiaineDto, Boolean> oaFunction,
            Function<Lops2019SortablePaikallinenOppiaineDto, Boolean> poaFunction
    ) {

        Map<String, Lops2019PerustePaikallinenOppiaineDto> oppiaineJarjestyksetMap = new HashMap<>();

        if (oppiaineJarjestykset != null) {
            for (Lops2019OppiaineJarjestysDto oppiaineJarjestys : oppiaineJarjestykset) {
                String koodi = oppiaineJarjestys.getKoodi();
                Integer jarjestys = oppiaineJarjestys.getJarjestys();
                if (oppiaineJarjestyksetMap.containsKey(koodi)) {
                    Lops2019PerustePaikallinenOppiaineDto dto = oppiaineJarjestyksetMap.get(koodi);
                    dto.setJarjestys(jarjestys);
                } else {
                    Lops2019PerustePaikallinenOppiaineDto dto = new Lops2019PerustePaikallinenOppiaineDto();
                    dto.setJarjestys(jarjestys);
                    oppiaineJarjestyksetMap.put(koodi, dto);
                }
            }
        }

        // Perusteen oppiaineet
        if (oppiaineet != null) {
            oppiaineet.forEach(oa -> {
                String koodi = (oa).getKoodi().getUri();
                if (oppiaineJarjestyksetMap.containsKey(koodi)) {
                    Lops2019PerustePaikallinenOppiaineDto dto = oppiaineJarjestyksetMap.get(koodi);
                    dto.setOa(oa);
                } else {
                    Lops2019PerustePaikallinenOppiaineDto dto = new Lops2019PerustePaikallinenOppiaineDto();
                    dto.setOa(oa);
                    oppiaineJarjestyksetMap.put(koodi, dto);
                }
            });
        }

        // Paikalliset oppiaineet
        if (paikallisetOppiaineet != null) {
            paikallisetOppiaineet.forEach(poa -> {
                String koodi = poa.getKoodi();
                if (oppiaineJarjestyksetMap.containsKey(koodi)) {
                    Lops2019PerustePaikallinenOppiaineDto dto = oppiaineJarjestyksetMap.get(koodi);
                    dto.setPoa(poa);
                } else {
                    Lops2019PerustePaikallinenOppiaineDto dto = new Lops2019PerustePaikallinenOppiaineDto();
                    dto.setPoa(poa);
                    dto.setPaikallinen(true);
                    oppiaineJarjestyksetMap.put(koodi, dto);
                }
            });
        }

        // Paikalliset ja perusteen oppiaineet
        oppiaineJarjestyksetMap.values().stream()
                .sorted(comparing(dto -> dto.getOa() != null
                        ? dto.getOa().getKoodi().getUri()
                        : (dto.getPoa() != null ? dto.getPoa().getKoodi() : "")))
                .sorted(comparing(Lops2019PerustePaikallinenOppiaineDto::isPaikallinen))
                .sorted(comparing((Lops2019PerustePaikallinenOppiaineDto dto) -> Optional
                        .ofNullable(dto.getJarjestys()).orElse(Integer.MAX_VALUE)))
                .forEach(dto -> {
                    if (dto.getOa() != null) {
                        oaFunction.apply(dto.getOa());
                    } else if (dto.getPoa() != null) {
                        poaFunction.apply(dto.getPoa());
                    }
                });
    }
}

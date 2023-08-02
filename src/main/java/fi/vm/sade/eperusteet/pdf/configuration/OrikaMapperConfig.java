package fi.vm.sade.eperusteet.pdf.configuration;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.ArviointiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimuksenKohdealueDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.PaikallisetAmmattitaitovaatimukset2019Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Arviointi2020Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.GeneerinenArviointiasteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.GeneerinenArviointiasteikkoKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ammattitaitovaatimukset.AmmattitaitovaatimusKohdealueetDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.Ammattitaitovaatimukset2019Dto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaMapperConfig {

    @Bean
    public MapperFacade mapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Arviointi2020Dto.class, GeneerinenArviointiasteikkoKaikkiDto.class).byDefault();
        mapperFactory.classMap(GeneerinenArviointiasteikkoDto.class, GeneerinenArviointiasteikkoKaikkiDto.class).byDefault();
        mapperFactory.classMap(PaikallisetAmmattitaitovaatimukset2019Dto.class, Ammattitaitovaatimukset2019Dto.class).byDefault();
        mapperFactory.classMap(ArviointiDto.class, fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi.ArviointiDto.class).byDefault();
        mapperFactory.classMap(AmmattitaitovaatimusKohdealueetDto.class, AmmattitaitovaatimuksenKohdealueDto.class).byDefault();

        return mapperFactory.getMapperFacade();
    }
}

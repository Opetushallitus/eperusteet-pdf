package fi.vm.sade.eperusteet.pdf.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fi.vm.sade.eperusteet.pdf.dto.common.AbstractRakenneOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.common.RakenneModuuliDto;
import fi.vm.sade.eperusteet.pdf.dto.common.RakenneOsaDto;

import java.io.IOException;

public class AbstractRakenneOsaDeserializer extends StdDeserializer<AbstractRakenneOsaDto> {

    public AbstractRakenneOsaDeserializer() {
        super(AbstractRakenneOsaDto.class);
    }

    @Override
    public AbstractRakenneOsaDto deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final TreeNode tree = jp.readValueAsTree();
        final ObjectCodec codec = jp.getCodec();
        TreeNode erikoisuus = tree.get("erikoisuus");
        TreeNode tosaviite = tree.get("_tutkinnonOsaViite");
        TreeNode osat = tree.get("osat");

        if (tosaviite != null || erikoisuus != null) {
            return codec.treeToValue(tree, RakenneOsaDto.class);
        }
        if (osat != null) {
            return codec.treeToValue(tree, RakenneModuuliDto.class);
        }
        throw new JsonMappingException(jp, "Tuntematon rakenneosan", jp.getCurrentLocation());
    }
}

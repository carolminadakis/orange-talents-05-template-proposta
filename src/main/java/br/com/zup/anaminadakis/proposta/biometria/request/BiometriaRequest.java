package br.com.zup.anaminadakis.proposta.biometria.request;

import br.com.zup.anaminadakis.proposta.biometria.model.Biometria;
import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;


import javax.validation.constraints.NotBlank;
import java.util.Base64;


public class BiometriaRequest {

    @NotBlank
    private String fingerprint;

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria converteBiometria(Cartao cartao) {

        Base64.getDecoder().decode(this.fingerprint);
        return new Biometria(cartao, getFingerprint());
    }
}


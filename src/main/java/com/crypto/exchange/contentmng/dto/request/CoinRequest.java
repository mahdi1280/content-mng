package com.crypto.exchange.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CoinRequest implements Serializable {

    private final String name;
    private final String code;
    private final String title;
    private final transient MultipartFile logo;
    private final long currencyTypeId;

    @JsonCreator
    public CoinRequest(String name, String code, String title, MultipartFile logo, long currencyTypeId) {
        this.name = name;
        this.code = code;
        this.title = title;
        this.logo = logo;
        this.currencyTypeId = currencyTypeId;
    }

    @NotNull(message = "{coin.request.name.null}")
    @NotBlank(message = "{coin.request.name.blank}")
    @Size(min = 1, max = 60, message = "{coin.request.name.size}")
    public String getName() {
        return name;
    }

    @NotNull(message = "{coin.request.code.null}")
    @NotBlank(message = "{coin.request.code.blank}")
    @Size(min = 1, max = 60, message = "{coin.request.code.size}")
    public String getCode() {
        return code;
    }

    @NotNull(message = "{coin.request.title.null}")
    @NotBlank(message = "{coin.request.title.blank}")
    @Size(min = 1, max = 60, message = "{coin.request.title.size}")
    public String getTitle() {
        return title;
    }

    @NotNull(message = "{coin.request.logo.null}")
    public MultipartFile getLogo() {
        return logo;
    }

    @Min(value = 1,message = "{currency.type.id.valid}")
    public long getCurrencyTypeId() {
        return currencyTypeId;
    }
}

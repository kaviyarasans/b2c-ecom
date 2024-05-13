package com.sentryc.ecom.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.graphql.data.method.annotation.Argument;

@Data
public class PageInput {

    private int page;

    private int size;
}

package com.sentryc.ecom.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageInput {

    private int page;

    private int size;
}
